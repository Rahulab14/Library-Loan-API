# 📚 Library Loan API

A Spring Boot REST API for managing books, loans, reservations, FIFO waitlists, and dynamic overdue fines.

This project was built as part of the Kalvium Backend Assessment.

---

## 🚀 Features

### Book Management
- Add books
- List all books
- Filter available books
- Update daily fine rate
- Unique ISBN validation

### Loan Management
- Borrow books
- Return books
- Automatic 14-day loan period
- Dynamic overdue fine calculation
- Borrower limit (maximum 3 active loans)

### Reservation System
- Reserve unavailable books
- FIFO waitlist
- Reservation limit (maximum 3 active reservations)
- Prevent duplicate reservations
- Prevent reserving books already borrowed by the same user

### Notification Workflow
- When a book is returned:
  - First reservation in queue becomes NOTIFIED
  - Notification is logged in console
  - Book remains unavailable
  - Notified borrower gets exclusive access

### Overdue Tracking
- View overdue loans
- Fine calculated using book-specific daily rate

### Error Handling
- Consistent JSON error responses
- Global exception handling
- Proper HTTP status codes

---

# 🛠 Tech Stack

- Java 17
- Spring Boot 3
- Spring Data JPA
- H2 Database
- Maven
- JUnit 5
- Mockito
- Lombok

---

# 📁 Project Structure

```text
library-loan-api

src
├── main
│   ├── java
│   │   └── com.parkease.library
│   │
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   ├── dto
│   ├── exception
│   └── enums
│
│   └── resources
│       └── application.properties
│
└── test
    └── java
        └── com.parkease.library

README.md
pom.xml
```

---

# ⚙️ Setup Instructions

## Prerequisites

Install:

- Java 17
- Maven 3.9+
- IntelliJ IDEA / VS Code

Verify:

```bash
java -version
mvn -version
```

---

## Clone Repository

```bash
git clone <repository-url>
cd library-loan-api
```

---

## Run Application

Using Maven:

```bash
mvn spring-boot:run
```

Or:

```bash
./mvnw spring-boot:run
```

Application starts at:

```text
http://localhost:8080
```

---

# 🗄 H2 Database

### H2 Console

```text
http://localhost:8080/h2-console
```

### Connection Details

```text
JDBC URL:
jdbc:h2:mem:librarydb

Username:
sa

Password:
(password blank)
```

---

# 📖 Business Rules

## Books

- ISBN must be unique
- Fine rate defaults to ₹10/day

## Loans

- Maximum 3 active loans per borrower
- Loan period = 14 days
- Book must be available

## Reservations

- Maximum 3 active reservations
- FIFO queue
- Cannot reserve same book twice
- Cannot reserve a book already borrowed

## Notifications

When a book is returned:

- First reservation becomes NOTIFIED
- Console log generated
- Book remains unavailable

Example:

```text
[NOTIFICATION] Book "Spring Boot" is now available for borrower "John"
```

---

# 💰 Fine Calculation

Formula:

```text
Fine = Daily Fine Rate × Days Overdue
```

Example:

```text
Book Fine Rate = ₹20/day

Due Date = 2025-05-01
Returned = 2025-05-04

Days Overdue = 3

Fine = ₹20 × 3

Total Fine = ₹60
```

---

# 🔄 FIFO Waitlist Logic

Reservations are maintained in FIFO order.

Example:

```text
Book Borrowed by User A

Queue:

1. User B
2. User C
3. User D
```

When User A returns:

```text
User B → NOTIFIED
User C → Waiting
User D → Waiting
```

Book remains unavailable until User B borrows it.

This prevents queue jumping and ensures fairness.

---

# 📌 API Endpoints

## Books

### Create Book

```http
POST /api/books
```

Request:

```json
{
  "title": "Spring Boot",
  "author": "John",
  "isbn": "ISBN001",
  "dailyFineRate": 20
}
```

---

### Get All Books

```http
GET /api/books
```

---

### Get Available Books

```http
GET /api/books?available=true
```

---

### Update Fine Rate

```http
PUT /api/books/{id}/fine
```

Request:

```json
{
  "dailyFineRate": 25
}
```

---

# Loans

### Borrow Book

```http
POST /api/loans
```

Request:

```json
{
  "bookId": 1,
  "borrowerName": "John"
}
```

---

### Return Book

```http
PUT /api/loans/{id}/return
```

Response:

```json
{
  "loanId": 1,
  "fineAmount": 40
}
```

---

### Get Overdue Loans

```http
GET /api/loans/overdue
```

---

# Reservations

### Reserve Book

```http
POST /api/reservations
```

Request:

```json
{
  "bookId": 1,
  "borrowerName": "Jane"
}
```

---

### Get Book Reservations

```http
GET /api/books/{id}/reservations
```

---

### Cancel Reservation

```http
DELETE /api/reservations/{id}
```

---

### Borrow Notified Reservation

```http
POST /api/reservations/{id}/borrow
```

---

# 🧪 Example cURL Commands

## Add Book

```bash
curl -X POST http://localhost:8080/api/books \
-H "Content-Type: application/json" \
-d '{
"title":"Spring Boot",
"author":"John",
"isbn":"ISBN001",
"dailyFineRate":20
}'
```

---

## Borrow Book

```bash
curl -X POST http://localhost:8080/api/loans \
-H "Content-Type: application/json" \
-d '{
"bookId":1,
"borrowerName":"John"
}'
```

---

## Reserve Book

```bash
curl -X POST http://localhost:8080/api/reservations \
-H "Content-Type: application/json" \
-d '{
"bookId":1,
"borrowerName":"Jane"
}'
```

---

## Return Book

```bash
curl -X PUT http://localhost:8080/api/loans/1/return
```

---

## View Overdue Loans

```bash
curl http://localhost:8080/api/loans/overdue
```

---

# ✅ Testing

Run tests:

```bash
mvn test
```

Coverage includes:

- Book creation
- Duplicate ISBN validation
- Borrowing rules
- Fine calculation
- Reservation creation
- Reservation limits
- FIFO waitlist
- Notification workflow

---

# 🎥 Video Demonstration Checklist

Demonstrate:

- Add book with custom fine rate
- Borrow book
- Return book on time
- Return overdue book
- Create reservation
- Add second reservation
- Show FIFO queue
- Return book
- Show notification log
- Borrow from notified reservation
- View overdue loans

---

# 👨‍💻 Author

Kalvium Backend Assessment

Library Loan API
Built with Spring Boot 3 and Java 17.