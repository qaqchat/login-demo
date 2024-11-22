FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/*.jar /app/qaqchat-app.jar

ENTRYPOINT ["java", "-jar", "/app/qaqchat-app.jar"]

EXPOSE 8080
