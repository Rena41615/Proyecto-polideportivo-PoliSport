CREATE TABLE contratos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    run_empleado INT NOT NULL,
    dvrun_empleado VARCHAR(1) NOT NULL,
    cargo VARCHAR(255) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_termino DATE,
    salario_mensual INT NOT NULL,
    tipo_contrato VARCHAR(50) NOT NULL,
    estado VARCHAR(50) NOT NULL
);