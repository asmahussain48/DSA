package Lab4.ClassMethods;


class userData {
    String Name;
    int Age;
    String PhoneNumber;
    String Address;

    public userData( String name , int age , String phoneNumber , String address ) {
        Name=name;
        Age=age;
        PhoneNumber=phoneNumber;
        Address=address;
    }

    @Override
    public String toString( ) {
        return " " + Name + ", ";
//                + " (" + Age + "), " + PhoneNumber + ", " + Address;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( ! (o instanceof userData) ) return false;
        userData u=(userData) o;
        return java.util.Objects.equals( PhoneNumber , u.PhoneNumber );


    }
}

class node {
    userData Data;
    node next ;
    node prev;
    node( userData Data){
        this.Data = Data;
        this.next = null;
        this.prev = null;
    }
}
public class LinkList_methods_class {
    node head;
    node tail;
    int size = 0;
    public void addToFront(userData Data){
       node newNode = new node( Data );
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
    public userData getFrontItem(){
        if (head == null){
            return null;
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
        node current = head.next;
        head.next = null;
        head = current;
        head.prev = null;
        size--;
    }

    public void addToBack(userData Data){
        node newNode = new node(Data);
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

    public userData getBackItem(){
        if(head== null){
            return null;
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
        node last = tail.prev;
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

    public void find(userData key){
        if(head == null){
            System.out.println("List is empty!" );
            return;
        }
        node current = head;
        int index = 0;
       while(current != null){
           if(java.util.Objects.equals( current.Data,  key )){
               System.out.println( "Key " + current.Data +" found at  "+ index + " index. ");
               index++;
               return;
           }
           current = current.next;
       }

    }
    public userData remove(int key) {
        if (head == null) {
            return null;
        }
        node current = head;
        while (current != null && java.util.Objects.equals( current.Data,  key )){
            current = current.next;
        }
        userData toRemove = current.Data;
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

    public void addKeyBeforeNode(userData Key, userData Data){
        node newNode = new node( Data );
        if(head == null){
            System.out.println("List is empty!" );
            return;
        }
        if(java.util.Objects.equals( head.Data,  Key )){
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            size++;
            return;
        }
        node current = head;
        while(current != null){
            if(java.util.Objects.equals( current.Data,  Key )){
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
    public void addKeyAfterNode(userData key, userData Data){
        node newNode = new node( Data );
        if(head == null){
            System.out.println("List is empty!" );
            return;
        }
        node current = head;
        while(current != null){
            if(java.util.Objects.equals( current.Data,  key )){
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
        node current = tail;
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
        node current = head;
        while(current != null){
            System.out.print(current.Data + " -> " );
            current = current.next;
        }
        System.out.println("null" );
    }

    public  void main( String[] args ) {
        LinkList_methods_class l1 = new LinkList_methods_class();
        userData u1 = new userData("Asma", 21, "03333886321", "Sukkur");
        userData u2 = new userData("Ali",  22, "03001234567", "Karachi");
        userData u3 = new userData("Hira", 20, "03451239876", "Lahore");
        userData u4 = new userData("Saad", 24, "03151234098", "Islamabad");
        userData u5 = new userData("Zoya", 23, "03211230987", "Quetta");

        l1.addToFront( u1 );
        l1.addToFront( u2 );
        l1.addToFront( u3 );
        l1.addToFront( u4 );
        l1.addToFront( u5 );
        l1.addToFront( u2 );
        l1.printAll();
        l1.removeFrontItem();
        System.out.println("After removing Front Item :  " );
        l1.printAll();
        System.out.println("After Adding Back Item :  " );
        userData u6  = new userData("Bilal", 25, "03045551234", "Faisalabad");
        l1.addToBack( u6 );
        l1.printAll();
        System.out.println ("Get Back Item:  " + l1.getBackItem() );
        System.out.println("Remove Back Item" );
        l1.removeBackItem();
        l1.printAll();
        System.out.println("FInd" );
        l1.find(u4);
        System.out.println("Is List Empty (True or False) : "+ l1.isListEmpty() );
        System.out.println("Add key Before Node" );
        userData u7  = new userData("Amna",  19, "03331234567", "Multan");
        l1.addKeyBeforeNode( u5, u7 );
        l1.printAll();
        System.out.println("Add key After Node" );
        userData u8  = new userData("Hamza", 27, "03141239876", "Peshawar");
        l1.addKeyAfterNode( u3, u8 );
        l1.printAll();
        System.out.println("After Reversing" );
        l1.printInReverseOrder();

        
    }   
}
