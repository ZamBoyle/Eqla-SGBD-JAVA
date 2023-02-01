package Exemples.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Input {

    public static int getValidInt(String message, Scanner scanner) {
        if (scanner == null)
            scanner = new Scanner(System.in);
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Veuillez entrer un nombre valide");
                scanner.nextInt();
            }
        }
    }

    public static int getValidInt(String message) {
        return getValidInt(message, null);
    }

    public static int getValidInt(String message, int min, int max, Scanner scanner) {
        int input = getValidInt(message, scanner);
        while (input < min || input > max) {
            System.out.println("Veuillez entrer un nombre entre " + min + " et " + max);
            input = getValidInt(message, scanner);
        }
        return input;
    }

    public static int getValidInt(String message, int min, int max) {
        return getValidInt(message, min, max, null);
    }

    //avec une récursivité
    public static java.util.Date getValidDate(String message, Scanner scanner) {
        if (scanner == null)
            scanner = new Scanner(System.in);
        System.out.print(message);
        String input = scanner.nextLine();
        
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return format.parse(input);
        } catch (ParseException e) {
            System.out.println("Format de date invalide, veuillez réessayer.");
            return getValidDate(message, scanner);
        }
    }

    //avec un while 
/*     public static java.util.Date getValidDate(String message, Scanner scanner) {
        if (scanner == null)
            scanner = new Scanner(System.in);
        
        java.util.Date date = null;
        while (date == null) {
            System.out.print(message);
            String input = scanner.nextLine();

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                date = format.parse(input);
            } catch (ParseException e) {
                System.out.println("Format de date invalide, veuillez réessayer.");
            }
        }
        return date;
    }
 */
    public static java.util.Date getValidDate(String message) {
        return getValidDate(message, null);
    }

    public static java.sql.Date getSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }
}
