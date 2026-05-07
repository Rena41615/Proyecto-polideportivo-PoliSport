-- V6__Insert_miembros_permisos_staff_data.sql
-- Insertar más datos de permisos del staff

INSERT INTO `miembros_permisos_staff` (`staff_id`, `permiso`, `descripcion`) VALUES
(1, 'EDITAR_ENTRENAMIENTOS', 'Permiso para editar planes de entrenamiento'),
(2, 'EDITAR_PLAN_NUTRICIONAL', 'Permiso para editar planes nutricionales'),
(4, 'CREAR_PLAN_NUTRICIONAL', 'Permiso para crear planes nutricionales'),
(5, 'CREAR_ENTRENAMIENTOS', 'Permiso para crear planes de entrenamiento'),
(3, 'EDITAR_REPORTES', 'Permiso para generar y editar reportes'),
(5, 'VER_ATLETAS', 'Permiso para visualizar información de atletas'),
(6, 'VER_BIOMETRIA', 'Permiso para ver datos biométricos de atletas'),
(7, 'CREAR_ENTRENAMIENTOS', 'Permiso para crear planes de entrenamiento'),
(8, 'ASISTENCIA_ENTRENAMIENTOS', 'Permiso para asistencia en entrenamientos'),
(9, 'MANTENIMIENTO_EQUIPO', 'Permiso para gestionar equipo técnico'),
(10, 'VER_PLAN_NUTRICIONAL', 'Permiso para ver planes nutricionales');

