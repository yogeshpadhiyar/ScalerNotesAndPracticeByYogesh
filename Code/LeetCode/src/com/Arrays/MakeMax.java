package com.Arrays;

import java.util.ArrayList;
import java.util.Scanner;

public class MakeMax {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int B = sc.nextInt();
        int N = sc.nextInt();
        ArrayList<Integer> A = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            A.add( sc.nextInt());
        }

        solve(A,B);
    }

    public static int solve(ArrayList<Integer> A, int B){
        int count=0;
        if(!A.contains(B))
            return -1;
        for (int i = 0; i < A.size(); i++) {
            if(A.get(i)>B){
                count++;
            }
        }
        return count;
    }
}
