package com.Arrays;

import java.util.ArrayList;
import java.util.Scanner;

public class PickElmFromLorRAndFindMaxSum {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int a = sc.nextInt();
        ArrayList<Integer> A = new ArrayList<>();
        for (int i = 0; i < a; i++) {
            A.add(sc.nextInt());
        }
        int B = sc.nextInt();
        System.out.println(solve(A,B));
    }

    public static int solve(ArrayList<Integer> A, int B){
        ArrayList<Integer> pf = new ArrayList<>();
        int maximum = A.get(0);
        pf.add(0,A.get(0));
        for (int i = 1; i < A.size(); i++) {
            pf.add(i,A.get(i)+pf.get(i-1));
            maximum = (maximum < A.get(i))?  A.get(i): maximum;
        }
        System.out.println(pf);
        int i = B-1, j=0, n=A.size();
        int max=Integer.MIN_VALUE;
        if(B==1){
            return maximum;
        }else if(B==n){
            return pf.get(n-1);
        }
        while (j<=B){
            if(j==0){
                if(max < pf.get(i)){
                    max = pf.get(i);
                }
            }else if(i<0){
                if(j==n && max < pf.get(n-1)){
                    max = pf.get(n-1);
                }
                if(max < (pf.get(n-1)-pf.get(n-1-j))){
                    max = pf.get(n-1)-pf.get(n-1-j);
                }
            }else{
                if(max< (pf.get(i)+pf.get(n-1)-pf.get(n-1-j))){
                    max = pf.get(i)+pf.get(n-1)-pf.get(n-1-j);
                }
            }
            i--;
            j++;
        }
        return max;
    }

}
