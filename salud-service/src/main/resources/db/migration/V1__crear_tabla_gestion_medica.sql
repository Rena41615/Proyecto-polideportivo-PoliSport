CREATE TABLE IF NOT EXISTS gestion_medica (
                                              id             BIGINT AUTO_INCREMENT PRIMARY KEY,
                                              atleta_id      BIGINT       NOT NULL,
                                              tipo_lesion    VARCHAR(255) NOT NULL,
    descripcion    VARCHAR(255) NOT NULL,
    fecha_lesion   VARCHAR(50)  NOT NULL,
    fecha_retorno  VARCHAR(50),
    estado_retorno VARCHAR(255) NOT NULL,
    medico_id      BIGINT       NOT NULL,
    tratamiento    VARCHAR(255) NOT NULL
    );