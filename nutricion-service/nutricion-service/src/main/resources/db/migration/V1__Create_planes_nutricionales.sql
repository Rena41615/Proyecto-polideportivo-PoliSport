CREATE TABLE planes_nutricionales (
    id BIGINT NOT NULL AUTO_INCREMENT,
    atleta_id BIGINT NOT NULL,
    deporte VARCHAR(100) NOT NULL,
    objetivo VARCHAR(30) NOT NULL,
    fecha_inicio DATE,
    fecha_fin DATE,
    calorias_diarias_gr INT,
    proteina_gr INT,
    carbohidratos_gr INT,
    lipidos_gr INT,
    estado VARCHAR(20) NOT NULL DEFAULT 'BORRADOR',
    notas VARCHAR(500),
    PRIMARY KEY (id),
    INDEX idx_atleta_id (atleta_id),
    INDEX idx_estado (estado)
) ENGINE=InnoDB;
