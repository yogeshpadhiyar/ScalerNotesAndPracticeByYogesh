package com.advanceClass;

import com.Arrays.ArrayAll;

import java.util.*;

import static com.advanceClass.AdvanceClass2.swap;

public class SortingAll {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

//        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(14046, 57239, 78362, 99387, 27609, 55100, 65536, 62099, 40820, 33056, 88380, 78549, 57512, 33137, 81212, 32365, 42276, 65368, 52459, 74924, 25355, 76044, 78056, 45190, 94365, 58869, 20611));
        ArrayList<Integer> A = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            A.add(sc.nextInt());
        }
//        int S = sc.nextInt();
//        int M = sc.nextInt();
//        int E = sc.nextInt();l
        /*ArrayList<Integer> B = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            B.add(sc.nextInt());
        }*/
//        mergeSort(A,S,E);
        System.out.println(maxUnSortSubArray(A));
    }

    private static int kthSmallest(final List<Integer> A, int B){
        ArrayList<Integer> ans = new ArrayList<>(A);
        for (int i = 0; i <B; i++) {
            int minIndex=i, minVal = ans.get(i);
            for (int j = i; j < ans.size(); j++) {
                if(ans.get(j)<minVal){
                    minIndex = j;
                    minVal = ans.get(j);
                }
            }
            int temp = ans.get(i);
            ans.set(i,ans.get(minIndex));
            ans.set(minIndex,temp);
        }
        return ans.get(B-1);
    }

    private static ArrayList<Integer> merge(ArrayList<Integer> A, ArrayList<Integer> B) {
        int n = A.size();
        int m = B.size();
        int i=0,j=0;
        ArrayList<Integer> ans = new ArrayList<>();
        while (i<n && j<m){
            if(A.get(i)<=B.get(j)){
                ans.add(A.get(i));
                i++;
            }else{
                ans.add(B.get(j));
                j++;
            }
        }
        if(i!=n) {
            for (int k = i; k < n; k++) {
                ans.add(A.get(k));
            }
        }
        if(j!=m) {
            for (int k = j; k < m; k++) {
                ans.add(B.get(k));
            }
        }
        return ans;
    }

    private static void mergeInSide(ArrayList<Integer> A, int S, int M, int E){
        ArrayList<Integer> temp = new ArrayList<>();
        int i = S, j = M+1;
        while (i<=M && j<=E){
            if(A.get(i)<=A.get(j)){
                temp.add(A.get(i));
                i++;
            }else{
                temp.add(A.get(j));
                j++;
            }
        }
        if(i<=M){
            for (int k = i; k <=M; k++) {
                temp.add(A.get(k));
            }
        }
        if(j<=E){
            for (int k = j; k <=E; k++) {
                temp.add(A.get(k));
            }
        }

        for (int k = S; k <=E; k++) {
            A.set(k,temp.get(k-S));
        }
    }

    private static void mergeSort(ArrayList<Integer> A, int S, int E){
        if(S==E) return;
        int M = (S+E)/2;
        mergeSort(A,S,M);
        mergeSort(A,M+1,E);
        mergeInSide(A,S,M,E);
    }

    public static int chocolateDis(ArrayList<Integer> A, int B){
        if(B==0||A.size()==0||A.size()<B) return 0;
        Collections.sort(A);
        ArrayList<Integer> t = new ArrayList<>();
        int ans =Integer.MAX_VALUE;
        int min,max;
        for (int i=0, j=B-1; i<A.size()- B; i++, j++) {
            min = A.get(i);
            max = A.get(j);
            ans = Math.min(ans,(max-min));
        }
        return ans;
    }

    //pair of A[i]>B[j]
    private static int pairGreaterElm(ArrayList<Integer> A, ArrayList<Integer> B){
        Collections.sort(A);
        Collections.sort(B);
        int n = A.size();
        int m = B.size();
        int i=0,j=0,ans=0;
        while (i<n && j<m){
            if(A.get(i)>B.get(j)){
                ans += n-i;
                j++;
            }else i++;
        }
        return ans;
    }


    private static int inversionAlgo(ArrayList<Integer> A, int s, int e){
        int M = 1000000007;
        if(s==e) return 0;
        int m = (s+e)/2;
        int L = inversionAlgo(A,s,m);
        int R = inversionAlgo(A,m+1,e);
        int C = mergeInv(A,s,m,e);
        return ((L+R)%M+C)%M;
    }

    private static int mergeInv(ArrayList<Integer> A, int s, int m, int e){
        int M = 1000000007;
        ArrayList<Integer> t = new ArrayList<>();
        long c=0L;
        int p1 = s, p2 = m+1;
        while (p1<=m && p2<=e){
            if(A.get(p1)<=A.get(p2)){
                t.add(A.get(p1));
                p1++;
            }else{
                t.add(A.get(p2));
                p2++;
                c = (c + (m+1 - p1))%M;
            }
        }
        if(p1<=m){
            for (int i = p1; i <=m; i++) {
                t.add(A.get(i));
            }
        }
        if(p2<=e){
            for (int i = p2; i <=e; i++) {
                t.add(A.get(i));
            }
        }
        for (int i = s; i <=e; i++) {
            A.set(i,t.get(i-s));
        }
        c = (c+M)%M;
        return (int)c;
    }

    private static int makeUniqueNo(ArrayList<Integer> A){
        Collections.sort(A);
        int lastElm = A.get(0);
        int c =0;
        for (int i = 1; i < A.size(); i++) {
            if(A.get(i)<=lastElm){
                int add =  (lastElm-A.get(i))+1;
                lastElm = A.get(i)+add;
                c +=add;
            }else
                lastElm = A.get(i);
        }
        return c;
    }

    //Given an array of integers A, we call (i, j) an important reverse pair if i < j and A[i] > 2*A[j].
    private static int reversePairs(ArrayList<Integer> A, int s, int e){
        if(s==e) return 0;
        int m = (s+e)/2;
        int L = reversePairs(A,s,m);
        int R = reversePairs(A,m+1,e);
        int C = mergeDecOrder(A,s,m,e);
        return (L+R+C);
    }

    private static int getPairCount(ArrayList<Integer> A, int s, int m, int e){
        int c =0;
        int p1 = s, p2 = m+1;
        while (p1<=m && p2<=e){
            if(A.get(p1)>2*(long)A.get(p2)) {
                c += (m + 1 - p1);
                p2++;
            }else   p1++;
        }
        return c;
    }
    private static int mergeDecOrder(ArrayList<Integer> A, int s, int m, int e) {
        ArrayList<Integer> t = new ArrayList<>();
        int c =0;
        int p1 = s, p2 = m+1;
        c = getPairCount(A,s,m,e);
        while (p1<=m && p2<=e){
            if(A.get(p1)<=A.get(p2)){
                t.add(A.get(p1));
                p1++;
            }else{
                t.add(A.get(p2));
                p2++;
            }
        }
        if(p1<=m){
            for (int i = p1; i <=m; i++) {
                t.add(A.get(i));
            }
        }
        if(p2<=e){
            for (int i = p2; i <=e; i++) {
                t.add(A.get(i));
            }
        }
        for (int i = s; i <=e; i++) {
            A.set(i,t.get(i-s));
        }
        return c;
    }

    private static ArrayList<ArrayList<Integer>> BClosestPointsToOrigin(ArrayList<ArrayList<Integer>> A, int B) {
        A.sort((t1,t2)->{
            long ans1= (long) (Math.pow(t1.get(0),2)+Math.pow(t1.get(1),2));
            long ans2= (long) (Math.pow(t2.get(0),2)+Math.pow(t2.get(1),2));
            if(ans1!=ans2)
                return (ans1<ans2)?-1:1;
            return (t1.get(0)<t2.get(0))?-1:1;
        });
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (int i = 0; i <B; i++) {
            ans.add(A.get(i));
        }
        return ans;
    }

    public static int reArrangeByPivot(ArrayList<Integer> A, int s, int e){
        int pivot = A.get(s);
        int i=s+1,j=e;
        while (i<=j){
            if(pivot>=A.get(i)) i++;
            else if(pivot<A.get(j)) j--;
            else {
                swap(A, i, j);
                i++;j--;
            }

        }
        swap(A,s,j);
//        System.out.println(A);
        return j;
    }

    private static void quickSort(ArrayList<Integer> A,int s, int e){
        if(s>=e) return;
        int i = reArrangeByPivot(A,s,e);
        quickSort(A,s,i-1);
        quickSort(A,i+1,e);
    }

    private static ArrayList<Integer> maxMinMagic(ArrayList<Integer> A){
        Collections.sort(A);
        int M = 1000000007;
        long max=0,min=0;
        for (int i = 0,j=A.size()-1; i <A.size()/2; i++,j--) {
            max = (max + (Math.abs(A.get(i)-A.get(j)))%M)%M;
        }
        for (int i = 0; i <A.size(); i+=2) {
            min = (min + (Math.abs(A.get(i)-A.get(i+1)))%M)%M;
        }
        return new ArrayList(Arrays.asList(max,min));
    }

    private static ArrayList<Integer> maxUnSortSubArray(ArrayList<Integer> A){
        int n = A.size();
        int p1=0,p2=n-1;

        while (p1<n-1 && A.get(p1)<=A.get(p1+1))    p1++;
        while (p2>0 && A.get(p2-1)<=A.get(p2))    p2--;

        //p1==n-1 means p1 pointer at last elm.
        if(p1==n-1) return new ArrayList<>(Arrays.asList(-1));

        int min = A.get(p1+1);
        int max = A.get(p1+1);
        for (int i = p1+1; i <=p2; i++) {
            min = Math.min(min,A.get(i));
            max = Math.max(max,A.get(i));
        }
        int l=0,r = n-1;

        while (A.get(l)<=min && l<=p1)  l++;
        while (A.get(r)>=max && r>=p2)  r--;
        return new ArrayList<>(Arrays.asList(l,r));
    }
}
