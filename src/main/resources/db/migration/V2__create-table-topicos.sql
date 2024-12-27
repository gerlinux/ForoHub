create table topicos(
    id bigint not null auto_increment,
    usuario_id bigint not null,
    titulo varchar(100) not null unique,
    mensaje varchar(300) not null unique,
    fecha datetime not null,
    curso varchar(40) not null,
    status tinyint,

    primary key(id),
    constraint fk_topicos_usuario_id foreign key(usuario_id) references usuarios(id)
);