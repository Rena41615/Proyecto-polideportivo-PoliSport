CREATE TABLE miembros_staff (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(80) NOT NULL,
    apellido VARCHAR(80) NOT NULL,
    documento VARCHAR(20) NOT NULL UNIQUE,
    telefono VARCHAR(20),
    email VARCHAR(120),
    fecha_ingreso SYSDATE NOT NULL DEFAULT (CURDATE()),
    activo BOOLEAN NOT NULL DEFAULT true,
    PRIMARY KEY (id),
    INDEX idx_documento (documento),
    INDEX idx_activo (activo)
)

INSERT INTO `miembros_staff` (`nombre`, `apellido`, `documento`, `telefono`, `email`, `fecha_ingreso`, `activo`) VALUES
('Pedro', 'Martinez', '1234567890', '+573001234567', 'pedro.martinez@polisport.com', true),
('Ana', 'García', '0987654321', '+573109876543', 'ana.garcia@polisport.com', true),
('Luis', 'Rodríguez', '1122334455', '+573205555555', 'luis.rodriguez@polisport.com', true);
