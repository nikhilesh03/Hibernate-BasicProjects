create table users (
id serial primary key,
name varchar(100),
email varchar(25) unique,
address varchar(100),
created_at timestamp default current_timestamp
);

create table blog(
id serial primary key,
title varchar(100),
content text,
user_id int REFERENCES users(id),
created_at timestamp default current_timestamp,
updated_at timestamp
);