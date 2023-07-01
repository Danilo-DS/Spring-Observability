FROM openjdk:17

WORKDIR /usr/src/app

COPY ./docker/app/*.jar .

COPY ./src/main/resources/logback/*.xml ./logback/logback-spring.xml

CMD ["java", "-jar", "spring-observability-app.jar"]