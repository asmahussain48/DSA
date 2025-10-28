package Lab2_Array;

import java.util.Scanner;
class BinaryToInteger {
    public static void main(String[] args) {
        // Example binary array (8 bits)
        int[] binary = {1, 0, 1, 1, 0, 0, 1, 1};

        int num = 0;
        int power = 128; // start with 2^7 = 128 for 8-bit binary

        for (int i = 0; i < 8; i++) {
            if (binary[i] == 1) {
                num += power;
            }
            power = power / 2; // move to next lower bit
        }

        System.out.println("Decimal: " + num);
    }
}
public class Arrays_BinaryToIneger {
    public static void main(String[] args) {
        System.out.print("Enter a non-negatve Integer");
        Scanner sc=new Scanner(System.in);
        int num = sc.nextInt();
        int binary[] = new int[8];
        int power = 128;

        if (num < 0 || num > 255) {
            System.out.println("Number out of range!");
            return;
        }

        for(int i = 0; i<8 ; i++){
            if(num<=0){
                break;
            }
                if ( num >= power ) {
                    binary[i]=1;
                    num-=power;
                } else {
                    binary[i]=0;
                }
                power=power / 2; //128 to 64 , 64 to 32 and so on


        }
        System.out.print("Binary: ");
        for(int e:binary){
            System.out.print(e+"  ");
        }
    }

}
