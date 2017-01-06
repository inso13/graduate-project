DROP TABLE user_roles IF EXISTS;
DROP TABLE dishes IF EXISTS;
DROP TABLE restaurants IF EXISTS;

DROP TABLE users IF EXISTS;

DROP SEQUENCE GLOBAL_SEQ IF EXISTS;

CREATE SEQUENCE GLOBAL_SEQ AS INTEGER START WITH 100000;

CREATE TABLE users
(
  id               INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name             VARCHAR(255),
  email            VARCHAR(255)         NOT NULL,
  password         VARCHAR(255)         NOT NULL,
  enabled          BOOLEAN   DEFAULT TRUE
);
CREATE UNIQUE INDEX users_unique_email_idx ON USERS (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR(255),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY ( user_id ) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
  id          INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);
CREATE UNIQUE INDEX rest_idx ON restaurants (id);

CREATE TABLE dishes
(
  id          INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  description VARCHAR(255) NOT NULL,
  price    INT          NOT NULL,
  rest_id     INTEGER      NOT NULL,
  FOREIGN KEY ( rest_id ) REFERENCES restaurants (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX dishes_idx ON dishes (id, rest_id)