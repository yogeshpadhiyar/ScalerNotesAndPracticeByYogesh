package com.Arrays;

import java.util.ArrayList;
import java.util.Scanner;

public class AllSubArraySum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> A = new ArrayList<>();
        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            A.add(sc.nextInt());
        }
//        int B = sc.nextInt();
//        System.out.println(solve(A));
        System.out.println(subArrayWithBitWiseOR(N,A));
    }

    //Contribution Technic
    private static Long solve(ArrayList<Integer> A) {
        int n = A.size();
        Long max_sum =0L;
        for(int i=0;i<n;i++){
            Long a = (long) (i + 1) * (n - i);
            Long c = a * A.get(i);
            max_sum += c;
        }
        return max_sum;
    }

    private static int leastAverageSubArrayWithBLength(ArrayList<Integer> A, int B){
        int sum = 0;
        for (int i = 0; i < B; i++) {
            sum += A.get(i);
        }
        int least =sum;
        int least_index=0;
        for (int i = 1; i < A.size()-B; i++) {
            sum = sum +A.get(i+B-1) - A.get(i-1);
            if(least> sum){
                least = sum;
                least_index = i;
            }
        }
        return least_index;
    }

    private static Long subArrayWithBitWiseOR(int A, ArrayList<Integer> B){
        long total = (long) A * (A + 1) / 2;
        int zeroCount=0;
        for (int i = 0; i < A; i++) {
            if(B.get(i)==0) zeroCount++;
            if(B.get(i)==1 || i==A-1){
                total -= (long) zeroCount *(zeroCount+1)/2;
                zeroCount=0;
            }
        }
        return total;
    }
}
