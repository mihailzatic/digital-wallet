# Mini Digital Wallet API 🏦

A robust, full-stack banking simulation demonstrating core backend engineering principles, RESTful API design, and modern automated testing. 

This project was built to showcase a "digital bank with a human touch," featuring a layered architecture, atomic financial transactions, and a clean, vanilla JavaScript frontend for immediate end-to-end evaluation.

## 🚀 Tech Stack

* **Backend:** Java 17, Spring Boot 4.0
* **Data Access:** Spring Data JPA (Hibernate)
* **Database:** H2 In-Memory Database (for zero-config evaluation)
* **Testing:** JUnit 5, Mockito, Spring MockMvc (Configured for Spring Boot 4.0 Modules)
* **Frontend:** Vanilla HTML5, CSS3 (Flexbox), JavaScript (Fetch API)
* **Build Tool:** Maven

## ⚙️ Core Features & Architecture

* **Layered Architecture:** Strict separation of concerns across Controller, Service, Repository, and Entity layers.
* **Atomic Transactions:** The transferFunds method utilizes Spring's @Transactional annotation. This ensures that if a system failure occurs after a withdrawal but before a deposit, the entire transaction rolls back, preventing lost funds—a critical requirement for financial software.
* **RESTful Endpoints:** Clean API design handling account creation, balance retrieval, and fund transfers.
* **Exception Handling:** Graceful error handling returning appropriate HTTP status codes (e.g., 400 Bad Request for insufficient funds).
* **Comprehensive Testing:** Includes unit tests for business logic (using @MockitoBean to mock the database) and integration tests simulating real HTTP requests to the controllers via @WebMvcTest.

## 🛠️ How to Run Locally

You do not need to install any external databases to run this project.

1. Clone the repository:
   git clone https://github.com/mihailzatic/wallet.git
   cd wallet

2. Run the Spring Boot application:
   ./mvnw spring-boot:run

3. Access the Application:
   * Frontend UI: Open http://localhost:8080/index.html in your browser.
   * Database Console: Open http://localhost:8080/h2-console 
     (JDBC URL: jdbc:h2:mem:walletdb | User: sa | Password: password)

## 🧪 Running the Tests

To execute the automated test suite (Unit & Integration tests) in your terminal, run:

./mvnw test
