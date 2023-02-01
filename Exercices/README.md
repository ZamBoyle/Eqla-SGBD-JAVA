# Exercices: Java et mySQL/MariaDB
Créez un projet que vous appelerez ExercicesJava.

## New user & connector
### Exercice1 - Création d'un utilisateur
Vous allez créer un nouvel utilisateur qui se nommera 'new_user2' et ayant comme mot de passe 'secret'.

### Exercice2.txt - Ajout du connecteur MySQL
Dans cet exercice, vous allez ajouter le connector de MySQL comme nous l'avons vu au cours.
Créez un fichier Exercie2.txt et indiquez-y comment vous avez procédé.

### Exercice3.java - Simple SELECT
Comme pour l'exemple vu au cours, veuillez affichez la liste des thèmes présents dans la base de données biblio4_prof.

### Exercice4.java - Simple SELECT

Vous reprendrez le code précédent et maintenant vous mettrez votre code dans une fonction qui s'appelera displayThemes();

Votre méthode main appelera votre méthode displayThemes();

### Exercice5.java - Simple SELECT sans paramètre

Vous utiliserez le code précédent.

Faites une méthode qui s'appelera displayAuteurs(): elle affichera tous les auteurs. Les champs de la table à afficher seront: tous.

Votre méthode main appelera seulement la méthode displayAuteurs();

### Exercice6.java - SELECT avec 1 paramètre

Vous utiliserez le code précédent.

Faites une méthode qui s'appellera displayAuteur: cette méthode recevra un entier qui indique l'id de l'auteur à rechercher.

Votre méthode main demandera l'identifiant (id) de l'auteur et appellera ensuite la méthode displayAuteur avec l'identifiant fournit en paramètre. Les champs de la table à afficher seront: tous.

### Exercice7.java - Factorisation

Vous utiliserez le code précédent.

Comparez vos fonctions displayAuteurs() et displayAuteur. Si vous ne l'avez pas déjà fait, n'y a-t-il pas moyen de factoriser votre code ?

### Exercice8.java - SELECT avec 2 paramètres

Vous utiliserez le code précédent.

Faites une méthode qui s'appellera displayExemplaire: cette méthode recevra en paramètres le rayon et l'état de l'exemplaire. Les champs de la table à afficher seront: id, livre_id, rayon et date_acquisition.

Elle affichera les exemplaires en fonction de ces critères.

Votre méthode main appellera cette méthode en fournissant les paramètres reçu de l'utilisateur.

### Exercice9.java - SELECT avec 2 paramètres

Vous utiliserez le code précédent.

Ici, vous allez modifier la requêtre pour pouvoir afficher le titre du livre au lieu de livre_id.

### Exercice10.java - SELECT avec 2 paramètres

Vous utiliserez le code précédent.

Ajoutez en plus le nom et prénom de l'auteur.

### Exercice11.java - Prepared Statement

Vous utiliserez le code précédent.

Vous allez modifier le code pour qu'il utilise maintenant les requêtes préparées (Prepared Statement).













