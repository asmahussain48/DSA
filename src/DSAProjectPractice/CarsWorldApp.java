package DSAProjectPractice;

import java.util.Scanner;

public class CarsWorldApp {

    private static final String ADMIN_ID = "02324";
    private static final String ADMIN_PASSWORD = "1234";

    CarLinkedList inventory = new CarLinkedList();
    FeedbackQueue feedbackQueue = new FeedbackQueue(50);
    ActionStack undoStack = new ActionStack(50);
    CarPriceBST priceTree = new CarPriceBST();
    CarMinHeap minHeap = new CarMinHeap(1000);
    CarMaxHeap maxHeap = new CarMaxHeap(1000);

    Scanner scanner = new Scanner(System.in);
    InputHelper input = new InputHelper(scanner);

    public static void main(String[] args) {
        CarsWorldApp app = new CarsWorldApp();
        app.seedSampleData();
        app.mainMenu();
    }

    private void seedSampleData() {
        inventory.add(new Car("Suzuki", "Alto VXR", "Hatchback", 2022, 2600000));
        inventory.add(new Car("Honda", "City Aspire 1.5 i-VTEC", "Sedan", 2018, 3600000));
        inventory.add(new Car("Toyota", "Corolla GLi 1.3 VVTi", "Sedan", 2017, 3490000));
        inventory.add(new Car("Kia", "Sportage FWD", "SUV", 2021, 6850000));
        inventory.add(new Car("Honda", "Civic Oriel 1.8 i-VTEC", "Sedan", 2018, 5775000));
        inventory.add(new Car("Toyota", "Fortuner 2.8 Sigma", "SUV", 2021, 15300000));
        inventory.add(new Car("Suzuki", "Wagon R VXL", "Hatchback", 2018, 2350000));
        inventory.add(new Car("Daihatsu", "Mira X", "Hatchback", 2017, 2900000));
        inventory.add(new Car("MG", "HS Trophy", "SUV", 2021, 6450000));
        inventory.add(new Car("Toyota", "Yaris ATIV X CVT", "Sedan", 2021, 4700000));
        inventory.add(new Car("Honda", "Vezel Hybrid Z", "SUV", 2015, 4390000));
        inventory.add(new Car("Changan", "Alsvin 1.5 Lumiere", "Sedan", 2021, 3350000));
        inventory.add(new Car("Suzuki", "Cultus VXL", "Hatchback", 2020, 2980000));
        inventory.add(new Car("Toyota", "Prado TX 2.7", "SUV", 2012, 16500000));
        inventory.add(new Car("Mercedes-Benz", "CLA 180", "Sedan", 2013, 12000000));

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

            choice = input.readInt();

            switch (choice) {
                case 1:
                    handleAdminLogin();
                    break;
                case 2:
                    new UserDashboard(this).menu();
                    break;
                case 3:
                    System.out.println("Exiting CarsWorld. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 3);
    }

    private void handleAdminLogin() {
        System.out.print("Enter Admin ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (ADMIN_ID.equals(id) && ADMIN_PASSWORD.equals(password)) {
            System.out.println("Login successful. Welcome Admin!");
            new AdminDashboard(this).menu();
        } else {
            System.out.println("Invalid ID or Password.");
        }
    }

    void rebuildIndexes() {
        priceTree.clear();
        minHeap.clear();
        maxHeap.clear();

        for (Car c : inventory.getAllCars()) {
            priceTree.insert(c);
            minHeap.insert(c);
            maxHeap.insert(c);
        }
    }
}
