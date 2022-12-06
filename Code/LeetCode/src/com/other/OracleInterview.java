package com.other;


import com.tree.BinaryTreeImpl;

import java.util.*;

import static com.advanceClass.AdvanceClass2.swap;

public class OracleInterview {
     static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            this.val =x;
            this.left=null;
            this.right=null;
        }
    }
    static ArrayList<Integer> ans = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        String str = sc.nextLine();
        ArrayList<Integer> a = new ArrayList<>();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            a.add(sc.nextInt());
        }
        System.out.println(bubbleSortUsingRecursion(a));
        /*HashMap<Character, Integer> charOccurnce = findNoOfFreqOfCharInString(str);
        for (Map.Entry<Character, Integer> entry : charOccurnce.entrySet()){
            System.out.println(entry.getKey() +"->"+entry.getValue());
        }*/
//        System.out.println(findLargestAndSecoundLarget(a));
//        int arr[] = { 100,14, 46, 47, 94, 94, 52, 86, 36, 94, 89 };
//        String str1 = "abcdABCDabcd";
//        {a=2, A=1, b=2, B=1, c=2, C=1, d=2, D=1}

    }

    private static HashMap<Character, Integer> findNoOfFreqOfCharInString(String str) {
        HashMap<Character, Integer> hm  = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            hm.put(str.charAt(i), hm.getOrDefault(str.charAt(i),0)+1);
        }
        return hm;
    }

    private static ArrayList<Integer> findLargestAndSecoundLarget(ArrayList<Integer> A) {
        int largest = Integer.MIN_VALUE;
        int secoundLargest = Integer.MIN_VALUE;
        for (int i = 0; i < A.size(); i++) {
            if(A.get(i)>largest){
                secoundLargest = largest;
                largest = A.get(i);
            }else if(A.get(i)>secoundLargest)   secoundLargest = A.get(i);
        }
        return new ArrayList<>(Arrays.asList(largest,secoundLargest));
    }

    private static ArrayList<Integer> bubbleSortUsingRecursion(ArrayList<Integer> arr){
        bubbleSortUsingRecursionHelper(arr, arr.size()-1);
        return arr;
    }
    private static void bubbleSortUsingRecursionHelper(ArrayList<Integer> arr, int i) {
        //base condition
        if(i==0) return;
        int maxIndex = findMaxElmIndex(arr, i);
        swap(arr, i, maxIndex);
        bubbleSortUsingRecursionHelper(arr,i-1);
    }
    private static int findMaxElmIndex(ArrayList<Integer> arr, int i){
        int max = Integer.MIN_VALUE;
        int index =0;
        for (int j = 0; j <=i; j++) {
            if(max<arr.get(j)){
                max = arr.get(j);
                index = j;
            }
        }
        return index;
    }
}

