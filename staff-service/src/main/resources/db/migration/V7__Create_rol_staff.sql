CREATE TABLE IF NOT EXISTS `rol_staff` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(80) NOT NULL UNIQUE,
    `descripcion` VARCHAR(200),
    PRIMARY KEY (`id`),
    INDEX `idx_nombre_rol` (`nombre`)
) ENGINE=InnoDB;
