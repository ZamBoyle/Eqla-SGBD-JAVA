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
    "java.project.referencedLibraries": [
        "lib/**/*.jar",
        "/usr/share/java/mysql-connector-j-8.0.31.jar"
    ]
```
Evidemment modifiez cette entrée en fonction du chemin sur votre ordinateur et la version de votre connecteur.

La chaîne de caractères __"lib/\*\*/*.jar"__ indique que tous les fichiers ayant l'extension **.jar** dans le répertoire **lib** et **ses sous-répertoires** sont référencés comme bibliothèques.

Cette liste peut être utilisée, par exemple, pour indiquer au compilateur Java quelles bibliothèques doivent être incluses lors de la compilation du projet. Cela permet d'accéder aux classes contenues dans ces bibliothèques depuis votre code Java.


## 2. Première connexion - simple SELECT
Nous allons nous connecter à notre base de données locale biblio4_prof.

Nous allons faire un __SELECT * from lecteur__ et afficher le résultat:
```java
import java.sql.*;

public class Exemple1 {
    public static void main(String[] args) {
        try {
            // Chargement du pilote JDBC pour MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Etablissement de la connexion
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblio4_prof", "new_user", "password");
            
            // Création d'un objet Statement pour exécuter une requête de lecture
            Statement stmt = con.createStatement();

            // Exécution d'une requête de lecture
            // et récupération du résultat dans un objet ResultSet
            String query = "SELECT * FROM lecteur";
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
```java
Class.forName("com.mysql.cj.jdbc.Driver");
```
La méthode **forName** appartient à la classe **Class** qui est une classe de base de la **JVM**. 

**Class.forName("com.mysql.cj.jdbc.Driver")** est utilisé pour charger la classe **Driver** en mémoire, ce qui permet de l'utiliser pour établir une connexion à la base de données. 

Cela va également appeler le bloc statique de la classe, qui permet de déclencher la régistration du pilote auprès de **DriverManager**.


### 2.2 getConnection / Connection
```java
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblio4_prof", "new_user", "password");
```

Cette instruction crée une nouvelle instance de la classe **Connection**, c'est à dire **con**, en utilisant la méthode statique **getConnection()** de la classe **DriverManager**. La méthode **getConnection()** prend trois paramètres :

1. Le premier paramètre est une chaîne de caractères qui spécifie l'URL de la base de données. Dans ce cas, l'URL de la base de données est "**jdbc:mysql://localhost:3306/biblio4_prof**", ce qui signifie que la base de données est une base de données **MySQL** qui est hébergée **localement** sur le port **3306** et le nom de la base de données est **biblio4_prof**.

2. Le deuxième paramètre est une chaîne de caractères qui spécifie le nom d'utilisateur à utiliser pour se connecter à la base de données. Dans ce cas, le nom d'utilisateur est "**new_user**".

3. Le troisième paramètre est une chaîne de caractères qui spécifie le mot de passe à utiliser pour se connecter à la base de données. Dans ce cas, le mot de passe est "**password**".

En résumé cette instruction crée une connection JDBC (Java DataBase Connectivity) à une base de données MySQL, en utilisant un utilisateur nommé "**new_user**" et un mot de passe "**password**" pour se connecter à la base de données **biblio4_prof** qui est hébergée **localement**.

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

- **getInt**(int columnIndex) ou (String columnName)  : permet de récupérer la valeur d'une colonne de type INT ou BIGINT en spécifiant l'index(commençant à 1)/le nom de colonne.

- **getDouble**(int columnIndex) ou (String columnName)  : permet de récupérer la valeur d'une colonne de type DOUBLE ou FLOAT en spécifiant le nom de colonne/l'index de la colonne.

- **getTimestamp**(int columnIndex) ou (String columnName)  : permet de récupérer la valeur d'une colonne de type TIMESTAMP en spécifiant l'index/le nom de colonne.

- **getBoolean**(String columnIndex) ou (String columnName) : permet de récupérer la valeur d'une colonne de type BOOLEAN en spécifiant l'index/le nom de colonne.

- **getBytes**(int columnIndex) ou (String columnName)  : permet de récupérer la valeur d'une colonne de type BLOB en spécifiant l'index/le nom de colonne.

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
import java.sql.*;

public class Exemple1 {
    public static void main(String[] args) {
        try {
            // Chargement du pilote JDBC pour MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Etablissement de la connexion
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblio4_prof", "new_user", "password");
            
            // Création d'un objet Statement pour exécuter une requête de lecture
            Statement stmt = con.createStatement();

            String who = System.console.readLin("Nom du lecteur à rechercher:");

            // Exécution d'une requête de lecture
            // et récupération du résultat dans un objet ResultSet
            String query = "SELECT * FROM lecteur WHERE name LIKE '%"+who+"%';";
            ResultSet rs = stmt.executeQuery(query);    
            
            // Parcours du résultat
            while (rs.next()) {
                String id = rs.getInt("id");
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

#### 3.1.1 Problème de l'injection SQL
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
package Exemples.Chapitre2;

import java.io.Console;
import java.sql.*;

public class Exemple2 {
    public static void main(String[] args) {
        try {
            // Chargement du pilote JDBC pour MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Etablissement de la connexion
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblio4_prof", "new_user", "password");
            
            // Création d'un objet Statement pour exécuter une requête de lecture
            Statement stmt = con.createStatement();

            Console console = System.console();
            String id = console.readLine("Matricule (id) du lecteur:");

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
<!--
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
Si l'utilisateur entre _' OR '1'='1 pour nom, la requête générée sera :
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
import java.util.Scanner;

public class Input {

    public static int getValidInt(String message) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                scanner.close();
                return scanner.nextInt();
            } else {
                System.out.println("Veuillez entrer un nombre valide");
                scanner.next();
            }
        }
    }

    public static int getValidInt(String message, int min, int max) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                int i = scanner.nextInt();
                if (i >= min && i <= max) {
                    scanner.close();
                    return i;
                } else {
                    System.out.println("Veuillez entrer un nombre entre " + min + " et " + max);
                }
            } else {
                System.out.println("Veuillez entrer un nombre valide");
                scanner.next();
            }
        }
    }
}
```
Voici maintenant le code java corrigé:
```java
package Exemples.Chapitre2;

import java.io.Console;
import java.sql.*;

import Exemples.user.Input;

public class Exemple3 {
    public static void main(String[] args) {
        try {
            // Chargement du pilote JDBC pour MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Etablissement de la connexion
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblio4_prof", "new_user", "password");
            
            // Création d'un objet PreparedStatement pour exécuter une requête de lecture
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM lecteur WHERE id=?");

            int id= Input.getValidInt("Matricule (id) du lecteur:");
            stmt.setInt(1, id);

            // Exécution d'une requête de lecture
            // et récupération du résultat dans un objet ResultSet
            ResultSet rs = stmt.executeQuery();    
            
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
PreparedStatement stmt = con.prepareStatement("SELECT * FROM lecteur WHERE id=?");
```
En mettant un point d'interrogation on indique qu'à cet endroit on va utiliser/injecter une valeur d'un certain type.

Donc l'objectif maintenant c'est de remplacer ce ? par un entier. C'est ce que l'on va faire à partir de notre objet stmt en utilisant la méthode **setInt**: 
```java
stmt.setInt(1, id);
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
package Exemples.Chapitre2;

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
            // Chargement du pilote JDBC pour MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Etablissement de la connexion
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblio4_prof", "new_user", "password");
            
            // Création d'un objet PreparedStatement pour exécuter une requête de lecture
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM lecteur WHERE nom LIKE ? AND code_postal=?");

            stmt.setString(1, nameStartWith+"%");
            stmt.setInt(2, code_postal);

            // Exécution d'une requête de lecture
            // et récupération du résultat dans un objet ResultSet
            ResultSet rs = stmt.executeQuery();    
            
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
PreparedStatement stmt = con.prepareStatement("SELECT * FROM lecteur WHERE nom LIKE ? AND code_postal=?");
```
Les deux points d'interrogations seront remplacés par:
```java
stmt.setString(1, nameStartWith+"%");
stmt.setInt(2, code_postal);
```

### 4. Informations de connexion
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
    public final static String PASS = "password";
}
```
Si on a fait le bon import, on 
```java
// Etablissement de la connexion
Connection con = DriverManager.getConnection(DB.DB_URL, DB.USER, DB.PASS);

// Création d'un objet PreparedStatement pour exécuter une requête de lecture
PreparedStatement stmt = con.prepareStatement("SELECT * FROM lecteur WHERE nom LIKE ? AND code_postal=?");
```
Ou bien une classe avec des propriétés:
```java
package Exemples.dal;

public class DB {
    private  String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private  String DB_URL = "jdbc:mysql://localhost:3306/biblio4_prof";
    private  String USER = "new_user";
    private  String PASS = "password";

    public DB() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            throw new ClassCastException("Impossible de charger le pilote JDBC pour MySQL");
        }
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
Ici dans le constructeur j'ai directement chargé le driver mysql avec Class.forName.
J'ai directement affecté des valeurs aux propriétés mais on aurait pu les définir dans le constructeur.

Pour l'utiliser:
```java
DB db = new DB();
// Etablissement de la connexion
Connection con = DriverManager.getConnection(db.getDB_URL, db.getUSER, db.getPASS);

// Création d'un objet PreparedStatement pour exécuter une requête de lecture
PreparedStatement stmt = con.prepareStatement("SELECT * FROM lecteur WHERE nom LIKE ? AND code_postal=?");
```

Ou pourrait aussi faire en sorte que notre classe DB gère la connexion:
```java
package Exemples.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB implements AutoCloseable{
    private  String DB_URL = "jdbc:mysql://localhost:3306/biblio4_prof";
    private  String USER = "new_user";
    private  String PASS = "password";

    private Connection con;

    public DB() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Etablissement de la connexion
            try {
                con = DriverManager.getConnection(DB_URL, USER, PASS);
            } catch (SQLException e) {
                throw new RuntimeException("Impossible de se connecter à la base de données:"+e.getMessage());
            }

        } catch (ClassNotFoundException e) {
            throw new ClassCastException("Impossible de charger le pilote JDBC pour MySQL:"+e.getMessage());
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
Cette classe implémente une interface **AutoCloseable**: c'est une classe à laquelle on oblige l'implémentation d'une méthode et c'est à nous de définir cette implémentation.

L'interface oblige de définir la méthode **close()**. Ici dans cette méthode, je fermerai la connection qui a été faite dans le constructeur par défaut.


Et voici à quoi ressemblerait notre code final:
```java
package Exemples.Chapitre2;

import java.sql.*;

import Exemples.dal.DB;
import Exemples.user.Input;

public class Exemple5 {
    public static void main(String[] args) {
        String nameStartWith = System.console().readLine("Nom commence par:");
        int code_postal = Input.getValidInt("Code postal:",1000,9990);
        displayLecteurs(nameStartWith, code_postal);
    }

    public static void displayLecteurs(String nameStartWith, int code_postal){
        //On crée notre objet dans le try. L'intérêt c'est qu'à la fin du try, il va tout seul
        //Appeler la méthode close() ==> c'est beau hein ? :-)
        try (DB db = new DB()) {
            // Etablissement de la connexion
            Connection con = db.getConnection();
            
            // Création d'un objet PreparedStatement pour exécuter une requête de lecture
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM lecteur WHERE nom LIKE ? AND code_postal=?");

            stmt.setString(1, nameStartWith+"%");
            stmt.setInt(2, code_postal);

            // Exécution d'une requête de lecture
            // et récupération du résultat dans un objet ResultSet
            ResultSet rs = stmt.executeQuery();    
            
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

Bref faites comme vous voulez mais essayez de centraliser vos informations de connexion. Pour la suite je travaillerai peut-être avec des champs statiques. On verra mon humeur :-)


