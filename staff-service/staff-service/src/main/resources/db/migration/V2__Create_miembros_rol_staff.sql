CREATE TABLE miembros_rol_staff (
    id BIGINT NOT NULL AUTO_INCREMENT,
    staff_id BIGINT NOT NULL,
    rol VARCHAR(30) NOT NULL,
    asignado_desde SYSDATE NOT NULL DEFAULT (CURDATE()),
    PRIMARY KEY id,
    KEY fk_staff (staff_id),
    CONSTRAINT fk_mrs_staff FOREIGN KEY (staff_id)
        REFERENCES miembros_staff (id) ON DELETE CASCADE
) ENGINE=InnoDB;

INSERT INTO `miembros_rol_staff` (staff_id, rol, asignado_desde) VALUES
(1, 'ENTRENADOR'),
(2, 'NUTRICIONISTA'),
(3, 'TECNICO');
