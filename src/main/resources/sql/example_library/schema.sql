drop table if exists book;
drop table if exists account;
drop table if exists message;

create table book (
	id bigint ,
	douban_id varchar(64) not null,
	title varchar(128) not null,
	url varchar(255),
	description varchar(255),
	owner_id bigint not null,
	onboard_date timestamp,
	status varchar(32) not null,
	borrower_id bigint null,
	borrow_date timestamp,
    primary key (id)
);

create table account (
	id bigint,
	name varchar(64) not null,
	email varchar(128),
	hash_password varchar(255),
	primary key (id)
);
create table message (
	id bigint ,
	receiver_id bigint null,
	message varchar(256),
	receive_date timestamp,
	primary key (id)
);