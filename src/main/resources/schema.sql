DROP TABLE IF EXISTS member CASCADE;
DROP TABLE IF EXISTS email CASCADE;
DROP TABLE IF EXISTS video_lyrics CASCADE;

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
);

CREATE TABLE video_lyrics (
    id bigint GENERATED ALWAYS AS IDENTITY,
    video_id varchar(12) UNIQUE,
    lyrics_file varchar(128),
    time_offset int,
    PRIMARY KEY (id)
)