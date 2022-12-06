package com.codeChef.starter;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Start29C {
    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        int T = sc.nextInt();
        IntStream.range(0, T ).forEach(t->{
            int n = sc.nextInt();

            ArrayList<Integer> arr = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                arr.add( sc.nextInt());
            }
            System.out.println(PMA(n,arr));
        });
    }

    private static long PMA(int n, ArrayList<Integer> arr) {

        long evenSum= 0,oddSum=0;
        long min = Long.MAX_VALUE, max = Long.MIN_VALUE;
        for (int i = 0; i < arr.size(); i++) {
            if((i&1)==0){
                evenSum +=Math.abs(arr.get(i));
                min = Math.min(min,Math.abs(arr.get(i)));
            }else {
                oddSum +=Math.abs(arr.get(i));
                max = Math.max(max,Math.abs(arr.get(i)));
            }
        }
        long original = evenSum - oddSum;
        long afterSwap = original +2*(max-min);
        return Math.max(original, afterSwap);
    }

    private static int STRP(int n, String s) {
        int ans=0;
        for (int i = 0; i < s.length(); i++) {
            if(i!=s.length()-1 && s.charAt(i)==s.charAt(i+1)){
                i++;
            }
            ans++;
        }
        return ans;
    }

    private static int BOMBTHEBASE(int n, int x, ArrayList<Integer> arr) {
        for (int i = n-1; i >=0; i--) {
            if(arr.get(i)<x)
                return i+1;
        }
        return 0;
    }
}
