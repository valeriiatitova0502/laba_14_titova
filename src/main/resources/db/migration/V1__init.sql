-- Создаем таблицу для цветочного магазина
CREATE TABLE flowershop
(
    id serial PRIMARY KEY,
    name varchar(100),
    address varchar(250),
    phone varchar(13),
    working_hours varchar(13)
);

-- Вставляем данные для цветочного магазина
INSERT INTO flowershop (name, address, phone, working_hours)
VALUES
    ('Цветочный магазин 1', 'Улица Цветочная 1', '88005553535', '9-18'),
    ('Цветочный магазин 2', 'Проспект Розовый 5', '88001000101', '10-20'),
    ('Цветочный магазин 3', 'Улица Тюльпанов 10', '88124360736', '8-17');

CREATE TABLE users
(
    username VARCHAR(50)  NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled  boolean      NOT NULL,
    PRIMARY KEY (username)
);

INSERT INTO users
VALUES ('admin', '{noop}123', true),
       ('user', '{noop}456', true);

CREATE TABLE authorities
(
    username  varchar(50) NOT NULL,
    authority varchar(50) NOT NULL,

    CONSTRAINT authorities_idx UNIQUE (username, authority),

    CONSTRAINT authorities_ibfk_1
        FOREIGN KEY (username)
            REFERENCES users (username)
);

INSERT INTO authorities
VALUES ('admin', 'ROLE_ADMIN'),
       ('admin', 'ROLE_USER'),
       ('user', 'ROLE_USER');