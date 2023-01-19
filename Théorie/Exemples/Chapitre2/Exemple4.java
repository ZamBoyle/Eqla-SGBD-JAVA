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
