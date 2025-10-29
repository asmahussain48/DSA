package Lab5;
public class CircularQueueLL {
    // Node class for linked list
    private static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    private Node front;
    private Node rear;
    private int size;
    private int capacity; // Optional, for fixed-size queue

    // Constructor (with capacity)
    public CircularQueueLL(int capacity) {
        this.front = null;
        this.rear = null;
        this.size = 0;
        this.capacity = capacity;
    }

    // Check if queue is full
    public boolean isFull() {
        return size == capacity;
    }

    // Check if queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Insert (enqueue)
    public boolean insert(int item) {
        if (isFull()) {
            System.out.println("Queue is full! Cannot insert " + item);
            return false;
        }

        Node newNode = new Node(item);

        // If queue is empty
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
            newNode.next = front;
        } else {
            rear.next = newNode;
            rear = newNode;
            rear.next = front;     // circular link back to front
        }

        size++;
        System.out.println("Inserted: " + item);
        return true;
    }

    // Remove (dequeue)
    public int remove() {
        if (isEmpty()) {
            System.out.println("Queue is empty! Cannot remove.");
            return -1;
        }

        int removed = front.value;

        // If there is only one node
        if (front == rear) {
            front = null;
            rear = null;
        } else {
            front = front.next;
            rear.next = front;  // maintain circular link
        }

        size--;
        System.out.println("Removed: " + removed);
        return removed;
    }

    // Display queue elements
    public void display() {
        if (isEmpty()) {
            System.out.println("Empty");
            return;
        }

        Node temp = front;
        do {
            System.out.print(temp.value + " ");
            temp = temp.next;
        } while (temp != front);

        System.out.println("END");
    }
}
