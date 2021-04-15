INSERT INTO USERS (EMAIL, FIRST_NAME, LAST_NAME, PASSWORD)
VALUES ('user@gmail.com', 'Ivan', 'Ivanov', '{noop}password'),
       ('admin@gmail.com', 'Vladimir', 'Putin', '{noop}admin');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);
INSERT INTO RESTAURANT (TITLE, ADDRESS)
VALUES
       ('MacDonalds', 'Ulica durakov 229'),
       ('BurgerKing', 'Ulica durasv 229');
INSERT INTO DISH (name , PRICE, RESTAURANT_ID)
VALUES
       ('ChikenBurger', 50, 2),
       ('BigMac', 130, 1),
       ('ChickenMacNuggets', 90, 1),
       ('BigTasty', 230, 1),
       ('GrillBurger', 50, 2),
       ('Vopper', 220, 2);