package Practice;

public class BubbleSort {

    public static void main(String[] args) {
        int arr[] = {6, 3, 9, 5, 2, 8};   // Unsorted array
        int n = arr.length;

        // Bubble Sort logic
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // swap arr[j] and arr[j + 1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        // Print sorted array
        System.out.println("Sorted Array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}

