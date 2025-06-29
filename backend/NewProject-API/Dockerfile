# Fase de construcción
FROM maven:3.8.7-eclipse-temurin-21-alpine AS builder

# Usa un directorio de trabajo absoluto
WORKDIR /usr/src/app

# Copia solo el POM primero para cachear dependencias
COPY ./pom.xml .
RUN mvn dependency:go-offline

# Copia el código fuente
COPY ./src ./src

# Construye la aplicación
RUN mvn clean package -DskipTests -Dfile.encoding=UTF-8

# Fase de ejecución
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /usr/src/app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-jar", "app.jar"]