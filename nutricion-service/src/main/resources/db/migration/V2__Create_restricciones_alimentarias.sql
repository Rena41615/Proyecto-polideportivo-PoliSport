CREATE TABLE IF NOT EXISTS `restricciones_alimentarias` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `plan_id` BIGINT NOT NULL,
    `tipo` VARCHAR(30) NOT NULL,
    `descripcion` VARCHAR(500),
    PRIMARY KEY (`id`),
    KEY `fk_restriccion_plan` (`plan_id`),
    CONSTRAINT `fk_restriccion_plan` FOREIGN KEY (`plan_id`)
        REFERENCES `planes_nutricionales` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB;

