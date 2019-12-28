ARG version=11
ENV myName="Hello Konrad"

FROM openjdk:${version}
EXPOSE 8083

ENTRYPOINT ["mvn"]
#uruchomi sie z tym poleceniem

CMD ["clean","install"]
#doda sie do entrypoint, mozna to nadpisywac przy uruchamianiu obrazu

#ADD target/FactoryInRestApi-0.0.1-SNAPSHOT.jar .

#CMD java -jar FactoryInRestApi-0.0.1-SNAPSHOT.jar