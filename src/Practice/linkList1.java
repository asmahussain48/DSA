package Practice;
 class Node{
     Message Data;
     Node next;
     Node(Message Data){
         this.Data = Data;
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
         return Name + "( " + Age + " )";
     }
 }
class LinkList{
    Node head;
    public void addToFirst(Message Data){
        Node newNode = new Node(Data);
        if(head == null){
            head = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }
    public void display(){
        if(head == null){
            System.out.println("List is Empty!");
            return;
        }
        else{
            Node current = head;
            while (current != null){
                System.out.print(current.Data);
                if(current.next != null){
                    System.out.print(" -> ");
                }else{
                    System.out.print(" -> null");
                }
                current = current.next;
            }
        }
    }
}

public class linkList1 {
    public static void main(String[] args) {
        LinkList list = new LinkList();
        list.addToFirst(new Message("Asma ", 19));
        list.addToFirst(new Message("Aisha ", 14));
        list.display();


    }
}
