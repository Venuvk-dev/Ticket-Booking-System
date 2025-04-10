

# 🎟️ Event Ticket Booking System

A high-performance, **Spring Boot-based** ticket booking platform built to simulate a real-world ticket marketplace — powered by Java multithreading and the classic **producer-consumer** model.

---

## 🚀 Overview

Welcome to the Event Ticket Booking System — a smart, concurrency-driven application where:

- 🎤 **Vendors** release tickets for exciting events  
- 🙋‍♂️ **Customers** rush to grab their spots  
- ⚙️ Behind the scenes, a thread-safe **ticket pool** keeps everything running smoothly  

This system mirrors real-time ticket operations using **Java threads**, demonstrating advanced concurrency concepts like synchronization and resource sharing — all packed into a modern Spring Boot RESTful API.

---

## ✨ Key Features

✅ **Multi-threaded Engine**  
Efficiently handles concurrent operations using synchronized methods and thread coordination.

✅ **Ticket Pool Management**  
A shared buffer enabling producers (vendors) and consumers (customers) to interact safely and simultaneously.

✅ **Modern REST API**  
Endpoints tailored for both vendor and customer actions, easily accessible via HTTP tools or frontend clients.

✅ **Persistent Storage**  
MySQL database integration using Spring Data JPA to store events, tickets, and purchase records.

✅ **Frontend-Ready**  
CORS support allows seamless integration with frameworks like React, Vue, Angular, and more.

---

## 🛠️ Tech Stack

- **Java 17** – Core language  
- **Spring Boot** – Framework powerhouse  
- **Spring Data JPA** – ORM layer  
- **MySQL** – Data storage  
- **Lombok** – Reduces boilerplate  
- **Multithreading & Synchronization** – Producer-consumer magic

---

## 🧭 Project Structure

```plaintext
com.example.eventBookingSystem
├── TicketBookingSystemApplication.java  # Main app entry
├── business
│   ├── Vendor.java                      # Producer thread
│   ├── Customer.java                    # Consumer thread
│   └── TicketPool.java                  # Thread-safe ticket buffer
├── controller
│   ├── VendorController.java            # API for vendors
│   ├── CustomerController.java          # API for customers
│   └── TicketController.java            # API for ticket management
├── entity
│   ├── Vendor.java, Customer.java, Ticket.java
├── repository
│   ├── VendorRepository.java
│   ├── CustomerRepository.java
│   └── TicketRepository.java
├── config
│   ├── AppConfig.java, WebConfig.java   # Beans + CORS
└── service
    └── ServerControlService.java        # Server management
```

---

## ⚙️ Setup Guide

### ✅ Prerequisites

- Java 17+
- Maven
- MySQL

### 📦 Database Setup

```sql
CREATE DATABASE tickets;
```

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tickets
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### ▶️ Running the Application

```bash
git clone https://github.com/yourusername/event-booking-system.git
cd event-booking-system
mvn clean install
mvn spring-boot:run
```

👉 Access the app at: `http://localhost:8080`

---

## 🔗 REST API Highlights

### 🎟️ Vendor Actions
- `POST /api/vendors/start`  
  Start a vendor thread to release tickets  
  **Params**: `totalTickets`, `releaseInterval`, `eventName`, `venue`, `name`, `price`

### 🙋 Customer Actions
- `POST /api/customers/buy`  
  Start a customer thread to buy tickets  
  **Params**: `customerName`, `ticketsToBuy`, `purchaseInterval`

### 🎫 Ticket Management
- `POST /api/tickets/add` — Manually add a ticket  
- `GET /api/tickets/available` — View tickets in pool  
- `GET /api/tickets/purchased` — View bought tickets  
- `GET /api/tickets/start-server` / `stop-server` — Control server operations

---

## 🧵 Concurrency Design

This app is powered by the **Producer-Consumer Pattern**:

| Role     | Action                         |
|----------|--------------------------------|
| Vendor   | Produces tickets into pool     |
| Customer | Consumes tickets from pool     |
| TicketPool | Manages safe access with synchronized methods |

- Vendors **wait** when the pool is full 🛑  
- Customers **wait** when the pool is empty ⏳  
- Threads **notify** each other for smooth transitions 🔄

---

## 💡 Try It Out

### 🔥 Start a Vendor:
```bash
curl -X POST "http://localhost:8080/api/vendors/start?totalTickets=10&releaseInterval=2&eventName=Concert&venue=Stadium&name=MusicPromoter&price=99.99"
```

### 🙌 Start a Customer:
```bash
curl -X POST "http://localhost:8080/api/customers/buy?customerName=John&ticketsToBuy=3&purchaseInterval=3"
```

### 🎟️ Check Available Tickets:
```bash
curl http://localhost:8080/api/tickets/available
```

---

## 🤝 Contributions

PRs and ideas are warmly welcome — let's build something awesome together!

