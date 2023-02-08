package Exemples.Chapitre9;

import java.sql.*;
import Exemples.dal.DB;

public class Exemple12 {
    public static void main(String[] args) throws Exception{
        Connection con = null;
        try (DB db = new DB()) {
            con = db.getConnection();
            con.setAutoCommit(false);

            //Livre - On suppoera que l'utilisateur a déjà rentré les données suivantes
            int auteur_id = 461;//Adie Travers
            String titre = "Le Java c'est sympa les gars !";
            String langue = "français";
            int annee_publication = 2023;
            int nombre_pages = 450;
            String code_isbn = "0-9485-6768-6";
            int theme_id = 5;//J'ai mis roman pour ne pas créer un nouveau theme ;-)

            PreparedStatement preparedStatement = con.prepareStatement(
                "INSERT INTO livre (auteur_id, titre, langue, annee_publication, nombre_pages, code_isbn, theme_id) VALUES (?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
                );
            preparedStatement.setInt(1, auteur_id);
            preparedStatement.setString(2, titre);
            preparedStatement.setString(3, langue);
            preparedStatement.setInt(4, annee_publication);
            preparedStatement.setInt(5, nombre_pages);
            preparedStatement.setString(6, code_isbn);
            preparedStatement.setInt(7, theme_id);
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Le livre a été ajouté avec succès.");
            } else {
                System.out.println("Le livre n'a pas été ajouté !");
                throw new Exception("Erreur");
            }

            //On récupère l'id du livre ajouté
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            int livre_id = -1;
            if(generatedKeys.next()) {
                livre_id = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creation du livre a échoué, aucun ID n'a été retourné.");
            }

            //Exemplaire - On suppoera que l'utilisateur a déjà rentré les données suivantes
            String etat = "neuf";
            String reference ="I-782";
            java.util.Date today = new java.util.Date();
            java.sql.Date date_acquisition = new java.sql.Date(today.getTime());
            String rayon = "RAYON-19";
            boolean est_perdu = false;

            preparedStatement = con.prepareStatement(
                "INSERT INTO exemplaire (livre_id, etat, reference, date_acquisition, rayon, est_perdu) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, livre_id);
            preparedStatement.setString(2, etat);
            preparedStatement.setString(3, reference);
            preparedStatement.setDate(4, date_acquisition);
            preparedStatement.setString(5, rayon);
            preparedStatement.setBoolean(6, est_perdu);
            rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("L'exemplaire a été ajouté avec succès.");
            } else {
                System.out.println("L'exemplaire n'a pas été ajouté !");
                throw new Exception("Erreur");
            }
            con.commit();
        }
        catch (SQLException e) {
            try {
                System.out.println("Erreur SQL : " + e.getMessage());
                System.out.println("Rollback de la transaction");
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
