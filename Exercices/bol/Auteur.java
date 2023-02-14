package Exercices.bol;

import java.time.LocalDate;

public class Auteur {
    private Integer id;
    private String nom;
    private String prenom;
    private LocalDate date_naissance;

    private String nationalite;

    public Auteur() {
    }

    public Auteur(Integer id, String nom, String prenom, LocalDate date_naissance, String nationalite) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.nationalite = nationalite;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public LocalDate getDate_naissance() {
        return date_naissance;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String pays) {
        this.nationalite = pays;
    }

    public void setDate_naissance(LocalDate date_naissance) {
        this.date_naissance = date_naissance;
    }

    public void displayAuteur() {
        System.out.println(this.toString());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Auteur auteur = (Auteur) o;

        if (id != null ? !id.equals(auteur.id) : auteur.id != null) return false;
        if (nom != null ? !nom.equals(auteur.nom) : auteur.nom != null) return false;
        if (prenom != null ? !prenom.equals(auteur.prenom) : auteur.prenom != null) return false;
        if (date_naissance != null ? !date_naissance.equals(auteur.date_naissance) : auteur.date_naissance != null)
            return false;
        return nationalite != null ? nationalite.equals(auteur.nationalite) : auteur.nationalite == null;
    }

    @Override
    public String toString() {

        String  output= "id\t\tnom\t\tprenom\t\tdate_naissance\t\tnationalite\n";
output += this.getId() + "\t\t" + this.getNom() + "\t\t" + this.getPrenom() + "\t\t"
+ this.getDate_naissance() + "\t\t" + this.getNationalite();
        return output;
    }

    public static void displayAuteur(Auteur auteur) {
        System.out.println(auteur.toString());
    }
}