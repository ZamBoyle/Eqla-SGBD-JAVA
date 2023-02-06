# Java et mySQL/MariaDB

## 1. Utilisation de la Bibliothèque MySQL

Pour le moment, vous n'aviez pas de problème de faire un import des librairies du JDK.
Cependant, vous venez d'installer le connecteur java pour MySQL et pour utiliser cette librairie il existe plusieurs méthodes.

### 1.1 Via le GUI de VSCode

C'est la plus facile mais la moins accessible...

Ajouter la librairie directement avec l'interface graphique de vscode. C'est le plus simple et aussi le moins accessible. En-dessous de l'explorateur de fichiers, vous avez en bas un libellé nommé "JAVA PROJECTS" et un sous-libellé "Referenced Libraries" et à côté de celui-ci vous avez le symbole + qui permet d'ajouter une librairie java à votre projet. Vous devez indiquer où se trouve le .jar du connecteur MySQL par exemple:

1. sous Linux, il se trouve dans le répertoire **/usr/share/java/** et dans mon cas, c'est le fichier: **mysql-connector-j-8.0.31.jar** . La version peut bien entendu être différente.
2. sous Windows:
3. sous Mac:

### 1.2 Via le fichier settings.json

On peut avoir le même résultat en modifiant un fichier ce qui est évidemment plus accessible.

Dans le répertoire **.vscode** qui est généralement caché, modifier le fichier **settings.json** et ajouter une entrée dans '**java.project.referencedLibraries**':

```json
{
  "java.project.referencedLibraries": [
    "lib/**/*.jar",
    "/usr/share/java/mysql-connector-j-8.0.31.jar"
  ]
}
```

Evidemment modifiez cette entrée en fonction du chemin sur votre ordinateur et la version de votre connecteur.

La chaîne de caractères **"lib/\*\*/\*.jar"** indique que tous les fichiers ayant l'extension **.jar** dans le répertoire **lib** et **ses sous-répertoires** sont référencés comme bibliothèques.

Cette liste peut être utilisée, par exemple, pour indiquer au compilateur Java quelles bibliothèques doivent être incluses lors de la compilation du projet. Cela permet d'accéder aux classes contenues dans ces bibliothèques depuis votre code Java.

## 2. Première connexion - simple SELECT

Nous allons nous connecter à notre base de données locale biblio4_prof.

Nous allons faire un **SELECT \* from lecteur** et afficher le résultat:

```java
import java.sql.*;

