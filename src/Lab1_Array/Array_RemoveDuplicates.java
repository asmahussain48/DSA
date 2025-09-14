package Lab1_Array;

public class Array_RemoveDuplicates {
        public static void main(String [] args){
            int arr[] = {21, 9, 17, 43, 9, 15, 21, 6, 38, 6};

            System.out.println("Original array is: ");
            for(int i = 0; i<arr.length; i++){
                System.out.print(arr[i]);
                if(i!=(arr.length-1)){
                    System.out.print(", ");
                }
            }
            System.out.println();
            int element = 0;
            int Arr[] = new int[arr.length];
            for(int i = 0; i<arr.length; i++){
                boolean count = false;
                for(int j = 0; j<i-1; j++){
                    if(arr[i] == arr[j]){
                        count = true;
                        break;
                    }
                }
                if(!count) {
                    Arr[element]=arr[i];
                    element++;
                }

            }
            System.out.println();
            System.out.println("Array after removing duplicates is: ");
            for(int i = 0; i<element; i++){
                System.out.print(Arr[i]);
                if(i!=(element-1)) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
