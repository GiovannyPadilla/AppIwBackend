
---

## 📋 Índice

- [Descripción del proyecto](#-descripción-del-proyecto)
- [Estado del proyecto](#-estado-del-proyecto)
- [Funcionalidades](#-funcionalidades)
- [Acceso al proyecto](#-acceso-al-proyecto)
- [Cómo ejecutar](#%EF%B8%8F-cómo-ejecutar)
- [Base de datos](#-base-de-datos)
- [Endpoints de la API](#-endpoints-de-la-api)
- [Tecnologías utilizadas](#-tecnologías-utilizadas)
- [Autor](#-autor)

---

## 📌 Descripción del proyecto

API REST desarrollada con **Spring Boot** que gestiona la autenticación de usuarios mediante sesiones HTTP y expone endpoints para el CRUD completo de usuarios y productos. Se conecta a una base de datos **SQL Server** y utiliza **Spring Security** para proteger los recursos.

Este backend sirve como núcleo de la plataforma **appiw**, consumido por el frontend desarrollado en React.

---

## 🚧 Estado del proyecto

<h4 align="center">
🚧 Proyecto en desarrollo 🚧
</h4>

---

## ✅ Funcionalidades

- `Autenticación`: inicio y cierre de sesión con sesiones HTTP seguras
- `Verificación de sesión`: endpoint `/me` que confirma si el usuario está autenticado
- `Registro de usuarios`: creación de cuentas con contraseña encriptada con BCrypt
- `Gestión de usuarios`: listar, actualizar y eliminar usuarios
- `CRUD de productos`: crear, leer, actualizar y eliminar productos del inventario
- `Búsqueda de productos`: filtrado por nombre mediante query parameter
- `Protección de rutas`: filtro de sesión que bloquea requests sin autenticación

---

## 📁 Acceso al proyecto

Puedes clonar el repositorio con:

```bash
git clone <https://github.com/GiovannyPadilla/AppIwBackend.git-repositorio>
cd AppIW
```

O descargar el ZIP directamente desde GitHub usando el botón **Code → Download ZIP**.

---

## 🛠️ Cómo ejecutar

### Requisitos previos

- Java 17+
- Gradle 9+
- SQL Server corriendo en `localhost:1433`
- Base de datos `LOGINDB` con schema `AppLogin` creados

### Configuración

Edita el archivo `src/main/resources/application.yml` con tus credenciales:

```yaml
spring:
  datasource:
    url: jdbc:sqlserver://localhost:1433;databaseName=LOGINDB;encrypt=false
    username: GIOVADB
    password: Password@123
server:
  port: 8080
```

### Ejecutar

```bash
./gradlew bootRun
```

La API quedará disponible en `http://localhost:8080`.

---

## 🗄️ Base de datos

Ejecuta estos scripts en SQL Server antes de levantar la aplicación:

```sql
-- Tabla de usuarios
CREATE TABLE AppLogin.USERS (
    ID            INT IDENTITY(1,1) PRIMARY KEY,
    USERNAME      NVARCHAR(100) NOT NULL UNIQUE,
    PASSWORD      NVARCHAR(255) NOT NULL,
    REGISTRY_DATE DATETIME2     DEFAULT GETDATE()
);

-- Tabla de productos
CREATE TABLE AppLogin.PRODUCTS (
    ID                 INT IDENTITY(1,1) PRIMARY KEY,
    NOMBRE             NVARCHAR(255) NOT NULL,
    DESCRIPCION        NVARCHAR(MAX) NULL,
    PRECIO             DECIMAL(10,2) NOT NULL,
    STOCK              INT           NOT NULL DEFAULT 0,
    CATEGORIA          NVARCHAR(100) NULL,
    FECHA_CREACION     DATETIME2     DEFAULT GETDATE(),
    FECHA_MODIFICACION DATETIME2     DEFAULT GETDATE()
);
```

---

## 🔌 Endpoints de la API

### Autenticación `/api/auth`

| Método | Endpoint | Descripción | Requiere auth |
|---|---|---|---|
| POST | `/api/auth/login` | Inicia sesión | No |
| POST | `/api/auth/logout` | Cierra sesión | No |
| GET | `/api/auth/me` | Verifica sesión activa | No |

### Usuarios `/api/users`

| Método | Endpoint | Descripción | Requiere auth |
|---|---|---|---|
| GET | `/api/users` | Lista todos los usuarios | No |
| POST | `/api/users` | Crea un usuario | No |
| PUT | `/api/users/{id}` | Actualiza un usuario | Sí |
| DELETE | `/api/users/{id}` | Elimina un usuario | Sí |

### Productos `/api/products`

| Método | Endpoint | Descripción | Requiere auth |
|---|---|---|---|
| GET | `/api/products` | Lista todos los productos | Sí |
| GET | `/api/products/{id}` | Obtiene producto por ID | Sí |
| GET | `/api/products/search?name=X` | Busca por nombre | Sí |
| POST | `/api/products` | Crea un producto | Sí |
| PUT | `/api/products/{id}` | Actualiza un producto | Sí |
| DELETE | `/api/products/{id}` | Elimina un producto | Sí |

---

## 💻 Tecnologías utilizadas

- [Java 17](https://www.oracle.com/java/)
- [Spring Boot 4.0.5](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [Spring Data JPA + Hibernate](https://spring.io/projects/spring-data-jpa)
- [Microsoft SQL Server](https://www.microsoft.com/sql-server)
- [Lombok](https://projectlombok.org/)
- [Gradle](https://gradle.org/)

---

## 👤 Autor

| [<img src="https://github.com/identicons/appiw.png" width=90><br><sub>Giova</sub>](https://github.com/) |
| :---: |
