package com.HeapAndGreedy;

import java.util.*;

public class HeapClasses {
    class ListNode {
        public int val;
        public ListNode next,down;
        ListNode(int x) { val = x; next = null; }
        public ListNode() { next = null; }
    }
    public static class Pair<T, T1> {
        T val;
        T1 index;
        int index2;
        public Pair(T val, T1 index){
            this.val=val;
            this.index=index;
        }
        public Pair(T val, T1 index, int next){
            this.val=val;
            this.index=index;
            this.index2 =next;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
//        int c = sc.nextInt();
//        int B = sc.nextInt();
        ArrayList<Integer> A = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            A.add(sc.nextInt());
        }
        /*ArrayList<Integer> B = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            B.add(sc.nextInt());
        }*/
        System.out.println(specialMedian(A));
    }

    public ArrayList<Integer> theShipCompany(int A, int B, ArrayList<Integer> C) {
        PriorityQueue<Integer> min = new PriorityQueue<>();
        PriorityQueue<Integer> max = new PriorityQueue<>((a,b)->b-a);
        for (Integer integer : C) {
            min.add(integer);
            max.add(integer);
        }
        int maxSum =0,minSum=0;
        for (int i = 0; i < A; i++) {
            int tmax = max.poll();
            maxSum+=tmax;
            tmax--;
            if(tmax>0) max.add(tmax);

            int tmin = min.poll();
            minSum+=tmin;
            tmin--;
            if(tmin>0) min.add(tmin);
        }
        return new ArrayList<>(Arrays.asList(maxSum,minSum));
    }

    static PriorityQueue<Integer> minHeap;
    static PriorityQueue<Integer> maxHeap;
    public static int specialMedian(ArrayList<Integer> A) {
        int n =A.size();
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((a,b)-> b-a);
//        if(A.size()==2) return (Objects.equals(A.get(0), A.get(1)))?1:0;
        for (int i = 0; i < n-1; i++) {
            add_number(A.get(i));
            if(A.get(i+1)==find_median())   return 1;
        }

        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((a,b)-> b-a);
//        if(A.size()==2) return (Objects.equals(A.get(0), A.get(1)))?1:0;
        for (int i = n-1; i >0; i--) {
            add_number(A.get(i));
            if(A.get(i-1)==find_median())   return 1;
        }

        return 0;
    }
    public static void add_number(int no){
        maxHeap.offer(no);
        minHeap.offer(maxHeap.peek());
        maxHeap.poll();
        if(maxHeap.size()<minHeap.size()){
            maxHeap.offer(minHeap.peek());
            minHeap.poll();
        }
    }
    public static int find_median(){
        if(maxHeap.size()>minHeap.size())   return maxHeap.peek();
        else{
            int ans = maxHeap.peek()+minHeap.peek();
            if(ans%2!=0) return Integer.MAX_VALUE;
            else return ans/2;
        }
    }


    public static boolean runningMedianForSpecialMedian(ArrayList<Integer> A){
        PriorityQueue<Integer> min_heap = new PriorityQueue<>();
        PriorityQueue<Integer> max_heap = new PriorityQueue<>((a,b)-> b-a);
        max_heap.add(A.get(0));
        for (int i = 1; i < A.size(); i++) {
            if(A.get(i)<max_heap.peek()){
                max_heap.add(A.get(i));
            }else   min_heap.add(A.get(i));

            if(max_heap.size()<min_heap.size()){
                int elm =min_heap.poll();
                max_heap.add(elm);
            }else if(max_heap.size()>min_heap.size()){
                int elm=max_heap.poll();
                min_heap.add(elm);
            }
            if((i+1)%2==0){
                long median = ((long)min_heap.peek()+(long)max_heap.peek())/2;
                if(i+1<A.size() && A.get(i+1)==median) return true;
            }else{
                int median = max_heap.peek();
                if(i+1<A.size() && A.get(i+1)==median) return true;
            }
        }
        return false;
    }

    public ListNode mergeKLists(ArrayList<ListNode> a) {
        PriorityQueue<Pair<ListNode,Integer>> min_heap = new PriorityQueue<>((x,y)->x.val.val-y.val.val);
        for (int i = 0; i < a.size(); i++) {
            min_heap.add(new Pair<>(a.get(i),i));
        }
        ListNode head = new ListNode();
        ListNode temp = head;
        while (!min_heap.isEmpty()){
            Pair<ListNode,Integer> p = min_heap.poll();
            int index = p.index;
            ListNode aHead = a.get(index);
            ListNode node = aHead;
            aHead= aHead.next;
            a.set(index,aHead);
            if(aHead!=null) min_heap.add(new Pair<>(aHead,index));
            temp.next=node;
            temp=temp.next;
        }
        return head.next;
    }

