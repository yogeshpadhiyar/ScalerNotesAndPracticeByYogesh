package com.Arrays;

import java.util.*;

public class MatrixAll {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] matrix = new int[N][M];
        ArrayList<ArrayList<Integer>> mat = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < M; j++) {
                row.add(sc.nextInt());
            }
            mat.add(row);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        System.out.println(diagonalSum(matrix));

    }

    private static int diagonalSum(int[][] mat) {
        int sum=0;
        for (int i = 0,j=mat.length-1; i <mat.length; i++,j--) {
            sum += mat[i][i] +mat[i][j];
            if(i==j)
                sum -=mat[i][i];
        }
        return sum;
    }

    private static ArrayList<ArrayList<Integer>> rowToColZero(ArrayList<ArrayList<Integer>> A){
        Set<Integer> i = new HashSet<>();
        Set<Integer> j = new HashSet<>();
        for (int k = 0; k < A.size(); k++) {
            if(A.get(k).contains(0)){
                i.add(k);
                for (int l = 0; l < A.get(k).size(); l++) {
                    if(A.get(k).get(l)==0)
                        j.add(l);
                }
            }
        }
        for (int k = 0; k < A.size(); k++) {
            if(i.contains(k)){
                A.get(k).replaceAll(integer -> 0);
            }
            for (int l = 0; l < A.get(k).size(); l++) {
                if(j.contains(l)){
                    A.get(k).set(l,0);
                }
            }
        }

        return A;
    }

    private static int mainDiagonalSum(final List<ArrayList<Integer>> A){
        int sum=0;
        for (int i = 0; i < A.size(); i++) {
            sum += A.get(i).get(i);
        }
        return sum;
    }

    private static  ArrayList<Integer> rowSum(ArrayList<ArrayList<Integer>> A){
        ArrayList<Integer> rowSum = new ArrayList<>();
        for (int i = 0; i < A.size(); i++) {
            int sum = 0;
            for (int j = 0; j < A.get(0).size(); j++) {
                sum += A.get(i).get(j);
            }
            rowSum.add(sum);
        }
        return rowSum;
    }
    private static int isSameMatrix(ArrayList<ArrayList<Integer>> A, ArrayList<ArrayList<Integer>> B){
        for (int i = 0; i < A.size(); i++) {
            for (int j = 0; j < A.get(0).size(); j++) {
                if(!Objects.equals(A.get(i).get(j), B.get(i).get(j))){
                    return 0;
                }
            }
        }
        return 1;
    }

    private static ArrayList<ArrayList<Integer>> scalarMultiplication(ArrayList<ArrayList<Integer>> A, int B){
        for (ArrayList<Integer> integers : A) {
            for (int j = 0; j < integers.size(); j++) {
                integers.set(j, integers.get(j) * B);
            }
        }
        return A;
    }

    private static int minorDiagonalSum(final List<ArrayList<Integer>> A){
        int sum=0;
        int j = A.size();
        int i=1;
        while (j>=0 && i<=A.size()){
            sum += A.get(i-1).get(j-1);
            j--;i++;
        }
        return sum;
    }

    private static ArrayList<ArrayList<Integer>> spiralMatrixCreation(int A){
        ArrayList<ArrayList<Integer>> mat = new ArrayList<>();
        int c=1;
        for (int i = 0; i < A; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < A; j++) {
                row.add(0);
            }
            mat.add(row);
        }

        for (int k = A,p=0; k >=0; k-=2,p++) {
            int i=p,j=p;
            int n=0;
            while (n<k){
                mat.get(i).set(j,c++);
                j++;n++;
            }
            /*for (int n = 0; n < k-1; n++) {
                mat.get(i).set(j,c++);
                j++;
            }*/
            j--;
            i++;
            n=0;
            while (n<k-1){
                mat.get(i).set(j,c++);
                i++;n++;
            }
            i--;
            j--;
            n=0;
            while (n<k-1){
                mat.get(i).set(j,c++);
                j--;n++;
            }
            j++;
            i--;
            n=0;
            while (n<k-2){
                mat.get(i).set(j,c++);
                i--;n++;
            }
        }
        return mat;
    }

    private static ArrayList<ArrayList<Integer>> addMatrix(ArrayList<ArrayList<Integer>> A, ArrayList<ArrayList<Integer>> B){
        ArrayList<ArrayList<Integer>> C = new ArrayList<>();
        int N = A.size();
        int M = A.get(0).size();
        for (int i = 0; i < N; i++) {
            ArrayList<Integer> c_row = new ArrayList<>();
            for (int j = 0; j < M; j++) {
                c_row.add(A.get(i).get(j)+B.get(i).get(j));
            }
            C.add(c_row);
        }
        return C;
    }

    private static ArrayList<ArrayList<Integer>> antiDiagonal(ArrayList<ArrayList<Integer>> A){
        ArrayList<ArrayList<Integer>> r = new ArrayList<>();
        int n = A.size();
        int m = A.get(0).size();

        for (int k = 0; k < m; k++) {
            int i=0,j=k;
            diagonalInner(i,j,A,r);
        }

        for (int k = 0; k < n; k++) {
            int i=k, j=m-1;
            diagonalInner(i,j,A,r);
        }
        return r;
    }

    private static void diagonalInner(int i, int j , ArrayList<ArrayList<Integer>> A, ArrayList<ArrayList<Integer>> r ){
        int n = A.size();
        int m = A.get(0).size();
        ArrayList<Integer> row = new ArrayList<>();
        while (i<n && j>=0){
            row.add(A.get(i).get(j));
            i++;
            j--;
        }
        int rowLength = row.size();
        for (int l = 0; l <m- rowLength ; l++) {
            row.add(0);
        }
        r.add(row);
    }

    private static ArrayList<Integer> ColSum(ArrayList<ArrayList<Integer>> A){
        int n= A.size();
        int m = A.get(0).size();
        ArrayList<Integer> colsum = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int sum=0;
            for (int j = 0; j < n; j++) {
                sum += A.get(j).get(i);
            }
            colsum.add(sum);
        }
        return colsum;
    }

    private static ArrayList<ArrayList<Integer>> multiplexMatrix(ArrayList<ArrayList<Integer>> A, ArrayList<ArrayList<Integer>> B){
        ArrayList<ArrayList<Integer>> r = new ArrayList<>();
        int m = A.size(), n=A.get(0).size();
        int p = B.get(0).size();
        for (int i = 0; i < m; i++) {
            ArrayList<Integer> Crow = new ArrayList<>();
            for (int j = 0; j < p; j++) {
                int cElm=0;
                for (int k = 0; k < n; k++) {
                    cElm += A.get(i).get(k) * B.get(k).get(j);
                }
                Crow.add(cElm);
            }
            r.add(Crow);
        }
        return r;
    }

    private  static ArrayList<ArrayList<Integer>> subtractionMatrix(ArrayList<ArrayList<Integer>> A, ArrayList<ArrayList<Integer>> B) {
        ArrayList<ArrayList<Integer>> C = new ArrayList<>();
        for (int i = 0; i < A.size(); i++) {
            ArrayList<Integer> cRow = new ArrayList<>();
            for (int j = 0; j < A.get(0).size(); j++) {
                cRow.add(A.get(i).get(j) - B.get(i).get(j));
            }
            C.add(cRow);
        }
        return C;
    }
    
    private static ArrayList<ArrayList<Integer>> transpose(ArrayList<ArrayList<Integer>> A){
        ArrayList<ArrayList<Integer>> r = new ArrayList<>();
        for (int i = 0; i < A.get(0).size(); i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < A.size(); j++) {
                row.add(A.get(j).get(i));
            }
            r.add(row);
        }
        return r;
    }

    private static ArrayList<ArrayList<Integer>> rotate(ArrayList<ArrayList<Integer>> A){
        ArrayList<ArrayList<Integer>> r = new ArrayList<>();
        for (int i = 0; i < A.get(0).size(); i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < A.size(); j++) {
                row.add(A.get(j).get(i));
            }
            reverseFullList(row);
            r.add(row);
        }

        return r;
    }

    public static void reverseFullList(ArrayList<Integer> A){
        for (int i = A.size()-1,j=0; i >=A.size()/2; i--,j++) {
            int last = A.get(i);
            A.set(i,A.get(j));
            A.set(j,last);
        }
//        return A;
    }

    private static void swap(int i, int j, ArrayList<ArrayList<Integer>> a) {
        int temp = a.get(i).get(j);
        int temp1 = a.get(j).get(i);
        a.get(j).set(i,temp);
        a.get(i).set(j,temp1);
    }

    private static ArrayList<ArrayList<Integer>> squareTranspose(ArrayList<ArrayList<Integer>> A){
        for (int i = 0; i < A.size(); i++) {
            for (int j = 0; j < i; j++) {
                swap(i,j,A);
            }
        }
        for (int i = 0; i < A.size(); i++) {
            reverseFullList(A.get(i));
        }
        return A;
    }

    private static void printMatrix(ArrayList<ArrayList<Integer>> mat){
        for (int i = 0; i < mat.size(); i++) {
            System.out.println(mat.get(i));
        }
    }
}
