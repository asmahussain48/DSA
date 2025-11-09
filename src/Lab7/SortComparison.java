package Lab7;

public class SortComparison {

    // ---------- Bubble Sort ----------
    // Repeatedly compares adjacent elements and swaps them if out of order.
    // After each pass, the largest element "bubbles up" to the end.
    public static void bubbleSort(int[] arr) {
        int comparisons = 0;
        int swaps = 0;
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                comparisons++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                }
            }
        }

        System.out.println("Bubble Sort -> Comparisons: " + comparisons + ", Swaps: " + swaps);
    }

    // ---------- Selection Sort ----------
    // Finds the smallest element in the unsorted part and puts it at the beginning.
    public static void selectionSort(int[] arr) {
        int comparisons = 0;
        int swaps = 0;
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                swaps++;
            }
        }

        System.out.println("Selection Sort -> Comparisons: " + comparisons + ", Swaps: " + swaps);
    }

    // ---------- Insertion Sort ----------
    // Builds the sorted list one element at a time.
    // Takes each element and inserts it into the correct position.
    // compare arr[j] with current
    // shift element to the right
    public static void insertionSort(int[] arr) {
        int comparisons = 0;
        int swaps = 0;
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int current = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > current) {
                comparisons++;
                arr[j + 1] = arr[j];
                swaps++;
                j--;
            }

            // One more comparison when loop stops
            if (j >= 0) comparisons++;

            arr[j + 1] = current;
        }

        System.out.println("Insertion Sort -> Comparisons: " + comparisons + ", Swaps: " + swaps);
    }

    // ---------- MAIN ----------
    public static void main(String[] args) {

        int[] random = {6, 3, 9, 5, 2, 8};
        int[] descending = {9, 8, 6, 5, 3, 2};
        int[] Sorted = {2, 3, 5, 6,8,9};

        System.out.println("=== Random Array ===");
        runAll(random);

        System.out.println("\n=== Descending Array ===");
        runAll(descending);

        System.out.println("\n=== Almost Sorted Array ===");
        runAll(Sorted);
    }

    public static void runAll(int[] arr) {
        int[] a1 = new int[arr.length];
        int[] a2 = new int[arr.length];
        int[] a3 = new int[arr.length];
//making three separate copies of the same array before sorting.
        for (int i = 0; i < arr.length; i++) {
            a1[i] = arr[i];
            a2[i] = arr[i];
            a3[i] = arr[i];
        }

        bubbleSort(a1);
        selectionSort(a2);
        insertionSort(a3);
    }
}
