package Exercices.mytmp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import Exercices.dal.DB;
import Exercices.util.Input;

public class Exercice6 {
    public static void main(String[] args) {
        System.out.print(" ");
        Scanner sc = new Scanner(System.in);
        Integer id = Input.getValidInt("Identifiant de l'auteur à afficher :", sc);
        displayAuteur(id);
    }

    public static void displayAuteur(Integer id) {
        try (Connection con = DB.getInstance().getConnection();) {
            // Création d'un objet Statement pour exécuter une requête de lecture
            Statement stmt = con.createStatement();

            // Exécution d'une requête de lecture
            // et récupération du résultat dans un objet ResultSet
            String query = "SELECT * FROM auteur WHERE id = " + id;
            ResultSet rs = stmt.executeQuery(query);

            // Parcours du résultat
            while (rs.next()) {
                System.out.println(rs.getString("nom") + "\t\t" + rs.getString("prenom"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void displayAuteurs() {
        try (Connection con = DB.getInstance().getConnection();) {
            // Création d'un objet Statement pour exécuter une requête de lecture
            Statement stmt = con.createStatement();

            // Exécution d'une requête de lecture
            // et récupération du résultat dans un objet ResultSet
            String query = "SELECT * FROM auteur";
            ResultSet rs = stmt.executeQuery(query);

            // Parcours du résultat
            while (rs.next()) {
                System.out.println(rs.getString("nom") + "\t\t" + rs.getString("prenom"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
