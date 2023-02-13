package Exercices;

import java.sql.*;
import java.util.Scanner;

import Exemples.user.Input;
import Exercices.dal.DB;

public class Exercices {

    public static void main(String[] args) {
        Exercices exercices = new Exercices();
        
        /* System.out.println("Exercice 3");
        System.out.println("==========");
        Exercice3 exercice3 = exercices.new Exercice3();
        exercice3.Run();
        System.console().readLine("< Appuyez sur ENTER pour continuer >");
         
        System.out.println("Exercice 4");
        System.out.println("==========");
        Exercice4 exercice4 = exercices.new Exercice4();
        exercice4.Run();
        System.console().readLine("< Appuyez sur ENTER pour continuer >");
         
        System.out.println("Exercice 5");
        System.out.println("==========");
        Exercice5 exercice5 = exercices.new Exercice5();
        exercice5.Run();
        System.console().readLine("< Appuyez sur ENTER pour continuer >");

        System.out.println("Exercice 6");
        System.out.println("==========");
        Exercice6 exercice6 = exercices.new Exercice6();
        exercice6.Run();
        System.console().readLine("< Appuyez sur ENTER pour continuer >"); 

        System.out.println("Exercice 7");
        System.out.println("==========");
        Exercice7 exercice7 = exercices.new Exercice7();
        exercice7.Run();
        System.out.println("displayAuteurs()");
        exercice7.displayAuteurs();
        System.console().readLine("< Appuyez sur ENTER pour continuer >");

        System.out.println("Exercice 8");
        System.out.println("==========");
        Exercice8 exercice8 = exercices.new Exercice8();
        exercice8.Run();
        System.console().readLine("< Appuyez sur ENTER pour continuer >");

        System.out.println("Exercice 9");
        System.out.println("==========");
        Exercice9 exercice9 = exercices.new Exercice9();
        exercice9.Run();
        System.console().readLine("< Appuyez sur ENTER pour continuer >");

        System.out.println("Exercice 10");
        System.out.println("==========");
        Exercice10 exercice10 = exercices.new Exercice10();
        exercice10.Run();
        System.console().readLine("< Appuyez sur ENTER pour continuer >");*/

        System.out.println("Exercice 11");
        System.out.println("==========");
        Exercice11 exercice11 = exercices.new Exercice11();
        exercice11.Run();
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

    public class Exercice7{
        public void Run(){
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

        private void displayAuteursCommon(String query){
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

    public class Exercice8{
        public void Run(){
            System.out.print(" ");
            Scanner sc = new Scanner(System.in);
            System.out.print("Rayon de l'exemplaire à afficher :");
            String rayon = sc.next();
            System.out.print("Etat de l'exemplaire à afficher :");
            String etat =  sc.next();
            displayExemplaires(rayon,etat);
        }

        private void displayExemplaires(String rayon, String etat) {
            try (DB db = new DB()) {
                Connection con = db.getConnection();

                // Création d'un objet Statement pour exécuter une requête de lecture
                Statement stmt = con.createStatement();

                // Exécution d'une requête de lecture
                // et récupération du résultat dans un objet ResultSet
                String query = 
                    "SELECT * "+
                    "FROM exemplaire "+
                    "WHERE rayon = '" + rayon + "' AND etat = '" + etat + "'";
                ResultSet rs = stmt.executeQuery(query);

                // Parcours du résultat
                while (rs.next()) {
                    System.out.println("id:"+rs.getString("id")+"\t\tlivre_id:"+rs.getString("livre_id")+"\t\trayon:"+ rs.getString("rayon") + "\t\tetat:" + rs.getString("etat"));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public class Exercice9{
        public void Run(){
            System.out.print(" ");
            Scanner sc = new Scanner(System.in);
            System.out.print("Rayon de l'exemplaire à afficher :");
            String rayon = sc.next();
            System.out.print("Etat de l'exemplaire à afficher :");
            String etat =  sc.next();
            displayExemplaires(rayon,etat);
        }

        private void displayExemplaires(String rayon, String etat) {
            try (DB db = new DB()) {
                Connection con = db.getConnection();

                // Création d'un objet Statement pour exécuter une requête de lecture
                Statement stmt = con.createStatement();

                // Exécution d'une requête de lecture
                // et récupération du résultat dans un objet ResultSet
                String query = 
                    "SELECT livre.titre, exemplaire.id, rayon, etat "+
                    "FROM exemplaire "+
                    "INNER JOIN livre ON exemplaire.livre_id = livre.id "+                    
                    "WHERE rayon = '" + rayon + "' AND etat = '" + etat + "'";
                ResultSet rs = stmt.executeQuery(query);

                // Parcours du résultat
                while (rs.next()) {
                    System.out.println("id:"+rs.getString("id")+"\t\ttitre:"+rs.getString("livre.titre")+"\t\trayon:"+ rs.getString("rayon") + "\t\tetat:" + rs.getString("etat"));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public class Exercice10{
        public void Run(){
            System.out.print(" ");
            Scanner sc = new Scanner(System.in);
            System.out.print("Rayon de l'exemplaire à afficher :");
            String rayon = sc.next();
            System.out.print("Etat de l'exemplaire à afficher :");
            String etat =  sc.next();
            displayExemplaires(rayon,etat);
        }

        private void displayExemplaires(String rayon, String etat) {
            try (DB db = new DB()) {
                Connection con = db.getConnection();

                String query = 
                "SELECT livre.titre, exemplaire.id, rayon, etat, auteur.nom, auteur.prenom "+
                "FROM exemplaire "+
                "INNER JOIN livre ON exemplaire.livre_id = livre.id "+
                "INNER JOIN auteur ON livre.auteur_id = auteur.id "+                    
                "WHERE rayon = '" + rayon + "' AND etat = '" + etat + "'";
                
                PreparedStatement preparedStatement = con.prepareStatement(query);

                // Remplacement des paramètres
                preparedStatement.setString(1, rayon);
                preparedStatement.setString(2, etat);

                ResultSet rs = preparedStatement.executeQuery();

                // Parcours du résultat
                while (rs.next()) {
                    System.out.println("exemplaire_id:"+rs.getString("id")+"\t\ttitre:"+rs.getString("livre.titre")+"\t\tauteur_nom:"+rs.getString("auteur.nom")+"\t\tauteur_prenom:"+rs.getString("auteur.prenom")+"\t\trayon:"+ rs.getString("rayon") + "\t\tetat:" + rs.getString("etat"));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public class Exercice11{
        public void Run(){
            System.out.print(" ");
            Scanner sc = new Scanner(System.in);
            System.out.print("Rayon de l'exemplaire à afficher :");
            String rayon = sc.next();
            System.out.print("Etat de l'exemplaire à afficher :");
            String etat =  sc.next();
            displayExemplaires(rayon,etat);
        }

        private void displayExemplaires(String rayon, String etat) {
            try (DB db = new DB()) {
                Connection con = db.getConnection();

                String query = 
                    "SELECT livre.titre, exemplaire.id, rayon, etat, auteur.nom, auteur.prenom "+
                    "FROM exemplaire "+
                    "INNER JOIN livre ON exemplaire.livre_id = livre.id "+
                    "INNER JOIN auteur ON livre.auteur_id = auteur.id "+                         
                    "WHERE rayon = ? AND etat = ?";
                
                PreparedStatement preparedStatement = con.prepareStatement(query);

                // Remplacement des paramètres
                preparedStatement.setString(1, rayon);
                preparedStatement.setString(2, etat);

                ResultSet rs = preparedStatement.executeQuery();

                // Parcours du résultat
                while (rs.next()) {
                    System.out.println("exemplaire_id:"+rs.getString("id")+"\t\ttitre:"+rs.getString("livre.titre")+"\t\tauteur_nom:"+rs.getString("auteur.nom")+"\t\tauteur_prenom:"+rs.getString("auteur.prenom")+"\t\trayon:"+ rs.getString("rayon") + "\t\tetat:" + rs.getString("etat"));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public class Exercice12{
        public void Run(){
            System.out.print(" ");
            Scanner sc = new Scanner(System.in);
            System.out.print("Rayon de l'exemplaire à afficher :");
            String rayon = sc.next();
            System.out.print("Etat de l'exemplaire à afficher :");
            String etat =  sc.next();
            displayExemplaires(rayon,etat);
        }

        private void displayExemplaires(String rayon, String etat) {
            try (DB db = new DB()) {
                Connection con = db.getConnection();

                String query = 
                    "SELECT livre.titre, exemplaire.id, rayon, etat, auteur.nom, auteur.prenom "+
                    "FROM exemplaire "+
                    "INNER JOIN livre ON exemplaire.livre_id = livre.id "+
                    "INNER JOIN auteur ON livre.auteur_id = auteur.id "+                         
                    "WHERE rayon = ? AND etat = ?";
                
                PreparedStatement preparedStatement = con.prepareStatement(query);

                // Remplacement des paramètres
                preparedStatement.setString(1, rayon);
                preparedStatement.setString(2, etat);

                ResultSet rs = preparedStatement.executeQuery();

                // Parcours du résultat
                while (rs.next()) {
                    System.out.println("exemplaire_id:"+rs.getString("id")+"\t\ttitre:"+rs.getString("livre.titre")+"\t\tauteur_nom:"+rs.getString("auteur.nom")+"\t\tauteur_prenom:"+rs.getString("auteur.prenom")+"\t\trayon:"+ rs.getString("rayon") + "\t\tetat:" + rs.getString("etat"));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}