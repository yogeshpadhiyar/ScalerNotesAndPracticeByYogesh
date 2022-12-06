package com.Arrays;

import java.util.Scanner;

public class SuffalArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int nums[] = new int[2*n];
        for (int i = 0; i < 2*n; i++) {
            nums[i]= sc.nextInt();
        }
        System.out.println(solve(nums,n));
    }

    private static int[] solve(int[] nums, int n) {
        int[] r = new int[2*n];
        int k=0;
        for(int i=0,j=n; i<n; i++,j++){
            r[k++]=nums[i];
            r[k++]=nums[j];
        }
        return r;
    }
}
