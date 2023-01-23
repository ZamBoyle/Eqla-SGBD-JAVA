package Exemples.Chapitre5;

import java.sql.*;
import java.util.Scanner;

import Exemples.user.Input;

public class Exemple7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ajout d'un lecteur");
        System.out.println("==================");
        try (Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/biblio4_prof", "new_user",
                "password1")) {
            // Création d'un objet PreparedStatement pour exécuter une requête d'INSERT
            PreparedStatement pstmt = con.prepareStatement(
                    "INSERT INTO lecteur (nom,prenom,date_naissance,adresse,code_postal,num_rue, localite,telephone) VALUES (?,?,?,?,?,?,?,?)");

            // nom
            System.out.print("Nom : ");
            pstmt.setString(1, scanner.nextLine());

            // prenom
            System.out.print("Prenom : ");
            pstmt.setString(2, scanner.nextLine());

            // date de naissance
            java.util.Date date =  Input.getValidDate("Date de naissance :", scanner);
            pstmt.setObject(3, date);

            // adresse
            System.out.print("Adresse : ");
            pstmt.setString(4, scanner.nextLine());

            // num_rue
            pstmt.setInt(5, Input.getValidInt("Numéro d'habitation : ", scanner));
            scanner.nextLine(); // pour vider le buffer: on a lu un int

            // code_postal
            pstmt.setInt(6, Input.getValidInt("Code postal : ", scanner));
            scanner.nextLine(); // pour vider le buffer: on a lu un int

            // localite
            System.out.print("Localité : ");
            pstmt.setString(7, scanner.nextLine());

            // telephone
            System.out.print("Téléphone : ");
            pstmt.setString(8, scanner.nextLine());

            // Exécution d'une requête d'INSERT
            // et récupération du nombre d'enregistrements ajoutés: ici 1 si tout s'est bien passé
            int nbEnregistrements = pstmt.executeUpdate();

            if (nbEnregistrements > 0)
                System.out.println("1 lecteur a été ajouté.");
            else
                System.out.println("Aucun lecteur n'a été ajouté.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}