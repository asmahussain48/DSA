package Lab1_Array;
import java.util.Scanner;

public class Array_Shift {

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.println();
            System.out.print("Enter the Size of Array 1: ");
            int size1 = sc.nextInt();
            int Arr1[] = new int[size1];

            for(int i = 0; i<Arr1.length; i++){
                System.err.print("Enter the value of "+ (i+1) + ": ");
                Arr1[i] = sc.nextInt();
            }
            System.out.println();


            System.err.print("Enter the Size of Array 2: ");
            int size2 = sc.nextInt();
            int Arr2[] = new int[size2];

            for(int i = 0; i<Arr2.length; i++){
                System.out.print("Enter the value of " + (i+1)+ ": ");
                Arr2[i] = sc.nextInt();
            }
            System.out.println();



            int Size3 = (size1+size2);
            int Arr3[] = new int[Size3];
            for(int i = 0; i<Size3; i++){
                if(i<size1){
                    Arr3[i] = Arr1[i];
                }
                if(i>(size1-1)){
                    Arr3[i] = Arr2[i-size1];
                }
            }
            System.out.println();
            System.out.println("The combined elements from array 1 and array 2 stored in array 3 are: ");
            for(int i=0; i<Size3; i++){
                System.out.print(Arr3[i]);

                if(!(i==(Size3-1))){
                    System.out.print(", ");
                }

            }

        }
    }