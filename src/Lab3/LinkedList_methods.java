package Lab3;

class Node{
    contacts Data;
    Node next;
    int data;
    Node(contacts Data){
        this.Data = Data;
        this.next = null;
    }
    Node(int Data){
        this.data  = Data;
        this.next = null;
    }
}

class contacts {
    String Name;
    int Age;
    contacts(String Name , int Age){
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
    public void addToFront(contacts Data){
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
    public contacts getFrontItem(){
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

    public void addToBack(contacts Data){
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
    public contacts getBackItem(){
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
    public void find(contacts Data){
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

    public  void remove(contacts Data){
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

    private boolean messgeEqual(contacts a , contacts b){
        if(a == null || b == null) {
            return false;
        }
        boolean sameName = (a.Name == null && b.Name == null)
                || (a.Name != null && a.Name.equals(b.Name));
        return sameName && a.Age == b.Age;
    }

    public void addKeyBeforeNode(contacts Key, contacts target){
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
        if(current.next == null){
            System.out.println("Target not found in List");
            return;
        }
        Node newNode = new Node(Key);
        newNode.next = current.next;
        current.next = newNode;
    }

    public void addKeyAfterNode(contacts Key, contacts target){
        if(head == null){
            System.out.println("List is empty!");
            return;
        }
        Node current = head;
        while(current.next != null && !(messgeEqual(current.Data, target))){
            current = current.next;
        }
        if(current == null){
            System.out.println("Target not found in List");
            return;
        }
        Node newNode = new Node(Key);
        newNode.next = current.next;
        current.next = newNode;
    }



    public void isListEmpty(){
        if(head == null){
            System.out.println("List is Empty! ");
        }
        else{
            System.out.println("List in not Empty!");
        }
    }

    public void printAll() {
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

public class LinkedList_methods {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        list.addToFront(new contacts("Asma ", 19));
        list.addToFront(new contacts("Aisha ", 15));
        list.addToBack(new contacts("Eshal ", 4));
        list.addToBack(new contacts("Urooj", 13));
        System.out.println("Initial:");
        list.printAll();
        System.out.println("Front: " + list.getFrontItem());
        System.out.println("Back:  " + list.getBackItem());

        // Insert AFTER target
        contacts obj1 = new contacts("Fatima", 9);
        list.addKeyAfterNode(obj1, new contacts("Aisha ", 15));
        System.out.println("After addKeyAfterNode(Fatima, after Aisha):");
        list.printAll();

        // Insert BEFORE target
        list.addKeyBeforeNode(new contacts("Zara", 8), new contacts("Urooj", 13));
        System.out.println("After addKeyBeforeNode(Zara, before Urooj):");
        list.printAll();

        // Find
        list.find(new contacts("Zara", 8));
        list.find(new contacts("Noor", 20));

        // Remove front / back / by value
        list.removeFrontItem();
        System.out.println("After removeFrontItem():");
        list.printAll();

        list.removeBackItem();
        System.out.println("After removeBackItem():");
        list.printAll();

        list.remove(new contacts("Fatima", 9));
        System.out.println("After remove(Fatima):");
        list.printAll();

        // Empty check
        list.isListEmpty();
    }
}
