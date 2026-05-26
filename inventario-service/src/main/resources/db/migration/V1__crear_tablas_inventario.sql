CREATE TABLE instalacion (
                             id         BIGINT AUTO_INCREMENT PRIMARY KEY,
                             nombre     VARCHAR(100) NOT NULL,
                             tipo       VARCHAR(100) NOT NULL,
                             capacidad  INT          NOT NULL,
                             disponible TINYINT(1)   NOT NULL,
                             estado     VARCHAR(50)  NOT NULL
);

CREATE TABLE inventario (
                            id             BIGINT AUTO_INCREMENT PRIMARY KEY,
                            nombre         VARCHAR(100) NOT NULL,
                            cantidad       INT          NOT NULL,
                            estado         VARCHAR(50)  NOT NULL,
                            instalacion_id BIGINT       NOT NULL
);