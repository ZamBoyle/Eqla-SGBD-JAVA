package Exercices;

import java.util.List;

import Exercices.app.Menu;
import Exercices.bol.Auteur;
import Exercices.bol.Lecteur;
import Exercices.dal.*;

public class Exercice1 {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
/*         Biblio biblio = new Biblio();
        List<Lecteur> lecteurs = biblio.getLecteurs();
        lecteurs = biblio.getDataFromQuery("SELECT * FROM lecteur", Lecteur::fillLecteur);
        Lecteur lecteur = biblio.getLecteur(1);
        List<Lecteur> lecteursByName = biblio.getLecteursByName("Dupont"); */

        List<Auteur> auteurs = AuteurDAO.getAuteurs();
        Auteur auteur = AuteurDAO.getAuteurById(1);

        Menu menu = new Menu();
        menu.menuPrincipal();


    }
    
    public static void displayAuteur(Integer id) {
        Auteur auteur = AuteurDAO.getAuteurById(id);
        System.out.println(auteur);
    }
}
