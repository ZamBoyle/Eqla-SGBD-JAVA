package biblioX.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import biblioX.BOL.Emprunt;

public class EmpruntCollection {
    private List<Emprunt> emprunts;

    public EmpruntCollection() {
        emprunts = new ArrayList<Emprunt>();
    }

    public EmpruntCollection(List<Emprunt> emprunts) {
        this.emprunts = emprunts;
    }

    public EmpruntCollection(Emprunt[] emprunts) {
        this(new ArrayList<Emprunt>(Arrays.asList(emprunts)));
    }

    public void addEmprunt(Emprunt emprunt) {
        emprunts.add(emprunt);
    }

    public void removeEmprunt(Emprunt emprunt) {
        emprunts.remove(emprunt);
    }

    public Emprunt getEmprunt(int id) {
        for (Emprunt emprunt : emprunts) {
            if (emprunt.getId() == id) {
                return emprunt;
            }
        }
        return null;
    }

    public List<Emprunt> getEmprunts() {
        return emprunts;
    }

    public void setEmprunts(List<Emprunt> emprunts) {
        this.emprunts = emprunts;
    }

    public void displayEmprunts() {
        for (Emprunt emprunt : emprunts) {
            System.out.println(emprunt);
        }
    }
}
