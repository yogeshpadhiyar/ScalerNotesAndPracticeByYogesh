package com.other;

import sun.nio.cs.ext.DoubleByte;

import java.util.*;

public class Twitter {
    HashMap<Integer, HashSet<Integer>> followTable = new HashMap<>();
    static class pair{
        int uid;
        int tid;
        public pair(int uid, int tid){
            this.uid=uid;
            this.tid=tid;
        }
    }
    LinkedList<pair> newsFeed = new LinkedList<>();
    public Twitter(){

    }

    public void postTweet(int userId, int tweetId) {
        newsFeed.add(new pair(userId, tweetId));
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> topFeed = new ArrayList<>();
        HashSet<Integer> followers = new HashSet<>();
        if(followTable.containsKey(userId))
            followers = followTable.get(userId);
        followers.add(userId);
        int i=0;
        int j = newsFeed.size()-1;
        while (i<10 && j>=0){
            if(followers.contains(newsFeed.get(j).uid)){
                topFeed.add(newsFeed.get(j).tid);
                i++;
            }
            j--;
        }
        return topFeed;
    }

    public void follow(int followerId, int followeeId) {
        HashSet<Integer> followers;
        if(followTable.containsKey(followerId)){
            followers = followTable.get(followerId);
        }else{
            followers = new HashSet<>();
        }
        followers.add(followeeId);
        followTable.put(followerId,followers);
    }

    public void unfollow(int followerId, int followeeId) {
        HashSet<Integer> followers;
        if(followTable.containsKey(followerId)){
            followers = followTable.get(followerId);
            followers.remove(followeeId);
            followTable.put(followerId,followers);
        }
    }
}
