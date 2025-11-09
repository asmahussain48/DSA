package Lab7;

public class MergeUsingInsertion {

    // Method to merge two arrays while sorting them
    public static int[] mergeArrays(int[] arr1, int[] arr2) {
        int n1 = arr1.length;
        int n2 = arr2.length;
        int[] merged = new int[n1 + n2];  // third array to store result
        int size = 0; // number of elements currently in merged array

        // Insert all elements from first array in sorted order
        for (int num : arr1) {
            int j = size - 1;
            while (j >= 0 && merged[j] > num) {
                merged[j + 1] = merged[j]; // shift right if bigger element found
                j--;
            }
            merged[j + 1] = num; // insert number at correct position
            size++;
        }

        // Insert all elements from second array in sorted order
        for (int num : arr2) {
            int j = size - 1;
            while (j >= 0 && merged[j] > num) {
                merged[j + 1] = merged[j];
                j--;
            }
            merged[j + 1] = num;
            size++;
        }

        return merged; // merged and sorted array
    }

    // Main function
    public static void main(String[] args) {
        int[] arr1 = {6, 3, 9, 5, 2};
        int[] arr2 = {8, 1, 7, 4, 10};

        int[] merged = mergeArrays(arr1, arr2); // call function

        System.out.print("Array 1: ");
        printArray(arr1);

        System.out.print("Array 2: ");
        printArray(arr2);

        System.out.print("Merged & Sorted Array: ");
        printArray(merged);
    }

    // Helper function to print an array
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
