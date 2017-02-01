DELETE FROM user_roles;
DELETE FROM RESTAURANTS;
DELETE FROM DISHES;
DELETE FROM users;
ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO RESTAURANTS (NAME)
    VALUES ('MCdonalds'), ('Burger King');

INSERT INTO DISHES (description, price, RESTID) VALUES
  ('Биг мак', 130, 100002),
  ('Биг тейсти', 220, 100002),
  ('Картошка', 80, 100002),
  ('Кока-кола', 65, 100002),
  ('Биг кинг', 120, 100003),
  ('Воппер', 140, 100003),
  ('Пепси кола', 80, 100003),
  ('Пирожок', 70, 100003);

INSERT INTO VOTES (DATETIME, RESTID, USER_ID) VALUES
  ('2017-02-01', 100002, 100000);
