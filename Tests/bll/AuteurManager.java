package Tests.bll;

import java.util.List;

import Tests.bol.Auteur;
import Tests.dal.AuteurDAO;

public class AuteurManager {
    public static Auteur getAuteurById(int id) {
        return  AuteurDAO.getInstance().getAuteurById(id);
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

    public static void addAuteur(Auteur auteur) throws Exception {
         AuteurDAO.getInstance().addAuteur(auteur);
    }

    public static void updateAuteur(Auteur auteur) throws Exception {
        AuteurDAO.getInstance().updateAuteur(auteur);
    }

    public static void deleteAuteur(Auteur auteur) throws Exception {
        AuteurDAO.getInstance().deleteAuteur(auteur);
    }

    public static void deleteAuteurById(int id) throws Exception {
        AuteurDAO.getInstance().deleteAuteurById(id);
    }
}
