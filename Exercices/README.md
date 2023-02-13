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

### Exercice12.java - UPDATE Auteur 

#### 1. Classe Auteur
Vous allez créer une classe qui s'appellera Auteur.
- Elle sera dans un fichier nommé Auteur.java
- Cette classe aura les mêmes attributs que la table auteur.
- Ces attributs seront privés et peuvent être null: voir dans mysql la description de la table auteur.
- Elle autorisera l'accès aux attributs (getters et setters).
- Elle aura un constructeur par défaut.
- Elle aura un constructeur qui prendra en paramètres tous les attributs de la classe.
- Elle aura une méthode toString() qui affichera le nom et prénom de l'auteur.

#### 2. Méthode updateAuteur (Auteur auteur)
Faites une méthode qui s'appellera updateAuteur: cette méthode recevra en paramètres un objet de type Auteur. Elle mettra à jour l'auteur dans la table auteur.

#### 3. Méthode displayAuteurs(String nom)
Faites une méthode qui s'appellera displayAuteurs: cette méthode recevra en paramètres un nom d'auteur.

Elle affichera tous les auteurs correspondant à ce nom: id, nom, prénom, date_naissance, nationalité.

#### 4. Méthode displayAuteur(int id)
Vous utiliserez la méthode de l'exercice 6 pour afficher un auteur en fonction de son id.

#### 5. Méthode displayAuteur(Auteur auteur)
<!-- Vous appellerez la méthode displayAuteur(int id) en lui passant l'id de l'auteur. -->
Vous afficherez les informations de l'auteur.

#### 6. Méthode main(String[] args)
Vous allez modifier la méthode main pour qu'elle fasse ce qui suit:
- Vous demanderez à l'utilisateur de saisir le nom de l'auteur à modifier.
- Vous afficherez ensuite la liste des auteurs correspondant à ce nom (méthode displayAuteurs(String nom)).
- Vous demanderez ensuite à l'utilisateur de saisir l'id de l'auteur à modifier.
- Vous afficherez ensuite les informations de l'auteur (méthode displayAuteur).
- Vous demanderez ensuite à l'utilisateur de saisir les nouvelles informations de l'auteur.
- Vous appellerez ensuite la méthode updateAuteur avec l'objet Auteur créé à partir des informations saisies par l'utilisateur.
- Vous afficherez enfin les informations de l'auteur (méthode displayAuteur(int id)) pour vérifier que l'auteur a bien été modifié.
### Exercice13.java - INSERT Auteur
Vous utiliserez le code précédent.
#### 1. méthode insertAuteur(Auteur auteur)
Faites une méthode qui s'appellera insertAuteur:
- Elle recevra en paramètres un objet de type Auteur.
- Elle insérera l'auteur dans la table auteur.
- Attention, l'id de l'auteur ne doit pas être renseigné: donc l'id sera null dans l'objet de type Auteur.

#### 2. Méthode main(String[] args)
Vous allez modifier la méthode main pour qu'elle fasse ce qui suit:
- Vous demanderez à l'utilisateur de saisir les informations de l'auteur à insérer.
- vous créerez un objet de type Auteur avec ces informations.
- Vous appellerez ensuite la méthode insertAuteur avec l'objet Auteur créé à partir des informations saisies par l'utilisateur.
- Vous afficherez enfin les informations de l'auteur (méthode displayAuteur) pour vérifier que l'auteur a bien été inséré.

### Exercice14.java - DELETE Auteur

Vous utiliserez le code précédent.

#### 1. méthode deleteAuteur(int id)
Faites une méthode qui s'appellera deleteAuteur:
- Elle recevra en paramètres un id d'auteur.
- Elle supprimera l'auteur dans la table auteur.

#### 2. méthode deleteAuteur(Auteur auteur)
Faites une méthode qui s'appellera deleteAuteur:
- Elle recevra en paramètres un objet de type Auteur.
- Elle appellera la méthode deleteAuteur(int id) en lui passant l'id de l'auteur. 
- Attention, l'id de l'auteur doit être renseigné: donc l'id ne sera pas null dans l'objet de type Auteur.