    public static int kSmallestElmInSortedMatrix(ArrayList<ArrayList<Integer>> A, int B) {
        ArrayList<Integer> arr = sorted2DArrayToSorted1DArray(A);
        return arr.get(B-1);
    }

    public static ArrayList<Integer> sorted2DArrayToSorted1DArray(ArrayList<ArrayList<Integer>> A){
        int n = A.size(), m = A.get(0).size();
        PriorityQueue<Pair<Integer, Pair<Integer,Integer>>> min_heap = new PriorityQueue<>((a,b)-> a.val-b.val);
        for (int i = 0; i < n; i++) {
            min_heap.add(new Pair<>(A.get(i).get(0), new Pair<>(i,0)));
        }
        ArrayList<Integer> ans = new ArrayList<>();
        while (min_heap.size()>0){
            Pair<Integer, Pair<Integer,Integer>> p = min_heap.poll();
            ans.add(p.val);
            int r = p.index.val, c= p.index.index;
            if(c+1<m){
                min_heap.add(new Pair<>(A.get(r).get(c+1), new Pair<>(r,c+1)));
            }
        }
        return ans;
    }

    public static ArrayList<Integer> nMaxPairCombinations(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Integer> ans = new ArrayList<>();
        PriorityQueue<Pair<Integer,Pair<Integer,Integer>>> min_heap = new PriorityQueue<>((a,b)-> b.val-a.val);
        Collections.sort(A,Collections.reverseOrder());
        Collections.sort(B,Collections.reverseOrder());
        int n = A.size();
        HashSet<String> usedPair = new HashSet<>();
        min_heap.add(new Pair<>(A.get(0)+B.get(0), new Pair<>(0,0)));
        usedPair.add("0@0");
        for (int i = 1; i <=n; i++) {
            Pair<Integer,Pair<Integer,Integer>> p = min_heap.poll();
            ans.add(p.val);
            int r = p.index.val;
            int c = p.index.index;
            if(r+1<n && !usedPair.contains((r+1)+"@"+c)){
                min_heap.add( new Pair<>(A.get(r+1)+B.get(c), new Pair<>(r+1,c)));
                usedPair.add((r+1)+"@"+c);
            }
            if(c+1<n && !usedPair.contains(r+"@"+(c+1))){
                min_heap.add( new Pair<>(A.get(r)+B.get(c+1), new Pair<>(r,c+1)));
                usedPair.add(r+"@"+(c+1));
            }
        }
        return ans;
    }

    public static int mishaAndCandies(ArrayList<Integer> A, int B){
        int ans=0;
        PriorityQueue<Integer> min_heap = new PriorityQueue<>(A);
        while (min_heap.size()>0){
            int candies = min_heap.poll();
            if(candies<=B){
                int eat = Math.floorDiv(candies, 2);
                int remain = candies - eat;
                ans += eat;
                if (min_heap.size() > 0) {
                    int addInMinHeap = min_heap.poll();
                    min_heap.add(addInMinHeap + remain);
                }
            }else break;
        }
        return ans;
    }

    public static int minimumLargestElm(ArrayList<Integer> A, int B) {
        Collections.sort(A);
        PriorityQueue<Pair<Integer,Integer>> min_heap = new PriorityQueue<>((a,b)->(!Objects.equals(a.index2, b.index2))?a.index2 -b.index2 :a.index-b.index);
        for (int i = 0; i < A.size(); i++) {
            min_heap.add(new Pair<>(A.get(i),A.get(i), 2*A.get(i)));
        }
        int n=A.size();
        int max = A.get(n-1);
        for (int i = 0; i < B; i++) {
            Pair<Integer,Integer> p = min_heap.poll();
            int addVal = p.val+p.index;
            if(addVal>max){
                max=addVal;
            }
            min_heap.add(new Pair<>(addVal,p.index,addVal+p.index));
        }
        return max;
    }

