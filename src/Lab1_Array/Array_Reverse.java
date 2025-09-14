package Lab1_Array;
import java.util.Scanner;
public class Array_Reverse {

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.println();System.out.println();
            System.out.print("Enter the Size of Array: ");
            int size = sc.nextInt();
            int Arr[] = new int[size];

            for(int i = 0; i<Arr.length; i++){
                System.err.print("Enter the value of " + (i+1) + ": ");
                Arr[i] = sc.nextInt();
            }
            System.out.println();

            System.out.println("Orgignal Array: ");
            for(int i = 0; i<Arr.length; i++){
                System.out.print(Arr[i] + "       ");
            }

            int ReveArr[] = new int[size];
            for(int i =0; i<Arr.length; i++ ){
                ReveArr[i] = Arr[Arr.length-1-i];
            }
            System.out.println();

            System.out.println("Reversed Array: ");
            for(int i = 0; i<Arr.length; i++){
                System.out.print(ReveArr[i] + "       ");
            }
        }

    }
