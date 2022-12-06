package com.codeChef.starter;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Start28C {
    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        int T = sc.nextInt();
        IntStream.range(0, T ).forEach(t->{
            int n = sc.nextInt();
            ArrayList<Integer> arr = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                arr.add( sc.nextInt());
            }
            System.out.println(SUNDAY(n,arr));
        });
    }

    private static int SUNDAY(int n, ArrayList<Integer> arr) {
        int holiday = 8;
        for (int i = 0; i < arr.size(); i++) {
            if(arr.get(i)%7!=6 && arr.get(i)%7!=0)  holiday++;
        }
        return holiday;
    }

    private static int CARCHOICE(int x1, int x2, int y1, int y2) {
        float p1 = (float) y1/x1;
        float p2 = (float) y2/x2;
        if(p1!=p2)
            return (p1<p2)?-1:1;
        return 0;

    }

    private static int SELFDEF(int n, ArrayList<Integer> arr) {
        int c=0;
        for (int i = 0; i < n; i++) {
            if(arr.get(i)>=10 && arr.get(i)<=60)    c++;
        }
        return c;
    }
}
