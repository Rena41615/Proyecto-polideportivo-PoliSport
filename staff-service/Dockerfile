FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /workspace

COPY pom.xml .
COPY staff-service/pom.xml staff-service/pom.xml
COPY staff-service/src staff-service/src

RUN mvn -B -f staff-service/pom.xml -DskipTests package

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

COPY --from=build /workspace/staff-service/target/*.jar app.jar

EXPOSE 8090
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]

