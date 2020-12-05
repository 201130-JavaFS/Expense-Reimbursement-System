ALTER TABLE "ERS".users ADD CONSTRAINT users_fk FOREIGN KEY (user_role_id) REFERENCES "ERS".roles(ers_user_role_id);
ALTER TABLE "ERS".users ADD CONSTRAINT users_un UNIQUE (ers_username,user_email);