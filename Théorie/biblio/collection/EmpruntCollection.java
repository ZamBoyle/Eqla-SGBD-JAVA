package biblio.collection;

import java.util.ArrayList;
import java.util.List;

import biblio.BOL.Emprunt;

public class EmpruntCollection {
    private List<Emprunt> emprunts;

    public EmpruntCollection() {
        emprunts = new ArrayList<Emprunt>();
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
