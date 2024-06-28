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

CREATE TABLE IF NOT EXISTS person (
    user_id SERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    username VARCHAR(200) NOT NULL,
    cipher VARCHAR(255) NOT NULL,
    address VARCHAR(200) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    cellphone VARCHAR(20) NOT NULL,
    user_type SMALLINT NOT NULL, -- tipo do usuario(gerente, diretor, cliente)
    employee_number VARCHAR(200), -- pode ser nulo pois nem toda pessoa é funcionario
    manager_id BIGINT UNSIGNED, -- ID of the manager, can be NULL
    FOREIGN KEY (manager_id) REFERENCES person(user_id) -- Self-referential foreign key
);

alter table account add foreign key (account_holder_id) references person(user_id) on delete cascade;
insert into person(name, username, cipher, address, cpf, cellphone, user_type, employee_number, manager_id) values ("tester","test", "1234","Morro dos Macacos", "222333344455", "99887777", 3, null, null);

insert into person(name, username, cipher, address, cpf, cellphone, user_type, employee_number, manager_id) values ("Vinicius","viniciusogrande", "1234","Morro dos Macacos", "222333344455", "99887777", 1, null, 1);

insert into account(account_number, balance, account_limit, account_type, account_holder_id) values("182974", 1000, 10000, 1, 1), ("9523958", 2000, 4000, 1, 1);

