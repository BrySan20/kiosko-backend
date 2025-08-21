# Etapa de construcción
FROM maven:3.8.6-openjdk-18-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/kiosko-backend-1.0.0.jar app.jar

# Crear usuario no root por seguridad
RUN useradd -m appuser && chown -R appuser:appuser /app
USER appuser

# Exponer puerto
EXPOSE 8080

# Comando de inicio
ENTRYPOINT ["java", "-jar", "app.jar"]