#### 3. Méthode main(String[] args)
Vous allez modifier la méthode main pour qu'elle fasse ce qui suit:
- Vous demanderez à l'utilisateur de saisir le nom de l'auteur à supprimer.
- Vous afficherez ensuite la liste des auteurs correspondant à ce nom (méthode displayAuteurs).
- Vous demanderez ensuite à l'utilisateur de saisir l'id de l'auteur à supprimer.
- Vous afficherez ensuite les informations de l'auteur (méthode displayAuteur).
- Vous appellerez ensuite la méthode deleteAuteur avec l'objet Auteur créé à partir des informations saisies par l'utilisateur.
- Vous afficherez enfin les informations de l'auteur (méthode displayAuteur) pour vérifier que l'auteur a bien été supprimé.


### Exercice15.java

#### 1. Méthode equals

- Elle aura une méthode static equals(Auteur) qui permettra de comparer deux objets de type Auteur: deux auteurs seront considérés comme égaux si tous leurs attributs sont égaux. On verra ensemble comment faire.  
- La méthode equals() retournera un booléen.
- La méthode equals() aura l'anotation @Override.
- Elle ressemblera donc à ceci:
```java
    @Override
    public boolean equals(Object o) {
        // 1. Si l'objet passé en paramètre est null, on retourne false
        // 2. Si l'objet passé en paramètre est l'objet courant, on retourne true
        // 3. Si l'objet passé en paramètre n'est pas de type Auteur, on retourne false
        // 4. Si la classe de l'objet passé en paramètre est différente de la classe de l'objet courant, on retourne false
        // On le fait pour éviter les problèmes de cast et de cette manière:
        // if(getClass() != o.getClass()) return false; // On vérifie que les classes sont les mêmes pour éviter les problèmes de cast 
        // 5. On caste l'objet passé en paramètre en Auteur
        // 6. On compare les attributs de l'objet courant avec ceux de l'objet passé en paramètre
        // 7. Si tous les attributs sont égaux, on retourne true: if(this.nom.equals(auteur.nom) && .....) return true;
        // 8. Sinon, on retourne false
    }
```
Exemple d'utilisation de la méthode equals():
```java
Auteur auteur1 = new Auteur(1, "Dumas", "Alexandre", "1802-07-24", "France");
Auteur auteur2 = new Auteur(1, "Dumas", "Alexandre", "1802-07-24", "France");
Auteur auteur3 = new Auteur(2, "Dumas", "Alexandre", "1802-07-24", "France");
System.out.println(auteur1.equals(auteur2)); // Affichera true
System.out.println(auteur1.equals(auteur3)); // Affichera false
System.out.println(auteur1.equals(auteur1)); // Affichera true
```

#### 3. Méthode getAuteursFromAllExemplaires()
Votre méthode getAuteursFromAllExemplaires() devra retourner une liste d'auteurs de tous les exemplaires de la table exemplaire.

Vous utiliserez la requête suivante:
```sql
SELECT auteur.* FROM exemplaire INNER JOIN livre ON livre.id = exemplaire.livre_id INNER JOIN auteur ON livre.auteur_id = auteur.id
```
La précédente requête n'est pas optimale (on aurait dû utiliser DISTINCT) car elle retourne des doublons d'auteurs. En Java, vous devrez donc faire en sorte que la liste retournée ne contienne pas de doublons d'auteurs.

Pour cela, vous utiliserez la contains(Auteur) de votre liste d'auteurs. Si l'auteur n'est pas dans la liste, vous l'ajouterez. Sinon, vous ne l'ajouterez pas.

La méthode contains appellera votre méthode equals() pour comparer les auteurs.

#### 4. Méthode displayAuteurs()
Votre méthode displayAuteurs() devra afficher la liste des auteurs passée en paramètre.

#### 5. Méthode main(String[] args)
La méthode main devra faire ce qui suit:
- Vous recevrez la liste des auteurs de tous les exemplaires sans doublons (méthode getAuteursFromAllExemplaires).
- Vous afficherez ensuite la liste des auteurs (méthode displayAuteurs).
- Vous afficherez ensuite le nombre d'auteurs retournés.






