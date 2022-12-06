package com.Arrays;

import java.util.Scanner;
import java.util.stream.IntStream;

public class SecondLarge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        IntStream.range(0,T).forEach(t->{
            int n = sc.nextInt();
            int arr[] = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i]= sc.nextInt();
            }

            if(n<=1){
                System.out.println(-1);
            }else{
                int max=Integer.MIN_VALUE;
                int sec_max=Integer.MIN_VALUE;
                for (int i = 0; i < arr.length; i++) {
                    if(arr[i]>max){
                        sec_max = max;
                        max = arr[i];
                    }
                }
                System.out.println(sec_max);
            }
        });
    }
}
