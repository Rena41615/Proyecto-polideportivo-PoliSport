CREATE TABLE IF NOT EXISTS `permisos` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(120) NOT NULL UNIQUE,
    `descripcion` VARCHAR(300),
    PRIMARY KEY (`id`),
    INDEX `idx_nombre` (`nombre`)
) ENGINE=InnoDB;