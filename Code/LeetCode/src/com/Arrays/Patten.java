package com.Arrays;

import java.util.Scanner;

public class Patten {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        hollowDiamond(N);
    }

    private static void hollowDiamond(int n) {
        for (int i = 0; i < n; i++) {
            innerPatten(i,n);
            System.out.println();
        }
        for (int i = n-1; i >= 0; i--) {
            innerPatten(i,n);
            System.out.println();
        }
    }
    private static void innerPatten(int i, int n){
        for (int j = 0; j < n-i; j++) {
            System.out.print("*");
        }
        for (int j = 0; j < 2*i; j++) {
            System.out.print(" ");
        }
        for (int j = 0; j < n-i; j++) {
            System.out.print("*");
        }
    }
}
