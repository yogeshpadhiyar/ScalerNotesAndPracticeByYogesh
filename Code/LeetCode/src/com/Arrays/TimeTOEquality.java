package com.Arrays;

import java.util.ArrayList;
import java.util.Scanner;

public class TimeTOEquality {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int a = sc.nextInt();
        ArrayList<Integer> A = new ArrayList<>();
        for (int i = 0; i < a; i++) {
            A.add(sc.nextInt());
        }

        System.out.println(solve1(A));
    }

    private static int solve(ArrayList<Integer> A) {
        int sum=0, max=0;
        for (int i = 0; i < A.size(); i++) {
            sum += A.get(i);
            max = max<A.get(i)?A.get(i):max;
        }
        return max*A.size() - sum;
    }

    public static ArrayList<Integer> solve1(ArrayList<Integer> A){
        ArrayList<Integer> r = new ArrayList<>();
        int mul =1;
        for (int i = 0; i < A.size(); i++) {
            mul *= A.get(i);
        }
        for (int i = 0; i < A.size(); i++) {
            r.add(mul / A.get(i));
        }
        return r;
    }
}
