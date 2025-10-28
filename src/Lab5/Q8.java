package Lab5;

class Nod1 {
    int data;
    Nod1 next;

    public Nod1( int data) {
        this.data = data;
        this.next = null;
    }
}

class MyStackQ8 {
    private Nod1 top;
    private int size;

    public MyStackQ8() {
        top = null;
        size = 0;
    }

    public void push(int value) {
        Nod1 n = new Nod1(value);
        n.next = top;
        top = n;
        size++;
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return -1;
        }
        int v = top.data;
        top = top.next;
        size--;
        return v;
    }

    public int peek() {
        return isEmpty() ? -1 : top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int getSize() {
        return size;
    }
}

class QueueUsingTwoStacks {
    private MyStackQ8 stack_1;
    private MyStackQ8 stack_2;

    public QueueUsingTwoStacks() {
        stack_1 = new MyStackQ8();
        stack_2 = new MyStackQ8();
    }

    public void enqueue(int value) {
        stack_1.push(value);
        System.out.println(value + " enqueued.");
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return -1;
        }
        if (stack_2.isEmpty()) {
            while (!stack_1.isEmpty()) stack_2.push(stack_1.pop());
        }
        return stack_2.pop();
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return -1;
        }
        if (stack_2.isEmpty()) {
            while (!stack_1.isEmpty()) stack_2.push(stack_1.pop());
        }
        return stack_2.peek();
    }

    public boolean isEmpty() {
        return stack_1.isEmpty() && stack_2.isEmpty();
    }

    public int getSize() {
        return stack_1.getSize() + stack_2.getSize();
    }

    public void printAll() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return;
        }
        if (stack_2.isEmpty()) {
            while (!stack_1.isEmpty())
                stack_2.push(stack_1.pop());
        }

        System.out.print("Queue elements (front -> rear): ");
        MyStackQ8 temp = new MyStackQ8();
        while (!stack_2.isEmpty()) {
            int v = stack_2.pop();
            System.out.print(v + " ");
            temp.push(v);
        }
        System.out.println();
        while (!temp.isEmpty()) stack_2.push(temp.pop());
    }
}

public class Q8 {
    public static void main(String[] args) {
        QueueUsingTwoStacks q = new QueueUsingTwoStacks();

        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.printAll();

        System.out.println("Dequeued: " + q.dequeue());
        q.printAll();

        q.enqueue(40);
        q.enqueue(50);
        q.printAll();

        System.out.println("Front element: " + q.peek());
        System.out.println("Queue size: " + q.getSize());
    }
}



/*
Step-by-step iteration (states of the two stacks)
Notation:
stack1 = “in” (new enqueues go here), stack2 = “out” (dequeues/peeks come from here)
I show stacks top→bottom in brackets.
Start: stack1 [], stack2 []
enqueue(10) → push to stack1
stack1 [10], stack2 []
enqueue(20)
stack1 [20, 10], stack2 []
enqueue(30)
stack1 [30, 20, 10], stack2 []
printAll()
stack2 is empty ⇒ pour stack1→stack2:
move 30 → stack1 [20,10], stack2 [30]
move 20 → stack1 [10], stack2 [20,30]
move 10 → stack1 [], stack2 [10,20,30]
print by popping stack2 into a temp, then restore stack2 back to [10,20,30].
After print: stack1 [], stack2 [10,20,30]
dequeue()
stack2 not empty ⇒ pop from stack2 → returns 10
stack1 [], stack2 [20,30]
printAll()
stack2 not empty ⇒ no pouring
print pops 20 then 30 (into temp) and restores → stack2 back to [20,30]
After print: stack1 [], stack2 [20,30] (printed 20 30)
enqueue(40)
stack1 [40], stack2 [20,30]
enqueue(50)
stack1 [50,40], stack2 [20,30]
printAll()
stack2 not empty ⇒ no pouring; this implementation prints only stack2 contents
prints 20 30 (40 and 50 will appear after a future dequeue/peek when stack2 becomes empty and we pour stack1→stack2)
After print: stack1 [50,40], stack2 [20,30]
peek()
stack2 not empty ⇒ return top of stack2 → 20
stacks unchanged: stack1 [50,40], stack2 [20,30]
getSize()
size = stack1(2) + stack2(2) = 4

 */