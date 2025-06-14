FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/my-spring-boot-app.jar /app/my-spring-boot-app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/my-spring-boot-app.jar"]