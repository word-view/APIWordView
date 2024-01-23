
INSERT INTO member (username, email, password) VALUES
    ('Arthur', 'arthur.araujo@tutanota.com', 'senha');

INSERT INTO member (username, email, password) VALUES
    ('conta', 'conta2@tutanota.com', 'senha');

INSERT INTO member (username, email, password, role) VALUES
    ('mock.user', 'mock.user@gmail.com', '8631b7298388ab36f3785770cc07c4ea', 'USER');

INSERT INTO member (username, email, password, role) VALUES
    ('mock.admin', 'mock.admin@gmail.com', '061cf224cffe1951e32ffaa1c414544a', 'ADMIN');

INSERT INTO category (title) VALUES ('Natureza');

INSERT INTO lesson (id_category, title, difficulty)
VALUES (1, 'Plants', 'starter');

INSERT INTO lesson (id_category, title, difficulty)
VALUES (1, 'lesson3', 'starter');


INSERT INTO word (id_lesson, name, lang, localized_word, romanized_word)
VALUES (1, 'night', 'jp', '夜', 'yoru');