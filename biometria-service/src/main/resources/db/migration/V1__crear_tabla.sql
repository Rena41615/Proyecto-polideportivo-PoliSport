-- 1. Crear la tabla principal
CREATE TABLE IF NOT EXISTS analisis_biometrico (
                                                   id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                   usuario_id BIGINT NOT NULL,
                                                   tipo_biometria VARCHAR(50) NOT NULL,
                                                   huella_digital_hash TEXT,
                                                   estado_validacion VARCHAR(20) NOT NULL,
                                                   fecha_analisis TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Insertar datos iniciales (Requisito del examen)
INSERT INTO analisis_biometrico (usuario_id, tipo_biometria, huella_digital_hash, estado_validacion)
VALUES (1, 'HUELLA_DERECHA', 'hash_ejemplo_a1b2c3', 'VALIDADO');

INSERT INTO analisis_biometrico (usuario_id, tipo_biometria, huella_digital_hash, estado_validacion)
VALUES (2, 'FACIAL', 'hash_ejemplo_f4g5h6', 'PENDIENTE');