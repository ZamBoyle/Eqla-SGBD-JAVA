package biblio.BOL;

import java.sql.Date;

public class Emprunt {
    private int id;
    private Date date_emprunt;
    private Date date_retour;
    private Lecteur lecteur;
    private Exemplaire exemplaire;

    public Emprunt() {
    }

    public Emprunt(int id, Date date_emprunt, Date date_retour, Lecteur lecteur, Exemplaire exemplaire) {
        this.id = id;
        this.date_emprunt = date_emprunt;
        this.date_retour = date_retour;
        this.lecteur = lecteur;
        this.exemplaire = exemplaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_emprunt() {
        return date_emprunt;
    }

    public void setDate_emprunt(Date date_emprunt) {
        this.date_emprunt = date_emprunt;
    }

    public Date getDate_retour() {
        return date_retour;
    }

    public void setDate_retour(Date date_retour) {
        this.date_retour = date_retour;
    }

    public Lecteur getLecteur() {
        return lecteur;
    }

    public void setLecteur(Lecteur lecteur) {
        this.lecteur = lecteur;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    @Override
    public String toString() {
        return "Emprunt [id=" + id + ", date_emprunt=" + date_emprunt + ", date_retour=" + date_retour + ", lecteur="
                + lecteur + ", exemplaire=" + exemplaire + "]";
    }

}

