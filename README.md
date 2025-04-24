# Sistema de Gestión de Libros

Este proyecto es un sistema de gestión de libros, donde se pueden registrar libros, crear autores y asociar autores a libros. El sistema está compuesto por tres microservicios:

- **libro-service**: Para registrar y consultar libros.
- **autor-service**: Para registrar autores y asociarlos a libros.
- **gateway-service**: (opcional) API Gateway para enrutar las solicitudes entre los microservicios.

## Tecnologías

- **Spring Boot 3** con WebFlux
- **MongoDB** como base de datos (o PostgreSQL si se prefiere)
- **Docker** para contenerización
- **GitHub Actions** para CI/CD
- **JUnit** + **Mockito** + **WebTestClient** para pruebas automatizadas
- **Docker Compose** para levantar los servicios

## Endpoints

### 1. **Libro Service**

- **Crear un libro**  
  `POST /libros`  
  Cuerpo de la solicitud:  
  ```json
  {
    "id": "1",
    "titulo": "Clean Code",
    "isbn": "1234567890"
  }
 ```json
{
  "id": "1",
  "titulo": "Clean Code",
  "isbn": "1234567890"
}
 ```
- Listar todos los libros 
`GET /libros`
 ```json
[
  {
    "id": "1",
    "titulo": "Clean Code",
    "isbn": "1234567890"
  },
  {
    "id": "2",
    "titulo": "Design Patterns",
    "isbn": "9876543210"
  }
]
 ```
- Buscar libro por ID
  `GET /libros/{id}`

   ```json
   {
  "id": "1",
  "titulo": "Clean Code",
  "isbn": "1234567890"
}
## 2.Autor Service 
- Crear un autor
`POST/libros`

 ```json
{
  "id": "1",
  "nombre": "Robert C. Martin"
}
 ```
- Asignar autor a un libro 
`PUT/libros/{id}/autor`
Cuerpo de la solicitud:
 ```json
{
  "autorId": "1"
}
 ```
 ```json
{
  "id": "1",
  "titulo": "Clean Code",
  "isbn": "1234567890",
  "autor": {
    "id": "1",
    "nombre": "Robert C. Martin"
  }
}
 ```
----
## Instalación y ejecución 
## - Requisitos previos
- Java 17 (JDK)
- Maven o Gradle
- Docker (para contenerizar los microservicios)
- GitHub Actions para CI/CD (opcional, pero recomendado)

## - Pasos para ejecutar localmente 
1. Clonar el repositorio
```bash
git clone https://github.com/tu-usuario/sistema-gestion-libros.git
cd sistema-gestion-libros
```
2. Compilar el proyecto: si estas usando Maven:
```bash
mvn clean install
```
O si usas Gradle:
```bash
gradle build
```
3. Levantar los servicios usando Docker Compose: Desde la raíz del proyecto, ejecuta:
```bash
docker-compose up --build
```
Esto construirá las imágenes de los microservicios y la base de datos, y levantará los contenedores. Los microservicios estarán disponibles en los siguientes puertos:

- `libro-service: http://localhost:8081`
- `autor-service: http://localhost:8082`
- `gateway-service (si lo tienes): http://localhost:8080`

4. Verificar los servicios Puedes probar los endpoints utilizando herramientas como Postman o curl.
## - Configuración de la base de datos
-Si estás usando MongoDB (configuración por defecto):
-El contenedor de MongoDB se ejecutará en el puerto `27017`.
-Si prefieres usar PostgreSQL, deberás cambiar la imagen de la base de datos en el archivo `docker-compose.yml` y configurar las variables de conexión adecuadamente.

---
## CI/CD con GitHub Actions

El archivo de configuración de GitHub Actions está disponible en `.github/workflows/test.yml.`Este archivo automatiza el proceso de compilación y ejecución de pruebas:

1. Cuando haces un push o pull request a la rama main, GitHub Actions ejecutará los siguientes pasos:
- Compilar el código.
- Ejecutar pruebas utilizando Maven o Gradle.

2. Para ver el estado de las ejecuciones, puedes ir a la pestaña "Actions" en tu repositorio de GitHub.
---
## Dockerización 

Cada microservicio tiene un archivo `Dockerfile` para crear una imagen de Docker. Además, el archivo `docker-compose.yml` permite levantar los microservicios y la base de datos en contenedores Docker con el siguiente comando:
```bash
docker-compose up
```
Este comando construirá y ejecutará todos los contenedores definidos en el archivo `docker-compose.yml.`

---
## Archivos importantes

- Dockerfile: Define cómo se deben crear las imágenes de los microservicios.
- docker-compose.yml: Permite levantar todos los microservicios y la base de datos en contenedores.
- .github/workflows/test.yml: Define las acciones de CI/CD para compilar y ejecutar pruebas.

----
## Contribuciones 

Si deseas contribuir a este proyecyo, por favor abre un pull request con tus mejoras o correciones 





















