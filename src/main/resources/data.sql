CREATE CONSTANT GL_SEQ_START VALUE 100000;
INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('User1', 'user1@yandex.ru', '{noop}password'),
       ('User2', 'user2@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('Guest', 'guest@gmail.com', '{noop}guest');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', GL_SEQ_START + 1),
       ('USER', GL_SEQ_START + 2),
       ('ADMIN', GL_SEQ_START + 3),
       ('USER', GL_SEQ_START + 3);

INSERT INTO RESTAURANT (NAME, DESCRIPTION)
VALUES ('Koт Шредингера', 'Ресторан японской, китайской кухни'),
       ('Джумбус', 'Ресторан средиземноморской кухни'),
       ('Андерсон', 'Кафе для родителей с детьми, европейская кухня');


INSERT INTO MENU (ISSUED, RESTAURANT_ID, NAME, PRICE)
VALUES ('2024-01-07', GL_SEQ_START + 5, 'Том-ям', 600),
       ('2024-01-07', GL_SEQ_START + 5, 'Роллы с авокадо', 400),
       ('2024-01-07', GL_SEQ_START + 5, 'Чай с жасмином', 200),
       ('2024-01-08', GL_SEQ_START + 6, 'Сибас на гриле', 800),
       ('2024-01-08', GL_SEQ_START + 6, 'Овощи на гриле', 300),
       ('2024-01-08', GL_SEQ_START + 6, 'Капучино', 220),
       ('2024-01-09', GL_SEQ_START + 7, 'Шоколадный мусс', 800),
       ('2024-01-09', GL_SEQ_START + 7, 'Торт Наполеон', 300),
       ('2024-01-09', GL_SEQ_START + 7, 'Молочный коктейль', 220),
       (CURRENT_DATE(), GL_SEQ_START + 5, 'Том-ям', 600),
       (CURRENT_DATE(), GL_SEQ_START + 5, 'Роллы с авокадо', 400),
       (CURRENT_DATE(), GL_SEQ_START + 5, 'Чай с жасмином', 200),
       (CURRENT_DATE(), GL_SEQ_START + 6, 'Сибас на гриле', 800),
       (CURRENT_DATE(), GL_SEQ_START + 6, 'Овощи на гриле', 300),
       (CURRENT_DATE(), GL_SEQ_START + 6, 'Капучино', 220),
       (CURRENT_DATE(), GL_SEQ_START + 7, 'Шоколадный мусс', 400),
       (CURRENT_DATE(), GL_SEQ_START + 7, 'Торт Наполеон', 350),
       (CURRENT_DATE(), GL_SEQ_START + 7, 'Молочный коктейль', 150);

INSERT INTO VOTE (REGISTERED, REGISTERED_TIME, USER_ID, RESTAURANT_ID)
VALUES ('2024-01-07', '10:00:00', GL_SEQ_START + 1, GL_SEQ_START + 5),
       ('2024-01-07', '10:00:00', GL_SEQ_START + 2, GL_SEQ_START + 6),
       ('2024-09-08', '12:00:00', GL_SEQ_START + 1, GL_SEQ_START + 7),
       ('2019-09-08', '12:00:00', GL_SEQ_START + 2, GL_SEQ_START + 5),
       (CURRENT_DATE(), CURRENT_TIME(), GL_SEQ_START + 1, GL_SEQ_START + 5),
       (CURRENT_DATE(), CURRENT_TIME(), GL_SEQ_START + 2, GL_SEQ_START + 6);