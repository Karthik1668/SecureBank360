# SecureBank360

Full-stack banking management system built with Spring Boot, with JWT-secured REST APIs and atomic fund transfers. React.js frontend is in progress.

## Tech Stack
- **Backend:** Java 17, Spring Boot 4.1.1, Spring Security, Spring Data JPA, Maven
- **Auth:** JWT (jjwt 0.12.6), BCrypt
- **Database:** MySQL
- **Frontend:** React.js (in progress)

## Features
- JWT-based stateless authentication
- BCrypt password hashing
- Atomic fund transfers with `@Transactional` rollback
- Failed transfers logged in a separate transaction (`REQUIRES_NEW`) so the audit record survives rollback
- Auto-generated account numbers and UPI IDs on registration
- Loan workflow: apply, approve, reject
- 10 REST endpoints, tested with Postman

## Architecture
Entity -> Repository -> Service -> Controller -> DTO

## Database
4 tables: `users`, `accounts`, `transactions`, `loans`
- `accounts.userId` -> `users` (one-to-one)
- `loans.user_id` -> `users` (many-to-one)
- `transactions.from_account_id`, `transactions.to_account_id` -> `accounts` (many-to-one)
- Roles (`CUSTOMER`, `ADMIN`) are stored as an enum column in `users`

## How to Run
1. Create a MySQL database named `securebank360db`
2. Set environment variables: `DB_USERNAME`, `DB_PASSWORD`, `JWT_SECRET`
3. Run: `mvn spring-boot:run`
4. API runs at http://localhost:8081

## API Endpoints
| Method | Path | Description | Auth |
|---|---|---|---|
| POST | /api/auth/register | Register user (auto-creates savings account) | Public |
| POST | /api/auth/login | Login, returns JWT | Public |
| GET | /api/accounts/{accountNumber} | Get account details | JWT |
| POST | /api/transactions/transfer | Transfer funds (params: fromAccountNumber, toAccountNumber, amount, description) | JWT |
| GET | /api/transactions/statement/{accountNumber} | Last 10 transactions | JWT |
| POST | /api/loans/apply | Apply for a loan | JWT |
| GET | /api/loans/pending | List pending loans | JWT |
| PUT | /api/loans/approve/{loanId} | Approve a loan | JWT |
| PUT | /api/loans/reject/{loanId} | Reject a loan | JWT |
| GET | /api/loans/loansByUser | Loans for a user (param: email) | JWT |

Send the token as `Authorization: Bearer <token>`.

## Author
Karthik Kumar Chittoor - [linkedin.com/in/ckk1668](https://linkedin.com/in/ckk1668) | [github.com/Karthik1668](https://github.com/Karthik1668)
