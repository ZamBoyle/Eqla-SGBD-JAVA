package Exemples.Chapitre5;

import java.sql.*;
import java.util.Scanner;

import Exemples.dal.DB;
import Exemples.user.Input;

public class Exemple9 {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        addLecteur();
    } 

    public static void addLecteur(){
        scanner = new Scanner(System.in);
        System.out.println("Ajout d'un lecteur");
        System.out.println("==================");
        try (DB db = new DB()) {
            Connection con = db.getConnection();
            
       } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}