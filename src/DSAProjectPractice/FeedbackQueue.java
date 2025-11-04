package DSAProjectPractice;

// FeedbackQueue.java - simple fixed-size array based queue implementation.
// Demonstrates Queue data structure (FIFO).
public class FeedbackQueue {
    private Feedback[] data;
    private int front;
    private int rear;
    private int size;

    public FeedbackQueue(int capacity) {
        data = new Feedback[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }

    // Add feedback at the rear of the queue
    public void enqueue(Feedback f) {
        if (isFull()) {
            System.out.println("Feedback queue is full. Cannot accept more feedback now.");
            return;
        }
        rear = (rear + 1) % data.length;
        data[rear] = f;
        size++;
    }

    // Remove feedback from the front of the queue
    public Feedback dequeue() {
        if (isEmpty()) {
            return null;
        }
        Feedback f = data[front];
        data[front] = null; // help GC
        front = (front + 1) % data.length;
        size--;
        return f;
    }
}

