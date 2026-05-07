CREATE TABLE IF NOT EXISTS `miembros_permisos_staff` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `staff_id` BIGINT NOT NULL,
    `permiso` VARCHAR(120) NOT NULL,
    `descripcion` VARCHAR(300),
    `otorgado_desde` DATE NOT NULL DEFAULT CURRENT_DATE,
    PRIMARY KEY (`id`),
    KEY `fk_staff` (`staff_id`),
    CONSTRAINT `fk_mps_staff` FOREIGN KEY (`staff_id`)
        REFERENCES `miembros_staff` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB;