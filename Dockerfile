FROM openjdk:11
EXPOSE 8081
ADD target/user-rest-api.jar user-rest-api.jar
ENTRYPOINT ["java","-jar","/user-rest-api.jar"]