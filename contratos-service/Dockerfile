FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY pom.xml .
COPY contratos-service/pom.xml contratos-service/pom.xml
COPY contratos-service/src contratos-service/src
RUN mvn -B -f contratos-service/pom.xml -DskipTests clean package

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /workspace/contratos-service/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
