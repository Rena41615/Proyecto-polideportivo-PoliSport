FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /workspace

COPY pom.xml .
COPY iam-service/pom.xml iam-service/pom.xml
COPY iam-service/src iam-service/src

RUN mvn -B -f iam-service/pom.xml -DskipTests package

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

COPY --from=build /workspace/iam-service/target/*.jar app.jar

EXPOSE 8086
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

