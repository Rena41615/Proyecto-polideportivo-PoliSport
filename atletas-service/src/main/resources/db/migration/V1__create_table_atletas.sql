CREATE TABLE atletas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    run_atleta INT NOT NULL UNIQUE,
    dvrun_atleta VARCHAR(1) NOT NULL,
    primer_nombre VARCHAR(255) NOT NULL,
    segundo_nombre VARCHAR(255),
    tercer_nombre VARCHAR(255),
    primer_apellido VARCHAR(255) NOT NULL,
    segundo_apellido VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    deporte_principal VARCHAR(255),
    categoria VARCHAR(255),
    historial_deportivo TEXT
);