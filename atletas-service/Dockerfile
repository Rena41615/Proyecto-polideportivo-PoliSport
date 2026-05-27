FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY pom.xml .
COPY atletas-service/pom.xml atletas-service/pom.xml
COPY atletas-service/src atletas-service/src
RUN mvn -B -f atletas-service/pom.xml -DskipTests clean package

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /workspace/atletas-service/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
