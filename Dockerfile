FROM maven:3.8.1-openjdk as build

COPY src /home/app/src
COPY pom.xml /home/app

RUN mvn -f /home/app/pom.xml clean package

FROM adoptopenjdk:14-jre

ENV POSTGRESQL_URL="jdbc:postgresql://postgresql-server/app-transaction" POSTGRESQL_USERNAME="app-transaction" POSTGRESQL_PASSWORD="app-transaction"

RUN addgroup appuser && adduser --ingroup appuser appuser \
    && mkdir /app \
    && chown appuser.appuser /app

USER appuser
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

COPY --from=build /home/app/target/*.jar /app/app-transaction.jar

EXPOSE 8080
CMD ["java","-jar","/app/app-transaction.jar"]