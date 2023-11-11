# Usa una imagen base con Java 17
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo en la aplicación
WORKDIR /app

# Copia el archivo JAR de la aplicación al contenedor
COPY target/EjercicioPractico-0.0.1-SNAPSHOT.jar /app/app.jar

# Expone el puerto en el que la aplicación se ejecutará (ajústalo según sea necesario)
EXPOSE 8080

# Comando para ejecutar la aplicación al iniciar el contenedor
CMD ["java", "-jar", "app.jar"]