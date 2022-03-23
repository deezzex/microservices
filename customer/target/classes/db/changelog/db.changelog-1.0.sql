--liquibase formatted sql

--changeset deezex:1
CREATE TABLE IF NOT EXISTS customer(
    id BIGSERIAL PRIMARY KEY,
     first_name VARCHAR(128),
    last_name VARCHAR(128),
    email VARCHAR(128),
    birth_date DATE
    );