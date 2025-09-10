package Practice;

class Node{
     Message Data;
     Node next;
     int data;
     Node(Message Data){
         this.Data = Data;
         this.next = null;
     }
     Node(int Data){
         this.data  = Data;
         this.next = null;
     }
 }

 class Message{
     String Name;
     int Age;
     Message(String Name , int Age){
         this.Name = Name;
         this.Age = Age;
     }
     @Override
     public String toString(){
         return Name + "  Age: (" + Age + ")";
     }
 }
class LinkList{
    Node head;
    public void addToFront(Message Data){
        Node newNode = new Node(Data);
        if(head == null){
            head = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }
    public void addToFront(int Data){
        Node newNode = new Node(Data);
        if(head == null){
            head = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }
    public Message getFrontItem(){
        if(head == null){
            return null;
        }
        return head.Data;
    }
    public void removeFrontItem(){
        if(head == null){
            return;
        }
        else{
            head = head.next;
        }
    }

    public void addToBack(Message Data){
        Node newNode = new Node(Data);
        if(head == null){
            head = newNode;
            return;
        }
        else {
            Node current = head;
            while (current.next != null){
                current= current.next;
            }
            current.next = newNode;
        }
    }
    public Message getBackItem(){
        if(head == null){
            return null;
        }else{
            Node current = head;
            while (current.next != null){
                current = current.next;
            }
            return current.Data;
        }
    }

    public void removeBackItem(){
        if (head == null){
            return;
        }
        else{
            Node current = head;
            while (current.next.next != null){
                current = current.next;
            }
            current.next = null;
        }
    }
    public void find(Message Data){
            if (head == null){
                return;
            }
            else{
                int count = 0;
                boolean check = false;
                Node current = head;
                while(current != null){
                    if(current.Data.Age == Data.Age){
                        check = true;
                        break;
                    }
                    count++;
                    current = current.next;
                }
                if(check){
                    System.out.println(Data.Name +", " + Data.Age+  " is Found after " + count +  " occurences!");
                }else{
                    System.out.println(Data.Name +", " + Data.Age +  " is NOT Found after " + count +  " occurences!");
                }
            }
    }

    public  void remove(Message Data){
        if(head == null){
            return;
        }
        if (head.Data.Age == Data.Age) {
            head = head.next;
            return;
        }
        Node current = head;
        while(current.next != null){
            if(current.next.Data.Age == Data.Age) {
                current.next=current.next.next;
                return;
            }
                current = current.next;
        }
    }

    private boolean messgeEqual(Message a , Message b){
        if(a == null || b == null) {
            return false;
        }
        boolean sameName = (a.Name == null && b.Name == null)
                || (a.Name != null && a.Name.equals(b.Name));
        return sameName && a.Age == b.Age;
    }

    public void addKeyBeforeNode(Message Key, Message target){
        if(head == null){
            System.out.println("List is empty!");
            return;
        }
        if(messgeEqual(head.Data, target)){
            Node newNode = new Node(Key);
            newNode.next = head;
            head = newNode;
            return;
        }
        Node current = head;
        while(current.next != null && !(messgeEqual(current.next.Data, target))){
            current = current.next;
        }
        if(current.next == null)
    }

    public void isListEmpty(){
        if(head == null){
            System.out.println("List is Empty! ");
        }
        else{
            System.out.println("List in not Empty!");
        }
    }

    public void display() {
        if (head == null) {
            System.out.println("List is Empty!");
            return;
        }

        Node current = head;
        while (current != null) {
            System.out.print(current.Data + " -> ");
            current = current.next;
        }
        System.out.println(" null");
    }

}

public class linkList1_SimpleMethods {
    public static void main(String[] args) {
        LinkList list = new LinkList();

        Message obj1 = new Message("Fatima", 9);
        list.addToFront(new Message("Asma ", 19));
        list.addToFront(new Message("Aisha ", 15));
        list.addToBack(new Message("Eshal ", 4));
        list.addToBack(new Message("Urooj" , 13));
        list.display();

        list.addKeyBeforeNode(obj1,new Message("Aisha ", 15));
        list.display();
//        list.remove(new Message("Eshal ", 4));
//        list.display();
//        list.isListEmpty();
//        LinkList l1 = new LinkList();
//        l1.isListEmpty();
//        list.find(new Message("Urooj" , 13));
//        list.removeBackItem();
//        System.out.println(list.getFrontItem());
//        list.removeFrontItem();
//        System.out.println(list.getBackItem());
//        list.display();
    }
}
