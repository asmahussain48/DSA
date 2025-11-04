package DSAProjectPractice;

// ActionStack.java - simple stack implementation using an array.
// Demonstrates the Stack DSA.
public class ActionStack {
    private AdminAction[] data;
    private int top;

    public ActionStack(int capacity) {
        data = new AdminAction[capacity];
        top = -1;
    }

    public boolean isEmpty() { return top == -1; }

    public boolean isFull() { return top == data.length - 1; }

    public void push(AdminAction action) {
        if (isFull()) {
            System.out.println("Undo stack is full. Oldest actions will be lost.");
            // simple strategy: shift everything left by one (drop oldest)
            for (int i = 1; i <= top; i++) {
                data[i - 1] = data[i];
            }
            top--;
        }
        data[++top] = action;
    }

    public AdminAction pop() {
        if (isEmpty()) return null;
        AdminAction a = data[top];
        data[top] = null;
        top--;
        return a;
    }
}
