FROM adoptopenjdk:11-jre-hotspot as builder
EXPOSE 8081
ARG JAR_FILE=target/livestream-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
