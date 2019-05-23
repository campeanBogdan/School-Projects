drop database warehousedb;

create database if not exists warehousedb;
use warehousedb;

-- grant all privileges on warehousedb.* to 'root'@'localhost';

create table customer (
	id int primary key auto_increment not null,
	name varchar(50) not null,
	address varchar(100) not null,
	telNo varchar(10) not null);

create table warehousedb.order (
	id int primary key auto_increment not null,
	date varchar(15) not null);

create table product (
	id int primary key auto_increment not null,
	name varchar(50),
	price int);

create table stock (
	id int primary key auto_increment,
	stock int);


insert into product values ('1', 'lemn', '100'), ('2', 'piatra', '15'), ('3', 'lopata', 45);
insert into stock values ('1', '1000'), ('2', '2300'), ('3', '4000');