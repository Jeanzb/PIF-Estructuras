# 📌 Proyecto Gestor de Tareas con Spring Boot

Este es un sistema básico de gestión de tareas desarrollado con Java y Spring Boot. Permite crear, editar, eliminar, categorizar y marcar tareas como completadas, además de calcular el progreso general. La aplicación está diseñada para trabajar con estructuras de datos en memoria, utilizando listas dinámicas (`List<T>`), ideal para proyectos académicos o de práctica sin necesidad de persistencia en base de datos.

---

## 🚀 Funcionalidades

- Registro de usuarios (con validación de correo institucional)
- Gestión completa de tareas (CRUD)
- Categorización de tareas
- Visualización del progreso global
- Validación de formularios mediante Bean Validation
- Interfaz web con Thymeleaf
- Operación 100% en memoria (no requiere base de datos)

---

## 🧠 Tecnologías utilizadas

| Tecnología         | Descripción                          |
|-------------------|--------------------------------------|
| Java 17           | Lenguaje de programación principal   |
| Spring Boot       | Framework backend (MVC, servicios)   |
| Maven             | Gestión de dependencias              |
| Thymeleaf         | Motor de plantillas HTML             |
| HTML + JS + CSS   | Frontend básico                      |
| Bean Validation   | Validación de formularios (`jakarta.validation`) |
| IntelliJ IDEA o VS Code | IDE recomendado                 |

---

## 🗂️ Estructura del proyecto

ProyectoEstructuras/
├── src/main/java/com/example/proyectoestructuras/

│ ├── controllers/ # Controladores MVC

│ ├── models/ # Clases modelo (Tarea, Usuario)

│ ├── services/ # Lógica de negocio

│ └── ProyectoEstructurasApplication.java

├── src/main/resources/

│ ├── templates/ # Vistas Thymeleaf

│ ├── static/js/ # Scripts JS

│ └── application.properties # Configuración de la app

└── pom.xml # Configuración Maven
