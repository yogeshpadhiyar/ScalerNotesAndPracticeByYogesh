package com.Arrays;

import java.util.ArrayList;
import java.util.Scanner;

public class EvenSubArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> A = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            A.add(sc.nextInt());
        }
        System.out.println(solve(A));
    }

    private static String solve(ArrayList<Integer> A) {
        if(A.size()<2 || A.size()%2!=0 || A.get(0)%2!=0 || A.get(A.size()-1)%2!=0){
            return "NO";
        }
        for (int i = 1; i < A.size(); i+=2) {
            if(A.get(i) % 2 == 0){
                return "YES";
            }
        }
        return "NO";
    }

}
