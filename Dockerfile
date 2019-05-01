FROM openjdk:latest
ADD target/FactoryInRestApi-0.0.1-SNAPSHOT.jar .
EXPOSE 8083
CMD java -jar FactoryInRestApi-0.0.1-SNAPSHOT.jar