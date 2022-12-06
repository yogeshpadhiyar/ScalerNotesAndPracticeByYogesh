package com.Arrays;

import java.util.ArrayList;
import java.util.Scanner;

public class MaxSubArrayWithKLength {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> A = new ArrayList<>();
        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            A.add(sc.nextInt());
        }
        int k = sc.nextInt();
        System.out.println(solve(A, k));
    }

    private static int solve(ArrayList<Integer> A, Integer w) {
        int sum = Integer.MIN_VALUE;

        for (int i = 0; i < A.size() - w; i++) {
            sum +=A.get(i);
        }
        int max_sum = sum;
        for (int i = 1; i < A.size() - w; i++) {
            sum += A.get(i+w-1)-A.get(i-1);
            max_sum = Math.max(max_sum, sum);
        }
        return max_sum;
    }
}
