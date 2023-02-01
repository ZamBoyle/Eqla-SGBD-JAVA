package Exemples.Chapitre5;

import java.sql.*;
import java.util.Scanner;

import Exemples.user.Input;

public class Exemple8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("DELETE d'un lecteur");
        System.out.println("==================");
        Integer lastId = getLastId("lecteur");
        
        if(lastId != null){
            System.out.println("Dernier id: " + lastId);
            System.out.println("Id à supprimer: ");
            deleteLecteur(lastId);
        }
    }

    private static void deleteLecteur(Integer id) {
        try (Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/biblio4_prof", "new_user", "password1")) {
            // Création d'un objet PreparedStatement pour exécuter une requête d'INSERT
            PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM lecteur WHERE id = ?");

            // Définir les paramètres de la requête
            preparedStatement.setInt(1, id);

            // Exécuter la requête
            int rowsDeleted = preparedStatement.executeUpdate();

            // Vérifier le nombre de lignes supprimées
            if (rowsDeleted > 0) {
                System.out.println("Le lecteur a été supprimé avec succès.");
            } else {
                System.out.println("Aucun lecteur n'a été trouvé avec l'ID spécifié.");
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static Integer getLastId(String table) {
        try (Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/biblio4_prof", "new_user", "password1")) {

            PreparedStatement pstmt = con.prepareStatement("SELECT MAX(id) FROM " + table);

            ResultSet rs = pstmt.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}