DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS text_tracks CASCADE;
DROP TABLE IF EXISTS known_words CASCADE;

CREATE TABLE users
(
    id          bigint GENERATED ALWAYS AS IDENTITY,
    username    varchar(255),
    email       varchar(255) UNIQUE,
    password    varchar(255),
    role        varchar(255),
    lesson_time bigint DEFAULT 300000,
    PRIMARY KEY (id)
);

CREATE TABLE text_tracks
(
    id       bigint GENERATED ALWAYS AS IDENTITY,
    video_id varchar(12) UNIQUE,
    file     varchar(128),
    PRIMARY KEY (id)
);

CREATE TABLE known_words
(
    id      bigint GENERATED ALWAYS AS IDENTITY,
    user_id bigint,
    lang    varchar(64),
    words   varchar(5120),
    PRIMARY KEY (id)
);
