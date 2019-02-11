FROM openjdk:8
MAINTAINER Caspar
LABEL app="employee-service" version="0.0.1" by="caspar"
COPY ./build/libs/employee-service-0.0.1-SNAPSHOT.jar employee-service.jar
CMD java -jar employee-service.jar --spring.profiles.active=${env}
