package com.linkList;

import java.util.Scanner;

public class CreateLinkList {

    public class Node{
        int value;
        Node next;
        public Node(int n){
          this.value = n;
          this.next = null;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        CreateLinkList cll = new CreateLinkList();
        Node head = cll.CreateLinkListWithNSize(n);
        System.out.println(cll.size(head));
        Node temp = head;
        /*for (int i = 0; i < n; i++) {
            System.out.println(temp.value);
            temp = temp.next;
        }*/
        while (temp!=null){
            System.out.print(temp.value+" ");
            temp = temp.next;
        }

    }

    public Node CreateLinkListWithNSize(int n) {
        Node head = new Node(1);
        Node temp = head;
        for (int i = 2; i <= n; i++) {
            Node nextNode =  new Node(i);
            temp.next = nextNode;
            temp = nextNode;
        }
        return head;
    }

    public int size(Node head){
        int i=0;
        Node temp = head;
        while (temp!=null){
            i++;
            temp = temp.next;
        }
        return i;
    }

    public Node insertAtIndex(Node head, int x, int index){
        int size =size(head);
        if(index>size){
            System.out.println("Out of Bound");
            return head;
        }
        Node node = new Node(x);
        if(index==0){
            node.next = head;
            head = node;
            return head;
        }
        Node temp = head;
        int i=0;
        while (i!=index-1){
            temp=temp.next;
            i++;
        }
        node.next = temp.next;
        temp.next = node;
        return head;
    }

    public int deleteAtIndex(Node head, int index){
        int size = size(head);
        if(size==0) return 0;
        if(index>size){
            System.out.println("Out of Bound");
            return 0;
        }
        if(index==0){
            Node deletedNode = head;
            head=head.next;
            return deletedNode.value;
        }
        Node temp = head;
        int i=0;
        while (i!=index-1){
            temp = temp.next;
            i++;
        }
        Node deletedNode = temp.next;
        temp.next = temp.next.next;
        return deletedNode.value;
    }
}
