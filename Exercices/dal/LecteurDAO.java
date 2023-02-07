package Exercices.dal;

import Exercices.bol.Lecteur;

public class LecteurDAO {
    public static Lecteur getLecteurById(int id) throws Exception {
        return CommonDAO.getDataFromQuerySingle("SELECT * FROM lecteur WHERE id = " + id, Lecteur::fillLecteur);
    }

    public static Lecteur getLecteurByName(String name) throws Exception {
        return CommonDAO.getDataFromQuerySingle("SELECT * FROM lecteur WHERE nom LIKE '%" + name + "%'", Lecteur::fillLecteur);
    }

    public static Lecteur getLecteurByFirstName(String firstName) throws Exception {
        return CommonDAO.getDataFromQuerySingle("SELECT * FROM lecteur WHERE prenom LIKE '%" + firstName + "%'", Lecteur::fillLecteur);
    }

    public static Lecteur getLecteurByCP(int cp) throws Exception {
        return CommonDAO.getDataFromQuerySingle("SELECT * FROM lecteur WHERE code_postal =" + cp, Lecteur::fillLecteur);
    }
}