create table categoria (
	id bigint(20) primary key AUTO_INCREMENT,
	descr varchar(50) not null
) engine=innoDB default charset=utf8;

insert into categoria(descr) values ("BERMUDA");
insert into categoria(descr) values ("BLUSINHA");
insert into categoria(descr) values ("BLUSÃO MOLETOM");
insert into categoria(descr) values ("BLAZER");
insert into categoria(descr) values ("CALÇA LEGGING"); 
insert into categoria(descr) values ("BODY - MANGA LONGA");
insert into categoria(descr) values ("BODY - S/MANGA");