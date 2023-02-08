package Exemples.user;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Input {
    // Ne faites pas trop attention à cette fonction, elle permet de récupérer un nombre valide
    // Elle est générique, elle peut donc être utilisée pour récupérer un nombre entier, un nombre décimal, etc.
    // Elle utilise la réflexion pour appeler la méthode parseXxx de la classe Number (parseByte, parseShort, parseInt, parseLong, parseFloat, parseDouble, parseBigDecimal)
    // Elle utilise aussi la réflexion pour récupérer le type de retour de la méthode parseXxx (Byte, Short, Integer, Long, Float, Double, BigDecimal)
    // Elle utilise enfin la réflexion pour caster le résultat de la méthode parseXxx vers le type de retour de la méthode parseXxx
    // C'est un peu compliqué, mais ça permet d'écrire une seule fonction qui peut être utilisée pour récupérer n'importe quel nombre: entier, décimal, etc.
    // Vous pouvez aussi utiliser la fonction getValidInt pour récupérer un nombre entier valide mais pas de nombre décimal, etc.
    //
    // Elle est dite générique car elle peut être utilisée avec n'importe quel type de nombre (entier, décimal, etc.)

    public static <T extends Number> T getValidNumber(String message, Scanner scanner, Class<T> clazz) {
        if (scanner == null)
            scanner = new Scanner(System.in);
        while (true) {
            System.out.print(message);
            if (scanner.hasNext()) {
                try {
                    String input = scanner.nextLine();
                    Method method = clazz.getMethod("parse" + clazz.getSimpleName(), String.class);
                    return clazz.cast(method.invoke(null, input));
                } catch (Exception e) {
                    System.out.println("Votre saisie n'est pas valide, veuillez réessayer.");
                    scanner.next();
                }
            }
        }
    }
    
    public static String getValidString(String message, Scanner scanner, String... validChoices) {
        if (scanner == null)
            scanner = new Scanner(System.in);
        while (true) {
            System.out.print(message);
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                for (String choice : validChoices) {
                    if (input.equalsIgnoreCase(choice)) {
                        return choice;
                    }
                }
                System.out.println("Votre saisie n'est pas valide, veuillez réessayer.");
            }
        }
    }

    public static int getValidInt(String message, Scanner scanner) {
        if (scanner == null)
            scanner = new Scanner(System.in);
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                //return scanner.nextInt();
                return Integer.parseInt(scanner.nextLine());
            } else {
                System.out.println("Votre saisie n'est pas un nombre, veuillez réessayer.");
                scanner.next();
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
}
