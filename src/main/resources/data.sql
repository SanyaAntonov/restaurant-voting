INSERT INTO USERS (EMAIL, FIRST_NAME, LAST_NAME, PASSWORD)
VALUES ('user@gmail.com', 'Ivan', 'Ivanov', '{noop}password'),
       ('admin@gmail.com', 'Vladimir', 'Putin', '{noop}admin');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);
INSERT INTO RESTAURANT (TITLE, ADDRESS)
VALUES
       ('MacDonalds', 'Pushkin str'),
       ('BurgerKing', 'Lomonosov str');
INSERT INTO DISH (name , PRICE, RESTAURANT_ID)
VALUES
       ('ChickenBurger', 50, 2),
       ('BigMac', 130, 1),
       ('ChickenMacNuggets', 90, 1),
       ('BigTasty', 230, 1),
       ('GrillBurger', 50, 2),
       ('DoubleBurger', 220, 2);
INSERT INTO VOTE (DATE ,user_id, restaurant_id)
VALUES
    (TO_DATE('14/04/2021', 'DD/MM/YYYY'), 1, 1),
    (TO_DATE('15/04/2021', 'DD/MM/YYYY'), 1, 2);