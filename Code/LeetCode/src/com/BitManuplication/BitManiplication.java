package com.BitManuplication;

import java.util.ArrayList;
import java.util.Scanner;

public class BitManiplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> A = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            A.add(sc.nextInt());
        }

        System.out.println(solve(A));
    }

    private static int solve(ArrayList<Integer> A) {
        int sum =0;
        for (int i = 0; i < A.size(); i++) {
            if(A.get(i)==1){
                sum += (i *(i+1))/2;
            }
        }
        return sum;
    }
}
