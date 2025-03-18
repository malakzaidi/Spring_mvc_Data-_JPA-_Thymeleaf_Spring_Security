# Hospital Management System

![Hospital Management System](https://img.shields.io/badge/Spring%20Boot-Hospital%20Management-brightgreen)
![JPA](https://img.shields.io/badge/JPA-Repository-blue)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-5.3.3-orange)
![Bootstrap](https://img.shields.io/badge/Bootstrap-5.3.3-purple)

## Overview

This Hospital Management System is a web application built with Spring Boot that allows healthcare providers to manage patient records efficiently. The system provides a user-friendly interface for viewing, searching, and managing patient information.

## Features

- **Patient Management**: Add, view, and delete patient records
- **Pagination**: Navigate through patient lists with ease
- **Search Functionality**: Find patients by name
- **Health Status Tracking**: Monitor patient health status
- **Scoring System**: Track patient health scores

## Technologies Used

- **Backend**: Spring Boot, Spring Data JPA
- **Frontend**: Thymeleaf, Bootstrap 5.3.3
- **Database**: H2 Database (default, configurable)
- **Build Tool**: Maven/Gradle

## Project Structure

```
hospital-management/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/hospital/
│   │   │       ├── HospitalApplication.java
│   │   │       ├── entities/
│   │   │       │   └── Patient.java
│   │   │       ├── repositories/
│   │   │       │   └── PatientRepository.java
│   │   │       └── controllers/
│   │   │           └── PatientController.java
│   │   └── resources/
│   │       ├── templates/
│   │       │   └── patients.html
│   │       └── application.properties
│   └── test/
└── pom.xml
```

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven or Gradle
- Your favorite IDE (IntelliJ IDEA, Eclipse, etc.)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/hospital-management.git
   cd hospital-management
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```
   or
   ```bash
   gradle build
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
   or
   ```bash
   gradle bootRun
   ```

4. Open your browser and navigate to:
   ```
   http://localhost:8080/index
   ```

## Usage

### Patient Management

- **View Patients**: Navigate to the home page to see a list of all patients
- **Search Patients**: Use the search box to find patients by name
- **Delete Patient**: Click the "Delete" button next to a patient record to remove it
- **Pagination**: Use the navigation buttons at the bottom to move between pages

## Configuration

### Database Configuration

You can configure the database connection in `application.properties`:

```properties
# H2 Database
spring.datasource.url=jdbc:h2:mem:hospitaldb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Enable H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

### Pagination Configuration

You can modify the default pagination settings in the `PatientController.java` file:

```java
@GetMapping("/index")
public String index(Model model, 
                    @RequestParam(name = "page", defaultValue = "0") int p,
                    @RequestParam(name = "size", defaultValue = "4") int s,
                    @RequestParam(name = "keyword", defaultValue = "") String keyword) {
    // ...
}
```

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the project
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request


## Acknowledgments

- Spring Boot for the awesome framework
- Bootstrap for the responsive design
- Thymeleaf for the templating engine
