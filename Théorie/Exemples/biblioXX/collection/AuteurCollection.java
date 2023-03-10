package Exemples.biblioXX.collection;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Exemples.biblioXX.BOL.Auteur;

public class AuteurCollection {
    private List<Auteur> auteurs;

    public AuteurCollection() {
        auteurs = new ArrayList<Auteur>();
    }

    public AuteurCollection(List<Auteur> auteurs) {
        this.auteurs = auteurs;
    }

    public AuteurCollection(Auteur[] auteurs) {
        this(new ArrayList<Auteur>(Arrays.asList(auteurs)));
    }

   public List<Auteur> getAuteurs() {
        return auteurs;
    }

    public void addAuteur(Auteur auteur) {
        auteurs.add(auteur);
    }

    public void removeAuteur(Auteur auteur) {
        auteurs.remove(auteur);
    }

    public void removeAuteur(int id) {
        for (Auteur auteur : auteurs) {
            if (auteur.getId() == id) {
                auteurs.remove(auteur);
                break;
            }
        }
    }

    public Auteur getAuteur(int id) {
        for (Auteur auteur : auteurs) {
            if (auteur.getId() == id) {
                return auteur;
            }
        }
        return null;
    }

    public Auteur[] getMyAuteurs() {
        Auteur[] auteurs = new Auteur[] {
                new Auteur(1, "Leven", "Holli", Date.valueOf("1959-07-24"), "espa"),
                new Auteur(2, "Dumas", "Alexandre", Date.valueOf("1975-04-03"), "allemand"),
                new Auteur(3, "Silwood", "Vaughan", Date.valueOf("1983-08-02"), "belge"),
                new Auteur(4, "Kitchener", "Merline", Date.valueOf("1978-02-28"), "allemand"),
                new Auteur(5, "Cattermull", "Levi", Date.valueOf("1990-01-21"), "français"),
        };
        return auteurs;
    }

    public void displayAuteurs() {
        for (Auteur auteur : auteurs) {
            System.out.println(auteur);
        }
    }
}
