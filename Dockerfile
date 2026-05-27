FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /workspace

# Dependencias base
COPY pom.xml .

# Código fuente de los 10 servicios
COPY atletas-service/ atletas-service/
COPY biometria-service/ biometria-service/
COPY competencia-service/ competencia-service/
COPY contratos-service/ contratos-service/
COPY entrenamiento-service/ entrenamiento-service/
COPY iam-service/ iam-service/
COPY inventario-service/ inventario-service/
COPY nutricion-service/ nutricion-service/
COPY salud-service/ salud-service/
COPY staff-service/ staff-service/

# Compilar todos los servicios
RUN mvn -B -DskipTests clean package

# ==========================================
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiar los JARs de cada servicio
COPY --from=build /workspace/atletas-service/target/*.jar atletas-service.jar
COPY --from=build /workspace/biometria-service/target/*.jar biometria-service.jar
COPY --from=build /workspace/competencia-service/target/*.jar competencia-service.jar
COPY --from=build /workspace/contratos-service/target/*.jar contratos-service.jar
COPY --from=build /workspace/entrenamiento-service/target/*.jar entrenamiento-service.jar
COPY --from=build /workspace/iam-service/target/*.jar iam-service.jar
COPY --from=build /workspace/inventario-service/target/*.jar inventario-service.jar
COPY --from=build /workspace/nutricion-service/target/*.jar nutricion-service.jar
COPY --from=build /workspace/salud-service/target/*.jar salud-service.jar
COPY --from=build /workspace/staff-service/target/*.jar staff-service.jar

# Script de inicio
COPY start.sh .
RUN chmod +x start.sh

EXPOSE 8081 8082 8083 8084 8085 8086 8087 8088 8089 8090

ENTRYPOINT ["./start.sh"]
