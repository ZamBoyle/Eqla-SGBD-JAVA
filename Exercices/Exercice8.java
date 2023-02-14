package Exercices;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import Exercices.dal.DB;

public class Exercice8 {
    public static void main(String[] args) {
        System.out.print(" ");
        Scanner sc = new Scanner(System.in);
        System.out.print("Rayon de l'exemplaire à afficher :");
        String rayon = sc.next();
        System.out.print("Etat de l'exemplaire à afficher :");
        String etat = sc.next();
        displayExemplaires(rayon, etat);
        sc.close();
    }

    public static void displayExemplaires(String rayon, String etat) {
        try (DB db = new DB()) {
            Connection con = db.getConnection();

            // Création d'un objet Statement pour exécuter une requête de lecture
            Statement stmt = con.createStatement();

            // Exécution d'une requête de lecture
            // et récupération du résultat dans un objet ResultSet
            String query = "SELECT * " +
                    "FROM exemplaire " +
                    "WHERE rayon = '" + rayon + "' AND etat = '" + etat + "'";
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("id\t\tlivre_id\t\trayon\t\tetat");
            // Parcours du résultat
            while (rs.next()) {
                System.out.println(rs.getString("id") + "\t\t" + rs.getString("livre_id") + "\t\t"
                        + rs.getString("rayon") + "\t\t" + rs.getString("etat"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
