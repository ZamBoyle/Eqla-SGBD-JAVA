package Exercices.mytmp;

import Exercices.bol.Auteur;
import Exercices.dal.DB;
import Exercices.util.Input;
import Exercices.dal.AuteurDAO;

public class Exercice13 {
    public static void main(String[] args) {
        //insert auteur with DAO class
        Auteur auteur = new Auteur(0, "Doe", "John", Input.getValidLocalDate("Date de naissance:", null), "USA");
        AuteurDAO.insert(auteur);
        Auteur.displayAuteur(auteur);
    }    
}
