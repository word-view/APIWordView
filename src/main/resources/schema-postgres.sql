DROP TABLE IF EXISTS member        CASCADE;
DROP TABLE IF EXISTS member_events CASCADE;
DROP TABLE IF EXISTS category      CASCADE;
DROP TABLE IF EXISTS lesson        CASCADE;
DROP TABLE IF EXISTS word          CASCADE;

CREATE TABLE member (
    id bigint GENERATED ALWAYS AS IDENTITY,
    username varchar(255),
    email varchar(255) UNIQUE,
    password varchar(255),
    role varchar(255),
    PRIMARY KEY (id)
);


CREATE TABLE category (
    id bigint GENERATED ALWAYS AS IDENTITY,
    title varchar(255),
    PRIMARY KEY (id)
);

CREATE TABLE lesson (
    id bigint GENERATED ALWAYS AS IDENTITY,
    id_category bigint,
    title varchar(255),
    difficulty varchar(255),
    PRIMARY KEY (id),
    CONSTRAINT fk_id_category FOREIGN KEY (id_category) REFERENCES category(id) ON DELETE SET NULL
);

CREATE TABLE word (
    id bigint GENERATED ALWAYS AS IDENTITY,
    id_lesson bigint,
    name varchar(255) UNIQUE,
    lang varchar(255),
    localized_word varchar(255),
    romanized_word varchar(255),
    PRIMARY KEY (id),
    CONSTRAINT fk_id_lesson FOREIGN KEY (id_lesson) REFERENCES lesson(id) ON DELETE SET NULL
);