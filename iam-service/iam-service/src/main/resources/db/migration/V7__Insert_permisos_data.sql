-- V7__Insert_permisos_data.sql
-- Insertar más datos de permisos para IAM service

INSERT INTO `permisos` (`nombre`, `descripcion`) VALUES
('EDITAR_USUARIO', 'Permiso para editar información de usuario'),
('ELIMINAR_USUARIO', 'Permiso para eliminar usuario del sistema'),
('ASIGNAR_ROLES', 'Permiso para asignar roles a usuarios'),
('CREAR_SUPLEMENTACION', 'Permiso para crear suplementaciones'),
('EDITAR_SUPLEMENTACION', 'Permiso para editar suplementaciones'),
('VER_ENTRENAMIENTOS', 'Permiso para ver planes de entrenamiento'),
('EDITAR_ENTRENAMIENTO', 'Permiso para editar planes de entrenamiento'),
('VER_BIOMETRIA', 'Permiso para ver datos biométricos'),
('EDITAR_BIOMETRIA', 'Permiso para editar datos biométricos'),
('GESTIONAR_ROLES', 'Permiso para gestionar roles del sistema'),
('VER_AUDITORIA', 'Permiso para ver logs de auditoría'),
('EXPORTAR_DATOS', 'Permiso para exportar datos del sistema');

