DROP DATABASE IF EXISTS bank;
CREATE DATABASE bank;

USE bank;

CREATE TABLE client (
  id INT PRIMARY KEY AUTO_INCREMENT,
  prenom VARCHAR(50) NOT NULL,
  nom VARCHAR(50) NOT NULL,
  date_naissance DATE NOT NULL,
  sexe ENUM('Homme', 'Femme','Autre') NOT NULL,
  numero_national_belge VARCHAR(20) NOT NULL
);

CREATE TABLE compte (
  id INT PRIMARY KEY AUTO_INCREMENT,
  client_id INT NOT NULL,
  solde DECIMAL(10, 2) NOT NULL,
  FOREIGN KEY (client_id) REFERENCES client(id)
);

INSERT INTO client (prenom, nom, date_naissance, sexe, numero_national_belge) VALUES 
("John", "Doe", '1980-01-01', 'Homme', '123456789'),
("Jane", "Doe", '1985-02-01', 'Femme', '234567890'),
("Bob", "Smith", '1990-03-01', 'Homme', '345678901'),
("Alice", "Johnson", '1995-04-01', 'Femme', '456789012'),
("Michael", "Brown", '2000-05-01', 'Homme', '567890123'),
("Emily", "Davis", '2005-06-01', 'Femme', '678901234'),
("William", "Thompson", '2010-07-01', 'Homme', '789012345'),
("Ashley", "Wilson", '2015-08-01', 'Femme', '890123456'),
("James", "Anderson", '2020-09-01', 'Autre', '901234567'),
("Elizabeth", "Martinez", '2025-10-01', 'Femme', '012345678');

INSERT INTO compte (client_id, solde)
SELECT id, 1000
FROM client;