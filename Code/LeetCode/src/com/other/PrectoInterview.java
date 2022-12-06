package com.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class PrectoInterview {
    public static void main(String[] args) {
        String str = "abcabcabc";
        System.out.println(findDuplicate(str));
    }

    public static String findDuplicate(String str){
        HashMap<Character,Integer> set = new HashMap<>();
        char[] chArray = str.toCharArray();
        for (char c: chArray)   set.put(c, set.getOrDefault(c,0)+1);
        StringBuffer sb = new StringBuffer();
        for(Map.Entry<Character,Integer> c: set.entrySet()) {
            sb.append(c.getKey());
            sb.append(c.getValue());
        }
        return sb.toString();
    }
}
