package com.hashing;

import java.util.*;

import static com.Math.AdvanceMathAll.gcd;

public class HashingAdvance {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> A = new ArrayList<>();
        int m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            A.add(sc.nextInt());
        }
        ArrayList<ArrayList<Integer>> B = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            ArrayList<Integer> b = new ArrayList<>();
            b.add(sc.nextInt());
            b.add(sc.nextInt());
            b.add(sc.nextInt());
            b.add(sc.nextInt());
            B.add(b);
        }
        /*String A = sc.nextLine();
        int B = sc.nextInt();*/
        System.out.println(compareSortedSubArray(A,B));

    }

    public long taskSchedulerII(int[] tasks, int space){
        HashMap<Integer, Long> occur = new HashMap<>();

        long curr = 0L;
        for(int t: tasks){
            if(!occur.containsKey(t)){
                curr++;
                occur.put(t,curr);
            }else{
                long prev = occur.get(t);
                if(prev+space+1<=curr){
                    curr++;
                    occur.put(t,curr);
                }else{
                    curr= prev+space+1;
                    occur.put(t,curr);
                }
            }
        }
        return curr;
    }

    public static ArrayList<Integer> compareSortedSubArray(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
        int n = A.size();
        Random rd = new Random();
        HashMap<Integer, Long> hm = new HashMap<>();
        HashSet<Long> used = new HashSet<>();
        long[] pSum = new long[n+1];
        for (int i = 0; i < n; i++) {
            if(!hm.containsKey(A.get(i))) {
                long hash = rd.nextLong();
                while (used.contains(hash)){
                    hash = rd.nextLong();
                }
                hm.put(A.get(i),hash);
                used.add(hash);
            }
            pSum[i+1] = pSum[i]+hm.get(A.get(i));
        }

        ArrayList<Integer> ans = new ArrayList<>();
        for (ArrayList<Integer> t : B){
            long val1 = pSum[t.get(1)+1]-pSum[t.get(0)];
            long val2 = pSum[t.get(3)+1]-pSum[t.get(2)];
            if(val1==val2)  ans.add(1);
            else ans.add(0);
        }
        return ans;
        //Give TLE error
        /*ArrayList<Integer> a = new ArrayList<>();
        for (ArrayList<Integer> b : B) {
            if(b.get(1)-b.get(0)==b.get(3)-b.get(2)) {
                TreeSet<Integer> t = new TreeSet<>(A.subList(b.get(0), b.get(1) + 1));
                int l = t.size();
                t.addAll(A.subList(b.get(2), b.get(3) + 1));
                if (l == t.size()) a.add(1);
                else a.add(0);
            }else a.add(0);
        }
        return a;*/
    }

    public static ArrayList<Integer> closestOneToGivenIndex(ArrayList<ArrayList<Integer>> A , int N){
        TreeSet<Integer> set = new TreeSet<>();
        set.add(-N);
        set.add(2*N);
        ArrayList<Integer> ans = new ArrayList<>();
        for (ArrayList<Integer> a : A) {
            if(a.get(0)==1) {
                if (set.contains(a.get(1))) {
                    set.remove(a.get(1));
                } else set.add(a.get(1));
            } else{
                if(set.size()==2) {
                }
                else if(set.contains(a.get(1))){
                    ans.add(a.get(1));
                }else{
                    int l = set.floor(a.get(1));
                    int r = set.ceiling(a.get(1));
                    if(Math.abs(a.get(1)-l)>=Math.abs(r-a.get(1)))    ans.add(r);
                    else ans.add(l);
                }
            }
        }
        return ans;
    }

    public static ArrayList<Integer> commonElm(ArrayList<Integer> A, ArrayList<Integer> B) {
        HashMap<Integer,Integer> aMap = new HashMap<>();
        HashMap<Integer,Integer> bMap = new HashMap<>();
        Set<Integer> s = new TreeSet<>();
        for(Integer e:A) {
            aMap.put(e, aMap.getOrDefault(e, 0) + 1);
            s.add(e);
        }
        for(Integer e:B) {
            bMap.put(e, bMap.getOrDefault(e, 0) + 1);
            s.add(e);
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for(Integer e : s){
            int c = Math.min(aMap.getOrDefault(e,0),bMap.getOrDefault(e,0));
            while(c-->0)    ans.add(e);
        }
        return ans;
    }

    public static int pointOnLine(ArrayList<Integer> A, ArrayList<Integer> B) {
        int max = 0;
        if(A.size()==1) return 1;
        if(A.size()==2) return 2;
        for (int i = 0; i < A.size(); i++) {
            int dup = 1;
            HashMap<String ,Integer> slopCount = new HashMap<>();
            for (int j = i+1; j < A.size(); j++) {
                if(Objects.equals(A.get(i), A.get(j)) && Objects.equals(B.get(i), B.get(j)))    dup++;
                else {
                    int dy = B.get(j) - B.get(i);
                    int dx = A.get(j) - A.get(i);
                    int gcd = gcd(dy, dx);
                    slopCount.put(makeString(dy / gcd, dx / gcd), slopCount.getOrDefault(makeString(dy / gcd, dx / gcd), 0) + 1);
                }
            }
            max = Math.max(max,dup);
            for(Map.Entry<String,Integer> e : slopCount.entrySet()){
                max = Math.max(max, e.getValue()+dup);
            }
        }
        return max;
    }

    public static int countRectangle(ArrayList<Integer> A, ArrayList<Integer> B) {
        int c=0;
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < A.size(); i++) {
            set.add(makeString(A.get(i),B.get(i)));
        }

        for (int i = 0; i < A.size(); i++) {
            for (int j = i+1; j < A.size(); j++) {
                int tlx = A.get(i);
                int tly = B.get(i);
                int brx = A.get(j);
                int bry = B.get(j);
                if(tlx!=brx && tly!=bry){
                    int blx = tlx;
                    int bly = bry;
                    int trx = brx;
                    int trY = tly;
                    String pointTR = makeString(trx,trY);
                    String pointBL = makeString(blx,bly);
                    if(set.contains(pointTR) && set.contains(pointBL))  c++;
                }
            }
        }
        return c/2;
    }

    private static String makeString(int A, int B){
        return A+"@"+B;
    }

    public static int replicationSubString(int A, String B) {
        HashMap<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < B.length(); i++) {
            hm.put(B.charAt(i),hm.getOrDefault(B.charAt(i),0)+1);
        }
        for (Map.Entry<Character,Integer> e : hm.entrySet()){
            if(e.getValue()%A!=0)   return -1;
        }
        return 1;
    }

    public static int longestSubStringWithDisChar(String A){
        int max = 0;
        HashSet<Character> hm = new HashSet<>();
        int i = 0,j=0;
        while ( j < A.length()) {
            if(!hm.contains(A.charAt(j))){
                hm.add(A.charAt(j));
                max = Math.max(max,hm.size());
                j++;
            }else{
                hm.remove(A.charAt(i));
                i++;
            }
        }
        return max;
    }

    public static int noOfRightAngle(ArrayList<Integer> A, ArrayList<Integer> B){
        int M = 1000000007;
        HashMap<Integer,Integer> xAxis = new HashMap<>();
        HashMap<Integer,Integer> yAxis = new HashMap<>();
        for (Integer p:  A) {
            xAxis.put(p,xAxis.getOrDefault(p,0)+1);
        }
        for (Integer p:  B) {
            yAxis.put(p,yAxis.getOrDefault(p,0)+1);
        }

        long a = 0L;
        for(int i=0; i<A.size(); i++){
            int xCount = xAxis.get(A.get(i));
            int yCount = yAxis.get(B.get(i));

            a = (a%M + ((long) (xCount - 1) *(yCount-1))%M )%M;
        }
        a = (a+M)%M;
        return (int)a;
    }

    public static ArrayList<Integer> sortArrInGivenOrder(ArrayList<Integer> A, ArrayList<Integer> B) {
        SortedMap<Integer, Integer> hm = new TreeMap<>();
        for(Integer e:A)    hm.put(e,hm.getOrDefault(e,0)+1);
        ArrayList<Integer> ans = new ArrayList<>();
        for(Integer e : B){
            if(hm.containsKey(e)){
                int v = hm.get(e);
                while (v>0) {
                    ans.add(e);
                    v--;
                }
                hm.remove(e);
            }
        }
        for(Map.Entry<Integer,Integer> e : hm.entrySet()){
            int v = e.getValue();
            while (v>0){
                ans.add(e.getKey());
                v--;
            }
        }
        return ans;
    }

    public static ArrayList<Integer> longestSubArrayWithZero(ArrayList<Integer> A) {
        HashMap<Long,Integer> set  = new HashMap<>();
        set.put(0L,0);
        long sum=0;
        int l = Integer.MIN_VALUE,index=0;
        for (int i=1; i<=A.size(); i++) {
            sum +=A.get(i-1);
            if(set.containsKey(sum)) {
                if(l<Math.abs(i-set.get(sum))){
                    l = Math.abs(i-set.get(sum));
                    index=set.get(sum);
                }
            }
            else set.put(sum,i);
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = index; i <index+l; i++) {
            ans.add(A.get(i));
        }
        return ans;
    }

    public static int longestContinueSubseq(int[] nums){
        HashSet<Integer> set = new HashSet<>();
        for(int i: nums) set.add(i);
        int max = Integer.MIN_VALUE;
        for (Integer integer : set) {
            int c = 1;
            if (!set.contains(integer - 1)) {
                while (set.contains(integer + c)) c++;
                max = Math.max(max, c);
            }
        }
        return max!=Integer.MIN_VALUE?max:0;
    }

    public static int minDisOfSameElm(ArrayList<Integer> A){
        HashMap<Integer,Integer> hm = new HashMap<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < A.size(); i++) {
            if(hm.containsKey(A.get(i))){
                min = Math.min(min, Math.abs(i-hm.get(A.get(i)) )  );
            }
            hm.put(A.get(i),i);
        }
        return min;
    }

    private static int countNoOfPairElmEqual(ArrayList<Integer> A) {
        HashMap<Integer,Integer> hm = new HashMap<>();
        int ans =0;
        for (Integer e:A){
            if(hm.containsKey(e)) {
                ans +=hm.get(e);
                hm.put(e, hm.get(e) + 1);
            }
            else hm.put(e,1);
        }
        return ans;
    }
}
