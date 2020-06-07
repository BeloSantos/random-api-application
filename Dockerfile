FROM openjdk:8
copy target/random-api-application-docker-1.0-SNAPSHOT.jar src/main/random-api-application-docker.jar
CMD java -jar src/main/random-api-application-docker.jar

