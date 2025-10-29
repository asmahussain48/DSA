package Lab2_Array;

public class Array_MissingValues {
    public static void main(String[] args) {
        int arr1[] = {1, 1};
        int arr2[] = {2, 3, 4, 1, 6, 7, 1, 8, 3, 6};

        System.out.println("Missing values in Array1:");
        findMissing(arr1);

        System.out.println("\nMissing values in Array2:");
        findMissing(arr2);
    }

    public static void findMissing(int arr[]) {
        int n = arr.length;
        boolean visited[] = new boolean[n + 1]; // track numbers from 1 to n

        // Mark existing numbers as visited True
        for (int i = 0; i < n; i++) {
            int value = arr[i];
            if (value >= 1 && value <= n) {
                visited[value] = true;
            }
        }

        // Print missing numbers false ones
        boolean anyMissing = false;
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                System.out.print(i + " ");
                anyMissing = true;
            }
        }

        if (!anyMissing) {
            System.out.print("No missing values");
        }
    }
}
