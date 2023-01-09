import java.sql.*;

public class Exemple1 {
    public static void main(String args[]) {
        try {
            // Chargement du pilote JDBC pour MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Etablissement de la connexion
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pays", "new_user", "password");
            
            // Création d'un objet Statement
            Statement stmt = con.createStatement();


            String code = System.console().readLine("code pays:");
            String query = "SELECT display_order, name, full_name FROM Pays WHERE Id_Pays = " + code;
            System.out.println(query);
            
            // Exécution d'une requête de lecture
            ResultSet rs = stmt.executeQuery(query);
    
            while (rs.next())
                System.out.println(rs.getInt("name") + "\t  " + rs.getString(2) + "\t\t  " + rs.getString(3));

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
