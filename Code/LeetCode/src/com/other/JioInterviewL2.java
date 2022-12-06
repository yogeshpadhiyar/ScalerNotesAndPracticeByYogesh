package com.other;

public class JioInterviewL2 {

    static class JioInterViewL1{
        boolean interviewPassed = true;
        //default
        //para
    }

    JioInterViewL1 l1 = new JioInterViewL1();
}

interface DbConnection{
    void connectToDB();
    void getConnect();
    void takeBackConnect(Thread t);
    void createDBConnectionPoll(int noOfConnection);
}

abstract class Mysql implements  DbConnection{
    //
}