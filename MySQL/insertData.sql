-- Set de données pour les tests
INSERT INTO `hungrybirds`.`utilisateur` (`nom`, `prenom`, `mail`, `password`, `telephone`, `adresse`, `ville`, `code_postal`, `role`, `statut`)
VALUES ("Pezé", "Quentin", "quentinpeze@hotmail.fr", "test", 0681435382, "15 Allée Jean Pierre Vasseur", "Boulogne sur mer", 62000, 2, 1); 

INSERT INTO `hungrybirds`.`utilisateur` (`nom`, `prenom`, `mail`, `password`, `telephone`, `adresse`, `ville`, `code_postal`, `role`, `statut`)
VALUES ("Blampain", "Maxime", "maxime.blampain@projet.com", "camille", 0612151845, "74 rue du petit train", "Wimille", 62126, 0, 1); 

INSERT INTO `hungrybirds`.`utilisateur` (`nom`, `prenom`, `mail`, `password`, `telephone`, `adresse`, `ville`, `code_postal`, `role`, `statut`)
VALUES ("Poulain", "Pierre", "pierre.poulain@projet.com", "anakin", 0636353478, "94 rue Nationale", "Boulogne sur mer", 62200, 2, 1); 

INSERT INTO `hungrybirds`.`utilisateur` (`nom`, `prenom`, `mail`, `password`, `telephone`, `adresse`, `ville`, `code_postal`, `role`, `statut`)
VALUES ("Montbertrant", "Chloé", "chloe.montbertrant@projet.com", "cafeine", 0678956842, "94 rue Nationale", "Boulogne sur mer", 62200, 1, 1); 

INSERT INTO `hungrybirds`.`possede` (`id_utilisateur`, `id_structure`)
VALUES (1, 1);

INSERT INTO `hungrybirds`.`possede` (`id_utilisateur`, `id_structure`)
VALUES (3, 2);

INSERT INTO `hungrybirds`.`possede` (`id_utilisateur`, `id_structure`)
VALUES (4, 3);

INSERT INTO `hungrybirds`.`structure` (`id_structure`, `id_utilisateur`, `description`, `heure_debut`, `heure_fin`, `siret`)
VALUES ('1', '1', 'Tartes n co', '18:00:00', '19:00:00', '41538449400035');

INSERT INTO `hungrybirds`.`structure` (`id_structure`, `id_utilisateur`, `description`, `heure_debut`, `heure_fin`, `siret`)
VALUES ('2', '3', 'Chez l\'Dar', '17:30:00', '19:00:00', '83414944500019');

INSERT INTO `hungrybirds`.`structure` (`id_structure`, `id_utilisateur`, `description`, `siret`)
VALUES ('3', '4', 'Restos du coeur', '38917345100045');

INSERT INTO `hungrybirds`.`panier` (`id_structure`, `description`, `date_debut`, `date_fin`, `prix`, `quantite`)
VALUES (1, 'Panier garni pouvant comporter des tartes.', '2021-02-10 17:30:00', '2021-02-17 18:00:00', 12.5, 10);

INSERT INTO `hungrybirds`.`panier` (`id_structure`, `description`, `date_debut`, `date_fin`, `prix`, `quantite`)
VALUES (1, 'Panier garni comportant des tartes.', '2020-10-21 17:30:00', '2021-07-10 18:00:00', 7.59, 5);

INSERT INTO `hungrybirds`.`panier` (`id_structure`, `description`, `date_debut`, `date_fin`, `prix`, `quantite`)
VALUES (2, 'De la binouze', '2019-10-10 17:30:00', '2021-02-17 19:00:00', 12.5, 10);

INSERT INTO `hungrybirds`.`panier` (`id_structure`, `description`, `date_debut`, `date_fin`, `prix`, `quantite`)
VALUES (2, 'Encore plus de binouze', '2021-01-01 17:30:00', '2021-12-12 19:00:00', 7.59, 5);

INSERT INTO `hungrybirds`.`notifications` (`id_utilisateur`, `notifications_email`, `notifications_message`, `notifications_pushup`)
VALUES (1, 0, 0, 0);

INSERT INTO `hungrybirds`.`notifications` (`id_utilisateur`, `notifications_email`, `notifications_message`, `notifications_pushup`)
VALUES (2, 1, 1, 0);

INSERT INTO `hungrybirds`.`notifications` (`id_utilisateur`, `notifications_email`, `notifications_message`, `notifications_pushup`)
VALUES (3, 1, 0, 1);

INSERT INTO `hungrybirds`.`notifications` (`id_utilisateur`, `notifications_email`, `notifications_message`, `notifications_pushup`)
VALUES (4, 1, 1, 1);

INSERT INTO `hungrybirds`.`cagnotte` (`id_utilisateur`, `solde`)
VALUES (1, 30.0);

INSERT INTO `hungrybirds`.`cagnotte` (`id_utilisateur`, `solde`)
VALUES (2, 30.0);

INSERT INTO `hungrybirds`.`cagnotte` (`id_utilisateur`, `solde`)
VALUES (3, 35.0);

INSERT INTO `hungrybirds`.`favoris` (`id_utilisateur`, `id_favori`)
VALUES (1, 3);

INSERT INTO `hungrybirds`.`favoris` (`id_utilisateur`, `id_favori`)
VALUES (2, 1);

INSERT INTO `hungrybirds`.`favoris` (`id_utilisateur`, `id_favori`)
VALUES (2, 3);

INSERT INTO `hungrybirds`.`favoris` (`id_utilisateur`, `id_favori`)
VALUES (3, 4);

INSERT INTO `hungrybirds`.`ticket` (`id_expediteur`, `id_destinataire`, `en_tete`, `corps`)
VALUES (1, 4, 'Premier essai', 'Bonjour, ceci est un test');

INSERT INTO `hungrybirds`.`ticket` (`id_expediteur`, `id_destinataire`, `en_tete`, `corps`)
VALUES (2, 3, 'Second essai', 'Bonjour, ceci est un test');

INSERT INTO `hungrybirds`.`commande` (`id_utilisateur`,`id_panier`, `date`, `ancien_solde`, `prix`)
VALUES (1, 1, "2021-02-01", 30.0, 12.5);

INSERT INTO `hungrybirds`.`commande` (`id_utilisateur`,`id_panier`, `date`, `ancien_solde`, `prix`)
VALUES (2, 2, "2021-03-11", 30.0, 7.59);

INSERT INTO `hungrybirds`.`image` (`id_utilisateur`, `image_path`)
VALUES (1, 'C:/images');

INSERT INTO `hungrybirds`.`image` (`id_utilisateur`, `image_path`)
VALUES (2, 'C:/images');

INSERT INTO `hungrybirds`.`image` (`id_utilisateur`, `image_path`)
VALUES (3, 'C:/images');

INSERT INTO `hungrybirds`.`image` (`id_utilisateur`, `image_path`)
VALUES (4, 'C:/images');
