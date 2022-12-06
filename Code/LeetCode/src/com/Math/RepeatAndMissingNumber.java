package com.Math;

import java.util.ArrayList;
import java.util.Scanner;

public class RepeatAndMissingNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> A = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            A.add(sc.nextInt() );
        }
        System.out.println(repeatAndMissingNumber(A));
    }

    private static ArrayList<Integer> repeatAndMissingNumber(final ArrayList<Integer> A) {
        ArrayList<Integer> result = new ArrayList<>();
        int repeat = findDuplicate(A);
        result.add(repeat);
        int allXor=0,arrXor=0;
        for (int i = 0,j=1; i < A.size(); i++,j++) {
            arrXor ^=A.get(i);
            allXor ^= j;
        }
        result.add(arrXor^allXor);
        return result;
    }

    private static int findDuplicate(ArrayList<Integer> A){
        int slow=A.get(0);
        int fast=A.get(A.get(0)-1);
        while (slow!=fast){
            slow = A.get(slow);
            fast = A.get(A.get(fast)-1);
        }
        fast=0;
        while (slow!=fast){
            slow = A.get(slow);
            fast = A.get(fast);
        }
        return slow;
    }

    private static int findDuplicate(int[] nums){
        int slow=nums[0];
        int fast=nums[nums[0]];
        while (slow!=fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast=0;
        while (slow!=fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
