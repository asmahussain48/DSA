package DSAProjectPractice;

import java.util.ArrayList;

public class Printer {

    private static final String LINE =
            "-------------------------------------------------------------------------------------";

    public static void printCars(ArrayList<Car> cars) {
        if (cars == null || cars.isEmpty()) {
            System.out.println("No cars to display.");
            return;
        }

        printHeader();

        for (Car c : cars) {
            printCar(c);
        }
    }

    public static void printHeader() {
        System.out.printf(
                "%-20s %-35s %-12s %-8s %-12s%n",
                "Brand", "Model", "Category", "Year", "Price"
        );
        System.out.println(LINE);
    }

    public static void printCar(Car c) {
        System.out.printf(
                "%-20s %-35s %-12s %-8d %-12d%n",
                c.getBrand(),
                c.getModel(),
                c.getCategory(),
                c.getYear(),
                c.getPrice()
        );
    }
}
