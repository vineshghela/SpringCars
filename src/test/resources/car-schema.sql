drop table if exists `car` CASCADE;
drop table if exists `garage` CASCADE;
 
create table if not exists garage (id bigint PRIMARY KEY AUTO_INCREMENT, name varchar(255) not null);
create table if not exists car(id bigint PRIMARY KEY AUTO_INCREMENT, colour varchar(255) not null, doors integer not null check (doors<=5 AND doors>=3), make varchar(255) not null, model varchar(255) not null, name varchar(255) not null, garage_id bigint);