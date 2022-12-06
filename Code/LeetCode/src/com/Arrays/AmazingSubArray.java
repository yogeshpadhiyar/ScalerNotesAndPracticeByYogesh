package com.Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AmazingSubArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String n = sc.nextLine();

        System.out.println(solve(n));
    }

    private static int solve(String A) {
        int c=0;
        int n = A.length();
        ArrayList<Character> vowel = new ArrayList<>(Arrays.asList( 'a','e','i','o','u'));
        for (int i = 0; i < A.length(); i++) {
            if(vowel.contains(Character.toLowerCase(A.charAt(i)))){
                c += n-i;
            }
        }
        return c%10003;
    }

}
