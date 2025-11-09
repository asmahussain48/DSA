package Practice;

public class SelectionSort {

    public static void main(String[] args) {
        int arr[] = {6, 3, 9, 5, 2, 8};   // Unsorted array
        int n = arr.length;

        // Selection Sort logic
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;  // assume current index has the smallest element

            // find the index of the smallest element in the unsorted part
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;  // update smallest element index
                }
            }

            // swap smallest element with the first unsorted element
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }

        // Print sorted array
        System.out.println("Sorted Array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
