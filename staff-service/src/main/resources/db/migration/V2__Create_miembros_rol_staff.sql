CREATE TABLE IF NOT EXISTS `miembros_rol_staff` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `staff_id` BIGINT NOT NULL,
    `rol` VARCHAR(30) NOT NULL,
    `asignado_desde` DATE NOT NULL DEFAULT (CURRENT_DATE),
    PRIMARY KEY (`id`),
    KEY `fk_staff` (`staff_id`),
    CONSTRAINT `fk_mrs_staff` FOREIGN KEY (`staff_id`)
        REFERENCES `miembros_staff` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB;