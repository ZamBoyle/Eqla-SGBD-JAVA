package Exemples.Chapitre5;

import java.sql.*;
import java.util.Scanner;

import Exemples.user.Input;

public class Exemple8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/biblio4_prof", "new_user", "password1")) {
            // Création d'un objet PreparedStatement pour exécuter une requête d'INSERT
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM lecteur WHERE id = ?");

            // nom
            pstmt.setRowId(1, getValidId(2000));


            // Exécution d'une requête de DELETE
            // et récupération du nombre d'enregistrements supprimés
            int nbEnregistrements = pstmt.executeUpdate();
                
            if(nbEnregistrements > 0)
                System.out.println(nbEnregistrements + " enregistrement(s) supprimé(s).");    
            else
                System.out.println("Aucun enregistrement supprimé.");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void askIdentity(PreparedStatement pstmt) {
        //demande à l'utilisateur tous les champs de la table lecteur
        pstmt.setString(1, scanner));
    }
    

        


        
        
    }
}