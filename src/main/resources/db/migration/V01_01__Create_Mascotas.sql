create table mascotas(
    id serial primary key,
    nombre varchar(255) not null,
    tipo varchar(255) not null,
    raza varchar(255) not null,
    edad integer not null,
    dueno_id integer not null
);