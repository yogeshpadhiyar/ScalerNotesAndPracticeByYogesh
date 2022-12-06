package com.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class AdvanceRecursionAll {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int N = sc.nextInt();
        System.out.println(towerOfHanoi(N));
        ArrayList<Integer> a = new ArrayList<>(Collections.nCopies(5,0));
        int m = Integer.MAX_VALUE;
    }

    public static ArrayList<ArrayList<Integer>> towerOfHanoi(int A){
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        TOH(A,1,2,3,ans);
        return ans;
    }

    public static void TOH(int N, int S, int H, int D, ArrayList<ArrayList<Integer>> ans){
        if(N==0) return;
        TOH(N-1,S,D,H,ans);
//        System.out.println(N+"th disc: "+S +" to "+D);
        ans.add(new ArrayList<>(Arrays.asList(N,S,D)));
        TOH(N-1,H,S,D, ans);
    }
}
