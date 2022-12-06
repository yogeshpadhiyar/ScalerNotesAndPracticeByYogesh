package com.Arrays;

import java.util.Scanner;

public class CreateArrayGivenOrder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int nums[] = new int[N];
        int[] index = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i]= sc.nextInt();
        }
        for (int i = 0; i < N; i++) {
            index[i]= sc.nextInt();
        }
        System.out.println(solve(nums, index));
    }

    private static int[] solve(int[] nums, int[] index) {
        java.util.SortedMap<Integer,Integer> valCount = new java.util.TreeMap<>();
        java.util.SortedMap<Integer,Integer> valSum = new java.util.TreeMap<>();

        for (int num : index) {
            if (!valCount.containsKey(num)) {
                valCount.put(num, 1);
            } else {
                valCount.replace(num, valCount.get(num) + 1);
            }
        }

        int sum=0;
        for (Integer k : valCount.keySet()) {
            valSum.put(k,sum+=valCount.get(k));
        }
        int r[] = new int[nums.length];

        for (int i = 0; i < r.length; i++) {
            r[valSum.get(index[i])-1] = nums[i];
            valSum.replace(index[i], valSum.get(index[i])-1);
        }
        return r;

    }
}
