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
    public void addToFront(int Data){
        node newNode = new node( Data );
        node current = head;
        head = newNode;
        newNode.next = current;
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

    }
    public void removeFrontItem(){
        if(head == null){
            return;
        }
        if(head != null){
            head = head.next;
        }
    }
    public void addtoBack(int Data){
        if(head == null){
            return;
        }
            node newNode = new node( Data );
            node current = head;
            while(current.next != null){
                current = current.next;
            }
            current.next = newNode;
    }
    public void removeBackItem(){
        if(head == null){
            return;
        }
        node current = head;
        while(current != null){
            if(current.next.next == null){
                current.next = null;
            }
            current = current.next;
        }
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


    public void printAll(){
        if(head == null){
            System.out.println("Empty list!" );
        }
        node current = head;
        while(current != null){
            System.out.print(current.Data + "-> ");
            current = current.next;
        }
        System.out.println("null" );
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
        l1.removeFrontItem();
        l1.printAll();

        l1.addtoBack( 11 );
        l1.printAll();
        l1.removeBackItem();
        l1.printAll();

        l1.find( 4 );
    }
}
