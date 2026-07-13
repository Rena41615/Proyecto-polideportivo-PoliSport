-- Corrige miembros_rol_staff: la columna `rol` (VARCHAR) se reemplaza por
-- una FK real `rol_id` hacia `rol_staff`(id), tal como mapea la entidad
-- MiembrosRolStaff (@JoinColumn(name = "rol_id")).
-- Entorno de desarrollo: se eliminan los datos de prueba existentes en esta
-- tabla antes de aplicar el cambio de esquema.

DELETE FROM `miembros_rol_staff`;

ALTER TABLE `miembros_rol_staff`
    DROP COLUMN `rol`,
    ADD COLUMN `rol_id` BIGINT NOT NULL AFTER `staff_id`;

ALTER TABLE `miembros_rol_staff`
    ADD CONSTRAINT `fk_mrs_rol` FOREIGN KEY (`rol_id`)
        REFERENCES `rol_staff` (`id`) ON DELETE CASCADE;
