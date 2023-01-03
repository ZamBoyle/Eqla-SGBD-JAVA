package biblio.BOL;

import biblio.collection.*;

public class Livre {
    // Attributs
    private int id;
    private String titre;
    private String langue;
    private int nombre_pages;
    private String code_isbn;
    private int date_publication;
    private Auteur auteur;
    private Theme theme;

    // Constructeurs
    public Livre() {
    }

    public Livre(int id, String titre, String langue, int nombre_pages, String code_isbn, int date_publication,
            Auteur auteur, Theme theme) {
        this.id = id;
        this.titre = titre;
        this.langue = langue;
        this.nombre_pages = nombre_pages;
        this.code_isbn = code_isbn;
        this.date_publication = date_publication;
        this.auteur = auteur;
        this.theme = theme;
    }

    public Livre(int id, String titre, String langue, int nombre_pages, String code_isbn, int date_publication,
            int auteur_id, int theme_id) {
        this.id = id;
        this.titre = titre;
        this.langue = langue;
        this.nombre_pages = nombre_pages;
        this.code_isbn = code_isbn;
        this.date_publication = date_publication;
        this.auteur = new AuteurCollection().getAuteur(auteur_id);
        this.theme = new ThemeCollection().getTheme(theme_id);
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public int getNombre_pages() {
        return nombre_pages;
    }

    public void setNombre_pages(int nombre_pages) {
        this.nombre_pages = nombre_pages;
    }

    public String getCode_isbn() {
        return code_isbn;
    }

    public void setCode_isbn(String code_isbn) {
        this.code_isbn = code_isbn;
    }

    public int getDate_publication() {
        return date_publication;
    }

    public void setDate_publication(int date_publication) {
        this.date_publication = date_publication;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    // Méthodes
    @Override
    public String toString() {
        return "Livre [auteur=" + auteur + ", code_isbn=" + code_isbn + ", date_publication=" + date_publication
                + ", id=" + id + ", langue=" + langue + ", nombre_pages=" + nombre_pages + ", theme=" + theme
                + ", titre=" + titre + "]";
    }
}
