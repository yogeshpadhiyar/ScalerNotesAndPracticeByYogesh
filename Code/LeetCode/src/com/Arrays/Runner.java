package com.Arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<List<String>> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<String> temp = new ArrayList<>();
            temp.add(sc.next());
            temp.add(sc.next());
            temp.add(sc.next());
            items.add(temp);
        }
        String rk = sc.nextLine();
        String rv = sc.nextLine();
        System.out.println(solve(items, rk,rv));
    }

    private static int solve(List<List<String>> items, String ruleKey, String ruleValue) {
        int index = -1;
        if(Objects.equals(ruleKey, "type"))
            index=0;
        else if(Objects.equals(ruleKey, "color"))
            index=1;
        else
            index=2;

        int c=0;
        for(int i=0; i<items.size(); i++){
            if(Objects.equals(items.get(i).get(index), ruleValue)){
                c++;
            }
        }
        return c;
    }

    private static void rightToLeftDiagonal(int[][]A){
        int n=A.length,m=A[0].length;
        for (int k = 0; k < n; k++) {
            int i=0;
            int j=k;
            while(j>=0 && i<n){
                System.out.print(A[j][k]);
                i++;
                j--;
            }
            System.out.println();
        }
        for (int k = 1; k <n ; k++) {
            int i=k;
            int j=m-1;
            while(j>=0 && i<n){
                System.out.print(A[j][k]);
                i++;
                j--;
            }
            System.out.println();
        }
    }

    private static int[][] transpose(int[][]A){
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                swap(i,j,A);
            }
        }
        return A;
    }

    private static void swap(int i, int j, int[][]A) {
        int temp = A[i][j];
        A[i][j] = A[j][i];
        A[j][i] = temp;
    }

    private static int[][] rotateTo90Degree(int[][]A){
        transpose(A);
        for (int i = 0; i < A.length; i++) {
            reverseArray(A[0],0,A[0].length-1);
        }
        return A;
    }

    public static void reverseArray(int [] arr, int start, int end){
        int i=start,j= end;
        while (i<j){
            int temp= arr[j];
            arr[j]=arr[i];
            arr[i]=temp;
            i++;
            j--;
        }
    }

    public void rotate(int[] nums, int k) {
        int l = nums.length;
        k = k % l;
        if(k!=l && k!=0) {
            reverseArray(nums,0,l-1);
            reverseArray(nums,0,k);
            reverseArray(nums,k+1,l-1);
        }
    }
}
