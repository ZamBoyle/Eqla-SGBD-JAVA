package biblio.BOL;

import java.sql.Date;

import biblio.collection.LivreCollection;

public class Exemplaire {
    private int id;
    private String reference;
    private String rayon;
    private Date date_acquisition;
    private String etat;
    private boolean est_perdu;
    private Livre livre;

    public Exemplaire() {
    }

    public Exemplaire(int id, String reference, String rayon, Date date_acquisition, String etat, boolean est_perdu, Livre livre) {
        this.id = id;
        this.reference = reference;
        this.rayon = rayon;
        this.date_acquisition = date_acquisition;
        this.etat = etat;
        this.est_perdu = est_perdu;
        this.livre = livre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getRayon() {
        return rayon;
    }

    public void setRayon(String rayon) {
        this.rayon = rayon;
    }

    public Date getDate_acquisition() {
        return date_acquisition;
    }

    public void setDate_acquisition(Date date_acquisition) {
        this.date_acquisition = date_acquisition;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public boolean isEst_perdu() {
        return est_perdu;
    }

    public void setEst_perdu(boolean est_perdu) {
        this.est_perdu = est_perdu;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    @Override
    public String toString() {
        return "Exemplaire [id=" + id + ", reference=" + reference + ", rayon=" + rayon + ", date_acquisition="
                + date_acquisition + ", etat=" + etat + ", est_perdu=" + est_perdu + ", livre=" + livre + "]";
    }

}
