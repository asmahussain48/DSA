package Practice;

class node{
    int Data;
    node next;

    node(int Data){
        this.next = null;
        this.Data = Data;
    }
}
public class linklist1 {
    node head = null;
    node tail = null;
    int size = 0;
    public void addToFront(int Data){
        node newNode = new node( Data );
        if(head == null){
            head = newNode;
            tail = newNode;
        }else{
            node current = head;
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
        node current = head;
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
        node newNode = new node(Data);
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
        node current = head;
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
        node current = head;
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
        node current=head;
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
        node current = head;
        while(current != null){
            if(current.next.Data == key){
                node newNode = new node( Data );
                node temp = current.next;
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
        node newNode = new node( Data );
        if(head == null){
            System.out.println("List is empty!" );
        }

        node current = head;
        while(current != null){
            if(current.Data == key){
                node temp = current.next;

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
            node current = head;
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
            node current = head;

            for ( int i = 0 ; i <index -1 ; i++ ) {
                current=current.next;
            }
            node newNode = new node( Data );
            node temp = current.next;
            current.next = newNode;
            newNode.next = temp;
            size++;
        }
    }
    public void removeFrom(int index){
    if(head == null){
        return;
    }
    node current = head;
    for(int i = 0; i<index-1; i++){
        current = current.next;
    }
    node toRemove = current.next;
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
        node current = head;
        node nextNode = null;
        node prevNode = null;
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
        node current = head;
        while(current != null){
            System.out.print(current.Data + "-> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main( String[] args ) {
        linklist1 l1 = new linklist1();
        l1.addToFront( 3 );
        l1.addToFront( 4 );
        l1.addToFront( 5 );
        l1.addToFront( 6 );
        l1.addToFront( 7 );
        l1.addToFront( 8 );
        l1.printAll();

//        l1.removeFrontItem();
//        l1.printAll();
//
//        l1.addtoBack( 11 );
//        l1.printAll();
//        l1.removeBackItem();
//        l1.printAll();
//
//        l1.find( 4 );
//
//        System.out.println("Front Item " +l1.getFrontItem() );
//        System.out.println("Front Item " +l1.getBackItem() );
//        System.out.println("Remove " + l1.remove( 6 ) );
//        l1.printAll();

//        l1.addkeyBeforeNode( 23, 5 );
//        l1.printAll();
//
//        l1.addKeyAfterNode( 41, 5 );
//        l1.printAll();
//
//        System.out.println("Index of 5 : "+l1.getIndex( 5 ) );
//
//        l1.insertAt( 3, 10 );
//        l1.printAll();


//        l1.removeFrom( 3 );
//        System.out.println("Index 3 Remove: ");
//        l1.printAll();
//

        l1.reverse();
        l1.printAll();


    }
}
