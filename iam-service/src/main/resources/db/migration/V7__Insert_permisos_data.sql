-- Forzamos los IDs del 1 al 12 para que coincidan con la V9
INSERT INTO `permisos` (`id`, `nombre`, `descripcion`) VALUES
                (1, 'EDITAR_USUARIO', 'Permiso para editar informacion de usuario'),
                (2, 'ELIMINAR_USUARIO', 'Permiso para eliminar usuario del sistema'),
                (3, 'ASIGNAR_ROLES', 'Permiso para asignar roles a usuarios'),
                (4, 'CREAR_SUPLEMENTACION', 'Permiso para crear suplementaciones'),
                (5, 'EDITAR_SUPLEMENTACION', 'Permiso para editar suplementaciones'),
                (6, 'VER_ENTRENAMIENTOS', 'Permiso para ver planes de entrenamiento'),
                (7, 'EDITAR_ENTRENAMIENTO', 'Permiso para editar planes de entrenamiento'),
                (8, 'VER_BIOMETRIA', 'Permiso para ver datos biometricos'),
                (9, 'EDITAR_BIOMETRIA', 'Permiso para editar datos biometricos'),
                (10, 'GESTIONAR_ROLES', 'Permiso para gestionar roles del sistema'),
                (11, 'VER_AUDITORIA', 'Permiso para ver logs de auditoria'),
                (12, 'EXPORTAR_DATOS', 'Permiso para exportar datos del sistema');