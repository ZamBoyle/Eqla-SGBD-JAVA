package Exemples.Chapitre7;

import java.sql.*;
import java.time.LocalDate;
import java.util.Date;

import org.mariadb.jdbc.plugin.codec.LocalDateCodec;

import Exemples.dal.DB;

public class Exemple10 {
    public static void main(String[] args) throws Exception{
        Connection con = null;
        try (DB db = new DB()) {
            con = db.getConnection();
            con.setAutoCommit(false);

            //Premier lecteur - On suppoera que l'utilisateur a déjà rentré les données suivantes
            String nom = "Piette";
            String prenom = "Johnny";
            LocalDate date_naissance = LocalDate.parse("1974/12/31");
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
            
            preparedStatement.executeUpdate();

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
            preparedStatement.setNull(5, Types.INTEGER);
            preparedStatement.setString(6, code_postal);
            preparedStatement.setString(7, localite);
            preparedStatement.setString(8, telephone);

            preparedStatement.executeUpdate();
/*
            ResultSet rs = preparedStatement.getGeneratedKeys();
            int lecteur_id = 0;
            if (rs.next()) {
                lecteur_id = rs.getInt(1);
            }
*/

/* 
            if (rowsInserted > 0) {
                System.out.println("Le livre a été ajouté avec succès.");
            } else {
                System.out.println("Le livre n'a pas été ajouté !");
            }
*/

            System.out.println("Les lecteurs ont été ajoutés avec succès.");
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
