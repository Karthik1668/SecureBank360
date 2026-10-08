# SecureBank360
Full-stack banking management system.
## Tech Stack
Java, Spring Boot, Spring Security, JWT, MySQL, Maven, React.js
## Features
- JWT-based stateless authentication
- BCrypt password hashing
- Atomic transactions with rollback
- 9 REST endpoints, tested with Postman
## Architecture
Entity -> Repository -> Service -> Controller -> DTO
## Database
5 tables: customers, accounts, transactions, loans, roles
## How to Run
1. Create a MySQL database named securebank360
2. Set environment variables: DB_USERNAME, DB_PASSWORD, JWT_SECRET
3. Run: mvn spring-boot:run
4. API runs at http://localhost:8080
## API Endpoints
(List your endpoints here or link a Postman collection)
## Author
Karthik Kumar - linkedin.com/in/ckk1668