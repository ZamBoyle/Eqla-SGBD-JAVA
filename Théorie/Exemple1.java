import biblioX.Biblio;

public class Exemple1 {
    public static void main(String[] args) {
        Biblio biblio = new Biblio();
        /*
        biblio
                .getAuteurCollection()
                .getAuteurs()
                .stream().filter(auteur -> auteur.getNationalite().contains("français"))
                .forEach(auteur -> auteur.displayAuteur());
        */
        biblio.getThemeCollection().displayThemes();
        biblio.getLecteurCollection().displayLecteurs();
        // biblio.getLivreCollection().stream().filter() .displayLivres();
    }
}