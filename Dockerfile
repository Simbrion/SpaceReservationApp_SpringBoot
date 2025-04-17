FROM openjdk:21-jdk-oracle
WORKDIR /app
COPY target/SpaceReservationApp_SpringBoot-0.0.1-SNAPSHOT.jar sra.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "sra.jar"]