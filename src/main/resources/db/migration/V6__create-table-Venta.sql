CREATE TABLE venta (
                          id BIGINT NOT NULL AUTO_INCREMENT ,
                          fecha_venta DATE NOT NULL,
                          total_venta DECIMAL(10,2) NOT NULL,
                          usuario_id BIGINT NOT NULL,
                          primary key(id),
                          CONSTRAINT fk_venta_usuario_id FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);