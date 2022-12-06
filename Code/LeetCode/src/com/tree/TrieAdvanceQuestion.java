package com.tree;

import java.util.*;

import static com.BitManuplication.BitManuplicationAll.checkBit;

class TrieNode {
    boolean isEnd;
    int freq;
    HashMap<Character, TrieNode> charMap;
    public TrieNode() {
        this.isEnd = false;
        this.charMap = new HashMap<>();
        this.freq=0;
    }
}
class Pair<T, T1> {
    T count;
    T1 indexSum;
    public Pair(T val, T1 index){
        this.count =val;
        this.indexSum =index;
    }
}
class Node{
    Node[] c = new Node[2];
    int value;
    public Node(){
        this.value=0;
        this.c[0]=null;
        this.c[1]=null;
    }
}
public class TrieAdvanceQuestion{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        TrieAdvanceQuestion t = new TrieAdvanceQuestion();
        int n = sc.nextInt();
//        String temp = sc.nextLine();
        ArrayList<Integer> A = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            A.add(sc.nextInt());
        }
        int ans = xorTriplet(A);
        System.out.println(ans);
    }
    public void insertWord(String word, TrieNode r){
        for (int i = 0; i < word.length(); i++) {
            if(!r.charMap.containsKey(word.charAt(i))){
                TrieNode node = new TrieNode();
                r.charMap.put(word.charAt(i), node);
            }
            r.freq++;
            r = r.charMap.get(word.charAt(i));
        }
        r.isEnd=true;
    }
    public boolean isPresent(String word, TrieNode r){
        for (int i = 0; i < word.length(); i++) {
            if(r.charMap.containsKey(word.charAt(i))){
                r= r.charMap.get(word.charAt(i));
            }else return false;
        }
        return r.isEnd;
    }

    //TODO: Learning pending
    public static int subArrayXorLessThanB(ArrayList<Integer> A, int B) {
        int M = 1000000007;
        Node root = new Node();
        int preXor=0;
        long count=0L;
        insertBinaryTrie(root,preXor,20);
        for (int i = 0; i < A.size(); i++) {
            preXor ^= A.get(i);
            count += (long) queryForSubArrayXorLessThanB(root, preXor, B, 20);
            count %= M;
            insertBinaryTrie(root,preXor,20);
        }
        count%=M;
        return (int)count;
    }
    private static int queryForSubArrayXorLessThanB(Node root, int preXor, int B, int bit){
        int ans =0;
        Node temp = root;
        for (int i = bit; temp!=null && i>=0; i--) {
            int q = checkBit(B,i)?1:0;
            int index = checkBit(preXor,i)?1:0;

            if(q==1){
                if(temp.c[index]!=null){
                    ans += temp.c[index].value;
                }
                temp=temp.c[1-index];
            }else{
                temp= temp.c[index];
            }
        }
        return ans;
    }

    public static int xorTriplet(ArrayList<Integer> A){
        long ans=0L;
        int M = 1000000007;
        ArrayList<Integer> pf = new ArrayList<>();
        HashMap<Integer,Pair<Integer,Integer>> hm = new HashMap<>();
        pf.add(0);
        hm.put(0,new Pair<>(1,0));
        for (int i = 0; i < A.size(); i++) {
            int t = pf.get(i) ^ A.get(i);
            pf.add(t);
            if(!hm.containsKey(t)){
                hm.put(t,new Pair<>(1,i+1));
            }else{
                Pair<Integer,Integer> p = hm.get(t);
                ans = (ans + p.count*i - p.indexSum)%M;
                p.count++;
                p.indexSum +=i+1;
                hm.put(t,p);
            }
        }
        ans = (ans+M)%M;
        return (int)ans;
    }

    public static BinaryTreeImpl.TreeNode flatten(BinaryTreeImpl.TreeNode A){
        BinaryTreeImpl.TreeNode node = A;
        while (node!=null){
            if(node.left!=null) {
                BinaryTreeImpl.TreeNode rtMost = node.left;
                while (rtMost.right != null) rtMost = rtMost.right;
                rtMost.right = node.right;
                node.right = node.left;
                node.left = null;
            }
            node=node.right;
        }
        return A;
    }

    public static ArrayList<Integer> maximumXorSubArray(ArrayList<Integer> A) {
        ArrayList<Integer> pf = new ArrayList<>();
        pf.add(0);
        for (int i = 0; i < A.size(); i++) {
            pf.add(pf.get(i)^A.get(i));
        }
        int maxXor = maxXor(pf);
        int l=0,r=0;
        HashMap<Integer,Integer> hm = new HashMap<>();
        for (int i = 0; i < pf.size(); i++) {
            int val = maxXor^pf.get(i);
            if(hm.containsKey(val)){
                if((l==0&&r==0)||Math.abs(l-r)> Math.abs(hm.get(val)+1 - i)) {
                    l = hm.get(val) + 1;
                    r = i;
                }
            }else{
                hm.put(pf.get(i),i);
            }
        }
        return new ArrayList<>(Arrays.asList(l,r));
    }

    public static int maxXor(ArrayList<Integer> A){
        int max = Integer.MIN_VALUE;
        for(int i : A)  max = Math.max(max,i);
        int bit = noOfBits(max);
        Node root = new Node();
        for (int a : A){
            insertBinaryTrie(root, a, bit);
        }
        int ans=0;
        for(int a : A){
            ans = Math.max(ans, query(root,a,bit));
        }
        return ans;
    }

    private static int query(Node root, int a, int bit) {
        int ans=0;
        for (int i = bit; i>=0; i--) {
            int e = checkBit(a,i)?1:0;
            if(root.c[1-e]!=null){
                ans += 1<<i;
                root = root.c[1-e];
            }else{
                root = root.c[e];
            }
        }
        return ans;
    }
    private static void insertBinaryTrie(Node root, int a, int bit) {
        for (int i = bit; i >=0; i--) {
            int e = checkBit(a,i)?1:0;
            if(root.c[e]==null){
                root.c[e] = new Node();
            }
            root = root.c[e];
            root.value++;
        }
    }
    private static int noOfBits(int n){
        int c=0;
        while (n>0){
            c++;
            n = n>>1;
        }
        return c;
    }

    public ArrayList<Integer> spellingChecker(ArrayList<String> A, ArrayList<String> B) {
        TrieNode root = new TrieNode();
        for (String word : A){
            insertWord(word,root);
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (String word : B){
            if(isPresent(word,root)){
                ans.add(1);
            }else ans.add(0);
        }
        return ans;
    }

    public ArrayList<String> shortestUniquePrefix(ArrayList<String> A) {
        TrieNode root = new TrieNode();
        for(String word : A){
            insertWord(word,root);
        }
        ArrayList<String> ans = new ArrayList<>();
        for (String word : A) {
            TrieNode temp = root;
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < word.length(); i++) {
                sb.append(word.charAt(i));
                temp = temp.charMap.get(word.charAt(i));
                if(temp.freq==1){
                    break;
                }
            }
            ans.add(sb.toString());
        }
        return ans;
    }

    public ArrayList<Integer> ContactFinder(ArrayList<Integer> A, ArrayList<String> B) {
        ArrayList<Integer> ans = new ArrayList<>();
        TrieNode root = new TrieNode();
        for (int i = 0; i < A.size(); i++) {
            if(A.get(i)==0){
                insertWord(B.get(i),root);
            }else{
                TrieNode temp = root;
                int j;
                for (j = 0; j < B.get(i).length(); j++) {
                    if(!temp.charMap.containsKey(B.get(i).charAt(j))){
                        break;
                    }
                    temp = temp.charMap.get(B.get(i).charAt(j));
                }
                int occur=(j==B.get(i).length())?temp.freq:0;
                ans.add(occur);
            }
        }
        return ans;
    }
}