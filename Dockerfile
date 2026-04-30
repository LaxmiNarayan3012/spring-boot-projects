# Use Java 17 image
FROM eclipse-temurin:17-jdk-alpine

# Add jar file to container
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Run the jar
ENTRYPOINT ["java","-jar","/app.jar","--spring.profiles.active=docker"]