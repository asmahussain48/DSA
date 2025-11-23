package DSAProjectPractice;

import java.util.ArrayList;
import java.util.Scanner;

public class CarsWorldApp {

    private static final String ADMIN_ID = "17103";
    private static final String ADMIN_PASSWORD = "admin";

    private CarLinkedList inventory = new CarLinkedList();
    private FeedbackQueue feedbackQueue = new FeedbackQueue(50);
    private ActionStack undoStack = new ActionStack(50);
    private CarPriceBST priceTree = new CarPriceBST();
    private CarMinHeap minHeap = new CarMinHeap(1000);
    private CarMaxHeap maxHeap = new CarMaxHeap(1000);

    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        CarsWorldApp app = new CarsWorldApp();
        app.seedSampleData();
        app.mainMenu();
    }

    private void seedSampleData() {
        inventory.add(new Car("Honda", "City", "Hatchback", 2015, 2500000));
        inventory.add(new Car("Honda", "City", "Hatchback", 2020, 3500000));
        inventory.add(new Car("Honda", "Civic", "Sedan", 2020, 4000000));
        inventory.add(new Car("Honda", "Vezel Hybrid Z", "SUV", 2017, 4700000));

        inventory.add(new Car("Kia", "Picanto", "Hatchback", 2021, 2600000));
        inventory.add(new Car("Kia", "Sportage", "SUV", 2019, 6000000));

        inventory.add(new Car("Toyota", "Corolla GLi 1.3", "Sedan", 2018, 3000000));
        inventory.add(new Car("Toyota", "Altis Grande 1.8", "Sedan", 2016, 3200000));
        inventory.add(new Car("Toyota", "Yaris", "Sedan", 2021, 3000000));
        inventory.add(new Car("Toyota", "Fortuner", "SUV", 2021, 8000000));

        rebuildIndexes();
    }

    private void mainMenu() {
        int choice;
        do {
            System.out.println("\n===== CarsWorld =====");
            System.out.println("1. Admin Login");
            System.out.println("2. User Dashboard");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            choice = readInt();

            switch (choice) {
                case 1:
                    handleAdminLogin();
                    break;
                case 2:
                    userDashboard();
                    break;
                case 3:
                    System.out.println("Exiting CarsWorld. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 3);
    }

    private int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private long readLong() {
        while (!scanner.hasNextLong()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        long value = scanner.nextLong();
        scanner.nextLine();
        return value;
    }

    private void handleAdminLogin() {
        System.out.print("Enter Admin ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Password: ");
        String pass = scanner.nextLine();

        if (ADMIN_ID.equals(id) && ADMIN_PASSWORD.equals(pass)) {
            System.out.println("Login successful. Welcome Admin!\n");
            adminDashboard();
        } else {
            System.out.println("Invalid ID or Password.\n");
        }
    }

    private void adminDashboard() {
        int choice;
        do {
            System.out.println("\n===== ADMIN DASHBOARD =====");
            System.out.println("1. Add Car Data");
            System.out.println("2. Edit Car Price");
            System.out.println("3. Delete Car");
            System.out.println("4. View All Cars");
            System.out.println("5. Process Feedback Queue");
            System.out.println("6. Undo Last Action");
            System.out.println("7. Log Out");
            System.out.print("Enter choice: ");
            choice = readInt();

            switch (choice) {
                case 1:
                    addCarData();
                    break;
                case 2:
                    editCarPrice();
                    break;
                case 3:
                    deleteCar();
                    break;
                case 4:
                    printCars(inventory.getAllCars());
                    break;
                case 5:
                    processFeedback();
                    break;
                case 6:
                    undoLastAction();
                    break;
                case 7:
                    System.out.println("Logging out from admin dashboard.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 7);
    }

    private void addCarData() {
        System.out.println("\n--- Add New Car ---");
        System.out.print("Enter Car Brand: ");
        String brand = scanner.nextLine();
        System.out.print("Enter Car Model: ");
        String model = scanner.nextLine();
        System.out.print("Enter Car Category (Sedan/SUV/Hatchback): ");
        String category = scanner.nextLine();
        System.out.print("Enter Car Year: ");
        int year = readInt();
        System.out.print("Enter Car Price: ");
        long price = readLong();

        Car car = new Car(brand, model, category, year, price);
        inventory.add(car);

        undoStack.push(new AdminAction(AdminActionType.ADD, new Car(car), 0));

        System.out.println("Car added successfully.");

        rebuildIndexes();
    }

    private void editCarPrice() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("\n--- Edit Car Price ---");
        System.out.print("Enter Car Model whose price you want to change: ");
        String model = scanner.nextLine();
        Car car = inventory.findFirstByModel(model);
        if (car == null) {
            System.out.println("No car found with that model.");
            return;
        }
        System.out.println("Current details: ");
        System.out.println(formatHeader());
        System.out.println(car);
        System.out.print("Enter new price: ");
        long newPrice = readLong();

        long oldPrice = inventory.updatePriceByModel(model, newPrice);
        if (oldPrice != -1) {
            Car snapshot = new Car(car);
            snapshot.setPrice(oldPrice);
            undoStack.push(new AdminAction(AdminActionType.UPDATE_PRICE, snapshot, oldPrice));
            System.out.println("Price updated successfully.");

            rebuildIndexes();
        }
    }

    private void deleteCar() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("\n--- Delete Car ---");
        System.out.print("Enter Car Model to delete: ");
        String model = scanner.nextLine();

        System.out.print("Enter Car Year of the car to delete: ");
        int year = readInt();

        Car removed = inventory.deleteByModelAndYear(model, year);
        if (removed == null) {
            System.out.println("No car found with that model and year.");
        } else {
            undoStack.push(new AdminAction(AdminActionType.DELETE, new Car(removed), 0));
            System.out.println("Car deleted successfully.");

            rebuildIndexes();
        }
    }

    private void processFeedback() {
        System.out.println("\n--- Feedback Queue ---");
        if (feedbackQueue.isEmpty()) {
            System.out.println("No feedback at the moment.");
            return;
        }
        Feedback f;
        while ((f = feedbackQueue.dequeue()) != null) {
            System.out.println("Feedback: " + f.getMessage());
            System.out.println("(press Enter to read next)");
            scanner.nextLine();
        }
        System.out.println("All feedback processed.");
    }

    private void undoLastAction() {
        AdminAction action = undoStack.pop();
        if (action == null) {
            System.out.println("No actions to undo.");
            return;
        }

        switch (action.getType()) {
            case ADD:
                inventory.deleteByModel(action.getCarSnapshot().getModel());
                System.out.println("Undo successful: last added car removed.");
                break;
            case DELETE:
                inventory.add(new Car(action.getCarSnapshot()));
                System.out.println("Undo successful: last deleted car re-added.");
                break;
            case UPDATE_PRICE:
                inventory.updatePriceByModel(
                        action.getCarSnapshot().getModel(),
                        action.getOldPrice()
                );
                System.out.println("Undo successful: price restored.");
                break;
        }

        rebuildIndexes();
    }

    private void userDashboard() {
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
            choice = readInt();

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
                    printCars(inventory.getAllCars());
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
        String brand = scanner.nextLine();
        ArrayList<Car> result = inventory.findByBrand(brand);
        if (result.isEmpty()) {
            System.out.println("No cars found for this brand.");
        } else {
            printCars(result);
        }
    }

    private void searchCarByModel() {
        System.out.print("Enter Car Model: ");
        String model = scanner.nextLine();
        ArrayList<Car> result = inventory.findByModel(model);
        if (result.isEmpty()) {
            System.out.println("No cars found for this model.");
        } else {
            printCars(result);
        }
    }

    private void searchCarByYear() {
        System.out.print("Enter Car Year: ");
        int year = readInt();

        ArrayList<Car> all = inventory.getAllCars();
        ArrayList<Car> result = new ArrayList<>();

        for (Car c : all) {
            if (c.getYear() == year) {
                result.add(c);
            }
        }

        if (result.isEmpty()) {
            System.out.println("No cars found for this year.");
        } else {
            printCars(result);
        }
    }

    // UPDATED: now asks for brand, model, year
    private void costCalculator() {
        System.out.print("Enter Car Brand: ");
        String brand = scanner.nextLine();

        System.out.print("Enter Car Model: ");
        String model = scanner.nextLine();

        System.out.print("Enter Car Year: ");
        int year = readInt();

        // search by brand, model AND year
        Car car = inventory.findByBrandModelYear(brand, model, year);

        if (car == null) {
            System.out.println("Car not found with the given details.");
            return;
        }

        System.out.print("Enter quantity: ");
        int qty = readInt();

        long total = car.getPrice() * (long) qty;
        System.out.println("Base total = " + total);

        double tax = total * 0.05;
        double grandTotal = total + tax;

        System.out.println("Tax (5%) = " + tax);
        System.out.println("Grand Total = " + grandTotal);
    }

    private void giveFeedback() {
        System.out.print("Enter your feedback about CarsWorld: ");
        String msg = scanner.nextLine();
        feedbackQueue.enqueue(new Feedback(msg));
        System.out.println("Thank you! Your feedback has been recorded.");
    }

    private void printCars(ArrayList<Car> cars) {
        if (cars.isEmpty()) {
            System.out.println("No cars to display.");
            return;
        }
        System.out.println("\n" + formatHeader());
        for (Car c : cars) {
            System.out.println(c);
        }
    }

    private String formatHeader() {
        return String.format("%-10s %-18s %-10s %-6s %-10s",
                "Brand", "Model", "Category", "Year", "Price");
    }

    private void rebuildIndexes() {
        priceTree.clear();
        minHeap.clear();
        maxHeap.clear();

        ArrayList<Car> all = inventory.getAllCars();
        for (Car c : all) {
            priceTree.insert(c);
            minHeap.insert(c);
            maxHeap.insert(c);
        }
    }

    private void viewCarsSortedByPrice() {
        ArrayList<Car> list = new ArrayList<>();
        priceTree.inOrder(list);
        if (list.isEmpty()) {
            System.out.println("No cars to display.");
        } else {
            System.out.println("\nCars Sorted By Price (Ascending):");
            printCars(list);
        }
    }

    private void searchCarsByPriceRange() {
        System.out.print("Enter minimum price: ");
        long min = readLong();
        System.out.print("Enter maximum price: ");
        long max = readLong();

        ArrayList<Car> result = new ArrayList<>();
        priceTree.getInPriceRange(min, max, result);

        if (result.isEmpty()) {
            System.out.println("No cars found in this price range.");
        } else {
            System.out.println("\nCars in price range " + min + " - " + max + ":");
            printCars(result);
        }
    }

    private void showTopKCheapestCars(int k) {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.println("\nTop " + k + " Cheapest Cars:");
        System.out.println(formatHeader());

        for (int i = 0; i < k; i++) {
            Car c = minHeap.extractMin();
            if (c == null) break;
            System.out.println(c);
        }

        rebuildIndexes();
    }

    private void showTopKMostExpensiveCars(int k) {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.println("\nTop " + k + " Most Expensive Cars:");
        System.out.println(formatHeader());

        for (int i = 0; i < k; i++) {
            Car c = maxHeap.extractMax();
            if (c == null) break;
            System.out.println(c);
        }

        rebuildIndexes();
    }
}
