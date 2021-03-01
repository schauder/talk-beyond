create table color (
    id bigint auto_increment primary key,
    version int,
    name varchar(255)
);

create table minion (
    id bigint auto_increment primary key,
    version int,
    name varchar(255),
    number_of_eyes varchar (255) not null,
    color bigint,
    constraint FK_minion_color foreign key (color) references color
);
