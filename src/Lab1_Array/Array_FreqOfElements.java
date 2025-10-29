package Lab1_Array;

public class Array_FreqOfElements {
    public static void main(String[] args) {
        int arr[] = {21, 9, 17, 9, 21, 9};
        System.out.println("Array values are:");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "  ");
        }
        System.out.println();

        boolean visited[] = new boolean[arr.length];

        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) {
                continue;
            }

            int count = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    visited[j] = true;
                    count++;
                }
            }
            System.out.println(arr[i] + " occurs " + count + " times");
        }
    }
}
