package Exercices.dal;

import java.sql.ResultSet;
import java.util.List;
import java.util.function.Function;

import Exercices.bol.Auteur;
import Exercices.dal.*;

public class AuteurDAO {

    public static Auteur getActeurById(int id) {
        return null;
    }

    public static List<Auteur> getAuteurs() {
        return CommonDAO.getDataFromQuery("SELECT * FROM auteur", Auteur::fillAuteur);
    }

    public static Auteur getAuteurById(int id) {
        return CommonDAO.getDataFromQuerySingle("SELECT * FROM auteur WHERE id = " + id, Auteur::fillAuteur);
    }

    public static List<Auteur> getAuteursByName(String name) {
        return CommonDAO.getDataFromQuery("SELECT * FROM auteur WHERE nom LIKE '%" + name + "%'", Auteur::fillAuteur);
    }

    public static List<Auteur> getAuteursByFirstName(String firstName) {
        return CommonDAO.getDataFromQuery("SELECT * FROM auteur WHERE prenom LIKE '%" + firstName + "%'", Auteur::fillAuteur);
    }

    public static List<Auteur> getAuteursByNationality(String nationality){
        return CommonDAO.getDataFromQuery("SELECT * FROM auteur WHERE nationalite LIKE '%" + nationality + "%'", Auteur::fillAuteur);
    }

    public static List<Auteur> getAuteursByBirthDate(String birthDate){
        return CommonDAO.getDataFromQuery("SELECT * FROM auteur WHERE date_naissance LIKE '%" + birthDate + "%'", Auteur::fillAuteur);
    }
}
