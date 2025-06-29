# Fase de construcción - Sin dependencias de imágenes problemáticas
FROM eclipse-temurin:21-jdk-alpine AS builder

# Instalar Maven manualmente
RUN apk add --no-cache maven

WORKDIR /usr/src/app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests -Dfile.encoding=UTF-8

# Fase de ejecución
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /usr/src/app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-jar", "app.jar"]