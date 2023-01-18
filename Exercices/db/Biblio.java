package Exercices.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import Exercices.bol.Auteur;
import Exercices.bol.Lecteur;

public class Biblio implements AutoCloseable {

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

    // dispose object
    public void dispose() {
        try {
            con.close();
            con = null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Lecteur> getLecteursByName(String name) {
        return getDataFromQuery("SELECT * FROM lecteur WHERE nom LIKE '%" + name + "%'", Lecteur::fillLecteur);
    }

    public List<Lecteur> getLecteursByFirstName(String firstName) {
        return getDataFromQuery("SELECT * FROM lecteur WHERE prenom LIKE '%" + firstName + "%'", Lecteur::fillLecteur);
    }

    public List<Lecteur> getLecteursByCP(int cp) {
        return getDataFromQuery("SELECT * FROM lecteur WHERE cp =" + cp, Lecteur::fillLecteur);
    }

    public List<Auteur> getAuteurs() {
        return getDataFromQuery("SELECT * FROM auteur", Auteur::fillAuteur);
    }

    public List<Lecteur> getLecteurs() {
        return getDataFromQuery("SELECT * FROM lecteur", Lecteur::fillLecteur);
    }

    public Lecteur getLecteur(int id) {
        return getDataFromQuerySingle("SELECT * FROM lecteur WHERE id = " + id, Lecteur::fillLecteur);
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
