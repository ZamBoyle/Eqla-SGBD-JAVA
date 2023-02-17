package Tests.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Tests.bol.Auteur;

public class AuteurDAO {
    private static AuteurDAO instance;
    
    // Constructeur privé pour empêcher l'instanciation directe
    private AuteurDAO() {
    }

    // Méthode statique pour récupérer l'instance unique
    public static AuteurDAO getInstance() {
        if (instance == null) {
            instance = new AuteurDAO();
        }
        return instance;
    }

    public Auteur getAuteurById(int id) {
        Auteur auteur = null;
        try (Connection con = DB.getInstance().getConnection()) {
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

    private Auteur getAuteurFromRS(ResultSet rs) throws Exception {
        Auteur auteur = null;
        if (rs != null) {
            auteur = new Auteur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
                    rs.getDate("date_naissance").toLocalDate(), rs.getString("nationalite"));
        }
        return auteur;
    }

    public List<Auteur> getAuteurs() throws Exception {
        List<Auteur> auteurs = new ArrayList<>();
        try (Connection con = DB.getInstance().getConnection()) {
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

    public List<Auteur> getAuteursByName(String name) {
        List<Auteur> auteurs = new ArrayList<>();
        try (Connection con = DB.getInstance().getConnection()) {
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

    public List<Auteur> getAuteursByFirstName(String firstName) throws Exception {
        throw new Exception("Not implemented yet");
    }

    public List<Auteur> getAuteursByNationality(String nationality) throws Exception {
        throw new Exception("Not implemented yet");
    }

    public List<Auteur> getAuteursByBirthDate(String birthDate) throws Exception {
        throw new Exception("Not implemented yet");
    }

    public void updateAuteur(Auteur auteur) {
        try (Connection con = DB.getInstance().getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(
                    "UPDATE auteur SET nom = ?, prenom = ?, date_naissance = ?, nationalite = ? WHERE id = ?");
            preparedStatement.setString(1, auteur.getNom());
            preparedStatement.setString(2, auteur.getPrenom());
            preparedStatement.setDate(3, java.sql.Date.valueOf(auteur.getDate_naissance()));
            preparedStatement.setString(4, auteur.getNationalite());
            preparedStatement.setInt(5, auteur.getId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addAuteur(Auteur auteur) {
    }

    public void deleteAuteur(Auteur auteur) {
    }

    public void deleteAuteurById(int id) {
    }
}