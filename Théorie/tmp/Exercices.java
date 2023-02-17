package tmp;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Exemples.user.Input;
import Exercices.bol.Auteur;
import Exercices.dal.DB;
import Exercices.dal.AuteurDAO;

public class Exercices {

    public static void main(String[] args) {

        Exercices exercices = new Exercices();

        /*
         * System.out.println("Exercice 3");
         * System.out.println("==========");
         * Exercice3 exercice3 = exercices.new Exercice3();
         * exercice3.Run();
         * System.console().readLine("< Appuyez sur ENTER pour continuer >");
         * 
         * System.out.println("Exercice 4");
         * System.out.println("==========");
         * Exercice4 exercice4 = exercices.new Exercice4();
         * exercice4.Run();
         * System.console().readLine("< Appuyez sur ENTER pour continuer >");
         * 
         * System.out.println("Exercice 5");
         * System.out.println("==========");
         * Exercice5 exercice5 = exercices.new Exercice5();
         * exercice5.Run();
         * System.console().readLine("< Appuyez sur ENTER pour continuer >");
         * 
         * System.out.println("Exercice 6");
         * System.out.println("==========");
         * Exercice6 exercice6 = exercices.new Exercice6();
         * exercice6.Run();
         * System.console().readLine("< Appuyez sur ENTER pour continuer >");
         * 
         * System.out.println("Exercice 7");
         * System.out.println("==========");
         * Exercice7 exercice7 = exercices.new Exercice7();
         * exercice7.Run();
         * System.out.println("displayAuteurs()");
         * exercice7.displayAuteurs();
         * System.console().readLine("< Appuyez sur ENTER pour continuer >");
         * 
         * System.out.println("Exercice 8");
         * System.out.println("==========");
         * Exercice8 exercice8 = exercices.new Exercice8();
         * exercice8.Run();
         * System.console().readLine("< Appuyez sur ENTER pour continuer >");
         * 
         * System.out.println("Exercice 9");
         * System.out.println("==========");
         * Exercice9 exercice9 = exercices.new Exercice9();
         * exercice9.Run();
         * System.console().readLine("< Appuyez sur ENTER pour continuer >");
         * 
         * System.out.println("Exercice 10");
         * System.out.println("==========");
         * Exercice10 exercice10 = exercices.new Exercice10();
         * exercice10.Run();
         * System.console().readLine("< Appuyez sur ENTER pour continuer >");
         * 
         * System.out.println("Exercice 11");
         * System.out.println("==========");
         * Exercice11 exercice11 = exercices.new Exercice11();
         * exercice11.Run();
         * System.console().readLine("< Appuyez sur ENTER pour continuer >");
         

        System.out.println("Exercice 12");
        System.out.println("==========");
        Exercice12 exercice12 = exercices.new Exercice12();
        exercice12.Run();
        System.console().readLine("< Appuyez sur ENTER pour continuer >");*/



        System.out.println("Exercice 12");
        System.out.println("==========");
        Exercice12bis exercice12bis = exercices.new Exercice12bis();
        exercice12bis.Run();
        System.console().readLine("< Appuyez sur ENTER pour continuer >");
    }

