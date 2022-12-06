package com.stack;



public class CreateStack {

    public class Node{
        int value;
        Node next;
        int min = Integer.MAX_VALUE;
        public Node(int n){
            this.value = n;
            this.next = null;
        }
    }
    Node head;
    int top=-1;
    int min= Integer.MAX_VALUE;
    public void push(int x) {
        if(top==-1){
            head = new Node(x);
            head.min = x;
            top++;
            return;
        }
        Node n = new Node(x);
        n.min = Math.min(x, head.min);
        n.next = head;
        head = n;
        top++;
    }

    public void pop() {
        if(top!=-1) {
            Node d = head;
            head = head.next;
            d.next=null;
            min = Integer.MAX_VALUE;
            top--;
        }
    }

    public int top() {
        if(top!=-1) return head.value;
        return -1;
    }

    public int getMin() {
        /*Node temp = head;
        if(top==-1) return -1;
        while (temp!=null){
            min = Math.min(min,temp.value);
            temp = temp.next;
        }
        return min;*/
        if(top==-1) return -1;
        return head.min;
    }
    public static void main(String[] args) {

    }
}
