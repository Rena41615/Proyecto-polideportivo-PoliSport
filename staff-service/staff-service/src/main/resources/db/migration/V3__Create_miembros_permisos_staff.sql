-- V3__Create_miembros_permisos_staff.sql
-- Crear tabla de permisos del staff

CREATE TABLE IF NOT EXISTS `miembros_permisos_staff` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `staff_id` BIGINT NOT NULL,
    `permiso` VARCHAR(120) NOT NULL,
    `descripcion` VARCHAR(300),
    `otorgado_desde` DATE NOT NULL DEFAULT (CURDATE()),
    PRIMARY KEY (`id`),
    KEY `fk_staff` (`staff_id`),
    CONSTRAINT `fk_mps_staff` FOREIGN KEY (`staff_id`)
        REFERENCES `miembros_staff` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `miembros_permisos_staff` (`staff_id`, `permiso`, `descripcion`) VALUES
(1, 'CREAR_ENTRENAMIENTOS', 'Permiso para crear planes de entrenamiento'),
(1, 'VER_ATLETAS', 'Permiso para visualizar información de atletas'),
(2, 'CREAR_PLAN_NUTRICIONAL', 'Permiso para crear planes nutricionales'),
(3, 'VER_REPORTES', 'Permiso para ver reportes técnicos');
