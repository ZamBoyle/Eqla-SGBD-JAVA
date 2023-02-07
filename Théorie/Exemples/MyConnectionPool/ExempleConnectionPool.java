package Exemples.MyConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExempleConnectionPool {
    public static void main(String[] args) throws SQLException {
        ConnectionPool pool = new ConnectionPool("jdbc:mariadb://localhost:3306/mydb", "new_user", "password1");
        Connection connection = pool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } finally {
            pool.releaseConnection(connection);
        }
    }
}

/*

public class Main {
    public static void main(String[] args) throws SQLException {
        ConnectionPool connectionPool = new ConnectionPool(
                "jdbc:mysql://host:port/database", "user", "password");
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM table")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    //Traiter les résultats
                }
            }
        }
        connectionPool.releaseConnection(connection);
    }
}


La méthode main crée une instance de ConnectionPool en lui passant les informations de connexion à la base de données. Ensuite, elle appelle la méthode getConnection pour récupérer une connexion du pool.

Ensuite, elle utilise cette connexion pour créer un objet PreparedStatement, spécifiant la requête SQL à exécuter ( ici "SELECT * FROM table"). Elle utilise ensuite le PreparedStatement pour exécuter la requête et récupérer les résultats dans un objet ResultSet. Elle traite les résultats ensuite.

Finalement, elle utilise la méthode releaseConnection pour rendre la connexion au pool afin qu'elle puisse être réutilisée plus tard.

Il est important de noter que les blocs try-with-resources utilisés pour gérer les ressources (statement & resultset) sont introduits à partir de Java 7, ils permettent d'automatiquement fermer les ressources (en utilisant la méthode close) et peuvent remplacer les blocs try-finally pour libérer les ressources. Il est préférable de les utiliser pour éviter les fuites de ressources.

















 Dans cet exemple, le pool de connexions est créé en utilisant l'URL de la base de données, l'identifiant et le mot de passe. Ensuite, dans la méthode main, nous utilisons la méthode getConnection pour récupérer une connexion à partir du pool.
Ensuite, nous créons une PreparedStatement en utilisant la méthode connection.prepareStatement et nous définissons les paramètres de la requête. Enfin, nous exécutons la requête en utilisant la méthode executeQuery qui renvoie un ResultSet que nous pouvons parcourir pour afficher les résultats de la requête.

Il est important de noter que la méthode executeQuery lève une exception SQLException en cas d'erreur. Il est donc important de la gérer. Il est également important de rappeler que cette connexion doit être relâchée avec la méthode releaseConnection pour être réutilisée par le pool. C'est pourquoi l'utilisation d'un bloc try-finally est utilisé pour s'assurer que la méthode releaseConnection est appelée même si une exception est levée.

Il est à noter que cet exemple est donné à titre d'exemple simple, dans une application ré
 */