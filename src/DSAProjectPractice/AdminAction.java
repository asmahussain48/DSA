package DSAProjectPractice;

//  stores information about a single admin operation
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
    /*
    Adding a car
Removing a car
Updating a car’s price
…an AdminAction object is created to remember what changed so the system can undo it later.
     */

    public AdminActionType getType() {
        return type;
    }
    public Car getCarSnapshot() {
        return carSnapshot;
    }
    public long getOldPrice() {
        return oldPrice;
    }
}
