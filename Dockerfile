FROM 11.0.3-jdk-stretch
ADD target/FactoryInRestApi-0.0.1-SNAPSHOT.jar .
EXPOSE 8083
CMD java -jar FactoryInRestApi-0.0.1-SNAPSHOT.jar