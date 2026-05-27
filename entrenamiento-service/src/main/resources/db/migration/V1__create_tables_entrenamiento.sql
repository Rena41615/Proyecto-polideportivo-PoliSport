CREATE TABLE entrenamientos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    run_entrenador INT NOT NULL,
    dvrun_entrenador VARCHAR(1) NOT NULL,
    fecha_sesion DATE NOT NULL,
    tipo_entrenamiento VARCHAR(50) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    duracion_minutos INT NOT NULL
);

CREATE TABLE asistencia_entrenamiento (
    entrenamiento_id BIGINT NOT NULL,
    atleta_run INT NOT NULL,
    CONSTRAINT fk_entrenamiento FOREIGN KEY (entrenamiento_id) REFERENCES entrenamientos(id)
);