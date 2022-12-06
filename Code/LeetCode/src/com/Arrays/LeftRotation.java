package com.Arrays;

import java.util.ArrayList;
import java.util.Scanner;

public class LeftRotation {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int a = sc.nextInt();
        ArrayList<Integer> A = new ArrayList<>();
        for (int i = 0; i < a; i++) {
            A.add(sc.nextInt());
        }

        ArrayList<Integer> B = new ArrayList<>();
        int b = sc.nextInt();
        for (int i = 0; i < b; i++) {
            B.add(sc.nextInt());
        }

        System.out.println(solve(A,B));
    }

    public static ArrayList<ArrayList<Integer>> solve(ArrayList<Integer> A, ArrayList<Integer> B){
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int i = 0; i < B.size(); i++) {
            ArrayList<Integer> temp = new ArrayList<>(A);
            int b = B.get(i);
            int n = temp.size();
            reverseArrayList(temp,0,n-1);
            reverseArrayList(temp,0,n-b-1);
            reverseArrayList(temp,n-b, n-1);
            result.add(temp);
        }
        return result;
    }

    public static void reverseArrayList(ArrayList<Integer> list, int s, int e){
        while (s<e){
            int temp= list.get(e);
            list.set(e,list.get(s));
            list.set(s,temp);
            s++;
            e--;
        }
    }
}
