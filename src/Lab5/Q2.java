package Lab5;

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class QueueQ2 {
    private Node front, rear;
    private int size;

    public QueueQ2() {
        front = rear = null;
        size = 0;
    }

    public void enqueue(int data) {
        Node newNode = new Node(data);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        System.out.println(data + " enqueued.");
    }

    public void dequeue() {
        if (front == null) {
            System.out.println("Queue is empty. Cannot dequeue.");
            return;
        }
        System.out.println(front.data + " dequeued.");
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
    }

    public int getFront() {
        if (front == null) {
            System.out.println("Queue is empty.");
            return -1;
        }
        return front.data;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public void printAll() {
        if (front == null) {
            System.out.println("Queue is empty.");
            return;
        }
        Node current = front;
        System.out.print("Queue elements: ");
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}

public class Q2 {
    public static void main(String[] args) {
        QueueQ2 q = new QueueQ2();

        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);

        q.printAll();

        System.out.println("Front element: " + q.getFront());
        System.out.println("Queue size: " + q.getSize());
        System.out.println("Is queue empty? " + q.isEmpty());

        q.dequeue();
        q.printAll();

        q.dequeue();
        q.dequeue();
        q.dequeue();

        System.out.println("Is queue empty? " + q.isEmpty());
    }
}
