package com.Arrays;

import java.util.ArrayList;
import java.util.Scanner;

public class EvenOddMaxDiff {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        ArrayList<Integer> A = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            A.add( sc.nextInt());
        }

        System.out.println(solve(A));
    }
    public static int solve(ArrayList<Integer> A){
        int evenMax=Integer.MIN_VALUE, oddMax= Integer.MAX_VALUE;

        for (int i = 0; i < A.size(); i++) {
            if(A.get(i)!=0) {
                if (A.get(i) % 2 == 0 && A.get(i) > evenMax) {
                    evenMax = A.get(i);
                } else if (A.get(i) < oddMax) {
                    oddMax = A.get(i);
                }
            }
        }
        return evenMax-oddMax;
    }
}
