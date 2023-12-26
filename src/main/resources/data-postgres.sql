INSERT INTO member (username, email, password, token, admin)
VALUES ('Arthur', 'arthur.araujo@tutanota.com', 'senha', '4e9394b4d2876b8741b10a', true);

INSERT INTO member (username, email, password , token, admin)
VALUES ('conta', 'conta2@tutanota.com', 'senha', '4e9394b42d8741b10a', false);

INSERT INTO category (title) VALUES ('Natureza');

INSERT INTO lesson (id_category, title, difficulty)
VALUES (1, 'Plants', 'starter');

INSERT INTO lesson (id_category, title, difficulty)
VALUES (1, 'lesson3', 'starter');


INSERT INTO word (name_id, id_lesson)
VALUES ('phone', 1);

INSERT INTO language_word (localized_word, lang, id_word)
VALUES ('Telefone', 'pt-br', 1);