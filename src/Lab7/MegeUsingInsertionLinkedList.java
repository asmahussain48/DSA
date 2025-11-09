package Lab7;

public class MegeUsingInsertionLinkedList {

    // Node class to represent each element in the list
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // LinkedList class to handle list operations
    static class LinkedList {
        Node head;

        // Add element to the end of the list
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

        // Print linked list
        public void printList() {
            Node temp = head;
            while (temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
            System.out.println();
        }

        // Merge another list into this list while sorting (insertion logic)
        public void mergeAndSort(LinkedList otherList) {
            Node temp = otherList.head;

            // Insert each node from otherList into this sorted list
            while (temp != null) {
                insertInSortedOrder(temp.data);
                temp = temp.next;
            }
        }

        // Insert a new value in sorted order (like insertion sort)
        private void insertInSortedOrder(int value) {
            Node newNode = new Node(value);

            // If list is empty or new node should be first
            if (head == null || value < head.data) {
                newNode.next = head;
                head = newNode;
                return;
            }

            // Find correct place to insert
            Node current = head;
            while (current.next != null && current.next.data < value) {
                current = current.next;
            }

            // Insert node
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    // Main method
    public static void main(String[] args) {
        // First linked list
        LinkedList list1 = new LinkedList();
        list1.addToBack(6);
        list1.addToBack(3);
        list1.addToBack(9);
        list1.addToBack(5);
        list1.addToBack(2);

        // Second linked list
        LinkedList list2 = new LinkedList();
        list2.addToBack(8);
        list2.addToBack(1);
        list2.addToBack(7);
        list2.addToBack(4);
        list2.addToBack(10);

        System.out.print("List 1: ");
        list1.printList();

        System.out.print("List 2: ");
        list2.printList();

        // Merge and sort
        list1.mergeAndSort(list2);

        System.out.print("Merged & Sorted List: ");
        list1.printList();
    }
}
