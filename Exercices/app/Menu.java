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
        if (this.role == Role.ADMIN || this.role == Role.BIBLIOTHECAIRE) {
            menuGeneralEmploye();
        } else {
            menuGeneralLecteur();
        }
    }

    private void menuGeneralEmploye() {
        int choix = 0;
        while (choix != 9) {
            System.out.println("==================");
            System.out.println("= Menu Principal =");
            System.out.println("==================");
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
            choix =  Input.getValidInt("Votre choix: ");
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

    private void menuSysteme() {
    }

    private void menuThemes() {
    }

    private void menuAuteurs() {
    }

    private void menuExemplaires() {
    }

    private void menuEmprunts() {
    }

    private void menuLivres() {
    }

    private void menuLecteurs() {
        int choix = 0; 
        while (choix != 4) {
            System.out.println("==================");
            System.out.println("= Menu Lecteurs =");
            System.out.println("==================");
            System.out.println("1. Ajouter un lecteur");
            System.out.println("2. Modifier un lecteur");
            System.out.println("3. Supprimer un lecteur");
            System.out.println("4. Rechercher un lecteur");
            System.out.println("======================");
            System.out.println("5. Retour");
            choix = Input.getValidInt("Votre choix: ");
            switch (choix) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    menuRechercheLecteurs();
                    break;
                default:
                    System.out.println("Choix invalide");
                    break;
            }
        }
    }

    private void menuGeneralLecteur() {

        int choix = Input.getValidInt("Votre choix: ");
        while (choix != 4) {
            System.out.println("==================");
            System.out.println("= Menu Principal =");
            System.out.println("==================");
            System.out.println("1. Rechercher livres");
            System.out.println("2. Rechercher lecteurs");
            System.out.println("3. Rechercher auteurs");
            System.out.println("======================");
            System.out.println("4. Quitter");
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

    private void menuRecherche() {
        int max= this.role == Role.ADMIN || this.role == Role.BIBLIOTHECAIRE ? 6 : 3;
        int choix = Input.getValidInt("Votre choix: ");
        while (choix != max) {
                System.out.println("==================");
                System.out.println("= Menu Recherche =");
                System.out.println("==================");
                System.out.println("1. Rechercher un livre");
                System.out.println("2. Rechercher un auteur");
                System.out.println("3. Rechercher un thème");
                System.out.println("4. Rechercher un lecteur");
                System.out.println("5. Rechercher un emprunt");
                System.out.println("6. Rechercher un exemplaire");
            switch (choix) {
                case 1:
                    menuRechercheLivre();
                    break;
                case 2:
                    menuRechercheAuteur();
                    break;
                case 3:
                    menuRechercheTheme();
                    break;
                case 4:
                    menuRechercheLecteurs();
                    break;
                case 5:
                    menuRechercheEmprunt();
                    break;
                case 6:
                    menuRechercheExemplaire();
                    break;
                default:
                    System.out.println("Choix invalide");
                    break;
            }
        }
    }

    private void menuRechercheExemplaire() {
    }

    private void menuRechercheEmprunt() {
    }

    private void menuRechercheLecteurs() {
    }

    private void menuRechercheTheme() {
    }

    private void menuRechercheAuteur() {
    }

    private void menuRechercheLivre() {
    }

    private void menuRechercheEmploye(){


    }


    private void menuAdmin() {
        // Changer le mot de passe d'un utilisateur
        // Changer le role d'un utilisateur
        // Désactiver un utilisateur
        // Activer un utilisateur
        // Ajouter un utilisateur
        // Supprimer un utilisateur
    }
}
