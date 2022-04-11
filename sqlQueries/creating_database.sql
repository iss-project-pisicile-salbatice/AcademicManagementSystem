create database amsDB;
use amsDB;

create table Users (
        u_id int not null identity(1,1),
        about varchar(255),
        category int not null,
        full_name varchar(255),
        password varchar(255),
        user_name varchar(255),
        primary key (u_id)
)

drop table Users;

drop database amsDB;