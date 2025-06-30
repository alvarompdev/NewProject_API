# Usa una imagen con soporte completo de UTF-8
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build

# Configura variables de entorno para UTF-8
ENV LANG C.UTF-8
ENV LC_ALL C.UTF-8

WORKDIR /usr/src/app
COPY ./backend/NewProject-API/pom.xml .
RUN mvn dependency:go-offline

# Copia solo los fuentes necesarios
COPY ./backend/NewProject-API/src ./src

# Ejecuta Maven con configuracion UTF-8 explícita
RUN mvn clean package -DskipTests -Dfile.encoding=UTF-8

# Fase de ejecución
FROM eclipse-temurin:21-jre-alpine
COPY --from=build /usr/src/app/target/*.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-jar", "/app.jar"]