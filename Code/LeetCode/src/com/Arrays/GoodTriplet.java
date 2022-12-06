package com.Arrays;

import java.util.ArrayList;
import java.util.Scanner;

public class GoodTriplet {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> A = new ArrayList<>();
//        ArrayList<Integer> B = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            A.add(sc.nextInt());
        }
        /*for (int i = 0; i < n; i++) {
            B.add(sc.nextInt());
        }*/
//        System.out.println(goodTriplet(A,B));
        System.out.println(maxPositiveSubArray(A));
    }

    private static ArrayList<Integer> maxPositiveSubArray(ArrayList<Integer> A){
        int index =-1,length=0,max=0;
        for (int i = 0; i < A.size(); i++) {
            if(A.get(i)<0){
                if(max<length){
                    index = i-length;
                    max = length;
                }
                length=0;
            }else{
                length++;
            }
        }
        if(max<length){
            index = A.size()-length;
            max = length;
        }
        ArrayList<Integer> r = new ArrayList<>();
        for (int i = index; i < index+max; i++) {
            r.add(A.get(i));
        }
        return r;
    }
    private static int goodTriplet(ArrayList<Integer> A, ArrayList<Integer> B) {
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < A.size()-1; i++) {
            //lmin = find left min value
            int leftMin = Integer.MAX_VALUE;
            for (int j = i-1; j >=0; j--) {
                if(A.get(j)<A.get(i))
                    leftMin = Math.min(B.get(j),leftMin);
            }
            //rmin = find from right side min value
            int rightMin = Integer.MAX_VALUE;
            for (int j = i+1; j <A.size(); j++) {
                if(A.get(j)>A.get(i))
                    rightMin = Math.min(rightMin,B.get(j));
            }
            if(rightMin !=Integer.MAX_VALUE && leftMin !=Integer.MAX_VALUE)
                min = Math.min(leftMin+rightMin+B.get(i), min);
        }
        return (min!=Integer.MAX_VALUE)?min:-1;
    }
}
