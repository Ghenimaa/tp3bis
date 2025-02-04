-- Initialisation des tables
-- Ce fichier ne peut pas être vide

INSERT INTO Personne (matricule, nom, prenom, poste)
VALUES (1, 'Duval', 'Sophie', 'Cheffe de projet');

INSERT INTO Personne (matricule, nom, prenom, poste)
VALUES (2, 'David', 'Luc', 'Développeur');
INSERT INTO Personne (matricule, nom, prenom, poste)
VALUES (3, 'Calon', 'Malory', 'Designer');



INSERT INTO Projet (code, nom, debut, fin)
VALUES (101, 'Projet alpha', '2025-01-01', null);


INSERT INTO Participation (id, role, pourcentage, personne_id, projet_id)
VALUES (5, 'Cheffe sur Projet', 100, 1, 101);

INSERT INTO Participation (id, role, pourcentage, personne_id, projet_id)
VALUES (4, 'Développeur', 40, 2, 102);
INSERT INTO Participation (id, role, pourcentage, personne_id, projet_id)
VALUES (6, 'Designer', 60, 3, 103);