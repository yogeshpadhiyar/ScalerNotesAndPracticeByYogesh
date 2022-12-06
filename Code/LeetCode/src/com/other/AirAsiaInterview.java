package com.other;

public class AirAsiaInterview {

}

class Queue{
    Node head;
    Node tail;
    int size=0;
    public void insert(int val){
        Node node = new Node(val);
        if(tail==null){
            tail = node;
            head = tail;
        }else{
            tail.next = node;
            tail = tail.next;
        }
        size++;
    }

    public Node remove(){
        Node node;
        if(head==null){
            throw  new RuntimeException();
        }else{
            node = head;
            head = head.next;
        }
        size--;
        return node;
    }

    public int peek(){
        if(head==null){
            throw new RuntimeException();
        }else{
            return head.val;
        }
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }
}

class Node{
    int val;
    Node next;
    public Node(){
        this.next = null;
    }
    public Node(int val){
        this.val = val;
        this.next = null;
    }
}
//aaabbbbcccabxcca // using stack without flag overhead
/*

Vehical parkning
parkingSystem {
    noofEntryGate
    noofExitGate
    List<flor>
}

flor{
    florId
    List<parkingSlot>
}

parkingSlot{
    id
    type (car/ bike / bus)
}

Ticket{
    EntryTime
    ExitTime
    parkingSlot Name
    vehical No
}

payment{
    onCash
    Type of payment
}*/
