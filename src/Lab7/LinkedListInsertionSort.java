package Lab7;

public class LinkedListInsertionSort {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static class LinkedList {
        Node head;

        public void addToBack(int data) {
            Node newNode = new Node(data);
            if (head == null) {
                head = newNode;
                return;
            }
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }

        public void printList() {
            Node temp = head;
            while (temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
            System.out.println();
        }

        public void insertionSort() {
            Node sorted = null;
            Node current = head;

            while (current != null) {
                Node next = current.next;
                if (sorted == null || current.data < sorted.data) {
                    current.next = sorted;
                    sorted = current;
                } else {
                    Node temp = sorted;
                    while (temp.next != null && temp.next.data < current.data) {
                        temp = temp.next;
                    }
                    current.next = temp.next;
                    temp.next = current;
                }
                current = next;
            }
            head = sorted;
        }
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.addToBack(4);
        list.addToBack(2);
        list.addToBack(5);
        list.addToBack(3);
        list.addToBack(1);

        System.out.println("Before Sorting:");
        list.printList();

        list.insertionSort();

        System.out.println("After Sorting:");
        list.printList();
    }
}
