package com.Arrays;

import java.util.Scanner;

public class MaxContinuesOnesInString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(maxConsecutiveOnes(s));
    }

    private static int maxConsecutiveOnes(String A) {
        int max=0;
        int countOfOne=0;
        for (int i = 0; i < A.length(); i++) {
            if(A.charAt(i)=='1')countOfOne++;
        }
        boolean flag = false;
        int previousOneCount=-1;
        for (int i = 0; i < A.length(); i++) {
            if(A.charAt(i)=='0'){
                flag = true;
                //i to left previous 0 count
                int l = 0;
                if(previousOneCount==-1) {
                    for (int j = i - 1; j >= 0; j--) {
                        if (A.charAt(j) == '1') {
                            l++;
                        } else break;

                    }
                }else l=previousOneCount;
                //i to right next 0 count
                int r=0;
                for (int j = i+1; j <A.length(); j++) {
                    if(A.charAt(j)=='1'){
                        r++;
                    }else break;
                }
                previousOneCount = r;
                //if l+r <c1 then len = l+r+1 else len l+r break
                int len = (l+r<countOfOne)?l+r+1:l+r;
                max = Math.max(len,max);
            }
        }
        return (flag)?max:A.length();
    }
}
