package com.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class RecursionAll {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
//        int b = sc.nextInt();
//        int c = sc.nextInt();
//        String A = sc.nextLine();
        System.out.println(grayCode(a));

    }

    private static void printAll(int n) {
        if(n==1) {
            System.out.println(n);
            return;
        }
        System.out.println(n);
        printAll(n-1);
    }

    private static boolean isPalindrome(String str, int s, int e){
        if(s>=e) return true;
        if(str.charAt(s)==str.charAt(e)) {
            return isPalindrome(str, s+1, e-1);
        }else return false;
    }

    private static void directoryStructure(String root, ArrayList<String> answer){
        ArrayList<String> dirtList = getAllDirectories(root);

        for (int i = 0; i < dirtList.size(); i++) {
            answer.add(dirtList.get(i));
            directoryStructure(dirtList.get(i),answer);
        }
        ArrayList<String> fileList = getAllFiles(root);
        answer.addAll(fileList);
    }

    private static ArrayList<String> getAllDirectories(String folderName){return null;}
    private static ArrayList<String> getAllFiles(String folderName){return null;}

    private static int findAthFibonacci(int A){
        if(A<=1)    return A;
        return findAthFibonacci(A-1)+findAthFibonacci(A-2);
    }

    private static int findFactorial(int A){
        if(A==0) return 1;
        return findFactorial(A-1)*A;
    }

    private static void reverseString(String A){
        /*if(s>=e) {
            System.out.print(A.charAt(s));
            return;
        }
        System.out.print(A.charAt(e));
        reverseString(A,s+1,e-1);
        System.out.print(A.charAt(s));*/
        if(A.length()==0) return;
        reverseString(A.substring(1));
        System.out.println(A.charAt(0));
    }

    private static int sumAll(int A){
        if(A==0) return 0;
        return sumAll(A/10)+A%10;
    }

    private static int pow(int A, int B, int C){
        if(A==0) return 0;
        if(B==0) return 1;
        long y;
        if((B&1)==0){
            y = pow(A,B/2, C);
            y = (y*y)%C;
        }else{
            y = A%C;
            y = (y*pow(A,B-1, C)%C) %C;

        }
        return (int)((y+C)%C);
    }

    private static int isMagicMain(int A){
        if(A==1) return 1;
        if(A<10) return 0;
        else A = isMagic(A);
        return isMagicMain(A);

    }
    private static int isMagic(int A){
        if(A<10) return A;
        return isMagic(A/10)+A%10;
    }

    private static int kthSymbol(int A, int B){
        String str = makeString(A);
        return Integer.parseInt(str.charAt(B-1)+"");
    }
    private static String makeString(int A){
        if(A==1) return "0";
        String s = makeString(A-1);
        StringBuffer sb = new StringBuffer();
        sb.append(s);
        for (int i = 0; i <s.length(); i++) {
            sb.append((1 - Integer.parseInt(String.valueOf(s.charAt(i))))+"" );
        }
        return sb.toString();
    }

    private static ArrayList<Integer> grayCode(int a) {
        if(a==1) return new ArrayList<>(Arrays.asList(0,1));
        ArrayList<Integer> arr = grayCode(a-1);
        int lastElm = (1<<(a-1));
        int  n=arr.size();
        for (int i = n-1; i >=0 ; i--) {
            arr.add(lastElm + arr.get(i));
        }
        return arr;
    }
}

