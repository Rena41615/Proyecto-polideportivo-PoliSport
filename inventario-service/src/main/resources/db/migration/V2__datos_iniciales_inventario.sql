-- V2__datos_iniciales_inventario.sql
INSERT INTO instalacion (nombre, tipo, capacidad, disponible, estado) VALUES
                                                                          ('Gimnasio Techado', 'Gimnasio', 50, 1, 'Operativo'),
                                                                          ('Cancha de Fútbol 1', 'Cancha', 100, 1, 'Operativo'),
                                                                          ('Piscina Olímpica', 'Piscina', 30, 0, 'En mantención');

INSERT INTO inventario (nombre, cantidad, estado, instalacion_id) VALUES
                                                                      ('Balón de fútbol', 10, 'Disponible', 2),
                                                                      ('Colchoneta', 20, 'Disponible', 1),
                                                                      ('Pesa 10kg', 15, 'Disponible', 1),
                                                                      ('Tabla de natación', 8, 'En revisión', 3);