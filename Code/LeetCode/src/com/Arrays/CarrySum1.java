package com.Arrays;

import java.util.Scanner;

public class CarrySum1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String A = sc.nextLine();
        int a=0,ans=0;
        for (int i = 0; i < A.length(); i++) {
            if(A.charAt(i)=='A'){
                a++;
            }else if(A.charAt(i)=='A'){
                ans += a;
            }
        }
        System.out.println(ans);
        /*int g=0,ans1=0;
        for (int i = A.length()-1; i >=0; i--) {
            if(A.charAt(i)=='g'){
                g++;
            }else if(A.charAt(i)=='g'){
                ans1 += g;
            }
        }
        System.out.println(ans1);*/
    }
}
