CREATE TABLE IF NOT EXISTS `suplementaciones` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `plan_id` BIGINT NOT NULL,
    `tipo` VARCHAR(30) NOT NULL,
    `nombre` VARCHAR(100) NOT NULL,
    `dosis` DECIMAL(10,2),
    `unidad` VARCHAR(20),
    `horario` TIME,
    `observaciones` VARCHAR(300),
    PRIMARY KEY (`id`),
    KEY `fk_suplementacion_plan` (`plan_id`),
    CONSTRAINT `fk_suplementacion_plan` FOREIGN KEY (`plan_id`)
        REFERENCES `planes_nutricionales` (`id`) ON DELETE CASCADE
    ) ENGINE=InnoDB;