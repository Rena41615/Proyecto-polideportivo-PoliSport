CREATE TABLE IF NOT EXISTS gestion_medica (
                                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                              atleta_id BIGINT,
                                              tipo_lesion VARCHAR(255),
                                              descripcion VARCHAR(255),
                                              fecha_lesion VARCHAR(50),
                                              fecha_retorno VARCHAR(50),
                                              estado_retorno VARCHAR(255),
                                              medico_id BIGINT,
                                              tratamiento VARCHAR(255)
);

INSERT INTO gestion_medica (atleta_id, tipo_lesion, descripcion, fecha_lesion, fecha_retorno, estado_retorno, medico_id, tratamiento)
VALUES
    (1, 'Esguince', 'Esguince tobillo derecho grado 2', '2026-04-10', '2026-05-10', 'En recuperacion', 1, 'Reposo y fisioterapia'),
    (2, 'Contusion', 'Contusion rodilla izquierda', '2026-04-20', '2026-05-05', 'Apto', 1, 'Antiinflamatorios');