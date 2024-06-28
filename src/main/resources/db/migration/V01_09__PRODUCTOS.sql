CREATE TABLE IF NOT EXISTS product (
    id BIGINT PRIMARY KEY,
    name_product VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    Category_product VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL,
    imagen VARCHAR(255) NOT NULL
);