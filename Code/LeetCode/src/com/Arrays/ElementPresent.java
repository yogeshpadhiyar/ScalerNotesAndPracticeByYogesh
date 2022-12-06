package com.Arrays;

import java.util.Scanner;
import java.util.stream.IntStream;

public class ElementPresent {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        IntStream.range(0,T).forEach(t ->{
            int n = sc.nextInt();
            int arr[] = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i]= sc.nextInt();
            }
            int B = sc.nextInt();
            boolean flag=false;
            for (int i = 0; i < n; i++) {
                if(arr[i]==B){
                    flag=true;
                    break;
                }
            }
            if(flag)
                System.out.println(1);
            else
                System.out.println(0);

        });
    }
}
