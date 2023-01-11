import java.sql.*;

import Exemples.app.Menu;

public class Exemple2 {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.menuPrincipal();
    }

    private static void displayLecteurs() {
        try {
            // Chargement du pilote JDBC pour MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Etablissement de la connexion
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblio4_prof", "new_user", "password");
            
            // Création d'un objet Statement pour exécuter une requête de lecture
            Statement stmt = con.createStatement();

            String query = "SELECT * FROM lecteur";
            System.out.println(query);
            
            // Exécution d'une requête de lecture
            // et récupération du résultat dans un objet ResultSet
            ResultSet rs = stmt.executeQuery(query);
    
            
            // Parcours du résultat
            while (rs.next()) 
            System.out.println(rs.getString("nom") + "\t\t" + rs.getString("prenom"));

            // Fermeture de la connexion
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
