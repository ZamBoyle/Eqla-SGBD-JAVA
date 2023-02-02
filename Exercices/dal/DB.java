package Exercices.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB implements AutoCloseable {
    private String DB_URL = "jdbc:mariadb://localhost:3306/biblio4_prof";
    private String USER = "new_user";
    private String PASS = "password1";

    private Connection con;

    public DB() {
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException("Impossible de se connecter à la base de données:" + e.getMessage());
        }
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



/*public class DB {
    public final static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public final static String DB_URL = "jdbc:mysql://localhost:3306/biblio4_prof";
    public final static String USER = "new_user";
    public final static String PASS = "password";
}
*/