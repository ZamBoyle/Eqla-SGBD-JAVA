package App;

import user.Input;

public class Menu {
    public Menu() {
        super();
    }

    public void menuGeneral(){
        //fais le menu console d'une bibliothèque: afficher les lecteurs, afficher les livres, afficher les emprunts, ajouter un lecteur, ajouter un livre, ajouter un emprunt, supprimer un lecteur, supprimer un livre, supprimer un emprunt, modifier un lecteur, modifier un livre, modifier un emprunt
        int choix = 0;
        while (choix != 7) {
            System.out.println("================");
            System.out.println("= Menu général =");
            System.out.println("================");
            System.out.println("1. Gérer les lecteurs");
            System.out.println("2. Gérer les livres");
            System.out.println("3. Gérer les emprunts");
            System.out.println("4. Gérer les exemplaires");
            System.out.println("5. Gérer les auteurs");
            System.out.println("6. Gérer les thèmes");
            System.out.println("7. Quitter");
            choix = Input.getValidInt("Votre choix: ");
            switch (choix) {
                case 1:
                    menuLecteurs();
                    break;
                case 2:
                    menuLivres();
                    break;
                case 3:
                    menuEmprunts();
                    break;
                case 4:
                    menuExemplaires();
                    break;
                case 5:
                    menuAuteurs();
                    break;
                case 6:
                    menuThemes();
                    break;
                case 7:
                    System.out.println("Au revoir");
                    break;
                default:
                    System.out.println("Veuillez entrer un nombre entre 1 et 7");
                    break;
            }
        }
    }

    public void menuLecteurs(){
        //affiche les lecteurs
        //rechercher un lecteur
        //ajouter un lecteur
        //supprimer un lecteur
        //modifier un lecteur
    }

    public void menuLivres(){
        //affiche les livres
        //rechercher un livre
        //ajouter un livre
        //supprimer un livre
        //modifier un livre
    }

    public void menuEmprunts(){
        //affiche les emprunts
        //rechercher un emprunt
        //ajouter un emprunt
        //supprimer un emprunt
        //modifier un emprunt
    }

    public void menuExemplaires(){
        //affiche les exemplaires
        //rechercher un exemplaire
        //ajouter un exemplaire
        //supprimer un exemplaire
        //modifier un exemplaire
    }

    public void menuAuteurs(){
        //affiche les auteurs
        //rechercher un auteur
        //ajouter un auteur
        //supprimer un auteur
        //modifier un auteur
    }

    public void menuThemes(){
        //affiche les themes
        //rechercher un theme
        //ajouter un theme
        //supprimer un theme
        //modifier un theme
    }

}
