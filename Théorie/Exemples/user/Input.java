package Exemples.user;

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
                scanner.next();
            }
        }
    }

    public static int getValidInt(String message) {
        return getValidInt(message, null);
    }

    public static int getValidInt(String message, int min, int max, Scanner scanner) {
        if (scanner == null)
            scanner = new Scanner(System.in);
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                int i = scanner.nextInt();
                if (i >= min && i <= max) {
                    return i;
                } else {
                    System.out.println("Veuillez entrer un nombre entre " + min + " et " + max);
                }
            } else {
                System.out.println("Veuillez entrer un nombre valide");
                scanner.next();
            }
        }
    }

    public static int getValidInt(String message, int min, int max) {
        return getValidInt(message, min, max, null);
    }
}
