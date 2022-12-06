package com.Arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GoodPair {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        /*int B = sc.nextInt();
        int N = sc.nextInt();
        ArrayList<Integer> A = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            A.add( sc.nextInt());
        }*/
        int N = sc.nextInt();
        int nums[] = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i]= sc.nextInt();
        }

//        isGoodPair(A,B);
        System.out.println(goodPairCount(nums));
    }

    public static int isGoodPair(ArrayList<Integer> A, int B){
        for (int i = 0; i < A.size(); i++) {
            for (int j = i+1; j < A.size(); j++) {
                if(A.get(i)+A.get(j)==B)
                    return 1;
            }
        }
        return 0;
    }

    public static int goodPairCount(int[] nums){
        HashMap<Integer,Integer> valCount = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(!valCount.containsKey(nums[i])){
                valCount.put(nums[i],1);
            }else{
                valCount.replace(nums[i], valCount.get(nums[i])+1);
            }
        }
        int sum=0;
        for (int c : valCount.values()) {
            if (c > 1) {
                sum += (c * (c-1))/2;
            }
        }
        return sum;
    }
}
