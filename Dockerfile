FROM java:8-jdk-alpine
COPY target/group_finder-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'group_finder-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","group_finder-0.0.1-SNAPSHOT.jar"]