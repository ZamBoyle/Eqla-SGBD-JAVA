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
        Connection con = null;
        try {
            // Chargement du pilote JDBC pour MariaDB
            Class.forName("org.mariadb.jdbc.Driver");
            
            // Etablissement de la connexion
            con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/biblio4_prof", "new_user", "password1");
            
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
