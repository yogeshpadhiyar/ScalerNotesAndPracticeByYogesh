package com.Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ReverseArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> A = new ArrayList<>();
        int N = sc.nextInt();
        int arr[] = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        /*
        for (int i = 0; i < N; i++) {
            A.add(arr[i] );
        }
        A = reverseFullList(A);
        System.out.println(A);*/
            int k = sc.nextInt();
            k = k % N;

            reverseArray(arr, 0, N - 1);
            reverseArray(arr, 0, k - 1);
            reverseArray(arr, k, N - 1);

        /*int[] karray= new int[k];
        for (int i = 0; i < k; i++) {
            karray[i]=arr[N-i-1];
        }
        for (int i = N-k-1; i >= 0; i--) {
            arr[i+k] = arr[i];
        }
        for (int i = k-1; i >= 0; i--) {
            arr[k-i-1]=karray[i];
        }*/
        }

    public static void reverseArray(int [] arr, int start, int end){
        int i=start,j= end;
        while (i<j){
            int temp= arr[j];
            arr[j]=arr[i];
            arr[i]=temp;
            i++;
            j--;
        }
    }

    public static void printArray(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]+" ");
        }
    }

    public static ArrayList<Integer> reverseFullList(final List<Integer> A){
        ArrayList<Integer> r = new ArrayList<>();
        for (int i = A.size()-1; i >=0; i--) {
            r.add(A.get(i));
        }
        return r;
    }
}
