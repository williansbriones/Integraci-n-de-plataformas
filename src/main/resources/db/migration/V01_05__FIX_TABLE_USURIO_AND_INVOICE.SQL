ALTER TABLE invoice
    ADD COLUMN id_user BIGSERIAL NOT NULL;

ALTER TABLE invoice
    ADD COLUMN token BIGSERIAL NOT NULL;

ALTER TABLE invoice
    ADD CONSTRAINT fk_user_invoice FOREIGN KEY (id_user) REFERENCES users (id);
