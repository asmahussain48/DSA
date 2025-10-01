package Lab5;

class Nod {
    int data;
    Nod next;

    public Nod(int data) {
        this.data = data;
        this.next = null;
    }
}

class MyStackQ7 {
    private Nod top;

    public MyStackQ7() {
        top = null;
    }

    public void push(int value) {
        Nod newNode = new Nod(value);
        newNode.next = top;
        top = newNode;
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return -1;
        }
        int val = top.data;
        top = top.next;
        return val;
    }

    public int peek() {
        if (isEmpty()) {
            return -1;
        }
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void printAll() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return;
        }
        System.out.print("Stack (top -> bottom): ");
        Nod curr = top;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }
}

public class Q7 {
    public static void sortStack( MyStackQ7 stack) {
        MyStackQ7 tempStack = new MyStackQ7();
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            while (!tempStack.isEmpty() && tempStack.peek() > curr) {
                stack.push(tempStack.pop());
            }
            tempStack.push(curr);
        }
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
    }

    public static void main(String[] args) {
        MyStackQ7 stack = new MyStackQ7();
        stack.push(34);
        stack.push(3);
        stack.push(31);
        stack.push(98);
        stack.push(92);
        stack.push(23);

        System.out.println("Original stack:");
        stack.printAll();

        sortStack(stack);

        System.out.println("Sorted stack (smallest on top):");
        stack.printAll();
    }
}
/*
Start
O: [23, 92, 98, 31, 3, 34]
T: []
pop O → curr=23
O: [92, 98, 31, 3, 34], T: []
push curr to T
O: [92, 98, 31, 3, 34], T: [23]
pop O → curr=92
O: [98, 31, 3, 34], T: [23]
push curr to T (23 ≤ 92)
O: [98, 31, 3, 34], T: [92, 23]
pop O → curr=98
O: [31, 3, 34], T: [92, 23]
push curr to T (92 ≤ 98)
O: [31, 3, 34], T: [98, 92, 23]
pop O → curr=31
O: [3, 34], T: [98, 92, 23]
move 98 from T→O (98 > 31)
O: [98, 3, 34], T: [92, 23]
move 92 from T→O (92 > 31)
O: [92, 98, 3, 34], T: [23]
push curr (31) to T (23 ≤ 31)
O: [92, 98, 3, 34], T: [31, 23]
pop O → curr=92
O: [98, 3, 34], T: [31, 23]
push curr to T (31 ≤ 92)
O: [98, 3, 34], T: [92, 31, 23]
pop O → curr=98
O: [3, 34], T: [92, 31, 23]
push curr to T (92 ≤ 98)
O: [3, 34], T: [98, 92, 31, 23]
pop O → curr=3
O: [34], T: [98, 92, 31, 23]
move 98 from T→O (98 > 3
O: [98, 34], T: [92, 31, 23
move 92 from T→O (92 > 3)
O: [92, 98, 34], T: [31, 23]
move 31 from T→O (31 > 3)
O: [31, 92, 98, 34], T: [23]
move 23 from T→O (23 > 3)
O: [23, 31, 92, 98, 34], T: []
push curr (3) to T
O: [23, 31, 92, 98, 34], T: [3]
pop O → curr=23
O: [31, 92, 98, 34], T: [3]
push curr to T (3 ≤ 23)
O: [31, 92, 98, 34], T: [23, 3]
pop O → curr=31
O: [92, 98, 34], T: [23, 3]
push curr to T (23 ≤ 31)
O: [92, 98, 34], T: [31, 23, 3]
pop O → curr=92
O: [98, 34], T: [31, 23, 3]
push curr to T
O: [98, 34], T: [92, 31, 23, 3]
pop O → curr=98
O: [34], T: [92, 31, 23, 3]
push curr to T
O: [34], T: [98, 92, 31, 23, 3]
pop O → curr=34
O: [], T: [98, 92, 31, 23, 3]
move 98 from T→O (98 > 34)
O: [98], T: [92, 31, 23, 3]
move 92 from T→O (92 > 34)
O: [92, 98], T: [31, 23, 3]
push curr (34) to T (31 ≤ 34)
O: [92, 98], T: [34, 31, 23, 3]
pop O → curr=92
O: [98], T: [34, 31, 23, 3]
push curr to T
O: [98], T: [92, 34, 31, 23, 3]
pop O → curr=98
O: [], T: [92, 34, 31, 23, 3]
push curr to T
O: [], T: [98, 92, 34, 31, 23, 3]
Move everything back T → O (to get smallest on top)
move back (pop 98) → O: [98], T: [92, 34, 31, 23, 3]
move back (pop 92) → O: [92, 98], T: [34, 31, 23, 3]
move back (pop 34) → O: [34, 92, 98], T: [31, 23, 3]
move back (pop 31) → O: [31, 34, 92, 98], T: [23, 3]
move back (pop 23) → O: [23, 31, 34, 92, 98], T: [3]
move back (pop 3) → O: [3, 23, 31, 34, 92, 98], T: []
 */