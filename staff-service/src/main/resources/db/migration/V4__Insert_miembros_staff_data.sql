-- V4__Insert_miembros_staff_data.sql
-- Insertar mas datos de miembros de staff

INSERT INTO `miembros_staff` (`nombre`, `apellido`, `documento`, `telefono`, `email`, `fecha_ingreso`, `activo`) VALUES
('Carlos', 'Lopez', '1555666777', '+573301111111', 'carlos.lopez@polisport.com', CURDATE(), true),
('Maria', 'Sanchez', '1888999000', '+573402222222', 'maria.sanchez@polisport.com', CURDATE(), true),
('Juan', 'Fernandez', '1111222333', '+573503333333', 'juan.fernandez@polisport.com', CURDATE(), true),
('Patricia', 'Diaz', '1444555666', '+573604444444', 'patricia.diaz@polisport.com', CURDATE(), true),
('Roberto', 'Morales', '1777888999', '+573705555555', 'roberto.morales@polisport.com', CURDATE(), true),
('Elena', 'Vargas', '2000111222', '+573806666666', 'elena.vargas@polisport.com', CURDATE(), true),
('Fernando', 'Castro', '2333444555', '+573907777777', 'fernando.castro@polisport.com', CURDATE(), true);

