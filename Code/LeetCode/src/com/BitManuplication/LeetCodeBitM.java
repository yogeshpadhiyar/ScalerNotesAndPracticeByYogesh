package com.BitManuplication;

import javafx.collections.transformation.SortedList;

import java.util.*;

public class LeetCodeBitM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        String allowed = sc.nextLine();
        int n = sc.nextInt();
        int[] arr = new int[n];
//        int m = sc.nextInt();
        /*String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = sc.nextLine();
        }*/
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
//        System.out.println(countConsistentStrings(allowed,words));
//        System.out.println(hammingDistance(n,m));
        int[] res = sortByBits(arr);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i]+" ");
        }
        System.out.println();
    }

    private static int countConsistentStrings(String allowed, String[] words) {
        ArrayList<Character> allowedChar = new ArrayList<>();
        for (int i = 0; i < allowed.length(); i++) {
            allowedChar.add(allowed.charAt(i));
        }
        int c=0;
        for (String word : words) {
            boolean flag = true;
            for (int j = 0; j < word.length(); j++) {
                if (!allowedChar.contains(word.charAt(j))) {
                    flag = false;
                    break;
                }
            }
            if (flag) c++;
        }
        return c;
    }

    private static int hammingDistance(int x, int y){
        int diff =0;
        while (x!=0 || y !=0){
            if((x&1)!=(y&1)){
                diff++;
            }
            x =x >>1;
            y =y >>1;
        }
        return diff;
    }

    private static int[] countBits(int n){
        int[] cb = new int[n+1];
        if(n==0){
            cb[0]=0;
            return cb;
        }
        if(n==1){
            cb[0]=0;
            cb[1]=1;
            return cb;
        }
        cb[0]=0;
        cb[1]=1;
        int i=2,j=1;
        while (i<=n){
            if(i==Math.pow(2,j+1)) j++;
            if((Math.pow(2,j+1)-Math.pow(2,j))/2 + Math.pow(2,j) <= i){
                cb[i] = cb[(int) (i- Math.pow(2,j-1))] +1;
            }else{
                cb[i] = cb[(int) (i- Math.pow(2,j-1))];
            }
            i++;
        }
        return cb;
    }

    private static int[] countBitsSimple(int n){
        int cb[] = new int[n+1];
        cb[0]=0;
        for (int i = 0; i <=n; i++) {
            cb[i] = cb[i/2] + i%2;
        }
        return cb;
    }

    private static int[] sortByBits(int[] arr){
        HashMap<Integer, ArrayList<Integer>> list = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int ones = noOfOnesBit(arr[i]);
            if(list.containsKey(ones)){
                list.get(ones).add(arr[i]);
            }else{
                list.put(ones, new ArrayList<>(Arrays.asList(arr[i])));
            }
        }
        int[] result = new int[arr.length];
        ArrayList<Integer> r = new ArrayList<>();
        for (ArrayList<Integer> ele : list.values()) {
            ele.sort(Integer::compareTo);
            r.addAll(ele);
        }
        for (int i = 0; i < r.size(); i++) {
            result[i]=r.get(i);
        }
        return result;
    }

    private static int noOfOnesBit(int n){
        int c=0;
        while (n!=0){
            if((n&1)==1)
                c++;
            n = n>>1;
        }
        return c;
    }
}
