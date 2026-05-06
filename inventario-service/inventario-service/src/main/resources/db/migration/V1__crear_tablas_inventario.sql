CREATE TABLE IF NOT EXISTS instalacion (
                                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           nombre VARCHAR(255),
                                           tipo VARCHAR(255),
                                           capacidad INT NOT NULL,
                                           disponible BIT NOT NULL,
                                           estado VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS inventario (
                                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                          nombre VARCHAR(255),
                                          cantidad INT NOT NULL,
                                          estado VARCHAR(255),
                                          instalacion_id BIGINT
);

INSERT INTO instalacion (nombre, tipo, capacidad, disponible, estado)
VALUES ('Cancha Principal', 'Cancha', 100, 1, 'Bueno'),
       ('Gimnasio A', 'Gimnasio', 30, 1, 'Bueno');

INSERT INTO inventario (nombre, cantidad, estado, instalacion_id)
VALUES ('Balones de futbol', 20, 'Bueno', 1),
       ('Pesas 10kg', 15, 'Bueno', 2);