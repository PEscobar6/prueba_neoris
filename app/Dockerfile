FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/admintest-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080

# Agregamos la espera a la base de datos antes de iniciar la aplicación
CMD ["java", "-jar", "app.jar"]