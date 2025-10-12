package Lab5;

class QueueQ1 {
    private int[] arr;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public QueueQ1( int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void enqueue(int value) {
        if (isFull()) {
            System.out.println("Queue is full! Cannot insert " + value);
            return;
        }
        rear = (rear + 1) % capacity;
        arr[rear] = value;
        size++;
        System.out.println(value + " added to queue");
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty! Cannot remove.");
            return -1;
        }
        int removed = arr[front];
        front = (front + 1) % capacity;
        size--;
        return removed;
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return -1;
        }
        return arr[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int getSize() {
        return size;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return;
        }
        System.out.print("Queue elements: ");
        for (int i = 0; i < size; i++) {
            System.out.print(arr[(front + i) % capacity] + " ");
        }
        System.out.println();
    }
}

public class Q1 {
    public static void main(String[] args) {
        QueueQ1 q = new QueueQ1(5);

        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.display();

        System.out.println("Dequeued: " + q.dequeue());
        q.display();

        System.out.println("Front element: " + q.peek());
        System.out.println("Queue size: " + q.getSize());

        q.enqueue(40);
        q.enqueue(50);
        q.enqueue(60);
        q.display();

        System.out.println("Is queue full? " + q.isFull());
    }
}
