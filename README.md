# Student Management System

A RESTful Student Management System built using Java, Spring Boot, Spring Data JPA, Hibernate, and PostgreSQL. The application provides student record management APIs and authentication using JWT and Google OAuth2 with Spring Security.

## Features

- **Student CRUD Operations:** Create, retrieve, update, and delete student records.
- **Soft Delete:** Mark student records as deleted without immediately removing them from the database.
- **RESTful APIs:** Expose endpoints for student management and authentication.
- **JWT Authentication:** Support user registration and login using JSON Web Tokens.
- **Google OAuth2 Login:** Authenticate users through their Google accounts.
- **Spring Security:** Secure application endpoints with authentication and authorization rules.
- **Password Encryption:** Use BCrypt password encoding for user passwords.
- **DTO Pattern:** Separate request and response objects from database entities.
- **Input Validation:** Validate incoming data using Jakarta Bean Validation.
- **Global Exception Handling:** Handle application errors and return meaningful API responses.
- **PostgreSQL Integration:** Persist student and user information in a relational database.

## Tech Stack

| Technology | Purpose |
|---|---|
| Java | Backend programming |
| Spring Boot | Application development |
| Spring Web | REST API development |
| Spring Security | Authentication and authorization |
| Spring Data JPA | Database access |
| Hibernate | ORM and entity mapping |
| PostgreSQL | Relational database |
| Maven | Dependency and build management |
| JWT | Token-based authentication |
| OAuth2 Client | Google login integration |
| Lombok | Reduce boilerplate code |
| JUnit 5 / Mockito | Automated testing |

## Project Architecture

The application follows a layered architecture:

- **Controller Layer:** Receives HTTP requests and returns API responses.
- **Service Layer:** Contains application business logic.
- **Repository Layer:** Performs database operations using Spring Data JPA.
- **Entity Layer:** Represents database tables.
- **DTO Layer:** Defines API request and response structures.
- **Security Configuration:** Configures authentication, authorization, JWT processing, and OAuth2 login.
- **Exception Handling:** Centralizes application error handling.

## Prerequisites

Install the following before running the application:

- Java Development Kit compatible with the project's configured Java version
- PostgreSQL
- Git
- IntelliJ IDEA or another Java IDE

Maven is available through the project's Maven Wrapper if it is included in the repository.

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/Tiggasant/Student-Management-System](https://github.com/Tiggasant/Student-Management-System
cd SpringBootCRUD
```

Replace the placeholders with your actual GitHub repository URL and project directory.

### 2. Create the PostgreSQL database

Create a database in PostgreSQL for the application.

```sql
CREATE DATABASE student_management;
```

If your application uses a different database name, use the name configured in your local environment.

### 3. Configure environment variables

Configure your database credentials and authentication secrets locally. Do not commit passwords, JWT secrets, or Google OAuth client secrets to GitHub.

Example configuration for `application.properties`:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update

jwt.secret=${JWT_SECRET}
jwt.expiration=${JWT_EXPIRATION}

spring.security.oauth2.client.registration.google.client-id=${GOOGLE_CLIENT_ID}
spring.security.oauth2.client.registration.google.client-secret=${GOOGLE_CLIENT_SECRET}
spring.security.oauth2.client.registration.google.scope=openid,profile,email
```

These are configuration examples. Match the property names and settings to your existing application, including any additional properties required by your JWT and OAuth2 configuration.

Set the corresponding environment variables on your computer before starting the application.

### 4. Configure Google OAuth2

1. Create or select a project in Google Cloud Console.
2. Configure the OAuth consent screen.
3. Create an OAuth 2.0 client ID for a web application.
4. Configure the authorized redirect URI to match your Spring Security configuration. For a typical local Spring Security OAuth2 login, this is:

   `http://localhost:8080/login/oauth2/code/google`

5. Store the Google client ID and client secret in your local environment.

The callback URI must match the one registered in Google Cloud Console.

### 5. Run the application

From the directory containing `pom.xml`, run:

**Windows**

```powershell
.\mvnw.cmd spring-boot:run
```

**Linux/macOS**

```bash
./mvnw spring-boot:run
```

Alternatively, run the main Spring Boot application class from your IDE.

The application will typically be available at:

`http://localhost:8080`

## API Endpoints

The following table describes the expected endpoint categories. Confirm the exact mappings in your controllers and security configuration before using them.

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/auth/register` | Register a user |
| POST | `/auth/login` | Authenticate a user and obtain a JWT |
| GET | `/api/students` | Retrieve student records |
| GET | `/api/students/{id}` | Retrieve a student by ID |
| POST | `/api/students` | Create a student |
| PUT | `/api/students/{id}` | Update a student |
| DELETE | `/api/students/{id}` | Delete or soft-delete a student, depending on the configured controller |
| GET | `/oauth2/authorization/google` | Start Google OAuth2 login |

**Authentication note:** The actual protection rules depend on `SecurityConfig`. Send the JWT in the `Authorization: Bearer <token>` header when the endpoint expects bearer-token authentication. If your application uses JWT cookies, follow the configured cookie and CSRF requirements instead.

## Testing

Use Postman to manually test registration, login, student CRUD operations, and authentication-protected endpoints.

Run automated tests using the Maven Wrapper:

**Windows**

```powershell
.\mvnw.cmd test
```

**Linux/macOS**

```bash
./mvnw test
```

Automated test coverage should be documented here after the test classes have been added and successfully executed.

## Security Considerations

- Keep database credentials and authentication secrets out of version control.
- Use a strong JWT signing secret and an appropriate expiration time.
- Store passwords using a secure password encoder such as BCrypt.
- Protect student APIs according to the configured authentication rules.
- Review CSRF protection when authentication credentials are stored in cookies.
- Use HTTPS and secure cookie settings in production.
- Restrict OAuth2 redirect URIs to trusted URLs.

## Future Improvements

- Expand unit and integration test coverage.
- Add API documentation using OpenAPI/Swagger.
- Improve API error responses and validation.
- Add pagination and sorting for student records.
- Configure a production-ready deployment and environment-specific settings.

## Author

**Santkumar Tigga**

B.Tech in Computer Science and Engineering

GitHub: Add your GitHub profile URL here

LinkedIn: Add your LinkedIn profile URL here
