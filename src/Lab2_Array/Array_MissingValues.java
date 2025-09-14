package Lab2_Array;
import java.util.ArrayList;
import java.util.List;

public class Array_MissingValues {
    public static void main(String[] args) {
        int Array1[]={1, 1};
        int Array2[] = {2,3,4,1,6,7,1,8,3,6};
        System.out.println(MissingValue(Array1));
        System.out.println(MissingValue(Array2));
    }
    public static List<Integer> MissingValue(int[] Array){
            ArrayList<Integer> missingValues = new ArrayList<>();
            int n = Array.length;
            boolean vistied [] = new boolean[n+1];
            for(int i =0; i<n; i++){
                vistied[Array[i]] = true;
            }
            for(int i =1; i<= n; i++){
                if(!vistied[i]){
                    missingValues.add(i);
                }
            }
          return missingValues;
    }
}