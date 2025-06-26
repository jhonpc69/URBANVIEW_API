FROM eclipse-temurin:17-jdk-alpine

RUN apk --no-cache add curl

VOLUME /tmp
EXPOSE 8092

ARG APP_USER=appuser
ARG TIME_ZONE

RUN apk add --no-cache tzdata
RUN ln -snf /usr/share/zoneinfo/${TIME_ZONE} /etc/localtime && echo ${TIME_ZONE} > /etc/timezone

RUN mkdir -p /opt/appconfig

RUN addgroup -S ${APP_USER} && adduser -S ${APP_USER} -G ${APP_USER}

RUN chown ${APP_USER}.${APP_USER} /opt/appconfig

USER ${APP_USER}

ARG JAR_FILE=*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar ${0} ${@}"]
