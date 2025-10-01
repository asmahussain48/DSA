package Lab5;

class Node3 {
    char data;
    Node3 next;

    public Node3(char data) {
        this.data = data;
        this.next = null;
    }
}

class StackQ5 {
    private Node3 top;

    public StackQ5() {
        top = null;
    }

    public void push(char value) {
        Node3 newNode = new Node3(value);
        newNode.next = top;
        top = newNode;
    }

    public char pop() {
        if (isEmpty()) {
            return '\0';
        }
        char popped = top.data;
        top = top.next;
        return popped;
    }

    public boolean isEmpty() {
        return top == null;
    }
}

public class Q5 {
    public static boolean isPalindrome(String str) {
        StackQ5 stack = new StackQ5();
        int length = str.length();

        for (int i = 0; i < length; i++) {
            stack.push(str.charAt(i));
        }

        for (int i = 0; i < length; i++) {
            if (str.charAt(i) != stack.pop()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s1 = "madam";
        String s2 = "hello";

        System.out.println(s1 + " is palindrome? " + isPalindrome(s1));
        System.out.println(s2 + " is palindrome? " + isPalindrome(s2));
    }
}
