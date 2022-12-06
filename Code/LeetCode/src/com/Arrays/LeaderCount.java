package com.Arrays;

import java.util.ArrayList;
import java.util.Scanner;

public class LeaderCount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> A = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            A.add(sc.nextInt());
        }
        System.out.println(solve(A));
    }

    private static ArrayList<Integer> solve(ArrayList<Integer> A) {
        int max = A.get(A.size()-1);
        ArrayList<Integer> r = new ArrayList<>();
        r.add(A.get(A.size()-1));
        for (int i = A.size()-2; i >=0; i--) {
            if(A.get(i)>max){
                max = A.get(i);
                r.add(A.get(i));
            }
        }
        return r;
    }
}
