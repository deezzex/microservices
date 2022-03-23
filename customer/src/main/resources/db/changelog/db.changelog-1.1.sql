--liquibase formatted sql

--changeset deezex:1
ALTER TABLE customer
ALTER COLUMN id TYPE INT;