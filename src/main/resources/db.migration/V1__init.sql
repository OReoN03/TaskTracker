CREATE TABLE user
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    login         VARCHAR(255) NOT NULL UNIQUE,
    first_name    VARCHAR(255) NOT NULL,
    patronymic    VARCHAR(255),
    last_name     VARCHAR(255) NOT NULL,
    hash_password VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE workspace
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    title       VARCHAR(255) NOT NULL,
    description TEXT,
);

CREATE TABLE board
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    title        VARCHAR(255) NOT NULL,
    description  TEXT,
    workspace_id INT          NOT NULL
);

CREATE TABLE list
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    title    VARCHAR(255) NOT NULL,
    board_id INT          NOT NULL,
);

CREATE TABLE card
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    title       VARCHAR(255) NOT NULL,
    description TEXT,
    label       VARCHAR(255),
    deadline    DATETIME,
    assignee_id INT          NOT NULL,
    list_id     INT          NOT NULL
);

INSERT INTO user (id, login, first_name, patronymic, last_name, hash_password, email)
VALUES (next value for sq_user_id, 'admin', 'Admin', NULL, 'Admin', '$2a$04$l6jf/IelD8EcKEx0z5LJFur01DtdBcTLUxfiq79X1GF2hgJdmIeEW', 'admin@mail.ru'),
       (next value for sq_user_id, 'ivanov', 'Иван', 'Иванович', 'Иванов', '$2a$10$68olLYWwEaKT5tET0pyqEO2wALSDaQauWvXpYtyCWGRrxugiA3ibK', 'ivanov@mail.ru'),
       (next value for sq_user_id, 'petrov', 'Петр', 'Петрович', 'Петров', '$2a$10$apcWOiLHASd.0ZdPwVzKMOEnw8uLKJeb5dKMweD1X/S9QuwHTv9VW', 'petrov@mail.ru'),
       (next value for sq_user_id, 'sergeev', 'Сергей', 'Сергеевич', 'Сергеев', '$2a$10$B5oxDvzLGBAIbHnYFiSA5ux/6OtiPZWoDTkBpsxRl49lQlFYJhmjm', 'sergeev@mail.ru');

//TODO
INSERT INTO workspace (id, title, description)
VALUES (next value for sq_workspace_id, 'Workspace1', NULL),
       (next value for sq_workspace_id, 'Workspace2', NULL),
       (next value for sq_workspace_id, 'Workspace3', NULL);

INSERT INTO board (id, title, description, workspace_id)
VALUES (next value for sq_board_id, 'Project1', NULL, 1),
       (next value for sq_board_id, 'Project2', NULL, 1),
       (next value for sq_board_id, 'Project3', NULL, 2);

INSERT INTO list (id, title, board_id)
VALUES (next value for sq_list_id, 'To do', 1),
       (next value for sq_list_id, 'Doing', 1),
       (next value for sq_list_id, 'Done', 1),
       (next value for sq_list_id, 'To do', 2),
       (next value for sq_list_id, 'Doing', 2),
       (next value for sq_list_id, 'Done', 2),
       (next value for sq_list_id, 'To do', 3),
       (next value for sq_list_id, 'Doing', 3),
       (next value for sq_list_id, 'Done', 3);

INSERT INTO card (id, title, description, label, deadline, assignee_id, list_id)
VALUES (next value for sq_card_id, 'Task1', NULL, NULL, '2024-06-17 18:00:00', 2, 1),
       (next value for sq_card_id, 'Task2', NULL, NULL, '2024-06-20 18:00:00', 2, 2),
       (next value for sq_card_id, 'Task3', NULL, NULL, '2024-06-10 18:00:00', 3, 3),
       (next value for sq_card_id, 'Task4', NULL, NULL, '2024-06-14 18:00:00', 4, 4),
       (next value for sq_card_id, 'Task5', NULL, NULL, '2024-06-15 18:00:00', 4, 5);
