package com.Arrays;

import java.util.ArrayList;
import java.util.Scanner;

public class EquilibriumIndex {
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
        int total =0;
        for (int i = 0; i < A.size(); i++) {
            total += A.get(i);
        }
        int leftSum=0,rightSum=0;
        for (int i = 0; i < A.size(); i++) {
            if(i==0){
                leftSum=0;
                rightSum=total-A.get(i);
            }else {
                leftSum += A.get(i - 1);
                rightSum -= A.get(i);
            }
            if (leftSum == rightSum) {
                return i;
            }
        }
        return -1;
    }
}
