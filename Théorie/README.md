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
Je vais vous coller un code Java que je vais vous expliquer.

<div style="position: relative; padding-left: 3.8em; counter-reset: linenumber;">

```java
import java.sql.*;

public class Exemple1 {
    public static void main(String args[]) {
        try {
            // Chargement du pilote JDBC pour MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Etablissement de la connexion
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pays", "new_user", "password");
            
            // Création d'un objet Statement
            Statement stmt = con.createStatement();


            String code = System.console().readLine("code pays:");
            String query = "SELECT display_order, name, full_name FROM Pays WHERE Id_Pays = " + code;
            System.out.println(query);
            
            // Exécution d'une requête de lecture
            ResultSet rs = stmt.executeQuery(query);
    
            while (rs.next())
                System.out.println(rs.getInt("name") + "\t  " + rs.getString(2) + "\t\t  " + rs.getString(3));

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
```
</div>