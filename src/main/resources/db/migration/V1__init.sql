CREATE TABLE role
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE
);

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

CREATE TABLE user_role
(
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (role_id) REFERENCES role (id)
);

CREATE TABLE workspace
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    title       VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE workspace_admin
(
    workspace_id INT NOT NULL,
    admin_id     INT NOT NULL,
    FOREIGN KEY (workspace_id) REFERENCES workspace (id),
    FOREIGN KEY (admin_id) REFERENCES user (id)
);

CREATE TABLE workspace_guest
(
    workspace_id INT NOT NULL,
    guest_id     INT NOT NULL,
    FOREIGN KEY (workspace_id) REFERENCES workspace (id),
    FOREIGN KEY (guest_id) REFERENCES user (id)
);

CREATE TABLE board
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    title        VARCHAR(255) NOT NULL,
    description  TEXT,
    workspace_id INT          NOT NULL,
    FOREIGN KEY (workspace_id) REFERENCES workspace (id)
);

CREATE TABLE board_admin
(
    board_id INT NOT NULL,
    admin_id INT NOT NULL,
    FOREIGN KEY (board_id) REFERENCES board (id),
    FOREIGN KEY (admin_id) REFERENCES user (id)
);

CREATE TABLE board_guest
(
    board_id INT NOT NULL,
    guest_id INT NOT NULL,
    FOREIGN KEY (board_id) REFERENCES board (id),
    FOREIGN KEY (guest_id) REFERENCES user (id)
);

CREATE TABLE list
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    title    VARCHAR(255) NOT NULL,
    board_id INT          NOT NULL,
    FOREIGN KEY (board_id) REFERENCES board (id)
);

CREATE TABLE task
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    title       VARCHAR(255) NOT NULL,
    description TEXT,
    label       VARCHAR(255),
    deadline    DATETIME,
    assignee_id INT,
    list_id     INT          NOT NULL,
    FOREIGN KEY (assignee_id) REFERENCES user (id),
    FOREIGN KEY (list_id) REFERENCES list (id)
);

INSERT INTO role (name)
VALUES ("ROLE_ADMIN"),
    ("ROLE_OMNI"),
    ("ROLE_USER");

INSERT INTO user (login, first_name, patronymic, last_name, hash_password, email)
VALUES ("admin", "Admin", NULL, "Admin", "$2a$04$l6jf/IelD8EcKEx0z5LJFur01DtdBcTLUxfiq79X1GF2hgJdmIeEW", "admin@mail.ru"),
       ("ivanov", "Иван", "Иванович", "Иванов", "$2a$10$68olLYWwEaKT5tET0pyqEO2wALSDaQauWvXpYtyCWGRrxugiA3ibK", "ivanov@mail.ru"),
       ("petrov", "Петр", "Петрович", "Петров", "$2a$10$apcWOiLHASd.0ZdPwVzKMOEnw8uLKJeb5dKMweD1X/S9QuwHTv9VW", "petrov@mail.ru"),
       ("sergeev", "Сергей", "Сергеевич", "Сергеев", "$2a$10$B5oxDvzLGBAIbHnYFiSA5ux/6OtiPZWoDTkBpsxRl49lQlFYJhmjm", "sergeev@mail.ru");

INSERT INTO user_role
VALUES (1, 1), (2, 2), (3, 3), (4, 3);

INSERT INTO workspace (title, description)
VALUES ("Workspace1", NULL),
       ("Workspace2", NULL),
       ("Workspace3", NULL);

INSERT INTO workspace_admin (workspace_id, admin_id)
VALUES (1, 1), (2, 1), (3, 1);

INSERT INTO workspace_guest (workspace_id, guest_id)
VALUES (1, 2), (1, 3), (1, 4);

INSERT INTO board (title, description, workspace_id)
VALUES ("Project1", NULL, 1),
       ("Project2", NULL, 1),
       ("Project3", NULL, 2);

INSERT INTO board_admin (board_id, admin_id)
VALUES (1, 1), (2, 1), (3, 1);

INSERT INTO board_guest (board_id, guest_id)
VALUES (1, 2), (1, 3), (2, 4);

INSERT INTO list (title, board_id)
VALUES ("To do", 1),
       ("Doing", 1),
       ("Done", 1),
       ("To do", 2),
       ("Doing", 2),
       ("Done", 2),
       ("To do", 3),
       ("Doing", 3),
       ("Done", 3);

INSERT INTO task (title, description, label, deadline, assignee_id, list_id)
VALUES ("Task1", NULL, NULL, "2024-06-17 18:00:00", 2, 1),
       ("Task2", NULL, NULL, "2024-06-20 18:00:00", 2, 2),
       ("Task3", NULL, NULL, "2024-06-10 18:00:00", 3, 3),
       ("Task4", NULL, NULL, "2024-06-14 18:00:00", 4, 4),
       ("Task5", NULL, NULL, "2024-06-15 18:00:00", 4, 5);
