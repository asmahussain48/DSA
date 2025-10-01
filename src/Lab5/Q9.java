package Lab5;

class NodeQ9 {
    int data;
    NodeQ9 next;

    public NodeQ9( int data) {
        this.data = data;
        this.next = null;
    }
}

class MyStackQ9 {
    private NodeQ9 top;

    public void push(int value) {
        NodeQ9 newNode = new NodeQ9(value);
        newNode.next = top;
        top = newNode;
    }

    public int pop() {
        if (isEmpty()) return -1;
        int val = top.data;
        top = top.next;
        return val;
    }

    public int peek() {
        return isEmpty() ? -1 : top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }
}

class MinStack {
    private MyStackQ9 mainStack = new MyStackQ9();
    private MyStackQ9 minStack = new MyStackQ9();

    public void push(int value) {
        mainStack.push(value);
        if (minStack.isEmpty() || value <= minStack.peek()) {
            minStack.push(value);
        }
    }

    public int pop() {
        int val = mainStack.pop();
        if (val == minStack.peek()) {
            minStack.pop();
        }
        return val;
    }

    public int getMinimum() {
        return minStack.peek();
    }
}

public class Q9 {
    public static void main(String[] args) {
        MinStack stack = new MinStack();

        stack.push(5);
        stack.push(3);
        stack.push(7);
        stack.push(2);
        System.out.println("Minimum: " + stack.getMinimum());
        stack.pop();
        System.out.println("Minimum: " + stack.getMinimum());
    }
}




/*

Push sequence: 5, 3, 7, 2
Push(5) → min stack = [5]
Push(3) → min stack = [5, 3]
Push(7) → min stack = [5, 3] (7 > 3, so don’t push)
Push(2) → min stack = [5, 3, 2]
Now
getMinimum() → returns 2

Start
Main: []
Min: []
push(5)
Push 5 to Main.
Min is empty ⇒ also push 5 to Min.
Main: [5]
Min: [5]
Current min = 5
push(3)
Push 3 to Main.
3 ≤ top(Min=5) ⇒ push 3 to Min.
Main: [3, 5]
Min: [3, 5]
Current min = 3
push(7)
Push 7 to Main.
7 ≤ top(Min=3)? no ⇒ Min unchanged.
Main: [7, 3, 5]
Min: [3, 5]
Current min = 3
push(2)
Push 2 to Main.
2 ≤ top(Min=3) ⇒ push 2 to Min.
Main: [2, 7, 3, 5]
Min: [2, 3, 5]
Current min = 2
getMinimum()
Return top(Min) = 2.
(Stacks unchanged.)
 */