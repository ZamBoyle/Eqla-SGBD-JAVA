package Exemples.Chapitre2;

import java.sql.*;

public class Exemple1 {
    public static void main(String[] args) {
        Connection con = null;
        try {
            // Avant Java 7
            // Chargement du pilote JDBC pour MySQL
<<<<<<< HEAD
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("org.mariadb.jdbc.Driver");
            
            // Etablissement de la connexion
            //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblio4_prof", "new_user", "password1");
            Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/biblio4_prof", "new_user", "password1");
            
=======
            // Class.forName("com.mysql.cj.jdbc.Driver");

            // Avant Java 7
            // Chargement du pilote JDBC pour MariaDB
            //Class.forName("org.mariadb.jdbc.Driver");

            // Etablissement de la connexion
            // Si MySQL Connector
            // Connection con =
            // DriverManager.getConnection("jdbc:mysql://localhost:3306/biblio4_prof",
            // "new_user", "password1");

            // Si MariaDB Connector
            con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/biblio4_prof", "new_user", "password1");

>>>>>>> 3aeba16 (Updates)
            // Création d'un objet Statement pour exécuter une requête de lecture
            Statement stmt = con.createStatement();

            // Exécution d'une requête de lecture
            // et récupération du résultat dans un objet ResultSet
<<<<<<< HEAD
            String query = "SELECT * FROM lecteur;";
            ResultSet rs = stmt.executeQuery(query);    
            
=======
            String query = "SELECT * FROM lecteur";
            ResultSet rs = stmt.executeQuery(query);

>>>>>>> 3aeba16 (Updates)
            // Parcours du résultat
            while (rs.next()) {
                System.out.println(rs.getString("nom") + "\t\t" + rs.getString("prenom"));
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (con != null)
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }
}