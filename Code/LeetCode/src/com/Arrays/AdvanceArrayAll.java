package com.Arrays;

import java.util.*;

public class AdvanceArrayAll {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        String A = sc.nextLine();
        int n = sc.nextInt();
        ArrayList<Integer> A = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            A.add(sc.nextInt());
        }
//        int q = sc.nextInt();
        /*ArrayList<ArrayList<Integer>> queries = new ArrayList<>();
        for (int i = 0; i < q; i++) {
            ArrayList<Integer> query = new ArrayList<>();
            query.add(sc.nextInt());
            query.add(sc.nextInt());
            query.add(sc.nextInt());
            queries.add(query);
        }*/

        System.out.println(countOfMinSwap(A));
    }

    private static int largestSubArrayWithContiguousElm(ArrayList<Integer> A){
        //Brute force approach
        int ans = 0;
        for (int i = 0; i < A.size(); i++) {
            int min = A.get(i), max = A.get(i);
            for (int j = i+1; j < A.size(); j++) {
                min = Math.min(min,A.get(i));
                max = Math.max(max,A.get(i));

                if((max-min)==(j-i)){
                    ans = Math.max(ans,(j-i+1));
                }
            }
        }
        return ans;
    }

    private static ArrayList<Integer> GeneratePFSumFromQuery(ArrayList<Integer> a, ArrayList<ArrayList<Integer>> queries) {
        for (ArrayList<Integer> query: queries) {
            a.set(query.get(0),query.get(1));
        }
        int sum=0;
        for (int i = 0; i < a.size(); i++) {
            a.set(i,sum+a.get(i));
            sum =a.get(i);
        }
        return a;
    }

    private static ArrayList<Integer> GeneratePFSumFromQueryGoogle(int A, ArrayList<ArrayList<Integer>> queries){
        ArrayList<Integer> a = new ArrayList<>(Collections.nCopies(A+1,0));
        for (int i=0; i< queries.size(); i++) {
            a.set( queries.get(i).get(0) , a.get(queries.get(i).get(0)) + queries.get(i).get(2) );

            if(queries.get(i).get(1)+1<a.size())
                a.set( queries.get(i).get(1)+1 , a.get(queries.get(i).get(1)+1) - queries.get(i).get(2) );
        }
        int sum=0;
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 1; i < a.size(); i++) {
            ans.add(sum+a.get(i));
            sum =ans.get(i-1);
        }
        return ans;
    }

    private static ArrayList<Integer> allPFMaxSum(ArrayList<Integer> A){
        int max=Integer.MIN_VALUE;
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < A.size(); i++) {
            max = Math.max(max,A.get(i));
            ans.add(max);
        }
        return ans;
    }

    private static int fillWater(int[] height){
        //travel from 1 to n-1
        //pick i building
        //find left max (bountry)
        //find right max (bountry)
        //min of left max, right max
        //add on ans with water fill diff.
        int ans=0;
        ArrayList<Integer> pfMax = new ArrayList<>();
        ArrayList<Integer> sfMax = new ArrayList<>(Collections.nCopies(height.length,0));
        pfMax.add(height[0]);
        for (int i = 1; i < height.length; i++) {
            pfMax.add(Math.max(pfMax.get(i-1),height[i-1]));
        }
        sfMax.set(height.length-1,height[height.length-1]);
        for (int i = height.length-2; i >=0; i--) {
            sfMax.set(i,Math.max(sfMax.get(i+1),height[i+1]));
        }

        for (int i = 1; i < height.length - 1; i++) {
            int leftMax = pfMax.get(i);
            int rightMax = sfMax.get(i);
            int diff = Math.min(leftMax,rightMax)-height[i];
            if(diff>0) ans+=diff;
        }
        return ans;
    }

    public static ArrayList<Integer> plusOne(ArrayList<Integer> A) {
        for (int i = 0; i < A.size();) {
            if(A.get(i)==0) A.remove(i);
            else break;
        }
        int N = A.size()-1;
        if(N==-1) {
            A.add(1);
            return A;
        }
        if (A.get(N) >= 9) {
            while (N>=0 && A.get(N) == 9) {
                A.set(N, 0);
                N--;
            }
        }
        if(N!=-1) {
            A.set(N, A.get(N) + 1);
            return A;
        }
        else {
            ArrayList<Integer> ans = new ArrayList<>();
            ans.add(1);
            ans.addAll(A);
            return ans;
        }
    }

    public static ArrayList<Integer> maxSet(ArrayList<Integer> A) {
        A.add(-1);
        long max =0L;
        long t =0L;
        ArrayList<Integer> ans = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < A.size(); i++) {
            if(A.get(i)>=0){
                temp.add(A.get(i));
                t +=A.get(i);
            }else{
                if(max<t){
                    ans = temp;
                    max = t;
                    temp = new ArrayList<>();
                    t=0;
                }else if(max==t){
                    if(ans.size()<temp.size()){
                        ans = temp;
                        temp = new ArrayList<>();
                        t=0;
                    }else if(ans.size() == temp.size() && !ans.isEmpty() && ans.get(0) > temp.get(0)){
                        ans = temp;
                        temp = new ArrayList<>();
                        t=0;
                    }
                }
                temp = new ArrayList<>();
                t=0;
            }
        }
        return ans;
    }

    public static ArrayList<Integer> flip(String A) {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < A.length(); i++) {
            if(A.charAt(i)=='1')    arr.add(-1);
            else arr.add(1);
        }
        int l=0;
        ArrayList<Integer> ans = new ArrayList<>(Arrays.asList(Integer.MAX_VALUE,Integer.MAX_VALUE));
        int bestTillNow=0, bestEndHere=0;
        for (int i = 0; i < arr.size(); i++) {
            if(bestEndHere+arr.get(i)<0){
                l = i+1;
                bestEndHere=0;
            }else   bestEndHere+=arr.get(i);

            if(bestEndHere>bestTillNow){
                bestTillNow = bestEndHere;
                ans.set(0,l+1);
                ans.set(1,i+1);
            }
        }
        if(ans.get(0)==Integer.MAX_VALUE)   return new ArrayList<>();
        else return ans;
    }

    public static int rowMaxNoOfOnes(ArrayList<ArrayList<Integer>> A){
        int max =0; int index=0;
        for (int i = 0; i < A.size(); i++) {
            int c=0;
            for (int j = 0; j < A.get(0).size(); j++) {
                if(A.get(i).get(j)==1) c++;
            }
            if(max<c){
                max=c;
                index = i;
            }
        }
        return index;
    }

    public static class Pair {
        int val;
        int index;
        public Pair(int val, int index){
            this.val=val;
            this.index=index;
        }
    }
    public static int maximumGap(final List<Integer> A) {
        ArrayList<Pair> B = new ArrayList<>();
        for (int i = 0; i < A.size(); i++) {
            B.add(new Pair(A.get(i),i+1));
        }

        B.sort((v1, v2) -> {
            if (v1.val != v2.val) {
                return (v1.val < v2.val) ? -1 : 1;
            } else {
                return 0;
            }
        });

        int maxIndex =B.get(B.size()-1).index;
        int ans=0;
        for (int i = B.size()-1; i >=0; i--) {
            ans = Math.max(ans, maxIndex-B.get(i).index);
            maxIndex = Math.max(maxIndex,B.get(i).index);
        }
        return ans;
    }

    public static int countOfMinSwap(ArrayList<Integer> A){
        int c=0;
        for (int i = 0; i < A.size(); i++) {
            while (A.get(i)!=i ){
                swap(A,i,A.get(i));
                c++;
            }
        }
        return c;
    }
    public static void swap(ArrayList<Integer> A, int i, int j){
        int temp = A.get(j);
        A.set(j,A.get(i));
        A.set(i,temp);
    }
}
