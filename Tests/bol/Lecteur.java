package Tests.bol;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Lecteur {
    private int id;
    private String nom;
    private String prenom;
    private Date date_naissance;
    private String adresse;
    private int num_rue;
    private int code_postal;
    private String localite;
    private String telephone;

    public Lecteur() {
    }

    public Lecteur(int id, String nom, String prenom, Date date_naissance, String adresse, int num_rue, int code_postal,
            String localite, String telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.adresse = adresse;
        this.num_rue = num_rue;
        this.code_postal = code_postal;
        this.localite = localite;
        this.telephone = telephone;
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

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getNum_rue() {
        return num_rue;
    }

    public void setNum_rue(int num_rue) {
        this.num_rue = num_rue;
    }

    public int getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(int code_postal) {
        this.code_postal = code_postal;
    }

    public String getLocalite() {
        return localite;
    }

    public void setLocalite(String localite) {
        this.localite = localite;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public static Lecteur fillLecteur(ResultSet resultSet)  {
        Lecteur lecteur = new Lecteur();
        try {
            lecteur.setId(resultSet.getInt("id"));
            lecteur.setNom(resultSet.getString("nom"));
            lecteur.setPrenom(resultSet.getString("prenom"));
            lecteur.setAdresse(resultSet.getString("adresse"));
            lecteur.setNum_rue(resultSet.getInt("num_rue"));
            lecteur.setLocalite(resultSet.getString("localite"));
            lecteur.setCode_postal(resultSet.getInt("code_postal"));
            lecteur.setTelephone(resultSet.getString("telephone"));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
        return lecteur;
    }

    @Override
    public String toString() {
        return "Lecteur [adresse=" + adresse + ", code_postal=" + code_postal + ", date_naissance=" + date_naissance
                + ", id=" + id + ", localite=" + localite + ", nom=" + nom + ", num_rue=" + num_rue + ", prenom=" + prenom
                + ", telephone=" + telephone + "]";
    }
}
