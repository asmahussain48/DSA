package Lab3;

import java.util.Objects;

class LinkedList {
    Node head;
    Node tail;
    int size;

    public void addToFront( contacts data ) {
        Node newNode=new Node(data);
        newNode.next=head;
        head=newNode;
        if (tail == null) {
            tail=newNode;
        }
        size++;
    }

    public void addToBack( contacts data ) {
        Node newNode=new Node(data);
        if (head == null) {
            head=tail;
            tail=newNode;
        } else {
            tail.next=newNode;
            tail=newNode;
        }
        size++;
    }

    public contacts getFrontItem() {
        return head == null ? null : head.Data;
    }

    public contacts getBackItem() {
        return tail == null ? null : tail.Data;
    }

    public void removeFrontItem() {
        if (head == null) {
            return;
        }
        head=head.next;
        if (head == null) {
            tail=null;
        }
        size--;
    }

    public void removeBackItem() {
        if (head == null) {
            return;
        }
        if (head == tail) {
            head=tail=null;
            size=0;
            return;
        }
        Node current=head;
        while (current.next != tail) {
            current=current.next;
        }
        current.next=null;
        tail=current;
        size--;
    }

    public void printAll() {
        if (head == null) {
            System.out.println("List is Empty!");
            return;
        }
        Node current=head;
        while (current != null) {
            System.out.print(current.Data + " -> ");
            current=current.next;
        }
        System.out.println("null");
    }

    public void isListEmpty() {
        System.out.println(head == null ? "List is Empty!" : "List is not Empty!");
    }

    public void find( contacts target ) {
        int steps=0;
        Node current=head;
        while (current != null) {
            if (current.Data.equals(target)) {
                System.out.println(target + " is Found after " + steps + " occurrences!");
                return;
            }
            steps++;
            current=current.next;
        }
        System.out.println(target + " is NOT Found after " + steps + " occurrences!");
    }

    public void remove( contacts target ) {
        if (head == null) return;
        if (head.Data.equals(target)) {
            removeFrontItem();
            return;
        }
        Node current=head;
        while (current.next != null && !current.next.Data.equals(target)) {
            current=current.next;
        }
        if (current.next != null) {
            if (current.next == tail) {
                tail=current;
            }

            current.next=current.next.next;
            size--;
        }
    }

    public void addKeyBeforeNode( contacts key, contacts target ) {
        if (head == null) {
            System.out.println("List is Empty! Target not found.");
            return;
        }
        if (head.Data.equals(target)) {
            addToFront(key);
            return;
        }
        Node current=head;
        while (current.next != null && !current.next.Data.equals(target)) {
            current=current.next;
        }
        if (current.next == null) {
            System.out.println("Target not found in list.");
            return;
        }
        Node newNode=new Node(key);
        newNode.next=current.next;
        current.next=newNode;
        size++;
    }

    public void addKeyAfterNode( contacts key, contacts target ) {
        Node current=head;
        while (current != null && !current.Data.equals(target)) {
            current=current.next;
        }
        if (current == null) {
            System.out.println("Target not found in List");
            return;
        }
        Node newNode=new Node(key);
        newNode.next=current.next;
        current.next=newNode;
        if (current == tail) tail=newNode;
        size++;
    }

    public int size() {
        return size;
    }
}

public class LinkedList_tailMethods {
    public static void main( String[] args ) {
        LinkedList list=new LinkedList();
        list.addToFront(new contacts("Asma ", 19));
        list.addToFront(new contacts("Aisha ", 15));
        list.addToBack(new contacts("Eshal ", 4));
        list.addToBack(new contacts("Urooj", 13));
        System.out.println("Initial:");
        list.printAll();
        System.out.println("Front: " + list.getFrontItem());
        System.out.println("Back:  " + list.getBackItem());
        list.addKeyAfterNode(new contacts("Fatima", 9), new contacts("Urooj", 13));
        System.out.println("\nAfter addKeyAfterNode(Fatima after Urooj):");
        list.printAll();
        System.out.println("Back (O(1)): " + list.getBackItem());
        list.removeBackItem();
        System.out.println("\nAfter removeBackItem():");
        list.printAll();
        System.out.println("Back now: " + list.getBackItem());
        list.addToBack(new contacts("Zara", 8));
        list.addToBack(new contacts("Noor", 20));
        System.out.println("\nAfter two O(1) addToBack:");
        list.printAll();
        System.out.println("Size: " + list.size());
    }
}
