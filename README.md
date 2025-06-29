# [Nombre del proyecto] - Backend

**NewProject** es una API RESTful desarrollada con Java y Spring Boot. Permite consultar información sobre productos escaneando su código de barras, accediendo a fuentes abiertas como:

- [Open Food Facts](https://world.openfoodfacts.org/)
- [Open Products Facts](https://world.openproductsfacts.org/)
- [Open Beauty Facts](https://world.openbeautyfacts.org/)

## 📝 Índice

1. [Descripción](#descripción)
2. [Características principales](#características-principales)
3. [Tecnologías](#tecnologías)
4. [Estructura del proyecto](#estructura-del-proyecto)
5. [Requisitos previos](#requisitos-previos)
6. [Instalación y configuración](#instalación-y-configuración)
7. [Configuración de la base de datos](#configuración-de-la-base-de-datos)
8. [Ejecución del proyecto](#ejecución-del-proyecto)
9. [Uso de la aplicación](#uso-de-la-aplicación)
10. [Uso de la API (Swagger)](#uso-de-la-api-swagger)
11. [Endpoints principales](#endpoints-principales)
12. [Pruebas](#pruebas)
13. [Mantenimiento y evolución](#mantenimiento-y-evolución)
14. [Solución de problemas](#solución-de-problemas)
15. [Autor](#autor)
16. [Licencia](#licencia)

## Descripción

[Nombre del proyecto] ayuda a los usuarios a poder consultar la información de cualquier producto simplemente escaneando su código de barras desde su propio dispositivo móvil y de una forma sencilla e intuitiva.

- Escaneo de cualquier tipo de producto, ya sea un alimento, productos de limpieza, productos de higiene personal...
- Obtención de toda la información de cualquier producto escaneado independientemente del tipo de producto que sea.
- Posibilidad de poder hacer la lista de la compra, añadiendo los productos mediante su escaneo del código de barras.

> 🚀 **Nota:** Este README ofrece un resumen de alto nivel.

<!-- ## Características principales

- CRUD de **Usuarios**
- CRUD de **Hábitos** y **Tipos de hábitos**
- Registro diario de cumplimiento
- Gestión de **Objetivos** (Goals)
- Documentación interactiva con Swagger/OpenAPI
- Autenticación JWT
- Interfaz web responsive -->

## Tecnologías

- **Backend**: Java 21, Spring Boot 3.4.4, Spring Data JPA
- **Frontend**: Kotlin
- **Base de datos**: PostgreSQL
- **Contenedores**: Docker & Docker Compose
- **Documentación**: springdoc-openapi UI (Swagger)
- **Testing**: JUnit, Mockito, Postman

## Estructura del proyecto

```
NewProject_API/
├── backend/
│   └── NewProject-Api/
│       ├── src/main/java/alvarompdev/newprojectapi/
│       │   ├── config                 # Elementos de configuración
│       │   ├── controller             # Controladores REST
│       │   ├── custom_exceptions      # Excepciones personalizadas
│       │   ├── dto                    # DTOs de salida
│       │   ├── entity                 # Entidades JPA
│       │   ├── repository             # Repositorios Spring Data
│       │   ├── service                # Interfaces de la lógica de negocio
│       │   └── MainApp.java           # Clase principal de la API
│       ├── src/main/resources/
│       │   └── application.properties # Configuración general
│       └── src/test/java/alvarompdev/newprojectapi/
│           ├── controller             # Pruebas de los controladores REST
│           ├── dto                    # Pruebas de los DTOs
│           ├── entity                 # Pruebas de las entidades JPA
│           ├── repository             # Pruebas de los repositorios Spring Data
│           └── service                # Pruebas de la lógica de negocio
├── data/
│   └── postgres/                      # Persistencia de la base de datos PostgreSQL (Docker volume)
├── scripts/
│   └── docker-compose.yml             # Configuración Docker
├── docs/                              # Documentación del proyecto
└── README.md                          # Documentación principal del repositorio
```

## Requisitos previos

Antes de comenzar, asegúrate de tener instalado:

- **Java 21** o superior
- **Maven 3.8+** 
- **Docker** y **Docker Compose**
- **Git**

### Verificar instalaciones

```bash
# Verificar Java
java -version

# Verificar Maven
mvn -version

# Verificar Docker
docker --version
docker-compose --version
```

## Instalación y configuración

### 1. Clonar el repositorio

```bash
git https://github.com/alvarompdev/NewProject_API.git
cd backend
cd NewProject-API
```

### 2. Descargar dependencias

```bash
mvn clean install
```

## Configuración de la base de datos

### Usar Docker

#### 1. Levantar el contenedor PostgreSQL

```bash
# Navegar a la carpeta scripts
cd scripts

# Levantar el contenedor MySQL
docker-compose up -d
```

#### 2. Verificar que el contenedor esté ejecutándose

```bash
docker ps
```

Deberías ver un contenedor PostgreSQL ejecutándose en el puerto 5432.

#### 3. Verificar la conexión (opcional)

```bash
# Conectarse a PostgreSQL desde línea de comandos
docker exec -it compra-db psql -U compra_user -d compra_db
# Contraseña: compra_pass
```

### Configuración de propiedades

El archivo `application.properties` debe contener: (añadir el properties terminado aquí)

```properties

```

## Ejecución del proyecto

### 1. Asegurar que PostgreSQL esté ejecutándose

```bash
# Si usas Docker (si ya tienes el contenedor creado, puedes saltar al siguiente paso)
cd scripts
docker-compose up -d

# Verificar estado
docker ps
```

### 2. Ejecutar la aplicación

```bash
# Desde la raíz del proyecto
mvn spring-boot:run
```

O alternativamente:

```bash
# Compilar y ejecutar JAR
mvn clean package
java -jar target/trackup-0.0.1-SNAPSHOT.jar (modificar con el nombre de mi proyecto)
```

### 3. Verificar que la aplicación esté funcionando

- **Mediante web**: A pesar de que no haya aplicación web, puedes ir a tu navegador y en su URL escribir http://localhost:8080/api/product/5410041001204. Si ves un montón de texto, significa que todo está correcto y la aplicación está funcionando correctamente.
- **Swagger UI**: http://localhost:8080/swagger-ui.html

## Autor

**Álvaro Muñoz Panadero** (Software Developer)  
📧 alvaromp.dev@gmail.com  
🌐 [GitHub](https://github.com/alvarompdev)