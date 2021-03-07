-- Schema de création de la base
DROP SCHEMA IF EXISTS hungrybirds;
CREATE SCHEMA `hungrybirds`;
USE hungrybirds;

DROP TABLE IF EXISTS utilisateur;
CREATE TABLE `utilisateur` (
`id_utilisateur` INT NOT NULL AUTO_INCREMENT,
`nom` VARCHAR(255) NOT NULL,
`prenom` VARCHAR(255),
`mail` VARCHAR(255) NOT NULL,
`password` VARCHAR(255) NOT NULL,
`telephone` VARCHAR (255) NOT NULL,
`adresse` VARCHAR(255) NOT NULL,
`ville`  VARCHAR(255) NOT NULL,
`code_postal` INT NOT NULL,
`role` TINYINT NOT NULL, -- 0 -> utilisateur / 1 -> association / 2 -> vendeur
`statut` TINYINT NOT NULL,
PRIMARY KEY (id_utilisateur)
);

DROP TABLE IF EXISTS possede;
CREATE TABLE `possede` (
`id_utilisateur` INT NOT NULL AUTO_INCREMENT,
`id_structure` INT NOT NULL,
FOREIGN KEY (`id_utilisateur`) REFERENCES utilisateur(id_utilisateur)
);

DROP TABLE IF EXISTS notifications;
CREATE TABLE `notifications` (
`id_utilisateur` INT NOT NULL AUTO_INCREMENT,
`notifications_email` TINYINT DEFAULT 0,
`notifications_message` TINYINT DEFAULT 0,
`notifications_pushup` TINYINT DEFAULT 0,
FOREIGN KEY (`id_utilisateur`) REFERENCES utilisateur(id_utilisateur),
PRIMARY KEY (id_utilisateur)
);

DROP TABLE IF EXISTS cagnotte;
CREATE TABLE `cagnotte` (
`id_cagnotte` INT NOT NULL AUTO_INCREMENT,
`id_utilisateur` INT NOT NULL,
`solde` DECIMAL DEFAULT 0.00,
FOREIGN KEY (`id_utilisateur`) REFERENCES utilisateur(id_utilisateur),
PRIMARY KEY (id_cagnotte)
);

DROP TABLE IF EXISTS ticket;
CREATE TABLE `ticket` (
`id_ticket` INT NOT NULL AUTO_INCREMENT,
`id_expediteur` INT NOT NULL,
`id_destinataire` INT NOT NULL,
`en_tete` VARCHAR(255) NOT NULL,
`corps` VARCHAR(255) NOT NULL,
FOREIGN KEY (`id_expediteur`) REFERENCES utilisateur(id_utilisateur),
FOREIGN KEY (`id_destinataire`) REFERENCES utilisateur(id_utilisateur),
PRIMARY KEY (id_ticket)
);

DROP TABLE IF EXISTS image;
CREATE TABLE `image` (
`id_image` INT NOT NULL AUTO_INCREMENT,
`id_utilisateur` INT NOT NULL,
`image_path` VARCHAR(255),  -- Sûrement hébergé chez Firebase, plus simple à mettre en place
FOREIGN KEY (`id_utilisateur`) REFERENCES utilisateur(id_utilisateur),
PRIMARY KEY (id_image)
);

DROP TABLE IF EXISTS favoris;
CREATE TABLE `favoris` (
`id_utilisateur` INT NOT NULL,
`id_favori` INT NOT NULL,
FOREIGN KEY (`id_utilisateur`) REFERENCES utilisateur(id_utilisateur),
FOREIGN KEY (`id_favori`) REFERENCES utilisateur(id_utilisateur)
);

CREATE INDEX id_structure ON possede(id_structure);

DROP TABLE IF EXISTS structure;
CREATE TABLE `structure` (
`id_structure` INT NOT NULL AUTO_INCREMENT,
`id_utilisateur` INT NOT NULL,
`description` VARCHAR(255),
`heure_debut` TIME,
`heure_fin` TIME,
`siret` BIGINT NOT NULL,
FOREIGN KEY (`id_structure`) REFERENCES possede(id_structure),
PRIMARY KEY (id_structure)
);

CREATE INDEX id_structure ON structure(id_structure);

DROP TABLE IF EXISTS panier;
CREATE TABLE `panier` (
`id_panier` INT NOT NULL AUTO_INCREMENT,
`id_structure` INT NOT NULL,
`description` VARCHAR(255),
`date_debut` DATETIME,
`date_fin` DATETIME,
`prix` DECIMAL,
`quantite` INT,
FOREIGN KEY (`id_structure`) REFERENCES structure(id_structure),
PRIMARY KEY (id_panier)
);

DROP TABLE IF EXISTS commande;
CREATE TABLE `commande` (
`id_commande` INT NOT NULL AUTO_INCREMENT,
`id_utilisateur` INT NOT NULL,
`id_panier` INT NOT NULL,
`date` DATETIME NOT NULL,
`ancien_solde` DECIMAL DEFAULT NULL,
`prix` DECIMAL DEFAULT NULL,
FOREIGN KEY (`id_utilisateur`) REFERENCES utilisateur(id_utilisateur),
FOREIGN KEY (`id_panier`) REFERENCES panier(id_panier),
PRIMARY KEY (id_commande)
);
