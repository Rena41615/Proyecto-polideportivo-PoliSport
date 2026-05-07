CREATE TABLE IF NOT EXISTS `pautas_alimentarias` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `plan_id` BIGINT NOT NULL,
    `categoria` VARCHAR(30) NOT NULL,
    `dia_semana` VARCHAR(10) NOT NULL,
    `horario` TIME,
    `descripcion` VARCHAR(500),
    PRIMARY KEY (`id`),
    KEY `fk_pauta_plan` (`plan_id`),
    CONSTRAINT `fk_pauta_plan` FOREIGN KEY (`plan_id`)
        REFERENCES `planes_nutricionales` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB;
