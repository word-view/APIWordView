DROP TABLE IF EXISTS member              CASCADE;
DROP TABLE IF EXISTS word                CASCADE;
DROP TABLE IF EXISTS non_alphabetic_word CASCADE;

CREATE TABLE member (
    id bigint GENERATED ALWAYS AS IDENTITY,
    username varchar(255),
    email varchar(255) UNIQUE,
    password varchar(255),
    role varchar(255),
    PRIMARY KEY (id)
);

CREATE TABLE word (
    id bigint GENERATED ALWAYS AS IDENTITY,
    name varchar(255) UNIQUE,
    lang varchar(255),
    translated_word varchar(255),
    PRIMARY KEY (id)
);

CREATE TABLE non_alphabetic_word (
    id bigint GENERATED ALWAYS AS IDENTITY,
    name varchar(255) UNIQUE,
    lang varchar(255),
    translated_word varchar(255),
    romanized_word varchar(255),
    PRIMARY KEY (id)
);