package Exemples.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB implements AutoCloseable {
    private String DB_URL = "jdbc:mariadb://localhost:3306/biblio4_prof";
    private String USER = "new_user";
    private String PASS = "password1";

    private Connection con;

    public DB() {

        /*
         * try {
         * Class.forName("org.mariadb.jdbc.Driver");
         * // Etablissement de la connexion
         */
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException("Impossible de se connecter à la base de données:" + e.getMessage());
        }
        /*
         * } catch (ClassNotFoundException e) {
         * throw new
         * ClassCastException("Impossible de charger le pilote JDBC pour MySQL:"+e.
         * getMessage());
         * }
         */
    }

    public String getDB_URL() {
        return this.DB_URL;
    }

    public String getUSER() {
        return this.USER;
    }

    public String getPASS() {
        return this.PASS;
    }

    public Connection getConnection() {
        return con;
    }

    @Override
    public void close() throws Exception {
        if (this.con != null) {
            this.con.close();
        }
    }
}