package Exemples.Chapitre8;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Exemple9 {
    private static final String DATE_FORMAT = "dd/MM/yyyy";

    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Veuillez saisir une date au format "+DATE_FORMAT);
            String dateStr = sc.nextLine();
            try {
                java.util.Date dateUtil = format.parse(dateStr);
                java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
                System.out.println("Date reçue: "+dateSql);
            } catch (ParseException e) {
                System.out.println("Erreur lors du parse de la chaîne reçue:"+dateStr);
            }
        }
    }
}
