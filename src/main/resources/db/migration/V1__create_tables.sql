create table if not exists users (
	user_id serial primary key,
	name varchar(50),
	email varchar(80),
	role char(1)
);