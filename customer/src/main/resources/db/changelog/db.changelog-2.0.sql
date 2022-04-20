--liquibase formatted sql

--changeset deezex:1
ALTER TABLE customer
ADD COLUMN password VARCHAR(256) NOT NULL DEFAULT '123';