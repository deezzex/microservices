--liquibase formatted sql

--changeset deezex:1
CREATE TABLE IF NOT EXISTS account(
    id BIGSERIAL PRIMARY KEY,
    amount DECIMAL(15,2),
    last_name VARCHAR(128),
    name VARCHAR(128),
    customer_id BIGINT
    );