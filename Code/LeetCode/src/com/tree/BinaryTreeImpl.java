package com.tree;
import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;

import java.util.*;
import java.util.function.DoubleBinaryOperator;

public class BinaryTreeImpl {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right,next;
        public TreeNode(int x){
            this.val =x;
            this.left=null;
            this.right=null;
            this.next=null;
        }
    }
    public static class Pair<T, T1> {
        T node;
        T1 index;
        public Pair(T val, T1 index){
            this.node=val;
            this.index=index;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n =sc.nextInt();
        ArrayList<Integer> A = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            A.add(sc.nextInt());
        }
        TreeNode root = deserializeTree(A);
        System.out.println(printAllRootToLeafPath(root));
    }

    static TreeNode largestDeepAns = null;
    static int maxDeep;
    public static TreeNode largestDeepestNode(TreeNode A){
        findDepth(A,0);
        return largestDeepAns;
    }
    private static int findDepth(TreeNode A, int depth) {
        if(A==null) return depth;
        int Dl = findDepth(A.left,depth+1);
        int Dr = findDepth(A.right,depth+1);
        if(Dl==Dr && maxDeep<=Dl){
            maxDeep = Dl;
            largestDeepAns=A;
        }
        return Math.max(Dl,Dr);
    }

    public static ArrayList<ArrayList<Integer>> printAllRootToLeafPath(TreeNode A) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        inOrderWithLeafNodePath(A, ans, new Stack<>());
        return ans;
    }
    private static void inOrderWithLeafNodePath(TreeNode A, ArrayList<ArrayList<Integer>> ans, Stack<Integer> leafPath) {
        if(A.left==null && A.right==null){
            leafPath.push(A.val);
//            ArrayList<Integer> path = new ArrayList<>(leafPath);
            ans.add(new ArrayList<>(leafPath));
            leafPath.pop();
            return;
        }
        if(A.left!=null){
            leafPath.push(A.val);
            inOrderWithLeafNodePath(A.left,ans,leafPath);
            leafPath.pop();
        }
        if(A.right!=null){
            leafPath.push(A.val);
            inOrderWithLeafNodePath(A.right,ans,leafPath);
            leafPath.pop();
        }
    }

    static int flag=0;
    public static int possibleToPartitionInEqualSum(TreeNode A){
        flag=0;
        ArrayList<Integer> preOrder = new ArrayList<>();
        preorderTraversal(A,preOrder);
        long total=0L;
        for (Integer integer : preOrder) {
            total += integer;
        }
//        System.out.println(preOrder);
        if(total%2==0)  sumOfSubNode(A,total/2);
        return flag;
    }
    private static long sumOfSubNode(TreeNode A, long target){
        if(A==null) return 0;
        long l = sumOfSubNode(A.left, target);
        if(l==target)   flag=1;
        long r = sumOfSubNode(A.right,target);
        if(r==target)   flag=1;
        long sum = l+r+A.val;
        if(sum==target)   flag=1;
        return sum;
    }

    public TreeNode convertToCircularList(TreeNode root){
        if(root==null) return null;
        TreeNode c1 = convertToCircularList(root.left);
        TreeNode c2 = convertToCircularList(root.right);
        root.left = root;
        root.right = root;
        return combineToCircularList( combineToCircularList(c1,root), c2);
    }
    public TreeNode combineToCircularList(TreeNode c1 , TreeNode c2){
        if(c1==null) return c2;
        if(c2==null) return c1;
        TreeNode lastC1 = c1.left;
        TreeNode lastC2 = c2.left;
        lastC1.right = c2;
        c2.left = lastC1;
        c1.left = lastC2;
        lastC2.right = c1;
        return c1;
    }

    public void connectNextNotPerfectBinary(TreeNode root){
        TreeNode curr = root;
        while (curr!=null){
            TreeNode temp = curr;
            while (temp!=null) {
                if (temp.left != null) {
                    if(temp.right!=null)    temp.left.next =temp.right;
                    else    temp.left.next = getNextRight(temp);
                }
                if(temp.right!=null){
                    temp.right.next = getNextRight(temp);
                }
                temp = temp.next;
            }
            if(curr.left!=null) curr=curr.left;
            else if(curr.right != null) curr = curr.right;
            else curr = getNextRight(curr);
        }
    }
    private TreeNode getNextRight(TreeNode temp) {
        temp = temp.next;
        while (temp!=null){
            if(temp.left!=null) return temp.left;
            if(temp.right!=null) return temp.right;
            temp = temp.next;
        }
        return temp;
    }

    public void connectNextPerfectBinary(TreeNode root) {
        TreeNode curr = root;
        while (curr.left!=null){
            TreeNode temp = curr;
            while (temp!=null){
                temp.left.next = temp.right;
                if(temp.next!=null) temp.right.next = temp.next.left;
                temp = temp.next;
            }
            curr = curr.left;
        }
    }

    public ArrayList<Integer> recoverTree(TreeNode A) {
        int first =-1, mid =-1, last=-1, prev = -1;
        TreeNode curr = A;
        while (curr!=null){
            if(curr.left==null){
                if(curr.val<prev) {
                    if(first==-1){
                        first = prev;
                        mid = curr.val;
                    }else{
                        last = curr.val;
                    }
                }
                prev=curr.val;
                curr = curr.right;
            }else{
                TreeNode temp = curr.left;
                while (temp.right!=null && temp.right!=curr){
                    temp = temp.right;
                }
                if(temp.right==null){
                    temp.right=curr;
                    curr = curr.left;
                }
                if(temp.right==curr){
                    temp.right=null;
                    if(curr.val<prev) {
                        if(first==-1){
                            first = prev;
                            mid = curr.val;
                        }else{
                            last = curr.val;
                        }
                    }
                    prev=curr.val;
                    curr=curr.right;
                }
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
        res.add(first);
        if(last != -1){
            res.add(last);
        }else {
            res.add(mid);
        }
        Collections.sort(res);
        return res;
    }

    public ArrayList<Integer> inorderTraversalUsingMorris(TreeNode A) {
        ArrayList<Integer> ans = new ArrayList<>();
        TreeNode curr = A;
        while (curr!=null){
            if(curr.left==null){
                ans.add(curr.val);
                curr = curr.right;
            }else{
                TreeNode temp = curr.left;
                while (temp.right!=null && temp.right!=curr){
                    temp = temp.right;
                }
                if(temp.right==null) {
                    temp.right = curr;
                    curr = curr.left;
                }
                if(temp.right==curr){
                    temp.right=null;
                    ans.add(curr.val);
                    curr = curr.right;
                }
            }
        }
        return ans;
    }

    public int kthSmallest(TreeNode A, int B) {
        HashMap<TreeNode, Integer> hm = new HashMap<>();
        hm.put(null,0);
        fillSizeOfNode(A,hm);
        TreeNode curr = A;
        while (curr!=null){
            if(hm.get(curr.left)==B-1){
                return curr.val;
            }else if(hm.get(curr.left)>=B){
                curr = curr.left;
            }else{
                curr=curr.right;
                B = B - hm.get(curr.left)-1;
            }
        }
        return -1;
    }
    private int fillSizeOfNode(TreeNode A, HashMap<TreeNode,Integer> hm ){
        if(A==null) return 0;
        int ls = fillSizeOfNode(A.left,hm);
        int rs = fillSizeOfNode(A.right,hm);
        hm.put(A,1+ls+rs);
        return 1+ls+rs;
    }

    public TreeNode trimmingNode(TreeNode A, int L, int R){
        if(A==null) return null;
        if(A.val>=L && A.val<=R){
            A.left = trimmingNode(A.left,L,R);
            A.right = trimmingNode(A.right,L,R);
            return A;
        }else if(A.val<L){
            return trimmingNode(A.right,L,R);
        }else{
            return trimmingNode(A.left,L,R);
        }
    }

    public static TreeNode deserializeTree(ArrayList<Integer> A){
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(A.get(0));
        q.add(root);
        int i=1;
        while (!q.isEmpty()){
            TreeNode node = q.peek();
            q.remove();
            if(A.get(i)!=-1){
                node.left = new TreeNode(A.get(i));
                q.add(node.left);
            }
            i++;
            if(A.get(i)!=-1){
                node.right = new TreeNode(A.get(i));
                q.add(node.right);
            }
            i++;
        }
        return root;
    }
    public static ArrayList<Integer> serializeTree(TreeNode A){
        ArrayList<Integer> ans = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(A);
        while (!q.isEmpty()){
            TreeNode node = q.peek();
            q.remove();
            if(node==null) {
                ans.add(-1);
                continue;
            }
            ans.add(node.val);
            q.add(node.left);
            q.add(node.right);
        }
        return ans;
    }

    //sum binary tree = sum of left subtree val + sum of right subTree val;
    public Pair<Boolean,Integer> sumBinaryTreeHelper(TreeNode A) {
        //if(A==null) return new Pair<>(true,0);
        if(A.left==null && A.right==null) return new Pair<>(true,A.val);
        Pair<Boolean,Integer> l= (A.left!=null)?sumBinaryTreeHelper(A.left):new Pair<>(true,0);
        Pair<Boolean,Integer> r= (A.right!=null)?sumBinaryTreeHelper(A.right):new Pair<>(true,0);
        if((l.node && r.node) && (l.index+r.index==A.val))
            return new Pair<>(true, A.val+l.index+r.index);
        else return new Pair<>(false,l.index+r.index);
    }

    public int hasPathSum(TreeNode A, int B) {
        if(A.left==null && A.right==null) return A.val==B?1:0;
        if(A.left==null)    return hasPathSumHelper(A.right,B,A.val)?1:0;
        if(A.right==null)   return hasPathSumHelper(A.left,B,A.val)?1:0;
        return hasPathSumHelper(A,B,0)?1:0;
    }
    public boolean hasPathSumHelper(TreeNode A, int B, int sum) {
        if(A.left==null && A.right==null) {
            return (sum+A.val==B);
        }
        if(A.left!=null) {
            if(!hasPathSumHelper(A.left,B,sum+A.val))
                return A.right != null && hasPathSumHelper(A.right, B, sum + A.val);
            else return true;
        }else return hasPathSumHelper(A.right, B, sum + A.val);
    }

    public ArrayList<Integer> postorderTraversalUsingStack(TreeNode A) {
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.add(A);
        while (!s1.isEmpty()){
            TreeNode node = s1.peek();
            s1.pop();
            s2.add(node);
            if(node.left!=null)   s1.add(node.left);
            if(node.right!=null)   s1.add(node.right);
        }
        ArrayList<Integer> ans = new ArrayList<>();
        while (!s2.isEmpty()){
            ans.add(s2.pop().val);
        }
        return ans;
    }

    public int t2Sum(TreeNode A, int B) {
        HashSet<Integer> s = new HashSet<>();
        return t2SumHelper(A,B,s)?1:0;
    }
    private boolean t2SumHelper(TreeNode A, int B, HashSet<Integer> s){
        if(A== null) return false;
        if(s.contains(B-A.val)) return true;
        else    s.add(A.val);
        return (t2SumHelper(A.left, B,s) || t2SumHelper(A.right, B,s));
    }

    public int commonNodeSum(TreeNode A, TreeNode B) {
        long sum =0L;
        int M = 1000000007;
        HashSet<Integer> treeANodeVal = new HashSet<>(inOrderTravel(A));
        ArrayList<Integer> treeBNodeVal = inOrderTravel(B);
        for (int val : treeBNodeVal) {
            if(treeANodeVal.contains(val))  sum = (sum+val)%M;
        }
        sum = (sum+M)%M;
        return (int)sum;
    }

    public ArrayList<ArrayList<Integer>> findFloorAndCeil(TreeNode A, ArrayList<Integer> B) {
        ArrayList<Integer> inOrder = inOrderTravel(A);
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for(int v : B) {
            int floor = -1, ceil = -1;
            ArrayList<Integer> fc = new ArrayList<>();
            int l = 0, h=inOrder.size()-1;
            while (l<=h){
                int mid = (l+h)/2;
                if(inOrder.get(mid)==v){
                    floor=v;
                    ceil=v;
                    break;
                }else if(inOrder.get(mid)>v){
                    ceil = inOrder.get(mid);
                    h = mid-1;
                }else{
                    floor = inOrder.get(mid);
                    l = mid+1;
                }
            }
            fc.add(floor);
            fc.add(ceil);
            ans.add(fc);
        }
        return ans;
    }

    int max=Integer.MIN_VALUE;
    public int diameterOfBST(TreeNode A) {
        if(A==null) return -1;
        int l = diameterOfBST(A.left);
        int r = diameterOfBST(A.right);
        int ans = l+r+2;
        max = Math.max(max, ans);
        return Math.max(l,r)+1;
    }

    public int BSTNodeInRange(TreeNode A, int B, int C) {
        if(A==null) return 0;
        int count =0;
        if(A.val>=B && A.val<=C){
            count += BSTNodeInRange(A.left,B,C);
            count += BSTNodeInRange(A.right,B,C);
            count++;
        }else if(A.val<B){
            count += BSTNodeInRange(A.right,B,C);
        }else if(A.val>C){
            count += BSTNodeInRange(A.left,B,C);
        }
        return count;
    }

    public String checkBSTWithExactlyOneNode(ArrayList<Integer> pre) {
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        int root = pre.get(0);
        for (int i = 1; i < pre.size(); i++) {
            if(pre.get(i)>root){
                min = root;
            }else max = root;
            if(pre.get(i)<min || pre.get(i)>max)    return "NO";
            root = pre.get(i);
        }
        return "YES";
    }

    public int distanceBtwNode(TreeNode A, int B, int C) {
        ArrayList<Integer> pathB = pathOfNode(A,B);
        ArrayList<Integer> pathC = pathOfNode(A,C);
        ArrayList<Integer> pathBtwNode = new ArrayList<>();
        int i;
        for (i = 0; i < Math.min(pathB.size(), pathC.size()); i++) {
            if(!Objects.equals(pathB.get(i), pathC.get(i)))  break;
        }
        if(i!=0) {
            i = i - 1;
        }
        for (int j = pathB.size() - 1; j >= i; j--) {
            pathBtwNode.add(pathB.get(j));
        }
        for (int j = i + 1; j < pathC.size(); j++) {
            pathBtwNode.add(pathC.get(j));
        }
//        System.out.println(pathBtwNode);
        return pathBtwNode.size() - 1;
    }
    private ArrayList<Integer> pathOfNode(TreeNode A, int B){
        if(A==null) return null;
        if(A.val==B){
            return new ArrayList<>(Arrays.asList(A.val));
        }
        ArrayList<Integer> path = pathOfNode(A.left,B);
        if(path==null){
            path = pathOfNode(A.right,B);
        }
        if(path!=null)  path.add(0,A.val);
        return path;
    }

    //Least common ancestor
    public int lca(TreeNode A, int B, int C) {
        ArrayList<Integer> pathB = pathOfGivenNode(A, B);
        ArrayList<Integer> pathC = pathOfGivenNode(A, C);
        int j=0;
        for (j = 0; j < Math.min(pathB.size(), pathC.size()); j++) {
            if(!Objects.equals(pathB.get(j), pathC.get(j)))  break;
        }
        return (j==0)? -1 : pathB.get(j-1);
    }

    public ArrayList<Integer> inorderTraversalUsingStack(TreeNode A) {
        Stack<TreeNode> s = new Stack<>();
        ArrayList<Integer> ans = new ArrayList<>();
        TreeNode curr= A;
        while (curr!=null || !s.isEmpty()){
            if(curr!=null){
                s.push(curr);
                curr= curr.left;
            }else{
                TreeNode node = s.peek();
                s.pop();
                ans.add(node.val);
                curr = node.right;
            }
        }
        return ans;
    }

    public TreeNode sortedArrayToBST(final int[] A) {
        return constructBBST(A,0,A.length-1);
    }
    private TreeNode constructBBST(int[] A, int s, int e) {
        if(s>e) return null;
        int mid = (s+e)/2;
        TreeNode root = new TreeNode(A[mid]);
        root.left = constructBBST(A,s,mid-1);
        root.right = constructBBST(A,mid+1,e);
        return root;
    }


    //TODO: Learning pending
    class Value{
        int max_size=0;
        boolean is_bst = false;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
    }
    public int largestBSTSubTreeSize(TreeNode A){
        Value val = new Value();
        largestBSTSubTreeSizeHelper(A,val,val,val,val);
        return val.max_size;
    }
    private int largestBSTSubTreeSizeHelper(TreeNode A, Value min_ref, Value max_ref, Value max_size_ref, Value is_bst_ref){
        if(A==null){
            is_bst_ref.is_bst = true;
            return 0;
        }
        int min = Integer.MAX_VALUE;
        boolean left_flag = false;
        boolean right_flag = false;
        int ls,rs;
        max_ref.max=Integer.MIN_VALUE;
        ls = largestBSTSubTreeSizeHelper(A.left,min_ref,max_ref,max_size_ref,is_bst_ref);
        if(is_bst_ref.is_bst && A.val>max_ref.max){
            left_flag= true;
        }
        min = min_ref.min;
        min_ref.min = Integer.MAX_VALUE;
        rs = largestBSTSubTreeSizeHelper(A.right,min_ref,max_ref,max_size_ref,is_bst_ref);
        if(is_bst_ref.is_bst && A.val<min_ref.min){
            right_flag=true;
        }
        //Update Min max value for parent recursion
        if(min<min_ref.min){
            min_ref.min = min;
        }
        if(A.val<min_ref.min){
            min_ref.min=A.val;
        }
        if(A.val>max_ref.max){
            max_ref.max=A.val;
        }

        if(left_flag && right_flag){
            if(ls+rs+1>max_size_ref.max_size){
                max_size_ref.max_size = ls+rs+1;
            }
            return ls+rs+1;
        }else{
            is_bst_ref.is_bst=false;
            return 0;
        }
    }

    public int isValidBST(TreeNode A) {
        boolean left = isInGivenRange(A.left, Integer.MIN_VALUE, A.val-1);
        boolean right = isInGivenRange(A.right, A.val+1, Integer.MAX_VALUE);
        return (left && right)?1:0;
    }
    private boolean isInGivenRange(TreeNode A, int min, int max){
        if(A==null) return true;
        if(A.val>=min && A.val<=max) {
            return ( isInGivenRange(A.left, min, A.val - 1) && isInGivenRange(A.right, A.val + 1, max) );
        }else return false;
    }

    public static ArrayList<Integer> diagonalViewLeftToRight(TreeNode A){
        ArrayList<Integer> ans = new ArrayList<>();
        Queue<Pair<TreeNode,Integer>> q = new LinkedList<>();
        HashMap<Integer,ArrayList<Integer>> hm = new HashMap<>();
        preOrderWithDiagonalLevel(A,q,0);
        for(Pair<TreeNode,Integer> p : q){
            ArrayList<Integer> nodeValues;
            if(!hm.containsKey(p.index)){
                nodeValues = new ArrayList<>();
            }else {
                nodeValues = hm.get(p.index);
            }
            nodeValues.add(p.node.val);
            hm.put(p.index,nodeValues);
        }
        for (int i = 0; i < hm.size(); i++) {
            ans.addAll(hm.get(i));
        }
        return ans;
    }
    private static void preOrderWithDiagonalLevel(TreeNode A, Queue<Pair<TreeNode,Integer>> q, int level){
        if(A==null) return;
        q.add(new Pair<>(A,level));
        preOrderWithDiagonalLevel(A.left, q, level+1);
        preOrderWithDiagonalLevel(A.right, q, level);
    }

    public ArrayList<Integer> bottomView(TreeNode A) {
        ArrayList<Integer> ans = new ArrayList<>();
        HashMap<Integer,Integer> hm = new HashMap<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Queue<Pair<TreeNode, Integer>> q = new LinkedList<>();
        q.add(new Pair<>(A,0));
        while (!q.isEmpty()){
            Pair<TreeNode, Integer> p = q.peek();
            q.remove();
            TreeNode node = p.node;
            int level = p.index;
            min = Math.min(min,level);
            max = Math.max(max,level);

            hm.put(level,node.val);
            if(node.left!=null) q.add(new Pair<>(node.left,level-1));
            if(node.right!=null) q.add(new Pair<>(node.right,level+1));
        }
        for (int j = min; j <=max; j++) {
            ans.add(hm.get(j));
        }
        return ans;
    }

    public ArrayList<Integer> topView(TreeNode A) {
        ArrayList<Integer> ans = new ArrayList<>();
        HashMap<Integer,Integer> hm = new HashMap<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Queue<Pair<TreeNode, Integer>> q = new LinkedList<>();
        q.add(new Pair<>(A,0));
        while (!q.isEmpty()){
            Pair<TreeNode, Integer> p = q.peek();
            q.remove();
            TreeNode node = p.node;
            int level = p.index;
            min = Math.min(min,level);
            max = Math.max(max,level);
            if(!hm.containsKey(level)){
                hm.put(level,node.val);
            }
            if(node.left!=null) q.add(new Pair<>(node.left,level-1));
            if(node.right!=null) q.add(new Pair<>(node.right,level+1));
        }
        for (int j = min; j <=max; j++) {
            ans.add(hm.get(j));
        }
        return ans;
    }

    public ArrayList<Integer> rightView(TreeNode A) {
        ArrayList<Integer> ans = new ArrayList<>();
        Deque<TreeNode> q = new LinkedList<>();
        q.addLast(A);
        q.addLast(null);
        ans.add(A.val);
        while (!q.isEmpty()){
            TreeNode node = q.peekFirst();
            q.removeFirst();
            if(node==null){
                if(!q.isEmpty()){
                    ans.add(q.peekLast().val);
                    q.addLast(null);
                }
            }else{
                if(node.left!=null) q.addLast(node.left);
                if(node.right!=null) q.addLast(node.right);
            }
        }
        return ans;
    }

    public static ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode A) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        HashMap<Integer, ArrayList<TreeNode>> hm = new HashMap<>();
        int minLevel = Integer.MAX_VALUE;
        int maxLevel = Integer.MIN_VALUE;
        Queue<Pair<TreeNode,Integer>> q = new LinkedList<>();
        q.add(new Pair<>(A,0));
        while (!q.isEmpty()){
            Pair<TreeNode, Integer> p = q.peek();
            q.remove();
            TreeNode n = p.node;
            int level = p.index;
            minLevel = Math.min(minLevel,level);
            maxLevel = Math.max(maxLevel,level);
            ArrayList<TreeNode> verticalNodes;
            if(!hm.containsKey(level)){
                verticalNodes = new ArrayList<>();
            }else{
                verticalNodes = hm.get(level);
            }
            verticalNodes.add(n);
            hm.put(level,verticalNodes);

            if(n.left!=null)    q.add(new Pair<>(n.left,level-1));
            if(n.right!=null)    q.add(new Pair<>(n.right,level+1));
        }
        for (int i = minLevel; i <=maxLevel; i++) {
            ArrayList<Integer> values = new ArrayList<>();
            for(TreeNode n : hm.get(i)){
                values.add(n.val);
            }
            ans.add(values);
        }
        return ans;
    }

    public static TreeNode buildTree(ArrayList<Integer> A, ArrayList<Integer> B)    {
        HashMap<Integer,Integer> hmIno = new HashMap<>();
        for (int i =0; i<B.size(); i++) hmIno.put(B.get(i),i);
        return createBinaryTree(A, 0, A.size()-1, hmIno, 0, B.size()-1);
    }

    private static TreeNode createBinaryTree(ArrayList<Integer> pre, int ps, int pe, HashMap<Integer,Integer> ino, int is, int ie) {
        if(ps>pe) return null;
        TreeNode root = new TreeNode(pre.get(ps));
        int inx = ino.get(pre.get(ps));
        int dis = inx - is;
        root.left = createBinaryTree(pre,ps+1,ps+dis, ino, is, inx-1);
        root.right= createBinaryTree(pre,ps+dis+1,pe, ino, inx+1, ie);
        return root;
    }

    private static TreeNode createBinaryTreeUsingPostAndInorder(ArrayList<Integer> post, int ps, int pe, HashMap<Integer,Integer> ino, int is, int ie){
        if(ps>pe) return null;
        TreeNode root = new TreeNode(post.get(pe));
        int index = ino.get(post.get(pe));
        int dis = index - is;
        root.left = createBinaryTreeUsingPostAndInorder(post,ps,ps+dis-1,ino,is,index-1);
        root.right = createBinaryTreeUsingPostAndInorder(post,ps+dis,pe-1,ino,index+1,ie);
        return root;
    }

    public static ArrayList<Integer> leftView(TreeNode A){
        Deque<TreeNode> q = new LinkedList<>();
        q.addLast(A);
        q.addLast(null);
        ArrayList<Integer> level = new ArrayList<>();
        level.add(A.val);
        while (!q.isEmpty()){
            TreeNode t = q.peekFirst();
            q.removeFirst();
            if(t==null){
                if(!q.isEmpty()){
                    level.add(q.peekFirst().val);
                    q.addLast(null);
                }
            }else{
                if(t.left!=null)    q.addLast(t.left);
                if(t.right!=null)   q.addLast(t.right);
            }
        }
        return level;
    }

    public static ArrayList<ArrayList<Integer>> travelLeftToRightPrintByLevel(TreeNode A){
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        Deque<TreeNode> q = new LinkedList<>();
        q.add(A);
        q.add(null);
        ArrayList<Integer> level = new ArrayList<>();

        while (!q.isEmpty()){
            TreeNode t = q.peek();
            q.removeFirst();
            if(t==null){
                ans.add(level);
                level = new ArrayList<>();
                if(!q.isEmpty()) q.add(null);
            }else{
                level.add(t.val);
                if(t.left!=null) q.add(t.left);
                if(t.right!=null) q.add(t.right);
            }
        }
        return ans;
    }

    public static double average(ArrayList<Integer> list){
        int n = list.size();
        double s = 0;
        for(int l : list) s+=l;
        return s/n;
    }

    public static ArrayList<Integer> travelLeftToRight(TreeNode A){
        ArrayList<Integer> ans = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(A);
        while (!q.isEmpty()){
            TreeNode t = q.peek();
            q.remove();
            ans.add(t.val);
            if(t.left!=null)  q.add(t.left);
            if(t.right!=null)  q.add(t.right);
        }
        return ans;
    }

    public static ArrayList<Integer> pathOfGivenNode(TreeNode A, int B) {
        ArrayList<Integer> path = new ArrayList<>();
        ArrayList<Integer> ans= new ArrayList<>();
        checkWithPath(A,B,path);
        for (int i = path.size()-1; i>=0; i--) {
            ans.add(path.get(i));
        }
        return ans;
    }
    private static boolean checkWithPath(TreeNode root, int B, ArrayList<Integer> path){
        if(root==null) return false;
        if(root.val==B){
            path.add(root.val);
            return true;
        }
        if(checkWithPath(root.left,B,path) || checkWithPath(root.right,B,path)){
            path.add(root.val);
            return true;
        }else return false;
    }

    public static ArrayList<Integer> inOrderTravel(TreeNode A){
        ArrayList<Integer> ans = new ArrayList<>();
        if(A ==null) return null;
        ArrayList<Integer> allLeft = inOrderTravel(A.left);
        if(allLeft!=null)   ans.addAll(allLeft);
        ans.add(A.val);
        ArrayList<Integer> allRight = inOrderTravel(A.right);
        if(allRight!=null) ans.addAll(allRight);
        return ans;
    }

    public int[] preorderTraversal(TreeNode A) {
        ArrayList<Integer> ans = new ArrayList<>();
        preorderTraversal(A,ans);
        int[] result = new int[ans.size()];
        int j=0;
        for(int i: ans) {
            result[j] = i;
            j++;
        }
        return result;
    }

    public static void preorderTraversal(TreeNode A, ArrayList<Integer> ans) {
        if(A==null) return;
        ans.add(A.val);
        preorderTraversal(A.left,ans);
        preorderTraversal(A.right,ans);
    }

    public static void preOrderTravel(TreeNode root){
        if(root ==null) return;
        System.out.print(root.val +" ");
        preOrderTravel(root.left);
        preOrderTravel(root.right);
    }

    public static void postOrderTravel(TreeNode root){
        if(root ==null)  return;
        postOrderTravel(root.left);
        postOrderTravel(root.right);
        System.out.print(root.val +" ");
    }

    public static int size(TreeNode root){
        int count=0;
        if(root==null)  return 0;
        return count+size(root.left)+size(root.right)+1;
        /*count += size(root.left);
        count++;
        count+=size(root.right);
        return count;*/

    }

    public static int sum(TreeNode root){
        int sum=0;
        if(root==null)  return 0;
        sum +=root.val;
        sum += sum(root.left);
        sum +=sum(root.right);
        return sum;
    }

    public static int height(TreeNode root){
        if(root==null) return -1;
        return 1+ Math.max(height(root.left),height(root.right));
    }

    public static int isSameTree(TreeNode A, TreeNode B) {
        if(A==null && B==null) return 1;
        if(A == null || B == null) return 0;
        if(A.val ==B.val && isSameTree(A.left,B.left)==1){
            return isSameTree(A.right,B.right);
        }else return 0;
    }

    public static void invertingTree(TreeNode A){
        if(A==null) return;
        TreeNode temp = A.left;
        A.left = A.right;
        A.right = temp;
        invertingTree(A.left);
        invertingTree(A.right);
    }

    //Not working
    public static int isSymmetric(TreeNode A){
        if(A==null) return 1;
        TreeNode leftN = A.left;
        TreeNode rightN = A.right;
        if(leftN == null && rightN == null) return 1;
        if(leftN == null || rightN == null) return 0;
        if(leftN.val==rightN.val)   return 1;
        return (isSymmetric(leftN)==1 && isSymmetric(leftN)==1) ? 1 : 0;

    }

    public static int isBSTTree(TreeNode A){
        ArrayList<Integer> inOrder = inOrderTravel(A);
        int max=inOrder.get(0);
        for (int i = 1; i < inOrder.size(); i++) {
            if(max<inOrder.get(i)) max = inOrder.get(i);
            else return 0;
        }
        return 1;
    }

    public static int isBalanced(TreeNode A, int res){
        if(A==null) return 0;
        int l = 1+isBalanced(A.left,res);
        int r = 1+isBalanced(A.right,res);
        if(Math.abs(l-r)>1) res = 0;
        return Math.max(l,r);
    }

    public static int searchInBST(TreeNode A, int k){
        if(A==null) return 0;
        if(A.val ==k) return 1;
        else if(A.val >k)   return searchInBST(A.left,k);
        else  return searchInBST(A.right,k);

        //interative
        /*while (A!=null){
            if(A.data==k)   return 1;
            else if(A.data>k)   A = A.left;
            else A = A.right;
        }
        return 0;*/
    }

    int i=-1;
    ArrayList<Integer> ans = new ArrayList<>();
    public  ArrayList<Integer> pathOfGivenValue(TreeNode A, int B){

        if(A==null) return ans;
        else if(A.val==B){
            ans.add(A.val);
            i++;
            return ans;
        }else {
            ans.add(A.val);
            i++;
            ans = pathOfGivenValue(A.left,B);
            if(ans.contains(B)) {
                return ans;
            }
            ans = pathOfGivenValue(A.right, B);
            if(ans.contains(B)) {
                return ans;
            }
            ans.remove(i);
            i--;
            return ans;
        }
    }
}
