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
            return;
        }
        newNode.next = head;
        head = newNode;
        size++;
    }
    public void inserAtEnd(int Data){

    }

    public void printAll(){
        if(head == null){
            System.out.println("List is empty!" );
            return;
        }
        node current = head;
        while(current != null){
            System.out.print(current.Data + " -> " );
            current = current.next;
        }
        System.out.print("null" );
    }


    public void main( String[] args ) {
        cicularLinkList_methods l1 = new cicularLinkList_methods();
        l1.insertAtBegin( 5 );
        l1.insertAtBegin( 4 );
        l1.printAll();
    }
}
