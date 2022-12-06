package com.codeChef.feblong;

import java.util.*;
import java.util.stream.IntStream;

public class FebLongTwo implements Comparator {
    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        int T = sc.nextInt();
        IntStream.range(0, T ).forEach(t->{
            int n = sc.nextInt();
            ArrayList<Integer> arr = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                arr.add( sc.nextInt());
            }
            System.out.println(BITSWAPS(arr,n));
        });
    }

    private static String BITSWAPS(ArrayList<Integer> arr, int n) {
        Collections.sort(arr, new FebLongTwo());
        System.out.println(arr);
        return "OK";
    }

    @Override
    public int compare(Object o1, Object o2){
        int e1 = Integer.parseInt(o1.toString());
        int e2 = Integer.parseInt(o2.toString());
        if((e1 & e2) !=0){
            if(e1!=e2)
                return (e1<e2)?-1:1;
            return 0;
        }else return 0;
    }

    private static String XORPAL(String str, int n) {
        int no0=0, no1=0;
        for (int i = 0; i < n; i++) {
            if(str.charAt(i)=='0') no0++;
            else no1++;
        }
        if((n&1)==1){
            return ((no0&1)==1 || (no1&1)==1)? "YES" : "NO";
        }else{
            if((no0&1)==0 && (no1&1)==0){
                return "YES";
            }else if(no0 == n/2 && no1==n/2){
                return "YES";
            }else
                return "NO";
        }
    }

    private static int NOFIX(int[] arr, int n) {
        int index =1, ans=0,i=0;
        while (i<n){
            if(arr[i]==index){
                ans++;
            }else{
                i++;
            }
            index++;
        }
        return ans;
    }

    private static int WCC(String val, int x) {
        int c=0,n=0;
        for (int i = 0; i < val.length(); i++) {
            if(val.charAt(i)=='C') c+=2;
            else if(val.charAt(i)=='N') n+=2;
            else {
                c++;n++;
            }
        }
        if(c>n) return 60*x;
        else if(c<n) return 40*x;
        else return 55*x;
    }

    private static String HELIUM3(int a, int b, int x, int y) {
        return a*b<=x*y? "YES":"NO";
    }

}
