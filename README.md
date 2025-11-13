📘 Descripción

Sistema Integral Papelería AMC es una aplicación web desarrollada para la gestión completa de una papelería, que integra los módulos de ventas, compras, productos, usuarios y categorías.

El sistema está construido bajo una arquitectura MVC en capas, utilizando Spring Boot 3 y Spring Data JPA, con seguridad implementada mediante Spring Security y JWT, y persistencia gestionada con MySQL y Flyway.

El frontend, desarrollado con HTML, CSS y JavaScript, permite la interacción directa con la API REST, ofreciendo una interfaz adaptable para pantallas táctiles y generación automática de tickets y reportes PDF mediante pdfmake.

Este es la primer version de un proyecto personal consolidando los conocimientos adquiridos en varios cursos.

⚙️ Arquitectura del proyecto

El backend está organizado en una arquitectura modular y escalable:
```
 ├── controller/ → Define los endpoints REST y gestiona las solicitudes del cliente.
 ├── service/ → Contiene la lógica de negocio y validaciones.
 ├── repository/ → Administra la persistencia con Spring Data JPA (Hibernate).
 ├── domain/ → Estructura entidades, DTOs y repositorios por módulo.
 └── infra/ → Maneja seguridad, excepciones y configuración global (CORS, JWT, etc.).
```
🚀 Funcionalidades principales
```
 ✅ Autenticación y autorización con Spring Security + JWT.
 ✅ Migraciones automáticas de base de datos con Flyway.
 ✅ CRUDs completos con eliminación lógica y paginación.
 ✅ Documentación interactiva con Swagger (SpringDoc OpenAPI).
 ✅ Generación de tickets y reportes PDF con pdfmake.
 ✅ Manejo de respuestas HTTP con ResponseEntity.
 ✅ Pruebas de endpoints realizadas con Postman.
```
🧮 Módulos incluidos

Ventas: Flujo de venta con carrito dinámico, cálculo automático de cambio y ticket generado en PDF.

Compras: Registro y control de entradas de productos.

Productos: Gestión completa del inventario con paginación y filtros.

Usuarios: Roles y permisos configurados con JWT.

Categorías: CRUD con eliminación lógica para mantener integridad referencial.

🧰 Tecnologías utilizadas

Backend:

Java 17

Spring Boot 3

Spring Data JPA (Hibernate)

Spring Security + JWT

Flyway

Swagger / SpringDoc

Maven

Base de datos:

MySQL

Frontend:

HTML, CSS, JavaScript

pdfmake (para generación de PDFs)

📊 Ejemplo de uso

El vendedor accede con su correo y contraseña (encriptada).

Visualiza los productos con stock y precio.

Selecciona productos para la venta mediante un panel táctil.

El sistema calcula el importe total, el efectivo recibido y muestra el cambio.

Finalmente, genera un ticket en PDF con el detalle de la transacción.

El administrador puede consultar un estado de cuenta filtrado por rango de fechas, con totales de ventas, costos y ganancias.

🧱 Próximas mejoras (Versión 2)

Implementar test unitarios y de integración.

Mejorar la interfaz visual (frontend moderno), el frontEnd se realizo con ayuda de IA

Desplegar la aplicación completa en la nube (Railway / Render / Oracle Cloud).


🧑‍💻 Autor

Aníbal Melchor
📍 Desarrollador Backend Java | Spring Boot | MySQL
