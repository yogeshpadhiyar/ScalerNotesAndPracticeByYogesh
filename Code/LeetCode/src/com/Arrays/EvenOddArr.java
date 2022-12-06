package com.Arrays;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class EvenOddArr {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        IntStream.range(0,T).forEach(t -> {
            int n = sc.nextInt();
            ArrayList<Integer> odd = new ArrayList<>();
            ArrayList<Integer> even = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                int elm = sc.nextInt();
                if(elm %2==0){
                    even.add(elm);
                }else {
                    odd.add(elm);
                }
            }
            printArray(odd);
            printArray(even);

        });
    }

    public static void printArray(ArrayList<Integer> A){
        for (int i = 0; i < A.size(); i++) {
            System.out.print(A.get(i)+" ");
        }
        System.out.println();
    }
}
