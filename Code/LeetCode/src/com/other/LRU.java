package com.other;

import java.util.HashMap;

public class LRU {
    int capacity;
    HashMap<Integer,DLL> position;
    DLL head, tail;
    static class DLL{
        int key;
        int val;
        DLL next;
        DLL prev;
        public DLL(){}
        public DLL(int key, int val){
            this.key =key;
            this.val =val;
            this.next=null;
            this.prev=null;
        }
    }
    public void insertAtBack(DLL node){
        tail.next = node;
        node.prev = tail;
        tail=tail.next;
        tail.next=null;
    }

    public void delete(DLL node){
        DLL p = node.prev;
        DLL n = node.next;
        if(n!=null)
            n.prev = p;
        if(p!=null)
            p.next = n;
    }

    public LRU(int capacity){
        this.capacity = capacity;
        this.position = new HashMap<>();
        head = new DLL();
        tail=head;
    }

    public int get(int key) {
        int val =-1;
        if(!position.containsKey(key))
            return val;
        else{
            val = position.get(key).val;
            DLL node = position.get(key);
            if(node.next!=null) {
                delete(node);
                insertAtBack(node);
            }
        }
        return val;
    }

    public void set(int key, int value) {
        if(!position.containsKey(key)){
            if(position.keySet().size()==capacity) {
                position.remove(head.next.key);
                DLL temp = head.next;
                delete(head);
                head=temp;
            }
            DLL node = new DLL(key,value);
            position.put(key, node);
            insertAtBack(node);
        }else{
            DLL node = position.get(key);
            node.val = value;
            position.put(key,node);
            if(node.next!=null){
                delete(node);
                insertAtBack(node);
            }
        }
    }
}
