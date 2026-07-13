-- V3__agregar_columnas_faltantes.sql
-- Las entidades JPA Instalacion e Inventario mapean las columnas
-- "descripcion" y "ubicacion", pero la migracion V1 no las creaba.
-- Con spring.jpa.hibernate.ddl-auto=validate, el arranque de la
-- aplicacion fallaba porque el esquema real no coincidia con el
-- mapeo de las entidades.

ALTER TABLE instalacion
    ADD COLUMN descripcion VARCHAR(500) NULL,
    ADD COLUMN ubicacion   VARCHAR(255) NULL;

ALTER TABLE inventario
    ADD COLUMN descripcion VARCHAR(500) NULL,
    ADD COLUMN ubicacion   VARCHAR(255) NULL;
