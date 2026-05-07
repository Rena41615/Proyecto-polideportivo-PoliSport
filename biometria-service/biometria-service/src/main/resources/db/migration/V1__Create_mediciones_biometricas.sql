CREATE TABLE IF NOT EXISTS `mediciones_biometricas` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `atleta_id` BIGINT NOT NULL,
    `peso_kg` DECIMAL(5,2),
    `altura_cm` DECIMAL(5,2),
    `porcentaje_grasa` DECIMAL(5,2),
    `fecha_medicion` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `notas` VARCHAR(500),
    PRIMARY KEY (`id`),
    INDEX `idx_atleta_id` (`atleta_id`),
    INDEX `idx_fecha_medicion` (`fecha_medicion`)
) ENGINE=InnoDB;
