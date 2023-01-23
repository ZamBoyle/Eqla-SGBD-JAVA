package Exemples.user;

import java.sql.Date;
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

    public static java.util.Date getValidDate(String message, Scanner scanner) {
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

    public static java.sql.Date getSqlDate(java.util.Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
            String strDate = sdf.format(date);
			java.util.Date dateUtil = sdf.parse(strDate);
			java.sql.Date dateSql = new Date(dateUtil.getTime());
			System.out.println("Date java.util.Date : " + dateUtil);
			System.out.println("Date java.sql.Date : " + dateSql);
            return new java.sql.Date(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return null;
    }


}
