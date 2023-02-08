package Exemples.Chapitre9;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import Exemples.dal.DB;
import Exemples.user.Input;

public class Exemple13 {
    private static Scanner sc = new Scanner(System.in);
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

        int choix = Input.getValidInt("Veuillez saisir votre choix:", 1, 6, sc);
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
        try (DB db = new DB("bank");
                Connection con = db.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement("SELECT client.*, SUM(compte.solde) AS total_solde FROM client JOIN compte ON client.id = compte.client_id GROUP BY nom, prenom");
                ResultSet rs = preparedStatement.executeQuery();) {
            System.out.println();
            System.out.println("Liste des clients:");
            System.out.println("Nom - Prénom - Date de naissance - Total des soldes");
            System.out.println("----------------------------");
            while (rs.next()) {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                java.util.Date dateNaissance = rs.getDate("date_naissance");

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String dateNaissanceFormatee = dateFormat.format(dateNaissance);

                double totalSolde = rs.getDouble("total_solde");

                System.out.println(nom + " - " + prenom + " - " + dateNaissanceFormatee + " - " + totalSolde + " €");
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
        System.out.println();
        System.out.println("Liste des opérations:");
        System.out.println("---------------------");
        System.out.println("1. Transfert d'argent entre deux comptes");
        System.out.println("2. Retrait d'argent");
        System.out.println("3. Dépôt d'argent");
        System.out.println("4. Retour au menu principal");
        System.out.println("----------------------------");

        int choix = Input.getValidInt("Veuillez saisir votre choix:", 1, 4, sc);
        switch (choix) {
            case 1:
                transferMoneyMenu();
                break;
            case 2:
                withdrawMoney();
                break;
            case 3:
                depositMoney();
                break;
            case 4:
                displayMenu();
                break;
        }
    }

    private static void transferMoneyMenu() {
        System.out.println();
        System.out.println("Transfert d'argent entre deux comptes");
        System.out.println("-------------------------------------");
        int compteSource = Input.getValidInt("Compte source:", sc);
        int compteDestination = Input.getValidInt("Compte destination:",sc);
        double montant = Input.getValidNumber("Montant:", sc, Double.class);
        String question = "Voulez-vous confirmer le transfert de " + montant + " euros du compte " + compteSource
                + " vers le compte " + compteDestination + "? (O/N)";
        String confirmation = Input.getValidString(question, sc, "O", "N");
        if (confirmation.equals("O")) {
            transferMoney(compteSource, compteDestination, montant);
        } else {
            displayOperations();
        }
    }

    private static void transferMoney(int compteSource, int compteDestination, double montant) {
        try (DB db = new DB("bank");) {
            Connection con = db.getConnection();
            con.setAutoCommit(false);
            try {
                // Débit du compte source
                PreparedStatement preparedStatementDebit = con
                        .prepareStatement("UPDATE compte SET solde = solde - ? WHERE id = ?");
                preparedStatementDebit.setDouble(1, montant);
                preparedStatementDebit.setInt(2, compteSource);
                preparedStatementDebit.executeUpdate();

                // Crédit du compte destination
                PreparedStatement preparedStatementCredit = con
                        .prepareStatement("UPDATE compte SET solde = solde + ? WHERE id = ?");
                preparedStatementCredit.setDouble(1, montant);
                preparedStatementCredit.setInt(2, compteDestination);
                preparedStatementCredit.executeUpdate();

                // Validation de la transaction
                con.commit();
            } catch (Exception e) {
                // Annulation de la transaction
                con.rollback();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void withdrawMoney() {
    }

    private static void depositMoney() {
    }

    private static void displayAccounts() {
        try (
                DB db = new DB("bank");
        ) {
            Connection con = db.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("SELECT compte.id, solde, nom, prenom, date_naissance FROM compte INNER JOIN client ON compte.client_id = client.id");
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println();
            System.out.println("Liste des comptes:");
            System.out.println("Numéro Compte - Solde - Nom - Prénom - Date de naissance");
            System.out.println("------------------------------------------------");
            while (rs.next()) {
                int numero = rs.getInt("id");
                double solde = rs.getDouble("solde");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                java.util.Date dateNaissance = rs.getDate("date_naissance");

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String dateNaissanceFormatee = dateFormat.format(dateNaissance);

                System.out.println(numero + " - " + solde + " € - " + nom + " - " + prenom + " - " + dateNaissanceFormatee);
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private static void createDatabase() {
        String sql = getFileContent("/home/zam/repos/Eqla-SGBD-JAVA/Théorie/Exemples/Chapitre9/bank.sql");

    }

    private static String getFileContent(String filename) {
        String sql = "";
        try {
            // Le fichier d'entrée
            FileInputStream file = new FileInputStream(filename);
            try (Scanner scanner = new Scanner(file)) {
                // renvoie true s'il y a une autre ligne à lire
                while (scanner.hasNextLine()) {
                    sql += scanner.nextLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sql;
    }
}
