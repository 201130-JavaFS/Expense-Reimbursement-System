CREATE TABLE "ERS".reimbursement (
	reimb_id int NOT NULL,
	reimb_amount int8 NOT NULL,
	reimb_submitted timestamp(0) NOT NULL,
	reimb_resolved timestamp(0) NOT NULL,
	reimb_description varchar(250) NULL,
	reimb_receipt bytea NULL,
	reimb_author int NOT NULL,
	reimb_resolver int NULL,
	reimb_status_id int NOT NULL,
	reimb_type_id int NOT NULL,
	CONSTRAINT reimbursement_pk PRIMARY KEY (reimb_id)
);