package com.threadPractice;
/*
* using yield method create example of which thread finish first.
* Some platform not provide proper support for yield method
*
* join use : if we want to wait for some other thread then use join method
* Which ever method has to wait that thread use join method
* Like : we have t1 , t2 thread.
* suppose : t1 has to wait for t2 execution
* then in t1 run code use join method like this : t2.join().
* when t2.join execute ...t1 thread go into wait state and once t2 complete t1 thread start execution
*
* Join also use with time period - given time period-time another thread is waiting
* Every join method throws InterruptedException if someone try to invoke (compulsory handle or throws)
* Method: join(long ms) , join(long ms, int ns)
* */
public class ThreadExample {
    public static void main(String[] args) {
        MyThreadClass t = new MyThreadClass();
        Thread thread = new Thread(t);
        thread.start();
        for (int i = 0; i < 10; i++) {
            System.out.println("Main Method"+ Thread.currentThread().getName());
        }
    }
}
class MyThreadClass implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Child Method : "+ Thread.currentThread().getName());
            Thread.yield();
        }
    }
}
/*
* Case DeadLock:
* DeadLock status : Thread.currentThread().join()
*   it means that same thread wait for complete same thread completion.
*   Thread wait infinity time.
* case :
* Child thread has to wait until complete main thread
*
* class MyThreadClass implements Runnable{
    static MyThread mt;
    @Override
    public void run() {
        try{
           mt.join()
         } catch(InterruptedException e){
                e.msg();
          }
        for (int i = 0; i < 10; i++) {
            System.out.println("Child Method : "+ Thread.currentThread().getName());
            Thread.yield();
        }
    }
}
* public static void main(String[] args) throws IE{
        MyThread.mt = Thread.currentThread();
        MyThreadClass t = new MyThreadClass();
        Thread thread = new Thread(t);
        thread.start();
        for (int i = 0; i < 10; i++) {
            System.out.println("Main Method"+ Thread.currentThread().getName());
            Thread.sleep(2000);
        }
    }
*
*
* */

/*
* Sleep :
* Exmaple Slide Rotate: each slide rotate after 5s.
*
* classs SlideRotate{
*   public static void main (String[] args) throws IE{
*   for(int i=0; i<10;i++){
*       sout("Slide - "+i);
*       Thread.sleep(5000);
*    }
* }
*
* How to interrupt thread ?
* using interrupt method of thread class: public void interrupt()
* Exmple :
* class MyThreadClass implements Runnable{
    static MyThread mt;
    @Override
    public void run() {
        try{
          for (int i = 0; i < 10; i++) {
           sout("I am lazy thread);
           Thread.sleep(2000);
          }
         } catch(InterruptedException e){
                sout("i got interrupt);
          }
    }
  public static void main(String[] args){
   MyThread t = new MyThread();
   t.start();
   t.interrupt();
   sout("End of main thread");
   }

  Output : I am lazy Thread. I got interrupt. End of main thread.

* Interrupt call never waste : it will wait for thread to go sleep and then interrupt thread. (JVM will take care of it)
* If target thread never go into sleep then interrupt call is waste(only one case)
*/