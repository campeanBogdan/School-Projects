use depozit;

-- grant all privileges on *.* to 'user'@'localhost';
-- grant all privileges on depozit.* to 'user'@'localhost';

-- create user ''@'localhost' identified by '';
-- grant all privileges on *.* to ''@'localhost';

create table product (
	id int primary key not null auto_increment,
	name varchar(30) not null,
	price int not null);