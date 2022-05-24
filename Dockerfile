FROM openjdk:8-jdk-alpine
EXPOSE 8000
ADD target/authorization-0.0.1-SNAPSHOT.jar authorization.jar
ENTRYPOINT ["java","-jar", "/authorization.jar" ]