    public static ArrayList<ArrayList<Integer>> bClosestPointsToOrigin(ArrayList<ArrayList<Integer>> A, int B) {
        PriorityQueue<Pair<Long,Integer>> max_heap = new PriorityQueue<>((a,b)-> (int) (b.val-a.val));
        for (int i = 0; i < A.size(); i++) {
            long val = (long) A.get(i).get(0) *A.get(i).get(0) + (long) A.get(i).get(1) *A.get(i).get(1);
            max_heap.add(new Pair<>(val,i));
            if(max_heap.size()>B){
                max_heap.poll();
            }
        }
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        while (!max_heap.isEmpty()){
            Pair<Long,Integer> p = max_heap.poll();
            ans.add(A.get(p.index));
        }
        return ans;
    }

    public static int maximumArraySumAfterBNegative(ArrayList<Integer> A, int B) {
        PriorityQueue<Pair<Integer,Integer>> min_heap = new PriorityQueue<>((a,b)-> a.val-b.val);
        for (int i = 0; i < A.size(); i++) {
            min_heap.add(new Pair<>(A.get(i),i));
        }
        for (int i = 0; i < B; i++) {
            Pair<Integer,Integer> p = min_heap.poll();
            int val = p.val;
            int index = p.index;
            A.set(index,-val);
            min_heap.add(new Pair<>(-val,index));
        }
        int ans = 0;
        for (Integer integer : A) {
            ans += integer;
        }
        return ans;
    }

    public static ArrayList<Integer> productOf3(ArrayList<Integer> B) {
        PriorityQueue<Integer> min_heap = new PriorityQueue<>();
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < B.size(); i++) {
            min_heap.add(B.get(i));
            if(min_heap.size()>3) {
                min_heap.poll();
            }
            if(min_heap.size()==3) {
                int f = min_heap.poll();
                int s = min_heap.poll();
                int t = min_heap.poll();
                ans.add(f * s * t);
                min_heap.add(f);
                min_heap.add(s);
                min_heap.add(t);
            }
            if(min_heap.size()<3)   ans.add(-1);
        }
        return ans;
    }

    public static ArrayList<Integer> athLargest(int A, ArrayList<Integer> B) {
        PriorityQueue<Integer> min_heap = new PriorityQueue<>();
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < A; i++) {
            ans.add(-1);
            min_heap.add(B.get(i));
        }
        ans.remove(0);
        ans.add(min_heap.peek());
        for (int i = A; i < B.size(); i++) {
            min_heap.add(B.get(i));
            if(min_heap.size()>A){
                min_heap.poll();
            }
            ans.add(min_heap.peek());
        }
        return ans;
    }

    public static ArrayList<Integer> kPlacesApart(ArrayList<Integer> A, int B) {
        PriorityQueue<Integer> min_heap = new PriorityQueue<>();
        ArrayList<Integer> ans =new ArrayList<>();
        for (int i = 0; i <=B; i++) {
            min_heap.add(A.get(i));
        }
        ans.add(min_heap.poll());
        for (int i = B+1; i <A.size(); i++) {
            min_heap.add(A.get(i));
            ans.add(min_heap.poll());
        }
        while (!min_heap.isEmpty()){
            ans.add(min_heap.poll());
        }
        return ans;
    }

    public static ArrayList<Integer> runningMedian(ArrayList<Integer> A) {
        ArrayList<Integer> ans = new ArrayList<>();
        PriorityQueue<Integer> min_heap = new PriorityQueue<>((a,b)-> a-b);
        PriorityQueue<Integer> max_heap = new PriorityQueue<>((a,b)-> b-a);
        ans.add(A.get(0));
        max_heap.add(A.get(0));
        for (int i = 1; i < A.size(); i++) {
            //inserting
            if(A.get(i)<max_heap.peek()){
                max_heap.add(A.get(i));
            }else{
                min_heap.add(A.get(i));
            }
            //balancing
            if(max_heap.size()<min_heap.size()){
                int elm = min_heap.peek();
                min_heap.remove();
                max_heap.add(elm);
            }else if((max_heap.size()-min_heap.size())>1){
                int elm = max_heap.peek();
                max_heap.remove();
                min_heap.add(elm);
            }
            int s = i+1;
            if(s%2==0){
                ans.add((max_heap.peek()+min_heap.peek())/2);
            }else{
                ans.add(max_heap.peek());
            }
        }
        return ans;
    }

    private static int connectRope(ArrayList<Integer> A) {
        int ans=0;
        PriorityQueue<Integer> heap = new PriorityQueue<>((a,b)-> a-b);
        heap.addAll(A);
        while (heap.size()!=1){
            int a = heap.poll();
            int b = heap.poll();
            int sum = a+b;
            ans += sum;
            heap.add(sum);
        }
        return ans;
    }
}
