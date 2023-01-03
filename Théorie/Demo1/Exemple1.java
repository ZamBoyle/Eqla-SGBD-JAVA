package Demo1;

import biblio.Biblio;

public class Exemple1 {
    public static void main(String[] args) {
        Biblio biblio = new Biblio();
        biblio
                .getAuteurCollection()
                .getAuteurs()
                .stream().filter(auteur -> auteur.getNationalite().equals("français"))
                .forEach(auteur -> auteur.displayAuteur());
        // biblio.getThemeCollection().displayThemes();
        // biblio.getLivreCollection().stream().filter() .displayLivres();
    }
}