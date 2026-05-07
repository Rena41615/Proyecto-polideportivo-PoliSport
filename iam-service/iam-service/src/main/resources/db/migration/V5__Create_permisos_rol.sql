CREATE TABLE permisos_rol (
    id BIGINT NOT NULL AUTO_INCREMENT,
    rol_id BIGINT NOT NULL,
    permiso_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    KEY fk_rol (rol_id),
    KEY fk_permiso (permiso_id),
    CONSTRAINT `fk_pr_rol` FOREIGN KEY (`rol_id`)
        REFERENCES `roles` (`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_pr_permiso` FOREIGN KEY (`permiso_id`)
        REFERENCES `permisos` (`id`) ON DELETE CASCADE,
    UNIQUE KEY `uk_rol_permiso` (`rol_id`, `permiso_id`)
) ENGINE=InnoDB;

INSERT INTO `permisos_rol` (`rol_id`, `permiso_id`) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5),
(2, 1), (2, 2),
(3, 3), (3, 4),
(5, 3);
