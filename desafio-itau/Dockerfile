FROM openjdk:12-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} desafio-itau.jar
ENTRYPOINT ["java","-jar","/desafio-itau.jar"]