package Practice;

public class InsertionSort {

    public static void main(String[] args) {
        int arr[] = {6, 3, 9, 5, 2, 8};   // Unsorted array

        // Insertion Sort logic
        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];   // Current element to insert
            int j = i - 1;

            // Move larger elements to the right
            while (j >= 0 && arr[j] > current) {
                arr[j + 1] = arr[j];
                j--;
            }

            // Insert current at the correct position
            arr[j + 1] = current;
        }

        // Print sorted array
        System.out.println("Sorted Array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}

/*
📘 BASIC NOTES — INSERTION SORT

➡ Idea:
   - It builds the sorted part of the array one element at a time.
   - It picks an element (called 'current') and inserts it into its correct place
     among the already sorted elements on the left.

➡ Steps:
   1. Start from index 1 (second element).
   2. Compare the current element with the elements before it.
   3. Shift all larger elements one step to the right.
   4. Insert the current element in the right position.

➡ Example:
   Unsorted: [6, 3, 9, 5, 2, 8]
   After 1st pass → [3, 6, 9, 5, 2, 8]
   After 2nd pass → [3, 6, 9, 5, 2, 8] (no change)
   After 3rd pass → [3, 5, 6, 9, 2, 8]
   After 4th pass → [2, 3, 5, 6, 9, 8]
   After 5th pass → [2, 3, 5, 6, 8, 9]

➡ Time Complexity:
   - Worst Case: O(n²)
   - Best Case (already sorted): O(n)

➡ Space Complexity:
   - O(1) (In-place sorting)

➡ Good for:
   - Small or nearly sorted arrays.
*/