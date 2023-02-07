package Exemples.Chapitre9;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import Exemples.dal.DB;
import Exemples.user.Input;

public class ExempleZ {
    public static void main(String[] args) {
        while (true) {
            displayMenu();
        }
    }

    public static void displayMenu() {
        System.out.println("Menu principal");
        System.out.println("--------------");
        System.out.println("1. Afficher la liste des clients");
        System.out.println("2. Afficher la liste des comptes");
        System.out.println("3. Afficher la liste des opérations");
        System.out.println("4. Créer un nouveau client");
        System.out.println("5. Créer un nouveau compte");
        System.out.println("6. Quitter");
        System.out.println("----------------------------");

        int choix = Input.getValidInt("Veuillez saisir votre choix:", 1, 6);
        switch (choix) {
            case 1:
                displayClients();
                break;
            case 2:
                displayAccounts();
                break;
            case 3:
                displayOperations();
                break;
            case 4:
                createClient();
                break;
            case 5:
                createAccount();
                break;
            case 6:
                System.out.println("Au revoir");
                System.exit(0);
                break;
        }

        System.out.println("----------------------------");
    }

    public static void displayClients() {
        try (DB db = new DB();
                Connection con = db.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM client");
                ResultSet rs = preparedStatement.executeQuery();) {

            System.out.println("Liste des clients:");
            System.out.println("Nom - Prénom - Date de naissance");
            System.out.println("----------------------------");
            while (rs.next()) {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                java.util.Date dateNaissance = rs.getDate("date_naissance");

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String dateNaissanceFormatee = dateFormat.format(dateNaissance);

                System.out.println(nom + " - " + prenom + " - " + dateNaissanceFormatee);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createAccount() {
    }

    private static void createClient() {
    }

    private static void displayOperations() {
    }

    private static void displayAccounts() {
        try (
                DB db = new DB();
                Connection con = db.getConnection();

        ) {

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private static void createDatabase() {
        String sql = getFileContent("bank.sql");

    }

    private static String getFileContent(String filename){
        String sql="";
        try {
            // Le fichier d'entrée
            FileInputStream file = new FileInputStream("bank.sql");
            try (Scanner scanner = new Scanner(file)) {
                // renvoie true s'il y a une autre ligne à lire
                while (scanner.hasNextLine()) {
                    sql+=scanner.nextLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sql;
    }
}
