package Exercices.mytmp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import Exercices.bol.Auteur;
import Exercices.dal.DB;
import Exercices.util.Input;

public class Exercice12 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in);) {
            System.out.println("AUTEUR A MODIFIER");
            System.out.println("=================");
            System.out.print("Nom de l'auteur à modifier:");
            String nom = sc.next();
            displayAuteursByName(nom);
            // System.out.print("Id de l'auteur à modifier:");
            int id = Input.getValidInt("Id de l'auteur à modifier:", sc);
            sc.nextLine();
            displayAuteur(id);
            System.out.print("Nouveau nom:");
            String newNom = sc.nextLine();
            System.out.print("Nouveau prénom:");
            String newPrenom = sc.nextLine();
            LocalDate newDateNaissance = Input.getValidLocalDate("\nNouvelle date de naissance:", sc);
            System.out.print("Nouvelle nationalité:");
            String newPays = sc.next();
            updateAuteur(id, newNom, newPrenom, newDateNaissance, newPays);
            displayAuteur(id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void updateAuteur(int id, String newNom, String newPrenom, LocalDate newDate_naissance,
            String newNationalite) {
        Auteur auteur = new Auteur(id, newNom, newPrenom, newDate_naissance, newNationalite);
        updateAuteur(auteur);
    }

    public static void updateAuteur(Auteur auteur) {
        try (Connection con = new DB().getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(
                    "UPDATE auteur SET nom = ?, prenom = ?, date_naissance = ?, nationalite = ? WHERE id = ?");
            preparedStatement.setString(1, auteur.getNom());
            preparedStatement.setString(2, auteur.getPrenom());
            preparedStatement.setDate(3, Date.valueOf(auteur.getDate_naissance()));
            preparedStatement.setString(4, auteur.getNationalite());
            preparedStatement.setInt(5, auteur.getId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void displayAuteursByName(String nom) {
        try (Connection con = new DB().getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM auteur WHERE nom LIKE ?");
            preparedStatement.setString(1, nom + "%");

            displayAuteurCommon(preparedStatement);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void displayAuteur(int id) {
        try (Connection con = new DB().getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM auteur WHERE id = ?");
            preparedStatement.setInt(1, id);

            displayAuteurCommon(preparedStatement);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // On pourrait aussi faire une méthode displayAuteur(Auteur auteur) qui appelle
    // displayAuteur(int id)
    // Mais ça ne serait pas très utile car ça ne sert à rien de récupèrer l'auteur
    // dans la base de données car on l'a déjà
    /*
     * public static void displayAuteur(Auteur auteur) {
     * displayAuteur(auteur.getId());
     * }
     */

    // On va plutôt afficher auteur.toString()
    public static void displayAuteur(Auteur auteur) {
        System.out.println(auteur.toString());
    }

    public static void displayAuteurCommon(PreparedStatement preparedStatement) throws SQLException {
        try (ResultSet rs = preparedStatement.executeQuery();) {
            System.out.println("id\t\tnom\t\tprenom\\t\tdate_naissance\t\tnationalite");
            // Parcours du résultat
            while (rs.next()) {
                System.out
                        .println(rs.getString("id") + "\t\t" + rs.getString("nom") + "\t\t" + rs.getString("prenom")
                                + "\t\t" + rs.getString("date_naissance") + "\t\t" + rs.getString("nationalite"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}