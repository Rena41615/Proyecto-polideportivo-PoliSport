-- V5__Insert_miembros_rol_staff_data.sql
-- Insertar datos de relación miembros_rol_staff para los 7 miembros creados

INSERT INTO `miembros_rol_staff` (staff_id, rol, asignado_desde) VALUE
    (1, 'ENTRENADOR', CURDATE()),
    (2, 'NUTRICIONISTA', CURDATE()),
    (3, 'TECNICO', CURDATE()),
    (4, 'NUTRICIONISTA', CURDATE()),
    (5, 'ENTRENADOR', CURDATE()),
    (6, 'TECNICO', CURDATE()),
    (7, 'ENTRENADOR', CURDATE());