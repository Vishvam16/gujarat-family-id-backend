# Gujarat Family ID

A full-stack web application for introducing a **unique Family ID system in Gujarat** to centralize family information, identify eligible government schemes, and manage scheme applications.

## 🚀 Overview

The Gujarat Family ID platform provides a centralized family-level record that can be used to:

- Create a unique Gujarat Family ID
- Store family and member information
- Manage family members
- Maintain address and demographic details
- Identify government schemes for which a family is eligible
- Apply for eligible schemes
- Track application status
- View a consolidated family dashboard

### Problem Statement

> **Introduction of family ID in Gujarat to improve beneficiary management for various government schemes.**

---

## 🏗️ Solution Architecture

```text
                         ┌─────────────────────┐
                         │       USERS         │
                         │                     │
                         │ Citizens            │
                         │ Government Officials│
                         │ Administrators      │
                         └──────────┬──────────┘
                                    │
                                  HTTPS
                                    │
                                    ▼
                    ┌─────────────────────────────┐
                    │       REACT FRONTEND        │
                    │          Vite + MUI         │
                    │                             │
                    │  • Family Dashboard        │
                    │  • Family Management        │
                    │  • Member Management        │
                    │  • Scheme Eligibility       │
                    │  • Scheme Applications      │
                    └──────────────┬──────────────┘
                                   │
                              REST APIs
                                   │
                                   ▼
                    ┌─────────────────────────────┐
                    │      SPRING BOOT BACKEND    │
                    │          Java 21             │
                    │                             │
                    │  • Family Management        │
                    │  • Member Management         │
                    │  • Scheme Management         │
                    │  • Eligibility Engine        │
                    │  • Application Management    │
                    │  • Dashboard APIs            │
                    └──────────────┬──────────────┘
                                   │
                             JPA / Hibernate
                                   │
                                   ▼
                    ┌─────────────────────────────┐
                    │        POSTGRESQL           │
                    │                             │
                    │  • Families                 │
                    │  • Family Members            │
                    │  • Addresses                 │
                    │  • Schemes                  │
                    │  • Applications              │
                    └─────────────────────────────┘
```

### Deployment Architecture

```text
                         GitHub
                           │
              ┌────────────┴────────────┐
              │                         │
              ▼                         ▼
          Vercel                      Render
              │                         │
              ▼                         ├── Docker
      React Frontend                    │
                                        ├── Spring Boot
                                        │
                                        ▼
                                  PostgreSQL
```

---

## 🛠️ Tech Stack

### Frontend

- React
- Vite
- Material UI
- Axios
- JavaScript

### Backend

- Java 21
- Spring Boot 3.5.6
- Spring Web
- Spring Data JPA
- Hibernate
- Maven
- Bean Validation

### Database

- PostgreSQL

### Deployment

- Docker
- Render
- Vercel
- GitHub

---

## ✨ Features

### Family ID Generation

Every family receives a unique identifier in the format:

```text
GJ-FAM-XXXXXXXXXX
```

### Family Management

Stores:

- Family name
- Annual income
- Category
- Rural / Urban classification
- Address
- Creation and update timestamps

### Family Member Management

Each family can have multiple members with:

- First name
- Last name
- Date of birth
- Gender
- Identity number
- Mobile number
- Relationship
- Education level
- Occupation
- Employment status
- Annual income
- Verification status

Supported operations:

```text
Add Member
View Member
Update Member
Delete Member
```

### Government Scheme Management

Government schemes can define eligibility criteria such as:

- Minimum family income
- Maximum family income
- Rural / Urban applicability
- Required category
- Active / inactive status

### Scheme Eligibility

The system automatically evaluates family information against active scheme criteria and displays eligible schemes.

```text
Family Information
       │
       ├── Annual Income
       ├── Category
       └── Rural / Urban
              │
              ▼
      Eligibility Engine
              │
              ▼
     Eligible Schemes
```

### Scheme Applications

Families can apply for eligible schemes and track their application status.

```text
APPLIED
   │
   ▼
UNDER_REVIEW
   │
   ├──────────────┐
   ▼              ▼
APPROVED       REJECTED
   │
   ▼
BENEFIT_RECEIVED
```

### Family Dashboard

The dashboard provides a consolidated view of:

- Family information
- Total family members
- Member details
- Eligible schemes
- Existing applications

---

## 🔌 REST API

### Family APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/families` | Create family |
| `GET` | `/api/families/{familyId}` | Get family |

### Family Member APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/families/{familyId}/members` | Add member |
| `GET` | `/api/families/{familyId}/members` | Get members |
| `GET` | `/api/families/{familyId}/members/{memberId}` | Get member |
| `PUT` | `/api/families/{familyId}/members/{memberId}` | Update member |
| `DELETE` | `/api/families/{familyId}/members/{memberId}` | Delete member |

