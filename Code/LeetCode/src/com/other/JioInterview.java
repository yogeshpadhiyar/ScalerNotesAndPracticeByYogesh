package com.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class JioInterview {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();
        /*ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(sc.nextInt());
        }*/
        JioInterview j  = new JioInterview();
        System.out.println(j.solution(A,B,C));
    }


    public String solution(int A, int B, int C) {
        // write your code in Java SE 8
        StringBuffer sb = new StringBuffer();
        int[] arr = new int[3];
        arr[0]=A;
        arr[1]=B;
        arr[2]=C;
        int total = A+B+C;
        int currUsed, lastUsed=-1;

        if(A>B && A>C){
            currUsed=0;
        }else if(B>C)   currUsed=1;
        else currUsed=2;

        while(total>0 ){
            total -= helper(total, sb, currUsed, arr);
            lastUsed=currUsed;
            currUsed = findMax(arr,  lastUsed);
            int c=0;
            for (int j : arr) {
                if (j == 0) c++;
            }
            if(c==2)    break;
        }
        for(int i=0; i<arr.length; i++){
            if(arr[i]!=0){
                helper(total, sb, i, arr);
            }
        }
        return sb.toString();
    }

    public int findMax(int[] arr,  int lastUsed){
        int tempMax=Integer.MIN_VALUE;
        int ans=0;
        for(int i=0; i<arr.length; i++){
            if(i!=lastUsed){
                if(tempMax<arr[i]){
                    ans=i;
                    tempMax=arr[i];
                }
            }
        }
        return ans;
    }

    public int helper(int total, StringBuffer sb, int currUsed, int[] arr){
        if(currUsed==0){
            if(arr[currUsed]>=2){
                sb.append("aa");
                arr[currUsed] -=2;
                total=2;
            }else if(arr[currUsed]==1){
                sb.append("a");
                arr[currUsed]=0;
                total=1;
            }
        }else if(currUsed==1){
            if(arr[currUsed]>=2){
                sb.append("bb");
                arr[currUsed] -=2;
                total=2;
            }else if(arr[currUsed]==1){
                sb.append("b");
                arr[currUsed]=0;
                total=1;
            }
        }else{
            if(arr[currUsed]>=2){
                sb.append("cc");
                arr[currUsed] -=2;
                total=2;
            }else if(arr[currUsed]==1){
                sb.append("c");
                arr[currUsed]=0;
                total=1;
            }
        }
        return total;
    }

    private static  int maxWaterContain(int[] height){
        int maxWater = 0;
        int p1=0, p2= height.length-1;
        while(p1<p2){
            int distance = p2-p1;
            int minHeight = Math.min(height[p1],height[p2]);
            maxWater = Math.max(maxWater, distance*minHeight);
            if(height[p1]>=height[p2]){
                p2--;
            }else{
                p1++;
            }
        }
        return maxWater;
    }

    private static int kanandseAlgo(ArrayList<Integer> A){
        int max=A.get(0);
        int currSum=A.get(0);
        for (int i = 1; i < A.size(); i++) {
            currSum = Math.max(A.get(i), currSum+A.get(i));
            max = Math.max(max, currSum);
        }
        return max;
    }

    private static boolean ableToReachAtLast(ArrayList<Integer> A ){
        return false;
    }

}
