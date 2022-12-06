package com.Arrays;

import com.sun.jmx.remote.internal.ArrayQueue;

import javax.management.ObjectName;
import java.util.*;
import java.util.stream.Stream;

import static com.Arrays.ArrayAll.swap;
import static com.Arrays.LeftRotation.reverseArrayList;
import static com.BitManuplication.BitManuplicationAll.checkBit;


public class LeetCodeMediumAndHard {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
    class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
   }

    public static class Pair<T, T1> {
        T t;
        T1 t1;
        public Pair(T t, T1 t1){
            this.t =t;
            this.t1 =t1;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        String str = sc.nextLine();
//        int N = sc.nextInt();
        int d = sc.nextInt();
//        int c = sc.nextInt();
//        int b = sc.nextInt();
        /*String[] words =new String[N];
        for (int i = 0; i < N; i++) {
            words[i]=sc.next();
        }*/
//        String p = sc.nextLine();
//        String p1 = sc.nextLine();
//        String p2 = sc.nextLine();
        int[] numsV = new int[d];
        for (int i = 0; i < d; i++) {
            numsV[i] = sc.nextInt();
        }
        /*int[][] nums = new int[c][b];
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < b; j++) {
                nums[i][j]=sc.nextInt();
            }
        }*/
//        ArrayList<Integer> arr = new ArrayList<>();
//        for (int i = 0; i < d; i++) {
//            arr.add(sc.nextInt());
//        }

        System.out.println(solution(numsV));
    }

    public static int solution(int[] A) {
        // write your code in Java 8
        HashSet<Integer> set = new HashSet<>();
        for(int a : A)  set.add(a);
        int sum=0;
        for(int a : set)    sum += a;
        return sum;
    }

    public static int countNodes(TreeNode root) {
        if(root==null) return 0;
        if(isLeafNode(root)) return 1;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        int ans = 0;
        int levelNode=0, level=0;
        while (!q.isEmpty()){
            TreeNode temp = q.remove();
            if(temp==null){
                ans += levelNode;
                if(1<<level == levelNode) {
                    level++;
                    levelNode=0;
                }
                else break;
                if(!q.isEmpty()) q.add(null);
            }else{
                levelNode++;
                if(!isLeafNode(temp)) {
                    q.add(temp.left);
                    q.add(temp.right);
                }
            }
        }
        return ans;
    }

    private static boolean isLeafNode(TreeNode root) {
        return root.left==null && root.right==null;
    }

    class MedianFinder {
        PriorityQueue<Integer> min_heap;
        PriorityQueue<Integer> max_heap;
        public MedianFinder() {
            min_heap = new PriorityQueue<>((a,b)-> a-b);
            max_heap = new PriorityQueue<>((a,b)-> b-a);
        }

        public void addNum(int num) {
            if(!max_heap.isEmpty() && max_heap.peek()>num)
                max_heap.add(num);
            else
                min_heap.add(num);

            if(max_heap.size()<min_heap.size()){
                int elm = min_heap.peek();
                min_heap.remove();
                max_heap.add(elm);
            }else if((max_heap.size()-min_heap.size())>1){
                int elm = max_heap.peek();
                max_heap.remove();
                min_heap.add(elm);
            }

        }

        public double findMedian() {
            if(max_heap.size()==min_heap.size()){
                return (double) (max_heap.peek()+min_heap.peek())/2;
            }else   return (double) max_heap.peek();
        }
    }

    public static String removeDuplicates(String s, int k) {
        Stack<Pair<Character,Integer>> stack = new Stack<>();
        for (int i = 0; i < s.length();) {
            if(stack.isEmpty()){
                stack.push(new Pair<>(s.charAt(i),1));
                i++;
            }else{
                Pair<Character,Integer> t = stack.peek();
                int c=0;
                if(t.t==s.charAt(i)){
                    while (i<s.length() && t.t==s.charAt(i)){
                        c++;i++;
                    }
                    Pair<Character,Integer> pop = stack.pop();
                    c +=pop.t1;
                    if(c%k!=0)  stack.push(new Pair<>(pop.t, c%k));
                }else{
                    stack.push(new Pair<>(s.charAt(i),1));
                    i++;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            Pair<Character,Integer> temp = stack.pop();
            for (int i = 0; i < temp.t1; i++) {
                sb.insert(0,temp.t);
            }
        }
        return sb.toString();
    }

    public static String reverseVowels(String s) {
        HashSet<Character> vowels = new HashSet<>(Arrays.asList('a','e','i','o','u','A','E','I','O','U'));
        char[]  ch = s.toCharArray();
        int p1 = 0, p2=ch.length-1;
        while (p1<p2){
            if(!vowels.contains(ch[p1]))    p1++;
            else if(!vowels.contains(ch[p2]))   p2--;
            else{
                char temp = ch[p1];
                ch[p1]=ch[p2];
                ch[p2]=temp;
                p1++;
                p2--;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(char c : ch)    sb.append(c);
        return sb.toString();
    }

    public static int[] findErrorNums(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int n1=0,n2=0;
        for (int i = 1; i <=nums.length; i++) {
            n2 ^= i ^ nums[i-1];
            if(!set.contains(nums[i-1])){
                set.add(nums[i-1]);
            }else n1 = nums[i-1];
        }
        return new int[]{n1, n2^n1};
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])){
                if(Math.abs(i - map.get(nums[i]))<=k)   return true;
                map.put(nums[i],i);
            }else  map.put(nums[i],i);
        }
        return false;
    }

    public static String intToRoman(int num) {
        HashMap<Integer,String> map = new HashMap<>();
        map.put(1,"I");
        map.put(5,"V");
        map.put(10,"X");
        map.put(50,"L");
        map.put(100,"C");
        map.put(500,"D");
        map.put(1000,"M");
        TreeSet<Integer> set = new TreeSet<>();
        set.add(1);
        set.add(5);
        set.add(10);
        set.add(50);
        set.add(100);
        set.add(500);
        set.add(1000);
        int t = num;
        StringBuffer sb = new StringBuffer();
        while (t>3){
            if(t==4 || t==9){
                sb.append("I");
                t=t+1;
            }
            if((t>=40 && t<50) || (t>=90 && t<100)){
                sb.append("X");
                t=t+10;
            }
            if((t>=400 && t<500) || (t>=900 && t<1000)){
                sb.append("C");
                t=t+100;
            }
            int d = set.floor(t);
            int c = t/d;
            t = t%d;
            String s = map.get(d);
            while (c!=0){
                sb.append(s);
                c--;
            }
        }
        while (t!=0){
            sb.append("I");
            t--;
        }
        return sb.toString();
    }

    public static List<String> topKFrequent(String[] words, int k) {
        List<Pair<Integer,String>> list = new LinkedList<>();
        HashMap<String,Integer> hm = new HashMap<>();
        for (String w : words){
            hm.put(w, hm.getOrDefault(w,0)+1);
        }
        for(String w : hm.keySet()){
            list.add(new Pair<>(hm.get(w),w));
        }
        list.sort((a,b) -> Objects.equals(a.t, b.t) ? a.t1.compareTo(b.t1) : b.t-a.t);
        List<String> ans = new ArrayList<>();
        int c=0;
        for (Pair<Integer, String> integerStringPair : list) {
            if (c <= k)
                ans.add(integerStringPair.t1);
            else break;
            c++;
        }
        return ans;
    }

    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        dp[0]=1;
        dp[1]=1;
        for (int i = 2; i <=n; i++) {
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

    public void deleteNode(ListNode node) {
        ListNode temp = node;
        int hold;
        while (temp.next.next!=null){
            hold = temp.next.val;
            temp.val=hold;
            temp = temp.next;
        }
        hold = temp.next.val;
        temp.val=hold;
        temp.next=null;
    }

    public static boolean increasingTriplet(int[] nums) {
        if(nums.length<3)   return  false;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]<min1){
                min1 = nums[i];
            }else if(nums[i]<min2 && nums[i]>min1){
                min2 = nums[i];
            }else if(nums[i]>min1 && nums[i]>min2){
                return true;
            }
        }
        return false;

        /*if(nums.length<=2)  return false;
        int n = nums.length;
        int[] minLeft = new int[n];
        int[] maxRight = new int[n];
        int min=nums[0];
        for (int i = 0; i < n; i++) {
            min =  Math.min(min,nums[i]);
            minLeft[i] = min;
        }
        int max = nums[n-1];
        for (int i = n-1; i >=0; i--) {
            max =  Math.max(max, nums[i]);
            maxRight[i] = max;
        }
        for (int i = 1; i < nums.length-1; i++) {
            boolean left = minLeft[i - 1] < nums[i];
            boolean right = maxRight[i + 1] > nums[i];
            if(left && right)   return true;
        }
        return false;*/
    }

    public int maxProfit(int[] prices) {
        int ans=0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            ans = Math.max(ans, min-prices[i]);
            min = Math.min(min, prices[i]);
        }
        return ans;
    }

    public static String breakPalindrome(String palindrome) {
        if(palindrome.length()==1)  return "";
        char[] ch = palindrome.toCharArray();
        int i=0;
        boolean f = true;
        while (i<palindrome.length()/2){
            if(ch[i]!='a'){
                f=false;
                ch[i]='a';
                break;
            }else i++;
        }
        if(f)   ch[palindrome.length()-1]='b';
        StringBuilder res = new StringBuilder();
        for (char c : ch) {
            res.append(c);
        }
        return res.toString();
    }

    public static int minRoom(int[] A){
        Arrays.sort(A);
        int ans=0;
        int p=0;
        while (p<A.length){
            ans++;
            p += A[p];
        }
        return ans;
    }

    public static int notContinueThreeSameChar(String S){
        if(S.length()<=2)   return 0;
        int ans=0;
        char ch = S.charAt(0);
        int t=1;
        for (int i = 1; i < S.length(); i++) {
            if(ch==S.charAt(i)){
                t++;
            }else{
                ans += (t/3);
                t=1;
                ch = S.charAt(i);
            }
        }
        ans += (t/3);
        return ans;
    }

    public static List<Integer> pascalTriangleII(int rowIndex) {
        if(rowIndex==0) return new ArrayList<>(Arrays.asList(1));
        List<Integer> ans = new ArrayList<>();
        ans.add(1);
        ans.add(1);
        if(rowIndex==1) return ans;

        for (int i = 2; i <=rowIndex; i++) {
            List<Integer> temp = new ArrayList<>();
            temp.add(1);
            for (int j = 1; j < ans.size(); j++) {
                temp.add(ans.get(j-1)+ans.get(j));
            }
            temp.add(1);
            ans = temp;
        }
        return ans;
    }

    public static int tribonacci(int n) {
        if(n==0)    return 0;
        if(n==1)    return 1;
        if(n==2)    return 2;
        int[] dp = new int[n+1];
        dp[0]=0;
        dp[1]=1;
        dp[2]=1;
        for (int i = 3; i <=n; i++) {
            dp[i]= dp[i-3]+dp[i-2]+dp[i-1];
        }
        return dp[n];
    }

    public static boolean divisorGame(int n) {
        Boolean[] dp = new Boolean[n+1];
        dp[0]=false;
        dp[1]=false;
        if(n+1>2) dp[2]=true;
        return divisorGameHP(n, dp);
//        return dp[n];
    }

    private static boolean divisorGameHP(int n, Boolean[] dp) {
        if(n<=2)    return dp[n];
        if(dp[n]!=null) return dp[n];
        dp[n] = !divisorGameHP(n-1,dp);
        return dp[n];
    }

    public boolean findTarget(TreeNode root, int k) {
        HashSet<Integer> set = new HashSet<>();
        return findTargetHP(root, k , set);
    }

    private boolean findTargetHP(TreeNode root, int k, HashSet<Integer> set) {
        if(root==null)  return false;
        if(set.contains(k-root.val))    return true;
        set.add(root.val);
        return (findTargetHP(root.left, k , set))||(findTargetHP(root.right, k, set));
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res=0, diff = Integer.MAX_VALUE, n = nums.length;
        for (int i = 0; i < n; i++) {
            int first = nums[i];
            int s = i+1;
            int e = n-1;
            while (s<e){
                int sum = first + nums[s]+nums[e];
                if(sum==target) return target;
                else if(Math.abs(sum- target) < diff){
                    diff = Math.abs(sum - target);
                    res = sum;
                }
                if(sum < target) s++;
                else e--;
            }
        }
        return res;
    }

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if(depth==1){
            TreeNode node = new TreeNode(val);
            node.left = root;
            return node;
        }
        int d=0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        d++;
        while (!q.isEmpty()){
            TreeNode curr = q.poll();
            if(curr==null){
                d++;
                if(!q.isEmpty())    q.add(null);
            }
            else if(depth==d+1){
                TreeNode tempLeft = curr.left;
                TreeNode newLeft = new TreeNode(val);
                newLeft.left = tempLeft;
                curr.left=newLeft;

                TreeNode tempRight = curr.right;
                TreeNode newRight = new TreeNode(val);
                newRight.right = tempRight;
                curr.right=newRight;
            }else{
                if(curr.left!=null) q.add(curr.left);
                if(curr.right!=null) q.add(curr.right);

            }
        }
        return root;
    }

    public int numRollsToTarget(int n, int k, int target) {
        Long[][] dp = new Long[n+1][target+1];
        return (int)numRollsToTargetHP(n, k, target, dp);
    }

    private long numRollsToTargetHP(int n, int k, int sum, Long[][] dp) {
        if(n==0){
            if(sum==0)  return 1;
            else    return 0;
        }
        if(sum<0)   return 0;
        if(dp[n][sum]!=null)    return dp[n][sum];

        long ans =0;
        for (int i = 1; i <=k; i++) {
            ans = (ans+ numRollsToTargetHP(n-1,k,sum-i,dp)) % 1000000007;
        }
        dp[n][sum]=ans;
        return ans;
    }

    public int numDecodings(String s) {
        int[] dp = new int[s.length()];
        Arrays.fill(dp, -1);
        return numDecodingsHp(s, dp, s.length()-1);
    }

    private int numDecodingsHp(String s, int[] dp, int i) {
        return 0;
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> ans = new ArrayList<>();
        if(buildings.length==1){
            List<Integer> temp = new ArrayList<>();
            temp.add(buildings[0][0]);
            temp.add(buildings[0][2]);
            ans.add(temp);
            temp= new ArrayList<>();
            temp.add(buildings[0][1]);
            temp.add(0);
            ans.add(temp);
            return ans;
        }

        int maxIndex = 0;
        for (int[] building : buildings) {
            maxIndex = Math.max(maxIndex, building[1]);
        }
        int prev=0, flag=0;
        for (int i = 0; i < maxIndex + 1; i++) {
            int maxH = 0;
            for (int j = 0; j < buildings.length; j++) {
                if(buildings[j][0]>i)   break;
                if(buildings[j][0]<=i && buildings[j][1]>i){
                    maxH = Math.max(maxH, buildings[j][2]);
                }
            }
            if(maxH!=prev){
                List<Integer> temp = new ArrayList<>();
                temp.add(i);
                temp.add(maxH);
                ans.add(temp);
            }
            prev=maxH;
            flag=1;
        }
        if(flag==0){
            for (int i = 0; i < buildings.length; i++) {
                List<Integer> temp = new ArrayList<>();
                temp.add(buildings[i][0]);
                temp.add(buildings[i][2]);
                ans.add(temp);
                temp=new ArrayList<>();
                temp.add(buildings[i][1]);
                temp.add(0);
                ans.add(temp);
            }
        }
        return ans;
    }

    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        PriorityQueue<Pair<Integer,Integer>> maxHeap = new PriorityQueue<>((a,b)-> b.t-a.t);
        for (int i=0; i<arr.length; i++){
            Pair<Integer,Integer> p;
            if(maxHeap.size()==k){
                p= maxHeap.peek();
                if (p.t > Math.abs(arr[i] - x)) {
                    maxHeap.poll();
                    maxHeap.add(new Pair<>(Math.abs(arr[i]-x),i));
                }
            }else{
                maxHeap.add(new Pair<>(Math.abs(arr[i]-x),i));
            }
        }
        List<Integer> ans = new ArrayList<>();
        while (!maxHeap.isEmpty()){
            ans.add(arr[maxHeap.poll().t1]);
        }
        Collections.sort(ans);
        return ans;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int len = sizeOfList(head);
        if(n==len){
            head=head.next;
            return head;
        }
        ListNode temp = head;
        int index=len-n;
        int i=0;
        while (i!=index-1){
            i++;
            temp=temp.next;
        }
        temp.next= temp.next.next;
        return head;
    }

    private static int sizeOfList(ListNode head) {
        ListNode temp = head;
        int len=0;
        while (temp!=null){
            len++;
            temp=temp.next;
        }
        return len;
    }

    public static String pushDominoes(String dominoes) {
        int n = dominoes.length();
        char[] ans = new char[n];
        Arrays.fill(ans,'.');
        int lp=0,rp=0, i=0;
        while (lp<n && rp<n && i<n){
            if(dominoes.charAt(i)=='L'){
                ans[i]='L';
                int temp = i-1;
                while (temp>=0 && dominoes.charAt(temp)=='.'){
                    ans[temp]='L';
                    temp--;
                }
                lp=i;
            }else if(dominoes.charAt(i)=='R'){
                ans[i]='R';
                int temp = i+1, len=0;
                while (temp<n && dominoes.charAt(temp)=='.'){
                    len++;
                    temp++;
                }
                if(temp==n || dominoes.charAt(temp)=='R'){
                    int a=0;
                    while (a!=len){
                        ans[i+1]='R';
                        i++;
                        a++;
                    }
                }else if(dominoes.charAt(temp)=='L'){
                    ans[temp]='L';
                    i++;
                    if(len%2==0){
                        int a=0;
                        while (a!=(len/2)){
                            ans[i++]='R';
                            a++;
                        }
                        a=0;
                        while (a!=(len/2)){
                            ans[i++]='L';
                            a++;
                        }
                    }else{
                        int a=0;
                        while (a!=(len/2)){
                            ans[i++]='R';
                            a++;
                        }
                        a=0;
                        i++;
                        while (a!=(len/2)){
                            ans[i++]='L';
                            a++;
                        }
                    }
                }
                rp=i;
            }
            i++;
        }
        StringBuffer sb = new StringBuffer();
        for (char c: ans){
            sb.append(c);
        }
        return sb.toString();
    }

    public boolean equationsPossible(String[] equations) {
        int[] root = new int[26];
        for (int i = 0; i < 26; i++) {
            root[i]=i;
        }
        for(String s : equations){
            if(s.charAt(1)=='='){
                int a = find(root, s.charAt(0)-'a');
                int b = find(root, s.charAt(3)-'a');
                if(a!=b)    root[b]=a;
            }
        }
        for(String s : equations){
            if(s.charAt(1)=='!'){
                if(find(root,s.charAt(0)-'a')== find(root, s.charAt(3)-'a'))  return false;
            }
        }
        return true;
    }

    private int find(int[] root, int i) {
        if(root[i]==i) return i;
        else return find(root,root[i]);
    }


    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root!=null)      pathSumHp(root, targetSum, ans , new ArrayList<>(), 0);
        return ans;
    }

    private void pathSumHp(TreeNode root, int targetSum, List<List<Integer>> ans, ArrayList<Integer> path, int currSum) {
        if(root.left==null && root.right==null){
            currSum+=root.val;
            if(currSum==targetSum){
                path.add(root.val);
                ans.add( new ArrayList<>(path));
                path.remove(path.size()-1);
            }
            currSum-=root.val;
            return;
        }
        currSum += root.val;
        path.add(root.val);
        if(root.left!=null) pathSumHp(root.left, targetSum, ans, path, currSum);
        if(root.right!=null) pathSumHp(root.right, targetSum, ans, path, currSum);
        currSum -=root.val;
        path.remove(path.size()-1);
    }


    public static int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        int sum =0;
        int[] ans = new int[queries.length];
        for (int i : nums){
            if(i%2==0)  sum+=i;
        }
        int i=0;
        for (int[] q : queries){
            int v = q[0];
            int index = q[1];
            if (nums[index] % 2 == 0) {
                sum -= nums[index];
            }
            if((nums[index]+v)%2==0){
                sum += nums[index]+v;
            }
            nums[index] += v;
            ans[i++]=sum;
        }
        return ans;
    }

    public int findLength(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int ans = 0;
        int[][] dp = new int[n+1][m+1];
        for (int i = 1; i <=n; i++) {
            for (int j = 1; j <=m; j++) {
                if(nums1[i-1]==nums2[j-1]){
                    dp[i][j] = 1+dp[i-1][j-1];
                    ans = Math.max(ans,dp[i][j]);
                }else   dp[i][j]=0;
            }
        }
        return ans;
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        HashMap<String,Integer> wordMap = new HashMap<>();
        Set<Integer> set = new TreeSet<>();
        int n = words.length;

        for(int i=0;i<n;i++){
            wordMap.put(words[i],i);
            set.add(words[i].length());
        }

        List<List<Integer>> ans = new ArrayList<>();

        for(int i=0;i<n;i++){
            int length = words[i].length();

            if(length ==1){
                if(wordMap.containsKey("")){
                    ans.add(Arrays.asList(i, wordMap.get("")));
                    ans.add(Arrays.asList(wordMap.get(""), i));
                }
                continue;
            }
            String reverse= new StringBuilder(words[i]).reverse().toString();
            if(wordMap.containsKey(reverse) && wordMap.get(reverse) != i)
                ans.add(Arrays.asList(i,wordMap.get(reverse)));

            for(Integer k:set){
                if(k==length)
                    break;
                if(isPalindrome(reverse,0,length-1-k)){
                    String s1 = reverse.substring(length-k);
                    if(wordMap.containsKey(s1))
                        ans.add(Arrays.asList(i,wordMap.get(s1)));
                }

                if(isPalindrome(reverse,k,length-1)){
                    String s2 = reverse.substring(0,k);
                    if(wordMap.containsKey(s2))
                        ans.add(Arrays.asList(wordMap.get(s2),i));
                }
            }
        }
        return ans;
    }
    private boolean isPalindrome(String s, int left, int right){
        while(left<right)
            if(s.charAt(left++)!=s.charAt(right--))
                return false;
        return true;
    }

    public static int[] findOriginalArray(int[] changed) {
        if(changed.length%2==1) return new int[0];
        int len = changed.length;
        Arrays.sort(changed);
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i : changed){
            map.put(i, map.getOrDefault(i,0)+1);
        }
        int[] res = new int[len/2];
        int k = 0;
        for(int i=0; i<len; i++){
            int ele = changed[i];

            // if map contains 'ele'
            if(map.containsKey(ele)){

                // if map contains 'ele*2'
                if(map.containsKey(ele*2)){
                    res[k++] = ele;

                    // reduce frequency of 'ele' and 'ele*2'
                    map.put(ele,map.get(ele)-1);
                    map.put(ele*2,map.get(ele*2)-1);

                    // if freq of any key becomes <=0, remove it from map
                    if(map.get(ele)<=0) map.remove(ele);
                    if(map.containsKey(ele*2) && map.get(ele*2)<=0) map.remove(ele*2);
                }
                else return new int[0];
            }

        }
        return res;
    }

    static int count=0;
    public static int pseudoPalindromicPaths (TreeNode root) {
        int[] freq = new int[10];
        pseudoPalindromicPathsHelper(root, freq);
        return count;
    }
    public static void pseudoPalindromicPathsHelper(TreeNode root, int[] freq ){
        if(root==null)  return;
        if(root.left==null && root.right==null){
            freq[root.val]++;
            if(ableToMakePalindromic(freq)) {
                count++;
            }
            freq[root.val]--;
            return;
        }
        freq[root.val]++;
        pseudoPalindromicPathsHelper(root.left, freq);
        pseudoPalindromicPathsHelper(root.right, freq);
        freq[root.val]--;
    }

    private static boolean ableToMakePalindromic(int[] freq) {
        int odd=0;
        for(int i: freq){
            if((i&1)==1)    odd++;
            if(odd>1)   return false;
        }
        return true;
    }

    public static int bagOfTokensScore(int[] tokens, int power) {
        if(tokens.length==1){
            if(tokens[0]<=power) return 1;
            else return 0;
        }
        Arrays.sort(tokens);
        int p1=0, p2 = tokens.length-1;
        int ans=0, curr=0;
        while (p1<=p2){
            if(power>= tokens[p1]){
                curr++;
                power -= tokens[p1];
                p1++;
                ans = Math.max(ans,curr);
            }else if(curr>=1){
                curr--;
                power += tokens[p2];
                p2--;
            }else   break;
        }
        return ans;
    }

    public List<List<Integer>> levelOrder(Node root) {
        if(root==null) return new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        List<Integer> temp = new ArrayList<>();
        while (!q.isEmpty()){
            Node node = q.poll();
            if(node==null){
                ans.add(temp);
                temp=new ArrayList<>();
                if(!q.isEmpty()) q.add(null);
            }else{
                temp.add(node.val);
                q.addAll(node.children);
            }
        }
        return ans;
    }

    public List<List<Integer>> verticalTraversal(TreeNode A) {
        List<List<Integer>> ans = new ArrayList<>();
        HashMap<Integer, HashMap<Integer,ArrayList<TreeNode>> > hm = new HashMap<>();
        int minLevel = Integer.MAX_VALUE;
        int maxLevel = Integer.MIN_VALUE;
        int maxHLeve = Integer.MIN_VALUE;
        Queue<Pair<TreeNode,Pair<Integer,Integer>>> q = new LinkedList<>();
        q.add(new Pair<>(A,new Pair<>(0,0)));
        while (!q.isEmpty()){
            Pair<TreeNode,Pair<Integer,Integer>> p = q.peek();
            q.remove();
            TreeNode n = p.t;
            int vLevel = p.t1.t;
            int hLevel = p.t1.t1;
            minLevel = Math.min(minLevel,vLevel);
            maxLevel = Math.max(maxLevel,vLevel);
            maxHLeve =hLevel;
            HashMap<Integer,ArrayList<TreeNode>> verticalNodes;
            if(!hm.containsKey(vLevel)){
                verticalNodes = new HashMap<>();
            }else{
                verticalNodes = hm.get(vLevel);
            }
            ArrayList<TreeNode> nodes;
            if(!verticalNodes.containsKey(hLevel)){
                nodes= new ArrayList<>();
            }else   nodes= verticalNodes.get(hLevel);
            nodes.add(n);
            verticalNodes.put(hLevel,nodes);
            hm.put(vLevel,verticalNodes);

            if(n.left!=null)    q.add(new Pair<>(n.left,new Pair<>(vLevel-1,hLevel+1)));
            if(n.right!=null)    q.add(new Pair<>(n.right,new Pair<>(vLevel+1,hLevel+1)));
        }
        for (int i = minLevel; i <=maxLevel; i++) {
            ArrayList<Integer> values = new ArrayList<>();

            HashMap<Integer,ArrayList<TreeNode>> val = hm.get(i);
            for(int j=0; j<=maxHLeve; j++){
                ArrayList<Integer> temp = new ArrayList<>();
                for(TreeNode node: val.getOrDefault(j,new ArrayList<>()))   temp.add(node.val);
                Collections.sort(temp);
                values.addAll(temp);
            }
            ans.add(values);
        }
        return ans;
    }

    public int[] numsSameConSecDiff(int n, int k) {
        ArrayList<Integer> ans = new ArrayList<>();
        for (int num = 1; num <10; num++) {
            dfsForNumsSameConSecDiff(num, num, n-1, k, ans);
        }
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i]=ans.get(i);
        }
        return res;
    }
    private void dfsForNumsSameConSecDiff(int num, int digit, int currDigit, int k, ArrayList<Integer> ans) {
        if(currDigit==0){
            ans.add(num);
            return;
        }
        if(digit+k<=9){
            dfsForNumsSameConSecDiff(num*10+(digit+k), digit+k, currDigit-1, k, ans);
        }
        if(k!=0 && digit-k>=0){
            dfsForNumsSameConSecDiff(num*10+(digit-k), digit-k, currDigit-1, k, ans);
        }
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int r = heights.length;
        int c = heights[0].length;
        boolean[][] pac = new boolean[r][c];
        boolean[][] atl = new boolean[r][c];
        int[] x = new int[]{-1,1,0,0};
        int[] y = new int[]{0,0,-1,1};
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                //pacific
                if(i==0 || j==0){
                    dfsForPacAtlProblem(heights, pac, i, j, 0, x, y);
                }
                //Atlantic
                if(i == r-1 || j == c-1){
                    dfsForPacAtlProblem(heights, atl, i, j, 0, x, y);
                }
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(pac[i][j] && atl[i][j]){
                    ans.add( new ArrayList<>(Arrays.asList(i,j)));
                }
            }
        }
        return ans;
    }
    private void dfsForPacAtlProblem(int[][] heights, boolean[][] sea, int i, int j, int prev, int[] x, int[] y) {
        if(i<0 || i>=heights.length || j<0 || j>=heights[0].length || sea[i][j] || prev > heights[i][j])  return;

        sea[i][j]=true;
        for (int k = 0; k < x.length; k++) {
            dfsForPacAtlProblem(heights, sea, i+x[k], j+y[k], heights[i][j],x,y);
        }
    }

    public int numIslands(char[][] grid) {
        int count=0;
        int n = grid.length;
        int m = grid[0].length;
        int[] x = new int[]{-1,1,0,0};
        int[] y = new int[]{0,0,-1,1};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(grid[i][j]=='1'){
                    count++;
                    dfsForIslands(grid, i, j, x,y);
                }
            }
        }
        return count;
    }
    private void dfsForIslands(char[][] grid, int i, int j, int[] x, int[] y) {
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || grid[i][j]=='0')    return;
        grid[i][j]='0';
        for (int k = 0; k < x.length; k++) {
            dfsForIslands(grid, i+x[k], j+y[k], x, y);
        }
    }

    public static int maxSumSubMatrix(int[][] matrix, int k) {
        int ans=Integer.MIN_VALUE;
        int r = matrix.length;
        int c = matrix[0].length;
        int[] arr;

        for (int i = 0; i < r; i++) {
            arr = matrix[i];
            ans = Math.max(ans, maxSumSubArrayLessThanK(arr,k));
            for (int j = i+1; j < r; j++) {
                for (int l = 0; l < c; l++) {
                    arr[l]+=matrix[j][l];
                }
                ans = Math.max(ans, maxSumSubArrayLessThanK(arr,k));
            }
        }
        return ans;
    }
    public static int maxSumSubArrayLessThanK(int[] arr, int k){
        int max = Integer.MIN_VALUE;
        int sum=0;
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        for (int i = 0; i < arr.length; i++) {
            sum +=arr[i];
            if(sum==k)  return k;
            Integer gap = set.ceiling(sum-k);
            if(gap!=null)   max = Math.max(max, sum-gap);
            set.add(sum);
        }
        return max;
    }

    public boolean reorderedPowerOf2(int n) {
        int[] countNumber = countDigit(n);
        int num =1;
        for (int i = 0; i <31; i++) {
            if(Arrays.equals(countNumber,countDigit(num))){
                return true;
            }
            num = num<<1;
        }
        return false;
    }
    public static int[] countDigit(int n){
        int[] ans = new int[10];
        while (n>0){
            ans[n%10]++;
            n /=10;
        }
        return ans;
    }

    public boolean isPalindrome(ListNode head) {
        ListNode t = head;
        int n=0;
        while (t!=null){
            n++;
            t=t.next;
        }
        int mid = n/2;
        ListNode prev=null, next=null;
        ListNode curr = head;
        while (mid!=0){
            mid--;
            next= curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        if(n%2!=0)  curr=curr.next;
        while (curr!=null){
            if(prev.val!=curr.val)  return false;
            curr=curr.next;
            prev=prev.next;
        }
        return true;
    }

    private static boolean check(char[] s, char[] t, int ind){
        for (int j = 0; j < s.length; j++) {
            if(t[j+ind]!='*' && s[j]!=t[j+ind]) return false;
        }
        return true;
    }
    private static int replace(char[] s, char[] t, int ind){
        int c=0;
        for (int j = 0; j < s.length; j++) {
            if(t[j+ind]!='*'){
                c++;
                t[j+ind]='*';
            }
        }
        return c;
    }
    public static int[] movesToStamp(String stamp, String target) {
        char[] s = stamp.toCharArray();
        char[] t = target.toCharArray();
        ArrayList<Integer> ans = new ArrayList<>();
        boolean[] vis = new boolean[t.length];
        boolean flag=true;
        int star=0;
        while (star<target.length()){
            flag=false;
            for (int i = 0; i <=t.length - s.length; i++) {
                if(!vis[i] && check(s,t,i)){
                    star += replace(s,t,i);
                    flag=true;
                    vis[i]=true;
                    ans.add(i);
                }
            }
            if(!flag)   return new int[0];
        }
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i]=ans.get(ans.size()-i-1);
        }
        return res;
    }

    public static int minRefuelStops(int target, int startFuel, int[][] stations) {
        if(stations.length==0) return target<=startFuel?0:-1;
        PriorityQueue<int[]> max_heap = new PriorityQueue<>((a,b)->b[1]-a[1]);
        if(startFuel<stations[0][0])    return -1;
        int ans =0;
        int index =0;
        int max_reach=startFuel;
        while (max_reach<target){
            while (index<stations.length && stations[index][0]<=max_reach){
                max_heap.add(stations[index]);
                index++;
            }
            if(max_heap.isEmpty())  return -1;
            max_reach += max_heap.poll()[1];
            ans++;
        }
        return ans;
    }

    public boolean isPossibleToSplitArray(int[] nums) {
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        HashMap<Integer, Integer> hypotheticalMap = new HashMap<>();

        for(int i : nums){
            frequencyMap.put(i, frequencyMap.getOrDefault(i, 0) + 1);
        }

        for(int i : nums){

            int fromFm = frequencyMap.getOrDefault(i, 0);
            int fromHm = hypotheticalMap.getOrDefault(i, 0);

            if(fromFm == 0) continue;

            if(fromHm > 0) {
                hypotheticalMap.put(i, fromHm - 1);
                hypotheticalMap.put(i+1, hypotheticalMap.getOrDefault(i + 1, 0) + 1);
                frequencyMap.put(i, fromFm - 1);
            }
            else if(frequencyMap.getOrDefault(i, 0) > 0 && frequencyMap.getOrDefault(i + 1, 0) > 0 && frequencyMap.getOrDefault(i + 2, 0) > 0) {
                frequencyMap.put(i, frequencyMap.get(i) - 1);
                frequencyMap.put(i + 1, frequencyMap.get(i + 1) - 1);
                frequencyMap.put(i + 2, frequencyMap.get(i + 2) - 1);
                hypotheticalMap.put(i+3, hypotheticalMap.getOrDefault(i + 3, 0) + 1);

            }
            else {
                return false;
            }
        }
        return true;
    }

    public static int minSetSize(int[] arr) {
        HashMap<Integer,Integer> hm = new HashMap<>();
        for(int a : arr){
            hm.put(a, hm.getOrDefault(a,0)+1);
        }
        Integer[] valueSet = new Integer[hm.size()];
        hm.values().toArray(valueSet);
        Arrays.sort(valueSet,(i, t1) -> t1-i);
        int ans=0,sum=0;
        int half = arr.length/2;
        for (int a : valueSet){
            if(sum<half){
                sum+=a;
                ans++;
            }else  break;
        }
        return ans;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return constructBST(nums, 0, nums.length-1);
    }
    private TreeNode constructBST(int[] nums, int s, int e){
        if(s>e) return null;
        int mid = (s+e)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = constructBST(nums, s, mid-1);
        root.right = constructBST(nums, mid+1,e);
        return root;
    }

    public int numFactoredBinaryTrees(int[] arr) {
        int M = 1000000007;
        Arrays.sort(arr);
        HashMap<Integer, Set<Integer>> factor = new HashMap<>();
        for (int j : arr) {
            factor.put(j, getFactor(j));
        }

        long totalBT=0L;
        HashMap<Integer,Long> subTreeOfFactor = new HashMap<>();
        for (int num : arr) {
            Set<Integer> numFactor = factor.get(num);
            long subBT = 0L;
            for (int f : numFactor){
                long x = subTreeOfFactor.getOrDefault(f,0L);
                long y = subTreeOfFactor.getOrDefault(num/f,0L);
                subBT = (subBT+x*y)%M;
            }
            subBT++;    //add itself as root of subtree
            subTreeOfFactor.put(num,subBT);
            totalBT = (totalBT+subBT)%M;
        }
        return (int) totalBT;
    }
    private Set<Integer> getFactor(int num) {
        Set<Integer> factor = new HashSet<>();
        for (int j = 2; j <= Math.sqrt(num); j++) {
            if(num%j==0) {
                factor.add(j);
                factor.add(num/j);
            }
        }
        return factor;
    }

    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        return (int) Math.ceil(Math.log(buckets)/Math.log((minutesToTest/minutesToDie)+1));
    }

    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        dp[0]=1;
        for (int i = 1; i <=target; i++) {
            for(int num:nums){
                if(num<=i)  dp[i] += dp[i-num];
            }
        }
        return dp[target];
    }

    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> min_heap = new PriorityQueue<>();
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                min_heap.add(anInt);
            }
        }
        for (int i = 0; i < k-1; i++) {
            min_heap.poll();
        }
        return min_heap.poll();
    }

    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        uniquePathsHelper(m,n,m-1,n-1,dp);
        return dp[m-1][n-1];
    }
    private static int uniquePathsHelper(int m, int n, int i, int j, int[][] dp) {
        if(i==0 && j==0) {
            dp[i][j]=1;
            return dp[i][j];
        }
        if(dp[i][j]==0){
            if(i>0) dp[i][j] += uniquePathsHelper(m,n,i-1,j,dp);
            if(j>0) dp[i][j] += uniquePathsHelper(m,n,i,j-1,dp);
//            dp[i][j]= uniquePathsHelper(m,n,i-1,j,dp)+uniquePathsHelper(m,n,i,j-1,dp);
        }
        return dp[i][j];
    }

    public List<String> wordSubsets(String[] words1, String[] words2) {
        int[] word2Freq = getMaxFreq(words2);
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < words1.length; i++) {
            int[] word1Freq = getFreq(words1[i]);
            boolean flag = true;
            for (int j = 0; j < 26; j++) {
                if(word1Freq[j]<word2Freq[j]){
                    flag=false;
                    break;
                }
            }
            if(flag)    ans.add(words1[i]);
        }
        return ans;
    }
    private int[] getMaxFreq(String[] words2) {
        int[] frequencies = new int[26];
        for (int i = 0; i < words2.length; i++) {
            int[] wordFreq = getFreq(words2[i]);
            for (int j = 0; j < 26; j++) {
                frequencies[j] = Math.max(wordFreq[j],frequencies[j]);
            }
        }
        return frequencies;
    }
    private int[] getFreq(String s) {
        int[] c = new int[26];
        for (char ch : s.toCharArray()) c[ch-'a']++;
        return c;
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        ArrayList<Integer> merge = new ArrayList<>();
        int p1=0, p2=0;
        int n=nums1.length, m=nums2.length;
        while (p1<n && p2<m){
            if(nums1[p1]<=nums2[p2]){
                merge.add(nums1[p1]);
                p1++;
            }else{
                merge.add(nums2[p2]);
                p2++;
            }
        }
        while (p1<n){
            merge.add(nums1[p1++]);
        }
        while (p2<m){
            merge.add(nums2[p2++]);
        }
        int size = merge.size()%2==0 ? merge.size()+1:merge.size();
        int mid = size/2 ;
        if(merge.size()%2==0){
            return  ((double) (merge.get(mid)+merge.get(mid-1)))/2;
        }else{
            return merge.get(mid);
        }
    }

    public boolean isAnagram(String s, String t) {
        HashMap<Character,Integer> sMap = new HashMap<>();
        HashMap<Character,Integer> tMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            sMap.put(s.charAt(i), sMap.getOrDefault(s.charAt(i),0)+1);
        }
        for (int i = 0; i < t.length(); i++) {
            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i),0)+1);
        }
        if(sMap.size()!=tMap.size()) return false;
        for (Map.Entry<Character,Integer> entry : tMap.entrySet()) {
            if(!Objects.equals(sMap.get(entry.getKey()), entry.getValue()))  return false;
        }
        return true;
    }

    public void flatten(TreeNode root) {
        TreeNode curr=root;
        while (curr!=null){
            if(curr.left!=null){
                TreeNode rtMost = curr.left;
                while (rtMost.right!=null)  rtMost = rtMost.right;
                rtMost.right = curr.right;
                curr.right = curr.left;
                curr.left=null;
            }
            curr= curr.right;
        }
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int n=matrix.length;
        int m=matrix[0].length;
        int r=0, c=m-1;
        while (r<n && c>=0){
            if(matrix[r][c]==target)    return true;
            else if(matrix[r][c]>target){
                c--;
            }else   r++;
        }
        return false;
        /*int r = matrix.length;
        int c = matrix[0].length;
        if(r==1){
            int index = binarySearchInArr(matrix[0],target);
            return index < c && matrix[0][index] == target;
        }
        int[] lastCol = new int[r];
        for (int i = 0; i < r; i++) {
            lastCol[i]=matrix[i][c-1];
        }
        int index = binarySearchInArr(lastCol,target);
        if(index>=r)    return false;
        if(lastCol[index]==target)  return true;
        else{
            int ans = binarySearchInArr(matrix[index],target);
            return ans<c && matrix[index][ans]==target;
        }*/
    }

    private int binarySearchInArr(int[] matrix, int target) {
        int l =0, h=matrix.length-1;
        int ans=0;
        while (l<=h){
            int m = (l+h)/2;
            if(matrix[m]==target)   return m;
            else if(matrix[m]<target){
                ans=m+1;
                l=m+1;
            }else   h=m-1;
        }
        return ans;
    }

    public static List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        List<Integer> ans = new ArrayList<>();
        ArrayList<Integer> set = new ArrayList<>();
        for (int i = n-1; i>=0; i--) {
            int index = binarySearchInSet(set,nums[i]);
            set.add(index,nums[i]);
            ans.add(index);
        }
        Collections.reverse(ans);
        return ans;
    }
    private static int binarySearchInSet(ArrayList<Integer> set, int num) {
        int l =0, h=set.size()-1;
        while (l<=h){
            int mid = (l+h)/2;
            if(mid==0){
                if(set.get(mid)>=num)   return 0;
                else if(set.get(h)<num)   return h+1;
                else return 1;
            }
            if(set.get(mid)>=num && set.get(mid-1)<num) {
                return mid;
            }else if(set.get(mid)>=num && set.get(mid-1)>=num)  h=mid-1;
            else l=mid+1;
        }
        return set.size();
    }

    public ListNode partition(ListNode head, int x) {
        if(head==null || head.next==null)  return head;
        ListNode t1 = null,t2 = null;
        ListNode h1 = null,h2 = null;
        ListNode curr=head;
        while (curr!=null){
            if(curr.val<x){
                if(t1 == null){
                    t1=curr;
                    h1=t1;
                }else {
                    t1.next = curr;
                    t1=t1.next;
                }
            }else{
                if(t2 == null){
                    t2=curr;
                    h2=t2;
                }else {
                    t2.next = curr;
                    t2=t2.next;
                }
            }
            curr=curr.next;
            if(t1!=null)    t1.next=null;
            if(t2!=null)    t2.next=null;
        }
        if(t1!=null)    t1.next=h2;
        else    return h2;
        return h1;
    }

    public int numMatchingSubseq(String s, String[] words) {
        HashMap<String, Integer> hm = new HashMap<>();
        for (String str : words){
            hm.put(str, hm.getOrDefault(str,0)+1);
        }
        int ans=0;
        char[] sChar = s.toCharArray();
        for(String str : hm.keySet()){
            char[] temp = str.toCharArray();
            int i=0,j=0;
            while (i<sChar.length && j<temp.length){
                if(sChar[i]==temp[j]){
                    i++;j++;
                }else   i++;
            }
            if(j==temp.length)  ans+=hm.get(str);
        }
        return ans;
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>(Collections.nCopies(1,1)));
        for (int i = 1; i < numRows; i++) {
            List<Integer> t = new ArrayList<>();
            t.add(1);
            for (int j = 1; j < i; j++) {
                t.add(ans.get(i-1).get(j)+ans.get(i-1).get(j-1));
            }
            t.add(1);
            ans.add(t);
        }
        return ans;
    }

    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        int res = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 1; j < col; j++) {
                matrix[i][j] += matrix[i][j-1];
            }
        }

        for (int start = 0; start < col; start++) {
            for (int end = start; end < col; end++) {
                int subMatSum = 0;
                Map<Integer,Integer> countElm = new HashMap<>();
                countElm.put(0,1);
                for (int k = 0; k < row; k++) {
                    int pf = start==0?0:matrix[k][start-1];
                    subMatSum += matrix[k][end] - pf;

                    if(countElm.containsKey(subMatSum-target)){
                        res += countElm.get(subMatSum-target);
                    }
                    countElm.put(subMatSum, countElm.getOrDefault(subMatSum,0)+1);
                }
            }
        }
        return res;
    }

    public int findFootballPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        long[][][] dp = new long[m][n][maxMove+1];
        for(long[][] e1: dp) {
            for(long[] e2: e1) {
                Arrays.fill(e2, -1);
            }
        }
        return (int) findFootballPathsDFS(m, n, maxMove, startRow, startColumn, dp);
    }

    private long findFootballPathsDFS(int m, int n, int maxMove, int i, int j, long[][][] dp) {
        if(i<0 || j<0 || i>=m || j>=n)  return 1;
        if(maxMove==0)  return 0;
        if(dp[i][j][maxMove]>=0)    return dp[i][j][maxMove];
        long r = 0;
        r += findFootballPathsDFS(m,n,maxMove-1,i-1,j,dp);
        r%=1000000007;
        r += findFootballPathsDFS(m,n,maxMove-1,i+1,j,dp);
        r%=1000000007;
        r += findFootballPathsDFS(m,n,maxMove-1,i,j-1,dp);
        r%=1000000007;
        r += findFootballPathsDFS(m,n,maxMove-1,i,j+1,dp);
        r%=1000000007;
        dp[i][j][maxMove]=r;
        return r;
    }

    public static int maxAreaOfIsland(int[][] grid) {
        int max=0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j]==1){
                    max = Math.max(max, dfs(grid, i, j));
                }
            }
        }
        return max;
    }

    private static int dfs(int[][] grid, int i, int j) {
        if(i<0 || i>=grid.length ||  j<0 || j>=grid[i].length || grid[i][j]==0) return 0;
        grid[i][j]=0;
        return 1+ dfs(grid,i-1,j)+ dfs(grid,i+1,j)+ dfs(grid, i, j-1)+ dfs(grid, i, j+1);
    }

    public static boolean makeSquare(int[] matchsticks) {
        if(matchsticks.length<4)    return false;
        int total =0;
        for(int i:matchsticks) total+=i;
        if(total%4!=0)  return false;
        int target = total/4;
        boolean[] used = new boolean[matchsticks.length];
        return makeSquareHelper(matchsticks, used, target, 0, 0, 0);
    }

    private static boolean makeSquareHelper(int[] matchsticks, boolean[] used, int target, int count, int currSum, int start) {
        if(count==3)    return true;
        if(currSum>target)  return false;
        if(currSum== target)    return makeSquareHelper(matchsticks, used, target, count+1, 0, 0);
        for (int i = start; i < matchsticks.length; i++) {
            if(!used[i]){
                used[i]=true;
                if(makeSquareHelper(matchsticks, used, target, count, currSum+matchsticks[i], i+1)){
                    return true;
                }
                used[i]=false;
            }
        }
        return false;
    }

    public static int minCostClimbingStairs(int[] cost) {
        if(cost.length==2)  return Math.min(cost[0],cost[1]);
        if(cost.length==3)  return Math.min(cost[0]+cost[2],cost[1]);
        int n = cost.length;
        int[] price = new int[n];
        price[n-1] = cost[n-1];
        price[n-2] = cost[n-2];
        for (int i = cost.length-3; i >=0; i--) {
            price[i] = cost[i]+Math.min(price[i+1],price[i+2]);
        }
        return Math.min(price[0],price[1]);
    }

    public static int maxResult(int[] nums, int k) {
        PriorityQueue<int[]> max_heap = new PriorityQueue<>((a,b)-> b[0]-a[0]);
        max_heap.add(new int[]{nums[0],0});
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            while (max_heap.peek()[1]<(i-k))    max_heap.poll();
            max_heap.add(new int[]{ans=max_heap.peek()[0]+nums[i],i});
        }
        return ans;
    }

    public static boolean isInterleave(String s1, String s2, String s3) {
        int m=s1.length(),n=s2.length();
        if(m+n!=s3.length())    return false;
        if(s1.length()==0)  return s2.equals(s3);
        if(s2.length()==0)  return s1.equals(s3);
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0]=true;

        for (int i = 1; i < m+1; i++) {
            dp[i][0] = dp[i-1][0] && s1.charAt(i-1)==s3.charAt(i-1);
        }
        for (int i = 1; i < n + 1; i++) {
            dp[0][i] = dp[0][i-1] && s2.charAt(i-1)==s3.charAt(i-1);
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                dp[i][j] = (dp[i-1][j] && s1.charAt(i-1)==s3.charAt(i+j-1))
                        || (dp[i][j-1] && s2.charAt(j-1)==s3.charAt(i+j-1));
            }
        }
        return dp[m][n];
    }

    public static int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        int M = 1000000007;
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        long hMax=horizontalCuts[0];
        long wMax=verticalCuts[0];
        for (int i = 1; i < horizontalCuts.length ; i++) {
            hMax = Math.max(hMax, Math.abs(horizontalCuts[i]-horizontalCuts[i-1]));
        }
        hMax = Math.max(hMax, Math.abs(h-horizontalCuts[horizontalCuts.length-1]));
        hMax = (hMax)%M;
        for (int i = 1; i < verticalCuts.length ; i++) {
            wMax = Math.max(wMax, Math.abs(verticalCuts[i]-verticalCuts[i-1]));
        }
        wMax = Math.max(wMax, Math.abs(w-verticalCuts[verticalCuts.length-1]));
        wMax = (wMax)%M;
        return (int)((hMax*wMax)%M);
    }

    public static int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a,b)-> a[1]!=b[1]? b[1]-a[1]:b[0]-a[0]);
        int count=0, ans=0;
        for (int[] boxType : boxTypes) {
            for (int j = 0; j < boxType[0]; j++) {
                if (count < truckSize) {
                    ans += boxType[1];
                    count++;
                } else break;
            }
            if (count == truckSize) break;
        }
        return ans;
    }

    public static int minMoves2(int[] nums) {
        int n= nums.length;
        Arrays.sort(nums);
        int d = (nums[0]+nums[n-1])/2;
        int mid = nums[n/2], ans =0;
        for(int i :nums){
            ans += Math.abs(i-mid);
        }
        return ans;
    }

    public int[][] reconstructQueue(int[][] people) {
        //Sort base on height dec order and k inc order
        Arrays.sort(people, (a,b) -> a[0]==b[0]?a[1]-b[1]:b[0]-a[0]);
        List<int[]> ordered = new LinkedList<>();
        for(int[] p : people)   ordered.add(p[1],p);
        return ordered.toArray(new int[people.length][2]);
    }

    public static int minDeletions(String s) {
        HashMap<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            hm.put(s.charAt(i), hm.getOrDefault(s.charAt(i),0)+1);
        }
        HashSet<Integer> uniqueFrq = new HashSet<>();
        int ans = 0;
        for (char c: hm.keySet()){
            int frq = hm.get(c);
            if(!uniqueFrq.contains(frq)){
                uniqueFrq.add(frq);
            }else{
                while (frq>0 && uniqueFrq.contains(frq)){
                    frq--;
                    ans++;
                }
                if(frq>0)   uniqueFrq.add(frq);
            }
        }
        return ans;
    }

    public static int minPartitions(String n) {
        int ans = 0;
        for (int i = 0; i < n.length(); i++) {
            ans = Math.max(ans , Integer.parseInt(n.charAt(i)+""));
        }
        return ans;
    }

    public static int maxScore(int[] cardPoints, int k) {
        int total = cardPoints[0];
        for (int i = 1; i < cardPoints.length; i++) {
            total += cardPoints[i];
        }
        int window = cardPoints.length-k;
        int max;
        int currSum = 0;
        for (int i = 0; i < window; i++) {
            currSum +=cardPoints[i];
        }
        max = total-currSum;
        for (int i = window; i < cardPoints.length; i++) {
            currSum = currSum - cardPoints[i-window] + cardPoints[i];
            max = Math.max(max,total-currSum);
        }
        return max;
    }

    //possible to convert non-decreasing array by modify one elm
    public static boolean checkPossibility(int[] nums) {
        if(nums.length==1)  return true;
        int ableToModify = 0;
        for (int i = 0; i < nums.length - 1 && ableToModify<=1; i++) {
            if(nums[i]>nums[i+1]){
                ableToModify++;
                if(i-1<0 || nums[i-1]<=nums[i+1]){
                    nums[i]=nums[i+1];
                }else{
                    nums[i+1] = nums[i];
                }
            }
        }
        return ableToModify<=1;
    }

    public static boolean isPossible(int[] target) {
        if(target.length==1) return target[0]==1;
        int totalSum = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b-a);
        for (int j : target) {
            pq.add(j);
            totalSum += j;
        }
        while (pq.peek()!=1){
            int max = pq.remove();
            if(totalSum-max==1) return true;
            int x = max%(totalSum-max);
            totalSum = totalSum - max+x;
            if(x==0|| x==max)   return false;
            else pq.add(x);
        }
        return true;
    }

    public static int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (c1,c2)-> c1[1]==c2[1]? c1[0]-c2[0]: c1[1]-c2[1]);
        System.out.println(Arrays.deepToString(courses));
        PriorityQueue<Integer> max_heap = new PriorityQueue<>((a,b)-> b-a);
        int usedDay = 0;
        for (int[] course : courses) {
            if (course[0] <= course[1]) {
                if (usedDay + course[0] <= course[1]) {
                    usedDay += course[0];
                    max_heap.offer(course[0]);
                } else {
                    if (max_heap.peek() > course[0]) {
                        usedDay -= max_heap.poll();
                        usedDay += course[0];
                        max_heap.offer(course[0]);
                    }
                }
            }
        }
        return max_heap.size();
    }

    public static int snakesAndLadders(int[][] board) {
        reverseMatrix(board);
        int length = board.length;
        Queue<Pair<Integer,Integer>> q = new LinkedList<>();
        HashSet<Integer> visit = new HashSet<>();
        q.add(new Pair<>(1,0));
        while (!q.isEmpty()){
            Pair<Integer,Integer> p = q.peek();
            q.remove();
            int square = p.t;
            int move = p.t1;
            for (int i = 1; i < 7; i++) {
                int nextSquare = square+i;
                Pair<Integer,Integer> rc = intToSquare(nextSquare, length);
                int r = rc.t;
                int c = rc.t1;
                if(board[r][c]!=-1){
                    nextSquare = board[r][c];
                }
                if(nextSquare ==length*length){
                    return move+1;
                }
                if(!visit.contains(nextSquare)){
                    visit.add(nextSquare);
                    q.add(new Pair<>(nextSquare,move+1));
                }
            }
        }
        return -1;
    }
    private static Pair<Integer, Integer> intToSquare(int nextSquare, int length) {
        int r = (nextSquare-1)/length;
        int c = (nextSquare-1)%length;
        if(r%2!=0)  c = length - 1 - c;
        return new Pair<>(r,c);
    }
    private static void reverseMatrix(int[][] board){
        int i=0,j=board.length-1;
        while (i<j){
            int[] tempI = board[i];
            int[] tempJ = board[j];
            board[j]=tempI;
            board[i]=tempJ;
            i++;
            j--;
        }
    }

    public int findKthLargest(int[] nums, int k) {
        /*Arrays.sort(nums);
        return nums[nums.length-k];*/
        //Using Quick Selection
        int n = nums.length;
        k = nums.length-k;
        int f = 0, b = n-1;
        int pivot = partition(nums,f,b);
        while (pivot!=k){
            if(pivot>k){
                b=pivot-1;
            }else{
                f = pivot-1;
            }
            pivot = partition(nums,f,b);
        }
        return nums[pivot];
    }
    private int partition(int[] nums, int s, int e){
        int f = s, b = e-1;
        int mid = s + (e-s)/2;
        //shift mid at end so middle(pivot) come at end
        swap(mid,e,nums);
        int p = nums[e];
        while (f<b){
            while (nums[f]<=p && f<e)   f++;
            while (nums[b]>p && b>0)    b--;
            if(f>=b){
                break;
            }
            swap(f,b,nums);
        }
        if(f==b && nums[f]<=nums[e])    return f;
        swap(f,e,nums);
        return f;
    }

    public static int furthestBuilding(int[] heights, int bricks, int ladders){
        int i=0;
        PriorityQueue<Integer> max_heap = new PriorityQueue<>(Collections.reverseOrder());
        for (i = 0; i < heights.length - 1; i++) {
            int diff = heights[i+1]-heights[i];
            if(diff<=0){
                continue;
            }else{
                if(diff<=bricks){
                    bricks -= diff;
                    max_heap.add(diff);
                }else if(ladders>0){
                    if(max_heap.size()>0){
                        int maxBrickUsedBefore = max_heap.peek();
                        if(maxBrickUsedBefore>diff){
                            bricks +=maxBrickUsedBefore;
                            max_heap.remove();
                            max_heap.add(diff);
                            bricks -=diff;
                        }
                    }
                    ladders--;
                }else{
                    return i;
                }
            }
        }
        return i;
    }

    //String
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        List<List<String>> res = new ArrayList<>();
        String s = "";
        for (int i = 0; i < searchWord.length(); i++) {
            s +=searchWord.charAt(i);
            List<String> tempList = new ArrayList<>();
            for (int j = 0; j < products.length; j++) {
                if(products[j].startsWith(s)){
                    tempList.add(products[j]);
                }
                if(tempList.size()==3)  break;
            }
            res.add(tempList);
        }
        return res;
    }

    public int longestStrChain(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        int[] dp = new int[words.length];
        Arrays.fill(dp,1);
        for (int i = words.length-1; i >=0; i--) {
            for (int j = i+1; j <words.length; j++) {
                if(words[i].length()+1 == words[j].length() && (checkForPredecessor(words[i],words[j]))){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
        }
        int max = 0;
        for(int i:dp)   max = Math.max(max,i);
        return max;
    }

    private boolean checkForPredecessor(String s1, String s2) {
        int a=0,b=0;
        while (a<s1.length() && s1.charAt(a)==s2.charAt(b)){
            a++;
            b++;
        }
        if(a==s1.length() || s2.substring(b+1).equals(s1.substring(a))){
            return true;
        }
        return false;
    }

    //Stack
    public static int largestRectangleArea(int[] heights) {
        ArrayList<Integer> A = new ArrayList<Integer>(){{ for (int i : heights) add(i); }};
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
                while (!st.isEmpty() && A.get(st.peek()) >= A.get(i)) {
                    st.pop();
                }
                if(!st.isEmpty())   ans.set(i,st.peek());
            }
            st.push(i);
        }
        return ans;
    }


    public static int BasicCalculateII(String s) {
        s = inFixToPostFix(s);
        return evalRPN(s);
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
                StringBuilder a = new StringBuilder();
                i++;
                while (A.charAt(i)!=')') {
                    a.append(A.charAt(i));
                    i++;
                }
                st.push(Integer.parseInt(a.toString()));
            }
        }
        return st.pop();
    }

    public static String inFixToPostFix(String A) {
        HashMap<Character, Integer> precedence = new HashMap<>();
//        precedence.put('^',3);
        precedence.put('/',2);
        precedence.put('*',2);
        precedence.put('+',1);
        precedence.put('-',1);

        Stack<Character> st = new Stack<>();
        StringBuilder sb=new StringBuilder();
        sb.append('(');
        for (int i = 0; i < A.length(); i++) {
            if(A.charAt(i)==' ')    continue;
            if(A.charAt(i)>='0' && A.charAt(i)<='9'){
                sb.append(A.charAt(i));
            }else{
                sb.append(')');
                while (!st.isEmpty() && precedence.get(st.peek()) >= precedence.get(A.charAt(i))){
                    sb.append(st.pop());
                }
                st.push(A.charAt(i));
                sb.append('(');
            }
        }
        sb.append(')');
        while (!st.isEmpty())   sb.append(st.pop());
        return sb.toString();
    }


    //HashTable
    public static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> hm = new HashMap<>();
        for (int n :nums){
            hm.put(n,hm.getOrDefault(n,0)+1);
        }
        PriorityQueue<Map.Entry<Integer,Integer>> entries = new PriorityQueue<>(new Comparator<Map.Entry<Integer,Integer>>() {

            @Override
            public int compare(Map.Entry<Integer, Integer> e1, Map.Entry<Integer, Integer> e2) {
                if(e1.getValue()-e2.getValue()==0)  return e1.getKey()-e2.getKey();
                return e1.getValue()-e2.getValue();
            }
        });

        for(Map.Entry<Integer,Integer> e : hm.entrySet()){
            entries.add(e);
            if(entries.size()>k)    entries.remove();
        }

