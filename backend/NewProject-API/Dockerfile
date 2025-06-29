# Fase de construcción - Imagen oficial VERIFICADA
FROM maven:3.8.6-amazoncorretto-21-alpine AS builder

WORKDIR /usr/src/app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests -Dfile.encoding=UTF-8

# Fase de ejecución - Imagen oficial VERIFICADA
FROM amazoncorretto:21-alpine
WORKDIR /app
COPY --from=builder /usr/src/app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-jar", "app.jar"]