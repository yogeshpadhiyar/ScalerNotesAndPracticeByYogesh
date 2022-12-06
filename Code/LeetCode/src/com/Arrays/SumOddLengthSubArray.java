package com.Arrays;

import java.util.ArrayList;
import java.util.Scanner;

public class SumOddLengthSubArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int nums[] = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i]= sc.nextInt();
        }
        System.out.println(solve(nums));
    }

    private static int solve(int[] arr) {
        int[] pf = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            pf[i] = arr[i]+ ((i==0)?0:pf[i-1]);
        }

        int sum=0;
        for (int i = 2; i < pf.length; i+=2) {
            for (int j = i; j < pf.length; j++) {
                sum += pf[j] - (j-(i+1)<0?0:pf[j-(i+1)]);
            }
        }
        return sum+pf[arr.length-1];
    }
}
