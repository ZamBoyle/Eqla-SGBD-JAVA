package Exercices.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import Exercices.bol.Auteur;

public class AuteurDAO {

    public static Auteur getAuteurById(int id) {
        Auteur auteur = null;
        try (Connection con = new DB().getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM auteur WHERE id = ?");
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            // Parcours du résultat
            if (rs.next()) {
                auteur = getAuteurFromRS(rs);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return auteur;
    }

    public static Auteur getAuteurFromRS(ResultSet rs) throws Exception {
        Auteur auteur = null;
        if (rs != null) {
            new Auteur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
                    rs.getDate("date_naissance").toLocalDate(), rs.getString("nationalite"));
        }
        return auteur;
    }

    public static List<Auteur> getAuteurs() throws Exception {
        List<Auteur> auteurs = new ArrayList<>();
        try (Connection con = new DB().getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM auteur");
            ResultSet rs = preparedStatement.executeQuery();
            // Parcours du résultat
            while (rs.next()) {
                auteurs.add(getAuteurFromRS(rs));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return auteurs;
    }

    public static List<Auteur> getAuteursByName(String name) {
        List<Auteur> auteurs = new ArrayList<>();
        try (Connection con = new DB().getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM auteur WHERE nom LIKE ?");
            preparedStatement.setString(1, name + "%");

            ResultSet rs = preparedStatement.executeQuery();
            // Parcours du résultat
            while (rs.next()) {
                auteurs.add(new Auteur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
                        rs.getDate("date_naissance").toLocalDate(), rs.getString("nationalite")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return auteurs;
    }

    public static List<Auteur> getAuteursByFirstName(String firstName) throws Exception {
        throw new Exception("Not implemented yet");
    }

    public static List<Auteur> getAuteursByNationality(String nationality) throws Exception {
        throw new Exception("Not implemented yet");
    }

    public static List<Auteur> getAuteursByBirthDate(String birthDate) throws Exception {
        throw new Exception("Not implemented yet");
    }

    private void updateAuteur(Auteur auteur) {
        try (Connection con = new DB().getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(
                    "UPDATE auteur SET nom = ?, prenom = ?, date_naissance = ?, nationalite = ? WHERE id = ?");
            preparedStatement.setString(1, auteur.getNom());
            preparedStatement.setString(2, auteur.getPrenom());
            preparedStatement.setDate(3, Date.valueOf(auteur.getDate_naissance()));
            preparedStatement.setString(4, auteur.getNationalite());
            preparedStatement.setInt(5, auteur.getId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
