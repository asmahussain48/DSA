package DSAProjectPractice;

import java.util.ArrayList;

public class CarLinkedList {

    private CarNode head;

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
            return null;
        }

        Car removed = current.next.data;
        current.next = current.next.next;
        return removed;
    }

    public Car deleteByModelAndYear(String model, int year) {
        if (head == null) return null;

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

        return null;
    }

    public long updatePriceByModel(String model, long newPrice) {
        Car car = findFirstByModel(model);
        if (car == null) return -1;
        long oldPrice = car.getPrice();
        car.setPrice(newPrice);
        return oldPrice;
    }

    public ArrayList<Car> findByBrand(String brand) {
        ArrayList<Car> result = new ArrayList<>();
        CarNode current = head;
        while (current != null) {
            if (current.data.getBrand().equalsIgnoreCase(brand)) {
                result.add(current.data);
            }
            current = current.next;
        }
        return result;
    }

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
            if (car.getBrand().equalsIgnoreCase(brand)
                    && car.getModel().equalsIgnoreCase(model)
                    && car.getYear() == year) {
                return car;
            }
            current = current.next;
        }
        return null;
    }

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
