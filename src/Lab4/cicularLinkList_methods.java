package Lab4;


class node{
    int Data;
    node next;
    node(int Data){
        this.Data = Data;
        this.next = null;
    }
}

public class cicularLinkList_methods {
    node head;
    node tail;
    int size;
    public void insertAtBegin( int Data){
        node newNode  = new node(Data);
        if(head == null){
            head = newNode;
            tail = newNode;
            tail.next = head;
            return;
        }
        newNode.next = head;
        head = newNode;
        tail.next = head;
        size++;
    }
    public void inserAtEnd(int Data){
        node newNode = new node(Data);
        if(head == null){
            head = newNode;
            tail = newNode;
            tail.next= head;
        }
        tail.next = newNode;
        tail = newNode;
        tail.next = head;
        size++;

    }

    public void delteFromBegin(){
        if(head == null){
            System.out.println("List is empty!" );
            return;
        }
        if(head == tail){
            head = null;
            tail = null;
        }
        head = head.next;
        tail.next = head;
        size--;
    }
    public void deleteFromEnd(){
        if(head == null){
            System.out.println("List is empty!" );
            return;
        }
        if(head == tail){
            head = null;
            tail = null;
        }
        node current = head;
        while(current.next != tail){
            current = current.next;
        }
        tail = current;
        current.next = head;
        tail.next = head;
        size--;
    }

    public boolean hasCycle(){
        if(head == null){
            return false;
        }
        node slow = head;
        node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;           // move 1 step
            fast = fast.next.next;      // move 2 steps

            if (slow == fast) {
                return true;  // cycle found
            }
        }
        return false;
    }

    public int middleElement(){
        if (head == null){
            return -1;
        }

        node slow = head;
        node fast = head;

        while(fast.next != head && fast.next.next != head){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow.Data;
    }


    public void displayList(){
        if(head == null){
            System.out.println("List is empty!" );
            return;
        }
        node current = head;
        do{
            System.out.print(current.Data + " -> " );
            current = current.next;
        }while(current != head);
        System.out.println(" (back to head) " );
    }


    public void main( String[] args ) {
        cicularLinkList_methods l1 = new cicularLinkList_methods();
        System.out.println("Insert at begin 5" );
        l1.insertAtBegin( 5 );
        System.out.println("Insert at begin 4" );
        l1.insertAtBegin( 4 );
        System.out.println("Insert at End 6" );
        l1.inserAtEnd( 6 );
        System.out.println("Insert at End 7" );
        l1.inserAtEnd( 7 );
        l1.displayList();
        System.out.println("Delete From Begin" );
        l1.delteFromBegin();
        l1.displayList();
        l1.deleteFromEnd();
        System.out.println("Delete From End" );
        l1.displayList();
        System.out.println("IS cyle (true or false): " + l1.hasCycle());

        l1.inserAtEnd( 1 );
        l1.inserAtEnd( 2 );
        l1.inserAtEnd( 3 );
        l1.inserAtEnd( 4 );
        l1.inserAtEnd( 5 );
        l1.inserAtEnd( 7 );
        l1.displayList();
        System.out.println("Middle Element: " + l1.middleElement() );
    }
}
