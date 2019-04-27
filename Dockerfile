FROM 11.0.2-jdk-windowsservercore-1809
ADD target/FactoryInRestApi-0.0.1-SNAPSHOT.jar .
EXPOSE 8083
CMD java -jar FactoryInRestApi-0.0.1-SNAPSHOT.jar