package Exercices.mytmp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import Exercices.dal.DB;
public class Exercice9 {
    public static void main(String[] args) {
        System.out.print(" ");
        Scanner sc = new Scanner(System.in);
        System.out.print("Rayon de l'exemplaire à afficher :");
        String rayon = sc.next();
        System.out.print("Etat de l'exemplaire à afficher :");
        String etat = sc.next();
        displayExemplaires(rayon, etat);
    }

    public static void displayExemplaires(String rayon, String etat) {
        try (Connection con = DB.getInstance().getConnection();) {
            

            // Création d'un objet Statement pour exécuter une requête de lecture
            Statement stmt = con.createStatement();

            // Exécution d'une requête de lecture
            // et récupération du résultat dans un objet ResultSet
            String query = "SELECT livre.titre, exemplaire.id, rayon, etat " +
                    "FROM exemplaire " +
                    "INNER JOIN livre ON exemplaire.livre_id = livre.id " +
                    "WHERE rayon = '" + rayon + "' AND etat = '" + etat + "'";
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("id\t\ttitre\t\trayon\t\tetat");
            // Parcours du résultat
            while (rs.next()) {
                // System.out.println("id:"+rs.getString("id")+"\t\ttitre:"+rs.getString("livre.titre")+"\t\trayon:"+
                // rs.getString("rayon") + "\t\tetat:" + rs.getString("etat"));
                System.out.println(rs.getString("id") + "\t\t" + rs.getString("titre") + "\t\t"
                        + rs.getString("rayon") + "\t\t" + rs.getString("etat"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}