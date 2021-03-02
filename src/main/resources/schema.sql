create table color
(
    id      bigint auto_increment primary key,
    version int,
    name    varchar(255)
);

create table minion
(
    id             bigint auto_increment primary key,
    version        int,
    name           varchar(255),
    number_of_eyes varchar(255),
    color          bigint,
    constraint FK_minion_color foreign key (color) references color
);

create table string_id_minion
(
    id   varchar(255) primary key,
    name varchar(255)
)