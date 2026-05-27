FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY pom.xml .
COPY entrenamiento-service/pom.xml entrenamiento-service/pom.xml
COPY entrenamiento-service/src entrenamiento-service/src
RUN mvn -B -f entrenamiento-service/pom.xml -DskipTests clean package

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /workspace/entrenamiento-service/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
