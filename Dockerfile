FROM openjdk:17
WORKDIR /opt
ENV PORT 9008
EXPOSE 9008
COPY target/*.jar /opt/registerservice.jar
ENTRYPOINT exec java -jar registerservice.jar