### Scheme APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/schemes` | Create scheme |
| `GET` | `/api/schemes` | Get all schemes |
| `GET` | `/api/schemes/{schemeId}` | Get scheme |
| `PUT` | `/api/schemes/{schemeId}` | Update scheme |
| `DELETE` | `/api/schemes/{schemeId}` | Delete scheme |

### Eligibility API

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/families/{familyId}/eligible-schemes` | Get eligible schemes |

### Application APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/families/{familyId}/applications` | Apply for scheme |
| `GET` | `/api/families/{familyId}/applications` | Get applications |
| `GET` | `/api/families/{familyId}/applications/{applicationId}` | Get application |
| `PUT` | `/api/families/{familyId}/applications/{applicationId}/status` | Update application status |

### Dashboard API

```http
GET /api/families/{familyId}/dashboard
```

Returns:

- Family details
- Family members
- Eligible schemes
- Scheme applications

---

## 📂 Backend Structure

```text
src/main/java/org/placement/project/
│
├── config/
│   └── CorsConfig.java
│
├── controller/
│   ├── FamilyController.java
│   ├── FamilyMemberController.java
│   ├── SchemeController.java
│   └── ApplicationController.java
│
├── dto/
│   ├── ...
│
├── entity/
│   ├── Family.java
│   ├── FamilyMember.java
│   ├── Address.java
│   ├── Scheme.java
│   └── SchemeApplication.java
│
├── repository/
│   ├── FamilyRepository.java
│   ├── FamilyMemberRepository.java
│   ├── AddressRepository.java
│   ├── SchemeRepository.java
│   └── SchemeApplicationRepository.java
│
└── service/
    ├── FamilyService.java
    ├── FamilyMemberService.java
    ├── SchemeService.java
    └── ApplicationService.java
```

---

## 📂 Frontend Structure

```text
src/
│
├── services/
│   ├── api.js
│   └── familyService.js
│
├── App.jsx
├── App.css
├── index.css
└── main.jsx
```

---

## ⚙️ Local Setup

### Prerequisites

- Java 21
- PostgreSQL
- Node.js
- npm
- Docker (optional)

### Clone Backend

```bash
git clone https://github.com/Vishvam16/gujarat-family-id-backend.git
cd gujarat-family-id-backend
```

### Backend Configuration

Configure the PostgreSQL connection using environment variables:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
PORT
```

Example:

```yaml
spring:
  datasource:
    url: ${DB_URL:jdbc:postgresql://localhost:5433/family_id_db}
    username: ${DB_USERNAME:postgres}
    password: ${DB_PASSWORD}

  jpa:
    hibernate:
      ddl-auto: update

server:
  port: ${PORT:8080}
```

### Run Backend

Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

Backend:

```text
http://localhost:8080
```

---

## 💻 Frontend Setup

```bash
cd frontend
npm install
npm run dev
```

Frontend:

```text
http://localhost:5173
```

---

## 🐳 Docker

The backend uses a multi-stage Docker build.

```dockerfile
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /build

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /build/target/family-id-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Build Docker Image

```bash
docker build -t gujarat-family-id-backend .
```

### Run Docker Container

```bash
docker run -p 8080:8080 gujarat-family-id-backend
```

---

## ☁️ Deployment

### Backend

The Spring Boot backend is containerized using Docker and deployed on **Render**.

### Frontend

The React frontend is deployed on **Vercel**.

### Database

The application uses **Render Managed PostgreSQL**.

### CI/CD

```text
GitHub
   │
   ├── Push to main
   │
   ▼
Render
   │
   ├── Build Docker Image
   └── Deploy Spring Boot Backend
```

---

## 🔐 Configuration

Database credentials are provided through environment variables and should never be committed to GitHub.

```text
DB_URL
DB_USERNAME
DB_PASSWORD
PORT
```

---

## 🔮 Future Scope

- Integration with Gujarat government databases
- Aadhaar/identity verification integrations
- Digital document storage
- SMS and email notifications
- Government department API integrations
- Advanced beneficiary analytics
- Automated scheme recommendations
- Verification and approval workflows
- Multilingual support including Gujarati
- Mobile application

---

## 🎯 Project Goal

The goal of the Gujarat Family ID platform is to establish a **centralized family-level information system** that can help streamline beneficiary identification, reduce duplicate records, improve scheme eligibility assessment, and simplify access to government welfare programs.

---

## 📌 Repository

**Backend:**  
https://github.com/Vishvam16/gujarat-family-id-backend

---

## 👨‍💻 Project

Developed as a **hackathon/academic project** focused on improving digital beneficiary management through a centralized Family ID system.
