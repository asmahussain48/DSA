package DSAProjectPractice;

import java.util.ArrayList;

// CarLinkedList.java - custom singly linked list implementation to store all cars.
// This demonstrates the Linked List DSA.
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

    // Delete car by model. Returns the deleted car or null if not found.
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

