package Exemples.biblioXX.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Exemples.biblioXX.BOL.Lecteur;

public class LecteurCollection {
    private List<Lecteur> lecteurs;

    public LecteurCollection() {
        lecteurs = new ArrayList<Lecteur>();
    }

    public LecteurCollection(List<Lecteur> lecteurs) {
        this.lecteurs = lecteurs;
    }

    public LecteurCollection(Lecteur[] lecteurs){
        this(new ArrayList<>(Arrays.asList(lecteurs)));
    }

    public void addLecteur(Lecteur lecteur) {
        lecteurs.add(lecteur);
    }

    public void removeLecteur(Lecteur lecteur) {
        lecteurs.remove(lecteur);
    }

    public Lecteur getLecteur(int id) {
        for (Lecteur lecteur : lecteurs) {
            if (lecteur.getId() == id) {
                return lecteur;
            }
        }
        return null;
    }

    public List<Lecteur> getLecteurs() {
        return lecteurs;
    }

    public void setLecteurs(List<Lecteur> lecteurs) {
        this.lecteurs = lecteurs;
    }

    public void displayLecteurs() {
        for (Lecteur lecteur : lecteurs) {
            System.out.println(lecteur);
        }
    }    
}
