create database Proyecto;
use Proyecto;

/*TABLA DE USUARIO ------------------------------------------------------------------------------*/
create table usuario (
    id_usuario bigint unsigned primary key auto_increment,
    numero_documento bigint unsigned not null,
    nombre varchar(255) not null,
    correo varchar(255) not null,
    contrasena varchar(255) not null,
    direccion varchar(255) not null,
    telefono varchar(20) not null,
    nivel_educativo enum('Bachiller', 'Tecnico', 'Tecnologo', 'Profesional') null,
    referencia_personal varchar(255) null,
    telefono_referencia_personal varchar(20) null,
    eps varchar(255) null,
    tipo_usuario enum('Administrador', 'Cliente', 'Sup_bodega', 'Gerente') not null,
    estado enum('A', 'I') default 'A' null,
    registrado_por bigint unsigned null,
    foreign key (registrado_por) references usuario(id_usuario),
    index (numero_documento)
);

/*TABLA DE PAQUETE ------------------------------------------------------------------------------*/
create table paquete (
    id_paquete tinyint unsigned auto_increment primary key,
    tipo_evento varchar(255) not null,
    nombre varchar(255) not null,
    descripcion varchar(255) not null,
    capacidad enum('20-40', '40-60', '60-80'),
    precio decimal(10, 2) not null
);

/*TABLA DE ALQUILER ------------------------------------------------------------------------------*/
create table alquiler (
    id_alquiler bigint unsigned primary key auto_increment,
    fecha_evento date not null,
    abono decimal(10, 2) not null,
    saldo decimal(10, 2) not null,
	valor_total decimal(10, 2) not null,
    fecha_devolucion date not null,
    id_paquete tinyint unsigned not null,
    foreign key (id_paquete) references paquete(id_paquete),
    id_usuario bigint unsigned not null,
    foreign key (id_usuario) references usuario(id_usuario)
);

/*TABLA DE PRODUCTO ------------------------------------------------------------------------------*/
create table producto (
    id_producto bigint unsigned primary key auto_increment,
    nombre_producto varchar(255) not null,
    existencia tinyint unsigned not null,
    descripcion varchar(255) not null,
    precio_compra decimal(10, 2) not null,
    precio_alquiler decimal(10, 2) not null,
    fecha_entrada date not null,
    fecha_salida date not null,
    imagen longblob not null,
    id_usuario bigint unsigned not null,
    foreign key (id_usuario) references usuario(id_usuario),
    index (nombre_producto)
);

/*TABLA DE DETALLES_CONTIENE ------------------------------------------------------------------------------*/
create table detalles_contiene (
    id_alquiler bigint unsigned not null,
    foreign key (id_alquiler) references alquiler(id_alquiler),
    id_producto bigint unsigned not null,
	foreign key (id_producto) references producto(id_producto),
    cantidad_producto int unsigned not null,
    valor_producto_alquiler decimal(10, 2) not null,
    descripcion varchar(255) not null
);

/*TABLA DE SERVICIO ------------------------------------------------------------------------------*/
create table servicio (
    id_servicio tinyint unsigned auto_increment primary key,
    nombre varchar(255) not null,
    descripcion varchar(255) not null,
    precio decimal(10, 2) not null
);

/*TABLA DE DETALLE_SERVICIO ------------------------------------------------------------------------------*/
create table detalle_servicio (
	id_servicio tinyint unsigned not null,
    foreign key (id_servicio) references servicio(id_servicio),
    id_alquiler bigint unsigned not null,
    foreign key (id_alquiler) references alquiler(id_alquiler),
    cantidad tinyint unsigned not null,
    valor_servicio decimal(10, 2) not null
);

/*TABLA DE DETALLE_PAQUETE ------------------------------------------------------------------------------*/
create table detalle_paquete (
    id_paquete tinyint unsigned not null,
    foreign key (id_paquete) references paquete(id_paquete),
    id_producto bigint unsigned not null,
    foreign key (id_producto) references producto(id_producto),
    cant_producto_paquete tinyint unsigned not null
);


/*CONSULTAS GENERALES ------------------------------------------------------------------------------*/
select*from usuario;
select*from alquiler;
select*from producto;
select*from detalles_contiene;
select*from paquete;
select*from servicio;
select*from detalle_servicio;
select*from detalle_paquete;

describe usuario;

INSERT INTO usuario (
    numero_documento,
    nombre,
    correo,
    contrasena,
    direccion,
    telefono,
    nivel_educativo,
    referencia_personal,
    telefono_referencia_personal,
    eps,
    tipo_usuario,
    estado,
    registrado_por
) VALUES (
    1000000001,                         -- numero_documento
    'Administrador Principal',          -- nombre
    'admin@arron.com',                  -- correo
    'admin123',
    'Sin dirección',                    -- dirección
    '3000000000',                       -- teléfono
    'Profesional',                      -- nivel educativo
    'Maria Perez',                               -- referencia_personal
    '3102145478',                          -- telefono_referencia_personal
    'Compensar',                               -- eps
    'Administrador',                    -- tipo_usuario
    'A',                                -- estado
    1                                -- registrado_por
);

/*Registro supervisor de bodega*/
INSERT INTO usuario (
    numero_documento,
    nombre,
    correo,
    contrasena,
    direccion,
    telefono,
    tipo_usuario,
    estado,
    registrado_por
) VALUES (
    987654321,
    'Juan Perez',
    'bodega@arron.com',
    '4321',
    'Bodega Central',
    '3007654321',
    'Sup_bodega',
    'A',
    1
);

/*Registro Gerente*/
INSERT INTO usuario (
    numero_documento,
    nombre,
    correo,
    contrasena,
    direccion,
    telefono,
    nivel_educativo,
    referencia_personal,
    telefono_referencia_personal,
    eps,
    tipo_usuario,
    estado,
    registrado_por
) VALUES (
    1234567890,                     -- numero_documento
    'Carlos Mendoza',              -- nombre
    'carlos.mendoza@empresa.com',  -- correo
    'contrasena123',               -- contrasena (en práctica debe ir cifrada)
    'Calle 45 # 22-10',            -- direccion
    '3004567890',                  -- telefono
    'Profesional',                 -- nivel_educativo
    'Juan Pérez',                  -- referencia_personal
    '3201234567',                  -- telefono_referencia_personal
    'Sura',                        -- eps
    'Gerente',                     -- tipo_usuario
    'A',                           -- estado
    1                              -- registrado_por
);
