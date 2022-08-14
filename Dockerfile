FROM openjdk:11
ENV TZ=Europe/Lisbon
ENV BOT_TOKEN=$BOT_TOKEN
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
