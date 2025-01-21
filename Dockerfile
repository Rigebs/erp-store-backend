# Etapa de construcción: Usar Eclipse Temurin JDK 23
FROM eclipse-temurin:23-jdk AS build

WORKDIR /app

# Instalar Maven
RUN apt-get update && apt-get install -y maven

# Copiar todos los archivos del proyecto al contenedor
COPY . .

# Ejecutar Maven para construir el proyecto
RUN mvn clean package -DskipTests

# Etapa de ejecución: Usar Eclipse Temurin JRE 23
FROM eclipse-temurin:23-jre

WORKDIR /app

# Copiar el JAR construido desde la etapa de construcción
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto en el que corre la aplicación
EXPOSE 8080

# Definir las variables de entorno
ENV CLOUDINARY_API_KEY=${CLOUDINARY_API_KEY}
ENV CLOUDINARY_API_SECRET=${CLOUDINARY_API_SECRET}
ENV CLOUDINARY_CLOUD_NAME=${CLOUDINARY_CLOUD_NAME}
ENV DATABASE_PASSWORD=${DATABASE_PASSWORD}
ENV DATABASE_URL=${DATABASE_URL}
ENV DATABASE_USERNAME=${DATABASE_USERNAME}
ENV JWT_EXPIRATION=${JWT_EXPIRATION}
ENV JWT_SECRET=${JWT_SECRET}

# Ejecutar el JAR
ENTRYPOINT ["java", "-jar", "app.jar"]