package Practice;

import java.util.LinkedList;
import java.util.Queue;

class Node {
    int data;
    Node left;
    Node right;

    // Constructor
    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class BinaryTree {
    static int index = -1;

    // Build tree from an array representation
    public static Node buildTree(int nodes[]) {
        index++;
        if (index >= nodes.length || nodes[index] == -1) {
            return null;
        }

        Node newNode = new Node(nodes[index]);
        newNode.left = buildTree(nodes);
        newNode.right = buildTree(nodes);

        return newNode;
    }

    // Preorder Traversal (Root -> Left -> Right)
    public static void preorder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }

    // Inorder Traversal (Left -> Root -> Right)
    public static void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    // Postorder Traversal (Left -> Right -> Root)
    public static void postorder(Node root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + " ");
    }

    // Level Order Traversal (Breadth-First)
    public static void levelOrder(Node root) {
        if (root == null) return;

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null); // Marks end of level

        while (!q.isEmpty()) {
            Node curr = q.remove();
            if (curr == null) {
                System.out.println(); // New line for next level
                if (!q.isEmpty()) {
                    q.add(null);
                }
            } else {
                System.out.print(curr.data + " ");
                if (curr.left != null) {
                    q.add(curr.left);
                }
                if (curr.right != null) {
                    q.add(curr.right);
                }
            }
        }
    }

    public static void main(String args[]) {
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, -1};
        BinaryTree tree = new BinaryTree();
        Node root = tree.buildTree(nodes);

        System.out.println("Preorder Traversal:");
        tree.preorder(root);

        System.out.println("\nInorder Traversal:");
        tree.inorder(root);

        System.out.println("\nPostorder Traversal:");
        tree.postorder(root);

        System.out.println("\nLevel Order Traversal:");
        tree.levelOrder(root);
    }
}
