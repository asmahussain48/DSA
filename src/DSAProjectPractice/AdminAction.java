package DSAProjectPractice;

// AdminAction.java - stores information about a single admin operation
// so we can UNDO it using a Stack.
public class AdminAction {
    private AdminActionType type;
    private Car carSnapshot;  // snapshot of the car BEFORE the change
    private long oldPrice;    // used for UPDATE_PRICE

    public AdminAction(AdminActionType type, Car carSnapshot, long oldPrice) {
        this.type = type;
        this.carSnapshot = carSnapshot;
        this.oldPrice = oldPrice;
    }

    public AdminActionType getType() { return type; }
    public Car getCarSnapshot() { return carSnapshot; }
    public long getOldPrice() { return oldPrice; }
}
