FROM adoptopenjdk:19-jre-hotspot
ARG JAR_FILE=target/api-invoice-uts-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]