package com.queue;

import java.util.Arrays;

public class MyCircularQueue {
    int[] cQueue;
    int front, rear, size, len;
    public MyCircularQueue(int k) {
        this.cQueue = new int[k];
        Arrays.fill(cQueue,-1);
        this.front = 0;
        this.rear = -1;
        this.size=k;
        this.len=0;
    }

    public boolean enQueue(int value) {
        if(isFull()) return false;
        else {
            cQueue[rear] = value;
            rear = (rear+1)%size;
            len++;
            return true;
        }
    }

    public boolean deQueue() {
        if(isEmpty())   return false;
        else{
            front = (front+1)%size;
            len--;
            return true;
        }
    }

    public int Front() {
        if(isEmpty())   return -1;
        return cQueue[front];
    }

    public int Rear() {
        if(isEmpty()) return -1;
        return cQueue[rear];
    }

    public boolean isEmpty() {
        return len==0;
    }

    public boolean isFull() {
        return len==size;
    }
}
