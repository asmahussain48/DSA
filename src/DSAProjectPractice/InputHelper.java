package DSAProjectPractice;

import java.util.Scanner;

public class InputHelper {

    private Scanner scanner;

    public InputHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readValidName(String fieldName) {
        int attempts = 0;

        while (attempts < 3) {
            String input = scanner.nextLine().trim();

            if (input.matches("[A-Za-z ]+") && input.length() > 1) {
                return input;
            }

            attempts++;
            if (attempts < 3) {
                System.out.print("Invalid " + fieldName + ". Enter again: ");
            }
        }

        System.out.println("Too many invalid attempts.");
        return null;
    }

    public String readValidCategory() {
        int attempts = 0;

        while (attempts < 3) {
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("Sedan")
                    || input.equalsIgnoreCase("SUV")
                    || input.equalsIgnoreCase("Hatchback")) {
                return input;
            }

            attempts++;
            if (attempts < 3) {
                System.out.print("Invalid category. Enter Sedan, SUV or Hatchback: ");
            }
        }

        System.out.println("Too many invalid attempts.");
        return null;
    }

    public int readInt() {
        int attempts = 0;

        while (attempts < 3) {
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                attempts++;
                if (attempts < 3) {
                    System.out.print("Please enter a valid number: ");
                }
            }
        }

        System.out.println("Too many invalid attempts.");
        return -1;
    }

    public long readLong() {
        int attempts = 0;

        while (attempts < 3) {
            String input = scanner.nextLine();
            try {
                return Long.parseLong(input);
            } catch (NumberFormatException e) {
                attempts++;
                if (attempts < 3) {
                    System.out.print("Please enter a valid number: ");
                }
            }
        }

        System.out.println("Too many invalid attempts.");
        return -1;
    }
}
