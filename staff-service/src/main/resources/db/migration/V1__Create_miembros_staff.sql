CREATE TABLE IF NOT EXISTS `miembros_staff` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(80) NOT NULL,
    `apellido` VARCHAR(80) NOT NULL,
    `documento` VARCHAR(20) NOT NULL UNIQUE,
    `telefono` VARCHAR(20),
    `email` VARCHAR(120),
    `fecha_ingreso` DATE NOT NULL DEFAULT (CURRENT_DATE),
    `activo` BOOLEAN NOT NULL DEFAULT true,
    PRIMARY KEY (`id`),
    INDEX `idx_documento` (`documento`),
    INDEX `idx_activo` (`activo`)
) ENGINE=InnoDB;
