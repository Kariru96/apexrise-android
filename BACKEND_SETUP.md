# ApexRise Spring Boot Backend

This is the backend for the multi-user ApexRise farm management system.

## Technology Stack
- Spring Boot 3.2+
- Spring Data JPA (Hibernate)
- PostgreSQL 13+
- JWT Authentication
- Maven

## Setup

### 1. Prerequisites
- Java 17+
- Maven 3.8+
- PostgreSQL 13+
- Git

### 2. Create the project

```bash
mvn archetype:generate \
  -DgroupId=com.apexrise \
  -DartifactId=apexrise-backend \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DinteractiveMode=false
  
cd apexrise-backend
```

### 3. Update pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.apexrise</groupId>
    <artifactId>apexrise-backend</artifactId>
    <version>1.0-SNAPSHOT</version>
    <name>ApexRise Backend</name>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.0</version>
        <relativePath/>
    </parent>

    <properties>
        <java.version>17</java.version>
    </properties>

    <dependencies>
        <!-- Spring Boot Web -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- Spring Data JPA -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <!-- PostgreSQL Driver -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- JWT (jjwt) -->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.12.3</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>0.12.3</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>0.12.3</version>
            <scope>runtime</scope>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!-- Testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

### 4. Create application.properties

```properties
# Server
server.port=8080
server.servlet.context-path=/api

# PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/apexrise_db
spring.datasource.username=apexrise_user
spring.datasource.password=your_secure_password
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQL13Dialect
spring.jpa.properties.hibernate.format_sql=true

# JWT
jwt.secret=your-very-long-secret-key-for-jwt-minimum-256-bits-recommended
jwt.expiration=86400000

# Logging
logging.level.root=INFO
logging.level.com.apexrise=DEBUG
```

### 5. Setup PostgreSQL Database

```sql
-- Create database
CREATE DATABASE apexrise_db;

-- Create user
CREATE USER apexrise_user WITH PASSWORD 'your_secure_password';

-- Grant privileges
GRANT CONNECT ON DATABASE apexrise_db TO apexrise_user;
GRANT USAGE ON SCHEMA public TO apexrise_user;
GRANT CREATE ON SCHEMA public TO apexrise_user;

-- Connect to database
\c apexrise_db

-- Grant table creation privileges
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO apexrise_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO apexrise_user;
```

## Backend Structure

The project should have the following structure:

```
src/main/java/com/apexrise/
├── config/
│   ├── JwtTokenProvider.java
│   ├── SecurityConfig.java
│   └── JwtAuthenticationFilter.java
├── controller/
│   ├── AuthController.java
│   └── SyncController.java
├── entity/
│   ├── User.java
│   ├── Cow.java
│   ├── MilkRecord.java
│   ├── WakulimaSale.java
│   └── Expense.java
├── dto/
│   ├── LoginRequest.java
│   ├── LoginResponse.java
│   ├── SyncRequest.java
│   ├── SyncResponse.java
│   └── ConflictResolution.java
├── repository/
│   ├── UserRepository.java
│   ├── CowRepository.java
│   ├── MilkRecordRepository.java
│   ├── WakulimaSaleRepository.java
│   └── ExpenseRepository.java
├── service/
│   ├── AuthService.java
│   ├── SyncService.java
│   └── ConflictResolutionService.java
└── ApexRiseBackendApplication.java

src/main/resources/
├── application.properties
└── schema.sql
```

## API Endpoints

### Authentication
- `POST /auth/login` - User login
- `POST /auth/register` - User registration

### Sync
- `POST /sync` - Sync local changes with server
- `POST /sync/resolve-conflict` - Resolve data conflicts

## Running the Backend

```bash
# Build
mvn clean package

# Run
mvn spring-boot:run

# Or
java -jar target/apexrise-backend-1.0-SNAPSHOT.jar
```

The backend will be available at `http://localhost:8080/api/`
