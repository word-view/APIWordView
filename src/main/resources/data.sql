INSERT INTO member (username, email, password) VALUES
    ('Arthur', 'arthur.araujo@tutanota.com', 'senha');

INSERT INTO member (username, email, password) VALUES
    ('conta', 'conta2@tutanota.com', 'senha');

INSERT INTO member (username, email, password, role) VALUES
    ('mock.user', 'mock.user@gmail.com', '8631b7298388ab36f3785770cc07c4ea', 'USER');

INSERT INTO member (username, email, password, role) VALUES
    ('mock.disposable', 'mock.disposable@gmail.com', '5b4a4769301f10a5ac64609e7e0bba0e', 'USER');

INSERT INTO member (username, email, password, role) VALUES
    ('mock.disposable.email', 'mock.disposable.email@gmail.com', '407c8797b415f2e557dc141d16c4ac86', 'USER');

INSERT INTO member (username, email, password, role) VALUES
    ('mock.admin', 'mock.admin@gmail.com', '061cf224cffe1951e32ffaa1c414544a', 'ADMIN');

INSERT INTO email (email) VALUES ('mock.user@gmail.com');

INSERT INTO known_words (user_id, lang, words) VALUES (3, 'en', 'rain,world');

INSERT INTO video_lyrics (video_id, lyrics_file, time_offset) VALUES ('ZnUEeXpxBJ0', 'aquarela', 0);