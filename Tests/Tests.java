package Tests;

import java.sql.*;
import java.text.SimpleDateFormat;

public class Tests {
    public static void main(String[] args) {
        try {
            // Chargement du pilote JDBC pour MySQL
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("org.mariadb.jdbc.Driver");
            
            // Etablissement de la connexion
            //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblio4_prof", "new_user", "password1");
            Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/biblio4_prof", "new_user", "password1");
            
            // Création d'un objet Statement pour exécuter une requête de lecture
            Statement stmt = con.createStatement();

            // Exécution d'une requête de lecture
            // et récupération du résultat dans un objet ResultSet
            String query = "SELECT * FROM lecteur;";
            ResultSet rs = stmt.executeQuery(query);    
            
            // Parcours du résultat
            while (rs.next()) {
                SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
                Date date = rs.getDate("date_naissance");
                String stringDate= DateFor.format(date);
                System.out.println(rs.getString("nom") + "\t\t" +stringDate+ "\t\t"+ rs.getString("prenom"));
            }

            // Fermeture de la connexion
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}