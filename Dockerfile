FROM openjdk:17-jdk-alpine
MAINTAINER KH
COPY target/mobilevalidation-0.0.1-SNAPSHOT.jar  mobilevalidation-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/mobilevalidation-0.0.1-SNAPSHOT.jar"]