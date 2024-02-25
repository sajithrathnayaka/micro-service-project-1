package com.example.backendengineerdata;

import java.util.Scanner;

class Example {
    public static int getAbs() {
        Scanner input = new Scanner(System.in);

        System.out.print("Input a num: ");
        int num = input.nextInt();


        return 1;
    }

    public static void main(String args[]){
        int abs = getAbs();
        System.out.println("Absolute value: " + abs);
    }



}

