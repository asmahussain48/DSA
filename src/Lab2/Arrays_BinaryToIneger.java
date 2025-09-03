package Lab2;

import java.util.Scanner;

public class Arrays_BinaryToIneger {
    public static void main(String[] args) {
        System.out.println("Enter a non-negatve Integer");
        Scanner sc=new Scanner(System.in);
        int num = sc.nextInt();
        int binary[] = new int[8];
        int power = 1;
        if(num<255 && num>0){

            for(int i = 7; i>=0; i--){
                if(num>power){
                    binary[i] = 1;
                    num= num-power;
                    power *= 2;
                }
            }


        }
        for(int e:binary){
            System.out.print(e+"  ");
        }
    }

}
