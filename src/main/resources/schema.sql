DROP TABLE IF EXISTS member CASCADE;

CREATE TABLE member (
    id bigint GENERATED ALWAYS AS IDENTITY,
    username varchar(255),
    email varchar(255) UNIQUE,
    password varchar(255),
    role varchar(255),
    PRIMARY KEY (id)
);

CREATE TABLE email (
    id bigint GENERATED ALWAYS AS IDENTITY,
    email varchar(255) UNIQUE,
    PRIMARY KEY (id)
)