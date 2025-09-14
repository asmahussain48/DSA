package Lab1_Array;

public class Array_duplicatesCount {
        public static void main(String [] args){
            int arr[] = {21, 9, 17, 43, 9, 15, 21, 6, 38, 6};
            int dup = 0;
            for(int i = 0; i<arr.length; i++){
                for(int j = i+1; j<arr.length; j++){
                    if(arr[i] == arr[j]){
                        dup++;
                        break;
                        //statement exits the inner loop as soon as the first duplicate is found
                    }
                }
            }
            System.out.println("The duplicates are : "+dup);
        }
    }