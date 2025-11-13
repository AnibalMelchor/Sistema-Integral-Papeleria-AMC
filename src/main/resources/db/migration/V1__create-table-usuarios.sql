create table usuario(
                         id bigint not null auto_increment,
                         nombre varchar(100) not null,
                         email varchar(50),
                         contrasena varchar(255) not null,
                         rol varchar(20),
                         activo tinyint not null,
                         primary key(id)
);