package Practice;

public class QuickSort {

    public static void quickSort(int arr[], int low, int high) {
        if (low < high) {
            int pidx = partition(arr, low, high);  // get pivot index
            quickSort(arr, low, pidx - 1);         // left side
            quickSort(arr, pidx + 1, high);        // right side
        }
    }

    public static int partition(int arr[], int low, int high) {
        int pivot = arr[high]; // choose last element as pivot
        int i = low - 1;       // pointer for smaller elements

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // place pivot in correct position
        i++;
        int temp = arr[i];
        arr[i] = arr[high];
        arr[high] = temp;

        return i; // return pivot index
    }

    public static void main(String[] args) {
        int arr[] = {6, 3, 9, 5, 2, 8};
        quickSort(arr, 0, arr.length - 1);

        System.out.println("Sorted array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}

