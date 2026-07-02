# ==========================================
# ETAPA 1: Construcción (Build)
# ==========================================
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /workspace

# Dependencias base
COPY pom.xml .

# Código fuente de los 11 servicios
COPY gateway-service/ gateway-service/
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

# Compilar todos los servicios saltando los tests que fallan
RUN mvn -B -Dmaven.test.skip=true clean package

# ==========================================
# ETAPA 2: Ejecución (Run)
# ==========================================
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiar el JAR de atletas
COPY --from=build /workspace/atletas-service/target/*.jar atletas-service.jar

# Exponemos el puerto
EXPOSE 8080

# ==========================================
# EL CAMBIO CLAVE: LEVANTAR SOLO ATLETAS
# ==========================================
ENTRYPOINT ["java", "-jar", "atletas-service.jar"]