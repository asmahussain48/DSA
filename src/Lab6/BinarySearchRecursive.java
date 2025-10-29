package Lab6;
class BinarySearchRecursive {

    static int binarySearch(int[] arr, int low, int high, int key) {
        if (low > high) return -1;
        int mid = (low + high) / 2;
        if (arr[mid] == key)
            return mid;
        else if (arr[mid] > key)
            return binarySearch(arr, low, mid - 1, key);
        else
            return binarySearch(arr, mid + 1, high, key);
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 8, 10, 12, 14, 16};

//        int key = 10;
        int key = 10;
        int result = binarySearch(arr, 0, arr.length - 1, key);

        if (result == -1)
            System.out.println("Element " + key + " not found.");
        else
            System.out.println("Element " + key + " found at index " + result);

        key = 5;
        result = binarySearch(arr, 0, arr.length - 1, key);
        if (result == -1)
            System.out.println("Element " + key + " not found.");
        else
            System.out.println("Element " + key + " found at index " + result);
    }
}
