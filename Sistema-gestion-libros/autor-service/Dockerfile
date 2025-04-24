# Usa una imagen de Java con JDK 17
FROM eclipse-temurin:17-jdk-alpine

# Crea directorio de la app
WORKDIR /app

# Copia el archivo JAR al contenedor
COPY target/*.jar app.jar

# Expón el puerto (libro: 8081, autor: 8082)
EXPOSE 8082

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]


# Dockerfile para microservicio (libro/autor/gateway)
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
