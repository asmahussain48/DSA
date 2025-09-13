package Lab3;

import javax.xml.crypto.Data;

class LinkeList {
    Node head;
    Node tail;
    int size;

    public void addToFront( contacts data ) {
        Node newNode=new Node(data);
        newNode.next=head;
        head=newNode;
        if (tail == null) tail=newNode;
        size++;
    }

    public void addToBack( contacts data ) {
        Node newNode=new Node(data);
        if (head == null) {
            head=tail=newNode;
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
        if (head == null) return;
        head=head.next;
        if (head == null) tail=null;
        size--;
    }

    public void removeBackItem() {
        if (head == null) return;
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
        while (current.next != null && ! current.next.Data.equals(target)) {
            current=current.next;
        }
        if (current.next != null) {
            if (current.next == tail) tail=current;
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
        while (current.next != null && ! current.next.Data.equals(target)) {
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
        while (current != null && ! current.Data.equals(target)) {
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

    // ---------- Index-based operations ----------

    public contacts get( int index ) {
        checkGetIndex(index);
        Node current = head;
        for (int i=0; i < index; i++) {
            current=current.next;
        }
        return current.Data;
    }

    public void insertAt( int index, contacts data ) {
        Node current = head;
        for (int i=0; i < (index - 1); i++) {
            current =current.next;
        }
        Node newNode=new Node(data);
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    public void insertAtEnd(contacts data){
        Node current = head;
        Node newNode = new Node(data);
        while(current.next != null){
            current = current.next;
        }
        current.next = newNode;
    }


    public contacts removeFrom( int index ) {
        Node current = head;
        for (int i=0; i < index - 1; i++){
            current =current.next;
        }
        Node toRemove = current.next; // for return the removeValue
        current.next= current.next.next;
        size--;
        return toRemove.Data;
    }

    public int getSize() {
        return size;
    }

    public int getLength() {
        return size;
    }

    private void checkGetIndex( int index ) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    public void reverse() {
        if (size <= 1){
            return;
        }
        for (int i = 1; i < size; i++) {
            Node current = head;
            for (int j=0; j < i - 1; j++){
                current =current.next;
            }
            Node toRemove = current.next; // for return the removeValue
            current.next= current.next.next;
            contacts x =  toRemove.Data;
            Node newNode=new Node(x);
            newNode.next=head;
            head=newNode;
            if (tail == null) tail=newNode;
        }
    }


}

public class LinkedList_IndexMedthods {
    public static void main( String[] args ) {
        LinkeList list=new LinkeList();

        list.addToFront(new contacts("Asma ", 19));
        list.addToFront(new contacts("Aisha ", 15));
        list.addToBack(new contacts("Eshal ", 4));
        list.addToBack(new contacts("Urooj", 13));
        list.printAll();

        System.out.println("Size: " + list.getSize());
        System.out.println("get(0): " + list.get(0));
        System.out.println("get(2): " + list.get(2));

         System.out.println("After insertAt(2, Fatima):");
        list.printAll();

        contacts removed=list.removeFrom(3);
        System.out.println("Removed at index 3: " + removed);
        list.printAll();

        System.out.println("After insertAt(size, Zara):");
        list.printAll();
        list.reverse();    // or list.reverseInPlace();
        list.printAll();
        System.out.println("Final Size via getLength(): " + list.getLength());
    }
}
