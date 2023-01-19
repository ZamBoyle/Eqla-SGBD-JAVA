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
