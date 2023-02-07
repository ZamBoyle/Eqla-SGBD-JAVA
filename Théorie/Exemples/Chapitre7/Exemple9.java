package Exemples.Chapitre7;

import java.sql.*;
import Exemples.dal.DB;

public class Exemple9 {
    public static void main(String[] args) throws Exception{
        Connection con = null;
        try (DB db = new DB()) {
            con = db.getConnection();
            //con.setAutoCommit(false);

            //Livre - On suppoera que l'utilisateur a déjà rentré les données suivantes
            int auteur_id = 461;//Adie Travers
            String titre = "Le Java c'est sympa les gars !";
            String langue = "français";
            int annee_publication = 2023;
            int nombre_pages = 450;
            String code_isbn = "0-9485-6768-6";

            PreparedStatement preparedStatement = con.prepareStatement(
                "INSERT INTO livre (auteur_id, titre, langue, annee_publication, nombre_pages, code_isbn) VALUES (?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
                );
            preparedStatement.setInt(1, auteur_id);
            preparedStatement.setString(2, titre);
            preparedStatement.setString(3, langue);
            preparedStatement.setInt(4, annee_publication);
            preparedStatement.setInt(5, nombre_pages);
            preparedStatement.setString(6, code_isbn);
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Le livre a été ajouté avec succès.");
            } else {
                System.out.println("Le livre n'a pas été ajouté !");
            }

            //Exemplaire - On suppoera que l'utilisateur a déjà rentré les données suivantes
            String etat = "neuf";
            String reference ="I-782";
            java.sql.Date date_acquisition = (Date)new java.util.Date();
            String rayon = "RAYON-19";
            boolean est_perdu = false;
            //Theme - On suppoera que l'utilisateur a déjà rentré les données suivantes
            String  theme = "Informatique";

            con.commit();
        }
        catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
