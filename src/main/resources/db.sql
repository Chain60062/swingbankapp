create database if not exists bank;

use bank;

create table
    if not exists account (
        account_number varchar(20) not null primary key,
        balance decimal(9, 2) not null,
        account_limit decimal(9, 2) not null,
        account_type smallint not null, -- tipo da conta(1 para corrente, 2 para poupanca)
        account_holder_id bigint unsigned not null
    );

create table
    if not exists person (
        user_id serial,
        name varchar(200) not null,
        username varchar(200) not null,
        cipher varchar(255) not null,
        address varchar(200) not null,
        cpf varchar(14) not null,
        cellphone varchar(20) not null,
        user_type smallint not null, -- tipo do usuario(gerente, diretor, cliente)
        employee_number varchar(200)-- pode ser nulo pois nem toda pessoa é funcionario
    );

alter table account add foreign key (account_holder_id) references person(user_id) on delete cascade;

insert into person(name, username, cipher, address, cpf, cellphone, user_type, employee_number) values ("Vinicius","viniciusogrande", "1234","Morro dos Macacos", "222333344455", "99887777", 1, null);

insert into person(name, username, cipher, address, cpf, cellphone, user_type, employee_number) values ("tester","test", "1234","Morro dos Macacos", "222333344455", "99887777", 2, null);

insert into account(account_number, balance, account_limit, account_type, account_holder_id) values("182974", 1000, 10000, 1, 1), ("9523958", 2000, 4000, 1, 1);

