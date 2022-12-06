package com.linkList;

import java.util.ArrayList;
import java.util.Scanner;

public class Assignment2 {
    static class ListNode {
       public int value;
       public ListNode next;
       ListNode(int x) { value = x; next = null; }
     }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> t = new ArrayList<>();
            t.add(sc.nextInt());
            t.add(sc.nextInt());
            t.add(sc.nextInt());
            A.add(t);
        }
        Solution s = new Solution();
        ListNode head = s.solve(A);
        ListNode temp = head;
        while (temp!=null){
            System.out.print(temp.value +" ");
        }
    }

    public static class Solution{
        public ListNode solve(ArrayList<ArrayList<Integer>> A){
            int size=0;
            ListNode head = null;
            for (ArrayList<Integer> ope : A){
                int op = ope.get(0);
                if(op==0){
                    //insert at head
                    ListNode node = new ListNode(ope.get(1));
                    node.next = head;
                    head = node;
                    size++;
                }else if(op==1){
                    //insert at end
                    ListNode node = new ListNode(ope.get(1));
                    if(size==0){
                        head =node;
                        size++;
                    }else {
                        ListNode temp = head;
                        while (temp.next != null) {
                            temp = temp.next;
                        }
                        node.next = temp.next;
                        temp.next = node;
                        size++;
                    }

                }else if(op==2){
                    //insert before given index
                    ListNode node = new ListNode(ope.get(1));
                    if(ope.get(2)==0){
                        node.next=head;
                        head=node;
                        size++;
                    }else if(ope.get(2)<=size) {
                        ListNode temp = head;
                        int i = 0;
                        while (i != ope.get(2) - 1) {
                            temp = temp.next;
                            i++;
                        }
                        node.next = temp.next;
                        temp.next = node;
                        size++;
                    }
                }else{
                    //delete at given index
                    if(ope.get(1)==0){
                        head=head.next;
                        size--;
                    }else if(ope.get(1)<size) {
                        ListNode temp = head;
                        int i = 0;
                        while (i != ope.get(1) - 1) {
                            temp = temp.next;
                            i++;
                        }
                        temp.next = temp.next.next;
                        size--;
                    }
                }
            }
            return head;
        }

    }
}
