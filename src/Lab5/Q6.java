package Lab5;
class Node4 {
    char data;
    Node4 next;

    public Node4(char data) {
        this.data = data;
        this.next = null;
    }
}

class Stack4 {
    private Node4 top;

    public Stack4() {
        top = null;
    }

    public void push(char value) {
        Node4 newNode = new Node4(value);
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

public class Q6 {
    public static boolean isBalanced(String str) {
        Stack4 stack = new Stack4();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else if (ch == ')' || ch == '}' || ch == ']') {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if ((ch == ')' && top != '(') ||
                        (ch == '}' && top != '{') ||
                        (ch == ']' && top != '[')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s1 = "{[()]}";
        String s2 = "{[(])}";
        String s3 = "((()))";
        String s4 = "{[()]}}";

        System.out.println(s1 + " -> " + isBalanced(s1)); // true
        System.out.println(s2 + " -> " + isBalanced(s2)); // false
        System.out.println(s3 + " -> " + isBalanced(s3)); // true
        System.out.println(s4 + " -> " + isBalanced(s4)); // false
    }
}
