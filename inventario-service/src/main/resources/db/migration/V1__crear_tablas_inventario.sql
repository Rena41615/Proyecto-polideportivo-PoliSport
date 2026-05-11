CREATE TABLE instalacion (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             nombre VARCHAR(100) NOT NULL,
                             ubicacion VARCHAR(100) NOT NULL
);

INSERT INTO instalacion (nombre, ubicacion) VALUES ('Gimnasio Techado', 'Sector A');
INSERT INTO instalacion (nombre, ubicacion) VALUES ('Cancha de Fútbol 1', 'Sector B');