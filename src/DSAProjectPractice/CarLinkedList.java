package DSAProjectPractice;

import java.util.ArrayList;

// custom singly linked list implementation to store all cars.
public class CarLinkedList {
    private CarNode head; // first node of list

    // Add a car at the end of the list
    public void add(Car car) {
        CarNode newNode = new CarNode(car);
        if (head == null) {
            head = newNode;
            return;
        }
        CarNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    public Car deleteByModel(String model) {
        if (head == null) return null;

        // If first node matches
        if (head.data.getModel().equalsIgnoreCase(model)) {
            Car removed = head.data;
            head = head.next;
            return removed;
        }

        CarNode current = head;
        while (current.next != null &&
                !current.next.data.getModel().equalsIgnoreCase(model)) {
            current = current.next;
        }

        if (current.next == null) {
            return null; // not found
        }

        Car removed = current.next.data;
        current.next = current.next.next;
        return removed;
    }


    public Car deleteByModelAndYear(String model, int year) {
        if (head == null) return null;

        // Check first node
        if (head.data.getModel().equalsIgnoreCase(model)
                && head.data.getYear() == year) {
            Car removed = head.data;
            head = head.next;
            return removed;
        }

        CarNode current = head;
        while (current.next != null) {
            Car nextCar = current.next.data;
            if (nextCar.getModel().equalsIgnoreCase(model)
                    && nextCar.getYear() == year) {
                Car removed = nextCar;
                current.next = current.next.next;
                return removed;
            }
            current = current.next;
        }

        return null; // no car with that model+year
    }

    // Update price for a car by its model.
    // Returns previous price or -1 if not found.
    public long updatePriceByModel(String model, long newPrice) {
        Car car = findFirstByModel(model);
        if (car == null) return -1;
        long oldPrice = car.getPrice();
        car.setPrice(newPrice);
        return oldPrice;
    }

    // Find all cars with given brand (returns ArrayList as result)
    public ArrayList<Car> findByBrand(String brand) {
        ArrayList<Car> result = new ArrayList<>(); // This uses ArrayList DSA
        CarNode current = head;
        while (current != null) {
            if (current.data.getBrand().equalsIgnoreCase(brand)) {
                result.add(current.data);
            }
            current = current.next;
        }
        return result;
    }

    // Find all cars with given model
    public ArrayList<Car> findByModel(String model) {
        ArrayList<Car> result = new ArrayList<>();
        CarNode current = head;
        while (current != null) {
            if (current.data.getModel().equalsIgnoreCase(model)) {
                result.add(current.data);
            }
            current = current.next;
        }
        return result;
    }

    // Find first car with model (for internal use)
    public Car findFirstByModel(String model) {
        CarNode current = head;
        while (current != null) {
            if (current.data.getModel().equalsIgnoreCase(model)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }
    public Car findByBrandModelYear(String brand, String model, int year) {
        CarNode current = head;
        while (current != null) {
            Car car = current.data;
            if (car.getBrand().equalsIgnoreCase(brand) &&
                    car.getModel().equalsIgnoreCase(model) &&
                    car.getYear() == year) {
                return car;
            }
            current = current.next;
        }
        return null;
    }

    // Get all cars as an ArrayList (for printing, searching etc.)
    public ArrayList<Car> getAllCars() {
        ArrayList<Car> result = new ArrayList<>();
        CarNode current = head;
        while (current != null) {
            result.add(current.data);
            current = current.next;
        }
        return result;
    }

    public boolean isEmpty() {
        return head == null;
    }
}
