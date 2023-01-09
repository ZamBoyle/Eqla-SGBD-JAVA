package biblioX;
public class Biblio1 {
    public static void main(String[] args) {
        Biblio biblio = new Biblio();

        //biblio.getThemeCollection().displayThemes();
        //biblio.getLecteurCollection().displayLecteurs();
        biblio.getAuteurCollection().displayAuteurs();
        //biblio.getLivreCollection().displayLivres();
        //biblio.getEmpruntCollection().displayEmprunts();

        
                /*
        biblio
                .getAuteurCollection()
                .getAuteurs()
                .stream().filter(auteur -> auteur.getNationalite().contains("français"))
                .forEach(auteur -> auteur.displayAuteur());
        */
        // biblio.getLivreCollection().stream().filter() .displayLivres();
    }
}