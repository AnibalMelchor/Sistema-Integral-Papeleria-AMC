alter table compra
    add cancelada tinyint,
    add column observaciones varchar(255);

update compra set cancelada = 0;