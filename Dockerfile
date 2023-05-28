FROM openjdk:17

WORKDIR /app

COPY target/ms_students_sprb.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]