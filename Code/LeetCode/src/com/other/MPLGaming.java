package com.other;

public class MPLGaming {
    public static void main(String[] args) {

    }

    public int waterTrap(int[] arr){
        int[] leftMax = new int[arr.length];
        int[] rightMax = new int[arr.length];

        int leftMaxIndex=0, leftMaxVal=arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(leftMaxVal<arr[i]){
                leftMaxVal = arr[i];
                leftMaxIndex = i;
            }
            leftMax[i]= leftMaxIndex;
        }
        //Same as right reverse
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            int leftVal = leftMax[i];
            int rightVal = rightMax[i];
            int min = Math.min(arr[ leftVal],arr[rightVal]);
            int distance = rightVal-leftVal;
            ans = Math.max( ans, distance*min);
        }
        return ans;
    }


}
