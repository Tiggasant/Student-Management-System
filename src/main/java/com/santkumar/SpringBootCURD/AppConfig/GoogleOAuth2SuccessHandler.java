package com.santkumar.SpringBootCURD.AppConfig;



import com.santkumar.SpringBootCURD.repository.UserRepository;
import com.santkumar.SpringBootCURD.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class GoogleOAuth2SuccessHandler
        implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final boolean cookieSecure;
    private final String successUrl;

    public GoogleOAuth2SuccessHandler(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            @Value("${app.jwt.cookie-secure:false}")
            boolean cookieSecure,
            @Value("${app.oauth2.success-url}")
            String successUrl ) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.cookieSecure = cookieSecure;
        this.successUrl = successUrl;
    }

    @Override
    public void onAuthenticationSuccess(
            @NonNull HttpServletRequest request,
            @NonNull  HttpServletResponse response,
            Authentication authentication
    )
            throws IOException, ServletException {

              // name,  email   checks then assign into googleUser
        if (!(authentication.getPrincipal() instanceof OidcUser googleUser)) {
            throw new ServletException("Expected a Google OIDC user");
        }

        String subject = googleUser.getSubject();
        String email = googleUser.getEmail();
        Boolean verified = googleUser.getClaim("email_verified");

        if (email == null || !Boolean.TRUE.equals(verified)) {
            throw new ServletException(
                    "A verified Google email is required");
        }

        // user from database
        User user = userRepository
                .findByProviderAndProviderSubject("GOOGLE", subject)
                .orElseGet(() -> createGoogleUser(email, subject));

        String token = jwtService.generateToken(user.getUsername());

        Cookie cookie = new Cookie("access_token", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(cookieSecure);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60);
        cookie.setAttribute("SameSite", "Lax");

        response.addCookie(cookie);
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Referrer-Policy", "no-referrer");

        response.sendRedirect(successUrl);
    }

 // user not exist then we need to create
    private User createGoogleUser(String email, String subject) {

        if (userRepository.findByEmail(email).isPresent()
                || userRepository.findByUsername(email).isPresent()) {
            throw new OAuth2AuthenticationException(
                    new OAuth2Error("account_conflict"),
                    "An account already exists for this email. "
                            + "Sign in using the existing account.");
        }

        User user = new User();
        user.setUsername(email);
        user.setEmail(email);
        user.setProvider("GOOGLE");
        user.setProviderSubject(subject);
        user.setPassword(passwordEncoder.encode(
                UUID.randomUUID().toString()));

        return userRepository.save(user);
    }
}
