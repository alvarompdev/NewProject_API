# Dockerfile definitivo corregido
FROM eclipse-temurin:21-jdk-alpine AS builder

RUN apk add --no-cache maven

WORKDIR /usr/src/app

# Copia el POM desde la ruta relativa correcta
COPY ./backend/NewProject-API/pom.xml .
RUN mvn dependency:go-offline

# Copia el código fuente desde la ruta relativa
COPY ./backend/NewProject-API/src ./src

RUN mvn clean package -DskipTests -Dfile.encoding=UTF-8

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /usr/src/app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-jar", "app.jar"]