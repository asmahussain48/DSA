package Lab2_Array;

public class Array_targetIndex {
    public static void main(String[] args) {
        int Array[] = {1,2,4,5,6,7,9,11,15,19,20};
        int target = 9;
        //Binary Search on a sorted array.
        int n = Array.length;
        int left = 0;
        int right = n-1;
        int indexTarget = -1;
        while (left<=right){
            int mid = left + (right - left)/2;
            if(Array[mid] == target){
            indexTarget = mid;
            }
            if(Array[mid]<target){
                left = mid +1;
            }else{
                right = mid-1;
            }
        }
        System.out.println(indexTarget);
    }
}
