package Practice;

import java.util.Scanner;

class array{
    int [] array;
    public void input(){
        System.out.println("Input the number of elements to store in the array: ");
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        array = new int[size];
        System.out.println("Input " + size + " elements in the array: ");
        for(int i = 0; i<size; i++){
            System.out.print("element- "+ i + ": ");
            array[i] = sc.nextInt();
        }
    }

    public void displayInReverse(){
        for(int i =array.length-1; i>=0 ; i--){
            System.out.print(array[i] + "  ");
        }
    }

}

public class array2 {
    public static void main(String[] args) {
        array arr1 = new array();
        arr1.input();
        arr1.displayInReverse();
    }

}
