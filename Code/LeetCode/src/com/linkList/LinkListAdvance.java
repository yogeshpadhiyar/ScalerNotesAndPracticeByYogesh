package com.linkList;


import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class ListNode {
  public int val;
  public ListNode next,down;
  ListNode(int x) { val = x; next = null; }
    public ListNode() { next = null; }
}

class RandomListNode {
      int label;
      RandomListNode next, random;
      RandomListNode(int x) { this.label = x; }
}

public class LinkListAdvance {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
//        int B = sc.nextInt();
//        int C = sc.nextInt();
        ListNode head = new ListNode();
        ListNode t = head;
        for (int i = 0; i < n; i++) {
            ListNode newNode = new ListNode(sc.nextInt());
            t.next = newNode;
            t=newNode;
        }

        /*ListNode head2 = new ListNode();
        ListNode t2 = head2;
        for (int i = 0; i < m; i++) {
            ListNode newNode = new ListNode(sc.nextInt());
            t2.next= newNode;
            t2=newNode;
        }*/
        head= head.next;
//        head2= head2.next;
        head = rotateRight(head,m);
        print(head);
    }
    public static int size(ListNode A){
        int size=0;
        ListNode t = A;
        while (t!=null) {
            size++;
            t=t.next;
        }
        return size;
    }
    public static void print(ListNode A){
        ListNode t = A;
        if(t==null) {
            System.out.println("null");
            return;
        }
        while (t.next !=null){
            System.out.print(t.val+"->");
            t=t.next;
        }
        System.out.print(t.val+"->null");
        System.out.println();
    }

    public static ListNode flatten(ListNode root) {
        if(root==null||root.next ==null){
            return root;
        }
        return mergeTwoDownLists(root,flatten(root.next));
    }
    public static ListNode mergeTwoDownLists(ListNode A, ListNode B) {
        if(A==null) return B;
        if(B==null) return A;
        ListNode result;
        if(A.val<B.val){
            result =A;
            result.down = mergeTwoDownLists(A.down,B);
        }else{
            result =B;
            result.down = mergeTwoDownLists(A,B.down);
        }
        return result;
    }

    public static int longestPalindrome(ListNode A){
        ListNode curr = A;
        ListNode prev = null;
        int ans=0;
        while (curr!=null){
            //Reverse list
            ListNode nxt = curr.next;
            curr.next = prev;
            //Count Longest palindrome
            //for odd
            ans = Math.max(ans, (2 * countSameNode(prev, nxt))+1);
            //for even
            ans = Math.max(ans, 2 * countSameNode(curr, nxt));
            //move pointer forward
            prev=curr;
            curr=nxt;
        }
        return ans;
    }
    private static int countSameNode(ListNode A, ListNode B) {
        int c=0;
        while (A!=null && B!=null){
            if(A.val!=B.val){
                break;
            }
            c++;
            A=A.next;
            B=B.next;
        }
        return c;
    }

    public ListNode getIntersectionNode(ListNode A, ListNode B) {
        if(A==null || B==null) return null;
        int sizeA = size(A);
        int sizeB = size(B);
        int diff = Math.abs(sizeA-sizeB);
        if(sizeA>sizeB){
            while (diff>0) {
                A = A.next;
                diff--;
            }
        }else{
            while (diff>0) {
                B = B.next;
                diff--;
            }
        }
        ListNode head=null;
        while (A!=null && B!=null){
            if(A==B){
                head = A;
                return head;
            }
            A=A.next;
            B=B.next;
        }
        return head;
    }

    public static ListNode deleteDuplicateII(ListNode head){
        if(head==null||head.next ==null){
            return head;
        }
        ListNode first=head;
        ListNode second=head.next;
        ListNode list=new ListNode();
        ListNode listHead=list;
        Set<Integer> set=new HashSet<>();
        while(first!=null&&second!=null){
            if(first.val==second.val){
                set.add(first.val);
                second=second.next;
            }else{
                if(!set.contains(first.val)){
                    list.next =new ListNode(first.val);
                    list=list.next;
                }
                first=second;
                second=second.next;

            }
        }
        if(first!=null&&!set.contains(first.val)){
            list.next =new ListNode(first.val);
            list=list.next;
        }
        return listHead.next;
    }

    public static ListNode swapPairs(ListNode A) {
        ListNode t1 = A;
        if(t1 == null || t1.next ==null) return A;
        ListNode t2 = A.next;
        ListNode h2 = t2;
        ListNode h1 = t1;
        while (t2!=null){
            t1.next = t2.next;
            t1=t1.next;
            if(t1!=null)
                t2.next = t1.next;
            t2=t2.next;
        }
        print(h1);
        print(h2);
        ListNode head = h2;
        ListNode t= h2;
        boolean flag = false;
        while (h1!=null && h2!=null){
            if(flag){
                flag=false;
                h1=h1.next;
                t.next =h2;
            }else{
                flag=true;
                h2=h2.next;
                t.next =h1;
            }
            t=t.next;
        }
        return head;
    }

    public static ListNode addTwoNumbers(ListNode A, ListNode B) {
        int sum,carry=0;
        ListNode at= A;
        ListNode bt= B;
        ListNode head,t;
        head= new ListNode(0);
        t = head;
        while (at!=null && bt!=null){
            sum = carry+at.val+ bt.val;
            ListNode node = new ListNode(sum%10);
            carry = sum/10;
            t.next = node;
            t=node;
            at=at.next;
            bt=bt.next;
        }
        while (at!=null){
            sum = carry + at.val;
            ListNode node = new ListNode(sum%10);
            carry = sum/10;
            t.next =node;
            t=node;
            at=at.next;
        }

        while (bt!=null){
            sum = carry + bt.val;
            ListNode node = new ListNode(sum%10);
            carry = sum/10;
            t.next =node;
            t=node;
            bt=bt.next;
        }
        if(carry!=0){
            ListNode node = new ListNode(carry);
            t.next = node;
            t = node;
        }
        head=head.next;
        return head;

    }

    public static RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode t = head;
        while (t!=null){
            RandomListNode n = new RandomListNode(t.label);
            n.next = t.next;
            t.next = n;
            t=t.next.next;
        }

        RandomListNode t1 = head;
        RandomListNode t2 = head.next;
        while (t1!=null){
            if(t1.random!=null) {
                t2.random = t1.random.next;
            }
            t1 = t1.next.next;
            if(t2.next!=null)
                t2 = t2.next.next;
        }

        RandomListNode t3 = head;
        RandomListNode t4 = head.next;
        RandomListNode h2 = t4;
        while (t3!=null){
            t3.next = t4.next;
            t3=t3.next;
            if(t3!=null)
                t4.next = t3.next;
            t4=t4.next;
        }
        return h2;
    }

    public static ListNode sortList(ListNode A) {
        if(A==null || A.next ==null) return A;
        ListNode h1=A,h2;
        ListNode c = getMiddleElm(A);
        h2=c.next;
        c.next =null;
        h1 = sortList(h1);
        h2 = sortList(h2);
        return mergeTwoLists(h1,h2);
    }

    public static ListNode mergeTwoLists(ListNode A, ListNode B) {
        ListNode head,h1=A,h2=B;
        if(A==null) return B;
        if(B==null) return A;
        if(h1.val<h2.val){
            head=h1;
            h1=h1.next;
        }else{
            head=h2;
            h2=h2.next;
        }
        ListNode h3 = head;
        while (h1!=null && h2!=null){
            if(h1.val<h2.val){
                h3.next = h1;
                h1=h1.next;
            }else{
                h3.next = h2;
                h2=h2.next;
            }
            h3=h3.next;
        }
        if(h1==null)    h3.next = h2;
        if(h2==null)    h3.next = h1;
        return head;
    }

    public static ListNode getMiddleElm(ListNode A){
        ListNode s=A, f = A;
        while (f.next !=null && f.next.next !=null){
            s= s.next;
            f= f.next.next;
        }
        return s;
    }

    public static ListNode reorderList(ListNode A) {
        ListNode s=A, f = A;
        while (f.next !=null && f.next.next !=null){
            s= s.next;
            f= f.next.next;
        }
        ListNode h2 = s.next;
        s.next =null;
        ListNode h1 = A , head = A ,t =A;
        h2 = reverseList(h2);
        boolean flag = true;
        while (h1!=null && h2!=null){
            if(flag){
                flag=false;
                h1=h1.next;
                t.next =h2;
            }else{
                flag=true;
                h2=h2.next;
                t.next =h1;
            }
            t=t.next;
        }
        return head;
    }

    public static ListNode detectCycle(ListNode a) {
        ListNode s = a, f=a;
        boolean hasCycle=false;
        while (f.next !=null && f.next.next !=null){
            s=s.next;
            f=f.next.next;
            if(s==f){
                hasCycle = true;
                break;
            }
        }
        if(hasCycle){
            ListNode h = a;
            while (h.next !=s.next){
                s=s.next;
                h=h.next;
            }
            s.next =null;
        }
        return a;
    }

    public static ListNode deleteDuplicates(ListNode A) {
        ListNode curr , currNext , next2nd;
        curr=A;
        if(A==null) return A;
        if(A.next ==null)
            return A;
        else
            currNext= curr.next;
        if(currNext.next ==null){
            if(curr.val==currNext.val){
                A=A.next;
                return A;
            }else   return A;
        }else
            next2nd = currNext.next;

        while (next2nd!=null){
            if(curr.val==currNext.val && currNext.val== next2nd.val){
                curr.next = next2nd.next;
                currNext=next2nd;
                next2nd=next2nd.next;
            }
            else if(curr.val==currNext.val || currNext.val== next2nd.val){
                curr.next = next2nd;
                currNext=next2nd;
                next2nd=next2nd.next;
            }else{
                curr = currNext;
                currNext=next2nd;
                next2nd=next2nd.next;
            }
        }
        return A;
    }

    public static ListNode removeNthFromEnd(ListNode A, int B) {
        int size = size(A);
        if(B>=size){
            A = A.next;
            return A;
        }else{
            ListNode t = A;
            for (int i = 1; i < size - B; i++) {
                t= t.next;
            }
            t.next =t.next.next;
        }
        return A;
    }

    public static ListNode reverseBetween(ListNode A, int B, int C) {
        ListNode h, t=A;
        ListNode h2=null ,rev = null;
        ListNode f=A;
        boolean flag= true;
        for (int i = 1; i < B - 1; i++) {
            f=f.next;
        }
        if(B!=1)    t=f.next;
        h=t;
        for (int i = B; i <= C; i++) {
            h = h.next;
            t.next = rev;
            rev = t;
            t =h;
            if(flag){
                h2=rev;
                flag=false;
            }
        }
        if(f.next !=null) {
            f.next = rev;
        }else{
            A=rev;
        }
        h2.next =h;
        return A;
    }

    public ListNode deleteMiddleNode(ListNode A) {
        int size = size(A);
        if(size<=1){
            ListNode t=null;
            return t;
        }
        int mid = (size%2==0)? size/2+1:size/2;
        ListNode t = A;
        for (int i = 1; i < mid; i++) {
            t=t.next;
        }
        t.next = t.next.next;
        return A;
    }

    public static ListNode reverseList(ListNode A) {
        ListNode h1 =A, t =A;
        ListNode h2 = null;
        while (h1!=null){
            h1 = h1.next;
            t.next = h2;
            h2=t;
            t=h1;
        }
        return h2;
    }

    public static ListNode reverseList(ListNode A, int B) {
        int size = size(A);
        for (int i = 1; i <=size; i+=B) {
            A=reverseBetween(A,i,i+B-1);
        }
        return A;
    }

    public static ListNode middleElm(ListNode A){
        int size = size(A);
        if(size==1) return A;
        int mid = (size%2==0)? size/2+1:size/2;
        ListNode t = A;
        for (int i = 0; i < mid; i++) {
            t=t.next;
        }
        return t;
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if(head==null || head.next==null) return head;
        int size = size(head);
        k = k%size;
        if(k==0|| k ==size) return head;
        head = reverseList(head);
        head = reverseBetween(head,1,k);
        head = reverseBetween(head,k+1,size);
        return head;
    }
}
