-- The tables created here are reused for multiple examples.
-- Therefore they contain columns that are not used for other examples.

create table person
(
    id   bigint auto_increment primary key,
    name varchar(255)
);

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
    evil_master    bigint,
    description    varchar(4000),
    constraint FK_minion_color foreign key (color) references color,
    constraint FK_minion_person foreign key (evil_master) references person
);

create table string_id_minion
(
    id   varchar(255) primary key,
    name varchar(255)
);


create table toy
(
    minion  bigint not null,
    name    varchar(255)
)
