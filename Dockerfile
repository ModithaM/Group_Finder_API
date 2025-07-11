#Runtime
FROM eclipse-temurin:17-jdk
#target
ADD target/group_finder-0.0.1-SNAPSHOT.jar app.jar
#public port
EXPOSE 8080
# Expose the port the app runs on
ENTRYPOINT ["java","-jar","/app.jar"]