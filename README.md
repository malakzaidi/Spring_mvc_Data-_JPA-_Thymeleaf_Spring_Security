# 🏥 Hospital Management System

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen)
![Spring MVC](https://img.shields.io/badge/Spring%20MVC-Latest-green)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-Latest-orange)
![Bootstrap](https://img.shields.io/badge/Bootstrap-5.3.3-purple)
![JPA](https://img.shields.io/badge/Spring%20Data%20JPA-Latest-blue)
![Validation](https://img.shields.io/badge/Spring%20Validation-Latest-red)
![Spring Security](https://img.shields.io/badge/Spring%20Security-Latest-yellow)


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
- [Spring Security Implementation](#-spring-security-implementation)
  - [InMemory Authentication](#-inmemory-authentication)
  - [JDBC Authentication](#-jdbc-authentication)
  - [UserDetailsService Authentication](#-userdetailsservice-authentication)
- [Installation & Setup](#-installation--setup)
- [Usage Guide](#-usage-guide)
- [API Reference](#-api-reference)
- [Contributing](#-contributing)

## 📋 Overview

The Hospital Management System is a comprehensive web application built using Spring MVC, Thymeleaf, and Spring Data JPA that enables healthcare providers to efficiently manage patient records. The system provides a robust and user-friendly interface for viewing, searching, editing, and deleting patient information with support for pagination, form validation, responsive design, and security features.

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
| ![Spring Security](https://img.shields.io/badge/Spring%20Security-yellow?style=for-the-badge&logo=springsecurity) | Authentication & Authorization | Latest |

## 🏗️ Spring MVC Architecture

Spring MVC (Model-View-Controller) is a framework for building web applications. In our Hospital Management System:

- **Model**: Represented by our `Patient` entity and `PatientRepository` interface
- **View**: Implemented using Thymeleaf templates
- **Controller**: The `PatientController` class processes user requests and returns appropriate views

The flow of a typical request in our application:

1. User makes a request (e.g., `/index`)
2. Spring Security intercepts and authenticates the request
3. The `PatientController` processes the request
4. Business logic is executed, often involving data retrieval from the `PatientRepository`
5. The controller puts necessary data in the Model
6. The controller returns a view name (e.g., "patients")
7. Spring resolves the view using Thymeleaf
8. The rendered HTML is sent back to the user

## 🌿 Thymeleaf Integration

Thymeleaf is a modern server-side Java template engine that enables us to create dynamic HTML pages. In our project, we use it to:

- Iterate over collections (`th:each`)
- Display model attributes (`th:text`)
- Create URLs (`th:href` with `@{}`)
- Process forms (`th:action`, `th:value`)
- Conditionally apply classes (`th:class`)
- Handle form validation errors (`th:errors`)
- Implement layout inheritance (`layout:decorate`)
- Access security information (`sec:authorize`)

### Code Analysis: Thymeleaf Templates

Let's analyze the provided Thymeleaf templates:

#### Template Layout (`template1.html`)

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org " 
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout "
      xmlns:sec="http://www.thymeleaf.org/extras/spring-security">
<head>
  <meta charset="UTF-8">
  <title>Title</title>
  <link rel="stylesheet" href="/webjars/bootstrap/5.3.3/css/bootstrap.min.css" />
  <script src="/webjars/bootstrap/5.3.3/js/bootstrap.bundle.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <!-- Navigation bar content -->
  <div class="container-fluid">
    <ul class="navbar-nav">
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">
          Patients
        </a>
        <ul class="dropdown-menu">
          <li><a class="dropdown-item" th:href="@{/index}">Rechercher</a></li>
          <li sec:authorize="hasAuthority('ADMIN')"><a class="dropdown-item" th:href="@{/formPatient}">Nouveau</a></li>
        </ul>
      </li>
    </ul>
    <ul class="navbar-nav" sec:authorize="isAuthenticated()">
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">
          <span sec:authentication="name"></span>
        </a>
        <ul class="dropdown-menu">
          <li><a class="dropdown-item" th:href="@{/logout}">Logout</a></li>
        </ul>
      </li>
    </ul>
  </div>
</nav>
<section layout:fragment="content1"></section>
</body>
</html>
```

This is the master template that defines the common layout structure. It includes:
- The navigation bar present on all pages
- A placeholder for page-specific content (`layout:fragment="content1"`)
- Common CSS and JavaScript resources
- Security-based conditional elements

#### Patient List Page

```html
<div layout:fragment="content1">
<div class="p-3">
    <div class="card">
        <div class="card-header"> Liste des patients </div>
        <div class="card-body">
            <form method="get" th:action="@{index}">
                <!-- Search form -->
                <div class="input-group">
                    <input type="text" name="keyword" class="form-control" th:value="${keyword}">
                    <button class="btn btn-primary">Search</button>
                </div>
            </form>
        <table class="table">
            <thead>
                <tr>
                    <th>ID</th><th>Nom</th><th>Date Naissance</th><th>Malade</th><th>Score</th><th sec:authorize="hasAuthority('ADMIN')">Actions</th>
                </tr>
            </thead>
            <tbody>
                <tr th:each="p:${patients}">
                    <td th:text="${p.id}"></td>
                    <td th:text="${p.nom}"></td>
                    <td th:text="${p.dateNaissance}"></td>
                    <td th:text="${p.malade}"></td>
                    <td th:text="${p.score}"></td>
                    <td sec:authorize="hasAuthority('ADMIN')">
                        <a th:href="@{/editPatient(id=${p.id}, page=${currentPage}, keyword=${keyword})}" class="btn btn-success">
                            <i class="bi bi-pencil"></i>
                        </a>
                        <a onclick="return confirm('Are you sure?')" th:href="@{/delete(id=${p.id}, page=${currentPage}, keyword=${keyword})}" class="btn btn-danger">
                            <i class="bi bi-trash"></i>
                        </a>
                    </td>
                </tr>
            </tbody>
        </table>
            <ul class="nav nav-pills">
                <li th:each="value, item: ${pages}">
                    <a th:href="@{/index(page=${item.index}, keyword=${keyword})}"
                       th:class="${currentPage==item.index? 'btn btn-info ms-1' : 'btn btn-outline-info'}"
                       th:text="${1+item.index}"></a>
                </li>
            </ul>
        </div>
    </div>
</div>
</div>
```

This template inherits from the master layout and provides:
- A search form
- A table of patients with dynamic data
- Edit and delete buttons for each patient (visible only to admins)
- Pagination controls

## 📁 Project Structure

```
hospital-management/
├── src/main/
│   ├── java/com/hospital/
│   │   ├── HospitalApplication.java
│   │   ├── entities/
│   │   │   ├── Patient.java
│   │   │   ├── User.java
│   │   │   └── Role.java
│   │   ├── repositories/
│   │   │   ├── PatientRepository.java
│   │   │   ├── UserRepository.java
│   │   │   └── RoleRepository.java
│   │   ├── security/
│   │   │   ├── SecurityConfig.java
│   │   │   └── SecurityController.java
│   │   ├── services/
│   │   │   ├── UserServiceImpl.java
│   │   │   └── UserDetailsServiceImpl.java
│   │   └── controllers/
│   │       └── PatientController.java
│   ├── resources/
│       ├── templates/
│       │   ├── template1.html
│       │   ├── patients.html
│       │   ├── formPatients.html
│       │   ├── editPatients.html
│       │   ├── login.html
│       │   └── notAuthorized.html
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

### Security Features

- **Authentication**: Multiple authentication methods (InMemory, JDBC, UserDetailsService)
- **Authorization**: Role-based access control
- **Login/Logout**: Custom login page and logout functionality
- **Access Control**: Restrict certain operations to admins only

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
<div class="mb-3">
    <label class="form-label">Nom :</label>
    <input class="form-control" type="text" name="nom" th:value="${patient.nom}">
    <span class="text-danger" th:errors="${patient.nom}"></span>
</div>
```

## 🧩 Thymeleaf Layout System

Our application uses Thymeleaf Layout Dialect (from `ultraq.net.nz`) to implement template inheritance:

```html
<!-- In child templates -->
<html xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout " 
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

## 🎬 Video Demo : Before Securing the app

https://github.com/user-attachments/assets/02bcefcc-282c-44ff-a00e-88e0a043342e 

## 🔒 Spring Security Implementation

The application implements three different authentication strategies to demonstrate Spring Security flexibility:

### 🔑 InMemory Authentication

InMemory Authentication stores user credentials in memory, making it suitable for development, testing, or applications with a small fixed set of users.

## 🎬 Video Demo : After Securing the app with InMemory Authentication

https://github.com/user-attachments/assets/cb300cfe-4a90-4e26-8cd8-6ede79729f55 

#### Configuration:

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                .requestMatchers("/index").hasAuthority("USER")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/index", true)
            )
            .exceptionHandling(exception -> exception
                .accessDeniedPage("/notAuthorized")
            );
        
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        PasswordEncoder passwordEncoder = passwordEncoder();
        return new InMemoryUserDetailsManager(
            User.withUsername("user").password(passwordEncoder.encode("1234")).authorities("USER").build(),
            User.withUsername("admin").password(passwordEncoder.encode("1234")).authorities("USER", "ADMIN").build()
        );
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

**Key Features:**
- Users and passwords defined directly in code
- No database interaction required
- Uses BCrypt password encoding
- Simple to set up and understand
- Limited to predefined users at startup
- Credentials lost on application restart

**Best For:**
- Development environments
- Simple demos and POCs
- Testing security configurations
- Applications with fixed, limited users
- Learning Spring Security basics

### 🗄️ JDBC Authentication

JDBC Authentication retrieves user credentials from a database, providing more flexibility and persistence than InMemory authentication.


## 🎬 Video Demo : After Securing the app with JDBC Authentication

https://github.com/user-attachments/assets/e27d4be7-ecdf-4c06-b4b1-14ed67c4d7eb 

### Database Visualisation

![Image](https://github.com/user-attachments/assets/c13d0278-4db7-4f80-9ccf-b7cf20482e41)

#### Database Schema:

```sql
CREATE TABLE users (
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(120) NOT NULL,
    active BOOLEAN NOT NULL
);

CREATE TABLE authorities (
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users(username)
);
```

#### Configuration:

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                // Same authorization rules as before
            )
            .formLogin(form -> form
                // Same form login configuration
            )
            .exceptionHandling(exception -> exception
                // Same exception handling
            );
        
        return http.build();
    }

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        
        // Optionally customize queries if not using default schema
        manager.setUsersByUsernameQuery("SELECT username, password, active FROM users WHERE username=?");
        manager.setAuthoritiesByUsernameQuery("SELECT username, authority FROM authorities WHERE username=?");
        
        return manager;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

**Key Features:**
- Uses database tables for user authentication
- Follows Spring Security's default schema or custom queries
- Persists user credentials across application restarts
- Allows user management via database operations
- More scalable than InMemory authentication

**Best For:**
- Applications needing persistent user storage
- Systems with moderate user management needs
- Scenarios leveraging existing user databases
- Applications with changing user base

### 👤 UserDetailsService Authentication

UserDetailsService provides the most flexible authentication method by implementing a custom service to load user details from any source.

### Database Visualisation : custom app_user , app_role and app_user_roles , with encoded passwords 

![Image](https://github.com/user-attachments/assets/d976aeba-162b-4143-90e6-199f1f2b05a5)

![Image](https://github.com/user-attachments/assets/fce33b90-8339-442c-9b85-0f0f5b4f44de)

### Note :
- The video demonstration is similar to the previous method ( users created can access to the application after successful authentication and based on the role affected to them , they use it )

#### Entity Classes:

```java
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class User {
    @Id
    private String userId;
    private String username;
    private String password;
    private boolean active;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();
}

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users = new ArrayList<>();
}
```

#### Repositories:

```java
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);
}
```

#### Service Implementation:

```java
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        
        if(user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });
        
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            authorities
        );
    }
}
```

#### Configuration:

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                // Same authorization rules as before
            )
            .formLogin(form -> form
                // Same form login configuration
            )
            .exceptionHandling(exception -> exception
                // Same exception handling
            )
            .userDetailsService(userDetailsService);
        
        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}
```


**Key Features:**
- Complete control over user authentication process
- Custom data model for users and authorities
- Flexible integration with any data source
- Support for complex authorization rules
- Can implement custom logic during authentication
- Ideal for applications with unique security requirements

**Best For:**
- Enterprise applications with complex user models
- Systems integrating with external identity providers
- Applications with custom authorization needs
- Scenarios where users have different types of credentials
- Projects requiring fine-grained security control

## 🔧 Installation & Setup

### Prerequisites

- Java JDK 17+
- Maven 3.6+
- MySQL 8.0+
- Your favorite IDE (IntelliJ IDEA, Eclipse, etc.)

### Steps

1. Clone the repository:
```bash
git clone https://github.com/malakzaidi/Spring_mvc_Data-_JPA-_Thymeleaf_Spring_Security.git
cd Spring_mvc_Data-_JPA-_Thymeleaf_Spring_Security
```

2. Create MySQL database:
```sql
CREATE DATABASE HOSPITAL;
```

3. Configure the application.properties file:
```properties
# Application Name
spring.application.name=hospital

# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/HOSPITAL
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

# Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Server Configuration
server.port=8080

# Thymeleaf Configuration
spring.thymeleaf.cache=false
```

4. Build the project:
```bash
mvn clean install
```

5. Run the application:
```bash
mvn spring-boot:run
```

6. Access the application:
```
http://localhost:8080/
```

## 📝 Usage Guide

### Managing Patients

1. **View All Patients**:
   - Log in with your credentials
   - Navigate to `/index` or click "Rechercher" in the Patients dropdown
   - Use pagination to navigate through records

2. **Search for Patients**:
   - Enter a name in the search box
   - Click the search button
   - Results will be paginated if numerous

3. **Add a New Patient** (Requires ADMIN role):
   - Click on "Nouveau" in the Patients dropdown
   - Fill out the form with patient details
   - Submit to create a new patient record

4. **Edit a Patient** (Requires ADMIN role):
   - Click the "Edit" button next to a patient record
   - Update the information in the form
   - Submit to save changes

5. **Delete a Patient** (Requires ADMIN role):
   - Click the trash icon next to a patient record
   - Confirm deletion in the dialog
   - Patient will be removed from the database

### Authentication and Security

1. **Login**:
   - Navigate to `/login` or try to access any secured page
   - Enter your username and password
   - Click Login

2. **Logout**:
   - Click on your username in the top right corner
   - Click "Logout" from the dropdown menu

3. **Access Control**:
   - Users with only USER role can view patients
   - Users with ADMIN role can add, edit, and delete patients
   - Attempting unauthorized actions redirects to the "Not Authorized" page

## 📚 API Reference

### Controller Endpoints

| Endpoint | Method | Description | Parameters | Required Authority |
|----------|--------|-------------|------------|-------------------|
| `/index` | GET | List patients | page, size, keyword | USER |
| `/delete` | GET | Delete a patient | id, page, keyword | ADMIN |
| `/` | GET | Redirect to index | None | USER |
| `/formPatient` | GET | Show add patient form | None | ADMIN |
| `/save` | POST | Save a patient | patient object, page, keyword | ADMIN |
| `/editPatient` | GET | Show edit patient form | id, page, keyword | ADMIN |
| `/login` | GET | Show login form | None | PUBLIC |
| `/notAuthorized` | GET | Access denied page | None | AUTHENTICATED |

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
