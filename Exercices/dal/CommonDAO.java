package Exercices.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CommonDAO {
    public static <T> List<T> getDataFromQuery(String query, Function<ResultSet, T> mapResult) {
        List<T> list = new ArrayList<T>();
        try (
            Connection con = DriverManager.getConnection(DB.DB_URL, DB.USER, DB.PASS);
            Statement stm = con.createStatement()) {
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


    public static <T> T getDataFromQuerySingle(String query, Function<ResultSet, T> mapResult) {
        List<T> list = getDataFromQuery(query, mapResult);
        return list.size() == 1 ? list.get(0) : null;
    }

}
