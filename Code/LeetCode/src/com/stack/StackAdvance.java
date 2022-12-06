package com.stack;

import java.util.*;

public class StackAdvance {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        /*int n = sc.nextInt();
        ArrayList<Integer> A = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            A.add(sc.nextInt());
        }*/
        System.out.println(checkTwoBracketExpr(str1,str2));
    }

    public static int checkTwoBracketExpr(String A, String B){
        boolean[] charSignA = checkCharSign(A);
        boolean[] charSignB = checkCharSign(B);
        for (int i = 0; i < charSignA.length; i++) {
            if(charSignA[i]!=charSignB[i])  return 0;
        }
        return 1;
    }
    public static boolean[] checkCharSign(String A){
        boolean[] charSign = new boolean[26];
        Stack<Boolean> s = new Stack<>();
        s.push(true);
        for (int i = 0; i < A.length(); i++) {
            if(A.charAt(i)=='+' || A.charAt(i)=='-')    continue;
            if(A.charAt(i)=='('){
                if(i!=0) {
                    if (A.charAt(i - 1) == '-') s.push(!s.peek());
                    else s.push(s.peek());
                }
            }else if(A.charAt(i)==')') {
                s.pop();
            }else{
                if(i!=0) {
                    if (s.peek()) {
                        charSign[A.charAt(i) - 'a'] = A.charAt(i - 1) != '-';
                    } else {
                        charSign[A.charAt(i) - 'a'] = A.charAt(i - 1) == '-';
                    }
                }else{
                    charSign[A.charAt(i) - 'a'] = true;
                }
            }
        }
        return charSign;
    }

    public static int minDiffMaxForAllSubArr(ArrayList<Integer> A){
        ArrayList<Integer> nextMax = nearestGreaterElmIndexFromRight(A);
        ArrayList<Integer> prevMax = nearestGreaterElmIndexFromLeft(A);
        ArrayList<Integer> nextMin = nearestSmallerElmIndexFromRight(A);
        ArrayList<Integer> prevMin = nearestSmallerElmIndexFromLeft(A);
        long ans =0L;

        int M = 1000000007;
        for (int j = 0; j < A.size(); j++) {
            int maxK = nextMax.get(j);
            int maxI = prevMax.get(j);
            int minK = nextMin.get(j);
            int minI = prevMin.get(j);
            long max = (long) A.get(j) *(maxK - j) *(j-maxI);
            long min = (long) A.get(j) *(minK - j) *(j-minI);
            ans = (ans + (max-min))%M;
        }
        ans = (ans+M)%M;
        return (int)ans;
    }
    public static ArrayList<Integer> nearestGreaterElmIndexFromRight(ArrayList<Integer> A){
        Stack<Integer> st = new Stack<>();
        ArrayList<Integer> ans = new ArrayList<>(Collections.nCopies(A.size(),A.size()));
        st.push(A.size()-1);
        for (int i=A.size()-2; i>=0; i--) {
            if (st.isEmpty()) {
            } else {
                while (!st.isEmpty() && A.get(st.peek()) <= A.get(i)) {
                    st.pop();
                }
                if(!st.isEmpty())   ans.set(i,st.peek());
            }
            st.push(i);
        }
        return ans;
    }
    public static ArrayList<Integer> nearestGreaterElmIndexFromLeft(ArrayList<Integer> A){
        Stack<Integer> st = new Stack<>();
        ArrayList<Integer> ans = new ArrayList<>(Collections.nCopies(A.size(),-1));
        st.push(0);
        for (int i=1; i<A.size(); i++) {
            if (st.isEmpty()) {
            } else {
                while (!st.isEmpty() && A.get(st.peek()) < A.get(i)) {
                    st.pop();
                }
                if(!st.isEmpty())   ans.set(i,st.peek());
            }
            st.push(i);
        }
        return ans;
    }

    public static int allSubArrMaxSecMaxXor(ArrayList<Integer> A){
        int ans=0;
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < A.size(); i++) {
            while (!st.isEmpty() ){
                ans = Math.max(ans, A.get(i)^A.get(st.peek()));
                if(A.get(st.peek())>A.get(i))
                    break;
                st.pop();
            }
            st.push(i);
        }
        return ans;
    }

    public static int maximumRectangle(ArrayList<ArrayList<Integer>> A) {
        int maxArea = 0;
        int m = A.get(0).size();
        ArrayList<Integer> histogram = new ArrayList<>(Collections.nCopies(m,0));
        for (ArrayList<Integer> arr : A) {
            for (int j = 0; j < m; j++) {
                int val = 0;
                if (arr.get(j) != 0) val = histogram.get(j) + arr.get(j);
                histogram.set(j, val);
            }
            maxArea = Math.max(maxArea, largestRectangleArea(histogram));
        }
        return maxArea;
    }

    public static int largestRectangleArea(ArrayList<Integer> A) {
        if(A.size()==1) return A.get(0);
        ArrayList<Integer> prevSmaller = nearestSmallerElmIndexFromLeft(A);
        ArrayList<Integer> nextSmaller = nearestSmallerElmIndexFromRight(A);
        int ans =0;
        for (int i = 0; i < A.size(); i++) {
            int height = A.get(i);
            int s = prevSmaller.get(i);
            int e = nextSmaller.get(i);
            int distance = e - s - 1;
            ans = Math.max(ans, height*distance);
        }
        return ans;
    }
    public static ArrayList<Integer> nearestSmallerElmIndexFromRight(ArrayList<Integer> A){
        Stack<Integer> st = new Stack<>();
        ArrayList<Integer> ans = new ArrayList<>(Collections.nCopies(A.size(),A.size()));
        st.push(A.size()-1);
        for (int i=A.size()-2; i>=0; i--) {
            if (st.isEmpty()) {
            } else {
                while (!st.isEmpty() && A.get(st.peek()) >= A.get(i)) {
                    st.pop();
                }
                if(!st.isEmpty())   ans.set(i,st.peek());
            }
            st.push(i);
        }
        return ans;
    }
    public static ArrayList<Integer> nearestSmallerElmIndexFromLeft(ArrayList<Integer> A){
        Stack<Integer> st = new Stack<>();
        ArrayList<Integer> ans = new ArrayList<>(Collections.nCopies(A.size(),-1));
        st.push(0);
        for (int i=1; i<A.size(); i++) {
            if (st.isEmpty()) {
            } else {
                while (!st.isEmpty() && A.get(st.peek()) > A.get(i)) {
                    st.pop();
                }
                if(!st.isEmpty())   ans.set(i,st.peek());
            }
            st.push(i);
        }
        return ans;
    }

    public static ArrayList<Integer> nearestSmallerFromLeft(ArrayList<Integer> A){
        Stack<Integer> st = new Stack<>();
        ArrayList<Integer> ans = new ArrayList<>(Collections.nCopies(A.size(),-1));
        st.push(A.get(0));
        for (int i=1; i<A.size(); i++) {
            if (st.isEmpty()) {
            } else {
                while (!st.isEmpty() && st.peek() >= A.get(i)) {
                    st.pop();
                }
                if(!st.isEmpty())   ans.set(i,st.peek());
            }
            st.push(A.get(i));
        }
        return ans;
    }

    public static ArrayList<Integer> nearestGreaterFromLeft(ArrayList<Integer> A){
        Stack<Integer> st = new Stack<>();
        ArrayList<Integer> ans = new ArrayList<>(Collections.nCopies(A.size(),-1));
        st.push(A.get(0));
        for (int i=1; i<A.size(); i++) {
            if (st.isEmpty()) {
            } else {
                while (!st.isEmpty() && st.peek() <= A.get(i)) {
                    st.pop();
                }
                if(!st.isEmpty())   ans.set(i,st.peek());
            }
            st.push(A.get(i));
        }
        return ans;
    }

    public static int braces(String A) {
        if(A.length()==1){
            if(A.charAt(0)=='(' || A.charAt(0)==')')
                return 1;
            else return 0;
        }
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < A.length(); i++) {
            if(A.charAt(i)>='a' && A.charAt(i)<='z'){}
            else{
                if(A.charAt(i)==')'){
                    if(st.peek()=='(')  return 1;
                    else{
                        while (st.peek()!='(')  st.pop();
                        st.pop();
                    }
                }
                else    st.push(A.charAt(i));
            }
        }
        return 0;
    }

    public int ballPasses(int A, int B, ArrayList<Integer> C) {
        Stack<Integer> st = new Stack<>();
        st.push(B);
        for (int c: C) {
            if(c==0){
                st.pop();
            }else{
                st.push(c);
            }
        }
        return st.pop();
    }

    public static String inFixToPostFix(String A) {
        HashMap<Character, Integer> precedence = new HashMap<>();
        precedence.put('^',3);
        precedence.put('/',2);
        precedence.put('*',2);
        precedence.put('+',1);
        precedence.put('-',1);

        Stack<Character> st = new Stack<>();
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < A.length(); i++) {
            if(A.charAt(i)>='a' && A.charAt(i)<='z'){
                sb.append(A.charAt(i));
            }else if(A.charAt(i)=='('){
                st.push(A.charAt(i));
            }else if(A.charAt(i)==')'){
                while (st.peek()!='(')  sb.append(st.pop());
                st.pop();
            }else{
                while (!st.isEmpty() && !st.peek().equals('(') && precedence.get(st.peek()) >= precedence.get(A.charAt(i))){
                    sb.append(st.pop());
                }
                st.push(A.charAt(i));
            }
        }
        while (!st.isEmpty())   sb.append(st.pop());
        return sb.toString();
    }

    public static String duplicateRemoveAtConsecutive(String A){
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < A.length(); i++) {
            if(st.isEmpty()){
                st.push(A.charAt(i));
            }else{
                if(st.peek().equals(A.charAt(i))){
                    st.pop();
                }else
                    st.push(A.charAt(i));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Character c : st)
            sb.append(c);
        return sb.toString();
    }

    public static ArrayList<Integer> sortedStackMerge(Stack<Integer> A, Stack<Integer> B){
        Stack<Integer> st = new Stack<>();
        while (!A.isEmpty() && !B.isEmpty()){
            if(A.peek()>B.peek()){
                st.push(A.pop());
            }else{
                st.push(B.pop());
            }
        }
        while (!A.isEmpty())    st.push(A.pop());
        while (!B.isEmpty())    st.push(B.pop());
        ArrayList<Integer> ans = new ArrayList<>();
        while (!st.isEmpty())   ans.add(st.pop());
        return ans;
    }

    public static ArrayList<Integer> sortStack(ArrayList<Integer> A){
        if(A.size()<=1) return A;
        ArrayList<Integer> B = new ArrayList<>();
        int size = A.size();
        for (int i = size-1; i >= size/2; i--) {
            B.add(A.remove(i));
        }
        A=sortStack(A);
        B=sortStack(B);
        Stack<Integer> Ast = new Stack<>();
        Ast.addAll(A);
        Stack<Integer> Bst = new Stack<>();
        Bst.addAll(B);
        return sortedStackMerge(Ast,Bst);
    }

    public static int evalRPN(String A) {
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < A.length(); i++) {
            if(Objects.equals(A.charAt(i), '+') || Objects.equals(A.charAt(i), '-')
                    || Objects.equals(A.charAt(i), '*') || Objects.equals(A.charAt(i), '/')){
                int b = st.pop();
                if(st.isEmpty()) return b;
                int a = st.pop();
                switch (A.charAt(i)){
                    case '+':{ st.push(a+b);
                                break;}
                    case '-':{st.push(a-b);
                                break;}
                    case '*':{st.push(a*b);
                                break;}
                    case '/':{
                        if(b!=0)    st.push(a/b);
                        else st.push(1);
                        break;
                    }
                }
            }else{
                st.push(Integer.parseInt(A.charAt(i)+""));
            }
        }
        return st.pop();
    }
}
