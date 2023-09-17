DROP TABLE IF EXISTS member        CASCADE;
DROP TABLE IF EXISTS member_events CASCADE;
DROP TABLE IF EXISTS lesson        CASCADE;
DROP TABLE IF EXISTS word          CASCADE;

CREATE TABLE member (
    id bigint GENERATED ALWAYS AS IDENTITY,
    username varchar(255),
    email varchar(255) UNIQUE,
    password varchar(255),
    token varchar(255) UNIQUE,
    admin BOOL DEFAULT false,
    PRIMARY KEY (id)
);

/** 
    Member events catalogs important things that happens to a 
    member's account, for example a login from a new device and etc...
*/
CREATE TABLE member_events (
    id bigint GENERATED ALWAYS AS IDENTITY,
    id_member bigint,
    event_description varchar(500),
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id),
    CONSTRAINT fk_id_member FOREIGN KEY (id_member) REFERENCES member(id) ON DELETE CASCADE
);


CREATE TABLE lesson (
    id bigint GENERATED ALWAYS AS IDENTITY,
    title varchar(255),
    difficulty varchar(255),
    PRIMARY KEY (id)
);

CREATE TABLE word (
    id bigint GENERATED ALWAYS AS IDENTITY,
    name_id varchar(255) UNIQUE,
    id_lesson bigint,
    PRIMARY KEY (id),
    CONSTRAINT fk_id_lesson FOREIGN KEY (id_lesson) REFERENCES lesson(id) ON DELETE SET NULL
);