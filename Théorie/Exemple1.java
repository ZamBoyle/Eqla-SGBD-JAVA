import java.sql.*;

import Exemples.user.Input;

public class Exemple1 {
    public static void main(String[] args) {
        try {
            // Chargement du pilote JDBC pour MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            displayLecteurs();
            int lecteur_id = Input.getValidInt("Quel est l'id du lecteur à afficher ? ");
            displayLecteursById(lecteur_id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void displayLecteursById(int i) {
    }

    private static void displayLecteurs() throws ClassNotFoundException, SQLException {
        // Etablissement de la connexion
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblio4_prof", "new_user",
                "password");

        // Création d'un objet Statement pour exécuter une requête de lecture
        Statement stmt = con.createStatement();

        // Exécution d'une requête de lecture
        // et récupération du résultat dans un objet ResultSet
        String query = "SELECT * FROM lecteur";
        ResultSet rs = stmt.executeQuery(query);

        // Parcours du résultat
        while (rs.next()) {
            System.out.println(rs.getString("nom") + "\t\t" + rs.getString("prenom"));
        }

        // Fermeture de la connexion
        con.close();

    }
}