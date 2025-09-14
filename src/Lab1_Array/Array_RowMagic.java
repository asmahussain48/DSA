package Lab1_Array;

public class Array_RowMagic {
    public static void main(String[] args) {
        int Array1[][] = {{4, 7 ,2} ,{2 ,6 ,5} ,{9 ,3 ,1},{8 ,7 ,1}};

        System.out.println("Array values are:");
        for (int i = 0; i < Array1.length; i++) {
            for (int j = 0; j < Array1[i].length; j++) {
                System.out.print("  "+Array1[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();

        int Rows[] = new int[Array1.length];
        for(int i = 0 ; i< Array1.length; i++){
            int Sum = 0;
            for(int j = 0; j<Array1[i].length; j++){
                Sum += Array1[i][j];
            }
            Rows[i] = Sum;
        }
        boolean check = true;
        for(int i = 1; i<Rows.length; i++){
                if(Rows[i] != Rows[0]){
                    check = false;
                    break;
            }
        }

        if(check){
            System.out.println("The array is Row-Magic");
        }
        else {
            System.out.println("The array is not Row-Magic");
        }
    }
}
