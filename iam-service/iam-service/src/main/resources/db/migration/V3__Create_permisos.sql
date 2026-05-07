-- V3__Create_permisos.sql
-- Crear tabla de permisos

CREATE TABLE IF NOT EXISTS `permisos` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(120) NOT NULL UNIQUE,
    `descripcion` VARCHAR(300),
    PRIMARY KEY (`id`),
    INDEX `idx_nombre` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `permisos` (`nombre`, `descripcion`) VALUES
('CREAR_PLAN_NUTRICIONAL', 'Permiso para crear planes nutricionales'),
('EDITAR_PLAN_NUTRICIONAL', 'Permiso para editar planes nutricionales'),
('VER_ATLETAS', 'Permiso para ver lista de atletas'),
('EDITAR_ATLETA', 'Permiso para editar información de atleta'),
('VER_REPORTES', 'Permiso para ver reportes del sistema');
