#!/bin/sh
set -e
java -jar /app/gateway-service.jar --server.port=8080 &
java -jar /app/atletas-service.jar --server.port=8081 &
java -jar /app/biometria-service.jar --server.port=8082 &
java -jar /app/competencia-service.jar --server.port=8083 &
java -jar /app/contratos-service.jar --server.port=8084 &
java -jar /app/entrenamiento-service.jar --server.port=8085 &
java -jar /app/inventario-service.jar --server.port=8086 &
java -jar /app/salud-service.jar --server.port=8087 &
java -jar /app/iam-service.jar --server.port=8088 &
java -jar /app/nutricion-service.jar --server.port=8089 &
java -jar /app/staff-service.jar --server.port=8090 &
wait