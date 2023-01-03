package biblio.BOL;

import biblio.collection.*;

public class Livre {
    // Attributs
    private int id;
    private String titre;
    private String langue;
    private int nombre_pages;
    private String code_isbn;
    private int annee_publication;
    private Auteur auteur;
    private Theme theme;

    // Constructeurs
    public Livre() {
    }

    public Livre(int id, String titre, String langue, int nombre_pages, String code_isbn, int annee_publication,
            Auteur auteur, Theme theme) {
        this.id = id;
        this.titre = titre;
        this.langue = langue;
        this.nombre_pages = nombre_pages;
        this.code_isbn = code_isbn;
        this.annee_publication = annee_publication;
        this.auteur = auteur;
        this.theme = theme;
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

    public int getAnnee_publication() {
        return annee_publication;
    }

    public void setAnnee_publication(int annee_publication) {
        this.annee_publication = annee_publication;
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
        return "Livre [auteur=" + auteur + ", code_isbn=" + code_isbn + ", date_publication=" + annee_publication
                + ", id=" + id + ", langue=" + langue + ", nombre_pages=" + nombre_pages + ", theme=" + theme
                + ", titre=" + titre + "]";
    }
}
