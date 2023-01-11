package Exercices.user;

public class Input {
    public static int getValidInt(String message) {
        int i = 0;
        boolean valid = false;
        while (!valid) {
            try {
                i = Integer.parseInt(System.console().readLine(message));
                valid = true;
            } catch (Exception e) {
                System.out.println("Veuillez entrer un nombre valide");
            }
        }
        return i;
    }
}
