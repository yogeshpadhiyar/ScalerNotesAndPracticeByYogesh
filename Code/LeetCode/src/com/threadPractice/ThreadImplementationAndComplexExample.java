package com.threadPractice;

/*What is Thread Scheduling?
In which order thread should execute is decide by thread scheduler
We can not say exact output. It's depend on JVM to JVM

Difference btw t.start() and t.run()?
using t.start() -> new thread will be created and start job that define in run method.
using t.run() -> it's work as normal method and not create new thread.

why need to execute thread using start() instead of run()?
using start method -> 1. Register the thread to the thread scheduler
                      2. perform mandatory activity
                      3.invoke run method

Overloading of run method is possible ?
Yes, But start method only call no arg run method.
overloaded run method need to execute explicitly

Can we don't override run method?
yes it is possible don't affect program.
but it's higly recommened to override run method otherwise it's act as just call of thread. it's better to don't go for multithreading

Can we have to override start method?
if we override start method total output execute by only main thread.
Calling of start method act as simple method calling.
it is not recommnded override start method otherwise don't go for multi threading concept.

If we call super.start method into override start method it's we create new thread and work as flow.

We can not start thread 2 time it's give IllegalThreadStateException

Runnable approach is recommended due to implementation so that other class can extends
Hybrid Approach also possible

every thread has their name that given by JVM or by user. Able to change name of thread even main thread also.
To see which method is running Thread class has static method currentThread().

Priority
1 is Min priority
10 is Max priority
IF same priority occur then it's depend on thread scheduler which on execute first
IF we try to set Invalid priority give runTime exception : IllegalArgumentException.

Default priority only for main thread is 5. For All remaining thread default priority will be inherited from parent thread to child

Prevent thread using this method: yield , join , sleep
Yield method - stop execution and check if same priority thread is waiting for execute or not ..if not present or low priority thread present
 then continue execution otherwise pause execution and other thread is execute

Thread class has 8 constructor
Thread t = new Thread()
Thread t = new Thread(Runnable r)
Thread t = new Thread(String name)
Thread t = new Thread(Runnable r , String name)
Thread t = new Thread(ThreadGroup g, String name)
Thread t = new Thread(ThreadGroup g, Runnable r)
Thread t = new Thread(ThreadGroup g, Runnable r,  String name)
Thread t = new Thread(ThreadGroup g, Runnable r,  String name, long stackSize)
*/

public class ThreadImplementationAndComplexExample {
    public static void main(String[] args) {
        // we have now only 1 thread that is main thread
        //using new MyThread() we create thread initialization
        MyThread t = new MyThread();
        //usign start main thread start child thread
        //using start() we start method. So now we have 2 thread (1st is main & 2nd is child)
        t.start();  //we start thread it execute code of run method
        //this for loop execute by main thread
        for (int i = 0; i < 10; i++) {
            System.out.println("main thread");
        }


        MyRunnable r = new MyRunnable();
        //Need to add target runnable so able to call object r run method
        Thread t1 = new Thread(r);


        //Case Study
        MyRunnable r1 = new MyRunnable();
        Thread t2 = new Thread();
        Thread t3 = new Thread(r);
        //case1:
        t2.start(); //run without error ...but not use r1 run method
        t2.run();   // execute run method like normal method
        t3.start(); // new thread creation -- execute r1 run method
        t3.run();   // r1 run method execute  just like normal method call
        //r1.start();     // compile time error -- cannot find symbol
        r1.run();   //r1 run method execute  just like normal method call


        //Hybrid approach also work because MyThread extends Thread and Thread class implement Runnable interface
        MyThread t4 = new MyThread();
        Thread t5 = new Thread(t4);
        t5.start();

    }
}

class MyThread extends Thread{
    @Override
    public void run() {
        //full code of in run is called job of thread
        for (int i = 0; i < 10; i++) {
            System.out.println("child thread");
        }
    }
}

class MyRunnable implements Runnable{

    @Override
    public void run() {

    }
}
