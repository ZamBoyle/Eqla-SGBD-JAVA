package Exemples.Chapitre3;

import java.sql.*;

import Exemples.user.Input;

public class Exemple3 {
    public static void main(String[] args) {
        Connection con = null;
        try {
            // Etablissement de la connexion
            con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/biblio4_prof", "new_user", "password1");
            
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
