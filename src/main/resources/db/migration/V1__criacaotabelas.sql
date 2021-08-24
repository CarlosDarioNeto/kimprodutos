
create table Produto(
id int primary key auto_increment,
nome varchar(40) not null,
preco float(14,2) not null,
ativo enum('0','1') not null);


create table Vendedor(
matricula varchar(4) primary key,
nome varchar(40) not null,
ativo enum('0','1') not null
);


create table Venda(
matricula_vendedor varchar(4),
id varchar(12) primary key,
valor_total float(16,2) not null,
foreign key (matricula_vendedor) references vendedor(matricula)
);


create table Item(
id varchar(24) primary key,
quantidade int not null,
preco_corrente float(14,2) not null,
id_produto int,
id_venda varchar(12),
foreign key (id_produto) references produto(id),
foreign key (id_venda) references venda(id)
);

