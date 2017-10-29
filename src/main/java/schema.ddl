
    create table Usuario (
        idUsuario bigint not null auto_increment,
        nome varchar(255),
        sobreNome varchar(255),
        primary key (idUsuario)
    ) ENGINE=InnoDB;

    create table appartment (
        id_appartment bigint not null auto_increment,
        floor varchar(255) not null,
        number varchar(255) not null,
        id_building bigint,
        primary key (id_appartment)
    ) ENGINE=InnoDB;

    create table building (
        id_building bigint not null auto_increment,
        display_name varchar(255) not null,
        street varchar(255) not null,
        primary key (id_building)
    ) ENGINE=InnoDB;

    create table renter (
        id bigint not null auto_increment,
        cpf varchar(255) not null,
        display_name varchar(255) not null,
        email varchar(255) not null,
        phone varchar(255) not null,
        id_appartment bigint,
        primary key (id)
    ) ENGINE=InnoDB;

    alter table appartment 
        add constraint FK_jahxum256m9wi5yt991gl7qp4 
        foreign key (id_building) 
        references building (id_building);

    alter table renter 
        add constraint FK_qlo2jy4ht2w4gbjkdf0asml8q 
        foreign key (id_appartment) 
        references appartment (id_appartment);
