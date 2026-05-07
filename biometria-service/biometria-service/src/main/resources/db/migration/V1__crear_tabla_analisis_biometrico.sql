CREATE TABLE IF NOT EXISTS analisis_biometrico (
                                                   id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                   atleta_id BIGINT,
                                                   fecha VARCHAR(50),
    peso DOUBLE NOT NULL,
    altura DOUBLE NOT NULL,
    imc DOUBLE NOT NULL,
    porcentaje_grasa DOUBLE NOT NULL,
    masa_muscular DOUBLE NOT NULL,
    vo2_max DOUBLE NOT NULL,
    frecuencia_cardiaca_reposo INT NOT NULL,
    indicador_rendimiento DOUBLE NOT NULL
    );

INSERT INTO analisis_biometrico (atleta_id, fecha, peso, altura, imc, porcentaje_grasa, masa_muscular, vo2_max, frecuencia_cardiaca_reposo, indicador_rendimiento)
VALUES
    (1, '2026-05-01', 75.5, 1.78, 23.8, 15.2, 63.9, 48.5, 62, 87.3),
    (2, '2026-05-01', 68.0, 1.70, 23.5, 18.1, 55.7, 42.0, 70, 75.0);'2026-05-01', 68.0, 1.70, 23.5, 18.1, 55.7, 42.0, 70, 75.0);18.1, 55.7, 42.0, 70, 75.0);