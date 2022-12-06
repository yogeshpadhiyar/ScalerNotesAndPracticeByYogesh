package com.Arrays;

import java.util.Scanner;

public class CellWithOddValue {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int indexs = sc.nextInt();
        int[][] indices = new int[indexs][2];
        for (int i = 0; i < indexs; i++) {
                indices[i][0]= sc.nextInt();
                indices[i][1]= sc.nextInt();
        }
        System.out.println(solve(m,n,indices));
    }

    private static int solve(int m, int n, int[][] indices) {
        int[][] r = new int[m][n];
        for(int i=0; i<indices.length; i++){
            int row = indices[i][0];
            int col = indices[i][1];

            for(int j=0; j<r[0].length; j++){
                r[row][j] += 1;
            }
            for(int k=0; k<r.length; k++){
                r[k][col] += 1;
            }
        }
        int count=0;
        for(int i=0;i<r.length; i++){
            for(int j=0; j<r[0].length;j++){
                if(r[i][j]%2!=0){
                    count++;
                }
            }
        }
        return count;
    }
}
