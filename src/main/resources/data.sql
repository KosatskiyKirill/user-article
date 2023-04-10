SET MODE MYSQL;
create table if not exists users (
                       id bigint primary key,
                       name varchar(255),
                       email varchar(255) unique,
                       password varchar(255),
                       age int);
create table if not exists articles (
                          id bigint primary key,
                          text varchar(255),
                          color varchar(50),
                          user_id bigint,
                              CONSTRAINT artusr FOREIGN KEY (user_id)
                              REFERENCES users(id));

insert into users(id, name, email, password, age) values (0, 'test', 'test@t.com', '$2a$10$fLLGYWFpzmF4WLYfURpcSOaiojZUe/dWyArwKc1OzbSVbWytHinWK', 40);
insert into users(id, name, email, password, age) values (1, 'test1', 'testOne@t.com', '$2a$10$fLLGYWFpzmF4WLYfURpcSOaiojZUe/dWyArwKc1OzbSVbWytHinWK', 30);
insert into users(id, name, email, password, age) values (2, 'test2', 'testTwo@t.com', '$2a$10$fLLGYWFpzmF4WLYfURpcSOaiojZUe/dWyArwKc1OzbSVbWytHinWK', 20);
insert into users(id, name, email, password, age) values (3, 'test3', 'testThree@t.com', '$2a$10$fLLGYWFpzmF4WLYfURpcSOaiojZUe/dWyArwKc1OzbSVbWytHinWK', 18);
insert into users(id, name, email, password, age) values (4, 'test4', 'testFour@t.com', '$2a$10$fLLGYWFpzmF4WLYfURpcSOaiojZUe/dWyArwKc1OzbSVbWytHinWK', 15);
insert into articles(id, text, color, user_id) values (0, 't', 'BLACK', 0);
insert into articles(id, text, color, user_id) values (1, 'b', 'WHITE', 0);
insert into articles(id, text, color, user_id) values (2, 'r', 'BLUE', 0);
insert into articles(id, text, color, user_id) values (3, 'r', 'GREEN', 0);
insert into articles(id, text, color, user_id) values (4, 'e', 'BLACK', 1);
