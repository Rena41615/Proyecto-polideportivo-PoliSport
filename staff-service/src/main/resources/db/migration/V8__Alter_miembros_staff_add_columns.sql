ALTER TABLE `miembros_staff`
    ADD COLUMN `run` INT NULL AFTER `id`,
    ADD COLUMN `dv` VARCHAR(1) NULL AFTER `run`,
    ADD COLUMN `puesto` VARCHAR(100) NULL AFTER `email`,
    ADD COLUMN `observaciones` VARCHAR(500) NULL AFTER `activo`;
