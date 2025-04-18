# Usar una imagen base con JDK 11 y Maven
FROM openjdk:17-jdk-slim

ARG JAR_FILE=target/PiuraTours-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app_PiuraTours.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app_PiuraTours.jar"]