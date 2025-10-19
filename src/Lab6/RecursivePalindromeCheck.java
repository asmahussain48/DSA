package Lab6;

public class RecursivePalindromeCheck {

    public static void main(String[] args) {
        String s1 = "Racecar";
        String s2 = "Cake";

        System.out.println(s1 + " → " + (isPalindrome(s1) ? "Palindrome" : "Not Palindrome"));
        System.out.println(s2 + " → " + (isPalindrome(s2) ? "Palindrome" : "Not Palindrome"));
    }

    // Recursive method to check if string is palindrome
    public static boolean isPalindrome(String s) {
        // Convert to lowercase and remove spaces (optional)
        s = s.toLowerCase().replaceAll("\\s+", "");
        return checkPalindrome(s, 0, s.length() - 1);
    }

    // Helper recursive function
    private static boolean checkPalindrome(String s, int start, int end) {
        // Base case 1: If start >= end, all characters checked
        if (start >= end)
            return true;

        // Base case 2: Mismatch found
        if (s.charAt(start) != s.charAt(end))
            return false;

        // Recursive call to check inner substring
        return checkPalindrome(s, start + 1, end - 1);
    }
}
