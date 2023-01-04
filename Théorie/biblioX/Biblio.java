package biblioX;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import biblioX.BOL.*;
import biblioX.collection.*;

public class Biblio {
    private LivreCollection livres = new LivreCollection();
    private AuteurCollection auteurs =new AuteurCollection();
    private ThemeCollection themes = new ThemeCollection();
    private ExemplaireCollection exemplaires = new ExemplaireCollection();
    private EmpruntCollection emprunts = new EmpruntCollection();
    private LecteurCollection lecteurs = new LecteurCollection();

    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/biblio4_prof";
    private final String USER = "new_user";
    private final String PASS = "password";

    public Biblio() {
        fillAuteurs();
        fillThemes();
        fillLivres();
        fillExemplaires();
        fillLecteurs();
        fillEmprunts();
        
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
    }

    public AuteurCollection getAuteurCollection() {
        return auteurs;
    }

    public ThemeCollection getThemeCollection() {
        return themes;
    }

    public LivreCollection getLivreCollection() {
        return livres;
    }

    public ExemplaireCollection getExemplaireCollection() {
        return exemplaires;
    }

    public EmpruntCollection getEmpruntCollection() {
        return emprunts;
    }

    public LecteurCollection getLecteurCollection() {
        return lecteurs;
    }

    

    public void fillAuteurs() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            // Créer un objet Statement
            Statement statement = connection.createStatement();

            // Exécuter une requête SQL pour lire tous les auteurs de la table Auteur
            ResultSet resultSet = statement.executeQuery("SELECT * FROM auteur");

            // Parcourir tous les enregistrements de la table Auteur
            while (resultSet.next()) {
                // Récupérer les valeurs de chaque colonne
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                Date dateNaissance = resultSet.getDate("date_naissance");
                String nationalite = resultSet.getString("nationalite");

                // Créer un nouvel objet Auteur avec les valeurs lues
                Auteur auteur = new Auteur(id, nom, prenom, dateNaissance, nationalite);

                // Ajouter l'objet Auteur au tableau
                auteurs.addAuteur(auteur);
            }

            // Fermer le ResultSet et le Statement
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fillThemes() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            // Créer un objet Statement
            Statement statement = connection.createStatement();

            // Exécuter une requête SQL pour lire tous les auteurs de la table theme
            ResultSet resultSet = statement.executeQuery("SELECT * FROM theme");

            // Parcourir tous les enregistrements de la table Theme
            while (resultSet.next()) {
                // Récupérer les valeurs de chaque colonne
                int id = resultSet.getInt("id");
                String libelle = resultSet.getString("theme");

                // Créer un nouvel objet Auteur avec les valeurs lues
                Theme theme = new Theme(id, libelle);

                // Ajouter l'objet Theme au tableau
                themes.addTheme(theme);
            }

            // Fermer le ResultSet et le Statement
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fillLivres() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            // Créer un objet Statement
            Statement statement = connection.createStatement();

            // Exécuter une requête SQL pour lire tous les auteurs de la table livre
            ResultSet resultSet = statement.executeQuery("SELECT * FROM livre");

            // Parcourir tous les enregistrements de la table Livre
            while (resultSet.next()) {
                // Récupérer les valeurs de chaque colonne
                int id = resultSet.getInt("id");
                String titre = resultSet.getString("titre");
                String langue = resultSet.getString("langue");
                int nombre_pages = resultSet.getInt("nombre_pages");
                String code_isbn = resultSet.getString("code_isbn");
                int annee_publication = resultSet.getInt("annee_publication");
                int idAuteur = resultSet.getInt("auteur_id");
                int idTheme = resultSet.getInt("theme_id");

                // Créer un nouvel objet livre avec les valeurs lues
                Auteur auteur = auteurs.getAuteur(idAuteur);
                Theme theme = themes.getTheme(idTheme);

                Livre livre = new Livre(id, titre, langue, nombre_pages, code_isbn, annee_publication, auteur, theme);

                // Ajouter l'objet Livre au tableau
                livres.addLivre(livre);
            }

            // Fermer le ResultSet et le Statement
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fillExemplaires() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            // Créer un objet Statement
            Statement statement = connection.createStatement();

            // Exécuter une requête SQL pour lire tous les auteurs de la table exemplaire
            ResultSet resultSet = statement.executeQuery("SELECT * FROM exemplaire");

            // Parcourir tous les enregistrements de la table Exemplaire
            while (resultSet.next()) {
                // Récupérer les valeurs de chaque colonne
                int id = resultSet.getInt("id");
                String reference = resultSet.getString("reference");
                String rayon = resultSet.getString("rayon");
                Date date_acquisition = resultSet.getDate("date_acquisition");
                String etat = resultSet.getString("etat");
                boolean est_perdu = resultSet.getBoolean("est_perdu");
                int livre_id = resultSet.getInt("livre_id");

                // Créer un nouvel objet exemplaire avec les valeurs lues
                Livre livre = livres.getLivre(livre_id);

                Exemplaire exemplaire = new Exemplaire(id, reference, rayon, date_acquisition, etat, est_perdu, livre);

                // Ajouter l'objet Exemplaire au tableau
                exemplaires.addExemplaire(exemplaire);
            }

            // Fermer le ResultSet et le Statement
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fillLecteurs(){
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            // Créer un objet Statement
            Statement statement = connection.createStatement();

            // Exécuter une requête SQL pour lire tous les auteurs de la table lecteur
            ResultSet resultSet = statement.executeQuery("SELECT * FROM lecteur");

            // Parcourir tous les enregistrements de la table Lecteur
            while (resultSet.next()) {
                // Récupérer les valeurs de chaque colonne
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                Date date_naissance = resultSet.getDate("date_naissance");
                String adresse = resultSet.getString("adresse");
                int num_rue = resultSet.getInt("num_rue");
                int code_postal = resultSet.getInt("code_postal");
                String localite = resultSet.getString("localite");
                String telephone = resultSet.getString("telephone");

                // Créer un nouvel objet Lecteur avec les valeurs lues
                Lecteur lecteur = new Lecteur(id, nom, prenom, date_naissance, adresse, num_rue, code_postal, localite, telephone);

                // Ajouter l'objet Lecteur au tableau
                lecteurs.addLecteur(lecteur);
            }

            // Fermer le ResultSet et le Statement
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fillEmprunts() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            // Créer un objet Statement
            Statement statement = connection.createStatement();

            // Exécuter une requête SQL pour lire tous les auteurs de la table emprunt
            ResultSet resultSet = statement.executeQuery("SELECT * FROM emprunt");

            // Parcourir tous les enregistrements de la table Emprunt
            while (resultSet.next()) {
                // Récupérer les valeurs de chaque colonne
                int id = resultSet.getInt("id");
                Date date_emprunt = resultSet.getDate("date_emprunt");
                Date date_retour = resultSet.getDate("date_retour");
                int lecteur_id = resultSet.getInt("lecteur_id");
                int exemplaire_id = resultSet.getInt("exemplaire_id");

                // Créer un nouvel objet Emprunt avec les valeurs lues
                Lecteur lecteur = lecteurs.getLecteur(lecteur_id);
                Exemplaire exemplaire = exemplaires.getExemplaire(exemplaire_id);

                Emprunt emprunt = new Emprunt(id, date_emprunt, date_retour, lecteur, exemplaire);

                // Ajouter l'objet Emprunt au tableau
                emprunts.addEmprunt(emprunt);
            }

            // Fermer le ResultSet et le Statement
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
