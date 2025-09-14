package Lab1_Array;

public class Array_FlipedRow {
    public static void main(String[] args) {
//            int Array1[][] = {
//                    {4, 7, 2, 8, 9, 5},
//                    {2, 6, 5, 3, 8, 1},
//                    {9, 3, 1, 7, 2, 6},
//                    {8, 7, 1, 5, 6, 4}
//            };
            int Array1[][]={
                    {3,4,2},
                    {4,5,5},
                    {12,9,5}
            };
            System.out.println("Original array values are:");
            for (int i = 0; i < Array1.length; i++) {
                for (int j = 0; j < Array1[i].length; j++) {
                    System.out.print("  "+Array1[i][j] + "  ");
                }
                System.out.println();
            }
            System.out.println();

            for(int i = 0; i<Array1[0].length; i++){
                for(int j = 0; j<Array1.length-1; j=j+2){
                    if(Array1[i].length % 2 == 0) {
                        int temp = Array1[j][i];
                        Array1[j][i]=Array1[j+1][i];
                        Array1[j+1][i]=temp;
                    }
                    else{
                        int temp=Array1[j][i];
                        Array1[j][i]=Array1[j+2][i];
                        Array1[j+2][i]=temp;
                    }
                }
            }
        System.out.println("Vertically flipped array is");
        for (int i = 0; i < Array1.length; i++) {
            for (int j = 0; j < Array1[i].length; j++) {
                System.out.print("  "+Array1[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();

        }
}
