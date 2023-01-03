package biblio.BOL;

import java.sql.Date;

public class Emprunt {
    private int id;
    private Date date_emprunt;
    private Date date_retour;
    private int lecteur_id;
    private int exemplaire_id;

    public Emprunt() {
    }

    public Emprunt(int id, Date date_emprunt, Date date_retour, int lecteur_id, int exemplaire_id) {
        this.id = id;
        this.date_emprunt = date_emprunt;
        this.date_retour = date_retour;
        this.lecteur_id = lecteur_id;
        this.exemplaire_id = exemplaire_id;
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

    public int getLecteur_id() {
        return lecteur_id;
    }

    public void setLecteur_id(int lecteur_id) {
        this.lecteur_id = lecteur_id;
    }

    public int getExemplaire_id() {
        return exemplaire_id;
    }

    public void setExemplaire_id(int exemplaire_id) {
        this.exemplaire_id = exemplaire_id;
    }

    @Override
    public String toString() {
        return "Emprunt [id=" + id + ", date_emprunt=" + date_emprunt + ", date_retour=" + date_retour + ", lecteur_id="
                + lecteur_id + ", exemplaire_id=" + exemplaire_id + "]";
    }
}

