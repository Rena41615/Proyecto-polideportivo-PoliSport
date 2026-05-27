CREATE TABLE IF NOT EXISTS `usuarios` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `email` VARCHAR(120) NOT NULL UNIQUE,
    `password_hash` VARCHAR(255) NOT NULL,
    `nombre` VARCHAR(80) NOT NULL,
    `apellido` VARCHAR(80) NOT NULL,
    `activo` BOOLEAN NOT NULL DEFAULT true,
    `fecha_registro` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    INDEX `idx_email` (`email`),
    INDEX `idx_activo` (`activo`)
) ENGINE=InnoDB;