//        List<Map.Entry<Integer,Integer>> entries = new ArrayList<>(hm.entrySet());
//        Collections.sort(entries, (e1,e2)-> e2.getValue().compareTo(e1.getValue()));
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i]=entries.remove().getKey();
        }
        return ans;
    }

    public static int nthSuperUglyNumber(int n, int[] primes) {
        int [] pointer = new int[primes.length];
        Arrays.fill(pointer,1);
        int [] dp = new int[n+1];
        dp[1]=1;

        for (int i = 2; i < n + 1; i++) {
            Integer[] product = new Integer[pointer.length];
            for (int j = 0; j < product.length; j++) {
                product[j] = primes[j] * dp[pointer[j]];
            }
            int min = Collections.min(Arrays.asList(product));
            dp[i]=min;
            for (int j = 0; j < product.length; j++) {
                if(product[j]==min) pointer[j]++;
            }
        }
        return dp[n];
    }

    public static boolean isHappy(int n) {
        if(n<7 && n>1)  return false;
        if(n==1)    return true;
        int sum=0;
        while (n>0){
            int digit = n%10;
            sum += digit*digit;
            n /=10;
        }
        return isHappy(sum);
    }

    public static void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean firstColVal = false;
        for (int i = 0; i < m; i++) {
            if(matrix[i][0]==0) firstColVal=true;
            for (int j = 1; j < n; j++) {
                if(matrix[i][j]==0){
                    matrix[i][0]=matrix[0][j]=0;
                }
            }
        }
        for (int i = m-1; i >=0; i--) {
            for (int j = n-1; j>=1; j--) {
                if(matrix[i][0] ==0 || matrix[0][j]==0) matrix[i][j]=0;
            }
            if(firstColVal) matrix[i][0]=0;
        }
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, ArrayList<String>> hm = new HashMap<>();
        for (String s : strs){
            char[] cs = s.toCharArray();
            Arrays.sort(cs);
            if(hm.containsKey(Arrays.toString(cs))){
                hm.get(Arrays.toString(cs)).add(s);
            }else{
                hm.put(Arrays.toString(cs), new ArrayList<>(Collections.singletonList(s)));
            }
        }
        List<List<String>> ans = new ArrayList<>();
        for(Map.Entry<String,ArrayList<String>> e : hm.entrySet()){
            ans.add(e.getValue());
        }
        return ans;
    }

    public static List<String> letterCombinations(String digits) {
        HashMap<Character,ArrayList<Character>> hm = new HashMap<>();
        hm.put('2', new ArrayList<>(Arrays.asList('a','b','c')));
        hm.put('3', new ArrayList<>(Arrays.asList('d','e','f')));
        hm.put('4', new ArrayList<>(Arrays.asList('g','h','i')));
        hm.put('5', new ArrayList<>(Arrays.asList('j','k','l')));
        hm.put('6', new ArrayList<>(Arrays.asList('m','n','o')));
        hm.put('7', new ArrayList<>(Arrays.asList('p','q','r','s')));
        hm.put('8', new ArrayList<>(Arrays.asList('t','u','v')));
        hm.put('9', new ArrayList<>(Arrays.asList('w','x','y','z')));
        List<String> ans = new ArrayList<>();
        if(digits.length()==0)  return new ArrayList<String>();
        List<Character> ch = hm.get(digits.charAt(0));
        for (char c: ch){
            ans.add(c+"");
        }
        for (int i = 1; i < digits.length(); i++) {
            List<Character> chars = hm.get(digits.charAt(i));
            List<String> newAns = new ArrayList<>();
            for(char c : chars){
                for (String an : ans) {
                    newAns.add(an + c);
                }
            }
            ans = newAns;
        }

        return ans;
    }

    public static String sortString(String s) {
        TreeMap<Character, Integer> hm = new TreeMap<>();
        for (int i = 0; i < s.length(); i++) {
            hm.put(s.charAt(i), hm.getOrDefault(s.charAt(i),0)+1);
        }
        StringBuffer sb = new StringBuffer();
        while (hm.size()!=0){
            for (char i = 'a'; i <='z'; i++) {
                if(hm.containsKey(i)){
                    sb.append(i);
                    if((hm.get(i)-1)==0)
                        hm.remove(i);
                    else
                        hm.put(i,hm.get(i)-1);

                }
            }
            for (char i = 'z'; i >='a'; i--) {
                if(hm.containsKey(i)){
                    sb.append(i);
                    if((hm.get(i)-1)==0)
                        hm.remove(i);
                    else
                        hm.put(i,hm.get(i)-1);

                }
            }
        }
        return sb.toString();
    }

    public static int uniqueMorseRepresentations(String[] words) {
        String[] mossCode = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        HashMap<Integer, String> hm = new HashMap<>();
        for (int i=0; i<mossCode.length; i++){
            hm.put(i,mossCode[i]);
        }
        HashSet<String> set = new HashSet<>();
        for (String w : words){
            StringBuffer s =new StringBuffer();
            for (int i = 0; i < w.length(); i++) {
                s.append(hm.get(w.charAt(i)-'a'));
            }
            set.add(s.toString());
        }
        return set.size();
    }

    public static int countKDifference(int[] nums, int k) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int i:nums) hm.put(i,hm.getOrDefault(i,0)+1);
        int ans =0;
        for(Integer e : hm.keySet()){
            int t1 = e-k;
            int t2 = k+e;
            if(hm.containsKey(t1))  ans += hm.get(t1)*hm.get(e);
            if(hm.containsKey(t2))  ans += hm.get(t2)*hm.get(e);
        }
        return ans/2;
    }

    public static int numJewelsInStones(String jewels, String stones) {
        HashSet<Character> jew = new HashSet<>();
        for (int i = 0; i < jewels.length(); i++) {
            jew.add(jewels.charAt(i));
        }
        HashMap<Character,Integer> hm = new HashMap<>();
        for (int i = 0; i < stones.length(); i++) {
            hm.put(stones.charAt(i), hm.getOrDefault(stones.charAt(i),0)+1);
        }
        int ans=0;
        for (Character c: jew){
            if(hm.containsKey(c)){
                ans += hm.get(c);
            }
        }
        return ans;
    }

    public static int[] frequencySort(int[] nums) {
        HashMap<Integer,Integer> hm = new HashMap<>();
        for (int a : nums)  hm.put(a, hm.getOrDefault(a,0)+1);
        int[][] num = new int[hm.size()][2];
        int j=0;
        for (int i: hm.keySet()){
            num[j][0]=i;
            num[j][1]=hm.get(i);
            j++;
        }
        Arrays.sort(num, (a,b)-> (a[1]==b[1]? b[0]-a[0] : a[1]-b[1]));
        int[] ans = new int[nums.length];
        j=0;
        for(int[] i:num){
            int l =i[1];
            while (l>0){
                ans[j]=i[0];
                j++;
                l--;
            }
        }
        return ans;
    }

    public static List<Integer> partitionLabels(String s) {
        HashMap<Character,Integer> hm = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            hm.put(s.charAt(i),i);
        }
        List<Integer> ans = new ArrayList<>();
        int max=0,p=-1;
        for (int i = 0; i < s.length(); i++) {
            max = Math.max(max,hm.get(s.charAt(i)));
            if(max==i){
                ans.add(max-p);
                p=max;
            }
        }
        return ans;
    }

    //BitWise
    public static int subarrayBitwiseORs(int[] arr) {
        HashSet<Long> s = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            long or = 0;
            for (int j = i; j < arr.length; j++) {
                or |= arr[j];
                s.add(or);
            }
        }
        return s.size();
    }

    public static int numSteps(String s) {
        int carry=0,ans=0;
        for (int i = s.length()-1; i >=0; i--) {
            int rmb = s.charAt(i)-'0';
            if((rmb+carry)==1) {
                carry=1;
                ans += 2;
            }
            else ans++;
        }
        return ans+carry;
    }

    public static int totalHammingDistance(int[] nums) {
        int c=0;
        for (int i = 0; i <=31; i++) {
            int c0=0,c1=0;
            for (int j = 0; j < nums.length; j++) {
                if(checkBit(nums[j],i)) c1++;
                else c0++;
            }
            c += (c0*c1);
        }
        return c;
    }

    public static int minFlips(int a, int b, int c) {
        int ans=0;
        for (int i = 0; i <=31; i++) {
            if(checkBit(c,i)){
                if(!checkBit(a,i) && !checkBit(b,i)) {
                    ans++;
                }
            }else{
                if(checkBit(a,i))   ans++;
                if(checkBit(b,i))   ans++;
            }
        }
        return ans;
    }

    public static int bulbSwitch(int n) {
        int c=0,i=1;
        while (i*i<=n){
            c++;
            i++;
        }
        return c;
    }

    public static List<Integer> circularPermutation(int n, int start) {
        ArrayList<Integer> grayCode = generateGrayCode(n);
        int l = grayCode.size();
        reverseArrayList(grayCode,0,l-1);
        int index = 0;
        for (int i=0; i<grayCode.size();i++){
            if(grayCode.get(i)==start){
                index = i;
                break;
            }
        }
        reverseArrayList(grayCode,0,index);
        reverseArrayList(grayCode,index+1,l-1);
        return grayCode;
    }

    private static ArrayList<Integer> generateGrayCode(int n) {
        if(n==1) return new ArrayList<>(Arrays.asList(0,1));
        ArrayList<Integer> ans = generateGrayCode(n-1);
        int length=ans.size();
        int lastElm = 1<<(n-1);
        for (int i = length-1; i >=0; i--) {
            ans.add(ans.get(i)+lastElm);
        }
        return ans;
    }

    public static int numSplits(String s) {
        int p1=1,ans=0;
        HashMap<Character, Integer> left = new HashMap<>();
        HashMap<Character, Integer> right = new HashMap<>();
        for (int i = 1; i < s.length(); i++) {
            if(right.containsKey(s.charAt(i))){
                right.put(s.charAt(i),right.get(s.charAt(i))+1);
            }else   right.put(s.charAt(i),1);
        }
        left.put(s.charAt(0),1);
        while (p1<s.length()){
            if(left.keySet().size()==right.keySet().size()) ans++;

            if(left.containsKey(s.charAt(p1))){
                left.put(s.charAt(p1),left.get(s.charAt(p1))+1);
            }else   left.put(s.charAt(p1),1);

            if(right.containsKey(s.charAt(p1))){
                right.put(s.charAt(p1),right.get(s.charAt(p1))-1);
                if(right.get(s.charAt(p1))==0)
                    right.remove(s.charAt(p1));
            }
            p1++;
        }
        return ans;
    }


    //Array
    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        if(firstList==null || secondList==null||
                firstList.length==0 || secondList.length==0 )    return new int[0][0];

        int i=0,j=0;
        List<int[]> ans = new ArrayList<>();
        while (i<firstList.length && j<secondList.length){
            int leftA = firstList[i][0];
            int rightA = firstList[i][1];
            int leftB = secondList[j][0];
            int rightB = secondList[j][1];

            if(leftA>rightB){
                j++;
                continue;
            }
            if(leftB>rightA){
                i++;
                continue;
            }
            int left=-1,right=-1;
            if(leftA>=leftB){
                left = leftA;
            }else{
                left=leftB;
            }

            if(rightA<=rightB){
                right=rightA;
                i++;
            }else {
                right=rightB;
                j++;
            }
            ans.add(new int[]{left,right});
        }
        return ans.toArray(new int[ans.size()][2]);
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        ArrayList<Integer> numList = new ArrayList();
        for (int n :nums)   numList.add(n);
        permute(numList,new ArrayList<>(), ans);
        return ans;
    }
    private static void permute(ArrayList<Integer> nums, ArrayList<Integer> fixPos, List<List<Integer>> ans){
        if(nums.isEmpty()){
            ans.add(new ArrayList<>(fixPos));
            return;
        }
        int n = nums.size();
        for (int i = 0; i < n; i++) {
            int num = nums.get(i);
            fixPos.add(num);
            nums.remove(i);
            permute(nums,fixPos,ans);
            nums.add(i,num);
            fixPos.remove(fixPos.size()-1);
        }
    }

    public static List<Integer> findDuplicates(int[] nums) {
        int i=0;
        while (i<nums.length){
            int pos = nums[i]-1;
            if(nums[i]!=nums[pos]){
                swap(i,pos,nums);
            }else i++;
        }
        List<Integer> ans = new ArrayList<>();
        for (int j = 0; j < nums.length; j++) {
            if(nums[j]!=j+1)    ans.add(nums[j]);
        }
        return ans;
    }

    public static List<List<String>> displayTable(List<List<String>> orders) {
         HashMap<String, HashMap<String ,Integer>> tableWiseDishOrder = new HashMap<>();
         HashSet<String> dishes = new HashSet<>();
         HashSet<Integer> tables = new HashSet<>();
         for (List<String> order: orders){
             dishes.add(order.get(2));
             tables.add(Integer.parseInt(order.get(1)));
             if(!tableWiseDishOrder.containsKey(order.get(1))){
                 HashMap<String,Integer> dish = new HashMap<>();
                 dish.put(order.get(2),1);
                 tableWiseDishOrder.put(order.get(1), dish);
             }else{
                 HashMap<String,Integer> allDish = tableWiseDishOrder.get(order.get(1));
                 if(allDish.containsKey(order.get(2)))  allDish.put(order.get(2),allDish.get(order.get(2))+1);
                 else   allDish.put(order.get(2),1);
             }
         }
         //Crete Table
        List<List<String>> table = new ArrayList<>();
        List<String> header = new ArrayList<>();
        header.add("table");
        List<String> allDishes = new ArrayList<>(dishes);
        Collections.sort(allDishes);
        header.addAll(allDishes);
        table.add(header);
        List<Integer> allTables = new ArrayList<>(tables);
        Collections.sort(allTables);

        for (int i = 0; i < allTables.size(); i++) {
            List<String> tableOrder = new ArrayList<>();
            tableOrder.add(allTables.get(i).toString());
            for (int j = 0; j < allDishes.size(); j++) {
                String orderC="0";
                HashMap<String,Integer> t = tableWiseDishOrder.get(allTables.get(i).toString());
                if(t.containsKey(allDishes.get(j))){
                    orderC = t.get(allDishes.get(j)).toString();
                }
                tableOrder.add( orderC );
            }
            table.add(tableOrder);
        }
        return table;
    }

    public static int numOfPairs(String[] nums, String target) {
        int c=0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if(i!=j){
                    String str = nums[i]+nums[j];
                    if(str.equals(target))  c++;
                }
            }
        }
        return c;
    }

    public static List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        int totalSubset = 1<<nums.length;
        Set<List<Integer>> ans = new HashSet<>();
        for (int i = 0; i < totalSubset; i++) {
            List<Integer> t = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if(checkBit(i,j)){
                    t.add(nums[j]);
                }
            }
            ans.add(t);
        }
