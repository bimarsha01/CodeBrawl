# 🚀 CodeBrawl

CodeBrawl is a competitive programming platform where developers sharpen their coding skills through both solo practice and real-time coding battles against other players.

Think of it as a combination of **LeetCode**, **Codeforces**, and real-time multiplayer competition.

---

## 📌 Project Status

🚧 Currently in active development.

The project is focused on building a production-grade backend architecture before implementing the real-time coding features.

---

# ✨ Current Features

### Authentication
- User Registration
- User Login
- JWT Authentication
- BCrypt Password Hashing
- Spring Security Authentication
- Global Exception Handling
- DTO Validation
- MapStruct Mapping
- PostgreSQL Integration

### Backend Architecture
- Layered Architecture
- DTO Pattern
- Repository Pattern
- Service Layer
- Custom Exception Handling
- Global Exception Handler
- Validation using Jakarta Validation
- Clean API Response Structure

---

# 🛠 Tech Stack

### Backend
- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- PostgreSQL
- MapStruct
- Lombok
- Maven

### Authentication
- JWT (JSON Web Token)
- BCrypt Password Encoder

---

# 📂 Project Structure

```
src
 ├── Controller
 ├── Service
 ├── Security
 ├── Entity
 ├── DTO
 ├── Repository
 ├── Mapper
 ├── ExceptionHandling
 ├── ApiResponses
 └── Configuration
```

---

# 🚧 Planned Features

## Authentication
- Refresh Token Authentication
- HttpOnly Secure Cookies
- JWT Authentication Filter
- Role-Based Authorization
- Logout
- Password Reset
- Email Verification
- OAuth2 (Google / GitHub)

## Core Platform
- User Profiles
- Coding Problems
- Online Code Execution
- Matchmaking
- Real-Time Coding Battles
- Leaderboards
- Rating System
- Contest Creation
- Friends System
- Notifications
- Submission History

---

# 📖 Learning Goals

This project is being built as a production-style backend application with emphasis on:

- Clean Architecture
- Spring Security
- Authentication & Authorization
- Scalable REST APIs
- Software Design Principles
- Real-world Backend Development

---

# ⚙️ Running the Project

### Clone

```bash
git clone https://github.com/your-username/CodeBrawl.git
```

### Navigate

```bash
cd CodeBrawl
```

### Configure

Create an `application.properties` (or `application.yml`) file and configure:

- PostgreSQL Database
- JWT Secret
- JWT Expiration

### Run

```bash
mvn spring-boot:run
```

---

# 📈 Roadmap

- [x] User Registration
- [x] Login
- [x] JWT Authentication
- [x] Validation
- [x] Exception Handling
- [x] MapStruct Integration
- [ ] Refresh Token
- [ ] HttpOnly Cookie Authentication
- [ ] JWT Filter
- [ ] Role-Based Authorization
- [ ] Email Verification
- [ ] OAuth2 Login
- [ ] Coding Problems
- [ ] Matchmaking
- [ ] Real-Time Battles
- [ ] Leaderboard

---

# 🤝 Contributing

Contributions, suggestions, and feedback are welcome.

---

# 📄 License

This project is currently under development and is intended for educational and portfolio purposes.