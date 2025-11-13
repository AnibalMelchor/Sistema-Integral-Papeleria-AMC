alter table venta
    add cancelada tinyint,
    add column observaciones varchar(255);

update venta set cancelada = 0;