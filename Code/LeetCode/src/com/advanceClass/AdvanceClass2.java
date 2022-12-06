package com.advanceClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class AdvanceClass2 {
    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
//        int B = sc.nextInt();
        /*ArrayList<Integer> A = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            A.add(sc.nextInt());
        }*/
//        int[][] matrix = new int[N][M];
        ArrayList<ArrayList<Integer>> mat = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < M; j++) {
                row.add(sc.nextInt());
            }
            mat.add(row);
        }

        System.out.println(maxSubMatSubSorted(mat));
    }

    private static void prefixSumMatrix(ArrayList<ArrayList<Integer>> mat){
        int M = 1000000007;
        for (int i = 0; i < mat.size(); i++) {
            for (int j = 0; j < mat.get(0).size(); j++) {
                int temp = mat.get(i).get(j);
                temp = (temp<0)?(M+temp):temp%M;
                if(j==0)    mat.get(i).set(j,temp);
                else    mat.get(i).set(j,(mat.get(i).get(j-1)+temp)%M);
            }
        }
        for (int i = 0; i < mat.get(0).size(); i++) {
            for (int j = 0; j < mat.size(); j++) {
                int temp = mat.get(j).get(i);
                temp = (temp<0)?(M+temp):temp%M;
                if(j==0)    mat.get(j).set(i,temp);
                else    mat.get(j).set(i,(mat.get(j-1).get(i)+temp)%M);
            }
        }
    }

    public static ArrayList<Integer> subMatSum(ArrayList<ArrayList<Integer>> A, ArrayList<Integer> B, ArrayList<Integer> C, ArrayList<Integer> D, ArrayList<Integer> E) {
        prefixSumMatrix(A);
        ArrayList<Integer> ans = new ArrayList<>();
        int M = 1000000007;
        for (int i = 0; i < B.size(); i++) {
            int a1 = B.get(i)-1;
            int b1 = C.get(i)-1;
            int a2 = D.get(i)-1;
            int b2 = E.get(i)-1;
            int bottom =A.get(a2).get(b2);
            int top = (a1>0&&b1>0)?A.get(a1-1).get(b1-1):0;
            int upper = (a1>0)?A.get(a1-1).get(b2):0;
            int side = (b1>0)?A.get(a2).get(b1-1):0;
            long qAns = ((bottom + top)%M - (upper + side)%M)%M;
            if(qAns<0) qAns +=M;
            else    qAns %=M;
            ans.add((int)(qAns));
        }
        return ans;
    }

    public static int sumOfAllSubMat(ArrayList<ArrayList<Integer>> A){
        int sum =0;
        int N = A.size();
        int M = A.get(0).size();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sum += A.get(i).get(j)* (i+1)*(j+1)*(N-i)*(M-j);
            }
        }
        return sum;
    }


    public static int firstPositiveMissing(ArrayList<Integer> A){
        for (int i = 0; i < A.size();) {
            if(A.get(i)<1)  A.remove(i);
            else i++;
        }
        int N = A.size();
        for (int i = 0; i < A.size(); i++) {
            if(A.get(i)>0 && A.get(i)<N){
                A.set(A.get(i),-A.get(i));
            }
        }
        System.out.println(A);
        return 0;
        //Swapping technic
        /*for (int i = 0; i < A.size(); i++) {
            while (A.get(i)>0 && A.get(i)<=A.size() && A.get(i)!=(i+1)){
                if(Objects.equals(A.get(i), A.get(A.get(i) - 1)))   break;
                swap(A,A.get(i)-1,i);
            }
        }

        int ans =A.size()+1;
        for (int i = 0; i < A.size(); i++) {
            if(A.get(i)!=i+1){
                ans = i+1;
                break;
            }
        }
        return ans;*/
    }
    public static void swap(ArrayList<Integer> A, int i, int j){
        int temp = A.get(j);
        A.set(j,A.get(i));
        A.set(i,temp);
    }

    public class AInterval {
      int start;
      int end;
      AInterval() { start = 0; end = 0; }
      AInterval(int s, int e) { start = s; end = e; }
    }
    public static ArrayList<AInterval> insert(ArrayList<AInterval> intervals, AInterval newInterval) {
        ArrayList<AInterval> ans = new ArrayList<>();
        for (int i=0; i<intervals.size(); i++) {
            if(newInterval.start> intervals.get(i).end ){
                ans.add(intervals.get(i));
            }else if(intervals.get(i).start> newInterval.end){
                ans.add(newInterval);
                for (int j = i; j < intervals.size(); j++) {
                    ans.add(intervals.get(j));
                }
                return ans;
            }else {
                newInterval.start = Math.min(newInterval.start,intervals.get(i).start);
                newInterval.end = Math.max(newInterval.end,intervals.get(i).end);
            }
        }
        ans.add(newInterval);
        return ans;
    }

    public static ArrayList<AInterval> merge(ArrayList<AInterval> intervals){
        intervals.sort((i1,i2)->{
            if(i1.start!=i2.start)
                return (i1.start<i2.start)?-1:1;
            else if(i1.end!=i2.end)
                return (i1.end<i2.end)?-1:1;
            return 0;
        });
        ArrayList<AInterval> ans = new ArrayList<>();
        AInterval newInterval = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            if(newInterval.end < intervals.get(i).start){
                ans.add(newInterval);
                newInterval = intervals.get(i);
            }else if(newInterval.end>=intervals.get(i).start){
                newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
                newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
            }
        }
        ans.add(newInterval);
        return ans;
    }

    public static int maxSubMatSum(ArrayList<ArrayList<Integer>> A) {
        int n= A.size();
        int m = A.get(0).size();
        int max = A.get(0).get(0);
        for (int k = 0; k < n; k++) {
            ArrayList<Integer> rs = new ArrayList<>(Collections.nCopies(m,0));
            for (int i = k; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    rs.set(j, rs.get(j) + A.get(i).get(j));
                }
                max = Math.max(max, kadane(rs));
            }
        }
        return max;
    }
    public static int kadane(ArrayList<Integer> A){
        int max = A.get(0);
        int sum=A.get(0);
        for (int i = 1; i < A.size(); i++) {
            sum=  Math.max(A.get(i),sum+A.get(i));
            max = Math.max(max,sum);
        }
        return max;
    }

    public static int maxSubSqrMatSum(ArrayList<ArrayList<Integer>> A, int B) {
        int n= A.size();
        int max = Integer.MIN_VALUE;
        ArrayList<Integer> rs = new ArrayList<>(Collections.nCopies(n,0));
        for (int i = 0; i < B; i++) {
            for (int j = 0; j < n; j++) {
                rs.set(j, rs.get(j) + A.get(i).get(j));
            }
        }
        max = Math.max(max,slideWindowMax(rs,B));
        for (int i = B; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rs.set(j, rs.get(j) + A.get(i).get(j) - A.get(i-B).get(j));
            }
            max = Math.max(max, slideWindowMax(rs,B));
        }
        return max;
    }
    public static int slideWindowMax(ArrayList<Integer> A, int w){
        int sum = 0;
        int max =Integer.MIN_VALUE;
        for (int i = 0; i < w; i++) {
            sum+=A.get(i);
        }
        max = Math.max(max,sum);
        for (int i = w; i < A.size(); i++) {
            sum +=A.get(w) - A.get(i-w);
            max = Math.max(max,sum);
        }
        return max;
    }

    public static int maxArr(ArrayList<Integer> A) {
        if(A.size()==1) return 0;
        int max1 =Integer.MIN_VALUE;
        int min1 =Integer.MAX_VALUE;
        int max2 =Integer.MIN_VALUE;
        int min2 =Integer.MAX_VALUE;
        for (int i = 0; i < A.size(); i++) {
            max1 = Math.max(max1,A.get(i)+(i+1));
            min1 = Math.min(min1,A.get(i)+(i+1));
            max2 = Math.max(max2,A.get(i)-(i+1));
            min2 = Math.min(min2,A.get(i)-(i+1));
        }
        return Math.max(max1-min1, max2-min2);
    }

    public static int pickFromBothSide(ArrayList<Integer> A, int B){
        ArrayList<Integer> ans = new ArrayList<>();
        int max=0;
        for (int i = 0; i < B; i++) {
            ans.add(A.get(i));
            max+=A.get(i);
        }
        int sum = max;
        int n = ans.size()-1;
        int i=1;
        while (n>=0){
            ans.set(n,A.get(A.size()-i));
            sum += A.get(A.size()-i) - A.get(n);
            max = Math.max(max,sum);
            i++;
            n--;
        }
        return max;
    }

    public static int minimumSwap(ArrayList<Integer> A,int B)   {
        int c =0;
        for (Integer elm : A)   if (elm <= B)   c++;
        int badElm = 0, ans=Integer.MAX_VALUE;
        for (int i = 0; i < c; i++) {
            if(A.get(i)>B) badElm++;
        }
        ans = badElm;
        for (int i = c; i < A.size(); i++) {
            if(A.get(i)>B) badElm++;
            if(A.get(i-c)>B)  badElm--;
            ans = Math.min(ans,badElm);
        }
        return ans;
    }

    public static int searchInSortedMat(ArrayList<ArrayList<Integer>> A, int B){
        int n =A.size();
        int m = A.get(0).size();
        int i=0,j=m-1;
        while (i<n && j>=0){
            if(A.get(i).get(j)==B){
                if(j!=0 && A.get(i).get(j-1)==B){
                    j--;
                }else   return (i+1)*1009+(j+1);
            }else if(A.get(i).get(j)<B){
                i++;
            }else{
                j--;
            }
        }
        return -1;
    }

    public static int maxChunk(ArrayList<Integer> A){
        int c=0, max=Integer.MIN_VALUE;
        for (int i = 0; i < A.size(); i++) {
            max = Math.max(max,A.get(i));
            if(max==i){
                c++;
            }
        }
        return c;
    }

    public static Long maxSubMatSubSorted(ArrayList<ArrayList<Integer>> A){
        int r = A.size();
        int c = A.get(0).size();
        ArrayList<ArrayList<Long>> mat = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            ArrayList<Long> col = new ArrayList<>(Collections.nCopies(c,0L));
            mat.add(col);
        }
        //row suffix
        for (int i = 0; i < r; i++) {
            for (int j = c-1; j >=0; j--) {
                if(j==c-1) mat.get(i).set(j, Long.valueOf(A.get(i).get(j)));
                else{
                    mat.get(i).set(j,mat.get(i).get(j+1)+A.get(i).get(j));
                }
            }
        }

        //Col suffix
        for (int i = 0; i <c; i++) {
            for (int j = r-1; j >= 0; j--) {
                if(j==r-1) mat.get(j).set(i,mat.get(j).get(i));
                else {
                    mat.get(j).set(i, mat.get(j+1).get(i)+mat.get(j).get(i));
                }
            }
        }
        System.out.println(mat);
        Long max = mat.get(0).get(0);
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                max = Math.max(max,mat.get(i).get(j));
            }
        }
        return max;
    }
}