public class Exemple1 {
    public static void main(String[] args) {
        try {
            // Avant Java 7
            // Chargement du pilote JDBC
            // Class.forName("com.mysql.cj.jdbc.Driver"); //MySQL
            Class.forName("org.mariadb.jdbc.Driver"); //MariaDB

            // Etablissement de la connexion
            //Si MySQL Connector
            //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblio4_prof", "new_user", "password1");

            //Si MariaDB Connector
            Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/biblio4_prof", "new_user", "password1");

            // Création d'un objet Statement pour exécuter une requête de lecture
            Statement stmt = con.createStatement();

            // Exécution d'une requête de lecture
            // et récupération du résultat dans un objet ResultSet
            String query = "SELECT * FROM lecteur;";
            ResultSet rs = stmt.executeQuery(query);

            // Parcours du résultat
            while (rs.next()) {
                System.out.println(rs.getString("nom") + "\t\t" + rs.getString("prenom"));
            }

            // Fermeture de la connexion
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
```

### 2.1 forName

#### 2.1.1 Avant JAVA 7

```java
Class.forName("com.mysql.cj.jdbc.Driver");
```

La méthode **forName** appartient à la classe **Class** qui est une classe de base de la **JVM**.

**Class.forName("com.mysql.cj.jdbc.Driver")** est utilisé pour charger la classe **Driver** en mémoire, ce qui permet de l'utiliser pour établir une connexion à la base de données.

Cela va également appeler le bloc statique de la classe, qui permet de déclencher la régistration du pilote auprès de **DriverManager**.

#### 2.1.2 Après JAVA 7

A partir de Java 7, le driver JDBC de MariaDB est automatiquement chargé à l'exécution grâce à la spécification JDBC 4.0.

Cela signifie qu'il n'est plus nécessaire d'utiliser la méthode Class.forName pour charger manuellement le pilote.

Le pilote est désormais enregistré auprès de l'API JDBC lors de son installation, de sorte qu'il est automatiquement détecté et chargé par le système lorsqu'une application tente de se connecter à une base de données MariaDB.

### 2.2 getConnection / Connection

```java
Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/biblio4_prof", "new_user", "password1");
```

Cette instruction crée une nouvelle instance de la classe **Connection**, c'est à dire **con**, en utilisant la méthode statique **getConnection()** de la classe **DriverManager**. La méthode **getConnection()** prend trois paramètres :

1. Le premier paramètre est une chaîne de caractères qui spécifie l'URL de la base de données. Dans ce cas, l'URL de la base de données est "**jdbc:mysql://localhost:3306/biblio4_prof**", ce qui signifie que la base de données est une base de données **MySQL** qui est hébergée **localement** sur le port **3306** et le nom de la base de données est **biblio4_prof**.

2. Le deuxième paramètre est une chaîne de caractères qui spécifie le nom d'utilisateur à utiliser pour se connecter à la base de données. Dans ce cas, le nom d'utilisateur est "**new_user**".

3. Le troisième paramètre est une chaîne de caractères qui spécifie le mot de passe à utiliser pour se connecter à la base de données. Dans ce cas, le mot de passe est "**password1**".

En résumé cette instruction crée une connection JDBC (Java DataBase Connectivity) à une base de données MySQL, en utilisant un utilisateur nommé "**new_user**" et un mot de passe "**password1**" pour se connecter à la base de données **biblio4_prof** qui est hébergée **localement**.

### 2.3 createStatement / Statement

```java
Statement stmt = con.createStatement();
```

Dans cette instruction, nous utilisons la méthode **createStatement()** de l'objet **con** pour créer un objet **Statement**.

**con** est supposé être une instance d'une classe qui implémente l'interface **java.sql.Connection**, qui est utilisée pour établir une connexion à une base de données. La méthode **createStatement()** de l'interface **Connection** crée un objet **Statement** qui peut être utilisé pour exécuter des commandes SQL sur la base de données connectée.

**stmt** est une variable de type **Statement**, qui est utilisée pour stocker l'objet **Statement** créé par la méthode **createStatement()**.
Cette variable sera utilisée pour l'envoi des requêtes à la base de données

### 2.4 executeQuery / ResultSet

```java
String query = "SELECT * FROM lecteur";
ResultSet rs = stmt.executeQuery(query);
```

Cette ligne de code est utilisée pour exécuter une requête SQL sur une base de données à partir d'un objet **Statement** dans un programme Java.

**stmt** est un objet **Statement** qui est utilisé pour envoyer des commandes SQL à la base de données. **executeQuery(query)** est une méthode de l'objet Statement qui prend une chaîne de requête SQL en argument et l'exécute sur la base de données. La méthode renvoie un objet **ResultSet** qui contient les résultats de la requête.

**ResultSet** est une interface qui représente un jeu de résultats d'une requête SQL. Il contient les lignes de résultats de la requête, et chaque colonne dans chaque ligne peut être accédée par un nom ou un index.

En résumer, la ligne de code ci-dessus exécute une requête SQL donné sur la base de données via l'objet **stmt**, puis il retourne les résultats dans un objet **ResultSet** qui est appelé **rs**.

### 2.5 rs.next / getString / getInt / etc...

```java
while (rs.next()) {
    System.out.println(rs.getString("nom") + "\t\t" + rs.getString("prenom"));
}
```

La méthode **next()** de l'objet **ResultSet** permet de parcourir les lignes du résultat d'une requête. Elle retourne true si il y a une ligne suivante, et false si il n'y en a pas. Dans le code que vous avez fourni, la boucle while continue de tourner tant qu'il y a des lignes suivantes dans le **ResultSet**.

A l'intérieur de la boucle, les méthodes **getString()** sont utilisées pour récupérer les valeurs de certaines colonnes spécifiques (dans ce cas nom et prenom du lecteur) de la ligne actuelle dans le **ResultSet**. Ces valeurs sont ensuite affichées à l'aide de la méthode **println()** de l'objet **System.out**.

Il est important de noter que le curseur est avancé d'une ligne à chaque tour de boucle grâce à la méthode next(). Ainsi dans la première boucle on récupère la première ligne, dans la deuxième boucle la seconde ligne, etc.

Il existe plusieurs méthodes pour récupérer des valeurs à partir d'un objet ResultSet en fonction du type de données de la colonne. Voici quelques exemples :

- **getInt**(int columnIndex) ou (String columnName) : permet de récupérer la valeur d'une colonne de type INT ou BIGINT en spécifiant l'index(commençant à 1)/le nom de colonne.

- **getDouble**(int columnIndex) ou (String columnName) : permet de récupérer la valeur d'une colonne de type DOUBLE ou FLOAT en spécifiant le nom de colonne/l'index de la colonne.

- **getTimestamp**(int columnIndex) ou (String columnName) : permet de récupérer la valeur d'une colonne de type TIMESTAMP en spécifiant l'index/le nom de colonne.

- **getBoolean**(String columnIndex) ou (String columnName) : permet de récupérer la valeur d'une colonne de type BOOLEAN en spécifiant l'index/le nom de colonne.

- **getBytes**(int columnIndex) ou (String columnName) : permet de récupérer la valeur d'une colonne de type BLOB en spécifiant l'index/le nom de colonne.

- **getObject**(int columnIndex) ou (String columnName) : permet de récupérer la valeur d'une colonne quelque soit le type de celle-ci en spécifiant l'index/le nom de colonne.

Toutes ces méthodes lèvent une **SQLException** si les valeur récupéré ne peuvent être converti au type voulu, par exemple si on récupère une chaine de caractère avec getInt. Il est donc important de gérer cette exception pour eviter les erreurs de runtime.

### 2.6 con.close()

```java
con.close();
```

La méthode close permet de fermer la connexion de l'objet con qui est du type Connection.

### 2.7 ColumnName ou ColumnIndex ?

Comme vous l'avez vu, il est possible de récupérer une valeur String, Int, Boolean, etc. en spécifiant soit le nom de colonne ou bien son index.

L'utilisation du nom de colonne est plus pratique car il permet d'éviter les erreurs liées à des changements dans l'ordre des colonnes dans la requête SQL. Il est donc conseillé d'utiliser les méthodes qui prennent en paramètre columnName plutôt que les méthodes qui prennent l'index de colonne, cependant parfois pour des raisons de performances ou de simplicité il peut être préférable d'utiliser l'index.

## 3. SELECT avec paramètre(s)

Notre précédent exemple nous permet d'avoir la liste de tous les Lecteurs. Ce qui peut faire vraiment beaucoup si nous avons par exemple 10000 lecteurs.

On peut par exemple rechercher un lecteur en fonction de son numéro de lecteur qui sera ici le champ id.

### 3.1 Méthode risquée - permettant les injections SQL

Voyons comment en reprenant notre code mais en ajoutant une entrée à l'utilisateur le nom d'un lecteur par exemple 'Dupont': ahhh les Dupont... ;-)

```java
package Exemples.Chapitre3;

import java.sql.*;

public class Exemple2 {
    public static void main(String[] args) {
        try {
            // Etablissement de la connexion
            Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/biblio4_prof", "new_user", "password1");

            // Création d'un objet Statement pour exécuter une requête de lecture
            Statement stmt = con.createStatement();

            String who = System.console().readLine("Nom du lecteur à rechercher:");

            // Exécution d'une requête de lecture
            // et récupération du résultat dans un objet ResultSet
            String query = "SELECT * FROM lecteur WHERE name LIKE '%"+who+"%';";
            ResultSet rs = stmt.executeQuery(query);

            // Parcours du résultat
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom= rs.getString("nom");
                String prenom = rs.getString("prenom");
                System.out.println(nom + " ("+id+") \t\t" + prenom);
            }

            // Fermeture de la connexion
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
```

Alors si on exécute notre code, celui-ci demandera à l'utilisateur le nom du lecteur à rechercher, notre fameux 'Dupont' qui a oublié sa carte d'emprunt.

Ensuite, s'afficheront, pour tous les Dupont trouvés, les champs suivants: nom (id) prenom

```console
Dupont (2) Jeanne
```

#### 3.1.1 Problème des caractères spéciaux et de l'injection SQL

Cette partie est très importante car elle va vous permettre des diminuer les risques d'attaque d'injection SQL.

Lors du cours SQL, vous vous rappelez que je vous ai dit qu'il fallait redoubler les apostrophes car si votre chaîne contient un apostrophe SQL "pensera" que vous avez terminé la chaîne.

Nous allons donc faire une méthode doubleQuote qui recevra une chaîne où il fadra redoubler tous les '. Elle renverra une chaîne où les ' seront doublés.

```java
    public static String doubleQuotes(String str){
        return str.replace("'","''");
    }
```

Cependant il y a aussi d'autres caractères à prendre en compte. Voici une méthode plus générique:

```java
public static String escapeSQL(String input) {
    return input.replace("'", "''").replace("\\", "\\\\").replace("%", "\\%").replace("_", "\\_");
}
```

L'appel de la fonction escapeSQL se fera par exemple ainsi:

```java
    who = escapeSQL(who);
    String query = "SELECT * FROM lecteur WHERE name LIKE '%"+who+"%';";
```

Je vais vous montrer les réels danger des injections SQL avec des exemples.

Je vais donc partir du principe que nous n'avons pas de fonctions escapeSQL et doubleQuotes.

Soit le code suivant:

```java
package Exemples.Chapitre3;

import java.io.Console;
import java.sql.*;

public class Exemple3 {
    public static void main(String[] args) {
        try {
            // Etablissement de la connexion
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblio4_prof", "new_user", "password");

            // Création d'un objet Statement pour exécuter une requête de lecture
            Statement stmt = con.createStatement();

            int id= Input.getValidInt("Matricule (id) du lecteur:");
            stmt.setInt(1, id);

            // Exécution d'une requête de lecture
            // et récupération du résultat dans un objet ResultSet
            String query = "SELECT * FROM lecteur WHERE id="+id+";";
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);

            // Parcours du résultat
            while (rs.next()) {
                String nom= rs.getString("nom");
                String prenom = rs.getString("prenom");
                System.out.println(nom + " ("+id+") \t\t" + prenom);
            }

            // Fermeture de la connexion
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
```

Si j'exécute le code il va me demander un id et va faire une recherche SQL.
Si on entre 2 la requête va devenir:

```sql
SELECT * FROM lecteur WHERE id=2;
```

Jusque là, c'est cool. Mais imaginons que je tape comme entrée utilisateur:2 OR 1=1;--
Notre chaîne query va devenir:

```sql
SELECT * FROM lecteur WHERE id=2 OR 1=1;--;
```

Le but de notre requête était de chercher au maximum 1 seul lecteur et ici on a détourné notre requête.

En mettant OR 1=1 notre requête sera toujours vraie. Notre requête va alors afficher TOUS les lecteurs.

Vous avez remarqué que j'ai ajouté --
En SQL le -- est un commentaire donc ça veut dire que tout ce qui est à droite sera ignoré.

Comme vous le voyez c'est très dangereux de faire des requêtes SQL sans les protéger un minimum.

**Apostrophe**:
Si l'utilisateur tape comme entrée: ' OR '1'='1
Notre requête va devenir:

```sql
SELECT * FROM lecteur WHERE nom LIKE '' OR '1'='1'
```

Cette requête va retourner tous les enregistrements de la table lecteur, car la condition '1'='1' est toujours vraie.

**Guillemet**:
Si l'utilisateur entre " OR "1"="1" pour nom, la requête générée sera :

```sql
SELECT * FROM lecteur WHERE nom = "" OR "1"="1"
```

Cette requête va retourner tous les enregistrements de la table lecteur, car la condition "1"="1" est toujours vraie.

**Pourcent**:
Si l'utilisateur entre %' OR '1'='1 pour nom, la requête générée sera :

```sql
SELECT * FROM lecteur WHERE nom LIKE '%' OR '1'='1%'
```

Cette requête va retourner tous les enregistrements de la table lecteur, car la condition '1'='1 est toujours vraie.

**Underscore**:
Si l'utilisateur entre \_' OR '1'='1 pour nom, la requête générée sera :

```sql
SELECT * FROM lecteur WHERE nom LIKE '_' OR '1'='1%'
```

Cette requête va retourner tous les enregistrements de la table lecteur qui ont un nom commençant par un caractère unique, car la condition '1'='1 est toujours vraie.

**Backslash**:
Si l'utilisateur entre \ pour nom, la requête générée sera :

```sql
SELECT * FROM lecteur WHERE nom = '\'
```

Cette requête peut causer un erreur ou des résultats inattendus, car la requête ne peut pas être interprétée correctement en raison de l'utilisation de Backslash.
-->

### 3.2 Méthode sécurisée

Alors on pourrait utiliser notre méthode escapeSQL() mais JAVA a implémenté un méthode qui s'occupe de nettoyer nos chaînes de caractères. Le principe est qu'on va dire que tel paramètre sera de tel type: par exemple int, bool, String, etc. Et JAVA va les nettoyer pour éviter ces injections.

#### 3.2.1 Premier exemple

J'ai créé une méthode getValidInt dans une classe Input pour être certain que l'on entre un entier valide.

```java
package Exemples.user;

import java.util.Scanner;

public class Input {

    public static int getValidInt(String message, Scanner scanner) {
        if (scanner == null)
            scanner = new Scanner(System.in);
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Veuillez entrer un nombre valide");
                scanner.next();
            }
        }
    }

    public static int getValidInt(String message) {
        return getValidInt(message, null);
    }

    public static int getValidInt(String message, int min, int max, Scanner scanner) {
        int input = getValidInt(message, scanner);
        while (input < min || input > max) {
            System.out.println("Veuillez entrer un nombre entre " + min + " et " + max);
            input = getValidInt(message, scanner);
        }
        return input;
    }

    public static int getValidInt(String message, int min, int max) {
        return getValidInt(message, min, max, null);
    }
}
```

Voici maintenant le code java modifié:

```java
package Exemples.Chapitre2;

import java.io.Console;
import java.sql.*;

import Exemples.user.Input;

public class Exemple3 {
    public static void main(String[] args) {
        try {
            // Etablissement de la connexion
            Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/biblio4_prof", "new_user", "password1");

            // Création d'un objet PreparedStatement pour exécuter une requête de lecture
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM lecteur WHERE id=?");

            int id= Input.getValidInt("Matricule (id) du lecteur:");
            pstmt.setInt(1, id);

            // Exécution d'une requête de lecture
            // et récupération du résultat dans un objet ResultSet
            ResultSet rs = pstmt.executeQuery();

            // Parcours du résultat
            while (rs.next()) {
                String nom= rs.getString("nom");
                String prenom = rs.getString("prenom");
                System.out.println(nom + " ("+id+") \t\t" + prenom);
            }

            // Fermeture de la connexion
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
```

Ce qui a changé c'est qu'on utilise maintenant un objet de type **PreparedStatement** et en plus dans on utilise un point d'interrogation dans la requête. Reprenons cette ligne de code:

```java
PreparedStatement pstmt = con.prepareStatement("SELECT * FROM lecteur WHERE id=?");
```

En mettant un point d'interrogation on indique qu'à cet endroit on va utiliser/injecter une valeur d'un certain type.

Donc l'objectif maintenant c'est de remplacer ce ? par un entier. C'est ce que l'on va faire à partir de notre objet stmt en utilisant la méthode **setInt**:

```java
pstmt.setInt(1, id);
```

Cette commande va indiquer que l'on va mettre pour le premier point d'interrogation un entier dont la valeur sera celle de notre variable **id**.

Et donc, il ne sera plus possible d'essayer de faire une injection SQL. Cependant, ici nous avons eu un garde fou avec notre méthode getValidInt. Mais il existe plusieurs méthodes dans la classe PreparedStatement:

- setInt
- setBoolean
- setString
- setFloat
- setDouble
- setDate
- setNull
- setURL

#### 3.2.2 Second exemple - Avec deux paramètres

Imaginez que vous ayez un formulaire de recherche qui permet une recherche multi-critères.

Dans notre cas, on voudrait trouver tous les lecteurs qui commencent par du et qui ont comme code postal 1000
En SQL on ferait:

```sql
SELECT * FROM lecteur WHERE nom LIKE 'du%' AND code_postal=1000;
```

Voici maintenant un code JAVA qui utilisera deux paramètres:

```java
package Exemples.Chapitre3;

import java.sql.*;

import Exemples.user.Input;

public class Exemple4 {
    public static void main(String[] args) {
        String nameStartWith = System.console().readLine("Nom commence par:");
        int code_postal = Input.getValidInt("Code postal:",1000,9990);
        displayLecteurs(nameStartWith, code_postal);
    }

    public static void displayLecteurs(String nameStartWith, int code_postal){
        try {
            // Etablissement de la connexion
            Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/biblio4_prof", "new_user", "password1");

            // Création d'un objet PreparedStatement pour exécuter une requête de lecture
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM lecteur WHERE nom LIKE ? AND code_postal=?");

            pstmt.setString(1, nameStartWith+"%");
            pstmt.setInt(2, code_postal);

            // Exécution d'une requête de lecture
            // et récupération du résultat dans un objet ResultSet
            ResultSet rs = pstmt.executeQuery();

            // Parcours du résultat
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String nom= rs.getString("nom");
                String prenom = rs.getString("prenom");
                System.out.println(nom + " ("+id+") \t\t" + prenom);
            }

            // Fermeture de la connexion
            con.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
```

Notre fonction main demandera à l'utilisateur d'entrer le début du nom et le code postal:

```java
String nameStartWith = System.console().readLine("Nom commence par:");
int code_postal = Input.getValidInt("Code postal:",1000,9990);

displayLecteurs(nameStartWith, code_postal);
```

La fonction displayLecteurs sera exécutée et retournera dans le cas de 'du' comme nom et 1000 comme code postal:

```console
Dupont (2)              Jeanne
Durand (3)              Philip
Dubois (5)              Christophe
```

La fonction displayLecteurs va utiliser un objet du type PreparedStatement:

```java
PreparedStatement pstmt = con.prepareStatement("SELECT * FROM lecteur WHERE nom LIKE ? AND code_postal=?");
```

Les deux points d'interrogations seront remplacés par:

```java
pstmt.setString(1, nameStartWith+"%");
pstmt.setInt(2, code_postal);
```

## 4. Informations de connexion

Comme vous l'avez-vous remarqué on doit tout le temps remettre nos informations de connexion: le nom du driver, le nom de la base de donnée, le login, le mot de passe, etc.

Imaginons que demain, tout change. On change de serveur, on change le nom d'utilisateur, le port, le mot de passe, etc.

On pourrait faire un "remplacer partout" bien bourin mais efficace dans tout le projet.

Mais il est plus intéressant de tout centraliser. C'est que nous allons faire en créant une classe dédiée que nous nommerons DB.

Ici une classe avec des constantes statiques:

```java
package Exemples.dal;

public class DB {
    public final static String DB_URL = "jdbc:mysql://localhost:3306/biblio4_prof";
    public final static String USER = "new_user";
    public final static String PASS = "password1";
}
```

Si on a fait un import Exemples.dal.DB, on peut alors utiliser notre classe et ses méthodes statiques:

```java
// Etablissement de la connexion
Connection con = DriverManager.getConnection(DB.DB_URL, DB.USER, DB.PASS);

// Création d'un objet PreparedStatement pour exécuter une requête de lecture
PreparedStatement pstmt = con.prepareStatement("SELECT * FROM lecteur WHERE nom LIKE ? AND code_postal=?");
```

Ou bien une classe avec des propriétés en lecture seule:

```java
package Exemples.dal;

public class DB {
    private  String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    private  String DB_URL = "jdbc:mariadb://localhost:3306/biblio4_prof";
    private  String USER = "new_user";
    private  String PASS = "password1";

    public DB() {

    }

    public String getJDDBC_DRIVER() {
        return this.JDBC_DRIVER;
    }

    public String getDB_URL() {
        return this.DB_URL;
    }

    public String getUSER() {
        return this.USER;
    }

    public String getPASS() {
        return this.PASS;
    }
}
```

Ici, j'ai affecté des valeurs aux attributs.

Pour l'utiliser:

```java
DB db = new DB();
// Etablissement de la connexion
Connection con = DriverManager.getConnection(db.getDB_URL, db.getUSER, db.getPASS);

// Création d'un objet PreparedStatement pour exécuter une requête de lecture
PreparedStatement pstmt = con.prepareStatement("SELECT * FROM lecteur WHERE nom LIKE ? AND code_postal=?");
```

Ou pourrait aussi faire en sorte que notre classe DB gère la connexion et la ferme dans un try avec ressource:

```java
package Exemples.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB implements AutoCloseable{
    private  String DB_URL = "jdbc:mariadb://localhost:3306/biblio4_prof";
    private  String USER = "new_user";
    private  String PASS = "password1";

    private Connection con;

    public DB() {
        // Etablissement de la connexion
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException("Impossible de se connecter à la base de données:"+e.getMessage());
        }
    }

    public String getDB_URL() {
        return this.DB_URL;
    }

    public String getUSER() {
        return this.USER;
    }

    public String getPASS() {
        return this.PASS;
    }

    public Connection getConnection() {
        return con;
    }

    @Override
    public void close() throws Exception {
        if(this.con != null) {
            this.con.close();
        }
    }
}
```

Cette classe implémente une interface **AutoCloseable**. Une interface nous oblige l'implémentation d'une ou plusieurs méthodes et c'est à nous de définir les implémentations.

L'interface **AutoCloseable** oblige de définir la méthode **close()**. Ici dans cette méthode, je fermerai la connection qui a été faite dans le constructeur par défaut.

Et voici à quoi ressemble notre code final:

```java
package Exemples.Chapitre4;

import java.sql.*;

import Exemples.dal.DB;
import Exemples.user.Input;

public class Exemple5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nameStartWith = System.console().readLine("Nom commence par:", scanner);
        int code_postal = Input.getValidInt("Code postal:",1000,9990, scanner);
        displayLecteurs(nameStartWith, code_postal);
    }

    public static void displayLecteurs(String nameStartWith, int code_postal){
        //On crée notre objet db dans le try. L'intérêt c'est qu'à la fin du try, il va tout seul
        //Appeler la méthode close() ==> c'est beau hein ? :-)
        try (DB db = new DB()) {
            // Etablissement de la connexion
            Connection con = db.getConnection();

            // Création d'un objet PreparedStatement pour exécuter une requête de lecture
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM lecteur WHERE nom LIKE ? AND code_postal=?");

            pstmt.setString(1, nameStartWith+"%");
            pstmt.setInt(2, code_postal);

            // Exécution d'une requête de lecture
            // et récupération du résultat dans un objet ResultSet
            ResultSet rs = pstmt.executeQuery();

            // Parcours du résultat
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String nom= rs.getString("nom");
                String prenom = rs.getString("prenom");
                System.out.println(nom + " ("+id+") \t\t" + prenom);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
```

Bref faites comme vous voulez mais essayez de centraliser vos informations de connexion. Pour la suite je travaillerai peut-être avec des champs statiques ou je prendrai la connection. On verra mon humeur :-)

## 5. Mises à jour

On entend par mises à jour les opérations suivantes:

- INSERT
- UPDATE
- DELETE

Pour une requête de **SELECT** nous utilisions la méthode **executeQuery();** de la classe **PreparedStatement**.
Pour ces 3 opérations, on utilisera la même méthode de la classe **PreparedStatement**, la méthode **executeUpdate();**.

### 5.1 UPDATE

Pour faire un update on utilisera la méthode executeUpdate() sur notre un objet de type PreparedStatement. Cette méthode retournera le nombre d'enregistrements concernés par l'update.

Si l'on veut mettre à jour le prénom et le nom du lecteur ayant l'id 2:

```java
package Exemples.Chapitre5;

import java.sql.*;
import Exemples.dal.DB;

public class Exemple6 {
    public static void main(String[] args) {
        try (DB db = new DB()) {
            Connection con = db.getConnection();
            PreparedStatement pstmt = con.prepareStatement("UPDATE lecteur SET nom= ?, prenom = ? WHERE id = ?");

            System.out.println("Mise à jour du lecteur 2");
            System.out.println("========================");
            // Définition des paramètres de la requête
            // nom = "Piette", prenom = "Johnny", id = 2
            pstmt.setString(1, "Piette");
            pstmt.setString(2, "Johnny");
            pstmt.setInt(3, 2);

            // Exécution d'une requête d'UPDATE
            // et récupération du nombre d'enregistrements modifiés
            int nbEnregistrements = pstmt.executeUpdate();

            if(nbEnregistrements > 0)
                System.out.println(nbEnregistrements + " enregistrements modifiés.");
            else
                System.out.println("Aucun enregistrement modifié.");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
```

Ici rien de magique on fait un UPDATE au lieu d'un SELECT dans le prepareStatement et on utilise uniquement executeUpdate() et on n'a pas besoin évidemment de ResultSet.

En effet, le ResultSet renvoie un jeu de résultats. Ici on reçoit juste un entier pour avoir le nombre d'enregistrements modifiés.

### 5.2 INSERT

Pour plus de facilité et de lecture du code, dans la classe Input, j'ai ajouté une méthode: getValidDate qui permet d'avoir une date correcte au format dd/MM/yyy.

```java
public static java.util.Date getValidDate(String message, Scanner scanner) {
    java.util.Date date = null;
    while (date == null) {
        System.out.print(message);
        String input = scanner.nextLine();

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = format.parse(input);
        } catch (ParseException e) {
            System.out.println("Format de date invalide, veuillez réessayer.");
        }
    }
    return date;
}
```

Voici le code qui permet d'ajouter un lecteur dans notre bibliothèque:

```java
package Exemples.Chapitre5;

import java.sql.*;
import java.util.Scanner;

import Exemples.dal.DB;
import Exemples.user.Input;

public class Exemple7 {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        addLecteur();
    }

    public static void addLecteur(){
        scanner = new Scanner(System.in);
        System.out.println("Ajout d'un lecteur");
        System.out.println("==================");
        try (DB db = new DB()) {
            Connection con = db.getConnection();
            PreparedStatement pstmt = con.prepareStatement(
                    "INSERT INTO lecteur (nom,prenom,date_naissance,adresse,code_postal,num_rue, localite,telephone) VALUES (?,?,?,?,?,?,?,?)");

            // nom
            System.out.print("Nom : ");
            pstmt.setString(1, scanner.nextLine());

            // prenom
            System.out.print("Prenom : ");
            pstmt.setString(2, scanner.nextLine());

            // date de naissance
            java.util.Date date =  Input.getValidDate("Date de naissance :", scanner);
            pstmt.setObject(3, date);

            // adresse
            System.out.print("Adresse : ");
            pstmt.setString(4, scanner.nextLine());

            // num_rue
            pstmt.setInt(5, Input.getValidInt("Numéro d'habitation : ", scanner));
            scanner.nextLine(); // pour vider le buffer: on a lu un int

            // code_postal
            pstmt.setInt(6, Input.getValidInt("Code postal : ", scanner));
            scanner.nextLine(); // pour vider le buffer: on a lu un int

            // localite
            System.out.print("Localité : ");
            pstmt.setString(7, scanner.nextLine());

            // telephone
            System.out.print("Téléphone : ");
            pstmt.setString(8, scanner.nextLine());

            // Exécution d'une requête d'INSERT
            // et récupération du nombre d'enregistrements ajoutés: ici 1 si tout s'est bien passé
            int nbEnregistrements = pstmt.executeUpdate();

            if (nbEnregistrements > 0)
                System.out.println("1 lecteur a été ajouté.");
            else
                System.out.println("Aucun lecteur n'a été ajouté.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
```

### 5.3 DELETE

Pour l'exemple on va supprimer le dernier lecteur inscrit. Ca n'a pas de sens de le supprimer comme ça arbitrairement. C'est juste pour l'exemple: on supprime le dernier lecteur inscrit.

En effet, si le lecteur que nous essayons de supprimer est lié à un emprunt la base de données refusera de le supprimer. Et vous retournera cette erreur:

```
[ WARN] (main) Error: 1451-23000: Cannot delete or update a parent row: a foreign key constraint fails (`biblio4_prof`.`emprunt`, CONSTRAINT `emprunt_ibfk_1` FOREIGN KEY (`lecteur_id`) REFERENCES `lecteur` (`id`))
(conn=41) Cannot delete or update a parent row: a foreign key constraint fails (`biblio4_prof`.`emprunt`, CONSTRAINT `emprunt_ibfk_1` FOREIGN KEY (`lecteur_id`) REFERENCES `lecteur` (`id`))
```

Voici le code java qui supprime le dernier lecteur inscrit. Donc exécutez l'exemple précédent (qui ajout un lecteur), vous pourrez ainsi utiliser ce code sans qu'il ne génère une erreur.

```java
package Exemples.Chapitre5;

import java.sql.*;
import Exemples.dal.DB;

public class Exemple8 {
    public static void main(String[] args) {
        System.out.println("DELETE du dernier lecteur");
        System.out.println("=========================");
        Integer lastId = getLastId("lecteur");

        if(lastId != null){
            System.out.println("Dernier id: " + lastId);
            System.out.println("Id à supprimer: ");
            deleteLecteur(lastId);
        }
    }

    private static void deleteLecteur(Integer id) {
        try (DB db = new DB()) {
            Connection con = db.getConnection();
            // Création d'un objet PreparedStatement pour exécuter une requête d'INSERT
            PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM lecteur WHERE id = ?");

            // Définir les paramètres de la requête
            preparedStatement.setInt(1, id);

            // Exécuter la requête
            int rowsDeleted = preparedStatement.executeUpdate();

            // Vérifier le nombre de lignes supprimées
            if (rowsDeleted > 0) {
                System.out.println("Le lecteur a été supprimé avec succès.");
            } else {
                System.out.println("Aucun lecteur n'a été trouvé avec l'ID spécifié.");
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static Integer getLastId(String table) {
        try (Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/biblio4_prof", "new_user", "password1")) {
            PreparedStatement pstmt = con.prepareStatement("SELECT MAX(id) FROM " + table);

            ResultSet rs = pstmt.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
```

On va ajouter une fonction getLastId(String table) dans notre classe DB:

```java
    public Integer getLastId(String table) {
        try  {
            PreparedStatement pstmt = con.prepareStatement("SELECT MAX(id) FROM " + table);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
```

### 6. Types primitifs vs Classes objets

En Java, vous avez étudié l'utilisation de différents types primitifs, tels que `int`, `float`, `double`, `long`, `boolean`, etc.

Cependant, lors du traitement de données provenant d'une base de données, il peut arriver que certains champs retournent la valeur `null`. Or, il n'est pas possible de stocker la valeur `null` dans des types primitifs.

Pour gérer correctement ces situations, nous pouvons utiliser les classes objets correspondantes (par exemple `Integer`, `Float`, `Double`, `Long`, `Boolean`, etc.) pour représenter une valeur ou une valeur `null`. Cela nous permet de traiter les données de la base de données de manière plus flexible et de gérer les valeurs `null` avec plus de souplesse dans notre code Java.

Les types primitifs sont représentés par les classes objets correspondantes `Integer`, `Float`, `Double`, `Long`, etc. avec une première lettre en majuscule alors que les types primitifs ont leur première lettre en minuscule.

En utilisant les classes objets, nous avons accès à une variété de méthodes utiles telles que `compareTo()`, `equals()`, etc., qui ne sont pas disponibles avec les types primitifs. C'est pourquoi il est souvent préférable d'utiliser les classes objets plutôt que les types primitifs pour la manipulation de données en JAVA.

```java
//Entier non null
int entier = 5000;
//Entier nullable
Integer entierNullable = null;

//booléen non null
boolean isHappy = true;
//booléen nullable
Boolean dateRetour = null;
```

On pourrait se poser la question:"Mais pourquoi ne pas utiliser tout le temps les classes objets comme Integer, Float, Boolean, etc. à la place des types primitifs ?"

<u>Les types primitifs sont utilisés pour les raisons suivantes</u>:

- `Performances` : Les types primitifs sont plus rapides et consomment moins de mémoire que les classes objets correspondantes.

- `Espace de stockage` : Les types primitifs utilisent moins d'espace de stockage que les classes objets correspondantes, ce qui peut être important pour les applications nécessitant une grande quantité de données.

- `Simplicité` : Les types primitifs sont plus simples à utiliser que les classes objets. Par exemple, vous pouvez utiliser un int directement dans une expression mathématique sans avoir à créer un objet Integer pour le faire.

- `Préférence de programmation` : Certains développeurs préfèrent utiliser les types primitifs pour une question de style de programmation et pour une meilleure lisibilité du code.


### 7. setNull vs setObject vs setXxx

En Java, la méthode setNull de la classe PreparedStatement est utilisée pour définir un paramètre spécifié sur NULL. La méthode setObject est utilisée pour définir un paramètre spécifié sur l'objet Java donné. Les deux méthodes sont utilisées pour lier des valeurs à des paramètres dans une instruction préparée.

Les méthodes setXxx (telles que setInt, setString, setDouble, etc.) sont utilisées pour lier des valeurs à des types de données spécifiques. La méthode utilisée dépend du type de données à lier. Par exemple, setInt est utilisé pour lier une valeur entière, et setString est utilisé pour lier une valeur de chaîne.

Il est généralement recommandé d'utiliser la méthode setObject si le type du paramètre est inconnu ou varie de manière dynamique. Si le type du paramètre est connu, il peut être plus efficace d'utiliser la méthode setXxx pour ce type de données.

Voici un exemple d'utilisation de setNull, setObject et setXxx en Java pour une table de lecteurs d'une bibliothèque :

```java
String sql = "INSERT INTO auteur (nom, prenom, date_naissance, nationalite) VALUES (?, ?, ?, ?)";

try (
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/biblio4_prof", "new_user", "password1");
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
    ) {
    preparedStatement.setString(1, "Doe");
    preparedStatement.setString(2, "John");
    preparedStatement.setObject(3, null);
    preparedStatement.setNull(4, Types.VARCHAR);

    preparedStatement.executeUpdate();
    } catch (SQLException e) {
    e.printStackTrace();
}
```
Notre table auteur autorise les nulls pour les champs date_naissance et nationalite: On peut tant utiliser `setNull` ou `setObject`.

L'utilisation de la méthode `setObject` pour insérer une valeur nulle dans une base de données est plus simple que l'utilisation de la méthode `setNull` car la méthode setObject peut prendre n'importe quel objet en argument, y compris un objet null, tandis que `setNull` nécessite de spécifier le type de données SQL qui sera défini comme nul. Cela peut entraîner des erreurs si le type de données SQL n'est pas correctement spécifié. De plus, en utilisant `setObject`, vous pouvez simplement passer null comme argument sans avoir à spécifier le type de données SQL, ce qui est plus facile et plus concis.

### 8. Les dates

Alors il aurait été super cool de pouvoir utiliser le type `Date` du package `java.util` pour les bases de données: `java.util.Date`.

La classe `Date` du package `java.util` a plusieurs méthodes et le constructeur `new Date(String)` qui sont dépréciés car:

1. Problèmes de fiabilité : La classe `Date` a des problèmes de fiabilité avec la gestion des fuseaux horaires et des décalages de temps.

2. Peu pratique : La classe `Date` est difficile à utiliser pour des tâches courantes telles que la comparaison et la formatage des dates.

3. Incomplète : La classe `Date` ne fournit pas suffisamment de fonctionnalités pour gérer les dates et les heures correctement, ce qui rend nécessaire l'utilisation de bibliothèques tierces.

> Au lieu de la classe `Date`, il est recommandé d'utiliser la classe `LocalDate` ou `Instant` de la bibliothèque Java 8 `java.time` pour les opérations de date et d'heure. Ces classes sont plus fiables, plus pratiques à utiliser et plus complètes que la classe `Date`.

De plus si on essaie de faire un objet Date avec le constructeur: **new Date(String)**. Java indique que ce constructeur est déprécié:"*The constructor Date(String) is deprecatedJava(134217861)*"


On utilise aussi la classe `LocalDate` du package `java.time` pour définir une simple date année, jour mois sans la notion d'heure et de fuseau horaire. Mais il faudra aussi la convertir en Date sql.

Au lieu de cela, il est nécessaire d'utiliser le type `java.sql.Date` pour représenter les valeurs de date dans les bases de données, ce qui nécessite une conversion si vous avez une valeur `java.util.Date` ou `java.time.LocalDate`.

En conclusion, il faut être conscient de ces différences de types pour éviter les erreurs liées aux conversions.

#### 8.1 java.util.Date to java.sql.Date

Pour convertir une valeur de type `java.util.Date` en une valeur de type `java.sql.Date`, vous pouvez utiliser la méthode getTime de la classe `java.util.Date` pour extraire le nombre de millisecondes écoulées depuis le 1er janvier 1970, puis utiliser ce nombre pour initialiser une instance de `java.sql.Date`.

Par exemple, considérons une table Lecteur avec un champ date_naissance de type `java.sql.Date` :

```java
    private static final String DATE_FORMAT = "dd/MM/yyyy";

    public java.sql.Date getSqlDate(String dateStr) throws Exception{
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        java.util.Date dateUtil = null;
        try {
            dateUtil = format.parse(dateStr);
        } catch (ParseException e) {
            throw new Exception("Erreur lors du parse de la chaîne reçue:"+dateStr, e);
        }
        java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
        return dateSql;
    }
```
Dans ce code, **dateUtil** représente une valeur de type `java.util.Date`, et **dateSql** représente une valeur de type `java.sql.Date` qui a été initialisée à partir de la valeur **dateNaissanceUtil**.

Il est important de noter que la conversion implique l'ignorance des informations d'heure et de fuseau horaire présentes dans la valeur `java.util.Date`. Seules les informations de date (année, mois, jour) sont conservées.

En utilisant cette conversion, vous pouvez ensuite utiliser la valeur **dateSql**.

Si le **format.parse(dateStr)** échoue, je retourne une exception/erreur c'est pourquoi dans la définition de la méthode **getSqlDate** j'ai ajouté `thows Exception`. Donc le code appelant la méthode doit être préparé à gérer cette exception. Le code appelant devra donc utiliser un bloc `try-catch`.

Vous noterez que j'ai défini **DATE_FORMAT** comme constante. Si vous utilisez souvent ce format lors de manipulation de dates, vous pourrez directement appeler la constante. Une constante, on l'écrit en lettres majuscules.

#### 8.2 java.util.LocalDate to java.sql.Date
Le code précédent est converti pour utiliser un LocalDate au lieu d'un Date.
```java
private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

public LocalDate getLocalDate(String dateStr) throws Exception {
    LocalDate date = null;
    try {
        date = LocalDate.parse(dateStr, FORMAT);
    } catch (DateTimeParseException e) {
        throw new Exception("Erreur lors du parse de la chaîne reçue: " + dateStr, e);
    }
    return date;
}
```
Concernant ce code, on remarquera:
- la constante statique **FORMAT** du type `DateTimeFormatter` où l'on donne le format/motif(pattern) des dates.
- la méthode statique parse qui accepte une chaîne de caractère et le format de la date.

On doit passer par un `DateTimeFormatter` car notre format des dates est différent de 'yyyy/MM/dd'. Cependant si vous travaillez avec des dates de ce format, vous n'êtes pas obligés de passer par un `DateTimeFormatter` lors de l'appel de la méthode parse. Votre parse pourrait s'écrire tout simplement:
```java
LocalDate date = LocalDate.parse(dateStr);
```
En fait, ce code revient au même que d'écrire ceci:
```java
LocalDate date = LocalDate.parse("2022-01-01", DateTimeFormatter.ISO_LOCAL_DATE);
```

#### 8.3 java.sql.Date to java.util.LocalDate 

#### 8.4 java.sql.Date to java.util.Date 

### 9. Les transactions: Commit & Rollback

#### 8.1 Introduction

Pour le moment, nous avons mis à jour généralement une seule table à la fois.
Il peut arriver que l'on doit écrive dans plusieurs tables en même temps. Et que toutes les écritures doivent être faites sinon on pourrait avoir une incohérence dans la base de données.

Le "**commit**" en Java est utilisé pour enregistrer définitivement les modifications effectuées dans une transaction de la base de données. C'est une opération qui permet de valider les opérations effectuées dans la transaction.

Le "**rollback**" en Java, quant à lui, permet d'annuler les modifications effectuées dans une transaction en cas d'erreur ou de problème. Cela permet de retourner à l'état initial de la base de données avant les modifications.

En utilisant ces deux opérations ensemble, les développeurs peuvent garantir l'intégrité des données dans une base de données en annulant les opérations en cas de problème.

#### 8.2 Auto Commit

En Java, les opérations effectuées sur une base de données sont toujours effectuées dans le cadre d'une transaction. Cependant, lorsqu'un autocommit est défini à true, les transactions sont automatiquement commises après chaque opération, ce qui rend le processus transparent pour l'utilisateur. Cela signifie que les modifications effectuées dans la base de données sont immédiatement enregistrées et rendues permanentes, sans la nécessité d'une action supplémentaire de la part de l'utilisateur pour effectuer un commit explicitement.

Cette approche est souvent utilisée pour des applications simples ou pour des environnements de développement rapide, car elle simplifie le processus d'enregistrement des modifications dans la base de données, mais elle peut aussi présenter des risques pour la cohérence des données si des erreurs surviennent au milieu d'une opération complexe.

En Java, l'autocommit se définit sur un objet de connexion à la base de données, plutôt qu'au niveau de la base de données elle-même. Par défaut, l'autocommit est généralement défini à true, ce qui signifie que les transactions sont automatiquement commises après chaque opération effectuée sur cet objet de connexion.

Si vous souhaitez contrôler manuellement les transactions, vous pouvez définir l'autocommit à false en utilisant la méthode `setAutoCommit()` de l'objet de connexion. Cela vous permettra de contrôler explicitement les opérations de commit et de rollback pour les transactions effectuées sur cet objet de connexion.

Il est important de noter que les différents objets de connexion peuvent avoir des paramètres d'autocommit différents, ce qui permet de contrôler les transactions de manière fine sur une base de données complexe.

#### 8.3 Premier exemple / Ajout d'un couple de lecteurs

Imaginons pour l'exemple que notre bibliothèque est réservée pour les lecteurs en couple. Et que l'inscription d'un lecteur se fait en même que l'inscription de son conjoint.

Pas frapper ! C'est juste un exemple hein ! ;-)

Le code pourrait se présenter de la sorte (je n'utilise plus notre classe DB pour vous remontrer un code complet).

```java

```

#### 8.4 Second exemple / Ajout d'un livre

Nous venons de recevoir un nouveau livre pour notre bibliothèque. Le design de notre bibliothèque indique que nous empruntons non pas un livre mais un exemplaire d'un livre. Donc si nous venons de recevoir un livre non existant dans notre base de données, nous devrons créer le livre ET l'exemplaire ET le thème (informatique). Sinon on ne fera pas la création.

```java
package Exemples.Chapitre6;

import java.sql.*;
import Exemples.dal.DB;

public class Exemple9 {
    public static void main(){
        try(DB db = new DB();){
            Connection con = db.getConnection();
            con.setAutoCommit(false);

            //Livre
            int auteur_id = 461;//Adie Travers
            String titre = "Le Java c'est sympa les gars !";
            String langue = "français";
            int annee_publication = 2023;
            int nombre_pages = 450;
            String code_isbn = "0-9485-6768-6";
            //Exemplaire
            String etat = "neuf";
            String reference ="I-782";
            Date date_acquisition = new java.util.Date();
            String rayon = "RAYON-19";
            boolean est_perdu = false;
            //Theme
            String  theme = "Informatique";
        }
        catch (Exception e) {

        }
    }
}
```


