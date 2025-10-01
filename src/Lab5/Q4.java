package Lab5;

class Node2 {
    int data;
    Node2 next;

    public Node2(int data) {
        this.data = data;
        this.next = null;
    }
}

class StackQ4 {
    private Node2 top;
    private int size;

    public StackQ4() {
        top = null;
        size = 0;
    }

    public void push(int value) {
        Node2 newNode = new Node2(value);
        newNode.next = top;
        top = newNode;
        size++;
        System.out.println(value + " pushed into stack");
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty! Cannot pop.");
            return -1;
        }
        int popped = top.data;
        top = top.next;
        size--;
        return popped;
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty! Nothing to peek.");
            return -1;
        }
        return top.data;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void printAll() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return;
        }
        System.out.print("Stack elements: ");
        Node2 current = top;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}

public class Q4 {
    public static void main(String[] args) {
        StackQ4 stack = new StackQ4();

        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.printAll();

        System.out.println("Top element: " + stack.peek());

        System.out.println("Popped: " + stack.pop());
        stack.printAll();

        System.out.println("Stack size: " + stack.getSize());
        System.out.println("Is empty? " + stack.isEmpty());
    }
}
