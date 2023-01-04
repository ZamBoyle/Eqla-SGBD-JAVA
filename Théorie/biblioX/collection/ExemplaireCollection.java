package biblioX.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import biblioX.BOL.Exemplaire;

public class ExemplaireCollection {
    private List<Exemplaire> exemplaires;

    public ExemplaireCollection() {
        exemplaires = new ArrayList<Exemplaire>();
    }

    public ExemplaireCollection(List<Exemplaire> exemplaires) {
        this.exemplaires = exemplaires;
    }
    
    public ExemplaireCollection(Exemplaire[] exemplaires) {
        this(new ArrayList<Exemplaire>(Arrays.asList(exemplaires)));
    }

    public void addExemplaire(Exemplaire exemplaire) {
        exemplaires.add(exemplaire);
    }

    public void removeExemplaire(Exemplaire exemplaire) {
        exemplaires.remove(exemplaire);
    }

    public Exemplaire getExemplaire(int id) {
        for (Exemplaire exemplaire : exemplaires) {
            if (exemplaire.getId() == id) {
                return exemplaire;
            }
        }
        return null;
    }

    public List<Exemplaire> getExemplaires() {
        return exemplaires;
    }

    public void setExemplaires(List<Exemplaire> exemplaires) {
        this.exemplaires = exemplaires;
    }

    public void displayExemplaires() {
        for (Exemplaire exemplaire : exemplaires) {
            System.out.println(exemplaire);
        }
    }

    

}
