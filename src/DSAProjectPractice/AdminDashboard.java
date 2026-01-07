package DSAProjectPractice;

public class AdminDashboard {

    private CarsWorldApp app;

    public AdminDashboard(CarsWorldApp app) {
        this.app = app;
    }

    public void menu() {
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

            choice = app.input.readInt();

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
                    Printer.printCars(app.inventory.getAllCars());
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
        System.out.print("Enter Car Brand: ");
        String brand = app.input.readValidName("brand");
        if (brand == null) return;

        System.out.print("Enter Car Model: ");
        String model = app.input.readValidName("model");
        if (model == null) return;

        System.out.print("Enter Car Category (Sedan/SUV/Hatchback): ");
        String category = app.input.readValidCategory();
        if (category == null) return;

        System.out.print("Enter Car Year: ");
        int year = app.input.readInt();
        if (year == -1) return;

        System.out.print("Enter Car Price: ");
        long price = app.input.readLong();
        if (price == -1) return;

        Car car = new Car(brand, model, category, year, price);
        app.inventory.add(car);
        app.undoStack.push(new AdminAction(AdminActionType.ADD, new Car(car), 0));

        System.out.println("Car added successfully.");
        app.rebuildIndexes();
    }

    private void editCarPrice() {
        if (app.inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.print("Enter Car Model whose price you want to change: ");
        String model = app.scanner.nextLine();

        Car car = app.inventory.findFirstByModel(model);
        if (car == null) {
            System.out.println("No car found with that model.");
            return;
        }

        System.out.print("Enter new price: ");
        long newPrice = app.input.readLong();
        if (newPrice == -1) return;

        long oldPrice = app.inventory.updatePriceByModel(model, newPrice);

        if (oldPrice != -1) {
            app.undoStack.push(
                    new AdminAction(AdminActionType.UPDATE_PRICE, new Car(car), oldPrice)
            );
            System.out.println("Price updated successfully.");
            app.rebuildIndexes();
        }
    }

    private void deleteCar() {
        if (app.inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.print("Enter Car Model to delete: ");
        String model = app.scanner.nextLine();

        System.out.print("Enter Car Year: ");
        int year = app.input.readInt();

        Car removed = app.inventory.deleteByModelAndYear(model, year);

        if (removed == null) {
            System.out.println("No car found with that model and year.");
        } else {
            app.undoStack.push(
                    new AdminAction(AdminActionType.DELETE, new Car(removed), 0)
            );
            System.out.println("Car deleted successfully.");
            app.rebuildIndexes();
        }
    }

    private void processFeedback() {
        if (app.feedbackQueue.isEmpty()) {
            System.out.println("No feedback at the moment.");
            return;
        }

        Feedback feedback;
        while ((feedback = app.feedbackQueue.dequeue()) != null) {
            System.out.println("Feedback: " + feedback.getMessage());
            app.scanner.nextLine();
        }

        System.out.println("All feedback processed.");
    }

    private void undoLastAction() {
        AdminAction action = app.undoStack.pop();

        if (action == null) {
            System.out.println("No actions to undo.");
            return;
        }

        switch (action.getType()) {
            case ADD:
                app.inventory.deleteByModel(action.getCarSnapshot().getModel());
                break;
            case DELETE:
                app.inventory.add(new Car(action.getCarSnapshot()));
                break;
            case UPDATE_PRICE:
                app.inventory.updatePriceByModel(
                        action.getCarSnapshot().getModel(),
                        action.getOldPrice()
                );
                break;
        }

        System.out.println("Undo successful.");
        app.rebuildIndexes();
    }
}
