ALTER TABLE ers.users ADD CONSTRAINT users_fk FOREIGN KEY (user_role_id) REFERENCES ers.roles(ers_user_role_id);
ALTER TABLE ers.users ADD CONSTRAINT users_un UNIQUE (ers_username,user_email);
ALTER TABLE ers.reimbursement ADD CONSTRAINT reimbursement_fk FOREIGN KEY (reimb_author) REFERENCES ers.users(ers_users_id);
ALTER TABLE ers.reimbursement ADD CONSTRAINT reimbursement_fk_1 FOREIGN KEY (reimb_resolver) REFERENCES ers.users(ers_users_id);
ALTER TABLE ers.reimbursement ADD CONSTRAINT reimbursement_fk_2 FOREIGN KEY (reimb_status_id) REFERENCES ers.status(reimb_status_id);
ALTER TABLE ers.reimbursement ADD CONSTRAINT reimbursement_fk_3 FOREIGN KEY (reimb_type_id) REFERENCES ers.type(reimb_type_id);