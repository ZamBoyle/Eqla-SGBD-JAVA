package Exemples.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB implements AutoCloseable {
    private String DB_URL = "jdbc:mariadb://localhost:3306/biblio4_prof";
    private String USER = "new_user";
    private String PASS = "password1";

    private Connection con;

    // Constructeur par défaut pour la base de données biblio4_prof
    public DB() {
        this("biblio4_prof");
    }

    // Constructeur pour une base de données quelconque
    public DB(String db_name) {
        try {
            this.DB_URL = "jdbc:mariadb://localhost:3306/" + db_name;
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

    public static Integer getLastId(String table, String... id) {
        try (DB db = new DB()) {
            if (id.length == 0)
                id = new String[] { "id" };
            Connection con = db.getConnection();                
            PreparedStatement pstmt = con.prepareStatement("SELECT MAX(" + id[0] + ") FROM " + table);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}