FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /workspace

COPY pom.xml .
COPY nutricion-service/pom.xml nutricion-service/pom.xml
COPY nutricion-service/src nutricion-service/src

RUN mvn -B -f nutricion-service/pom.xml -DskipTests package

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

COPY --from=build /workspace/nutricion-service/target/*.jar app.jar

EXPOSE 8088
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]

