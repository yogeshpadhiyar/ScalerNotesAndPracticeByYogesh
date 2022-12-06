package com.Arrays;

import java.util.ArrayList;
import java.util.Scanner;

public class SwitchToggle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> A = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            A.add(sc.nextInt());
        }
        System.out.println(solve(A));
    }

    private static int solve(ArrayList<Integer> a) {
        int count=0;
        for (int i = 0; i < a.size(); i++) {
            if(count%2==0 && a.get(i)==0){
                count++;
            }else if(count%2==1 && a.get(i)==1){
                count++;
            }
        }
        return count;
    }
}
