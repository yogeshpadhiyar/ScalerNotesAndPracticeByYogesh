package com.Arrays;

import java.util.Scanner;

public class MinOperationToAllTogether {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String boxes = sc.nextLine();
        int[] result = minOperation(boxes);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]+" ");
        }
    }

    private static int[] minOperation(String boxes) {
        int[] setIndex = new int[boxes.length()];
        int[] result = new int[boxes.length()];
        for (int i = 0,j=0; i < boxes.length(); i++) {
            if(boxes.charAt(i)=='1'){
                setIndex[j]=i;
                j++;
            }
        }

        for (int i = 0; i < boxes.length(); i++) {
            for (int j = 0; j < setIndex.length; j++) {
                if (setIndex[j] != 0 || j==0)
                    result[i] += Math.abs(setIndex[j] - i);
                else
                    break;
            }
        }
        return result;
    }
}
