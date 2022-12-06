package com.Arrays;

import javafx.collections.transformation.SortedList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class SubSetAll {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        String a = sc.nextLine();
//        String b = sc.nextLine();
        int n = sc.nextInt();
//        int k = sc.nextInt();
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(sc.nextInt());
        }
        System.out.println(oddEvenMaxSumSubSequence(arr));
    }

    private static boolean sumOfAnySubSetIsK(ArrayList<Integer> arr, int k) {
        long n = 1L<<arr.size();
        for (int i = 0; i < n; i++) {
            long sum=0L;
            for (int j = 0; j < arr.size(); j++) {
                if(checkBit(i,j)){
                    sum+=arr.get(j);
                }
            }
            if(sum==k) return true;
        }
        return false;
    }

    private static ArrayList<ArrayList<Integer>> allSubSet(ArrayList<Integer> A){
        Collections.sort(A);
        ArrayList<ArrayList<Integer>> allSubSet = new ArrayList<>();
        allSubSetWithRecursion(A,0,allSubSet,new ArrayList<>());
        Collections.sort(allSubSet, (first , second) ->{
            for (int i = 0; i < first.size() && i<second.size(); i++) {
                if(first.get(i)!=second.get(i)) return (first.get(i)<second.get(i))?-1:1;
            }
            return first.size()<=second.size()?-1:1;
        });

        return allSubSet;
        /*int n = 1<<A.size();
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> subset = new ArrayList<>();
            for (int j = 0; j < A.size(); j++) {
                if(checkBit(i,j)){
                    subset.add(A.get(j));
                }
            }
            Collections.sort(subset);
            allSubSet.add(subset);
        }
        return allSubSet;*/

    }

    private static boolean checkBit(int i, int j) {
        return ((i >> j) & 1) == 1;
    }

    private static int sumOfAllSubSet(ArrayList<Integer> arr) {
        int presentNo = 1<<(arr.size()-1);
        int sum=0;
        for (int i = 0; i < arr.size(); i++) {
            sum += arr.get(i);
        }
        return presentNo * sum;
    }

    private static int sumOfAllSubSequenceMaxSum(ArrayList<Integer> arr){
        Collections.sort(arr); //sumOfAllSubSequenceMaxSum
//        arr.sort(Collections.reverseOrder()); //sumOfAllSubSequenceMinSum - just need to sort in reverse order
        int sum=0;
        for (int i = 0; i < arr.size(); i++) {
            sum+= arr.get(i)*(1<<i);
        }
        return sum;
    }

    private static int sumOfAllSubSequenceMaxMinDiffSum(ArrayList<Integer> arr){
        Collections.sort(arr); //sumOfAllSubSequenceMaxSum
        long maxSum=0;
        for (int i = 0; i < arr.size(); i++) {
            maxSum += arr.get(i) *power(2,i,1000000007)%1000000007;
            maxSum %= 1000000007;
        }
        arr.sort(Collections.reverseOrder()); //sumOfAllSubSequenceMinSum - just need to sort in reverse order
        long minSum =0;
        for (int i = 0; i < arr.size(); i++) {
            minSum += arr.get(i) *power(2,i,1000000007)%1000000007;
            minSum %= 1000000007;
        }
        return (int)((maxSum-minSum+1000000007)%1000000007);
    }

    private static long power(int A, int B, int C) {
        int multi = 1;
        for (int i = 1; i <= B; i++) {
            multi = (multi%C * A%C )%C;
            multi = (multi<0)?multi+C:multi;
        }
        return multi %C;
    }

    private static String lexicalSubSequenceFromString(String A){
        /*String ans=A.substring(0,2);
        for (int i = 0; i < A.length(); i++) {
            for (int j = i+1; j < A.length(); j++) {
                String s = A.charAt(i)+""+A.charAt(j);
                if(ans.charAt(0)>A.charAt(i)) ans = s;
                if(ans.charAt(0)==A.charAt(i) && ans.charAt(1)>A.charAt(j)) ans =s;
            }
        }
        return ans;*/

        char c1 = 'z';
        int index = 0;
        for (int i = 0; i<A.length()-1; i++) {
            if(A.charAt(i)<c1) {
                c1 = A.charAt(i);
                index =i;
            }
        }
        char c2='z';
        for (int i = index+1; i<A.length(); i++) {
            if(A.charAt(i)<c2) {
                c2 = A.charAt(i);
            }
        }
        return c1+""+c2;
    }

    private static int sumOfAllBitwiseOrSubArray(ArrayList<Integer> A){
        long ans =0;
        int[] bitsetCount = new int[28];
        for (int i = 0; i < A.size(); i++) {
            for (int j = 0; j <= 27; j++) {
                long pw = 1<<j;
                if(checkBit(A.get(i),j)) bitsetCount[j]=i+1;
                ans = (ans+ bitsetCount[j]*(pw) );
            }
        }
        return (int)(ans %1000000007);
    }

    private static void allSubSetWithRecursion(ArrayList<Integer> A, int index ,
                                               ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> sb){
        if(index==A.size()){
            ans.add(new ArrayList<>(sb));
            return;
        }
        allSubSetWithRecursion(A,index+1,ans, sb);
        sb.add(A.get(index));
        allSubSetWithRecursion(A,index+1,ans,sb);
        sb.remove(sb.size()-1);
    }

    private static int subSequenceAG(String A){
        int c=0;
        long sum=0L;
        for (int i = 0; i < A.length(); i++) {
            if(A.charAt(i)=='G'){
                sum += c%1000000007;
                sum %=1000000007;
            }
            if(A.charAt(i)=='A') c++;
        }
        return (int)(sum%1000000007);
    }

    private static String subSequenceAofB(String A, String B){
        for (int i = 0,j=0; i < B.length(); i++) {
            if(B.charAt(i)==A.charAt(j)){
                j++;
                if(j>=A.length())   return "YES";
            }
        }
        return "NO";
    }

    private static int oddEvenMaxSumSubSequence(ArrayList<Integer> A) {
        int flag =A.get(0)&1;
        int sum=0;
        for (int i = 0; i < A.size(); i++) {
            if((A.get(i)&1)==flag){
                flag = 1-flag;
                sum ++;
            }
        }
        return sum;
    }
}
