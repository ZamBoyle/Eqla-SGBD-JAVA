package Exercices.mytmp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Exercices.dal.DB;

public class Exercice3 {
        public static void main(String[] args) {
            try (Connection con = DB.getInstance().getConnection();) {
                // Création d'un objet Statement pour exécuter une requête de lecture
                Statement stmt = con.createStatement();

                // Exécution d'une requête de lecture
                // et récupération du résultat dans un objet ResultSet
                String query = "SELECT * FROM theme";
                ResultSet rs = stmt.executeQuery(query);

                // Parcours du résultat
                while (rs.next()) {
                    System.out.println(rs.getInt("id") + "\t\t" + rs.getString("theme"));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            System.console().readLine("< Appuyez sur ENTER pour continuer >");
        }
}
