create table minion (
    id bigint auto_increment,
    version int,
    name varchar(255),
    number_of_eyes varchar (255) not null,
    color varchar(10)
);