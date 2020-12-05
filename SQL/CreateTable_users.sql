CREATE TABLE "ERS".users (
	ers_users_id int NOT NULL,
	ers_username varchar(50) NOT NULL,
	ers_password varchar(50) NOT NULL,
	user_first_name varchar(100) NOT NULL,
	user_last_name varchar(100) NOT NULL,
	user_email varchar(150) NOT NULL,
	user_role_id int NOT NULL,
	CONSTRAINT users_pk PRIMARY KEY (ers_users_id)
);