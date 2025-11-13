CREATE TABLE compra (
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          fecha_compra date not null ,
                          total_compra DECIMAL(10,2) NOT NULL,
                          primary key(id)
);