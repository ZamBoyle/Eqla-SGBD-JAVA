package Exercices.app;

import Exercices.user.Role;
import Exercices.user.Input;

public class Menu {
    private int role;

    public Menu(int role) {
        this.role = role;
    }

    public Menu() {
        this.role = Role.LECTEUR;
    }

    public void menuPrincipal() {

        int max = this.role == Role.ADMIN || this.role == Role.BIBLIOTHECAIRE ? 9 : 2;
        System.out.println("================");
        System.out.println("= Menu Général =");
        System.out.println("================");
        int choix = 0;
        if (this.role == Role.ADMIN || this.role == Role.BIBLIOTHECAIRE) {
            while (choix != max) {
                System.out.println("1. Gérer les lecteurs");
                System.out.println("2. Gérer les livres");
                System.out.println("3. Gérer les emprunts");
                System.out.println("4. Gérer les exemplaires");
                System.out.println("5. Gérer les auteurs");
                System.out.println("6. Gérer les thèmes");
                System.out.println("7. Rechercher: livre, lecteur, emprunt, exemplaire, auteur, thème");
                System.out.println("================");
                System.out.println("8. Gérer le Systeme");
                System.out.println("================");
                System.out.println("9. Quitter");

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
                        menuRecherche();
                        break;
                    case 8:
                        menuSysteme();
                        break;
                    case 9:
                        break;
                    default:
                        System.out.println("Choix invalide");
                        break;
                }
            }
        }
        else{
            while (choix != max) {
                System.out.println("1. Rechercher livres");
                System.out.println("2. Rechercher lecteurs");
                System.out.println("3. Rechercher auteurs");
                System.out.println("4. Quitter");

                choix = Input.getValidInt("Votre choix: ");
                switch (choix) {
                    case 1:
                        menuRecherche();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("Choix invalide");
                        break;
                }
            }
        }
        System.out.println("Au revoir");
    }

    private void menuRecherche() {
    }

    private void menuSysteme() {
        // Changer le mot de passe de l'utilisateur
        if (this.role == Role.ADMIN) {
            menuAdmin();
        }
    }

    private void menuAdmin() {
        // Changer le mot de passe d'un utilisateur
        // Changer le role d'un utilisateur
        // Désactiver un utilisateur
        // Activer un utilisateur
        // Ajouter un utilisateur
        // Supprimer un utilisateur
    }

    public void menuLecteurs() {
        // affiche les lecteurs
        // rechercher un lecteur
        // ajouter un lecteur
        // supprimer un lecteur
        // modifier un lecteur
    }

    public void menuLivres() {
        // affiche les livres
        // rechercher un livre
        // ajouter un livre
        // supprimer un livre
        // modifier un livre
    }

    public void menuEmprunts() {
        // affiche les emprunts
        // rechercher un emprunt
        // ajouter un emprunt
        // supprimer un emprunt
        // modifier un emprunt
    }

    public void menuExemplaires() {
        // affiche les exemplaires
        // rechercher un exemplaire
        // ajouter un exemplaire
        // supprimer un exemplaire
        // modifier un exemplaire
    }

    public void menuAuteurs() {
        // affiche les auteurs
        // rechercher un auteur
        // ajouter un auteur
        // supprimer un auteur
        // modifier un auteur
    }

    public void menuThemes() {
        // affiche les themes
        // rechercher un theme
        // ajouter un theme
        // supprimer un theme
        // modifier un theme
    }

}
