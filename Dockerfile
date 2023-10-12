FROM openjdk:17-jdk-alpine

COPY build/libs/jaray-0.0.1-SNAPSHOT.jar jaray.jar

ENTRYPOINT ["java", "-jar", "jaray.jar"]