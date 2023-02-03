package Exemples.Chapitre7;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Exemples.biblioXX.BOL.Lecteur;

public class test {
    public static void main(String[] args) {
        String sql = "INSERT INTO auteur (nom, prenom, date_naissance, nationalite) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/biblio4_prof",
                "new_user", "password1");
                PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            
            String dateNaissanceString = "03/02/1998";
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date utilDateNaissance = null;
            try {
                utilDateNaissance = format.parse(dateNaissanceString);
            } catch (ParseException e) {
                e.printStackTrace();
                return;
            }
            
            java.sql.Date sqlDateNaissance = new java.sql.Date(utilDateNaissance.getTime());

            // exemple avec la table lecteur et le champ date_naissance
            Lecteur lecteur = new Lecteur();
            lecteur.setDate_naissance(sqlDateNaissance);

            java.util.Date dateNaissanceUtil = new java.util.Date(1974, 3, 7);

            preparedStatement.setString(1, "Doe");
            preparedStatement.setString(2, "John");
            preparedStatement.setObject(3, null);
            // preparedStatement.setDate()
            preparedStatement.setNull(4, Types.VARCHAR);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}