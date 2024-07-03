package Tests.bll;

import java.util.List;

import Tests.bol.Auteur;
import Tests.dal.AuteurDAO;

public class AuteurManager {
    public static Auteur get(int id) {
        return  AuteurDAO.getInstance().get(id);
    }

    public static List<Auteur> getAuteurs() throws Exception {
        return  AuteurDAO.getInstance().getAuteurs();
    }

    public static List<Auteur> getAuteursByName(String nom) throws Exception {
        return  AuteurDAO.getInstance().getAuteursByName(nom);
    }

    public static List<Auteur> getAuteursByFirstName(String firstName) throws Exception {
        return  AuteurDAO.getInstance().getAuteursByFirstName(firstName);
    }

    public static List<Auteur> getAuteursByNationality(String nationality) throws Exception {
        return  AuteurDAO.getInstance().getAuteursByNationality(nationality);
    }

    public static List<Auteur> getAuteursByBirthDate(String birthDate) throws Exception {
        return  AuteurDAO.getInstance().getAuteursByBirthDate(birthDate);
    }

    public static void add(Auteur auteur) throws Exception {
         AuteurDAO.getInstance().add(auteur);
    }

    public static void update(Auteur auteur) throws Exception {
        AuteurDAO.getInstance().update(auteur);
    }

    public static void delete(int id) throws Exception {
        AuteurDAO.getInstance().delete(id);
    }
}
