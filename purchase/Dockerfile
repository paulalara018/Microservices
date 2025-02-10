# Usa una imagen base de Java
FROM openjdk:21-jdk-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR generado por Spring Boot en la imagen Docker
COPY purchase/target/orden-0.0.1-SNAPSHOT.jar purchase-service.jar

# Expone el puerto 8080
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/purchase-service.jar"]