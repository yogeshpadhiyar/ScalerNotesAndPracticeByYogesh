package com.Arrays;

import java.util.Scanner;

public class MinMax {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int min=Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            int temp = sc.nextInt();
            if(temp>max)
                max=temp;
            if(temp<min)
                min=temp;
        }
        System.out.println(max+" "+min);

    }
}
