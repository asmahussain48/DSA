package Lab1_Array;

public class Array_ColumnMagic {
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

        int Column[] = new int[Array1.length];
        for(int i = 0 ; i< Array1[1].length; i++){ // changes column length
            int Sum = 0;
            for(int j = 0; j<Array1.length; j++){ // changes row length
                Sum += Array1[j][i]; //changes i to j and j to i 
            }
            Column[i] = Sum;
        }
        boolean check = true;
        for(int i = 1; i<Column.length; i++){
            if(Column[i] != Column[0]){
                check = false;
                break;
            }
        }

        if(check){
            System.out.println("The array is Column-Magic");
        }
        else {
            System.out.println("The array is not Column-Magic");
        }
    }
}
