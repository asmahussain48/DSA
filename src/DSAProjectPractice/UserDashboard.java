package DSAProjectPractice;

import java.util.ArrayList;

public class UserDashboard {

    private CarsWorldApp app;

    public UserDashboard(CarsWorldApp app) {
        this.app = app;
    }

    public void menu() {
        int choice;

        do {
            System.out.println("\n===== USER DASHBOARD =====");
            System.out.println("1. Search Car By Brand");
            System.out.println("2. Search Car By Model");
            System.out.println("3. Search Car By Year");
            System.out.println("4. View All Cars");
            System.out.println("5. View All Cars Sorted By Price");
            System.out.println("6. Search Cars By Price Range");
            System.out.println("7. Show Top 3 Cheapest Cars");
            System.out.println("8. Show Top 3 Most Expensive Cars");
            System.out.println("9. Cost Calculator");
            System.out.println("10. Give Feedback");
            System.out.println("11. Exit to Main Menu");
            System.out.print("Enter choice: ");

            choice = app.input.readInt();

            switch (choice) {
                case 1:
                    searchCarByBrand();
                    break;
                case 2:
                    searchCarByModel();
                    break;
                case 3:
                    searchCarByYear();
                    break;
                case 4:
                    Printer.printCars(app.inventory.getAllCars());
                    break;
                case 5:
                    viewCarsSortedByPrice();
                    break;
                case 6:
                    searchCarsByPriceRange();
                    break;
                case 7:
                    showTopKCheapestCars(3);
                    break;
                case 8:
                    showTopKMostExpensiveCars(3);
                    break;
                case 9:
                    costCalculator();
                    break;
                case 10:
                    giveFeedback();
                    break;
                case 11:
                    System.out.println("Leaving user dashboard.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 11);
    }

    private void searchCarByBrand() {
        System.out.print("Enter Car Brand: ");
        String brand = app.scanner.nextLine();
        Printer.printCars(app.inventory.findByBrand(brand));
    }

    private void searchCarByModel() {
        System.out.print("Enter Car Model: ");
        String model = app.scanner.nextLine();
        Printer.printCars(app.inventory.findByModel(model));
    }

    private void searchCarByYear() {
        System.out.print("Enter Car Year: ");
        int year = app.input.readInt();

        ArrayList<Car> result = new ArrayList<>();
        for (Car c : app.inventory.getAllCars()) {
            if (c.getYear() == year) {
                result.add(c);
            }
        }
        Printer.printCars(result);
    }

    private void viewCarsSortedByPrice() {
        ArrayList<Car> list = new ArrayList<>();
        app.priceTree.inOrder(list);
        Printer.printCars(list);
    }

    private void searchCarsByPriceRange() {
        System.out.print("Enter minimum price: ");
        long min = app.input.readLong();

        System.out.print("Enter maximum price: ");
        long max = app.input.readLong();

        ArrayList<Car> result = new ArrayList<>();
        app.priceTree.getInPriceRange(min, max, result);
        Printer.printCars(result);
    }

    private void showTopKCheapestCars(int k) {
        if (app.inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        Printer.printHeader();
        for (int i = 0; i < k; i++) {
            Car c = app.minHeap.extractMin();
            if (c != null) {
                Printer.printCar(c);
            }
        }
        app.rebuildIndexes();
    }

    private void showTopKMostExpensiveCars(int k) {
        if (app.inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        Printer.printHeader();
        for (int i = 0; i < k; i++) {
            Car c = app.maxHeap.extractMax();
            if (c != null) {
                Printer.printCar(c);
            }
        }
        app.rebuildIndexes();
    }

    private void costCalculator() {
        System.out.print("Enter Car Brand: ");
        String brand = app.scanner.nextLine();

        System.out.print("Enter Car Model: ");
        String model = app.scanner.nextLine();

        System.out.print("Enter Car Year: ");
        int year = app.input.readInt();

        Car car = app.inventory.findByBrandModelYear(brand, model, year);
        if (car == null) {
            System.out.println("Car not found with the given details.");
            return;
        }

        System.out.print("Enter quantity: ");
        int qty = app.input.readInt();

        long total = car.getPrice() * qty;
        double tax = total * 0.05;

        System.out.println("Base total = " + total);
        System.out.println("Tax (5%) = " + tax);
        System.out.println("Grand Total = " + (total + tax));
    }

    private void giveFeedback() {
        System.out.print("Enter your feedback about CarsWorld: ");
        String msg = app.scanner.nextLine();
        app.feedbackQueue.enqueue(new Feedback(msg));
        System.out.println("Thank you! Your feedback has been recorded.");
    }
}
