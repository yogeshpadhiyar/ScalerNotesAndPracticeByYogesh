package com.Arrays;

import java.util.HashMap;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

public class SmallestCount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int nums[] = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i]= sc.nextInt();
        }
        System.out.println(solve(nums));
    }

    private static int[] solve(int[] nums) {
        java.util.SortedMap<Integer,Integer> valCount = new java.util.TreeMap<>();
        java.util.SortedMap<Integer,Integer> valSum = new java.util.TreeMap<>();
        int[] index = new int[nums.length];
        for (int num : nums) {
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
        for(int i=0;i<nums.length; i++){
            r[i]= valSum.get(nums[i])-valCount.get(nums[i]);
        }
        for (int i = 0; i < r.length; i++) {
            r[valSum.get(index[i])-1] = nums[i];
            valSum.replace(index[i], valSum.get(index[i])-1);
        }
        return r;

    }
}
