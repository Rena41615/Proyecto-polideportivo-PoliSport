CREATE TABLE IF NOT EXISTS `atletas` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(80) NOT NULL,
    `apellido` VARCHAR(80) NOT NULL,
    `numero_documento` VARCHAR(20) NOT NULL UNIQUE,
    `numero_camiseta` INT,
    `fecha_nacimiento` DATE,
    `activo` BOOLEAN NOT NULL DEFAULT true,
    `fecha_registro` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    INDEX `idx_numero_documento` (`numero_documento`),
    INDEX `idx_activo` (`activo`)
) ENGINE=InnoDB;
