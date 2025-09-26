package Lab3.ClassMethods;

class UserData {
    String Name;
    int Age;
    String PhoneNumber;
    String Address;

    public UserData( String name , int age , String phoneNumber , String address ) {
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
        if ( ! (o instanceof UserData) ) return false;
        UserData u=(UserData) o;
        return java.util.Objects.equals( PhoneNumber , u.PhoneNumber );


    }
}

    class node {
        UserData Data;
        node next;

        node( UserData Data ) {
            this.next=null;
            this.Data=Data;
        }
    }

    public class LinkList_methods_class {
        node head=null;
        node tail=null;
        int size=0;

        public void addToFront( UserData Data ) {
            node newNode=new node( Data );
            if ( head == null ) {
                head=newNode;
                tail=newNode;
            } else {
                node current=head;
                head=newNode;
                newNode.next=current;

            }
            size++;
        }

        public UserData getFrontItem( ) {
            if ( head == null ) {
                return null;
            }
            return head.Data;
        }

        public UserData getBackItem( ) {
            if ( head == null ) {
                return null;
            }
            node current=head;
            while (current.next != null) {
                current=current.next;
            }
            return current.Data;
        }

        public void removeFrontItem( ) {
            if ( head == null ) {
                return;
            }
            if ( head != null ) {
                head=head.next;
            }
            size--;
        }

        public void addtoBack( UserData Data ) {
            node newNode=new node( Data );
            if ( head == null ) {
                head=newNode;
                tail=newNode;
            } else {
                tail.next=newNode;
                tail=newNode;
            }
            size++;
//            node newNode = new node( Data );
//            node current = head;
//            while(current.next != null){
//                current = current.next;
//            }
//            current.next = newNode;
        }

        public void removeBackItem( ) {
            if ( head == null ) {
                return;
            }
            if ( head.next == null ) {
                head=null;
                tail=null;
                return;
            }
            node current=head;
            while (current.next != tail) {
                current=current.next;
            }
            current.next=null;
            tail=current;
            size--;
//        node current = head;
//        while(current != null){
//            if(current.next.next == null){
//                current.next = null;
//            }
//            current = current.next;
//        }
        }

        public int find( UserData key ) {
            if(java.util.Objects.equals( head.Data , key ) ){
                System.out.println( "Key found at " + 0 + " index" );
                return 0;
            }
            node current=head;
            int index=0;
            while (current != null) {
                if ( java.util.Objects.equals( current.Data , key ) ) {
                    System.out.println( "Key found at " + index + " index" );
                    return index;
                }
                current=current.next;
                index++;
            }
            System.out.println( "Key not found" );
            return - 1;
        }

        public UserData remove( UserData key ) {
            if ( head == null ) {
                return null;
            }
            if ( head.Data == key ) {
                UserData toRemove=head.Data;
                head=head.next;
                if ( head == null ) {
                    tail=null;
                    size--;
                    return toRemove;
                }
            }
            node current=head;
            while (current.next != null) {
                if ( current.next.Data == key ) {
                    UserData toRemove=current.next.Data;
                    current.next=current.next.next;
                    size--;
                    return toRemove;
                }
                current=current.next;
            }
            return null;

        }

        public void addkeyBeforeNode( UserData Data , UserData key ) {
            if ( head == null ) {
                System.out.println( "List is empty!" );
            }
            if ( head.Data == key ) {
                addtoBack( Data );
                return;
            }
            node current=head;
            while (current != null) {
                if ( current.next.Data == key ) {
                    node newNode=new node( Data );
                    node temp=current.next;
                    current.next=newNode;
                    newNode.next=temp;
                    size++;
                    break;
                } else {
                    current=current.next;
                }
            }
        }

        public void addKeyAfterNode( UserData Data , UserData key ) {
            node newNode=new node( Data );
            if ( head == null ) {
                System.out.println( "List is empty!" );
            }

            node current=head;
            while (current != null) {
                if ( current.Data == key ) {
                    node temp=current.next;

                    current.next=newNode;
                    newNode.next=temp;
                    if ( current == tail ) {
                        tail=current;
                    }
                    size++;
                    break;
                } else {
                    current=current.next;
                }
            }
        }

        public int getSize( ) {
            return size;
        }

        public int getIndex( UserData key ) {
            if ( head == null ) {
                return - 1;
            } else {
                node current=head;
                int i=0;
                while (current.next != null) {
                    if ( current.Data == key ) {
                        return i;
                    }
                    current=current.next;
                    i++;
                }
                return - 1;
            }
        }

        public void insertAt( int index , UserData Data ) {

            if ( head == null ) {
                System.out.println( "List is empty!" );
                return;
            }
            if ( index < 0 || index > size ) {
                System.out.println( "Index Out of Bounds!" );
            }
            if ( index == 0 ) {
                addToFront( Data );
                return;
            }
            if ( index == size ) {
                addtoBack( Data );
                return;
            } else {
                node current=head;

                for ( int i=0 ; i < index - 1 ; i++ ) {
                    current=current.next;
                }
                node newNode=new node( Data );
                node temp=current.next;
                current.next=newNode;
                newNode.next=temp;
                size++;
            }
        }

        public void removeFrom( int index ) {
            if ( head == null ) {
                return;
            }
            node current=head;
            for ( int i=0 ; i < index - 1 ; i++ ) {
                current=current.next;
            }
            node toRemove=current.next;
            if ( toRemove == tail ) {
                tail=current;
            }
            current.next=current.next.next;
            size--;
        }

        public void reverse( ) {
            if ( head == null ) {
                return;
            }
            tail=head;
            node current=head;
            node nextNode=null;
            node prevNode=null;
            while (current != null) {
                nextNode=current.next;
                current.next=prevNode;
                prevNode=current;
                current=nextNode;
            }
            head=prevNode;
        }

        public void printAll( ) {
            if ( head == null ) {
                System.out.println( "Empty list!" );
            }
            node current=head;
            while (current != null) {
                System.out.print( current.Data + "-> " );
                current=current.next;
            }
            System.out.println( "null" );
        }

        public void main( String[] args ) {
            LinkList_methods_class l1=new LinkList_methods_class( );
            System.out.println( "Added Data -> " );
            UserData u1=new UserData( "Asma" , 21 , "03333886321" , "Sukkur" );
            UserData u2=new UserData( "Ali" , 22 , "03001234567" , "Karachi" );
            UserData u3=new UserData( "Hira" , 20 , "03451239876" , "Lahore" );
            UserData u4=new UserData( "Saad" , 24 , "03151234098" , "Islamabad" );

            l1.addToFront( u1 );
            l1.addToFront( u2 );
            l1.addToFront( u3 );
            l1.addToFront( u4 );

            l1.printAll( );

            System.out.print( " Remove Front Item -> " );
            l1.removeFrontItem( );
            l1.printAll( );
            System.out.print( " Add to Back newData -> " );
            UserData u5=new UserData( "Zoya" , 23 , "03211230987" , "Quetta" );
            l1.addtoBack( u5 );
            l1.printAll( );
            System.out.print( " Remove Back Item -> " );
            l1.removeBackItem( );
            l1.printAll( );
            System.out.print( " Find -> " );
            l1.find( u3 );

            System.out.println( "Front Item " + l1.getFrontItem( ) );
            System.out.println( "Back Item " + l1.getBackItem( ) );
            System.out.println( "Remove " + l1.remove( u1 ) );
            l1.printAll( );

            System.out.println( "Add 5th data before 2nd" );
            l1.addkeyBeforeNode( u5 , u2 );
            l1.printAll( );
            System.out.print( " Add first after 2nd  -> " );
            l1.addKeyAfterNode( u1 , u2 );
            l1.printAll( );

            System.out.println( "Index of 5 : " + l1.getIndex( u3 ) );

            l1.insertAt( 3 , u2 );
            l1.printAll( );


            l1.removeFrom( 3 );
            System.out.println( "Index 3 Remove: " );
            l1.printAll( );
//

            l1.reverse( );
            l1.printAll( );


        }
    }
