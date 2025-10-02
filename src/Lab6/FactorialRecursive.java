package Lab6;
class FactorialRecursive {

    static int factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        int num = 5;
        int result = factorial(num);
        System.out.println("Factorial of " + num + " is: " + result);

        num = 7;
        result = factorial(num);
        System.out.println("Factorial of " + num + " is: " + result);
    }
}
