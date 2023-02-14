package Exercices;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import Exercices.dal.DB;
import Exercices.utils.Input;

public class Exercice7 {
    public static void main(String[] args) {
        System.out.print(" ");
        Scanner sc = new Scanner(System.in);
        Integer id = Input.getValidInt("Identifiant de l'auteur à afficher :", sc);
        displayAuteur(id);
    }

    public static void displayAuteur(Integer id) {
        displayAuteursCommon("SELECT * FROM auteur WHERE id = " + id);
    }

    public static void displayAuteurs() {
        displayAuteursCommon("SELECT * FROM auteur");
    }

    public static void displayAuteursCommon(String query) {
        try (DB db = new DB()) {
            Connection con = db.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println(rs.getString("nom") + "\t\t" + rs.getString("prenom"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
