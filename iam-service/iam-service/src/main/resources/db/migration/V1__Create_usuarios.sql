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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `usuarios` (`email`, `password_hash`, `nombre`, `apellido`, `activo`, `fecha_registro`) VALUES
('admin@polisport.com', '$2a$10$admin_hash_example', 'Administrador', 'General', true, NOW()),
('nutricionista@polisport.com', '$2a$10$nutricionista_hash', 'Carlos', 'Nutricionista', true, NOW()),
('entrenador@polisport.com', '$2a$10$entrenador_hash', 'Juan', 'Entrenador', true, NOW());
