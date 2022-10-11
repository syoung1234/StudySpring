Create table users(
	id varchar2(8) primary key,
	password varchar2(10) not null,
	name varchar2(20) not null,
	role varchar2(10)
);

insert into users values('test','test123','관리자','Admin');
insert into users values('user','user1234','test','User');