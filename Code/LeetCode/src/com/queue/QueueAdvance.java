package com.queue;

import java.util.*;

public class QueueAdvance {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        String A = sc.nextLine();
        int n = sc.nextInt();
        int B = sc.nextInt();
        ArrayList<Integer> A = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            A.add(sc.nextInt());
        }
        System.out.println(sumOfMinMaxWindow(A,B));
    }

    public static int sumOfMinMaxWindow(ArrayList<Integer> A, int B){
        int M = 1000000007;
        long ans = 0L;
        ArrayList<Integer> windowMin = slidingMinimum(A,B);
        ArrayList<Integer> windowMax = slidingMaximum(A,B);
        for (int i = 0; i < windowMin.size(); i++) {
            ans = (ans+ windowMax.get(i)+windowMin.get(i))%M;
        }
        ans = (ans+M)%M;
        return (int)ans;
    }
    public static ArrayList<Integer> slidingMinimum(final List<Integer> A, int B) {
        if(A.size()<B)  return new ArrayList<>();
        Deque<Integer> q = new ArrayDeque<>();
        int start =0;
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < B; i++) {
            while (q.size()>0 && A.get(q.peekLast())>A.get(i)){
                q.removeLast();
            }
            q.addLast(i);
        }
        ans.add(A.get(q.peekFirst()));
        start++;
        for (int i = B; i < A.size(); i++) {
            while (q.size()>0 && A.get(q.peekLast())>A.get(i)){
                q.removeLast();
            }
            q.addLast(i);
            if(q.peekFirst()<start){
                while(q.peekFirst()<start)  q.removeFirst();
            }
            ans.add(A.get(q.peekFirst()));
            start++;
        }
        return ans;
    }
    public static ArrayList<Integer> slidingMaximum(final List<Integer> A, int B) {
        if(A.size()<B)  return new ArrayList<>();
        Deque<Integer> q = new ArrayDeque<>();
        int start =0;
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < B; i++) {
            while (q.size()>0 && A.get(q.peekLast())<A.get(i)){
                q.removeLast();
            }
            q.addLast(i);
        }
        ans.add(A.get(q.peekFirst()));
        start++;
        for (int i = B; i < A.size(); i++) {
            while (q.size()>0 && A.get(q.peekLast())<A.get(i)){
                q.removeLast();
            }
            q.addLast(i);
            if(q.peekFirst()<start){
                while(q.peekFirst()<start)  q.removeFirst();
            }
            ans.add(A.get(q.peekFirst()));
            start++;
        }
        return ans;
    }

    public static String firstNonRepeatChar(String A){
        StringBuilder sb = new StringBuilder();
        Queue<Character> q = new LinkedList<>();
        HashMap<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < A.length(); i++) {
            hm.put(A.charAt(i), hm.getOrDefault(A.charAt(i),0)+1);
            if(hm.get(A.charAt(i))==1)  q.add(A.charAt(i));
            while (q.size()>0 && hm.get(q.peek())>1){
                q.remove();
            }
            if(q.size()>0) sb.append(q.peek());
            else sb.append('#');
        }
        return sb.toString();
    }

    public static int taskScheduling(ArrayList<Integer> A, ArrayList<Integer> B) {
        int c=0;
        Queue<Integer> q = new LinkedList<>(A);
        for (Integer integer : B) {
            while (!Objects.equals(q.peek(), integer)) {
                q.add(q.remove());
                c++;
            }
            q.remove();
            c++;
        }
        return c;
    }
    
    public static ArrayList<Integer> reverseElmOfQueue(ArrayList<Integer> A, int B) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = B-1; i>=0; i--) {
            queue.add(A.get(i));
        }
        for (int i = 0; i < B; i++) {
            A.set(i,queue.remove());
        }
        return A;
    }

    public static ArrayList<Integer> nIntegerContainOnly123(int A){
        ArrayList<Integer> ls = new ArrayList<>();
        int curr=0;
        ls.add(1);
        if(ls.size()==A)    return ls;
        ls.add(2);
        if(ls.size()==A)    return ls;
        ls.add(3);
        while (ls.size()<A){
            int temp = ls.get(curr);
            ls.add(temp*10+1);
            if(ls.size()==A)    break;
            ls.add(temp*10+2);
            if(ls.size()==A)    break;
            ls.add(temp*10+3);
            if(ls.size()==A)    break;
            curr++;
        }
        return ls;
    }

    private static String perfectNumber(int A) {
        List<String> pnList = new ArrayList<>();
        int totalPN = 2;
        int curr=0;
        String mid11 = "11" , mid22 = "22";
        pnList.add("11");
        pnList.add("22");
        while (totalPN<=A){
            String str = pnList.get(curr);
            String s1 = str.substring(0,str.length()/2)+mid11+str.substring(str.length()/2);
            String s2 = str.substring(0,str.length()/2)+mid22+str.substring(str.length()/2);
            pnList.add(s1);
            pnList.add(s2);
            totalPN+=2;
            curr++;
        }
        return pnList.get(A-1);
    }
}
