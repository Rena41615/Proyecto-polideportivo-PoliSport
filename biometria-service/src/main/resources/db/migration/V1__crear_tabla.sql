CREATE TABLE IF NOT EXISTS analisis_biometrico (
                                                   id                        BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                   atleta_id                 BIGINT         NOT NULL,
                                                   fecha                     VARCHAR(50)    NOT NULL,
    peso                      DOUBLE         NOT NULL,
    altura                    DOUBLE         NOT NULL,
    imc                       DOUBLE         NOT NULL,
    porcentaje_grasa          DOUBLE         NOT NULL,
    masa_muscular             DOUBLE         NOT NULL,
    vo2_max                   DOUBLE         NOT NULL,
    frecuencia_cardiaca_reposo INT           NOT NULL,
    indicador_rendimiento     DOUBLE         NOT NULL
    );