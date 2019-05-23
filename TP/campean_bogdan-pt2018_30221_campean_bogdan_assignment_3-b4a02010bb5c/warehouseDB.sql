-- create database warehouseDB;
use warehouseDB;













-- drop table stock;
-- drop table product;
-- drop table customer;
-- drop table warehouseDb.order;


select * from product;


create table stock (
	id int auto_increment primary key not null,
    stock int not null);

create table product (
	id int auto_increment primary key not null,
    name varchar(20) not null,
    price int not null);

create table warehousedb.order (
	id int auto_increment primary key not null,
    date varchar(10));

create table customer (
	id int auto_increment primary key not null,
    name varchar(30),
    address varchar(100),
    telNo varchar(11));