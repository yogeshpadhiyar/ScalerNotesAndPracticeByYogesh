package com.Arrays;

import com.sun.org.apache.regexp.internal.RE;

import java.util.*;

public class TwoSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
//        int m = sc.nextInt();
//        int nums[] = new int[n];
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums.add(sc.nextInt());
        }
        /*ArrayList<Integer> B = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            B.add(sc.nextInt());
        }*/
//        int target = sc.nextInt();
        System.out.println(threeSumZero(nums));
    }

    public static int anotherCountRectangle(ArrayList<Integer> A, int B) {
        int p1=0,p2=A.size()-1;
        long c=0;
        int M=1000000007;
        while (p1<A.size() && p2>=0){
            long mul =(long) A.get(p1) *A.get(p2);
            if(mul<B){
                p1++;
                c = (c+p2+1)%M;
            }else{
                p2--;
            }
        }
        c = (c+M)%M;
        return (int)c;
    }

    public static ArrayList<Integer> closestPair(ArrayList<Integer> A, ArrayList<Integer> B, int C) {
        int p1=0,p2=B.size()-1;
        int ans=Integer.MAX_VALUE;
        int l=p1,r=p2;
        ArrayList<Integer> res = new ArrayList<>();
        while (p1<A.size() && p2>=0){
            int diff = Math.abs((A.get(p1)+B.get(p2))-C);
            if(ans==diff){
                if(p1<l || (p1==l && p2<r)){
                    l=p1;
                    r=p2;
                }
            }
            else if(ans>diff){
                ans = diff;
                l = p1;
                r=p2;
                if(diff==0) break;
            }
            if(A.get(p1)+B.get(p2)>C)   p2--;
            else    p1++;
        }
        res.add(A.get(l));
        res.add(B.get(r));
        return res;
    }

    public static ArrayList<Integer> sortColors(ArrayList<Integer> A) {
        int p1=0,p3=A.size()-1;
        ArrayList<Integer> ans = new ArrayList<>(Collections.nCopies(A.size(),0));
        for (Integer integer : A) {
            if (integer == 0) p1++;
            else if (integer == 2) {
                ans.set(p3, 2);
                p3--;
            }
        }
        for (int i = p1; i <=p3; i++) {
            ans.set(i,1);
        }
        return ans;
    }

    public int minimizeAbsoluteDiff(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<Integer> C) {
        int p1=0,p2=0,p3=0;
        int ans=Integer.MAX_VALUE;
        while (p1<A.size() && p2<B.size() && p3<C.size()){
            int abcMax = Math.max(A.get(p1),Math.max(B.get(p2),C.get(p3)));
            int abcMin = Math.min(A.get(p1),Math.min(B.get(p2),C.get(p3)));
            ans = Math.min(ans,Math.abs(abcMax-abcMin));
            if(A.get(p1)<=B.get(p2) && A.get(p1)<=C.get(p3))  p1++;
            else if(B.get(p2)<=C.get(p3))   p2++;
            else p3++;
        }
        return ans;
    }

    public static int minimize(final List<Integer> A, final List<Integer> B, final List<Integer> C) {
        int p1=0,p2=0,p3=0;
        int ans=Integer.MAX_VALUE;
        while (p1<A.size() && p2<B.size() && p3<C.size()){
            int ab = Math.abs(A.get(p1)-B.get(p2));
            int bc = Math.abs(B.get(p2)-C.get(p3));
            int ca = Math.abs(C.get(p3)-A.get(p1));
            int tempMax = Math.max(ab,bc);
            ans = Math.min(ans, Math.max(tempMax,ca));
            if(A.get(p1)<=B.get(p2) && A.get(p1)<=C.get(p3))  p1++;
            else if(B.get(p2)<=C.get(p3))   p2++;
            else p3++;
        }
        return ans;
    }

    public static int maxWaterContain(ArrayList<Integer> A){
        int p1=0,p2=A.size()-1;
        int ans = 0;
        while (p1<p2){
            ans = Math.max(ans, Math.min(A.get(p1),A.get(p2))*(p2-p1) );
            if(A.get(p2)<A.get(p1)){
                p2--;
            }else{
                p1++;
            }
        }
        return ans;
    }

    public static int twoDiffExist(ArrayList<Integer> A, int B){
        Collections.sort(A);
        B = Math.abs(B);
        int c=0;
        int p1=0,p2=1;
        HashSet<Integer> set = new HashSet<>();
        while (p2<A.size()){
            if(A.get(p2)-A.get(p1)==B){
                if(!set.contains(A.get(p2)) || !set.contains(A.get(p1)))    c++;
                set.add(A.get(p2));
                set.add(A.get(p1));
                p2++;
            }else if(A.get(p2)-A.get(p1)>B){
                p1++;
                if(p1==p2)  p2++;
            }else{
                p2++;
            }
        }
        return c;
    }

    public static ArrayList<Integer> twoSumWithContinuousSubArr(ArrayList<Integer> A, int B){
        int p1=0,p2=1;
        if(A.size()==1) {
            if(A.get(0)!=B)
                return new ArrayList<>(Arrays.asList(-1));
            else
                return A;
        }
        long sum=A.get(p1);
        if(sum==B)
            return new ArrayList<>(Arrays.asList(A.get(0)));
        while (p2<A.size()){
            if(sum+A.get(p2)==B){
                ArrayList<Integer> ans = new ArrayList<>();
                for (int i = p1; i <=p2; i++) {
                    ans.add(A.get(i));
                }
                return ans;
            }
            else if(sum+A.get(p2)>B){
                sum -=A.get(p1);
                p1++;
            }else{
                sum += A.get(p2);
                p2++;
            }
        }
        return new ArrayList<>(Arrays.asList(-1));
    }
    private static int twoSumExist(ArrayList<Integer> A, int B){
        int p1=0, p2=A.size()-1;
        long c=0, M = 1000000007;
        while (p1<p2){
            int req = B - A.get(p1);
            if(A.get(p2)==req){
                if(Objects.equals(A.get(p1), A.get(p2))){
                    long x = p2-p1+1;
                    c = (c+ ((x*(x-1))/2)%M ) %M;
                    break;
                }
                long d1=1,d2=1;
                while(p1<p2 && Objects.equals(A.get(p1), A.get(p1 + 1))) {
                    p1++;
                    d1++;
                }
                while(p1<p2 && Objects.equals(A.get(p2), A.get(p2 - 1))) {
                    p2--;
                    d2++;
                }
                p1++;
                p2--;
                c = (c%M + (d1*d2)%M ) %M;
            }
            else if(A.get(p2)>req){
                p2--;
            }else{
                p1++;
            }
        }
        c=(c+M)%M;
        return (int)c;
    }

    private static int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> need = new HashMap<>();
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if(!need.containsKey(target - nums[i])){
                need.put(nums[i],i);
            }else{
                result[0] =  need.get(target-nums[i]);
                result[1] = i;
                break;
            }
        }
        return result;
    }

    private static List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int p2,p3,n= nums.length;
        for (int p1 = 0; p1 < nums.length-2; p1++) {
            if(p1>0 && nums[p1]==nums[p1-1])
                continue;
            p2=p1+1;
            p3=n-1;
            while (p2<p3){
                if(nums[p1]+nums[p2]+nums[p3]==0){
                    List<Integer> set = new ArrayList<>(Arrays.asList(nums[p1],nums[p2],nums[p3]));
                    result.add(set);
                    p3--;
                    //skip all duplicate
                    while (p2<p3 && nums[p3]==nums[p3+1]) p3--;
                }else if(nums[p1]+nums[p2]+nums[p3]>0){
                    p3--;
                }else p2++;
            }
        }
        return result;
    }

    public static int threeSumClosest(ArrayList<Integer> A, int B) {
        Collections.sort(A);
        int p1, n=A.size();
        int p2,p3;
        long curr=Integer.MAX_VALUE;
        for (int i = 0; i <A.size(); i++) {
            p1=i;
            p2=i+1;
            p3=n-1;
            while (p2<p3){
                long sum = A.get(p1)+A.get(p2)+A.get(p3);
                if(Math.abs(sum-B)<Math.abs(curr-B)){
                    curr= sum;
                }
                if(sum>B)   p3--;
                else p2++;
                if(sum==B)  return (int)sum;
            }
        }
        return (int)curr;
    }

    public static ArrayList<ArrayList<Integer>> threeSumZero(ArrayList<Integer> A){
        Set<ArrayList<Integer>> ans = new HashSet<>();
        Collections.sort(A);
        int p1,p2,p3,n=A.size();
        for (int i = 0; i < n; i++) {
            p1=i;
            p2=i+1;
            p3=n-1;
            while (p2<p3){
                int sum = A.get(p1)+A.get(p2)+A.get(p3);
                if(sum==0){
                    ArrayList<Integer> pair = new ArrayList<>(Arrays.asList(A.get(p1),A.get(p2),A.get(p3)));
                    ans.add(pair);
                    p2++;
                }else if(sum>0) p3--;
                else p2++;
            }
        }
        ArrayList<ArrayList<Integer>> res = new ArrayList<>(ans);
        return res;
    }
}
