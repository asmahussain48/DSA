package Lab6;

class MyStack {
    private int[] arr;
    private int top;

    MyStack( int size ) {
        arr=new int[size];
        top=- 1;
    }

    public boolean isEmpty() {
        return top == - 1;
    }

    public void push( int x ) {
        if ( top == arr.length - 1 ) throw new RuntimeException( "Overflow" );
        arr[++ top]=x;
    }

    public int pop() {
        if ( isEmpty( ) ) throw new RuntimeException( "Underflow" );
        return arr[top--];
    }

    public void print() {
        for ( int i=0 ; i <= top ; i++ ) System.out.print( arr[i] + " " );
        System.out.println( );
    }

    private void insertAtBottom( int x ) {
        if ( isEmpty( ) ) {
            push( x );
            return;
        }
        int t=pop( );
        insertAtBottom( x );
        push( t );
    }

    public void reverse() {
        if ( isEmpty( ) )
            return;
        int t=pop( );
        reverse( );
        insertAtBottom( t );
    }
}

public class ReverseCustomStack {
    public static void main( String[] args ) {
        MyStack st=new MyStack( 10 );

        st.push( 1 );
        st.push( 2 );
        st.push( 3 );
        st.push( 4 );
        st.push( 5 );

        System.out.print( "Original: " );
        st.print( );

        st.reverse( );

        System.out.print( "Reversed: " );
        st.print( );
    }
}

/*
================ DRY RUN OF RECURSIVE STACK REVERSAL ================

Initial Stack (bottom -> top): [1, 2, 3, 4, 5]

Step 1: reverse()
   pop 5 → stack [1, 2, 3, 4], recurse

Step 2: reverse()
   pop 4 → stack [1, 2, 3], recurse

Step 3: reverse()
   pop 3 → stack [1, 2], recurse

Step 4: reverse()
   pop 2 → stack [1], recurse

Step 5: reverse()
   pop 1 → stack [], recurse

Step 6: reverse()
   stack empty → return

Unwinding recursion:
   insertAtBottom(1) → [1]
   insertAtBottom(2) → [2, 1]
   insertAtBottom(3) → [3, 2, 1]
   insertAtBottom(4) → [4, 3, 2, 1]
   insertAtBottom(5) → [5, 4, 3, 2, 1]

Final Stack (bottom -> top): [5, 4, 3, 2, 1]
*/
