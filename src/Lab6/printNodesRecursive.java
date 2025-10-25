package Lab6;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class printNodesRecursive {

    public static void main(String[] args) {
        // Create linked list: 10 -> 20 -> 30 -> 40
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);

        System.out.println("Linked List elements:");
        recursivePrint(head);
    }


    public static void recursivePrint(Node head) {
        if (head == null)  // base case
            return;

        System.out.println(head.data);  // print current node
        recursivePrint(head.next);      // recursive call for next node
    }
}
