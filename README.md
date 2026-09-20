Gujarat Family ID

A full-stack web application for creating and managing a unique Family ID for households in Gujarat, maintaining family/member information, checking eligibility for government schemes, and tracking scheme applications.

🚀 Project Overview

The Gujarat Family ID platform provides a centralized family-level record that can be used to:

Create a unique Gujarat Family ID
Store family and member information
Manage family members
Maintain address and demographic details
Identify government schemes for which a family may be eligible
Apply for eligible schemes
Track application status
View family-level dashboard information

The project is designed around the problem statement:

“Introduction of family ID in Gujarat to improve beneficiary management for various government schemes.”

🏗️ Solution Architecture
                    ┌──────────────────────┐
                    │       Citizens       │
                    │  / Government Users  │
                    └──────────┬───────────┘
                               │
                               │ HTTPS
                               ▼
                 ┌──────────────────────────┐
                 │      React + Vite        │
                 │        Frontend          │
                 │                          │
                 │  • Family Dashboard     │
                 │  • Family Management    │
                 │  • Member Management     │
                 │  • Scheme Eligibility   │
                 │  • Applications         │
                 └────────────┬─────────────┘
                              │
                              │ REST API
                              ▼
                 ┌──────────────────────────┐
                 │     Spring Boot API      │
                 │        Java 21           │
                 │                          │
                 │  • Family Management    │
                 │  • Member Management     │
                 │  • Scheme Management     │
                 │  • Eligibility Engine    │
                 │  • Application Tracking  │
                 │  • Dashboard APIs        │
                 └────────────┬─────────────┘
                              │
                              │ JPA / Hibernate
                              ▼
                 ┌──────────────────────────┐
                 │       PostgreSQL         │
                 │                          │
                 │  • Families              │
                 │  • Family Members        │
                 │  • Addresses             │
                 │  • Schemes               │
                 │  • Applications           │
                 └──────────────────────────┘
Deployment
GitHub
   │
   ├──────────────► Vercel
   │                 │
   │                 └── React Frontend
   │
   └──────────────► Render
                     │
                     ├── Docker
                     ├── Spring Boot Backend
                     │
                     └── PostgreSQL
🛠️ Tech Stack
Frontend
React
Vite
Material UI
React Router
Axios
JavaScript
Backend
Java 21
Spring Boot 3.5.6
Spring Web
Spring Data JPA
Hibernate
Maven
Bean Validation
Database
PostgreSQL
Deployment
Docker
Render
Vercel
GitHub
✨ Key Features
1. Family ID Generation

Each registered family receives a unique identifier in the format:

GJ-FAM-XXXXXXXXXX

The Family ID can subsequently be used to retrieve the family's complete information.

2. Family Management

Stores:

Family name
Annual income
Category
Rural / Urban classification
Address
Creation and update timestamps
3. Family Member Management

Each family can have multiple members with information such as:

Name
Date of birth
Gender
Identity number
Mobile number
Relationship with family
Education
Occupation
Employment status
Annual income
Verification status

Supported operations:

Add Member
View Member
Update Member
Delete Member
4. Government Scheme Management

Schemes contain eligibility conditions including:

Minimum family income
Maximum family income
Rural / Urban applicability
Required category
Active/inactive status
5. Eligibility Engine

The system automatically evaluates active schemes against family information.

Example:

Family
 ├── Annual Income
 ├── Category
 └── Rural/Urban
          │
          ▼
   Eligibility Engine
          │
          ▼
 Eligible Government Schemes
6. Scheme Applications

Families can apply for eligible schemes and track their application status.

Application lifecycle:

APPLIED
   ↓
UNDER_REVIEW
   ↓
APPROVED / REJECTED
   ↓
BENEFIT_RECEIVED
7. Family Dashboard

The dashboard provides a consolidated view of:

Family information
Total members
Member details
Eligible schemes
Existing applications
🔌 REST API
Family
Method	Endpoint	Description
POST	/api/families	Create family
GET	/api/families/{familyId}	Get family
Family Members
Method	Endpoint	Description
POST	/api/families/{familyId}/members	Add member
GET	/api/families/{familyId}/members	Get members
GET	/api/families/{familyId}/members/{memberId}	Get member
PUT	/api/families/{familyId}/members/{memberId}	Update member
DELETE	/api/families/{familyId}/members/{memberId}	Delete member
Schemes
Method	Endpoint	Description
POST	/api/schemes	Create scheme
GET	/api/schemes	Get schemes
GET	/api/schemes/{schemeId}	Get scheme
PUT	/api/schemes/{schemeId}	Update scheme
DELETE	/api/schemes/{schemeId}	Delete scheme
Eligibility
Method	Endpoint	Description
GET	/api/families/{familyId}/eligible-schemes	Get eligible schemes
Applications
Method	Endpoint	Description
POST	/api/families/{familyId}/applications	Apply for scheme
GET	/api/families/{familyId}/applications	Get applications
GET	/api/families/{familyId}/applications/{applicationId}	Get application
PUT	/api/families/{familyId}/applications/{applicationId}/status	Update status
Dashboard
GET /api/families/{familyId}/dashboard

Returns family details, members, eligible schemes, and applications in a single response.

📁 Backend Structure
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
📁 Frontend Structure
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
⚙️ Running Locally
Prerequisites

Make sure you have:

Java 21
PostgreSQL
Node.js
npm
Docker (optional)
Backend

Clone the repository:

git clone https://github.com/Vishvam16/gujarat-family-id-backend.git
cd gujarat-family-id-backend

Configure PostgreSQL and environment variables.

Example:

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

Run:

.\mvnw.cmd spring-boot:run

Backend:

http://localhost:8080
Frontend
cd frontend
npm install
npm run dev

Frontend:

http://localhost:5173
🐳 Docker

The backend uses a multi-stage Docker build.

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

Build:

docker build -t gujarat-family-id-backend .

Run:

docker run -p 8080:8080 gujarat-family-id-backend
☁️ Deployment
Backend

The Spring Boot backend is containerized using Docker and deployed on Render.

Frontend

The React frontend is configured for deployment on Vercel.

Database

PostgreSQL is hosted using Render's managed PostgreSQL service.

🔐 Data & Configuration

Database credentials are supplied through environment variables rather than being hardcoded in the application.

DB_URL
DB_USERNAME
DB_PASSWORD
PORT

Sensitive credentials should never be committed to GitHub.

🎯 Future Scope

Potential extensions include:

Integration with existing Gujarat government databases
Aadhaar/identity verification integrations
Digital document storage
SMS/email notifications
Government department integrations
Advanced beneficiary analytics
Scheme recommendation based on family profiles
Audit and verification workflows
Mobile application
Multilingual support including Gujarati
👥 Project Goal

The primary goal is to establish a single family-level identity and information layer that can simplify beneficiary identification, reduce duplicate information, and make access to government welfare schemes more streamlined.

📌 Repository

Backend:
Gujarat Family ID Backend – GitHub
