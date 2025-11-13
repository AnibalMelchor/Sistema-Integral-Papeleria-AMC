CREATE TABLE producto (
                          id BIGINT NOT NULL AUTO_INCREMENT ,
                          nombre VARCHAR(100) NOT NULL,
                          descripcion VARCHAR(255),
                          categoria_id BIGINT NOT NULL ,
                          precio_costo DECIMAL(10,2) NOT NULL,
                          precio_venta DECIMAL(10,2) NOT NULL,
                          inventario INT NOT NULL,
                          primary key(id),
                          CONSTRAINT fk_producto_categoria_id FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);