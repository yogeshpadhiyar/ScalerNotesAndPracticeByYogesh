package com.Arrays;

import java.util.ArrayList;
import java.util.Scanner;

public class SmallSubArrayContainMinMax {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> A = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            A.add(sc.nextInt());
        }
        System.out.println(solve(A));
    }

    private static int solve(ArrayList<Integer> A) {
        int min=Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < A.size(); i++) {
            if(A.get(i)>max){
                max= A.get(i);
            }
            if(A.get(i)<min){
                min = A.get(i);
            }
        }
        int last_min_index=-1,last_max_index=-1,len=Integer.MAX_VALUE;
        if(max==min)
            return 1;
        for (int i = 0; i < A.size(); i++) {
            if(A.get(i)==max){
                last_max_index = i;
                if(last_min_index!=-1 && len>Math.abs(last_max_index-last_min_index)+1){
                    len = Math.abs(last_max_index-last_min_index)+1;
                }
            }else if(A.get(i)==min){
                last_min_index = i;
                if(last_max_index!=-1 && len>Math.abs(last_max_index-last_min_index)+1){
                    len = Math.abs(last_max_index-last_min_index)+1;
                }
            }
        }
        return len;
    }
}
