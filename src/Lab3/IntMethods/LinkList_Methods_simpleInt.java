package Lab3.IntMethods;


class Node {
    int Data;
    Node next;

    Node(int Data){
        this.next = null;
        this.Data = Data;
    }
}

public class LinkList_Methods_simpleInt {

    Node head = null;
    Node tail = null;
    int size = 0;
    public void addToFront(int Data){
        Node newNode = new Node( Data );
        if(head == null){
            head = newNode;
            tail = newNode;
        }else{
            Node current = head;
            head = newNode;
            newNode.next = current;

        }
        size++;
    }
    public int getFrontItem(){
        if(head == null){
            return -1;
        }
        return head.Data;
    }
    public int getBackItem(){
        if(head == null){
            return -1;
        }
        Node current = head;
        while(current.next != null){
            current = current.next;
        }
        return current.Data;
    }
    public void removeFrontItem(){
        if(head == null){
            return;
        }
        if(head != null){
            head = head.next;
        }
        size--;
    }
    public void addtoBack(int Data){
        Node newNode = new Node(Data);
        if(head == null){
            head = newNode;
            tail = newNode;
        }else{
            tail.next = newNode;
            tail = newNode;
        }
        size++;
//            node newNode = new node( Data );
//            node current = head;
//            while(current.next != null){
//                current = current.next;
//            }
//            current.next = newNode;
    }
    public void removeBackItem(){
        if(head == null){
            return;
        }if ( head.next == null ) {
            head = null;
            tail = null;
            return;
        }
        Node current = head;
        while(current.next != tail){
            current = current.next;
        }
        current.next = null;
        tail = current;
        size--;
//        node current = head;
//        while(current != null){
//            if(current.next.next == null){
//                current.next = null;
//            }
//            current = current.next;
//        }
    }
    public void find(int key){
        if(head == null){
            return;
        }
        Node current = head;
        int count = 0;
        while (key != current.Data){
            current = current.next;
            count++;
        }
        if ( key == current.Data ) {
            System.out.println("Key find at " + count + " index ");
        }
    }
    public int remove(int key) {
        if ( head == null ) {
            return - 1;
        }
        if(head.Data == key){
            int toRemove = head.Data;
            head = head.next;
            if(head == null){
                tail = null;
                size--;
                return toRemove;
            }
        }
        Node current=head;
        while (current.next != null) {
            if ( current.next.Data == key ) {
                int toRemove = current.next.Data;
                current.next =current.next.next;
                size--;
                return toRemove;
            }
            current=current.next;
        }
        return -1;

    }

    public void addkeyBeforeNode(int Data, int key){
        if(head == null){
            System.out.println("List is empty!" );
        }
        if(head.Data == key){
            addtoBack( Data );
            return;
        }
        Node current = head;
        while(current != null){
            if(current.next.Data == key){
                Node newNode = new Node( Data );
                Node temp = current.next;
                current.next = newNode;
                newNode.next= temp;
                size++;
                break;
            }else{
                current = current.next;
            }
        }
    }

    public void addKeyAfterNode(int Data, int key){
        Node newNode = new Node( Data );
        if(head == null){
            System.out.println("List is empty!" );
        }

        Node current = head;
        while(current != null){
            if(current.Data == key){
                Node temp = current.next;

                current.next = newNode;
                newNode.next= temp;
                if(current == tail){
                    tail =current;
                }
                size++;
                break;
            }else{
                current= current.next;
            }
        }
    }

    public int getSize(){
        return size;
    }

    public int getIndex(int key){
        if(head == null){
            return -1;
        }else{
            Node current = head;
            int i = 0;
            while(current.next != null){
                if(current.Data == key){
                    return i;
                }
                current = current.next;
                i++;
            }
            return -1;
        }
    }

    public void insertAt(int index , int Data){

        if(head == null){
            System.out.println("List is empty!" );
            return;
        }if(index <0 || index> size){
            System.out.println("Index Out of Bounds!" );
        }
        if(index == 0){
            addToFront( Data );

            return;
        }
        if(index == size){
            addtoBack( Data );
            return;
        }
        else {
            Node current = head;

            for ( int i = 0 ; i <index -1 ; i++ ) {
                current=current.next;
            }
            Node newNode = new Node( Data );
            Node temp = current.next;
            current.next = newNode;
            newNode.next = temp;
            size++;
        }
    }
    public void removeFrom(int index){
        if(head == null){
            return;
        }
        Node current = head;
        for(int i = 0; i<index-1; i++){
            current = current.next;
        }
        Node toRemove = current.next;
        if(toRemove == tail){
            tail = current;
        }
        current.next = current.next.next;
        size--;
    }

    public void reverse(){
        if(head == null){
            return;
        }
        tail = head;
        Node current = head;
        Node nextNode = null;
        Node prevNode = null;
        while(current != null){
            nextNode = current.next;
            current.next = prevNode;
            prevNode = current;
            current = nextNode;
        }
        head = prevNode;
    }
    public void printAll(){
        if(head == null){
            System.out.println("Empty list!" );
        }
        Node current = head;
        while(current != null){
            System.out.print(current.Data + "-> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main( String[] args ) {
        LinkList_Methods_simpleInt l1 = new LinkList_Methods_simpleInt();
        l1.addToFront( 3 );
        l1.addToFront( 4 );
        l1.addToFront( 5 );
        l1.addToFront( 6 );
        l1.addToFront( 7 );
        l1.addToFront( 8 );
        l1.printAll();

        l1.removeFrontItem();
        l1.printAll();

        l1.addtoBack( 11 );
        l1.printAll();
        l1.removeBackItem();
        l1.printAll();

        l1.find( 4 );

        System.out.println("Front Item " +l1.getFrontItem() );
        System.out.println("Front Item " +l1.getBackItem() );
        System.out.println("Remove " + l1.remove( 6 ) );
        l1.printAll();

        l1.addkeyBeforeNode( 23, 5 );
        l1.printAll();

        l1.addKeyAfterNode( 41, 5 );
        l1.printAll();

        System.out.println("Index of 5 : "+l1.getIndex( 5 ) );

        l1.insertAt( 3, 10 );
        l1.printAll();


        l1.removeFrom( 3 );
        System.out.println("Index 3 Remove: ");
        l1.printAll();


        l1.reverse();
        l1.printAll();


    }
}
