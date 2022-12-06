package com.Arrays;

import java.util.ArrayList;
import java.util.Scanner;

public class RangeOfSum {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int a = sc.nextInt();
        ArrayList<Integer> A = new ArrayList<>();
        for (int i = 0; i < a; i++) {
            A.add(sc.nextInt());
        }
        ArrayList<ArrayList<Integer>> B = new ArrayList<>();
        int b = sc.nextInt();
        for (int i = 0; i < b; i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                temp.add(sc.nextInt());
            }
            B.add(temp);
        }

        System.out.println(solve(A,B));
    }

    private static ArrayList<Long> solve(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
        ArrayList<Long> pf = new ArrayList<>();
        ArrayList<Long> result = new ArrayList<>();
        pf.add(0, 0L);
        for (int i = 0; i < A.size(); i++) {
            pf.add(A.get(i)+pf.get(i));
        }

        for (ArrayList<Integer> b : B) {
            result.add( pf.get(b.get(1)) - pf.get(b.get(0)-1) );
        }
        return result;
    }
}
