package biblioX.collection;

import java.util.ArrayList;
import java.util.List;

import biblioX.BOL.Livre;

public class LivreCollection {
    private List<Livre> livres;

    public LivreCollection() {
        livres = new ArrayList<Livre>();
    }

    public void addLivre(Livre livre) {
        livres.add(livre);
    }

    public void removeLivre(Livre livre) {
        livres.remove(livre);
    }

    public Livre getLivre(int id) {
        for (Livre livre : livres) {
            if (livre.getId() == id) {
                return livre;
            }
        }
        return null;
    }

    public List<Livre> getLivres() {
        return livres;
    }

    public void setLivres(List<Livre> livres) {
        this.livres = livres;
    }

    public void displayLivres() {
        for (Livre livre : livres) {
            System.out.println(livre);
        }
    }
}
