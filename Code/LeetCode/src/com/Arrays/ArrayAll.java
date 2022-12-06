package com.Arrays;

import java.util.*;

public class ArrayAll {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        /*int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] arr = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }*/
        int m=sc.nextInt();
        int[] arr1D = new int[m];
        for (int i = 0; i < m; i++) {
            arr1D[i] = sc.nextInt();
        }

//        int c=sc.nextInt();
        System.out.println(lengthOfSubArrayWithConsequence(arr1D));
        /*for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i]+" ");
        }*/
    }

    private static int subsetXORSum(int[] nums){
        int sum=0;
        for (int i = 0; i < nums.length; i++) {
            int xorSum=0;
            for (int j = i; j <nums.length; j++) {
                for (int k = i; k <=j; k++) {
                    xorSum = nums[j] ^ xorSum;
                    sum += xorSum;
                }
            }
        }
        return sum;
    }

    private static int findLCM(int A, int B){
        int multi = A*B;
        int gcd = gcdOfTwoNumber(A,B);
        if(gcd ==0){
            return multi;
        }else{
            return multi /gcd;
        }

        /*int G=1;
        for (int i = 0; i < Math.min(A, B); i++) {
            if(A%i==0 && B%i==0){
                G=i;
            }
        }
        return (A*B)/G;*/
    }

    private static int findGCD(int[] nums) {
        int gcd;
        int min =Integer.MAX_VALUE , max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            min = Math.min(min,nums[i]);
            max = Math.max(max,nums[i]);
        }
        gcd = gcdOfTwoNumber(min,max);
        return gcd;
    }

    private static int gcdOfTwoNumber(int a, int b) {
        if(a==0)
            return b;
        if(b==0)
            return a;
        if(a==b)
            return a;
        if(a>b)
            return gcdOfTwoNumber(a-b,b);
        else
            return gcdOfTwoNumber(a,b-a);
    }

    public static int goodPairNoCount(int arr[]){
        Arrays.sort(arr);
        int c=0, r=0;
        for( int i =0; i<arr.length; i++){
            if(arr[i]==arr[i+1]){
                c++;
            }else{
                if(c!=0){
                    r += (c*c+1)/2;
                    c=0;
                }
            }
        }
        return r;
    }

    private static void movesZero(int[] nums){
        int nnz=1;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]==0){
                nnz = findNextPositive(nums, nnz);
                if(nnz==-1)
                    break;
                swap(i,nnz,nums);
            }
            nnz++;
        }
    }

    private static int findNextPositive(int[] nums, int nnz) {
        for (int i = nnz; i < nums.length; i++) {
            if(nums[i]!=0){
                return i;
            }
        }
        return -1;
    }

    public static void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static int[][] matrixReshape(int[][] mat, int r, int c){
        int m=mat.length, n=mat[0].length;
        int total= m*n;
        if(r*c!=total)
            return mat;

        int[][] res = new int[r][c];
        int t=1,i=0,j=0,i1=0,j1=0;
        while (t<=total){
            if(j==n){
                i++;
                j=0;
            }
            if(j1==c){
                i1++;
                j1=0;
            }
            res[i1][j1] = mat[i][j];
            t++;
            j++;
            j1++;
        }
        return res;
    }

    private static int findKthPositive(int[] arr, int k) {
        int i=0,j=1;
        while (i<arr.length){
            if(arr[i]!=j){
                if(k-1!=0)
                    k--;
                else
                    return j;
                j++;
            }else {
                i++;
                j++;
            }
        }
        return arr[arr.length-1]+k;
    }

    private static int[] sumZero(int n){
        int[] ans = new int[n];
        int i = n/2,j=0;
        if((n&1)==1) ans[j++]=0;
        while(j<n){
            ans[j++] = i;
            ans[j++] = -i;
            i--;
        }

        return ans;
    }

    private static int findMaxConsecutiveOnes(int[] nums) {
        int c=0,ans=0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]==0){
                ans = Math.max(c,ans);
                c=0;
            }else
                c++;
        }
        ans = Math.max(c,ans);
        return ans;
    }

    private static boolean checkZeroOnes(String s) {
        int c0=0,c1=0;
        int a0=0,a1=0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)=='1'){
                c1++;
                a0 = Math.max(c0,a0);
                c0=0;
            }else{
                c0++;
                a1 = Math.max(c1,a1);
                c1=0;
            }
        }
        a0 = Math.max(c0,a0);
        a1 = Math.max(c1,a1);
        return a1>a0;
    }

    private static int[] findingUsersActiveMinutes(int[][] logs, int k) {
        HashMap<Integer, Set<Integer>> count = new HashMap<>();
        for (int[] log : logs) {
            Set<Integer> temp;
            if (!count.containsKey(log[0])) {
                temp = new HashSet<>();
            } else {
                temp = count.get(log[0]);
            }
            temp.add(log[1]);
            count.put(log[0], temp);
        }
        int[] ans = new int[k];
        count.values().forEach(s -> {
            int temp = s.size();
            ans[temp-1]++;
        });
        return ans;
    }

    private static boolean carPooling(int[][] trips, int capacity) {
        int [] tripP = new int[1001];
        for(int[] trip : trips){
            tripP[trip[1]] += trip[0];
            tripP[trip[2]] -= trip[0];
        }
        int load = 0;
        for (int t : tripP) {
            load += t;
            if (load > capacity) {
                return false;
            }
        }
        return true;
    }

    private static int lengthOfSubArrayWithConsequence(int[] B){
        int max =0;
        for (int i = 0; i < B.length; i++) {
            ArrayList<Integer> sb = new ArrayList<>();
            for (int j = i; j < B.length; j++) {
                sb.add(B[j]);
                Collections.sort(sb);
                for (int k = 0; k < sb.size(); k++) {
                    if(k!=0){
                        if(sb.get(k-1)+1!=sb.get(k)) {
                            break;
                        }else{
                            max = Math.max(max,k+1);
                        }
                    }
                }
            }
        }
        return max;
    }
}
