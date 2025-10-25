package Lab4.intMethods;

class Node{
    int Data;
    Node next ;
    Node prev;
    Node(int Data){
        this.Data = Data;
        this.next = null;
        this.prev = null;
    }
}


public class LinkList_methods_simpleInt {
    Node head;
    Node tail;
    int size = 0;
    public void addToFront(int Data){
       Node newNode = new Node( Data );
       newNode.prev = null;
       newNode.next = head;
       if(head != null){
           head.prev = newNode;
       }
       if(head == null){
           tail = newNode;
       }
       head = newNode;
       size++;
    }
    public int getFrontItem(){
        if (head == null){
            return -1;
        }
        return head.Data;
    }
    public void removeFrontItem(){
        if(head == null){
            System.out.println("List is empty! can't remove any item" );
            return;
        }
        if(head.next == null){
            head = null;
//            tail = null;
            size--;
            return;
        }
        Node current = head.next;
        head.next = null;
        head = current;
        head.prev = null;
        size--;
    }

    public void addToBack(int Data){
        Node newNode = new Node(Data);
        if(head == null){
            head = newNode;
            return;
        }
//        Node current = head;
//        while(current.next != null){
//            current = current.next;
//        }
//        current.next = newNode;
//        newNode.prev = current;
//        size++;

        newNode.prev = tail;
        tail.next = newNode;
        tail = newNode;
        size++;
    }

    public int getBackItem(){
        if(head== null){
            return -1;
        }
//        Node current = head;
//        while(current.next != null){
//            current = current.next;
//        }
//        return current.Data;
        return tail.Data;

    }

    public void removeBackItem(){
        if(head == null){
            System.out.println("List is emptry!" );
            return;
        }
        Node last = tail.prev;
        last.next = null;
        tail = last;

//        Node lastNode = head; // walk to the last
//        while(lastNode.next != null){
//            lastNode = lastNode.next;
//        }
//
//        Node beforeLast = lastNode.prev;
//        beforeLast.next = null;
//        size--;
    }

    public void find(int key){
        if(head == null){
            System.out.println("List is empty!" );
            return;
        }
        Node current = head;
        int index = 0;
       while(current != null){
           if(current.Data == key){
               System.out.println( "Key " + current.Data +" found at  "+ index + " index. ");
               return;
           }
           current = current.next;
           index++;
       }
        System.out.println("Key " + key + " not found in the list.");
    }
    public int remove(int key) {
        if (head == null) {
            return -1;
        }
        Node current = head;
        while (current != null && current.Data != key){
            current = current.next;
        }
        int toRemove = current.Data;
        if (current.prev != null) {
            current.prev.next = current.next;
        }
        if (current.next != null){
            current.next.prev = current.prev;
        }

        current.next = null;
        current.prev = null;
        size--;
        return toRemove;
    }

    public boolean isListEmpty(){
        if(head == null){
            return true;
        }
        return false;
    }

    public void addKeyBeforeNode(int Key, int Data){
        Node newNode = new Node( Data );
        if(head == null){
            System.out.println("List is empty!" );
            return;
        }
        if(head.Data == Key){
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            size++;
            return;
        }
        Node current = head;
        while(current != null){
            if(current.Data == Key){
                newNode.prev = current.prev;
                newNode.next = current;
                current.prev.next= newNode;
                current.prev = newNode;
                size++;
                return;
            }
            current = current.next;
        }

    }
    public void addKeyAfterNode(int key, int Data){
        Node newNode = new Node( Data );
        if(head == null){
            System.out.println("List is empty!" );
            return;
        }
        Node current = head;
        while(current != null){
            if(current.Data == key){
                newNode.next = current.next;
                current.next.prev = newNode;
                newNode.prev = current;
                current.next = newNode;
                size++;
                return;
            }
            current = current.next;
        }
    }
    public void printInReverseOrder(){
        if(tail == null){
            System.out.println("List is empty!" );
            return;
        }
        Node current = tail;
        while(current != null){
            System.out.print(current.Data + " -> " );
            current = current.prev;
        }
        System.out.println("null" );
    }


    public void printAll(){
        if(head == null){
            System.out.println("List is empty!" );
            return;
        }
        Node current = head;
        while(current != null){
            System.out.print(current.Data + " -> " );
            current = current.next;
        }
        System.out.println("null" );
    }

    public  void main( String[] args ) {
        LinkList_methods_simpleInt l1 = new LinkList_methods_simpleInt();

        l1.addToFront( 2 );
        l1.addToFront( 4 );
        l1.addToFront( 5 );
        l1.addToFront( 6 );
        l1.addToFront( 7 );
        l1.addToFront( 8 );
        l1.addToFront( 9 );
        l1.addToFront( 10 );
        l1.addToFront( 2);
        l1.addToFront( 3 );
        l1.printAll();
//        l1.removeFrontItem();
//        System.out.println("After removing Front Item :  " );
//        l1.printAll();
//        System.out.println("After Adding Back Item :  " );
//        l1.addToBack( 1 );
//        l1.printAll();
//        System.out.println ("Get Back Item:  " + l1.getBackItem() );
//        System.out.println("Remove Back Item" );
//        l1.removeBackItem();
//        l1.printAll();
//        System.out.println("FInd 4" );
//        l1.find(4);
//        System.out.println("Is List Empty (True or False) : "+ l1.isListEmpty() );
//        System.out.println("Add key Before Node" );
//        l1.addKeyBeforeNode( 6, 1 );
//        l1.printAll();
//        System.out.println("Add key After Node" );
//        l1.addKeyAfterNode( 10, 11 );
//        l1.printAll();
//        System.out.println("After Reversing" );
//        l1.printInReverseOrder();


    }



}
