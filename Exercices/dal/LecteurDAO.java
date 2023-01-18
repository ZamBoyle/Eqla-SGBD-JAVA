package Exercices.dal;

import Exercices.bol.Lecteur;

public class LecteurDAO {
    public static Lecteur getLecteurById(int id) {
        return CommonDAO.getDataFromQuerySingle("SELECT * FROM lecteur WHERE id = " + id, Lecteur::fillLecteur);
    }

    public static Lecteur getLecteurByName(String name) {
        return CommonDAO.getDataFromQuerySingle("SELECT * FROM lecteur WHERE nom LIKE '%" + name + "%'", Lecteur::fillLecteur);
    }

    public static Lecteur getLecteurByFirstName(String firstName) {
        return CommonDAO.getDataFromQuerySingle("SELECT * FROM lecteur WHERE prenom LIKE '%" + firstName + "%'", Lecteur::fillLecteur);
    }

    public static Lecteur getLecteurByCP(int cp) {
        return CommonDAO.getDataFromQuerySingle("SELECT * FROM lecteur WHERE code_postal =" + cp, Lecteur::fillLecteur);
    }
}