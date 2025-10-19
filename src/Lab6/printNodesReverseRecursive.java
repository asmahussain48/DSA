package Lab6;



public class printNodesReverseRecursive {

    public static void main(String[] args) {
        // Create linked list: 10 -> 20 -> 30 -> 40
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);

        System.out.println("Linked List elements in reverse order:");
        recursiveReversePrint(head);
    }

    // Recursive method to print nodes in reverse order
    public static void recursiveReversePrint(Node head) {
        if (head == null)  // base case
            return;

        recursiveReversePrint(head.next);  // first go till the end
        System.out.println(head.data);     // then print on return
    }
}
