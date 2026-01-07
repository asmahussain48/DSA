package DSAProjectPractice;

public class AdminAction {

    private AdminActionType type;
    private Car carSnapshot;
    private long oldPrice;

    public AdminAction(AdminActionType type, Car carSnapshot, long oldPrice) {
        this.type = type;
        this.carSnapshot = carSnapshot;
        this.oldPrice = oldPrice;
    }

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
