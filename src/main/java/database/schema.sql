DROP DATABASE IF exists almacen;
CREATE DATABASE almacen CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE almacen;

create table productos (
	id int primary key,
	nombre varchar(300) not null,
	descripcion text,
	cantidad int default 0,
	precio decimal(10,2) not null
);

create table productos_fav(
	id int auto_increment primary key,
	id_producto int not null unique,
	foreign key(id_producto) references productos(id) on delete cascade
);

create table empleados (
	id int auto_increment primary key,
	nombre varchar(100) not null,
	puesto varchar(100)
);

create table pedidos(
	id int auto_increment primary key,
	id_producto int not null,
	id_empleado int,
	fecha datetime default current_timestamp,
	foreign key (id_producto) references productos(id) on delete cascade,
	foreign key (id_empleado) references empleados(id) on delete set null
);