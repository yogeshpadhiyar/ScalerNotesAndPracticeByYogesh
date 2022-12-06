package com.BackTracking;

import java.util.*;

public class BackTrackingProblem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
//        String tem = sc.nextLine();
        int B = sc.nextInt();
        ArrayList<Integer> A = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            A.add(sc.nextInt());
        }
        BackTrackingProblem bt = new BackTrackingProblem();
        System.out.println(bt.combinationSumII(A,B));
    }

    public ArrayList<ArrayList<Integer>> combinationSumII(ArrayList<Integer> A, int B) {
        Set<ArrayList<Integer>> ans = new TreeSet<>((a1,a2)->{
            ArrayList<Integer> o1 = (ArrayList<Integer>) a1;
            ArrayList<Integer> o2 = (ArrayList<Integer>) a2;
            for (int i = 0; i < Math.min(o1.size(), o2.size()); i++) {
                int c = o1.get(i).compareTo(o2.get(i));
                if (c != 0) {
                    return c;
                }
            }
            return Integer.compare(o1.size(), o2.size());
        });
        ArrayList<Integer> curr = new ArrayList<>();
        Collections.sort(A);
        combinationSumIIHelper(A, ans, curr, B, 0,0);
        return new ArrayList<>(ans);
    }
    private void combinationSumIIHelper(ArrayList<Integer> set, Set<ArrayList<Integer>> ans,
                                      ArrayList<Integer> curr, int B, int currSum, int j) {
        if(currSum>B)   return;
        if(currSum==B) {
            ArrayList<Integer> temp = new ArrayList<>(curr);
            Collections.sort(temp);
            ans.add(temp);
            return;
        }
        if(j==set.size())   return;
        curr.add(set.get(j));
        currSum+=set.get(j);
        combinationSumIIHelper(set,ans,curr,B,currSum,j+1);
        curr.remove(curr.size()-1);
        currSum-=set.get(j);
        combinationSumIIHelper(set,ans,curr,B,currSum,j+1);
    }

    public ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> A, int B){
        Set<ArrayList<Integer>> ans = new TreeSet<>((a1,a2)->{
            ArrayList<Integer> o1 = (ArrayList<Integer>) a1;
            ArrayList<Integer> o2 = (ArrayList<Integer>) a2;
            for (int i = 0; i < Math.min(o1.size(), o2.size()); i++) {
                int c = o1.get(i).compareTo(o2.get(i));
                if (c != 0) {
                    return c;
                }
            }
            return Integer.compare(o1.size(), o2.size());
        });
        ArrayList<Integer> curr = new ArrayList<>();
        ArrayList<Integer> set = new ArrayList<>();
        Collections.sort(A);
        for (int i = 0; i < A.size(); i++) {
            if(i==0|| !Objects.equals(A.get(i), A.get(i - 1))){
                set.add(A.get(i));
            }
        }
        combinationSumHelper(set, ans, curr, B, 0);
        return new ArrayList<>(ans);
    }

    private void combinationSumHelper(ArrayList<Integer> set, Set<ArrayList<Integer>> ans,
                                                               ArrayList<Integer> curr, int B, int currSum) {
        if(currSum>B)   return;
        if(currSum==B) {
            ArrayList<Integer> temp = new ArrayList<>(curr);
            Collections.sort(temp);
            ans.add(temp);
            return;
        }
        for (int i = 0; i < set.size(); i++) {
            curr.add(set.get(i));
            currSum+=set.get(i);
            combinationSumHelper(set,ans,curr,B,currSum);
            curr.remove(curr.size()-1);
            currSum-=set.get(i);
        }
    }


    public void solveSudoku(ArrayList<ArrayList<Character>> a) {
        ArrayList<ArrayList<Integer>> ref = new ArrayList<>();
        for (ArrayList<Character> t : a){
            ArrayList<Integer> temp = new ArrayList<>();
            for(char ch : t) {
                if(ch=='.') temp.add(0);
                else    temp.add(Integer.parseInt(ch + ""));
            }
            ref.add(temp);
        }
        sudokuSolver(a, ref,0);
        for (ArrayList<Character> c :a){
            System.out.println(c);
        }
    }
    private void sudokuSolver(ArrayList<ArrayList<Character>> a, ArrayList<ArrayList<Integer>> ref, int i) {
        if(i==81) {
            int BASE = 10,r=0;
            for (ArrayList<Integer> characters : ref) {
                ArrayList<Character> temp= new ArrayList<>();
                for (Integer integer:characters)    temp.add(Character.forDigit(integer,BASE));
                a.set(r,temp);
                r++;
            }
            return;
        }
        int r = i/9;
        int c = i%9;
        if(ref.get(r).get(c).equals(0)){
            for (int k = 1; k < 10; k++) {
                if(checkAbleToPlaceDigit(ref,r,c,k)){
                    ref.get(r).set(c, k);
                    sudokuSolver(a, ref,i+1);
                    ref.get(r).set(c,0);
                }
            }
        }else sudokuSolver(a,ref,i+1);
    }
    private boolean checkAbleToPlaceDigit(ArrayList<ArrayList<Integer>> a, int i, int j, int k) {
        int c=0;
        while (c<9){
            if(a.get(i).get(c)== k)  return false;
            c++;
        }
        int r=0;
        while (r<9){
            if(a.get(r).get(j)== k)  return false;
            r++;
        }
        r= (i/3) *3;
        c= (j/3) *3;
        for (int l = r; l < r + 3; l++) {
            for (int m = c; m < c + 3; m++) {
                if(a.get(l).get(m)== k)  return false;
            }
        }
        return true;
    }

    public ArrayList<ArrayList<String>> solveNQueens(int A) {
        ArrayList<ArrayList<String>> ans = new ArrayList<>();
        ArrayList<ArrayList<Integer>> ref = new ArrayList<>();
        for (int i = 0; i < A; i++) {
            ArrayList<Integer> t = new ArrayList<>(Collections.nCopies(A,0));
            ref.add(t);
        }
        solveNQueensHelper(A, ans, ref, 0);
        return ans;
    }
    private void solveNQueensHelper(int A, ArrayList<ArrayList<String>> ans, ArrayList<ArrayList<Integer>> ref, int i) {
        if(i==A){
            ArrayList<String> temp = new ArrayList<>();
            for (int j = 0; j < A; j++) {
                StringBuffer sb = new StringBuffer();
                for (int k = 0; k < A; k++) {
                    if(ref.get(j).get(k)==1)    sb.append('Q');
                    else sb.append('.');
                }
                temp.add(sb.toString());
            }
            ans.add(temp);
            return;
        }
        for (int j = 0; j < A; j++) {
            if(checkAbleToPlace(ref, A, i, j)){
                ref.get(i).set(j,1);
                solveNQueensHelper(A, ans, ref, i+1);
                ref.get(i).set(j,0);
            }
        }
    }
    private boolean checkAbleToPlace(ArrayList<ArrayList<Integer>> ref, int A, int i, int j) {
        int r=0;
        while (r<i){
            if(ref.get(r).get(j)==1)   return false;
            r++;
        }
        r=i;
        int c=j;
        while (r>=0 && c>=0){
            if(ref.get(r).get(c)==1)   return false;
            r--;c--;
        }
        r=i;
        c=j;
        while (r>=0 && c<A){
            if(ref.get(r).get(c)==1)   return false;
            r--;c++;
        }
        return true;
    }

    public ArrayList<ArrayList<Integer>> ratInMaze(ArrayList<ArrayList<Integer>> A) {
        int n = A.size();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<ArrayList<Integer>> ref = new ArrayList<>();
        if(A.get(0).get(0)!=1 || A.get(n-1).get(n-1)!=1)    return ref;
        for (int i = 0; i < n; i++) {
            ref.add(new ArrayList<>(Collections.nCopies(n,0)));
        }
        ref.get(0).set(0,1);
        ref.get(n-1).set(n-1,1);
        ratInMazePathFinder(A, ans, ref, A.size(), 0, 0);
        return ans;
    }
    private void ratInMazePathFinder(ArrayList<ArrayList<Integer>> A, ArrayList<ArrayList<Integer>> ans, ArrayList<ArrayList<Integer>> ref, int n, int i, int j) {
        if(i==n-1&&j==n-1 && A.get(i).get(j)==1){
            for (int k = 0; k < n; k++) {
                ans.add(new ArrayList<>(ref.get(k)));
            }
            return;
        }
        if(j+1<n && i<n && A.get(i).get(j+1)==1){
            ref.get(i).set(j+1,1);
            ratInMazePathFinder(A, ans, ref, n, i, j+1);
            ref.get(i).set(j+1,0);
        }
        if(i+1<n && j<n && A.get(i+1).get(j)==1){
            ref.get(i+1).set(j,1);
            ratInMazePathFinder(A, ans, ref, n, i+1, j);
            ref.get(i+1).set(j,0);
        }
    }

    public ArrayList<ArrayList<Integer>> permuteUnique(ArrayList<Integer> A) {
        Set<ArrayList<Integer>> ans = new HashSet<>();
        permutationGenerator(A, ans, A.size(), 0);
        return new ArrayList<>(ans);
    }

    /*public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> A) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        permutationGenerator(A, ans, A.size(), 0);
        return ans;
    }*/
    private void permutationGenerator(ArrayList<Integer> A, Set<ArrayList<Integer>> ans, int N, int i) {
        if(i==N-1){
            ans.add(new ArrayList<>(A));
            return;
        }
        for (int j = i; j < N; j++) {
            swap(A,i,j);
            permutationGenerator(A, ans, N, i+1);
            swap(A,i,j);
        }
    }
    public void swap (ArrayList<Integer> A, int i, int j){
        int t = A.get(i);
        A.set(i,A.get(j));
        A.set(j,t);
    }

    public ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> A) {
        Collections.sort(A);
        Set<ArrayList<Integer>> subSets = new TreeSet<>((a1,a2)->{
            ArrayList<Integer> o1 = (ArrayList<Integer>) a1;
            ArrayList<Integer> o2 = (ArrayList<Integer>) a2;
            for (int i = 0; i < Math.min(o1.size(), o2.size()); i++) {
                int c = o1.get(i).compareTo(o2.get(i));
                if (c != 0) {
                    return c;
                }
            }
            return Integer.compare(o1.size(), o2.size());
        });
        ArrayList<Integer> subset = new ArrayList<>();
        subsetsHelper(A, subSets, subset, 0);
        /*Collections.sort(subSets, (a1,a2)->{
            ArrayList<Integer> o1 = (ArrayList<Integer>) a1;
            ArrayList<Integer> o2 = (ArrayList<Integer>) a2;
            for (int i = 0; i < Math.min(o1.size(), o2.size()); i++) {
                int c = o1.get(i).compareTo(o2.get(i));
                if (c != 0) {
                    return c;
                }
            }
            return Integer.compare(o1.size(), o2.size());
        });*/
        return new ArrayList<>(subSets);
    }
    private void subsetsHelper(ArrayList<Integer> A, Set<ArrayList<Integer>> subSets, ArrayList<Integer> subset, int i){
        if(i==A.size()){
            subSets.add(new ArrayList<>(subset));
            return;
        }
        subset.add(A.get(i));
        subsetsHelper(A,subSets,subset,i+1);
        subset.remove(subset.size()-1);
        subsetsHelper(A,subSets,subset,i+1);
    }

    private int SIXLETS(ArrayList<Integer> A, int B) {
        return SIXLETSHelper(A, B, 0, 0, 0);
    }
    private int SIXLETSHelper(ArrayList<Integer> A, int B, int currSum, int i, int size) {
        if(i==A.size()) {
            if(size==B && currSum<=1000)    return 1;
            else return 0;
        }
        int ans =0;
        currSum += A.get(i);
        ans += SIXLETSHelper(A,B, currSum, i+1, size+1);
        currSum -= A.get(i);
        ans += SIXLETSHelper(A,B, currSum, i+1, size);
        return ans;
    }
}
