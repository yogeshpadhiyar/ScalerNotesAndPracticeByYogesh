package com.other;

import java.util.Arrays;
import java.util.Scanner;

public class XpressBeesInterview {
    static String result = "";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        /*String s1 = "xpressbees";
        String s2 = "xbes"; // o/p= bee -> j
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n][m];
        for(int i=0; i<n; i++){
            Arrays.fill(dp[i],-1);
        }
        String ans = "";
        findLCS(s1,s2,n-1,m-1,dp, ans);*/
        StringBuffer r1 = new StringBuffer(result);
        System.out.println(r1.reverse());
    }
    public static int findLCS(String s1, String s2, int i, int j, int[][] dp, String ans){
        if(i==-1 || j==-1) return 0;

        if(dp[i][j]==-1){
            if(s1.charAt(i)==s2.charAt(j)){
                ans+=s1.charAt(i);
                if(result.length()<ans.length()){
                    result = ans;
                }
                dp[i][j] = findLCS(s1,s2,i-1,j-1,dp,ans);
            }else{
                ans ="";
                int approach1 = findLCS(s1,s2,i-1,j,dp, ans);
                int approach2 = findLCS(s1,s2,i,j-1,dp, ans);
                dp[i][j] = Math.max(approach1,approach2);
            }
        }
        return dp[i][j];
    }

    // input -> 0-9 & a-z
    //String s = 2a4v3x
    //StringBuffer -> append
    public static void printCharByChar(int size, String str){
        char[] chArray = str.toCharArray();
        for (char c : chArray) System.out.println(c);
    }
}
