CREATE TABLE detalle_venta (
                          id BIGINT NOT NULL AUTO_INCREMENT ,
                          venta_id BIGINT NOT NULL,
                          producto_id BIGINT NOT NULL,
                          cantidad INT NOT NULL,
                          precio_unitario DECIMAL(10,2) NOT NULL,
                          primary key(id),
                          CONSTRAINT fk_detalleVenta_venta_id FOREIGN KEY (venta_id) REFERENCES venta(id),
                          CONSTRAINT fk_detalleVenta_producto_id FOREIGN KEY (producto_id) REFERENCES producto(id)
);