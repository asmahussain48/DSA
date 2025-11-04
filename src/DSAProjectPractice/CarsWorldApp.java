package DSAProjectPractice;
import java.util.ArrayList;
import java.util.Scanner;

// CarsWorldApp.java - Command line version of the GUI you showed.
// This file holds the MAIN method and menu handling for admin and user.
public class CarsWorldApp {

    // Hard-coded admin credentials (for demo only)
    private static final String ADMIN_ID = "17103";
    private static final String ADMIN_PASSWORD = "admin";

    private CarLinkedList inventory = new CarLinkedList();       // Linked list of cars
    private FeedbackQueue feedbackQueue = new FeedbackQueue(50); // Queue for feedback
    private ActionStack undoStack = new ActionStack(50);         // Stack for undo

    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        CarsWorldApp app = new CarsWorldApp();
        app.seedSampleData(); // load a few cars like in screenshots
        app.mainMenu();
    }

    // Fill inventory with some sample cars so the user can search immediately
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
    }

    // MAIN menu similar to the first GUI screen
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

    // Simple helper to read integer safely
    private int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return value;
    }

    private long readLong() {
        while (!scanner.hasNextLong()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        long value = scanner.nextLong();
        scanner.nextLine(); // consume newline
        return value;
    }

    // ADMIN LOGIN screen
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

    // ADMIN DASHBOARD menu (Add, Edit price, Delete, View etc.)
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

    // Add Data screen
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

        // push action to stack so we can undo
        undoStack.push(new AdminAction(AdminActionType.ADD, new Car(car), 0));

        System.out.println("Car added successfully.");
    }

    // Edit Price screen
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
            // Save previous state for undo
            Car snapshot = new Car(car);
            snapshot.setPrice(oldPrice); // ensure old price in snapshot
            undoStack.push(new AdminAction(AdminActionType.UPDATE_PRICE, snapshot, oldPrice));
            System.out.println("Price updated successfully.");
        }
    }

    // Delete Data screen
    private void deleteCar() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("\n--- Delete Car ---");
        System.out.print("Enter Car Model to delete: ");
        String model = scanner.nextLine();
        Car removed = inventory.deleteByModel(model);
        if (removed == null) {
            System.out.println("No car found with that model.");
        } else {
            undoStack.push(new AdminAction(AdminActionType.DELETE, new Car(removed), 0));
            System.out.println("Car deleted successfully.");
        }
    }

    // Process feedback queue (Admin reads messages in FIFO order)
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

    // Undo last admin action using STACK
    private void undoLastAction() {
        AdminAction action = undoStack.pop();
        if (action == null) {
            System.out.println("No actions to undo.");
            return;
        }

        switch (action.getType()) {
            case ADD:
                // remove the car that was last added (by model)
                inventory.deleteByModel(action.getCarSnapshot().getModel());
                System.out.println("Undo successful: last added car removed.");
                break;
            case DELETE:
                // re-insert the deleted car
                inventory.add(new Car(action.getCarSnapshot()));
                System.out.println("Undo successful: last deleted car re-added.");
                break;
            case UPDATE_PRICE:
                // restore old price
                inventory.updatePriceByModel(action.getCarSnapshot().getModel(),
                        action.getOldPrice());
                System.out.println("Undo successful: price restored.");
                break;
        }
    }

    // USER DASHBOARD menu
    private void userDashboard() {
        int choice;
        do {
            System.out.println("\n===== USER DASHBOARD =====");
            System.out.println("1. Search Car By Brand");
            System.out.println("2. Search Car By Model");
            System.out.println("3. View All Cars");
            System.out.println("4. Cost Calculator");
            System.out.println("5. Give Feedback");
            System.out.println("6. Exit to Main Menu");
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
                    printCars(inventory.getAllCars());
                    break;
                case 4:
                    costCalculator();
                    break;
                case 5:
                    giveFeedback();
                    break;
                case 6:
                    System.out.println("Leaving user dashboard.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 6);
    }

    // SEARCH BY BRAND screen
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

    // SEARCH BY MODEL screen
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

    // COST CALCULATOR screen
    private void costCalculator() {
        System.out.print("Enter Car Model to calculate cost for: ");
        String model = scanner.nextLine();
        Car car = inventory.findFirstByModel(model);
        if (car == null) {
            System.out.println("Car not found.");
            return;
        }
        System.out.print("Enter quantity: ");
        int qty = readInt();
        long total = car.getPrice() * qty;
        System.out.println("Base total = " + total);

        // Just to make it a bit interesting: add 5% tax
        double tax = total * 0.05;
        double grandTotal = total + tax;
        System.out.println("Tax (5%) = " + tax);
        System.out.println("Grand Total = " + grandTotal);
    }

    // USER FEEDBACK screen
    private void giveFeedback() {
        System.out.print("Enter your feedback about CarsWorld: ");
        String msg = scanner.nextLine();
        feedbackQueue.enqueue(new Feedback(msg));
        System.out.println("Thank you! Your feedback has been recorded.");
    }

    // Utility method to print a list of cars in table format
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
}



/*
How DSA is used in this project

Quick summary of where each data structure appears and why:

Linked List – CarLinkedList + CarNode

Stores the entire car inventory as a singly linked list.

Operations:

add(Car) – inserts a new car at the end.

deleteByModel – walks the list and removes a node by matching the model.

updatePriceByModel / findByBrand / findByModel.

This mimics how your GUI shows a dynamic table of cars that supports insert, delete, and update.

ArrayList – search results & view all

CarLinkedList returns results as ArrayList<Car> in:

findByBrand, findByModel, getAllCars.

CarsWorldApp then prints those lists.

This demonstrates how an array-based dynamic list is convenient for random access and iteration once the data is collected from the linked list.

Queue – FeedbackQueue

Implements a feedback box for users.

giveFeedback() enqueues Feedback objects at the rear.

processFeedback() in the admin dashboard dequeues them in the same order they were received (FIFO).

Internally it uses a circular array (front, rear, size), which is the textbook implementation of a queue.

Stack – ActionStack + AdminAction

Each admin operation (ADD, DELETE, UPDATE_PRICE) is saved as an AdminAction object on a stack.

When a car is added → push action type ADD.

When a car is deleted → push DELETE with a snapshot of the removed car.

When price is updated → push UPDATE_PRICE with the old price.

undoLastAction() pops the top action and reverses it:

Undo ADD → delete that car.

Undo DELETE → re-add the car.

Undo UPDATE_PRICE → restore old price.

This stack follows LIFO – the last action is undone first, exactly like Ctrl+Z in editors.

So overall:
 */