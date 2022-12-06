package com.StringProblem;

import java.util.*;
import java.util.stream.Collectors;

public class StringAll {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        /*int n = sc.nextInt();
        int[] arr = new int[2];
        arr[0] =sc.nextInt();
        arr[1] =sc.nextInt();
        */
        int n = sc.nextInt();
        String temp = sc.nextLine();
//        char[] a= str.toCharArray();
        ArrayList<String> A = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            A.add(sc.nextLine());
        }
//        int s = sc.nextInt();
//        int e = sc.nextInt();
//        String t = sc.nextLine();
        System.out.println(longestCommonPrefix(A));
    }

    public static int[] executeInstructions(int n, int[] startPos, String s) {
        int[] res = new int[s.length()];
        for (int l = 0; l < s.length(); l++) {
            int ans = 0;
            int i = startPos[0];
            int j = startPos[1];
            for (int k = l; k < s.length(); k++) {
                if (s.charAt(k) == 'R') {
                    if (j + 1 < n) {
                        ans++;
                        j++;
                    }else break;
                } else if (s.charAt(k) == 'L') {
                    if (j - 1 >=0) {
                        ans++;
                        j--;
                    }else break;
                } else if (s.charAt(k) == 'U') {
                    if (i - 1 >=0) {
                        ans++;
                        i--;
                    }else break;
                } else if (s.charAt(k) == 'D') {
                    if (i + 1 < n) {
                        ans++;
                        i++;
                    }else break;
                }
            }
            res[l]=ans;
        }
        return res;
    }

    public static int minAddToMakeValid(String s) {
        int ans=0;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)=='('){
                stack.push('(');
            }
            else {
                if(!stack.isEmpty()){
                    if(stack.peek()!='('){
                        ans++;
                    }
                    stack.pop();
                }else ans++;
            }
        }
        return ans+stack.size();
    }

    public static int minSwaps(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)=='['){
                stack.push(s.charAt(i));
            }else{
                if(!stack.isEmpty() && stack.peek()=='['){
                    stack.pop();
                }else{
                    stack.push(s.charAt(i));
                }
            }
        }
        if(stack.isEmpty()) return 0;
        return ((stack.size()/2 & 1)==0)?stack.size()/4: (stack.size()/2+1)/2;
    }

    public static int minSteps(String s, String t) {
        HashMap<Character, Integer> shm = new HashMap<>();
        HashMap<Character, Integer> thm = new HashMap<>();
        int n = t.length();
        for (int i = 0; i <n ; i++) {
            if(!shm.containsKey(s.charAt(i))){
                shm.put(s.charAt(i),1);
            }else shm.replace(s.charAt(i), shm.get(s.charAt(i))+1);

            if(!thm.containsKey(t.charAt(i))){
                thm.put(t.charAt(i),1);
            }else thm.replace(t.charAt(i), thm.get(t.charAt(i))+1);
        }
        int ans =0;

        for (char c : thm.keySet()) {
            if (!shm.containsKey(c)) {
                ans += thm.get(c);
            } else {
                int CTVal = thm.get(c);
                int CSVal = shm.get(c);
                if (CTVal - CSVal > 0)
                    ans += CTVal - CSVal;
            }
        }
        return ans;
    }

    public static String toggleChar(String s){
        int mask = 1<<5;
        char[] charArray = s.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] ^= mask;
            sb.append(charArray[i]);
        }
        return sb.toString();
    }

    public static String sortString(String s){
        int[] occur = new int[26];
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            occur[s.charAt(i)-'a']++;
        }
        for (int i = 0; i < occur.length; i++) {
            StringBuffer temp = new StringBuffer();
            for (int j = 0; j < occur[i]; j++) {
                temp.append((char) (i+'a'));
            }
            sb.append(temp);
        }
        return sb.toString();
    }

    public String reverseWords(String s) {
        StringBuilder ans = new StringBuilder();
        String[] sp = s.split(" ");
        for(int i=0; i<sp.length; i++){
            ans.append(reverseWord(sp[i]));
            if(i!= sp.length-1) ans.append(" ");
        }
        return ans.toString();
    }

    public static String reverseWord(String str){
        char[] strChar = str.toCharArray();
        int i=0,j=str.length()-1;
        while (i <j) {
            char temp = strChar[i];
            strChar[i] = strChar[j];
            strChar[j]=temp;
            i++;j--;
        }
        StringBuilder res= new StringBuilder();
        for (char c : strChar) {
            res.append(c);
        }
        return res.toString();
    }

    public static String reverseString(String s){
        StringBuffer buf = new StringBuffer();
        StringBuilder res = new StringBuilder();

        for (int i = s.length()-1; i >=0; i--) {
            char c = s.charAt(i);
            if(c!=' ') buf.append(c);
            else createString(res,buf);
        }
        createString(res,buf);
        return res.toString();
    }

    private static void createString(StringBuilder res, StringBuffer buf) {
        int l = buf.length()-1;
        while (l>=0){
            if(l== buf.length()-1 && res.length()>0) res.append(' ');
            res.append(buf.charAt(l));
            l--;
        }
        buf.setLength(0);
    }

    private static ArrayList<Character> to_lower(ArrayList<Character> A) {
        for (int i = 0; i < A.size(); i++) {
            if(A.get(i)>='A' && A.get(i)<='Z')  A.set(i,(char)(A.get(i)+('a'-'A')));
        }
        return A;
    }

    private static int inAlpha(ArrayList<Character> A){
        for (int i = 0; i < A.size(); i++) {
            if(!(A.get(i)>='A' && A.get(i)<='Z') ||
                    !(A.get(i)>='a' && A.get(i)<='z')||!(A.get(i)>='0' && A.get(i)<='9'))
                return 0;
        }
        return 1;
    }

    private static String longestPalindrome(String A) {
        int max=0;
        String ans="";
        for (int i = 0; i < A.length(); i++) {
            String maxOddL = findPalindromeFromCenter(A,i,i);
            if(max<maxOddL.length()){
                max = maxOddL.length();
                ans = maxOddL;
            }
            String maxEvenL = findPalindromeFromCenter(A,i,i+1);
            if(max<maxEvenL.length()){
                max = maxEvenL.length();
                ans = maxEvenL;
            }
        }
        return ans;
    }

    private static String findPalindromeFromCenter(String A, int i, int i1) {
        int s=i,e=i1;
        while (s>=0 && e<=A.length()-1 && A.charAt(s)==A.charAt(e)){
            s--;
            e++;
        }
        return A.substring(s+1,e);
    }

    private static int changeChar(String A, int B){
        HashMap<Character,Integer> hm = new HashMap<>();
        for (int i = 0; i < A.length(); i++) {
            if(hm.containsKey(A.charAt(i)))    hm.put(A.charAt(i), hm.get(A.charAt(i))+1);
            else hm.put(A.charAt(i),1);
        }

        Collection<Integer> vals = hm.values();
        ArrayList<Integer> values = new ArrayList<>(vals);
        Collections.sort(values);
        for (int i = 0; i < values.size();) {
            if(B-values.get(i)>=0){
                B-=values.get(i);
                values.remove(values.get(i));
            }else{
                break;
            }
        }
        return values.size();
    }

    private static  String scalerHM4(String A){
        ArrayList<Character> vowel = new ArrayList<>(Arrays.asList( 'a','e','i','o','u'));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < A.length(); i++) {
            if(vowel.contains(A.charAt(i))){
                sb.append('#');
            } else if(A.charAt(i)>='a' && A.charAt(i)<='z') sb.append(A.charAt(i));
        }
        return sb.append(sb).toString();
    }

    private static String longestCommonPrefix(ArrayList<String> A) {
        int i=0;
        while (i<A.get(0).length() && i<A.get(1).length() && A.get(0).charAt(i)==A.get(1).charAt(i)){
            i++;
        }
        for (int j = 1; j < A.size()-1; j++) {
            int t=0;
            while (t<A.get(j).length() && t<A.get(j+1).length()
                    &&A.get(j).charAt(t)==A.get(j+1).charAt(t) && t<i){
                t++;
            }
            i=t;
        }
        return A.get(0).substring(0,i);
    }
}
