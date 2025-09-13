package Lab3;

public class linkList_Reverse {
    Node head;
    class Node{
        contacts Data;
        Node next;
        int data;
        Node(int Data){
            this.data  = Data;
            this.next = null;
        }
    }

    public void reverse() {
        if (head == null || head.next == null) {
            return;
        }

        Node prev = null;
        Node curr = head;
        Node nextNode = head;
        while (nextNode != null) {
            // first move nextnode so that not lose
            nextNode = nextNode.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        head = prev;               // prev is new head
    }

    public static void main( String[] args ) {

    }
}
