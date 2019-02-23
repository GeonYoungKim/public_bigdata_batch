FROM openjdk:8
ADD bigdata-0.0.1-SNAPSHOT.jar docker-spring-boot.jar
ENTRYPOINT ["java","-jar","docker-spring-boot.jar","--job=extractDiffDataJob"]