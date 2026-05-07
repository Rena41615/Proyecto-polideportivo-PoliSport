-- V2__Create_roles.sql
-- Crear tabla de roles

CREATE TABLE IF NOT EXISTS `roles` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(80) NOT NULL UNIQUE,
    `descripcion` VARCHAR(200),
    PRIMARY KEY (`id`),
    INDEX `idx_nombre` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `roles` (`nombre`, `descripcion`) VALUES
('ADMIN', 'Administrador del sistema'),
('NUTRICIONISTA', 'Profesional de nutrición'),
('ENTRENADOR', 'Entrenador deportivo'),
('MEDICO', 'Personal médico'),
('USUARIO', 'Usuario estándar');

