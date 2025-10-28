package Lab6;
import java.util.Scanner;

class FibonacciRecursive {

    static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of terms (N): ");
        int N = sc.nextInt();

        System.out.println("Recrusive fun" );
        System.out.println("Fibonacci Series up to " + N + " terms:");
        for (int i = 0; i < N; i++) {
            System.out.print(fibonacci(i) + " ");
        }
    }
}
/*
Dry Run Example (N = 7)
We want first 7 terms → calls: fibonacci(0) to fibonacci(6)
fibonacci(0) → base case → 0
fibonacci(1) → base case → 1
fibonacci(2) → calls fibonacci(1) + fibonacci(0) → 1 + 0 = 1
fibonacci(3) → calls fibonacci(2) + fibonacci(1) → 1 + 1 = 2
fibonacci(4) → calls fibonacci(3) + fibonacci(2) → 2 + 1 = 3
fibonacci(5) → calls fibonacci(4) + fibonacci(3) → 3 + 2 = 5
fibonacci(6) → calls fibonacci(5) + fibonacci(4) → 5 + 3 = 8
 */