CREATE TABLE IF NOT EXISTS user_type
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(100),
    description VARCHAR(1000)
);

CREATE TABLE IF NOT EXISTS store
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(250) NOT NULL
);


CREATE TABLE IF NOT EXISTS cashier
(
    id       BIGSERIAL PRIMARY KEY,
    id_store BIGSERIAL NOT NULL, --CLEAR
    name     VARCHAR(250)
);

CREATE TABLE IF NOT EXISTS invoice
(
    id         BIGSERIAL PRIMARY KEY,
    date       TIMESTAMP    NOT NULL,
    store      VARCHAR(250) NOT NULL,
    id_cashier BIGSERIAL    NOT NULL,-- CLEAR
    total      INTEGER      NOT NULL,
    state_invoice VARCHAR(1000)

);

CREATE TABLE IF NOT EXISTS detail_invoice
(
    id         BIGSERIAL PRIMARY KEY,
    id_invoice BIGSERIAL, --CLEAR
    id_product BIGSERIAL, --CLEAR
    count      INTEGER,
    UNIQUE (id_product, id_invoice)
);

ALTER TABLE users
    ADD COLUMN IF NOT EXISTS user_type VARCHAR(200) NOT NULL;

ALTER TABLE cashier
    ADD CONSTRAINT fk_store_cashier FOREIGN KEY (id_store) REFERENCES store (id);

ALTER TABLE detail_invoice
    ADD CONSTRAINT fk_invoice_detail_invoce FOREIGN KEY (id_invoice) REFERENCES invoice (id);
