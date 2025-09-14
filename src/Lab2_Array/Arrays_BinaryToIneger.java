package Lab2_Array;

import java.util.Scanner;

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
            if(num >= power){
                binary[i] = 1;
                num -= power;
            }
            else {
                binary[i] = 0;
            }
            power = power/2; //128 to 64 , 64 to 32 and so on


        }
        System.out.print("Binary: ");
        for(int e:binary){
            System.out.print(e+"  ");
        }
    }

}
