-- V4__Create_roles_usuarios.sql
-- Crear tabla de relación roles_usuarios

CREATE TABLE IF NOT EXISTS `roles_usuarios` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `usuario_id` BIGINT NOT NULL,
    `rol_id` BIGINT NOT NULL,
    `fecha_asignacion` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `fk_usuario` (`usuario_id`),
    KEY `fk_rol` (`rol_id`),
    CONSTRAINT `fk_ru_usuario` FOREIGN KEY (`usuario_id`)
        REFERENCES `usuarios` (`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_ru_rol` FOREIGN KEY (`rol_id`)
        REFERENCES `roles` (`id`) ON DELETE CASCADE,
    UNIQUE KEY `uk_usuario_rol` (`usuario_id`, `rol_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `roles_usuarios` (`usuario_id`, `rol_id`) VALUES
(1, 1),
(2, 2),
(3, 3);
