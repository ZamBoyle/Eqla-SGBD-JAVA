package Exemples.Chapitre9;

import java.sql.*;
import java.text.SimpleDateFormat;

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

    private static void createDatabase(){
        String db ="""
CREATE DATABASE bank;

USE bank;

CREATE TABLE client (
  id INT PRIMARY KEY AUTO_INCREMENT,
  prenom VARCHAR(50) NOT NULL,
  nom VARCHAR(50) NOT NULL,
  date_naissance DATE NOT NULL,
  sexe ENUM('Homme', 'Femme','Autre') NOT NULL,
  numero_national_belge VARCHAR(20) NOT NULL
);

CREATE TABLE compte (
  id INT PRIMARY KEY AUTO_INCREMENT,
  client_id INT NOT NULL,
  solde DECIMAL(10, 2) NOT NULL,
  FOREIGN KEY (client_id) REFERENCES client(id)
);

INSERT INTO client (prenom, nom, date_naissance, sexe, numero_national_belge) VALUES 
("John", "Doe", '1980-01-01', 'Homme', '123456789'),
("Jane", "Doe", '1985-02-01', 'Femme', '234567890'),
("Bob", "Smith", '1990-03-01', 'Homme', '345678901'),
("Alice", "Johnson", '1995-04-01', 'Femme', '456789012'),
("Michael", "Brown", '2000-05-01', 'Homme', '567890123'),
("Emily", "Davis", '2005-06-01', 'Femme', '678901234'),
("William", "Thompson", '2010-07-01', 'Homme', '789012345'),
("Ashley", "Wilson", '2015-08-01', 'Femme', '890123456'),
("James", "Anderson", '2020-09-01', 'Autre', '901234567'),
("Elizabeth", "Martinez", '2025-10-01', 'Femme', '012345678');

INSERT INTO compte (client_id, solde)
SELECT id, 1000
FROM client;""";

    }
}
