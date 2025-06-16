FROM openjdk:17-jdk-slim

COPY target/group_finder-0.0.1-SNAPSHOT.jar /usr/app/app.jar
WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "app.jar"]