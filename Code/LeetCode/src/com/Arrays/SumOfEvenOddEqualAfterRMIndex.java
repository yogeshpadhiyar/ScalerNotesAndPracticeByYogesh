package com.Arrays;

import java.util.ArrayList;
import java.util.Scanner;

public class SumOfEvenOddEqualAfterRMIndex {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int a = sc.nextInt();
        ArrayList<Integer> A = new ArrayList<>();
        for (int i = 0; i < a; i++) {
            A.add(sc.nextInt());
        }

        System.out.println(solve(A));
    }
    public static int solve(ArrayList<Integer> A){
        ArrayList<Integer> sumOdd = new ArrayList<>();
        ArrayList<Integer> sumEven = new ArrayList<>();

        for (int i = 0; i < A.size(); i++) {
            if(i==0){
                sumEven.add(i,A.get(i));
                sumOdd.add(i, 0);
            }
            else if(i%2==0){
                sumEven.add(i,sumEven.get(i-1)+A.get(i));
                sumOdd.add(i, sumOdd.get(i - 1));
            }else{
                sumOdd.add(i,sumOdd.get(i-1)+A.get(i));
                sumEven.add(i, sumEven.get(i - 1));
            }
        }
        System.out.println(sumEven);
        System.out.println(sumOdd);
        int n = A.size();
        int count=0;
        for (int i = 0; i < n; i++) {
            int se = 0,so=0;
            if(i==0){
                se = sumOdd.get(n-1);
                so = sumEven.get(n-1)-sumEven.get(i);
            }else {
                se = sumEven.get(i - 1) + sumOdd.get(n - 1) - sumOdd.get(i);
                so = sumOdd.get(i - 1) + sumEven.get(n - 1) - sumEven.get(i);
            }
            if(se==so)
                count++;
        }
        return count;
    }
}
