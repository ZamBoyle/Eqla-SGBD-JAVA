package Exemples.Chapitre5;

import java.sql.*;

public class Exemple6 {
    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/biblio4_prof", "new_user", "password1")) {
            // Création d'un objet PreparedStatement pour exécuter une requête d'UPDATE
            PreparedStatement pstmt = con.prepareStatement("UPDATE lecteur SET nom= ?, prenom = ? WHERE id = ?");

            System.out.println("Mise à jour du lecteur 2");
            System.out.println("========================");
            // Définition des paramètres de la requête
            // nom = "Piette", prenom = "Johnny", id = 2
            pstmt.setString(1, "Piette");
            pstmt.setString(2, "Johnny");
            pstmt.setInt(3, 2);

            // Exécution d'une requête d'UPDATE
            // et récupération du nombre d'enregistrements modifiés
            int nbEnregistrements = pstmt.executeUpdate();
                
            if(nbEnregistrements > 0)
                System.out.println(nbEnregistrements + " enregistrements modifiés.");    
            else
                System.out.println("Aucun enregistrement modifié.");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}