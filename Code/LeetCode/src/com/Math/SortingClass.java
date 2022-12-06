package com.Math;

import java.util.*;

public class SortingClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(sc.nextInt());
        }

        System.out.println(arrayConsecutive(a));
    }

    private static int findMinCost(ArrayList<Integer> a) {
        Collections.sort(a);
        int previousPFSum=0;
        int pfSum=0;
        for (int i = 0; i < a.size(); i++) {
            previousPFSum +=a.get(i);
            pfSum += previousPFSum;
        }
        return pfSum;
    }

    private static int noOfNobelEle(ArrayList<Integer> a){
        //Non distinct element
        Collections.sort(a);
        int count=0;
        for (int i = 0; i < a.size(); i++) {
            if(a.get(i)==i) count++;
        }
        return count;
    }

    private static int noOfNobelEleV2(ArrayList<Integer> a){
        //distinct element
        Collections.sort(a);
        int count=0;
        int ans_count=0,preNobel=a.get(0);
        if(a.get(0)==0)
            ans_count++;
        for (int i = 1; i < a.size(); i++) {
            if(a.get(i)!=preNobel) {
                count = i ;
                preNobel=a.get(i);
            }
            if(count==a.get(i)) ans_count++;
        }
        return (ans_count==0)?-1:ans_count;
    }

    private static int noOfNobleEleGreaterThan(ArrayList<Integer> A){
        Collections.sort(A,Collections.reverseOrder());
        int count=0,preNoble=A.get(0),ans_count=0;
        for (int i = 0; i < A.size(); i++) {
            if(A.get(i)!=preNoble){
                count=i;
                preNoble=A.get(i);
            }
            if(A.get(i)==count) {
                ans_count++;
                count++;
            }
        }
        return (ans_count==0)?-1:ans_count;
    }

    private static int isArithmeticProgression(ArrayList<Integer> A){
        Collections.sort(A);
        int diff = Math.abs(A.get(1)-A.get(0));
        for (int i = 1; i < A.size(); i++) {
            if(Math.abs(A.get(i)-A.get(i-1))!=diff){
                return 0;
            }
        }
        return 1;
    }

    private static String largestNumber(final List<Integer> A) {
        A.sort((t1, t2) -> {
            String s1 = t1 + "";
            String s2 = t2 + "";
            if (Long.parseLong(s1 + s2) != Long.parseLong(s2 + s1))
                return Long.parseLong(s1 + s2) < Long.parseLong(s2 + s1) ? 1 : -1;
            return 0;
        });
        StringBuffer res = new StringBuffer();
        boolean flag = true;
        for (int i = 0; i < A.size(); i++) {
            if(flag && A.get(i)==0) {}
            else {
                res.append(A.get(i));
                flag=false;
            }
        }
        return (!res.toString().equals(""))? res.toString(): "0";
    }

    private static int solve(ArrayList<Integer> A) {
        A.sort(Collections.reverseOrder());
        int total= A.stream().mapToInt(i -> i).sum();
        int ans =total;
        for (int i = 1; i < A.size(); i++) {
            if(A.get(i)==0)
                break;
            ans += total-A.get(i-1);
            total -=A.get(i-1);
        }
        return ans;
    }

    public static int maxMod(ArrayList<Integer> A) {
        Collections.sort(A);
        ArrayList<Integer> set = new ArrayList<>();
        for(int i =0; i<A.size(); i++){
            if(!set.contains(A.get(i)))
                set.add(A.get(i));
        }
        int n = set.size();
        return (n==1)? 0 : set.get(n-2);
    }

    public static int arrayConsecutive(ArrayList<Integer> A){
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        long sum = 0L;
        for (int i = 0; i < A.size(); i++) {
            max = Math.max(max,A.get(i));
            min = Math.min(min,A.get(i));
            sum+=A.get(i);
        }
        min = min-1;
        long all = ((long) max *(max+1))/2;
        long diff = ((long) min *(min+1))/2;
        return (sum == (all-diff))?1:0;
    }

}


