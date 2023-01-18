package Exercices.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Biblio implements AutoCloseable{

    private Connection con;

    public Biblio() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Etablissement de la connexion
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblio4_prof", "new_user",
                    "password");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> getDataFromQuery(String query, Function<ResultSet, T> mapResult) {
        List<T> list = new ArrayList<T>();
        try (Statement stm = con.createStatement()) {
            ResultSet resultSet = stm.executeQuery(query);
            while (resultSet.next()) {
                T object = mapResult.apply(resultSet);
                list.add(object);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public <T> T getDataFromQuerySingle(String query, Function<ResultSet, T> mapResult) {
        List<T> list = getDataFromQuery(query, mapResult);
        return list.size() == 1 ? list.get(0) : null;
    }

    @Override
    public void close() throws Exception {
        if(this.con != null) {
            this.con.close();
        }        
    }
}
