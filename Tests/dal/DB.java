package Tests.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB implements AutoCloseable {
    private String DB_URL = "jdbc:mariadb://localhost:3306/biblio4_prof";
    private String USER = "new_user";
    private String PASS = "password1";

    private Connection con;

    private static DB instance;

    private DB() {
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException("Impossible de se connecter à la base de données:" + e.getMessage());
        }
    }

    public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
        }else{
            try {
                if(instance.getConnection() == null || instance.getConnection().isClosed()){
                    instance = new DB();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
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