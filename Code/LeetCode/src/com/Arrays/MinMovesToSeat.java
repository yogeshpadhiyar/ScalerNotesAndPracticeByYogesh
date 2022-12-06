package com.Arrays;

import java.util.Arrays;
import java.util.Scanner;

public class MinMovesToSeat {
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
        System.out.println(solve(nums,index));
    }

    private static int solve(int[] seats, int[] students) {
        Arrays.sort(seats);
        Arrays.sort(students);
        int sum=0;
        for (int i = 0; i < seats.length; i++) {
            sum += Math.abs(students[i]-seats[i]);
        }
        return sum;
    }
}
