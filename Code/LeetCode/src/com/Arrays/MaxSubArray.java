package com.Arrays;

import java.util.ArrayList;
import java.util.Scanner;

public class MaxSubArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> A = new ArrayList<>();
        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            A.add(sc.nextInt());
        }
        int B= sc.nextInt();
//        System.out.println(solve(A));
//        System.out.println(maxSubArray(A.size(),B,A));
        System.out.println(goodSubArray(A,B));
    }

    private static int solve(ArrayList<Integer> A) {
        /*int max_sum =A.get(0);
        for (int i = 0; i < A.size(); i++) {
            int sum=A.get(i);
            max_sum = Math.max(max_sum, sum);
            for (int j = i+1; j < A.size(); j++) {
                sum += A.get(j);
                max_sum = Math.max(max_sum, sum);
            }
        }
        return max_sum;*/

        //Learn Kadane algo also
        int max_sum = A.get(0);
        int curr_sum = A.get(0);
        for (int i = 1; i <A.size(); i++) {
            curr_sum = Math.max(A.get(i) , curr_sum+A.get(i));
            max_sum = Math.max( max_sum, curr_sum);
        }
        return max_sum;
    }

    private static int maxSubArray(int A, int B, ArrayList<Integer> C){
        int ms=0;
        for(int i=0; i<C.size(); i++){
            int s=0;
            for(int j=i; j<C.size(); j++){
                s += C.get(j);
                if(s<=B && ms<s){
                    ms = s;
                }
            }
        }
        return ms;
    }

    private static int goodSubArray(ArrayList<Integer> A, int B){
        int count=0;
        for(int i=0; i<A.size(); i++){
            int sum=0;
            for(int j=i;j<A.size();j++){
                sum += A.get(j);
                if(j%2==0 && sum>B){
                    count++;
                }else if(j%2!=0 && sum<B){
                    count++;
                }
            }
        }
        return count;
    }
}
