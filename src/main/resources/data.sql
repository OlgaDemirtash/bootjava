INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('User1', 'user1@yandex.ru', '{noop}password'),
       ('User2', 'user2@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('Guest', 'guest@gmail.com', '{noop}guest');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 100001),
       ('USER', 100002),
       ('ADMIN', 100003),
       ('USER', 100003);

INSERT INTO RESTAURANT (NAME, DESCRIPTION)
VALUES ('Koт Шредингера', 'Ресторан японской, китайской кухни'),
       ('Джумбус', 'Ресторан средиземноморской кухни'),
       ('Андерсон', 'Кафе для родителей с детьми, европейская кухня');


INSERT INTO MENU (REGISTERED, RESTAURANT_ID, DISH, PRICE)
VALUES ('2024-01-12', 100005, 'Том-ям', 600),
       ('2024-01-12', 100005, 'Роллы с авокадо', 400),
       ('2024-01-12', 100005, 'Чай с жасмином', 200),
       ('2024-01-12', 100006, 'Сибас на гриле', 800),
       ('2024-01-12', 100006, 'Овощи на гриле', 300),
       ('2024-01-12', 100006, 'Капучино', 220),
       ('2024-01-12', 100007, 'Шоколадный мусс', 800),
       ('2024-01-12', 100007, 'Торт Наполеон', 300),
       ('2024-01-12', 100007, 'Молочный коктейль', 220);

INSERT INTO VOTE (REGISTERED, USER_ID, RESTAURANT_ID)
VALUES ('2024-01-10', 100001, 100005),
       ('2024-01-10', 100002, 100006),
       ('2024-09-11', 100001, 100007),
       ('2019-09-11', 100002, 100005);