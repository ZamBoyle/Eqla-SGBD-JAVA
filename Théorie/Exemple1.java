import java.sql.Date;

import biblio.Biblio;
import biblio.BOL.*;
import biblio.collection.*;

public class Exemple1 {
    public static void main(String[] args) {
        // Auteur[] auteurs = fillAuteurs();
        // Theme[] themes = fillThemes();

        // AuteurCollection auteurCollection = new AuteurCollection(auteurs);
        // ThemeCollection themeCollection = new ThemeCollection(themes);

        // auteurCollection.displayAuteurs();
        // themeCollection.displayThemes();
        Biblio biblio = new Biblio();
        biblio.getAuteurs().displayAuteurs();
        biblio.getThemes().displayThemes();
        biblio.getLivres().displayLivres();
    }

    public static Auteur[] fillAuteurs() {
        Auteur[] auteurs = new Auteur[] {
                new Auteur(1, "Leven", "Holli", Date.valueOf("1959-07-24"), "espa"),
                new Auteur(2, "Dumas", "Alexandre", Date.valueOf("1975-04-03"), "allemand"),
                new Auteur(3, "Silwood", "Vaughan", Date.valueOf("1983-08-02"), "belge"),
                new Auteur(4, "Kitchener", "Merline", Date.valueOf("1978-02-28"), "allemand"),
                new Auteur(5, "Cattermull", "Levi", Date.valueOf("1990-01-21"), "français"),
        };
        return auteurs;
    }

    public static Theme[] fillThemes() {
        Theme[] themes = new Theme[] {
                new Theme(1, "Aventure"),
                new Theme(2, "Fantastique"),
                new Theme(3, "Policier"),
                new Theme(4, "Science-fiction"),
                new Theme(5, "Roman"),
        };
        return themes;
    }

}