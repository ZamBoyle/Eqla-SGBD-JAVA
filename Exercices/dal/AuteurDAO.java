package Exercices.dal;

import java.util.List;

import Exercices.bol.Auteur;

public class AuteurDAO {

    public static Auteur getActeurById(int id) {
        return null;
    }

    public static List<Auteur> getAuteurs() throws Exception {
        return CommonDAO.getDataFromQuery("SELECT * FROM auteur", Auteur::fillAuteur);
    }

    public static Auteur getAuteurById(int id) throws Exception {
        return CommonDAO.getDataFromQuerySingle("SELECT * FROM auteur WHERE id = " + id, Auteur::fillAuteur);
    }

    public static List<Auteur> getAuteursByName(String name) throws Exception {
        return CommonDAO.getDataFromQuery("SELECT * FROM auteur WHERE nom LIKE '%" + name + "%'", Auteur::fillAuteur);
    }

    public static List<Auteur> getAuteursByFirstName(String firstName) throws Exception {
        return CommonDAO.getDataFromQuery("SELECT * FROM auteur WHERE prenom LIKE '%" + firstName + "%'", Auteur::fillAuteur);
    }

    public static List<Auteur> getAuteursByNationality(String nationality) throws Exception{
        return CommonDAO.getDataFromQuery("SELECT * FROM auteur WHERE nationalite LIKE '%" + nationality + "%'", Auteur::fillAuteur);
    }

    public static List<Auteur> getAuteursByBirthDate(String birthDate) throws Exception{
        return CommonDAO.getDataFromQuery("SELECT * FROM auteur WHERE date_naissance LIKE '%" + birthDate + "%'", Auteur::fillAuteur);
    }
}
