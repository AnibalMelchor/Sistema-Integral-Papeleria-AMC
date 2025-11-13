CREATE TABLE detalle_compra (
                          id BIGINT NOT NULL AUTO_INCREMENT ,
                          compra_id BIGINT NOT NULL,
                          producto_id BIGINT NOT NULL,
                          cantidad INT NOT NULL,
                          precio_unitario DECIMAL(10,2) NOT NULL,
                          primary key(id),
                          CONSTRAINT fk_detalle_Compra_compra_id FOREIGN KEY (compra_id) REFERENCES compra(id),
                          CONSTRAINT fk_detalle_Compra_producto_id FOREIGN KEY (producto_id) REFERENCES producto(id)
);