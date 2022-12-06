package com.binarySearch;

import java.util.*;

import static com.Math.AdvanceMathAll.gcd;

public class BinarySearchClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
//        int m = sc.nextInt();
//        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(180, 181, 182, 183, 184, 187, 188, 189, 191, 192, 193, 194, 195, 196, 201, 202, 203, 204, 3, 4, 5, 6, 7, 8, 9, 10, 14, 16, 17, 18, 19, 23, 26, 27, 28, 29, 32, 33, 36, 37, 38, 39, 41, 42, 43, 45, 48, 51, 52, 53, 54, 56, 62, 63, 64, 67, 69, 72, 73, 75, 77, 78, 79, 83, 85, 87, 90, 91, 92, 93, 96, 98, 99, 101, 102, 104, 105, 106, 107, 108, 109, 111, 113, 115, 116, 118, 119, 120, 122, 123, 124, 126, 127, 129, 130, 135, 137, 138, 139, 143, 144, 145, 147, 149, 152, 155, 156, 160, 162, 163, 164, 166, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177));
        ArrayList<Integer> A = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            A.add(sc.nextInt());
        }
//        int a = sc.nextInt();
//        int b= sc.nextInt();
        System.out.println(findPeakElm(A));
    }

    //peak elm is A < B > C. So B is peak elm.
    //for corner case compare only one elm.
    public static int findPeakElm(ArrayList<Integer> A){
        int l=0,h=A.size()-1;
        int ans = 0;
        if(A.size()==1) return A.get(0);
        if(A.get(0)>A.get(1)) return A.get(0);
        if(A.get(h-1)<A.get(h)) return A.get(h);
        while (l<=h){
            int m =(l+h)/2;
            if(A.get(m-1)<=A.get(m) && A.get(m+1)<= A.get(m)){
                ans = A.get(m);
                break;
            }else if(A.get(m-1)<A.get(m) && A.get(m)<A.get(m+1)){
                l=m+1;
            }else{
                h=m-1;
            }
        }
        return ans;
    }

    public static int foodPacketDistribution(ArrayList<Integer> A, int B){
        int l=1,h=A.get(0);
        long total=0;
        for (Integer e:A){
            h = Math.min(h,e);
            total+=e;
        }
        if(total<B) return 0;
        int ans=0;
        while (l<=h){
            int m = (l+h)/2;
            if(isAbleToFeedPeople(A,B,m)){
                ans = m;
                l =m+1;
            }else   h=m-1;
        }
        return ans;
    }
    private static boolean isAbleToFeedPeople(ArrayList<Integer> A, int B, int p) {
        int req=0;
        for (Integer e : A){
            req += Math.floorDiv(e,p);
        }
        return req>=B;
    }

    public static int books(ArrayList<Integer> A, int B){
        if(A.size()<B)  return -1;
        int l=A.get(0),h=0;
        for (Integer e : A){
            l = Math.max(l,e);
            h += e;
        }
        int ans = 0;
        while (l<=h){
            int m =(l+h)/2;
            if(isAbleToFinishInTime(A,B,m)){
                ans = m;
                h=m-1;
            }else {
                l=m+1;
            }
        }
        return ans;
    }

    public static int paint(ArrayList<Integer> C, int A, int B){
        int M = 10000003;
        long b = B;
        ArrayList<Long> board = new ArrayList<>();
        long max=b*C.get(0);
        long total = 0;
        for(Integer e : C){
            max = Math.max(max,e*b);
            total += e*b;
            board.add(e*b);
        }
        long l=max,h=total;
        long ans =0;
        while (l<=h){
            long m = (l+h)/2;
            if(isAbleToFinishPaintInTime(board,A,m)){
                ans = m%M;
                h=m-1;
            }else{
                l=m+1;
            }
        }
        ans = (ans+M)%M;
        return (int)ans;
    }
    public static boolean isAbleToFinishPaintInTime(ArrayList<Long> A, int W, long T){
        int k=0;
        long sum=0;
        for (Long aLong : A) {
            sum += aLong;
            if (sum > T) {
                k++;
                sum = aLong;
                if (k == W) return false;
            }
        }
        return true;
    }

    public static int aggressiveCows(ArrayList<Integer> A, int B){
        Collections.sort(A);
        int n = A.size(),ans=0;
        int l=A.get(1)-A.get(0);
        int h=A.get(n-1)-A.get(0);
        for (int i = 2; i < A.size(); i++) {
            l = Math.min(l,A.get(i)-A.get(i-1));
        }

        while (l<=h){
            int m =(l+h)/2;
            if(isAbleToArrangeCowsInSpace(A,B,m)){
                ans = m;
                l=m+1;
            }else{
                h=m-1;
            }
        }
        return ans;
    }
    private static boolean isAbleToArrangeCowsInSpace(ArrayList<Integer> A, int B, int D) {
        int cows=1,last_pos=A.get(0);
        for (int i = 1; i < A.size(); i++) {
            if(A.get(i)-last_pos>=D){
                cows++;
                last_pos = A.get(i);
                if(cows==B) return true;
            }
        }
        return false;
    }

    public static int minTimeToCompleteWork(ArrayList<Integer> A, int W){
        int max=A.get(0);
        int total = 0;
        for(Integer e : A){
            max = Math.max(max,e);
            total += e;
        }
        int l=max,h=total;
        int ans =0;
        while (l<=h){
            int m = (l+h)/2;
            if(isAbleToFinishInTime(A,W,m)){
                ans = m;
                h=m-1;
            }else{
                l=m+1;
            }
        }
        return ans;
    }
    public static boolean isAbleToFinishInTime(ArrayList<Integer> A, int W, int T){
        int k=0;
        int sum=0;
        for (Integer integer : A) {
            sum += integer;
            if (sum > T) {
                sum = integer;
                k++;
                if (k == W) return false;
            }
        }
        return true;
    }

    public static int findMedian(ArrayList<ArrayList<Integer>> A){
        int n = A.size();
        int m = A.get(0).size();
        int M = (1+n*m)/2;
        int l= A.get(0).get(0);
        for (ArrayList<Integer> integerArrayList : A) {
            l = Math.min(l, integerArrayList.get(0));
        }
        int h= A.get(0).get(m-1);
        for (ArrayList<Integer> integers : A) {
            h = Math.max(h, integers.get(m - 1));
        }
        int ans=0;
        while (l<=h){
            int mid = (l+h)/2;
            int lessThenMid = giveLessThenMidFromMatrix(A,mid);
            if(lessThenMid>=M){
                ans=mid;
                h=mid-1;
            }else l=mid+1;
        }
        return ans;
    }

    private static int giveLessThenMidFromMatrix(ArrayList<ArrayList<Integer>> A, int mid) {
        int c=0;
        for(ArrayList<Integer> a : A){
            int l=0,h=a.size()-1;
            int ans=0;
            while (l<=h){
                int m=(l+h)/2;
                if(a.get(m)<=mid){
//                    ans = m;
                    l=m+1;
                }
                else    h=m-1;
            }
            c += l;
        }
        return c;
    }

    public static int kthSmallest(final List<Integer> A, int B) {
        B = B-1;
        int l =A.get(0),h=A.get(0);
        for (Integer e:A){
            l = Math.min(l,e);
            h= Math.max(h,e);
        }
        int ans=0;
        while (l<=h){
            int m = (l+h)/2;
            int lessNo = lessThanGivenNo(A,m);
            if(lessNo>B){
                h=m-1;
            }else{
                ans= m;
                l=m+1;
            }
        }
        return ans;
    }
    private static int lessThanGivenNo(List<Integer> A, int m) {
        int c=0;
        for (Integer e:A){
            if(e<m) c++;
        }
        return c;
    }

    public static int magicalNumberAth(int A, int B, int C){
        int M = 1000000007;
        long ans = 0;
        long lcm = ((long) B *C) / gcd(B,C);
        long l=1;
        long h = (long) Math.min(B, C) *A;
        while (l<=h){
            long m = l+(h-l)/2;
            long magicNo = ( Math.floorDiv(m,B) + Math.floorDiv(m,C) ) - Math.floorDiv(m,lcm);
            if(magicNo>=A){
                ans = m%M;
                h=m-1;
            }else{
                l = m+1;
            }
        }
        ans = (ans+M)%M;
        return (int)ans;
    }

    public static int specialInteger(ArrayList<Integer> A, int B){
        int l=0,h=A.size();
        int ans=0;
        while (l<=h){
            int m = (l+h)/2;
            long max = subArrayMaxSumWithKLength(A,m);
            if(max<=B){
                ans =m;
                l=m+1;
            }else{
                h=m-1;
            }
        }
        return ans;
    }
    public static long subArrayMaxSumWithKLength(ArrayList<Integer> A, int k){
        long sum=0;
        for (int i = 0; i <k; i++) {
            sum += A.get(i);
        }
        long max = sum;
        for (int i = k; i < A.size(); i++) {
            sum = sum -A.get(i-k)+A.get(i);
            max = Math.max(max,sum);
        }
        return max;
    }

    public static int sqrt(int A) {
        long ans =0;
        long l=1,h=A;
        while (l<=h){
            long m = l+(h-l)/2;
            if((m*m)<=A){
                ans=m;
                l=m+1;
            }else{
                h=m-1;
            }
        }
        return (int)ans;
    }

    public static int searchMatrix(ArrayList<ArrayList<Integer>> A, int B) {
        ArrayList<Integer> lastCol = new ArrayList<>();
        int n = A.size();
        int m = A.get(0).size();
        if(n==1){
            int ansIndex = binarySearch(A.get(0),B);
            if(ansIndex<m && A.get(0).get(ansIndex)==B) return 1;
            else return 0;
        }
        for (int i = 0; i < A.size(); i++) {
            lastCol.add(A.get(i).get(m-1));
        }
        int index = binarySearch(lastCol,B);
        if(index>=n) return 0;
        else if(lastCol.get(index)==B)   return 1;
        else{
            int ansIndex = binarySearch(A.get(index),B);
            if(ansIndex<m && A.get(index).get(ansIndex)==B) return 1;
            else return 0;
        }
    }

    public static ArrayList<Integer> searchRange(final List<Integer> A, int B) {
        int l=0,h=A.size()-1;
        int s=-1,e=-1;
        while (l<=h){
            int m = (l+h)/2;
            if(A.get(m)==B){
                s=m;
                h=m-1;
            }else if(A.get(m)<B)    l=m+1;
            else    h=m-1;
        }
        l=0;
        h=A.size()-1;
        while (l<=h){
            int m = (l+h)/2;
            if(A.get(m)==B){
                e=m;
                l=m+1;
            }else if(A.get(m)<B)    l=m+1;
            else    h=m-1;
        }
        return new ArrayList<>(Arrays.asList(s,e));
    }

    private static int binarySearch(ArrayList<Integer> A, int B) {
        int l=0,h=A.size()-1;
        int ans =0;
        while (l<=h){
            int m = (l+h)/2;
            if(A.get(m)==B){
                return m;
            }else if(A.get(m)<B){
                //go to  right
                ans = m+1;
                l = m+1;
            }else{
                h = m-1;
            }
        }
        return ans;
    }

    private static int ceilingInSortedArray(int A, ArrayList<Integer> B, int C) {
        int l=0,h=B.size()-1;
        int ans =0;
        while (l<=h){
            int m = (l+h)/2;
            if(B.get(m)==C){
                return B.get(m);
            }else if(B.get(m)<C){
                //go to  right
                ans = m+1;
                l = m+1;
            }else{
                h = m-1;
            }
        }
        if(ans>A-1)  return -1;
        return B.get(ans);
    }

    public static int singleElmInSortedArr(ArrayList<Integer> A) {
        int l=0,h= A.size()-1;
        if(h==0) return A.get(0);
        if(!Objects.equals(A.get(0), A.get(1)))  return A.get(0);
        if(!Objects.equals(A.get(h), A.get(h - 1))) return A.get(h);
        while (l<=h){
            int m = (l+h)/2;
            if((!Objects.equals(A.get(m), A.get(m - 1))) && (!Objects.equals(A.get(m), A.get(m + 1)))){
                return A.get(m);
            }
            if( ((m%2)==1 && Objects.equals(A.get(m), A.get(m - 1))) ||
                    ((m%2)==0 && Objects.equals(A.get(m), A.get(m + 1))) ){

                l=m+1;
            }else{
                h=m-1;
            }
        }
        return -1;
    }

    public static int rotatedSortedArraySearch(final List<Integer> A, int B){
        int k=A.size()-1;
        if(k==0){
            if(A.get(0).equals(B))  return A.get(0);
            else return -1;
        }
        //Tech: Using min elm check.
        int l=0,h=A.size()-1;
        while (l<=h){
            int m = (l+h)/2;
            if(m>l && A.get(m-1)>A.get(m)){
                k=m;
                break;
            }else if(m<h && A.get(m+1)<A.get(m)) {
                k=m+1;
                break;
            }else if(A.get(h)<A.get(m)){
                //goto right
                l = m+1;
            }else{
                h=m-1;
            }
        }
        l=0;
        h= k;
        int ans =binarySearchInRangeASC(A,B,l,h);
        if(ans==-1){
            return binarySearchInRangeASC(A,B,k,A.size()-1);
        }
        return ans;
    }

    public static int searchInBitonicArr(ArrayList<Integer> A, int B) {
        int k=A.size()-1;
        int l=0,h=A.size()-1;
        while (l<=h){
            int m = (l+h)/2;
            if(A.get(m-1)<A.get(m) && A.get(m+1)<A.get(m)){
                k=m;
                break;
            }else if(A.get(m-1)<A.get(m)){
                l = m+1;
            }else{
                h=m-1;
            }
        }
        l=0;
        h=k;
        int ans =binarySearchInRangeASC(A,B,l,h);
        if(ans==-1){
            return binarySearchInRangeDSC(A,B,k+1,A.size()-1);
        }
        return ans;
    }

    private static int binarySearchInRangeASC(List<Integer> A, int B, int l, int h) {
        while (l<=h){
            int m =(l+h)/2;
            if(A.get(m).equals(B))  return m;
            else if(A.get(m)<B) l=m+1;
            else h=m-1;
        }
        return -1;
    }
    private static int binarySearchInRangeDSC(ArrayList<Integer> A, int B, int l, int h) {
        while (l<=h){
            int m =(l+h)/2;
            if(A.get(m).equals(B))  return m;
            else if(A.get(m)>B) l=m+1;
            else h=m-1;
        }
        return -1;
    }
}
