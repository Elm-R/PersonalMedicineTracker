## Description

A medicine inventory tracker that manages medicines, tracks quantities, and monitors expiry dates via a REST API, using MySQL and fully containerized with Docker.

---
## Technologies
- Java 25 & Spring Boot (with Hibernate)
- Maven
- MySQL 
- Docker
- Terraform (Planned)
- AWS (Planned: S3, SES, Lambda, EventBridge, CloudWatch)
- Kubernetes (Planned)
- GitHub Actions (Planned)

---
## Structure
## Structure
- REST API with a service-based architecture
- Organized into controllers, services, repository, and entity/model layers
- MySQL database

---
## Core Features (Local)
- **CRUD Operations**
    - Add a medicine
    - List all medicines
    - Update quantity
    - Delete medicine
- **Expiry Tracking**
    - Show items expiring in X days
    - Show expired medicines

## Docker Setup
- Database container (MySQL)
- Application container (Spring Boot)
- Build and run stages with Docker
- Docker Compose for orchestration
