FROM openjdk:8-jdk-alpine
EXPOSE 8787
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} aneeque-backend.jar
ENTRYPOINT ["java","-jar","/aneeque-backend.jar"]