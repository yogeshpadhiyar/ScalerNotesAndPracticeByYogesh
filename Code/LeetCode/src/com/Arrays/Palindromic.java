package com.Arrays;

import java.util.Scanner;

public class Palindromic {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(isPalindromic(str));
    }

    private static boolean isPalindromic(String str) {
        int n = str.length();
        for (int i = 0,j=n-1; i < n/2; i++,j--) {
            if(str.charAt(i)!=str.charAt(j))
                return false;
        }
        return true;
    }
}
