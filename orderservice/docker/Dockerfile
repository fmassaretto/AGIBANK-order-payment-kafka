FROM gradle:8.13-jdk21-alpine AS temp_build_image

ENV APP_HOME=/app
WORKDIR $APP_HOME
COPY build.gradle.kts settings.gradle.kts $APP_HOME

COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle . /home/gradle/src

RUN chown -R gradle /home/gradle/src

RUN ./gradlew build || return 0
COPY . .
RUN ./gradlew clean build


FROM openjdk:21-slim
ENV ARTIFACT_NAME=orderservice-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/app

WORKDIR $APP_HOME
COPY --from=temp_build_image $APP_HOME/orderservice/build/libs/$ARTIFACT_NAME /app

EXPOSE 8081
ENTRYPOINT exec java -jar ${ARTIFACT_NAME}