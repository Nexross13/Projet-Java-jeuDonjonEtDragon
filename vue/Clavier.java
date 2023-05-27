package vue;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Clavier {
    private static Scanner scanner = new Scanner(System.in);

    public static int entrerClavierInt() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Vous devez entrer un entier.");
            scanner.next(); // On vide la ligne invalide dans le buffer
            return entrerClavierInt(); // On rappelle la méthode pour une nouvelle saisie
        }
    }

    public static String entrerClavierString() {
        return scanner.next();
    }

}
