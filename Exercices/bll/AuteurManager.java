package Exercices.bll;
import Exercices.bol.Auteur;
import Exercices.dal.AuteurDAO;

public class AuteurManager {
    public static Auteur getActeurById(int id) {
        return AuteurDAO.getActeurById(id);
    }
}
