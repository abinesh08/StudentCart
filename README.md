# 🗂️ StudentCart

**StudentCart** – A secure and modular Spring Boot application allowing students to submit documents for xerox via an online portal, reducing crowd and saving time at college/school shops.

---

## 🎯 Overview

**StudentCart** is a smart document submission platform for students, allowing them to upload documents for xerox/printing remotely. Designed for schools and colleges, it ensures:

- ✅ Zero crowding at the xerox shop  
- ✅ Faster pickup using student Roll Number  
- ✅ Seamless admin-side document handling  
- ✅ Secure document access and handling for multiple institutions  

---

## 🧰 Tech Stack

| Layer          | Technology Used                    |
|----------------|------------------------------------|
| Language       | Java 21                            |
| Framework      | Spring Boot                        |
| Architecture   | Modulith (Modular Monolith)        |
| MVC Pattern    | Spring MVC                         |
| Persistence    | Spring Data JPA + MySQL            |
| Security       | Spring Security (JWT-based login)  |
| File Handling  | Multipart Uploads (PDF, DOCX etc.) |
| Deployment     | (Optional: Docker or Fly.io)       |

---

## 🧩 Modules (Modulith Design)

### 1. 👥 User Module
- Register/Login for Students and Shop Admins
- Role-based access (STUDENT, ADMIN)

### 2. 📄 Document Module
- Upload Document (Student)
- View Pending Documents (Shop Admin)
- Mark as Completed/Printed

### 3. 🔔 Notification Module
- Notify when document is printed and ready
- SMS/Email integration (Optional)

### 4. 🏫 Institution Module
- Multi-tenant data (college/school level)
- Data security and segregation

### 5. 💵 Billing Module (Optional)
- Track document cost
- Show payable amount at shop

---

## 📁 Core Features

- ✅ Modular Spring Boot design (Modulith Architecture)  
- ✅ JWT-based authentication with Spring Security  
- ✅ Role-based access control (Student/Admin)  
- ✅ Secure Document Upload and Access  
- ✅ Admin dashboard to view, print, and complete orders  
- ✅ Roll Number-based document pickup  
- ✅ MySQL-based institution-specific data segregation  
- ✅ API-Driven backend (can integrate with future mobile app or frontend)  

---
