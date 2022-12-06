package com.codeChef;

import com.Arrays.ArrayAll;

import java.util.*;

import static com.BitManuplication.BitManuplicationAll.checkBit;
import static com.Math.AdvanceMathAll.isPrime;

public class Temp {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int a = sc.nextInt();
        int[] A = new int[a];
        for (int i = 0; i < a; i++) {
            A[i]= sc.nextInt();
        }
        System.out.println(distinctSubsequence(A,a));
    }

    //TODO Pending
    /*public static int solve(String A[], int n) {
        // code here
        char need = 'a';
        for (int i = 0; i < n; i++) {
            char innerNeed;
            for (int j = 0; j < A[i].length(); j++) {

            }
        }
    }*/

    public static int distinctSubsequence(int A[], int n) {
        // add your code here
        HashMap<Integer,Integer> hm = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if(hm.containsKey(A[i])){
                hm.put(A[i],hm.get(A[i])+1);
            }else   hm.put(A[i],1);
        }
        Collection<Integer> value =  hm.values();
        int l = value.size();
        Object[] val;
        val = value.toArray();
        Arrays.sort(val);
        int ans=Integer.MIN_VALUE;

        for (int i = 0; i < l; i++) {
            ans = Math.max(ans, (l-i)*Integer.parseInt(val[i].toString()));
        }
        return ans;
    }

    public static int findMin(int A[], int n) {
        // add your code here
        long sum =0;
        int minToMakeOdd=Integer.MAX_VALUE;
        if(n==1){
            if(A[0]%2==1){
                return -1;
            }else   return 0;
        }
        boolean flag = false;
        for(int i=0; i<n; i++){
            if(A[i]%2==0){
                flag=true;
                int elm=A[i],c=0;
                while(elm%2==0){
                    elm /= 2;
                    c++;
                }
                minToMakeOdd = Math.min(minToMakeOdd,c);
            }
            sum +=A[i];
        }
        if(sum%2==0){
            return 0;
        }else{
            if(flag)
                return minToMakeOdd;
            else
                return -1;
        }
    }

    public static int minStepToMakePermutation(ArrayList<Integer> A){
        Collections.sort(A);
        long ans = 0;
        int M = 1000000007;
        for (int i = 0; i < A.size(); i++) {
            ans = ( ans + (Math.abs(i+1 - A.get(i)))%M )%M;
        }
        return (int)ans;
    }

    public static int sweetDish(ArrayList<Integer> A, int B ,int C){
        HashSet<Integer> prime = new HashSet<>();
        for (Integer integer : A) {
            if (isPrime(integer)) prime.add(integer);
        }
        int ans =0;
        int totalPair = 1<<A.size();
        for (int i = 1; i <totalPair; i++) {
            int sum=0;
            boolean hasPrime = false;
            for (int j = 0; j <A.size(); j++) {
                if(checkBit(i,j)){
                    sum +=A.get(j);
                    if(prime.contains(A.get(j)))    hasPrime=true;
                }
            }
            if(hasPrime && sum>=B && sum<=C)    ans++;
        }
        return ans;
    }

    public static int fantastic4(ArrayList<Integer> A) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        hm.put(1,0);
        hm.put(2,0);
        hm.put(3,0);
        for(int i=0; i<A.size(); i++){
            if( (A.get(i)%4) !=0 ){
                int r = A.get(i)%4;
                hm.put(r,hm.get(r)+1);

            }
        }
        int ans =0;
        if(hm.get(1)<hm.get(3)){
            ans = hm.get(1);
            hm.put(3,hm.get(3)-hm.get(1));
            hm.put(1,0);
        }else{
            ans = hm.get(3);
            hm.put(1,hm.get(1)-hm.get(3));
            hm.put(3,0);
        }
        ans += hm.get(1)/2 + hm.get(3)/2;
        hm.put(2,hm.get(2)+(hm.get(1)/2)+(hm.get(3)/2));
        hm.put(1, hm.get(1)%2);
        hm.put(3, hm.get(3)%2);
        if(hm.get(1)!=hm.get(3) || hm.get(2)%2!=0){
            return -1;
        }else{
            return ans + hm.get(2)/2;
        }
    }

    private static long fun(long a, long b, long c){
        long result=-1;
        for (long i = c; i >=(c-b); i--) {
            if(i%a==b){
//                System.out.println(i);
                result=i;
                return result;
            }
        }
        return result;
    }
    private static int magic(int N, int[] A, int[] B){
        int r = 0;
        int M = 1000000007;
        for (int i = 0; i < A.length; i++) {
            long m = ((long) A[i] *(long)B[i])%M;
            r= (int) ((r+m)%M);
        }
        return (r/2);
    }


    public static String twoSwap(ArrayList<Integer> A) {
        int c=0,d=0;
        ArrayList<Integer> min = minArray(A);
        for(int i=0; i<A.size(); i++){
            if( A.get(i)!=min.get(i) ){
                int j=i+1;
                while(j<A.size() && A.get(j)!=min.get(i)) j++;
                min = swap(A,i,j);
                c++;
                if(c>2) break;
            }
        }
        for (int i = 1; i <A.size(); i++) {
            if(A.get(i-1)==A.get(i)) d++;
        }
        if(c==2 || (c==1 && d>0)){
            return "Yes";
        }else{
            return "No";
        }
    }

    public static ArrayList<Integer> minArray(ArrayList<Integer> A){
        int minValue = Integer.MAX_VALUE;
        ArrayList<Integer> min = new ArrayList<Integer>(Collections.nCopies(A.size(),0));
        for(int i=A.size()-1; i>=0; i--){
            minValue = Math.min(minValue, A.get(i));
            min.set(i,minValue);
        }
        return min;
    }

    public static ArrayList<Integer> swap (ArrayList<Integer> A, int i, int j){
        int t = A.get(i);
        A.set(i,A.get(j));
        A.set(j,t);
        return minArray(A);
    }
}
