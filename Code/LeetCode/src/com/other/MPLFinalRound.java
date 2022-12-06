package com.other;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.ThreadPoolExecutor;

public class MPLFinalRound {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread();
            thread.start();
            Thread.sleep(3000);
        }
    }
}


interface TimeProvider {
    long getMillis();
}
class TestTimeProvider implements TimeProvider {
    public long getMillis(){
        return Calendar.getInstance().getTimeInMillis();
    }
}

class CachingDataStructure {

    private int maxSize;
    private TimeProvider timeProvider;
    private HashMap<String, String> data;
    private HashMap<String, Long> timer;
    private long leastTime;

    CachingDataStructure(TimeProvider timeProvider, int maxSize) {
        this.timeProvider = timeProvider;
        this.maxSize = maxSize;
        this.data = new HashMap<>();
        this.timer = new HashMap<>();
    }

    public void put(String key, String value, long timeToLeaveInMilliseconds) {
        if(timeToLeaveInMilliseconds<1 || key==null || value ==null){
            throw new IllegalArgumentException();
        }
        if(!data.isEmpty() && leastTime > timeProvider.getMillis()+timeToLeaveInMilliseconds){
            return;
        }
        if(data.containsKey(key)){
            data.remove(key);
            timer.remove(key);
        }
        data.put(key,value);
        timer.put(key, timeProvider.getMillis()+timeToLeaveInMilliseconds);
        leastTime=Math.min(leastTime,timeProvider.getMillis()+timeToLeaveInMilliseconds);
    }

    public Optional<String> get(String key) {
        return Optional.ofNullable(data.getOrDefault(key, null));
    }

    private void removeInvalidElement(){
        long currTime = 1;
        for(String k : timer.keySet()){
            if(timer.get(k)<currTime){
                timer.remove(k);
            }
        }
    }

    public int size() {
        return this.data.size();
    }

}




