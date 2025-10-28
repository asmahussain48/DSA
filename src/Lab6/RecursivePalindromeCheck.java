package Lab6;

public class RecursivePalindromeCheck {

    public static void main(String[] args) {
        String s1 = "racecar";
        String s2 = "cake";

        System.out.println(s1 + " → " + (isPalindrome(s1) ? "Palindrome" : "Not Palindrome"));
        System.out.println(s2 + " → " + (isPalindrome(s2) ? "Palindrome" : "Not Palindrome"));
    }

    // Start checking from first and last index
    public static boolean isPalindrome(String s) {
        return check(s, 0, s.length() - 1);
    }

    private static boolean check(String s, int start, int end) {
        // If crossed or equal → palindrome confirmed
        if (start >= end)
            return true;

        // If characters do not match → not palindrome
        if (s.charAt(start) != s.charAt(end))
            return false;

        // Move towards center
        return check(s, start + 1, end - 1);
    }
}
