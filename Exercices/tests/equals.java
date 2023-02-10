
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
