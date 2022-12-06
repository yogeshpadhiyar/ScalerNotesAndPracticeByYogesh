package com.Math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ArrayWithMath {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n= sc.nextInt();
        ArrayList<Integer> A = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            A.add(sc.nextInt());
        }
        System.out.println(repeatedNumber(A));
    }

    private static int remainingPerson(int n) {
        int nearestPowOfTwo=1;
        while (nearestPowOfTwo<<1 <= n){
            nearestPowOfTwo = nearestPowOfTwo<<1;
        }
        return 1 + (n - nearestPowOfTwo)*2;
    }

    private static int majorityElement(final List<Integer> A){
        int major=A.get(0);
        int count=1;
        for (int i = 1; i < A.size(); i++) {
            if(A.get(i)==major)
                count++;
            else if(count!=0)
                count--;
            else major=A.get(i);
        }
        int majorC=0;
        for (int i = 0; i < A.size(); i++) {
            if(A.get(i)==major) majorC++;
        }
        return (majorC>A.size()/2)?major:-1;
    }

    private static ArrayList<Integer> threeMajorityElement(final List<Integer> nums){
        ArrayList<Integer> r = new ArrayList<>();
        if(nums.size()==1) {
            r.add(nums.get(0));
            return r;
        }
        if(nums.size()==2) {
            r.add(nums.get(0));
            r.add(nums.get(1));
            return r;
        }
        int m1=nums.get(0),c1=1;
        int m2=nums.get(1),c2=1;
        for (int i = 2; i < nums.size(); i++) {
            if(m1==m2){
                c1+=c2;
                m2=0;
                c2=0;
            }
            if(m1==0 && m2!=nums.get(i)) {
                m1 = nums.get(i);
                c1=0;
            }
            else if(m2==0 && m1!=nums.get(i)) {
                m2 = nums.get(i);
                c2=0;
            }
            if(m1!=nums.get(i) && m2!=nums.get(i)){
                if(c1!=0)   c1--;
                if(c2!=0)   c2--;
                if(c1==0)   m1=0;
                if(c2==0)   m2=0;
            }else if(m1==nums.get(i)) {
                c1++;
            }else if(m2==nums.get(i)){
                c2++;
            }
        }
        int m1Count=0,m2Count=0;
        for (int i = 0; i < nums.size(); i++) {
            if(m1==nums.get(i)) m1Count++;
            if(m2==nums.get(i)) m2Count++;
        }
        if(m1Count>nums.size()/3) {
            r.add(m1);
            return r;
        }
        else if(m2Count>nums.size()/3) {
            r.add(m2);
            return r;
        }
        else return r;
    }

    private static int MagicNumber(int A){
        int power=5;
        int ans=0;
        while(A>0){
            int bit = (A&1);
            ans += bit * power;
            power *=5;
            A = A>>1;
        }
        return ans;
    }

    private static ArrayList<Integer> repeatedNumber(final List<Integer> A) {
        int AXoRB = A.get(0);

        for (int i = 1; i < A.size(); i++) {
            AXoRB = AXoRB ^ A.get(i);
        }
        for (int i = 1; i <= A.size(); i++) {
            AXoRB = AXoRB ^ i;
        }
        int rightMostSetBit = AXoRB & -AXoRB;
        int a=0,b=0;
        for (int i = 0; i < A.size(); i++) {
            if((A.get(i)&rightMostSetBit)!=0){
                a = a^A.get(i);
            }else b = b^A.get(i);
        }
        for (int i = 1; i <= A.size(); i++) {
            if((i&rightMostSetBit)!=0){
                a = a^i;
            }else b = b^i;
        }
        if(A.contains(a))
            return new ArrayList<>(Arrays.asList(a,b));
        return new ArrayList<>(Arrays.asList(b,a));
    }
}
