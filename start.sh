#!/bin/sh
set -e

echo "=========================================="
echo "  Iniciando PoliSport - 10 microservicios"
echo "=========================================="

# Iniciar cada microservicio en un puerto distinto
java $JAVA_OPTS -jar /app/atletas-service.jar       --server.port=8081 &
echo "[OK] atletas-service   → puerto 8081"

java $JAVA_OPTS -jar /app/biometria-service.jar      --server.port=8082 &
echo "[OK] biometria-service  → puerto 8082"

java $JAVA_OPTS -jar /app/competencia-service.jar    --server.port=8083 &
echo "[OK] competencia-service → puerto 8083"

java $JAVA_OPTS -jar /app/contratos-service.jar      --server.port=8084 &
echo "[OK] contratos-service  → puerto 8084"

java $JAVA_OPTS -jar /app/entrenamiento-service.jar  --server.port=8085 &
echo "[OK] entrenamiento-service → puerto 8085"

java $JAVA_OPTS -jar /app/inventario-service.jar     --server.port=8086 &
echo "[OK] inventario-service → puerto 8086"

java $JAVA_OPTS -jar /app/salud-service.jar          --server.port=8087 &
echo "[OK] salud-service      → puerto 8087"

java $JAVA_OPTS -jar /app/iam-service.jar            --server.port=8088 &
echo "[OK] iam-service        → puerto 8088"

java $JAVA_OPTS -jar /app/nutricion-service.jar      --server.port=8089 &
echo "[OK] nutricion-service  → puerto 8089"

java $JAVA_OPTS -jar /app/staff-service.jar          --server.port=8090 &
echo "[OK] staff-service      → puerto 8090"

echo "=========================================="
echo "  Todos los servicios iniciados"
echo "=========================================="

# Esperar a que cualquier proceso hijo termine
wait
