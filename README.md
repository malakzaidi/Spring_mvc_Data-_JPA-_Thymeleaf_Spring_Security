# 🏥 Hospital Management System

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen)
![Spring MVC](https://img.shields.io/badge/Spring%20MVC-Latest-green)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-Latest-orange)
![Bootstrap](https://img.shields.io/badge/Bootstrap-5.3.3-purple)
![JPA](https://img.shields.io/badge/Spring%20Data%20JPA-Latest-blue)
![Validation](https://img.shields.io/badge/Spring%20Validation-Latest-red)

## 🎬 Video Demo

https://github.com/user-attachments/assets/02bcefcc-282c-44ff-a00e-88e0a043342e

https://github.com/user-attachments/assets/cb300cfe-4a90-4e26-8cd8-6ede79729f55

## 📑 Table of Contents

- [Overview](#-overview)
- [Technologies Used](#️-technologies-used)
- [Spring MVC Architecture](#️-spring-mvc-architecture)
- [Thymeleaf Integration](#-thymeleaf-integration)
- [Project Structure](#-project-structure)
- [Key Features](#-key-features)
- [Pagination Implementation](#-pagination-implementation)
- [Form Validation](#-form-validation)
- [Thymeleaf Layout System](#-thymeleaf-layout-system)
- [Installation & Setup](#-installation--setup)
- [Usage Guide](#-usage-guide)
- [API Reference](#-api-reference)
- [Contributing](#-contributing)

## 📋 Overview

The Hospital Management System is a comprehensive web application built using Spring MVC and Thymeleaf that enables healthcare providers to efficiently manage patient records. The system provides a robust and user-friendly interface for viewing, searching, editing, and deleting patient information with support for pagination, form validation, and responsive design.

## 🛠️ Technologies Used

| Technology | Description | Version |
|------------|-------------|---------|
| ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-green?style=for-the-badge&logo=springboot) | Application framework | 3.2.0 |
| ![Spring MVC](https://img.shields.io/badge/Spring%20MVC-green?style=for-the-badge&logo=spring) | Web framework | Latest |
| ![Thymeleaf](https://img.shields.io/badge/Thymeleaf-dark?style=for-the-badge&logo=thymeleaf) | Template engine | Latest |
| ![Bootstrap](https://img.shields.io/badge/Bootstrap-purple?style=for-the-badge&logo=bootstrap) | Frontend framework | 5.3.3 |
| ![JPA](https://img.shields.io/badge/Spring%20Data%20JPA-blue?style=for-the-badge&logo=spring) | Data persistence | Latest |
| ![MySQL](https://img.shields.io/badge/MySQL-blue?style=for-the-badge&logo=mysql) | Database | 8.0 |
| ![Maven](https://img.shields.io/badge/Maven-red?style=for-the-badge&logo=apachemaven) | Build tool | Latest |
| ![Validation](https://img.shields.io/badge/Validation-green?style=for-the-badge&logo=spring) | Form validation | Latest |

## 🏗️ Spring MVC Architecture

Spring MVC (Model-View-Controller) is a framework for building web applications. In our Hospital Management System:

- **Model**: Represented by our `Patient` entity and `PatientRepository` interface
- **View**: Implemented using Thymeleaf templates
- **Controller**: The `PatientController` class processes user requests and returns appropriate views

The flow of a typical request in our application:

1. User makes a request (e.g., `/index`)
2. The `PatientController` processes the request
3. Business logic is executed, often involving data retrieval from the `PatientRepository`
4. The controller puts necessary data in the Model
5. The controller returns a view name (e.g., "patients")
6. Spring resolves the view using Thymeleaf
7. The rendered HTML is sent back to the user

## 🌿 Thymeleaf Integration

Thymeleaf is a modern server-side Java template engine that enables us to create dynamic HTML pages. In our project, we use it to:

- Iterate over collections (`th:each`)
- Display model attributes (`th:text`)
- Create URLs (`th:href` with `@{}`)
- Process forms (`th:action`, `th:value`)
- Conditionally apply classes (`th:class`)
- Handle form validation errors (`th:errors`)
- Implement layout inheritance (`layout:decorate`)

### Code Analysis: Thymeleaf Templates

Let's analyze the provided Thymeleaf templates:

#### Template Layout (`template1.html`)

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org" 
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
  <meta charset="UTF-8">
  <title>Title</title>
  <link rel="stylesheet" href="/webjars/bootstrap/5.3.3/css/bootstrap.min.css" />
  <script src="/webjars/bootstrap/5.3.3/js/bootstrap.bundle.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <!-- Navigation bar content -->
</nav>
<section layout:fragment="content1"></section>
</body>
</html>
```

This is the master template that defines the common layout structure. It includes:
- The navigation bar present on all pages
- A placeholder for page-specific content (`layout:fragment="content1"`)
- Common CSS and JavaScript resources

#### Patient List Page

```html
<div layout:fragment="content1">
<div class="p-3">
    <div class="card">
        <div class="card-header"> Liste des patients </div>
        <div class="card-body">
            <form method="get" th:action="@{index}">
                <!-- Search form -->
            </form>
        <table class="table">
            <!-- Table content with th:each for patient list -->
        </table>
            <ul class="nav nav-pills">
                <!-- Pagination controls -->
            </ul>
        </div>
    </div>
</div>
</div>
```

This template inherits from the master layout and provides:
- A search form
- A table of patients with dynamic data
- Edit and delete buttons for each patient
- Pagination controls

## 📁 Project Structure

```
hospital-management/
├── src/main/
│   ├── java/com/hospital/
│   │   ├── HospitalApplication.java
│   │   ├── entities/
│   │   │   └── Patient.java
│   │   ├── repositories/
│   │   │   └── PatientRepository.java
│   │   └── controllers/
│   │       └── PatientController.java
│   ├── resources/
│       ├── templates/
│       │   ├── template1.html
│       │   ├── patients.html
│       │   ├── formPatients.html
│       │   └── editPatients.html
│       ├── static/
│       └── application.properties
└── pom.xml
```

## 🌟 Key Features

### Patient Management

- **View Patients**: List all patients with pagination
- **Add Patients**: Form with validation for adding new patients
- **Edit Patients**: Update existing patient information
- **Delete Patients**: Remove patients with confirmation dialog
- **Search Patients**: Filter patients by name

### Pagination Implementation

Our application implements server-side pagination using Spring Data JPA's `Page` and `Pageable` interfaces:

```java
@GetMapping("/index")
public String index(Model model, 
                   @RequestParam(name= "page", defaultValue = "0") int p,
                   @RequestParam(name="size", defaultValue = "4") int s,
                   @RequestParam(name="keyword", defaultValue = "") String keyword) {
    Page<Patient> pagePatients = patientRepository.findByNomContains(keyword, PageRequest.of(p,s));
    model.addAttribute("patients", pagePatients.getContent());
    model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
    model.addAttribute("currentPage", p);
    model.addAttribute("keyword", keyword);
    return "patients";
}
```

The repository interface leverages Spring Data JPA to implement pagination:

```java
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findByNomContains(String keyword, Pageable pageable);
}
```

This provides:
- Custom search method that returns a `Page` object
- Control over page size and number
- Total pages and elements information
- Sorted results

The Thymeleaf template then renders pagination controls:

```html
<ul class="nav nav-pills">
    <li th:each="value, item: ${pages}">
        <a th:href="@{/index(page=${item.index}, keyword=${keyword})}"
           th:class="${currentPage==item.index? 'btn btn-info ms-1' : 'btn btn-outline-info'}"
           th:text="${1+item.index}"></a>
    </li>
</ul>
```

## ✅ Form Validation

Spring Validation is integrated into our application through three key components:

1. **Dependency Addition**: In `pom.xml`
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

2. **Entity Annotations**: In `Patient.java`
```java
@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 2, max = 50, message = "Le nom doit contenir entre 2 et 50 caractères")
    private String nom;
    
    @NotNull(message = "La date de naissance est obligatoire")
    @Past(message = "La date de naissance doit être dans le passé")
    private Date dateNaissance;
    
    private boolean malade;
    
    @Min(value = 0, message = "Le score doit être positif")
    @Max(value = 100, message = "Le score ne peut pas dépasser 100")
    private int score;
}
```

3. **Controller Validation**: In `PatientController.java`
```java
@PostMapping("/save")
public String save(Model model, @Valid Patient patient,
                  BindingResult bindingResult,
                  @RequestParam(defaultValue = "0") int page,
                  @RequestParam(defaultValue = "") String keyword) {
    if(bindingResult.hasErrors()) return "formPatients";
    patientRepository.save(patient);
    return "redirect:/index?page="+page+"&keyword="+keyword;
}
```

4. **Template Error Display**: In Thymeleaf templates
```html
<input class="form-control" type="text" name="nom" th:value="${patient.nom}">
<span class="text-danger" th:errors="${patient.nom}"></span>
```

## 🧩 Thymeleaf Layout System

Our application uses Thymeleaf Layout Dialect (from `ultraq.net.nz`) to implement template inheritance:

```html
<!-- In child templates -->
<html xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout" 
      layout:decorate="template1">

<!-- Content specific to this page -->
<div layout:fragment="content1">
    <!-- Page content here -->
</div>
```

This approach:
- Reduces duplication by centralizing common elements like navigation
- Maintains consistent look and feel across the application
- Makes templates more maintainable

## 🔧 Installation & Setup

### Prerequisites

- Java JDK 17+
- Maven or Gradle
- MySQL 8.0+
- Your favorite IDE (IntelliJ IDEA, Eclipse, etc.)

### Steps

1. Clone the repository:
```bash
git clone https://github.com/yourusername/hospital-management.git
cd hospital-management
```

2. Create MySQL database:
```sql
CREATE DATABASE HOSPITAL;
```

3. Build the project:
```bash
mvn clean install
```

4. Run the application:
```bash
mvn spring-boot:run
```

5. Access the application:
```
http://localhost:8080/
```

### Configuration

#### Database Configuration (application.properties)

```properties
# Application Name
spring.application.name=hospital

# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/HOSPITAL
spring.datasource.username=root
spring.datasource.password=12345
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

# Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Server Configuration
server.port=8080

# Thymeleaf Configuration
spring.thymeleaf.cache=false
```

## 📝 Usage Guide

### Managing Patients

1. **View All Patients**:
   - Navigate to `/index` or the home page
   - Use pagination to navigate through records

2. **Search for Patients**:
   - Enter a name in the search box
   - Click the search button
   - Results will be paginated if numerous

3. **Add a New Patient**:
   - Click on "Nouveau" in the Patients dropdown
   - Fill out the form with patient details
   - Submit to create a new patient record

4. **Edit a Patient**:
   - Click the "Edit" button next to a patient record
   - Update the information in the form
   - Submit to save changes

5. **Delete a Patient**:
   - Click the trash icon next to a patient record
   - Confirm deletion in the dialog
   - Patient will be removed from the database

## 📚 API Reference

### Controller Endpoints

| Endpoint | Method | Description | Parameters |
|----------|--------|-------------|------------|
| `/index` | GET | List patients | page, size, keyword |
| `/delete` | GET | Delete a patient | id, page, keyword |
| `/` | GET | Redirect to index | None |
| `/formPatient` | GET | Show add patient form | None |
| `/save` | POST | Save a patient | patient object, page, keyword |
| `/editPatient` | GET | Show edit patient form | id, page, keyword |

### Repository Methods

| Method | Description | Return Type |
|--------|-------------|------------|
| `findByNomContains` | Search patients by name | Page\<Patient\> |
| `save` | Save or update a patient | Patient |
| `deleteById` | Delete a patient by ID | void |
| `findById` | Find a patient by ID | Optional\<Patient\> |

## 👥 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the project
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request
