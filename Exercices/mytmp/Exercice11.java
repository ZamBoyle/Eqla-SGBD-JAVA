package Exercices.mytmp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import Exercices.dal.DB;

public class Exercice11 {
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
        rayon = rayon.startsWith("rayon-") ? rayon.substring(6) : "rayon-" + rayon;
        try (DB db = new DB()) {
            Connection con = db.getConnection();
            String query = "SELECT livre.titre, exemplaire.id, rayon, etat, auteur.nom, auteur.prenom " +
                    "FROM exemplaire " +
                    "INNER JOIN livre ON exemplaire.livre_id = livre.id " +
                    "INNER JOIN auteur ON livre.auteur_id = auteur.id " +
                    "WHERE rayon = ? AND etat = ?";

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, rayon);
            preparedStatement.setString(2, etat);

            ResultSet rs = preparedStatement.executeQuery();

            System.out.println("exemplaire_id\t\ttitre\t\tauteur.nom\t\tauteur.prenom\t\trayon\t\tetat");
            // Parcours du résultat
            while (rs.next()) {
                // System.out.println("exemplaire_id:"+rs.getString("id")+"\t\ttitre:"+rs.getString("livre.titre")+"\t\tauteur_nom:"+rs.getString("auteur.nom")+"\t\tauteur_prenom:"+rs.getString("auteur.prenom")+"\t\trayon:"+
                // rs.getString("rayon") + "\t\tetat:" + rs.getString("etat"));
                System.out.println(rs.getString("id") + "\t\t" + rs.getString("titre") + "\t\t"
                        + rs.getString("auteur.nom") + "\t\t" + rs.getString("auteur.prenom") + "\t\t"
                        + rs.getString("rayon") + "\t\t" + rs.getString("etat"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
