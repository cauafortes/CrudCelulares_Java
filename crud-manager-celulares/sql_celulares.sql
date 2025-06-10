DROP DATABASE IF EXISTS crud_manager;

CREATE DATABASE IF NOT EXISTS crud_manager;

USE crud_manager;

CREATE TABLE IF NOT EXISTS users(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    sexo ENUM('M', 'F'),
    email VARCHAR(150) NOT NULL UNIQUE, 
    password VARCHAR(255) NOT NULL 
);

CREATE TABLE IF NOT EXISTS posts(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    content TEXT NOT NULL,
    post_date DATE NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY(user_id)
    REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS companies (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`name` VARCHAR(128) NOT NULL,
	`role` VARCHAR(128) NOT NULL,
	`start` DATE NOT NULL,
	`end` DATE,
	user_id INT NOT NULL,
	FOREIGN KEY(user_id)
	REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS Marcas (
    idMarca INT PRIMARY KEY AUTO_INCREMENT,
    nomeMarca VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS Celulares (
    idCelular INT PRIMARY KEY AUTO_INCREMENT,
    modelo VARCHAR(255) NOT NULL,
    cor VARCHAR(50),
    armazenamentoGB INT,
    preco DECIMAL(10, 2),
    dataLancamento DATE,
    idMarca INT,
    FOREIGN KEY (idMarca) REFERENCES Marcas(idMarca)
);

INSERT INTO users (nome, sexo, email, password) VALUES
("Emerson Carvalho", "M", "emerson@mail.com", "123456"),
("Luiza Carvalho", "F", "lu@mail.com", "123456"),
("Elenice Carvalho", "F", "le@mail.com", "123456"),
("Noé Carvalho", "M", "noe@mail.com", "123456"),
("Rosânia Carvalho", "F", "ro@mail.com", "123456");

INSERT INTO Marcas (nomeMarca) VALUES ('Samsung');
INSERT INTO Marcas (nomeMarca) VALUES ('Apple');
INSERT INTO Marcas (nomeMarca) VALUES ('Xiaomi');
INSERT INTO Marcas (nomeMarca) VALUES ('Motorola');
INSERT INTO Marcas (nomeMarca) VALUES ('LG');

INSERT INTO posts (content, post_date, user_id) VALUES
("Olá do Emerson", CURDATE(), 1),
("Olá da Luiza", CURDATE(), 2),
("Olá da Denise", CURDATE(), 3),
("Olá do Noé", CURDATE(), 4),
("Olá da Rosânia", CURDATE(), 5),
("Olá da Rosânia 1", CURDATE(), 5),
("Olá da Rosânia 2", CURDATE(), 5),
("Olá da Rosânia 3", CURDATE(), 5);

INSERT INTO companies (`name`, `role`, `start`, `end`, user_id) VALUES
('TechCorp', 'Developer', '2020-01-15', NULL, 1),
('Innovate Solutions', 'Designer', '2019-05-01', '2021-12-31', 2),
('Global Dynamics', 'Project Manager', '2022-03-10', NULL, 3);