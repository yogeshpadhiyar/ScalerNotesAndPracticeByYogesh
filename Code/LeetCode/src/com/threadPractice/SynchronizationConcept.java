package com.threadPractice;

/*
* Synchronizer : only for method & block (not for classes and variable)
* If diff thread try to access same java object it's give data inconsistancy problem.
* use for remove Data inconsistancy problem (Ex: same account access at same time from diff place)
* using synchronizer only one thread allow for execute so data inconsistancy problem resolve.
*
* Disad : it increase waiting time of thread for execution on method.
*
* Internally synchronization concept using lock. Every object in java has unique lock is object level lock
* When even we use synchronization that time only lock comes into pichter (Lock mechanizam takecare by JVM)
*
* Lock is done on object level while run syncro that's why not able to use other syncro method.
* Static method has class level lock.
* Expelnation : Each object has 2 area : syncro and non sycnro area. Non sycnro area able to access any time any no of thread
*                but syncro area access by only one thread at time.
* While any thread take lock into object and execute syncr method not any other thread able to other syncro method.
* while non sycnro method able to execute.
*
*
* IF very few line of code for synchronization that can be done by synchronization block.
* it's reduce waiting time and also improve performance of system
* 3 type of block :
*   1. current object lock : synchronized(this) {...}
*   2. particular object lock : synchronized(b) {...}
*   3. class level lock : synchronized(ClassName.class) {...}
*
* Note: lock in not available for primitive data types.
* Ex : int x=10;
*       synchromized(x){...}  // give error unexpected type
* */
public class SynchronizationConcept {

}

/*
* Inter thread communication
* wait, notify, notifyAll
*
*
* */