create database projeto_assistencia;

use projeto_assistencia;

create table tab_usuarios(
	iduser int primary key,
    usuario varchar(50) not null,
    fone varchar(15),
    login varchar(15) not null unique,
    senha varchar(15) not null
);

-- Create - Insert
insert into tab_usuarios(iduser, usuario, fone, login, senha) values(1,'Armando Víctor Pereira', '11995052373','Apvictor','0312');
insert into tab_usuarios(iduser, usuario, fone, login, senha) values(2,'Administrador', '11995052373','admin','admin');
insert into tab_usuarios(iduser, usuario, fone, login, senha) values(3,'Amanda Santos da Silva', '11223344556','AS','031218');

-- Read - Select
select * from tab_usuarios;

-- Update - Update
update tab_usuarios set fone='88888888' where iduser=2;

-- Delete - Delete
delete from tab_usuarios where iduser=3;

-- TABELA DE CLIENTES
create table tab_clientes(
	idcli int primary key auto_increment,
    nomecli varchar(50) not null,
    endcli varchar(100),
    fonecli varchar(50) not null,
    emailcli varchar(50)
);

insert into tab_clientes(nomecli,endcli,fonecli,emailcli) values('Samuel','Rua Luiz Rubino, 35', '99999999','samuel@gmail.com');

-- TABELA OS
create table tab_os(
	os int primary key auto_increment,
    data_os timestamp default current_timestamp,
    equipamento varchar(150) not null,
    defeito varchar(150) not null,
    servico varchar(150),
    tecnico varchar(50),
    valor decimal(10,2),
    idcli int not null,
    foreign key(idcli) references tab_clientes(idcli)
);

insert into tab_os(equipamento,defeito,servico,tecnico,valor,idcli) values('Celular Samsung J7 Prime','Tela Quebrada','Troca de Tela','Armando Víctor Pereira',87.50,1);

-- Inner join trazer infos de 2 tabelas
select 
O.os, equipamento, defeito, servico, tecnico, valor, 
C.nomecli,fonecli
from tab_os as O
inner join tab_clientes as C
on (O.idcli = C.idcli);


alter table tab_usuarios add column perfil varchar(20) not null;

update tab_usuarios set perfil='Técnico' where iduser=1;
update tab_usuarios set perfil='Administrador' where iduser=2;
update tab_usuarios set perfil='Usuário' where iduser=3;

alter table tab_os add tipo varchar(15) not null after data_os;

alter table tab_os add situacao varchar(20) not null after tipo;

drop table tab_usuarios;
drop table tab_clientes;
drop table tab_os;
