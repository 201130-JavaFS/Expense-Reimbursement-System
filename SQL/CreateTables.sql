--create database ers

CREATE TABLE ers.reimbursement (
	reimb_id int NOT NULL,
	reimb_amount float4 NOT NULL,
	reimb_submitted timestamp(0) NOT NULL,
	reimb_resolved timestamp(0) NULL,
	reimb_description varchar(250) NULL,
	reimb_receipt bytea NULL,
	reimb_author int NOT NULL,
	reimb_resolver int NULL,
	reimb_status_id int NOT NULL,
	reimb_type_id int NOT NULL,
	CONSTRAINT reimbursement_pk PRIMARY KEY (reimb_id)
);

CREATE TABLE ers.users (
	ers_users_id int NOT NULL,
	ers_username varchar(50) NOT NULL,
	ers_password varchar(100) NOT NULL,
	user_first_name varchar(100) NOT NULL,
	user_last_name varchar(100) NOT NULL,
	user_email varchar(150) NOT NULL,
	user_role_id int NOT NULL,
	CONSTRAINT users_pk PRIMARY KEY (ers_users_id)
);

CREATE TABLE ers.roles (
	ers_user_role_id int NOT NULL,
	user_role varchar(14) NOT NULL,
	CONSTRAINT roles_pk PRIMARY KEY (ers_user_role_id)
);

CREATE TABLE ers.status (
	reimb_status_id int NOT NULL,
	reimb_status varchar(10) NOT NULL,
	CONSTRAINT status_pk PRIMARY KEY (reimb_status_id)
);

CREATE TABLE ers.type (
	reimb_type_id int NOT NULL,
	reimb_type varchar(10) NOT NULL,
	CONSTRAINT type_pk PRIMARY KEY (reimb_type_id)
);
