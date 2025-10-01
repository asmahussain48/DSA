package Lab5;

class StackQ3 {
    private int[] arr;
    private int top;
    private int capacity;

    public StackQ3( int size) {
        arr = new int[size];
        capacity = size;
        top = -1;
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("Stack is full! Cannot push " + value);
            return;
        }
        arr[++top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty! Cannot pop.");
            return -1;
        }
        return arr[top--];
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty! Nothing to peek.");
            return -1;
        }
        return arr[top];
    }

    public int getSize() {
        return top + 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == capacity - 1;
    }

    public void printAll() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return;
        }
        System.out.print("Stack elements: ");
        for (int i = 0; i <= top; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}

public class Q3 {
    public static void main(String[] args) {
        StackQ3 stack = new StackQ3(5);

        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.printAll();

        System.out.println("Top element: " + stack.peek());

        System.out.println("Stack.pop -> "+stack.pop() );;
        stack.printAll();

        System.out.println("Stack size: " + stack.getSize());
        System.out.println("Is empty? " + stack.isEmpty());
        System.out.println("Is full? " + stack.isFull());
    }
}
