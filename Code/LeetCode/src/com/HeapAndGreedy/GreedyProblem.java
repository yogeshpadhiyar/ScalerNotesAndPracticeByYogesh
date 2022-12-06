package com.HeapAndGreedy;

import java.util.*;

public class GreedyProblem {
    class Pair<T,T1>{
        T t;
        T1 t1;
        public Pair(T t, T1 t1){
            this.t=t;
            this.t1=t1;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String A = sc.nextLine();
        int n = sc.nextInt();
//        ArrayList<Integer> A = new ArrayList<>();
//        ArrayList<Integer> B = new ArrayList<>();
        /*for (int i = 0; i < n; i++) {
            A.add(sc.nextInt());
        }*/
//        for (int i = 0; i < n; i++) {
//            B.add(sc.nextInt());
//        }
        GreedyProblem gp = new GreedyProblem();
        System.out.println(gp.binaryString(A,n));
    }

    public int binaryString(String A, int B) {
        int n =A.length();
        int[] temp = new int[n];
        int xor=0, ans=0;
        for (int i = 0; i <=n-B; i++) {
            xor ^= temp[i];
            if((xor==0 && A.charAt(i)=='0') || (xor==1 && A.charAt(i)=='1')){
                ans++;
                xor ^=1;
                if(i+B<n)   temp[i+B]=1;
            }
        }
        for (int i = n-B+1; i < n; i++) {
            xor^=temp[i];
            if((xor==0 && A.charAt(i)=='0') || (xor==1 && A.charAt(i)=='1'))    return -1;
        }
        return ans;
    }

    //TODO: learning pending
    public int seats(String A){
        int M = 10000003;
        ArrayList<Integer> pos = new ArrayList<>();
        int count=0;
        int len = A.length();
        for (int i = 0; i < len; i++) {
            if(A.charAt(i)=='x'){
                pos.add(i-count);
                count++;
            }
        }
        if(count==len || count==0) return 0;
        int med_index = (count-1)/2;
        int med_val = pos.get(med_index);
        int ans = 0;
        for (int i = 0; i < pos.size(); i++) {
            ans = (ans%M+ Math.abs(pos.get(i)-med_val)%M)%M;
        }
        return ans%M;
    }

    public int coinProblemWithPower5(int A) {
        int ans=0;
        while (A!=0){
            int p = (int)(Math.log(A)/Math.log(5));
            A -= Math.pow(5,p);
            ans++;
        }
        return ans;
    }

    public int candy(ArrayList<Integer> A) {
        int ans=0;
        ArrayList<Integer> c = new ArrayList<>();
        ArrayList<Integer> cr = new ArrayList<>(Collections.nCopies(A.size(),1));
        c.add(1);
        for (int i = 1; i < A.size(); i++) {
            if(A.get(i-1)<A.get(i)){
                c.add(1+c.get(i-1));
            }else   c.add(1);
        }
        for (int i = A.size()-1; i >0; i--) {
            if(A.get(i-1)>A.get(i)){
                cr.set(i-1,1+cr.get(i));
            }
        }
        for (int i = 0; i < c.size(); i++) {
            ans += Math.max(c.get(i),cr.get(i));
        }
        return ans;
    }

    public int mice(ArrayList<Integer> A, ArrayList<Integer> B) {
        Collections.sort(A);
        Collections.sort(B);
        int max=0;
        for (int i = 0; i < A.size(); i++) {
            max = Math.max(max,Math.abs(A.get(i)-B.get(i)));
        }
        return max;
    }

    public int freeCar(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Pair<Integer,Integer>> carTimeAndCProfit = new ArrayList<>();
        for (int i = 0; i < A.size(); i++) {
            carTimeAndCProfit.add(new Pair(A.get(i),B.get(i)));
        }
        carTimeAndCProfit.sort((a,b)->(!Objects.equals(a.t, b.t))?a.t-b.t:b.t1-a.t1);
        PriorityQueue<Integer> min_heap = new PriorityQueue<>();
        for (int i = 0; i < carTimeAndCProfit.size(); i++) {
            Pair<Integer,Integer> p = carTimeAndCProfit.get(i);
            int time = p.t-1;
            int profit = p.t1;
            if(time>=min_heap.size()){
                min_heap.add(profit);
            }else if(min_heap.peek()<profit){
                min_heap.poll();
                min_heap.add(profit);
            }
        }
        int M = 1000000009;
        long ans=0;
        while (min_heap.size()>0){
            ans = (ans+min_heap.poll())%M;
        }
        ans= (ans+M)%M;
        return (int)ans;
    }

    public int finishMaxJob(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Pair<Integer,Integer>> activity = new ArrayList<>();
        for (int i = 0; i < A.size(); i++) {
            activity.add(new Pair(A.get(i),B.get(i)));
        }
        activity.sort((a,b)-> a.t1-b.t1);
        int endTime=0,ans=0;
        for (int i = 0; i < activity.size(); i++) {
            if(activity.get(i).t>=endTime){
                ans++;
                endTime=activity.get(i).t1;
            }
        }
        return ans;
    }
}
