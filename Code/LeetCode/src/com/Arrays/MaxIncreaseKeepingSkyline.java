package com.Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class MaxIncreaseKeepingSkyline {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int p = sc.nextInt();
        /*String t=sc.nextLine();
        String[] arr = new String[p];
        for (int i = 0; i < p; i++) {
            arr[i]=sc.nextLine();
        }*/
        int[] arr = new int[p];
        for (int i = 0; i < p; i++) {
            arr[i] = sc.nextInt();
        }
        int q = sc.nextInt();
        /*int[][] grids = new int[p][q];
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < q; j++) {
                grids[i][j]= sc.nextInt();
            }
        }*/

//        System.out.println(Arrays.deepToString(diagonalSort(grids)));
        System.out.println(wateringPlants(arr,q));
    }

    private static int maxIncreaseKeepingSkyline(int[][] grids) {
        int[] rowMax = new int[grids.length];
        int[] colMax = new int[grids.length];
        for (int i = 0; i < grids.length; i++) {
            int row_max=0,col_max=0;
            for (int j = 0; j < grids.length; j++) {
                row_max = Math.max(grids[i][j],row_max);
                col_max = Math.max(grids[j][i],col_max);
            }
            rowMax[i]=row_max;
            colMax[i]=col_max;
        }

        int max=0;
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids.length; j++) {
                max += Math.min(rowMax[i],colMax[j]) - grids[i][j];
            }
        }
        return max;
    }

    private static int maxWidthOfVerticalArea(int[][] points){
        int max=0;
        Arrays.sort(points, (p1,p2)->{
            if(p1[0]<p2[0])
                return -1;
            else if(p1[0]>p2[0])
                return 1;
            return 0; });
        for (int i = 1; i < points.length; i++) {
            max = Math.max(max, points[i][0]-points[i-1][0]);
        }
        return max;
    }

    public static int numberOfBeams(String[] bank) {
        int[] beam = new int[bank.length];
        for (int i = 0,j=0; i < bank.length; i++) {
            int l = setLeaserCount(bank[i]);
            if(l!=0){
                beam[j]=l;
                j++;
            }
        }
        int ans=0;
        for (int i = 0; i < beam.length - 1; i++) {
            ans += beam[i]*beam[i+1];
        }
        return ans;

        /*int prev = 0;
        int res = 0;
        for (String b : bank) {
            int count = 0;
            for (char c : b.toCharArray()) {
                if (c == '1')
                    count++;
            }
            res += count * prev;
            if (count != 0)
                prev = count;
        }
        return res;*/
    }

    private static int setLeaserCount(String leasers){
        int c=0;
        for (int i = 0; i < leasers.length(); i++) {
            if(leasers.charAt(i)=='1') c++;
        }
        return c;
    }

    private static void sortDiagonalInner(int[][] mat, int start, int curr){
        int i=start,j=curr;
        ArrayList<Integer> diagonal = new ArrayList<>();
        while (i<mat.length && j<mat[0].length){
            diagonal.add(mat[i][j]);
            i++;j++;
        }
        Collections.sort(diagonal);
        i=start;
        j=curr;
        int c=0;
        while (i<mat.length && j<mat[0].length){
            mat[i][j] = diagonal.get(c);
            i++;j++;c++;
        }
    }
    private static int[][] diagonalSort(int[][] mat) {
        for (int k = 0; k < mat[0].length; k++) {
            sortDiagonalInner(mat,0,k);
        }
        for (int k = 1; k < mat.length; k++) {
            sortDiagonalInner(mat,k,0);
        }
        return mat;
    }

    private static int wateringPlants(int[] plants, int capacity) {
        int can = capacity;
        int step =1;
        for (int i = 0; i < plants.length-1; i++) {
            can = can - plants[i];
            if(can - plants[i+1]<0){
                step += 2*(i+1)+1;
                can = capacity;
            }else{
                step++;
            }
        }
        return step;
    }
}

