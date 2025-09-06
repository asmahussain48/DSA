package Practice;

import java.util.*;

class Array1{
    int array[];
    Array1(int size){
        array = new int[size];
    }

    public void input(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 10 elements in the array: ");
        for(int i = 0; i<array.length; i++){
            System.out.print("Element " + (i+1) + ": ");
            array[i] = sc.nextInt();
        }
    }

    public void display(){
        System.out.print("Elemtns in array are : ");
        for(int E:array){
            System.out.print(E+ "  ");
        }
        System.out.println();
    }

}

public class array_input {
    public static void main(String[] args) {
        Array1 arr1 = new Array1(10);
        arr1.input();
        arr1.display();
    }
}
