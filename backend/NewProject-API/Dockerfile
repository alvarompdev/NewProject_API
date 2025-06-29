# Fase de construcción - Usamos imagen de Java 21 + Maven instalado manualmente
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /usr/src/app

# Instala Maven manualmente
RUN apk add --no-cache maven

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