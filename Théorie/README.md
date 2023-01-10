# Java et mySQL/MariaDB


## 1. Utilisation de la Bibliothèque MySQL
Pour le moment, vous n'aviez pas de problème de faire un import des librairies du JDK.
Cependant, vous venez d'installer le connecteur java pour MySQL et pour utiliser cette librairie il existe plusieurs méthodes.
### 1.1 Via le GUI de VSCode
C'est la plus facile mais la moins accessible...

Ajouter la librairie directement avec l'interface graphique de vscode. C'est le plus simple et aussi le moins accessible. En-dessous de l'explorateur de fichiers, vous avez en bas un libellé nommé "JAVA PROJECTS" et un sous-libellé "Referenced Libraries" et à côté de celui-ci vous avez le symbole + qui permet d'ajouter une librairie java à votre projet. Vous devez indiquer où se trouve le .jar du connecteur MySQL par exemple:
1. sous Linux, il se trouve dans le répertoire /usr/share/java/ et dans mon cas, c'est le fichier: mysql-connector-j-8.0.31.jar
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

La chaîne de caractères __"lib/**/*.jar"__ indique que tous les fichiers ayant l'extension **.jar** dans le répertoire lib et ses sous-répertoires sont référencés comme bibliothèques.

Cette liste peut être utilisée, par exemple, pour indiquer au compilateur Java quelles bibliothèques doivent être incluses lors de la compilation du projet. Cela permet d'accéder aux classes contenues dans ces bibliothèques depuis votre code Java.


## 2. Première connexion
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

- **getInt**(int columnIndex) ou (String columnName)  : permet de récupérer la valeur d'une colonne de type INT ou BIGINT en spécifiant l'index/le nom de colonne (commençant à 1).

- **getDouble**(int columnIndex) ou (String columnName)  : permet de récupérer la valeur d'une colonne de type DOUBLE ou FLOAT en spécifiant le nom de colonne/l'index de la colonne.

- **getTimestamp**(int columnIndex) ou (String columnName)  : permet de récupérer la valeur d'une colonne de type TIMESTAMP en spécifiant l'index/le nom de colonne.

- **getBoolean**(String columnIndex) ou (String columnName) : permet de récupérer la valeur d'une colonne de type BOOLEAN en spécifiant l'index/le nom de colonne.

- **getBytes**(int columnIndex) ou (String columnName)  : permet de récupérer la valeur d'une colonne de type BLOB en spécifiant l'index/le nom de colonne.

- **getObject**(int columnIndex) ou (String columnName) : permet de récupérer la valeur d'une colonne quelque soit le type de celle-ci en spécifiant l'index/le nom de colonne.

Toutes ces méthodes lèvent une SQLException si les valeur récupéré ne peuvent être converti au type voulu, par exemple si on récupère une chaine de caractère avec getInt. Il est donc important de gérer cette exception pour eviter les erreurs de runtime.

### 2.6 con.close()
```java
con.close();
```
La méthode close permet de fermer la connexion de l'objet con qui est du type Connection.

### 2.7 ColumnName ou ColumnIndex ?
Comme vous l'avez vu, il est possible de récupérer une valeur String, Int, Boolean, etc. en spécifiant soit le nom de colonne ou bien son index.

Il est a noter que l'utilisation du nom de colonne est plus pratique car il permet d'éviter les erreurs liées à des changements dans l'ordre des colonnes dans la requête SQL. Il est donc conseillé d'utiliser les méthodes qui prennent en paramètre columnName plutôt que les méthodes qui prennent l'index de colonne, cependant parfois pour des raisons de performances ou de simplicité il peut être préférable d'utiliser l'index.