//        List<List<Integer>> result = new ArrayList<>(ans);
        return new ArrayList<>(ans);
    }

    public static int countMaxOrSubsets(int[] nums) {
        int max = 0,ans=0;
        for (int n: nums)   max |= n;

        int totalSubArr = 1<<nums.length;
        for (int i = 0; i < totalSubArr; i++) {
            int or=0;
            for (int j = 0; j < nums.length; j++) {
                if(checkBit(i,j)){
                    or |= nums[j];
                }
            }
            if(or==max) ans++;
        }
        return ans;
    }

    public static int countTriplets(int[] arr) {
        int[] pf = new int[arr.length];
        int xor=0,ans=0;
        for (int i=0;i<arr.length; i++){
            xor ^= arr[i];
            pf[i]=xor;
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if(i==0 && pf[j]==0) ans += j;
                if(i!=0 && (pf[j]^pf[i-1])==0){
                    ans += (j-i);
                }
            }
        }
        return ans;
    }

    public static int[] sortJumbled(int[] mapping, int[] nums) {
        int[][] newNums = new int[nums.length][2];
        int j=0;
        for (int e : nums){
            int newE=0;
            int i=1;
            if(e==0){
                newNums[j][0]=nums[j];
                newNums[j][1]=mapping[e];
                j++;
                continue;
            }
            while (e>0){
                int r = e%10;
                newE += (mapping[r]*i);
                e /=10;
                i *=10;
            }
            newNums[j][0]=nums[j];
            newNums[j][1]=newE;
            j++;
        }
        Arrays.sort(newNums, Comparator.comparingInt(p -> p[1]));
        for (int i = 0; i < nums.length; i++) {
            nums[i] = newNums[i][0];
        }
        return nums;
    }

    public static List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> ans = new ArrayList<>();
        int[] indexOfChar = new int[pattern.length()];
        for (int i = 0; i < pattern.length(); i++) {
            indexOfChar[i]=pattern.indexOf(pattern.charAt(i));
        }
        for (String s : words) {
            boolean f= true;
            for (int i = 0; i < pattern.length(); i++) {
                if(s.indexOf(s.charAt(i)) != indexOfChar[i]){
                        f=false;
                        break;
                }
            }
            if(f)  ans.add(s);
        }
        return ans;
    }

    //similar joseph problem
    public static int findTheWinner(int n, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <=n; i++) {
            list.add(i);
        }

        int x=0,i=0;
        while (list.size()>1){
            x++;
            i=i%list.size();
            if(x==k){
                list.remove(i);
                x=0;
            }else{
                i++;
            }
        }
        return list.get(0);
    }

    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> st = new Stack<>();
        int j = 0;
        for(int val : pushed){
            st.push(val);
            while(!st.isEmpty() && st.peek() == popped[j]){
                st.pop();
                j++;
            }
        }
        return st.isEmpty();
    }

    public static int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int n = piles.length;
        int ans =0;
        for (int i = n-2; i >=n/3; i-=2) {
            ans += piles[i];
        }
        return ans;
    }

    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int n = rowSum.length;
        int m = colSum.length;
        int[][] mat = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int val = Math.min(rowSum[i],colSum[j]);
                mat[i][j]=val;
                rowSum[i] -=val;
                colSum[j] -=val;
            }
        }
        return mat;
    }

    public static int numberOfArithmeticSlices(int[] nums) {
        int diff =0;
        int ans =0;

        for (int i = 0; i < nums.length-1; i++) {
            int temp = 1;
            diff = nums[i+1]-nums[i];
            while (i+1<nums.length && diff ==(nums[i+1]-nums[i])){
                temp++;
                i++;
            }
            i--;

            if(temp>=3){
                temp -=2;
                ans += (temp*(temp+1))/2;
            }
        }
        return ans;
    }

    public static List<Boolean> checkArithmeticSubArrays(int[] nums, int[] l, int[] r) {
        List<Boolean> ans = new ArrayList<>();
        for (int i = 0; i < l.length; i++) {
            ArrayList<Integer> t = new ArrayList<>();
            for (int j = l[i]; j <=r[i]; j++) {
                t.add(nums[j]);
            }
            Collections.sort(t);
            int d = t.get(1)-t.get(0);
            boolean f = true;
            for (int j = 1; j < t.size()-1; j++) {
                if((t.get(j+1)-t.get(j)) != d){
                    f=false;
                    break;
                }
            }
            ans.add(f);
        }
        return ans;
    }

    public static int triangularSumInner(int[] nums, int n) {
        if(n==1) return nums[0];
        for (int i = 0; i < n-1; i++) {
            nums[i] = (nums[i]+nums[i+1])%10;
        }

        return triangularSumInner(nums,n-1);
    }

    public static int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        int[] temp = new int[n];
        int p1=0,p2=n-1,i=0;
        int countPivot=0;
        while (i<n){
            if(nums[i]==pivot) {
                countPivot++;
                i++;
            }
            else if(nums[i]<pivot){
                temp[p1]=nums[i];
                p1++;
                i++;
            }else{
                temp[p2]=nums[i];
                p2--;
                i++;
            }
        }

        for (int j = 0; j < countPivot; j++) {
            temp[p1]=pivot;
            p1++;
        }
        int l = n-1;
        while (p1<=l){
            swap(p1,l,temp);
            p1++;l--;
        }
        return temp;
    }

    public static int[] processQueries(int[] queries, int m) {
        int[] arr = new int[m];
        for (int i = 0; i <m; i++) {
            arr[i] = i+1;
        }
        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int v =0;
            for (int j = 0; j < arr.length; j++) {
                if(queries[i]==arr[j]){
                    ans[i]=j;
                    v=j;
                    break;
                }
            }
            reverseArray(arr,0,v);
            reverseArray(arr, 1,v);
        }
        return ans;
    }

    private static void reverseArray(int[] arr, int s, int e) {
        while (s<=e){
            swap(s,e,arr);
            s++;
            e--;
        }
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.size()];
        int min = Integer.MAX_VALUE;
        for (List<Integer> tri : triangle) {
            int[] prev = dp.clone();
            for (int i = 0; i < tri.size(); i++) {
                if(i==0){
                    dp[i] = prev[i]+tri.get(i);
                }else if(i == tri.size()-1){
                    dp[i] = prev[i-1]+tri.get(i);
                }else{
                    dp[i] = Math.min(prev[i],prev[i-1])+tri.get(i);
                }
            }
        }
        for (int sum : dp)  min = Math.min(min,sum);
        return min;
    }

    public static int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for (int i = 0; i <= word1.length(); i++) {
            for (int j = 0; j <=word2.length(); j++) {
                if(i==0||j==0) continue;
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j] = 1+dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return word1.length()+word2.length() - 2 * dp[word1.length()][word2.length()];
    }
}
