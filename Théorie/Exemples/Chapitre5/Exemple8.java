package Exemples.Chapitre5;

import java.sql.*;

import Exemples.dal.DB;

public class Exemple8 {
    public static void main(String[] args) {
        System.out.println("DELETE du dernier lecteur");
        System.out.println("=========================");
        // Récupére le dernier id de la table lecteur dans un objet Integer qui peut être null
        Integer lastId = DB.getLastId("lecteur");
        deleteLecteur(lastId);
    }

    private static void deleteLecteur(Integer lastId) {
        try (DB db = new DB()) {
            if (lastId != null) {
                System.out.println("Dernier id: " + lastId);
                Connection con = db.getConnection();
                // Création d'un objet PreparedStatement pour exécuter une requête d'INSERT
                PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM lecteur WHERE id = ?");

                // Définir les paramètres de la requête
                preparedStatement.setInt(1, lastId);

                // Exécuter la requête
                int rowsDeleted = preparedStatement.executeUpdate();

                // Vérifier le nombre de lignes supprimées
                if (rowsDeleted > 0) {
                    System.out.println("Le lecteur a été supprimé avec succès.");
                } else {
                    System.out.println("Le lecteur avec l'id "+lastId+" n'a pas été supprimé !");
                }
            }
            else {
                System.out.println("Veuillez entrer un entier non nul.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}