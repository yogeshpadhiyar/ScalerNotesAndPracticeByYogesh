package com.Math;

import java.util.Scanner;

public class MathAll {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        /*int a= sc.nextInt();
        int n= sc.nextInt();
        int p= sc.nextInt();*/
        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();
        System.out.println(ConcatenateThreeNumber(A,B,C));
    }

    private static long power(int a, int n, int p) {
        int multi = 1;
        for (int i = 1; i <= n; i++) {
            multi = (multi%p * a%p )%p;
        }
        return multi %p;
    }

    private static int DivisionByEight(String A){
        int n = A.length();
        if(n>2){
            int sum = 4 * A.charAt(n-3) + 2 * A.charAt(n-2) + A.charAt(n-1);
            if(sum%8 == 0 ){
                return 1;
            }else{
                return 0;
            }
        }else{
            int sum=0,pow=1;
            for(int i=n-1; i>=0;i--){
                sum += pow*A.charAt(i);
                pow *= 10;
            }
            if(sum%8 == 0 ){
                return 1;
            }else{
                return 0;
            }
        }
    }

    private static int DivisionOfLargerNo(String A , int B){
        long ans = 0;
        long TenPower = 1;
        for (int i = A.length()-1; i >=0; i--) {
            ans =  (ans%B + (Integer.parseInt(String.valueOf(A.charAt(i)))%B * TenPower %B ) % B)%B;
            TenPower = (TenPower*10) %B;
        }
        return (int)ans;
    }

    private static int RectangleOverlap(int A, int B, int C, int D, int E, int F, int G, int H){
        if(A>=G || E>=C || B>=H || F>=D)
            return 0;
        return 1;
    }

    private static int ConcatenateThreeNumber(int A, int B, int C){
        int[] vals= {A,B,C};
        int max=0,sec_max=0,min=0;
        for (int i = 0; i < vals.length; i++) {
            if(vals[i]>=max){
                min=sec_max;
                sec_max=max;
                max=vals[i];
            }else if(vals[i]>=sec_max){
                min=sec_max;
                sec_max= vals[i];
            }else{
                min = vals[i];
            }
        }
        return (int) (Math.pow(10,5)*(min/10)+Math.pow(10,4)*(min%10)+Math.pow(10,3)*(sec_max/10)+Math.pow(10,2)*(sec_max%10)+max);
    }

    private static int titleToNumber(String A){
        int ans = 0;
        int twentySixPow=1;
        for (int i = A.length()-1; i >=0; i--) {
            int c = A.charAt(i) - 'A' +1;
            ans += c *twentySixPow;
            twentySixPow *= 26;
        }
        return ans;
    }
}
