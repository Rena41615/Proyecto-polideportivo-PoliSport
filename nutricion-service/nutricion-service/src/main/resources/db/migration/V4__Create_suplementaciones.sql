CREATE TABLE IF NOT EXISTS `suplementaciones` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `plan_id` BIGINT NOT NULL,
    `tipo` VARCHAR(30) NOT NULL,
    `nombre` VARCHAR(100) NOT NULL,
    `dosis` DECIMAL(5,2),
    `unidad` VARCHAR(20),
    `horario` TIME,
    `observaciones` VARCHAR(300),
    PRIMARY KEY (`id`),
    KEY `fk_suplemento_plan` (`plan_id`),
    CONSTRAINT `fk_suplemento_plan` FOREIGN KEY (`plan_id`)
        REFERENCES `planes_nutricionales` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB;