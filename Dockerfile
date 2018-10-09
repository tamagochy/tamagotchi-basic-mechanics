FROM openjdk:8-jre-alpine
LABEL maintainer="makar1031@yandex.ru"
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8085
CMD ["java", "-jar", "app.jar"]
