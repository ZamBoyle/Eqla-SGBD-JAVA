package Exercices.user;

import java.util.Scanner;

public class Input {

    public static int getValidInt(String message) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                scanner.close();
                return scanner.nextInt();
            } else {
                System.out.println("Veuillez entrer un nombre valide");
                scanner.next();
            }
        }
    }

    public static int getValidInt(String message, int min, int max) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                int i = scanner.nextInt();
                if (i >= min && i <= max) {
                    scanner.close();
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
}