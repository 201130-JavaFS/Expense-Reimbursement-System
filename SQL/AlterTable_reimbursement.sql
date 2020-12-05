ALTER TABLE "ERS".reimbursement ADD CONSTRAINT reimbursement_fk FOREIGN KEY (reimb_author) REFERENCES "ERS".users(ers_users_id);
ALTER TABLE "ERS".reimbursement ADD CONSTRAINT reimbursement_fk_1 FOREIGN KEY (reimb_resolver) REFERENCES "ERS".users(ers_users_id);
ALTER TABLE "ERS".reimbursement ADD CONSTRAINT reimbursement_fk_2 FOREIGN KEY (reimb_status_id) REFERENCES "ERS".status(reimb_status_id);
ALTER TABLE "ERS".reimbursement ADD CONSTRAINT reimbursement_fk_3 FOREIGN KEY (reimb_type_id) REFERENCES "ERS"."type"(reimb_type_id);