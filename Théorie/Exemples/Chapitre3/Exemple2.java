package Exemples.Chapitre3;

import java.sql.*;

public class Exemple2 {
    public static void main(String[] args) {
        Connection con = null;
        try {
            // Avant Java 7
            // Chargement du pilote JDBC pour MariaDB
            //Class.forName("org.mariadb.jdbc.Driver");
            
            // Etablissement de la connexion
            con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/biblio4_prof", "new_user", "password1");
            
            // Création d'un objet Statement pour exécuter une requête de lecture
            Statement stmt = con.createStatement();

            String who = System.console().readLine("Nom du lecteur à rechercher:");

            // Exécution d'une requête de lecture
            // et récupération du résultat dans un objet ResultSet
            String query = "SELECT * FROM lecteur WHERE nom LIKE '%"+who+"%';";
            ResultSet rs = stmt.executeQuery(query);    
            
            // Parcours du résultat
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom= rs.getString("nom");
                String prenom = rs.getString("prenom");
                System.out.println(nom + " ("+id+") \t\t" + prenom);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        finally {
            if (con != null)
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }
}
