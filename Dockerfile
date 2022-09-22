FROM openjdk:11-jre

COPY chaos-mesh-demo.jar /chaos-mesh-demo.jar

CMD [ "java", "-jar", "/chaos-mesh-demo.jar" ]