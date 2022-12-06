package com.codeChef.feblong;

import java.util.Scanner;
import java.util.stream.IntStream;

public class ChefHairCut {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        IntStream.range(0,T).forEach(t ->{
            int n = sc.nextInt();
            int o = sc.nextInt();
            String s = sc.next();
            BINBASBASIC(s,n,o);
        });
    }

    private static int EUREKA(int n){
        double N = 0.143*n;
        return (int)Math.round(Math.pow(N,n));
    }

    private static void BINBASBASIC(String s, int n, int o){
        boolean flag=true;
        int r=0;
        for (int i = 0,j=s.length()-1; i < n/2; i++,j--) {
            if(s.charAt(i)!=s.charAt(j)){
                if(o==0){
                    flag=false;
                    break;
                }
                o--;
            }else{
                r+=2;
            }
        }
        if(flag) {
            if((n&1)==1 || ((n&1)==0 && (Math.abs(o-r)&1)==0))
                System.out.println("YES");
            else
                System.out.println("NO");
        }
        else
            System.out.println("NO");
    }

}