    public class Exercice3 {
        public void Run() {
            try (DB db = new DB()) {
                Connection con = db.getConnection();

                // Création d'un objet Statement pour exécuter une requête de lecture
                Statement stmt = con.createStatement();

                // Exécution d'une requête de lecture
                // et récupération du résultat dans un objet ResultSet
                String query = "SELECT * FROM theme";
                ResultSet rs = stmt.executeQuery(query);

                // Parcours du résultat
                while (rs.next()) {
                    System.out.println(rs.getInt("id") + "\t\t" + rs.getString("theme"));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public class Exercice4 {
        public void Run() {
            displayThemes();
        }

        private void displayThemes() {
            try (DB db = new DB()) {
                Connection con = db.getConnection();

                // Création d'un objet Statement pour exécuter une requête de lecture
                Statement stmt = con.createStatement();

                // Exécution d'une requête de lecture
                // et récupération du résultat dans un objet ResultSet
                String query = "SELECT * FROM lecteur";
                ResultSet rs = stmt.executeQuery(query);

                // Parcours du résultat
                while (rs.next()) {
                    System.out.println(rs.getString("nom") + "\t\t" + rs.getString("prenom"));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public class Exercice5 {
        public void Run() {
            displayAuteurs();
        }

        private void displayAuteurs() {
            try (DB db = new DB()) {
                Connection con = db.getConnection();

                // Création d'un objet Statement pour exécuter une requête de lecture
                Statement stmt = con.createStatement();

                // Exécution d'une requête de lecture
                // et récupération du résultat dans un objet ResultSet
                String query = "SELECT * FROM auteur";
                ResultSet rs = stmt.executeQuery(query);

                // Parcours du résultat
                while (rs.next()) {
                    System.out.println(rs.getString("nom") + "\t\t" + rs.getString("prenom"));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public class Exercice6 {
        public void Run() {
            System.out.print(" ");
            Scanner sc = new Scanner(System.in);
            Integer id = Input.getValidInt("Identifiant de l'auteur à afficher :", sc);
            displayAuteur(id);
        }

        private void displayAuteur(Integer id) {
            try (DB db = new DB()) {
                Connection con = db.getConnection();

                // Création d'un objet Statement pour exécuter une requête de lecture
                Statement stmt = con.createStatement();

                // Exécution d'une requête de lecture
                // et récupération du résultat dans un objet ResultSet
                String query = "SELECT * FROM auteur WHERE id = " + id;
                ResultSet rs = stmt.executeQuery(query);

                // Parcours du résultat
                while (rs.next()) {
                    System.out.println(rs.getString("nom") + "\t\t" + rs.getString("prenom"));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        private void displayAuteurs() {
            try (DB db = new DB()) {
                Connection con = db.getConnection();

                // Création d'un objet Statement pour exécuter une requête de lecture
                Statement stmt = con.createStatement();

                // Exécution d'une requête de lecture
                // et récupération du résultat dans un objet ResultSet
                String query = "SELECT * FROM auteur";
                ResultSet rs = stmt.executeQuery(query);

                // Parcours du résultat
                while (rs.next()) {
                    System.out.println(rs.getString("nom") + "\t\t" + rs.getString("prenom"));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public class Exercice7 {
        public void Run() {
            System.out.print(" ");
            Scanner sc = new Scanner(System.in);
            Integer id = Input.getValidInt("Identifiant de l'auteur à afficher :", sc);
            displayAuteur(id);
        }

        private void displayAuteur(Integer id) {
            displayAuteursCommon("SELECT * FROM auteur WHERE id = " + id);
        }

        private void displayAuteurs() {
            displayAuteursCommon("SELECT * FROM auteur");
        }

        private void displayAuteursCommon(String query) {
            try (DB db = new DB()) {
                Connection con = db.getConnection();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    System.out.println(rs.getString("nom") + "\t\t" + rs.getString("prenom"));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public class Exercice8 {
        public void Run() {
            System.out.print(" ");
            Scanner sc = new Scanner(System.in);
            System.out.print("Rayon de l'exemplaire à afficher :");
            String rayon = sc.next();
            System.out.print("Etat de l'exemplaire à afficher :");
            String etat = sc.next();
            displayExemplaires(rayon, etat);
        }

        private void displayExemplaires(String rayon, String etat) {
            try (DB db = new DB()) {
                Connection con = db.getConnection();

                // Création d'un objet Statement pour exécuter une requête de lecture
                Statement stmt = con.createStatement();

                // Exécution d'une requête de lecture
                // et récupération du résultat dans un objet ResultSet
                String query = "SELECT * " +
                        "FROM exemplaire " +
                        "WHERE rayon = '" + rayon + "' AND etat = '" + etat + "'";
                ResultSet rs = stmt.executeQuery(query);

                System.out.println("id\t\tlivre_id\t\trayon\t\tetat");
                // Parcours du résultat
                while (rs.next()) {
                    // System.out.println("id:"+rs.getString("id")+"\t\tlivre_id:"+rs.getString("livre_id")+"\t\trayon:"+
                    // rs.getString("rayon") + "\t\tetat:" + rs.getString("etat"));
                    System.out.println(rs.getString("id") + "\t\t" + rs.getString("livre_id") + "\t\t"
                            + rs.getString("rayon") + "\t\t" + rs.getString("etat"));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public class Exercice9 {
        public void Run() {
            System.out.print(" ");
            Scanner sc = new Scanner(System.in);
            System.out.print("Rayon de l'exemplaire à afficher :");
            String rayon = sc.next();
            System.out.print("Etat de l'exemplaire à afficher :");
            String etat = sc.next();
            displayExemplaires(rayon, etat);
        }

        private void displayExemplaires(String rayon, String etat) {
            try (DB db = new DB()) {
                Connection con = db.getConnection();

                // Création d'un objet Statement pour exécuter une requête de lecture
                Statement stmt = con.createStatement();

                // Exécution d'une requête de lecture
                // et récupération du résultat dans un objet ResultSet
                String query = "SELECT livre.titre, exemplaire.id, rayon, etat " +
                        "FROM exemplaire " +
                        "INNER JOIN livre ON exemplaire.livre_id = livre.id " +
                        "WHERE rayon = '" + rayon + "' AND etat = '" + etat + "'";
                ResultSet rs = stmt.executeQuery(query);

                System.out.println("id\t\ttitre\t\trayon\t\tetat");
                // Parcours du résultat
                while (rs.next()) {
                    // System.out.println("id:"+rs.getString("id")+"\t\ttitre:"+rs.getString("livre.titre")+"\t\trayon:"+
                    // rs.getString("rayon") + "\t\tetat:" + rs.getString("etat"));
                    System.out.println(rs.getString("id") + "\t\t" + rs.getString("titre") + "\t\t"
                            + rs.getString("rayon") + "\t\t" + rs.getString("etat"));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public class Exercice10 {
        public void Run() {
            System.out.print(" ");
            Scanner sc = new Scanner(System.in);
            System.out.print("Rayon de l'exemplaire à afficher :");
            String rayon = sc.next();
            System.out.print("Etat de l'exemplaire à afficher :");
            String etat = sc.next();
            displayExemplaires(rayon, etat);
        }

        private void displayExemplaires(String rayon, String etat) {
            try (DB db = new DB()) {
                Connection con = db.getConnection();

                String query = "SELECT livre.titre, exemplaire.id, rayon, etat, auteur.nom, auteur.prenom " +
                        "FROM exemplaire " +
                        "INNER JOIN livre ON exemplaire.livre_id = livre.id " +
                        "INNER JOIN auteur ON livre.auteur_id = auteur.id " +
                        "WHERE rayon = '" + rayon + "' AND etat = '" + etat + "'";

                Statement statement = con.createStatement();

                ResultSet rs = statement.executeQuery(query);

                System.out.println("exemplaire_id\t\ttitre\t\tauteur.nom\t\tauteur.prenom\t\trayon\t\tetat");
                // Parcours du résultat
                while (rs.next()) {
                    // System.out.println("exemplaire_id:"+rs.getString("id")+"\t\ttitre:"+rs.getString("livre.titre")+"\t\tauteur_nom:"+rs.getString("auteur.nom")+"\t\tauteur_prenom:"+rs.getString("auteur.prenom")+"\t\trayon:"+
                    // rs.getString("rayon") + "\t\tetat:" + rs.getString("etat"));
                    System.out.println(rs.getString("id") + "\t\t" + rs.getString("titre") + "\t\t"
                            + rs.getString("auteur.nom") + "\t\t" + rs.getString("auteur.prenom") + "\t\t"
                            + rs.getString("rayon") + "\t\t" + rs.getString("etat"));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public class Exercice11 {
        public void Run() {
            System.out.print(" ");
            Scanner sc = new Scanner(System.in);
            System.out.print("Rayon de l'exemplaire à afficher :");
            String rayon = sc.next();
            System.out.print("Etat de l'exemplaire à afficher :");
            String etat = sc.next();
            displayExemplaires(rayon, etat);
        }

        private void displayExemplaires(String rayon, String etat) {
            rayon = rayon.startsWith("rayon-") ? rayon.substring(6) : "rayon-" + rayon;
            try (DB db = new DB()) {
                Connection con = db.getConnection();
                String query = "SELECT livre.titre, exemplaire.id, rayon, etat, auteur.nom, auteur.prenom " +
                        "FROM exemplaire " +
                        "INNER JOIN livre ON exemplaire.livre_id = livre.id " +
                        "INNER JOIN auteur ON livre.auteur_id = auteur.id " +
                        "WHERE rayon = ? AND etat = ?";

                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, rayon);
                preparedStatement.setString(2, etat);

                ResultSet rs = preparedStatement.executeQuery();

                System.out.println("exemplaire_id\t\ttitre\t\tauteur.nom\t\tauteur.prenom\t\trayon\t\tetat");
                // Parcours du résultat
                while (rs.next()) {
                    // System.out.println("exemplaire_id:"+rs.getString("id")+"\t\ttitre:"+rs.getString("livre.titre")+"\t\tauteur_nom:"+rs.getString("auteur.nom")+"\t\tauteur_prenom:"+rs.getString("auteur.prenom")+"\t\trayon:"+
                    // rs.getString("rayon") + "\t\tetat:" + rs.getString("etat"));
                    System.out.println(rs.getString("id") + "\t\t" + rs.getString("titre") + "\t\t"
                            + rs.getString("auteur.nom") + "\t\t" + rs.getString("auteur.prenom") + "\t\t"
                            + rs.getString("rayon") + "\t\t" + rs.getString("etat"));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public class Exercice12 {
        public void Run() {
            Scanner sc = new Scanner(System.in);
            System.out.println("AUTEUR A MODIFIER");
            System.out.println("=================");
            System.out.print("Nom de l'auteur à modifier:");
            String nom = sc.next();
            displayAuteursByName(nom);
            System.out.print("Id de l'auteur à modifier:");
            int id = sc.nextInt();
            displayAuteur(id);
            System.out.print("Nouveau nom:");
            String newNom = sc.next();
            System.out.print("Nouveau prénom:");
            String newPrenom = sc.next();
            LocalDate newDateNaissance = Input.getValidLocalDate("Nouvelle date de naissance:", sc);
            System.out.print("Nouvelle nationalité:");
            String newPays = sc.next();
            updateAuteur(id, newNom, newPrenom, newDateNaissance, newPays);
            displayAuteur(id);
        }

        private void updateAuteur(int id, String newNom, String newPrenom, LocalDate newDate_naissance,
                String newNationalite) {
            Auteur auteur = new Auteur(id, newNom, newPrenom, newDate_naissance, newNationalite);
            updateAuteur(auteur);
        }

        private void updateAuteur(Auteur auteur) {
            try (Connection con = new DB().getConnection()) {
                PreparedStatement preparedStatement = con.prepareStatement(
                        "UPDATE auteur SET nom = ?, prenom = ?, date_naissance = ?, nationalite = ? WHERE id = ?");
                preparedStatement.setString(1, auteur.getNom());
                preparedStatement.setString(2, auteur.getPrenom());
                preparedStatement.setDate(3, Date.valueOf(auteur.getDate_naissance()));
                preparedStatement.setString(4, auteur.getNationalite());
                preparedStatement.setInt(5, auteur.getId());

                preparedStatement.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        private void displayAuteursByName(String nom) {
            try (Connection con = new DB().getConnection()) {
                PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM auteur WHERE nom LIKE ?");
                preparedStatement.setString(1, nom + "%");

                displayAuteurCommon(preparedStatement);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        private void displayAuteur(int id) {
            try (Connection con = new DB().getConnection()) {
                PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM auteur WHERE id = ?");
                preparedStatement.setInt(1, id);

                displayAuteurCommon(preparedStatement);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        // On pourrait aussi faire une méthode displayAuteur(Auteur auteur) qui appelle
        // displayAuteur(int id)
        // Mais ça ne serait pas très utile car ça ne sert à rien de récupèrer l'auteur
        // dans la base de données car on l'a déjà
        /*
         * private void displayAuteur(Auteur auteur) {
         * displayAuteur(auteur.getId());
         * }
         */

        // On va plutôt afficher auteur.toString()
        private void displayAuteur(Auteur auteur) {
            System.out.println(auteur.toString());
        }

        private void displayAuteurCommon(PreparedStatement preparedStatement) throws SQLException {
            try (ResultSet rs = preparedStatement.executeQuery();) {
                System.out.println("id\t\tnom\t\tprenom\\t\tdate_naissance\t\tnationalite");
                // Parcours du résultat
                while (rs.next()) {
                    System.out
                            .println(rs.getString("id") + "\t\t" + rs.getString("nom") + "\t\t" + rs.getString("prenom")
                                    + "\t\t" + rs.getString("date_naissance") + "\t\t" + rs.getString("nationalite"));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public class Exercice12bis {
        public void Run() {
            Scanner sc = new Scanner(System.in);
            System.out.println("AUTEUR A MODIFIER");
            System.out.println("=================");
            System.out.print("Nom de l'auteur à modifier:");
            String nom = sc.next();
            List<Auteur> auteurs = getAuteursByName(nom);
            if (auteurs.size() > 0) {
                displayAuteurs(auteurs);
                int id = Input.getValidInt("Id de l'auteur à modifier:", sc);
                Auteur auteur = getAuteur(id);
                if (auteur != null) {
                    displayAuteur(auteur);
                    System.out.print("Nouveau nom:");
                    String newNom = sc.next();
                    System.out.print("Nouveau prénom:");
                    String newPrenom = sc.next();
                    LocalDate newDateNaissance = Input.getValidLocalDate("Nouvelle date de naissance:", sc);
                    System.out.print("Nouvelle nationalité:");
                    String newPays = sc.next();
                    updateAuteur(id, newNom, newPrenom, newDateNaissance, newPays);
                    displayAuteur(id);
                }
                else {
                    System.out.println("Auteur non trouvé");
                }
            }
            else {
                System.out.println("Auteur non trouvé");
            }
        }

        private Auteur getAuteur(int id) {
            Auteur auteur = null;
            try (Connection con = new DB().getConnection()) {
                PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM auteur WHERE id = ?");
                preparedStatement.setInt(1, id);

                ResultSet rs = preparedStatement.executeQuery();
                // Parcours du résultat
                if (rs.next()) {
                    auteur = new Auteur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
                            rs.getDate("date_naissance").toLocalDate(), rs.getString("nationalite"));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            return auteur;
        }

        private void updateAuteur(int id, String newNom, String newPrenom, LocalDate newDate_naissance,
                String newNationalite) {
            Auteur auteur = new Auteur(id, newNom, newPrenom, newDate_naissance, newNationalite);
            updateAuteur(auteur);
        }

        private void updateAuteur(Auteur auteur) {
            try (Connection con = new DB().getConnection()) {
                PreparedStatement preparedStatement = con.prepareStatement(
                        "UPDATE auteur SET nom = ?, prenom = ?, date_naissance = ?, nationalite = ? WHERE id = ?");
                preparedStatement.setString(1, auteur.getNom());
                preparedStatement.setString(2, auteur.getPrenom());
                preparedStatement.setDate(3, Date.valueOf(auteur.getDate_naissance()));
                preparedStatement.setString(4, auteur.getNationalite());
                preparedStatement.setInt(5, auteur.getId());

                preparedStatement.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        private List<Auteur> getAuteursByName(String nom) {
            List<Auteur> auteurs = new ArrayList<>();
            try (Connection con = new DB().getConnection()) {
                PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM auteur WHERE nom LIKE ?");
                preparedStatement.setString(1, nom + "%");

                ResultSet rs = preparedStatement.executeQuery();
                // Parcours du résultat
                while (rs.next()) {
                    auteurs.add(new Auteur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
                            rs.getDate("date_naissance").toLocalDate(), rs.getString("nationalite")));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            return auteurs;
        }

        private void displayAuteurs(List<Auteur> auteurs) {
            System.out.println("id\t\tnom\t\tprenom\\t\tdate_naissance\t\tnationalite");
            // Parcours du résultat
            for (Auteur auteur : auteurs) {
                System.out.println(auteur.toString());
            }
        }

        private void displayAuteur(int id) {
            try (Connection con = new DB().getConnection()) {
                PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM auteur WHERE id = ?");
                preparedStatement.setInt(1, id);

                displayAuteurCommon(preparedStatement);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        private void displayAuteur(Auteur auteur) {
            System.out.println(auteur.toString());
        }

        private void displayAuteurCommon(PreparedStatement preparedStatement) throws SQLException {
            try (ResultSet rs = preparedStatement.executeQuery();) {
                System.out.println("id\t\tnom\t\tprenom\\t\tdate_naissance\t\tnationalite");
                // Parcours du résultat
                while (rs.next()) {
                    System.out
                            .println(rs.getString("id") + "\t\t" + rs.getString("nom") + "\t\t" + rs.getString("prenom")
                                    + "\t\t" + rs.getString("date_naissance") + "\t\t" + rs.getString("nationalite"));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public class Exercice12bis2 {
        public void Run() {
            Scanner sc = new Scanner(System.in);
            System.out.println("AUTEUR A MODIFIER");
            System.out.println("=================");
            System.out.print("Nom de l'auteur à modifier:");
            String nom = sc.next();
            List<Auteur> auteurs = AuteurDAO.getAuteursByName(nom);
            if (auteurs.size() > 0) {
                displayAuteurs(auteurs);
                int id = Input.getValidInt("Id de l'auteur à modifier:", sc);
                Auteur auteur = getAuteur(id);
                if (auteur != null) {
                    displayAuteur(auteur);
                    System.out.print("Nouveau nom:");
                    String newNom = sc.next();
                    System.out.print("Nouveau prénom:");
                    String newPrenom = sc.next();
                    LocalDate newDateNaissance = Input.getValidLocalDate("Nouvelle date de naissance:", sc);
                    System.out.print("Nouvelle nationalité:");
                    String newPays = sc.next();
                    updateAuteur(id, newNom, newPrenom, newDateNaissance, newPays);
                    displayAuteur(id);
                }
                else {
                    System.out.println("Auteur non trouvé");
                }
            }
            else {
                System.out.println("Auteur non trouvé");
            }
        }

        private Auteur getAuteur(int id) {
            Auteur auteur = null;
            try (Connection con = new DB().getConnection()) {
                PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM auteur WHERE id = ?");
                preparedStatement.setInt(1, id);

                ResultSet rs = preparedStatement.executeQuery();
                // Parcours du résultat
                if (rs.next()) {
                    auteur = new Auteur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
                            rs.getDate("date_naissance").toLocalDate(), rs.getString("nationalite"));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            return auteur;
        }

        private void updateAuteur(int id, String newNom, String newPrenom, LocalDate newDate_naissance,
                String newNationalite) {
            Auteur auteur = new Auteur(id, newNom, newPrenom, newDate_naissance, newNationalite);
            updateAuteur(auteur);
        }

        private void updateAuteur(Auteur auteur) {
            try (Connection con = new DB().getConnection()) {
                PreparedStatement preparedStatement = con.prepareStatement(
                        "UPDATE auteur SET nom = ?, prenom = ?, date_naissance = ?, nationalite = ? WHERE id = ?");
                preparedStatement.setString(1, auteur.getNom());
                preparedStatement.setString(2, auteur.getPrenom());
                preparedStatement.setDate(3, Date.valueOf(auteur.getDate_naissance()));
                preparedStatement.setString(4, auteur.getNationalite());
                preparedStatement.setInt(5, auteur.getId());

                preparedStatement.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        private void displayAuteurs(List<Auteur> auteurs) {
            System.out.println("id\t\tnom\t\tprenom\\t\tdate_naissance\t\tnationalite");
            // Parcours du résultat
            for (Auteur auteur : auteurs) {
                System.out.println(auteur.toString());
            }
        }

        private void displayAuteur(int id) {
            try (Connection con = new DB().getConnection()) {
                PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM auteur WHERE id = ?");
                preparedStatement.setInt(1, id);

                displayAuteurCommon(preparedStatement);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        private void displayAuteur(Auteur auteur) {
            System.out.println(auteur.toString());
        }

        private void displayAuteurCommon(PreparedStatement preparedStatement) throws SQLException {
            try (ResultSet rs = preparedStatement.executeQuery();) {
                System.out.println("id\t\tnom\t\tprenom\\t\tdate_naissance\t\tnationalite");
                // Parcours du résultat
                while (rs.next()) {
                    System.out
                            .println(rs.getString("id") + "\t\t" + rs.getString("nom") + "\t\t" + rs.getString("prenom")
                                    + "\t\t" + rs.getString("date_naissance") + "\t\t" + rs.getString("nationalite"));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }


}