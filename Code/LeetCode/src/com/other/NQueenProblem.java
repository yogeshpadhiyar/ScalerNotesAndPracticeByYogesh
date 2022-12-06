package com.other;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.sun.javafx.fxml.expression.Expression.add;

public class NQueenProblem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
    }

    public static List<List<String>> NQueens(int n){
        char[][] board = new char[n][n];
        boolean[] cols = new boolean[n];
        boolean[] ndiag = new boolean[2*n-1];
        boolean[] rdiag = new boolean[2*n-1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j]='.';
            }
        }
        List<List<String>> ans = new ArrayList<>();
        findDiffNQueenSolution(0,n,board,cols,ndiag,rdiag,ans);
        return ans;
    }

    private static void findDiffNQueenSolution(int row, int n, char[][] board, boolean[] cols, boolean[] ndiag,
                                               boolean[] rdiag, List<List<String>> ans) {
        if(row==board.length){
            add(board,ans);
            return;
        }
        for (int i = 0; i < board.length; i++) {
            if(!cols[i] && !ndiag[row+i] && !rdiag[row-i+n-1]){
                cols[i]=true;
                ndiag[row+i]=true;
                rdiag[row-i+n-1]=true;
                board[row][i]='Q';
                findDiffNQueenSolution(row+1,n,board,cols,ndiag,rdiag,ans);
                board[row][i]='.';
                cols[i]=false;
                ndiag[row+i]=false;
                rdiag[row-i+n-1]=false;
            }
        }

    }

    private static void add(char[][] board, List<List<String>> ans){
        List<String> rowList = new ArrayList<>();
        for (char[] chars : board) {
            String row = "";
            for (int j = 0; j < board.length; j++) {
                row = row + chars[j];
            }
            rowList.add(row);
        }
        ans.add(rowList);
    }
}
