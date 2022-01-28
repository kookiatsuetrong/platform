# Platform

Spring Boot MVC seems to be a very hard stack for
many reasons. In this sample project, we use
Spring Boot with JPA and Bootstrap to create a
platform for user registration, log in, and log out.

![](screen.png)


Creating database schema
```sql
create database platform default charset 'UTF8';
create user owner identified with mysql_native_password
	by 'P@ssw0rd';
grant all on platform.* to owner;
use platform;

create table members(
	code       integer unique not null auto_increment,
	email      character varying(200) unique not null,
	password   character varying(200) not null,
	first_name character varying(200) not null,
	last_name  character varying(200) not null,
	type       character varying(200) not null default 'unknown',
	team_code  integer
);

-- TODO: add foreign key 

create table teams(
	code     integer unique not null auto_increment,
	name     character varying(200) unique not null,
	base     character varying(200) not null,
	type     character varying(200) not null default 'unknown'
);

insert into teams(name, base) values('Public', 'http://127.0.0.1:8864');

```
