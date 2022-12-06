package com.other;

import java.util.*;

public class LFUCache {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int op = sc.nextInt();
        List<Integer> oper = new ArrayList<>();
        List<List<Integer>> val = new ArrayList<>();
        for (int i = 0; i < op; i++) {
            oper.add(sc.nextInt());
            int v = oper.get(i);
            List<Integer> t = new ArrayList<>();
            if(v==0){
                t.add(sc.nextInt());
            }else if(v==1){
                t.add(sc.nextInt());
            }else {
                t.add(sc.nextInt());
                t.add(sc.nextInt());
            }
            val.add(t);
        }
        LFUCache lfu=null;
        for (int i = 0; i < op; i++) {
            if(oper.get(i)==0){
                lfu = new LFUCache(val.get(i).get(0));
            }else if(oper.get(i)==1){
                if(lfu!=null) System.out.println(lfu.get(val.get(i).get(0)));
            }else{
                if(lfu!=null) lfu.put(val.get(i).get(0),val.get(i).get(1));
            }
        }

    }

    static class DLL implements Comparator<DLL> {
        int key,val,counter;
        DLL next,prev;
        public DLL(){}
        public DLL(int k, int v, int c){
            this.key=k;
            this.val=v;
            this.counter=c;
            this.next=null;
            this.prev=null;
        }

        @Override
        public int compare(DLL dll1, DLL dll2) {
            return dll1.counter-dll2.counter;
        }
    }

    public void insert(DLL node){
        tail.next = node;
        node.prev=tail;
        tail=tail.next;
        tail.next=null;
    }

    public void delete(DLL node){
        DLL p = node.prev;
        DLL n = node.next;
        if(p!=null) p.next=n;
        if(n!=null) n.prev=p;
    }

    HashMap<Integer,DLL> hm;
    List<DLL> list;
    int capacity;
    DLL head,tail;
    public LFUCache(int capacity) {
        this.capacity=capacity;
        this.hm=new HashMap<>();
        this.list = new LinkedList<>();
        head=new DLL();
        tail=head;
    }

    public int get(int key) {
        int val=-1;
        if(hm.containsKey(key)){
            DLL node = hm.get(key);
            val=node.val;
            put(key,val);
        }
        return val;
    }

    public void put(int key, int value) {
        if(!hm.containsKey(key)){
            if(hm.size()==capacity){
                DLL node = list.get(0);
                delete(node);
                hm.remove(node.key);
                list.remove(node);
            }
            DLL node = new DLL(key,value,1);
            hm.put(key,node);
            insert(node);
            list.add(node);
        }else {
            DLL node = hm.get(key);
            list.remove(node);
            node.val=value;
            node.counter++;
            if(node.next!=null){
                delete(node);
                insert(node);
            }
            list.add(node);
        }
    }
}
