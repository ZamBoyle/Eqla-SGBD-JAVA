package Exemples.Chapitre2;

import java.io.Console;
import java.sql.*;

public class Exemple2 {
    public static void main(String[] args) {
        try {
            // Chargement du pilote JDBC pour MariaDB
            Class.forName("org.mariadb.jdbc.Driver");
            
            // Etablissement de la connexion
            Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/biblio4_prof", "new_user", "password1");
            
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