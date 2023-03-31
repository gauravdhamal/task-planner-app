# Task-Planner-App

# REST API for Task Planner Application.

* I developed this REST API for Task Planner Application. This API performs all the fundamental CRUD operations on task planning.

## Tech Stack

* Java
* Spring Framework
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Swagger

## Modules

* Sprint Module
* User Module
* Task Module

## Features

* User(Manager/Employee) can able to create sprint.
* Create multiple tasks inside Sprint.
* Associate/Dissociate task to employee.
* Update Sprint/Task.
* Delete Sprint/Task.

## ER-Diagram


## Work-Flow


## Installation & Run



```

      #Changing server port
      server.port=8080

      #Spring datasource properties
      spring.datasource.url=jdbc:mysql://localhost:3306/taskplanner
      spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
      spring.datasource.username=root
      spring.datasource.password=root

      #ORM specific properties
      spring.jpa.show-sql=true
      spring.jpa.hibernate.ddl-auto=update

```

## API Root Endpoint

`https://localhost:8080/`

`http://localhost:8080/swagger-ui/index.html`

---

### Swagger UI

---

![image](https://user-images.githubusercontent.com/86916671/227711805-b76561ab-965a-44b7-a44b-45b016e5c278.png)

---

### Sprint Controller

---

![Sprint Controller](https://user-images.githubusercontent.com/86916671/227713127-b109c0ed-f0f1-4fb4-975c-ba624a136833.JPG)

---

### Task Controller

---

![Task Controller](https://user-images.githubusercontent.com/86916671/227713136-ba9b942e-7914-458e-a5c0-536204533248.JPG)

---

### User Controller

---

![User Controller](https://user-images.githubusercontent.com/86916671/227713165-b374353e-e08c-45bd-b1c4-d9152ec3da2c.JPG)

---
