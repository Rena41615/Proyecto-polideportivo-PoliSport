CREATE TABLE competencias (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_competencia VARCHAR(255) NOT NULL,
    lugar_competencia VARCHAR(255) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    modalidad VARCHAR(50) NOT NULL,
    estado_competencia VARCHAR(50) NOT NULL,
    resultados_detalle TEXT
);

CREATE TABLE inscritos_competencia (
    competencia_id BIGINT NOT NULL,
    atleta_run INT NOT NULL,
    CONSTRAINT fk_competencia FOREIGN KEY (competencia_id) REFERENCES competencias(id)
);