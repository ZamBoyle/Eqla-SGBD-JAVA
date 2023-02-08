package Exemples.Chapitre9;

import java.sql.*;
import java.time.LocalDate;
import Exemples.dal.DB;

public class Exemple11 {
    public static void main(String[] args) throws Exception{
        Connection con = null;
        try (DB db = new DB()) {
            con = db.getConnection();
            con.setAutoCommit(false);

            //Premier lecteur - On suppoera que l'utilisateur a déjà rentré les données suivantes
            String nom = "Piette";
            String prenom = "Johnny";
            LocalDate date_naissance = LocalDate.parse("1974-12-31");
            String adresse = "Rue des écoles";
            int num_rue = 45;
            String code_postal = "75000";
            String localite = "Paris";
            String telephone = "01 23 45 67 89";

            PreparedStatement preparedStatement = con.prepareStatement(
                "INSERT INTO lecteur (nom, prenom, date_naissance, adresse, num_rue, code_postal, localite, telephone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
                );

            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setDate(3, java.sql.Date.valueOf(date_naissance));
            preparedStatement.setString(4, adresse);
            preparedStatement.setInt(5, num_rue);
            preparedStatement.setString(6, code_postal);
            preparedStatement.setString(7, localite);
            preparedStatement.setString(8, telephone);
            
            int nbEnregistrements= preparedStatement.executeUpdate();
            if (nbEnregistrements > 0)
                System.out.println("Le premier lecteur a été ajouté.");
            else
                throw new Exception("Aucun lecteur n'a été ajouté.");

            //Deuxième lecteur - On suppoera que l'utilisateur a déjà rentré les données suivantes
            nom = "Jacques";
            prenom = "Véronique";
            date_naissance = LocalDate.of(1986,01,18);
            adresse = "Rue des écoles";
            num_rue = 45;            
            code_postal = "75000";
            localite = "Paris";
            telephone = "01 23 33 48 91";

            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setDate(3, java.sql.Date.valueOf(date_naissance));
            preparedStatement.setString(4, adresse);
            preparedStatement.setInt(5, num_rue);
            preparedStatement.setString(6, code_postal);
            preparedStatement.setString(7, localite);
            preparedStatement.setString(8, telephone);

            nbEnregistrements = preparedStatement.executeUpdate();
            if (nbEnregistrements > 0)
                System.out.println("Le deuxième lecteur a été ajouté.");
            else
                throw new Exception("Aucun lecteur n'a été ajouté.");

            System.out.println("Les 2 lecteurs ont été ajoutés avec succès.");
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
