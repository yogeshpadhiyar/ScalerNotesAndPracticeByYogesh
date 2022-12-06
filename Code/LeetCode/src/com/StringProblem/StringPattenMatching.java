package com.StringProblem;

import java.util.*;

public class StringPattenMatching {
    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        String A = sc.nextLine();
        String B = sc.nextLine();
        System.out.println(minWindow(A,B));

    }

    private static int cyclicPermutation(String A, String B) {
        String lpsStr = A+"$"+B+B;
        int m = A.length(),ans=0;
        int[] lps  = new int[lpsStr.length()];
        lps[0]=0;
        for (int i = 1; i < lpsStr.length()-1; i++) {
            int x = lps[i-1];
            while(lpsStr.charAt(i)!=lpsStr.charAt(x)){
                if(x==0){
                    x=-1;
                    break;
                }
                x = lps[x-1];
            }
            lps[i]=x+1;
            if(lps[i]==m) ans++;
        }
        return ans;
    }

    public static String smallestPrefix(String A, String B) {
        int p1=1,p2=0;
        int n=A.length();
        StringBuffer s = new StringBuffer();
        s.append(A.charAt(0));
        while (p1<n){
           if(A.charAt(p1)<B.charAt(p2)){
               s.append(A.charAt(p1));
               p1++;
           }else    break;
        }
        s.append(B.charAt(p2));
        return s.toString();
    }

    public static int countA(String A){
        int c = 0;
        for (int i = 0; i < A.length(); i++) {
            if(A.charAt(i)=='a')    c++;
        }
        return (c*(c+1))/2;
    }

    public static int makeStringPalindrome(String A) {
        int n = A.length();
        StringBuilder AB = new StringBuilder(A);
        A = AB+"$"+AB.reverse();
        int[] lps = new int[A.length()];
        lps[0]=0;
        for (int i = 1; i < A.length(); i++) {
            int x = lps[i-1];
            while(A.charAt(i)!=A.charAt(x)){
                if(x==0){
                    x=-1;
                    break;
                }
                x = lps[x-1];
            }
            lps[i]=x+1;
        }
        return n-lps[2*n];
    }

    public static String closestPalindrome(String A){
        int c=0,n=A.length();
        int p1=0,p2=A.length()-1;
        while (p1<p2){
            if(A.charAt(p1)!=A.charAt(p2)){
                c++;
                if(c==2)    return "NO";
            }
            p1++;
            p2--;
        }
        return (c==0)? (n%2==0)?"NO":"YES":"YES";
    }

    public static int boringString(String A){
        char[] a = A.toCharArray();
        Arrays.sort(a);
        int n = a.length;
        int half = (n%2==0)?n/2:n/2+1;
        for(int i =0; i<n/2; i++){
            if(a[i]==a[i+half] ||a[i]==a[i+half]-1 || a[i]==a[i+half]+1){
                return 0;
            }
        }
        return 1;
    }

    public static int permutationOfAinB(String A, String B){
        int w = A.length(),ans=0;
        HashMap<Character, Integer> hm = new HashMap<>();
        HashMap<Character, Integer> hmA = new HashMap<>();
        for (int i = 0; i < w; i++) {
            hm.put(B.charAt(i),hm.getOrDefault(B.charAt(i),0)+1);
            hmA.put(A.charAt(i),hmA.getOrDefault(A.charAt(i),0)+1);
        }
        if(isSameHashMap(hmA,hm))   ans++;
        for (int i = w; i < B.length(); i++) {
            if(B.charAt(i)==B.charAt(i-w)){
                if(isSameHashMap(hmA,hm))   ans++;
            }else{
                hm.put(B.charAt(i),hm.getOrDefault(B.charAt(i),0)+1);
                if(hm.get(B.charAt(i-w))-1==0){
                    hm.remove(B.charAt(i-w));
                }else{
                    hm.put(B.charAt(i-w),hm.get(B.charAt(i-w))-1);
                }
                if(isSameHashMap(hmA,hm)) ans++;
            }
        }
        return ans;
    }
    private static boolean isSameHashMap(HashMap<Character, Integer> hmA, HashMap<Character, Integer> hm) {
        if(hmA.size()==hm.size()){
            for (Map.Entry<Character,Integer> e : hmA.entrySet()){
                if(hm.containsKey(e.getKey())){
                    if(!Objects.equals(hm.get(e.getKey()), e.getValue()))   return false;
                }else return false;
            }
        }else
            return false;

        return true;
    }

    public static int periodOfString(String A){
        int[] lps = new int[A.length()];
        lps[0]=0;
        for (int i = 1; i < A.length(); i++) {
            int x = lps[i-1];
            while(A.charAt(i)!=A.charAt(x)){
                if(x==0){
                    x=-1;
                    break;
                }
                x = lps[x-1];
            }
            lps[i]=x+1;
        }
        int ans=0;
        for (int i = lps.length-2; i>=0; i--) {
            if(lps[i+1]==0){
                ans=i+2;
                break;
            }
            if(lps[i]==0){
                ans=i+1;
                break;
            }
            if(lps[i]!=lps[i+1]-1){
                ans = i+1;
                break;
            }
        }
        return ans;
    }

    public static String minWindow(String A, String B) {
        int start=0,length=0,total=0;
        HashMap<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < B.length(); i++) {
            hm.put(B.charAt(i),hm.getOrDefault(B.charAt(i),0)+1);
        }
        for (int h = 0,t=0; t < A.length(); t++) {
            if(hm.containsKey(A.charAt(t))){
                hm.put(A.charAt(t),hm.get(A.charAt(t))-1);

                if(hm.get(A.charAt(t))>=0){
                    total++;
                }
                if(total==B.length()){
                    while (!hm.containsKey(A.charAt(h)) || hm.get(A.charAt(h))<0){
                        if(hm.containsKey(A.charAt(h))){
                            hm.put(A.charAt(h),hm.get(A.charAt(h))+1);
                        }
                        h++;
                    }
                    if(length==0||t-h+1<length){
                        length = t-h+1;
                        start=h;
                    }
                }
            }
        }
        return A.substring(start,start+length);
    }
}
