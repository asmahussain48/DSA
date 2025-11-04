package DSAProjectPractice;

// Car.java - represents a single car in the inventory
public class Car {
    private String brand;
    private String model;
    private String category; // e.g. Sedan, SUV, Hatchback
    private int year;
    private long price;

    public Car(String brand, String model, String category, int year, long price) {
        this.brand = brand;
        this.model = model;
        this.category = category;
        this.year = year;
        this.price = price;
    }

    // Copy constructor (used by undo stack so we don't lose old values)
    public Car(Car other) {
        this(other.brand, other.model, other.category, other.year, other.price);
    }

    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public String getCategory() { return category; }
    public int getYear() { return year; }
    public long getPrice() { return price; }

    public void setPrice(long price) { this.price = price; }

    @Override
    public String toString() {
        // formatted table row for printing
        return String.format("%-10s %-18s %-10s %-6d %-10d",
                brand, model, category, year, price);
    }
}

