package Exemples.Chapitre8;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Scanner;

public class ExempleY {
    private static final String DATE_PATTERN = "dd/MM/yyyy";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Veuillez saisir une date au format "+DATE_PATTERN+":");
            String dateStr = sc.nextLine();
            try {
                java.time.LocalDate localDate = LocalDate.parse(dateStr, DATE_FORMAT);
                java.sql.Date dateSql =Date.valueOf(localDate);
                System.out.println("Date reçue: "+dateSql);
            } catch (Exception e) {
                System.out.println("Erreur lors du parse de la chaîne reçue:"+dateStr);
            }
        } 
    }
}