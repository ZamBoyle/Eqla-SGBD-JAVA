package Exemples.biblioXX.BOL;

import java.sql.Date;

public class Auteur {
    private int id;
    private String nom;
    private String prenom;
    private Date date_naissance;

    private String nationalite;

    public Auteur() {
    }

    public Auteur(int id, String nom, String prenom, Date date_naissance, String nationalite) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.nationalite = nationalite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String pays) {
        this.nationalite = pays;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public void displayAuteur() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Auteur {" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", date_naissance='" + date_naissance + '\'' +
                ", pays='" + nationalite + '\'' +
                '}';
    }
}