package Lab2_Array;

public class Array_mergeSorted {

        public static void main(String[] args) {
            int[] arr1 = {5, 2, 9};
            int[] arr2 = {1, 4, 7};

            int[] merged = mergeArrays(arr1, arr2);
            System.out.print("[ ");
            for(int e:merged){
                System.out.print(e+", ");
            }
            System.out.print("]");
        }

        public static int[] mergeArrays(int[] arr1, int[] arr2) {
            int n1 = arr1.length;
            int n2 = arr2.length;
            int[] merged = new int[n1 + n2];
            int size = 0;

            for (int num : arr1) {
                int j = size - 1;
                while (j >= 0 && merged[j] > num) {
                    merged[j + 1] = merged[j]; //shifts the element at position j one step to the right.
                    j--;  //moves left to check the next element (the one before the one we just moved).
                }/*
             This loop continues shifting larger elements to the right until it finds the right position for num.
                 */
                merged[j + 1] = num;
                size++;
            }


            for (int num : arr2) {
                int j = size - 1;
                while (j >= 0 && merged[j] > num) {
                    merged[j + 1] = merged[j];
                    j--;
                }
                merged[j + 1] = num;
                size++;
            }

            return merged;
        }
}

