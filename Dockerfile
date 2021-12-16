FROM openjdk:oracle

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} aneeque-backend.jar
EXPOSE 8789
ENTRYPOINT ["java","-jar","/aneeque-backend.jar"]