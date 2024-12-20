CREATE DATABASE gestion_evenements2;

USE gestion_evenements2;

CREATE TABLE utilisateurs(
    id_user INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    type ENUM('etudiant', 'professeur') NOT NULL
);

CREATE TABLE evenements(
    id_event INT AUTO_INCREMENT PRIMARY KEY,
    nom_event VARCHAR(50) NOT NULL,
    date_event DATE NOT NULL,
    description TEXT,
    id_user INT,
    FOREIGN KEY (id_user) REFERENCES utilisateurs(id_user)
);

CREATE TABLE salles(
    id_salle INT AUTO_INCREMENT PRIMARY KEY,
    nom_salle VARCHAR(50) NOT NULL,
    capacite INT NOT NULL
);

CREATE TABLE terrains(
    id_terrain INT AUTO_INCREMENT PRIMARY KEY,
    nom_terrain VARCHAR(50) NOT NULL,
    type VARCHAR(50) NOT NULL
);

CREATE TABLE reservations(
    id_reservation INT AUTO_INCREMENT PRIMARY KEY,
    id_user INT,
    id_event INT,
    id_salle INT DEFAULT NULL,
    id_terrain INT DEFAULT NULL,
    date_reservation DATE NOT NULL,
    FOREIGN KEY (id_user) REFERENCES utilisateurs(id_user),
    FOREIGN KEY (id_event) REFERENCES evenements(id_event),
    FOREIGN KEY (id_salle) REFERENCES salles(id_salle),
    FOREIGN KEY (id_terrain) REFERENCES terrains(id_terrain)
);