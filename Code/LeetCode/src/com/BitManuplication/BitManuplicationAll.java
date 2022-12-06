package com.BitManuplication;

import java.util.*;

public class BitManuplicationAll {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int B = sc.nextInt();
//        ArrayList<Integer> A = new ArrayList<>();
        /*int[] a = new int[N];
//        int[][] que = new int[q][2];
        for (int i = 0; i < N; i++) {
//            A.add(sc.nextInt());
            a[i]=sc.nextInt();
        }*/


//        int m = sc.nextInt();
//        int N = sc.nextInt();
//        String A = sc.nextLine();
//        String B = sc.nextLine();
//        long a = sc.nextLong();
        System.out.println(smallestXor(N,B));
    }

    public static int compressBits(ArrayList<Integer> A){
        for (int i = 0; i < A.size(); i++) {
            for (int j = i+1; j < A.size(); j++) {
                int temp = A.get(i);
                A.set(i, A.get(i)&A.get(j));
                A.set(j, temp|A.get(j));
            }
        }
        int xorSum =0;
        for (int i = 0; i < A.size(); i++) {
            xorSum ^=A.get(i);
        }
        return xorSum;
    }

    private static int maximumSatisfaction(ArrayList<Integer> A){
        for (int i = 31; i >=0; i--) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int j = 0; j < A.size(); j++) {
                if(checkBit(A.get(j),i)){
                    temp.add(A.get(j));
                }
            }
            if(temp.size()>4){
                A = temp;
            }else if(temp.size()==4){
                A= temp;
                break;
            }
        }
        return A.get(0) & A.get(1) &A.get(2) & A.get(3);
    }

    private static ArrayList<Integer> singleNumberIII(ArrayList<Integer> A){
        ArrayList<Integer> r = new ArrayList<>();
        int aXORb=0;
        for (int i : A) aXORb ^=i;

        int mask = (aXORb & (aXORb-1) ) ^ aXORb;
        int a=0 ,b=0;
        for (int i : A) {
            if( (i & mask) !=0 )
                a ^=i;
            else
                b ^=i;
        }
        r.add(Math.min(a,b));
        r.add(Math.max(a,b));
        return r;

        /*ArrayList<Integer> elmHold = new ArrayList<>();
        for (int i = 0; i < A.size(); i++) {
            if(!elmHold.contains(A.get(i)))
                elmHold.add(A.get(i));
            else
                elmHold.remove(A.get(i));
        }
        elmHold.sort(Integer::compareTo);
        return elmHold;*/
    }

    private static int findComplement(int n){
        int i=0,j=0;
        while (i<n){
            i += i<<j;
            j++;
        }
        return i-n;
    }

    public static boolean checkBit(int n, int i){
        return ((n >> i) & 1) == 1;
    }

    private static int setBit(int N, int i){
        return (1<<i) | N ;
    }

    private static int toggleBit(int N, int i){
        return (1<<i) ^ N ;
    }

    private static int solve(int n){
        int t=n,c=0;
        while (t!=0){
            if((t&1)==0)
                c++;
            if((t&1)==1)
                break;
            t = t>>1;
        }
        return (1<< (c+1)) -1  ^ n;
//        return  n-1;
    }

    private static Long clearBitFromLastX(Long A, int B){
        return A & (-(1L << B));
    }

    private static int clearBit(int n, int i){
        return ( (1<<i) | n ) ^ (1<<i);
//        return  n & ~(1<<i);
    }

    private static char findTheDifference(String s, String t){
        HashMap<Character, Integer> charCount = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if(charCount.containsKey(s.charAt(i))){
                charCount.replace(s.charAt(i),charCount.get(s.charAt(i))+1);
            }else
                charCount.put(s.charAt(i),1);

            if(charCount.containsKey(t.charAt(i))){
                charCount.replace(t.charAt(i),charCount.get(t.charAt(i))+1);
            }else
                charCount.put(t.charAt(i),1);

        }
        if(charCount.containsKey(t.charAt(t.length()-1))){
            charCount.replace(t.charAt(t.length()-1),charCount.get(t.charAt(t.length()-1))+1);
        }else
            charCount.put(t.charAt(t.length()-1),1);

        char c = ' ';
        for (Map.Entry<Character,Integer> e:
             charCount.entrySet()) {
            if((e.getValue()&1)==1){
                c= e.getKey();
            }
        }
        return c;
    }

    private static int singleNumber(ArrayList<Integer> A) {
        int xor =0;
        for (int i = 0; i < A.size(); i++) {
            xor = xor ^ A.get(i);
        }
        return xor;
    }

    public static int singleNumberI(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-2; i+=3) {
            if(nums[i] != ((nums[i] ^ nums[i+1]) ^ nums[i+2])){
                    return nums[i];
            }
            if(nums[i]!=nums[i+1] ||nums[i+1]!=nums[i+2]) return nums[i];
        }
        return nums[nums.length-1];
    }

    private static String addBinaryString(String A, String B){
        if(A.length()>B.length()){
            int diff = A.length() - B.length();
            StringBuffer s = new StringBuffer();
            for (int i = 0; i < diff; i++) {
                s.append(0);
            }
            B = s+B;
        }else{
            int diff = B.length() - A.length();
            StringBuffer s = new StringBuffer();
            for (int i = 0; i < diff; i++) {
                s.append(0);
            }
            A = s+A;
        }
        int c=0;
        StringBuilder sb = new StringBuilder();
        for (int i = A.length()-1; i >=0; i--) {

            int a = Integer.parseInt(String.valueOf(A.charAt(i)));
            int b = Integer.parseInt(String.valueOf(B.charAt(i)));
            int s = (a+b+c)%2;
            c = (a+b+c)/2;
            sb.insert(0, s);
        }

        if(c!=0) sb.insert(0, c);
        return sb.toString();
    }

    private static int numSetBits(int A){
        int c=0;
        while (A!=0){
            if((A&1)==1){
                c++;
            }
            A = A>>1;
        }
        return c;

        /*while(A>0){
            A = A&A-1;
            c++;
        }
        return c;*/
    }

    private static String possibleToMakeZero(ArrayList<Integer> A){
        int xor=0;
        for (int i = 0; i < A.size(); i++) {
            xor =xor ^ A.get(i);
        }
        if((xor & 1) ==0){
            return "YES";
        }else
            return "NO";
    }

    private static long reverse(long a){
        long sum=0;
        for (int i = 31; i >=0; i--) {
            if((a&1)==1)
                sum += 1L <<i;
            a=a>>1;
        }
        return sum;
    }

    private static boolean checkPowersOfThree(int n) {
        for(int i=15;i>=0;i--)
        {
            int po=(int)Math.pow(3,i);
            if(n>=po) n=n-po;
            if(n==0)return true;
        }
        return false;
    }

    private static int[] getMaximumXor(int[] nums, int maximumBit) {
        int k = (1<<maximumBit) - 1;
        int[] ans = new int[nums.length];
        int total=0;
        for (int i = 0; i < nums.length; i++) {
            total ^= nums[i];
        }

        for (int i = nums.length-1, j=0; i >=0; i--,j++) {
            ans[j]= total ^ k;
            total = total ^ nums[i];
        }
        return ans;
    }

    private static int matrixScore(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int ans = n;

        for (int i = 1; i < m; i++) {
            int countOf0 =0;
            for (int j = 0; j < n; j++) {
                if((grid[j][0]==0 && grid[j][i] == 1) || (grid[j][0]==1 && grid[j][i]==0) )
                    countOf0++;
            }
            ans <<=1;
            ans += Math.max(countOf0 , n-countOf0);
        }
        return ans;
    }

    private static List<Integer> grayCode(int n) {
        if(n==1){
            ArrayList<Integer> baseRes = new ArrayList<>();
            baseRes.add(0);
            baseRes.add(1);
            return baseRes;
        }
        List<Integer> list = grayCode(n-1);
        int num = 1<<(n-1);
        ArrayList<Integer> res = new ArrayList<>(list);
        for (int i = list.size()-1; i >=0; i--) {
            res.add(list.get(i)+num);
        }
        return res;
    }

    private static int rangeBitwiseAnd(int left, int right) {
        int a = 0;
        while(left != right) {
            left >>= 1;
            right >>= 1;
            a++;
        }
        return left<<a;

    }

    private static int kthGrammar(int n, int k) {
        int h = 1<<(n-1);
        int c=0;
        while (h>1){
            h>>= 1;
            if(k>h){
                k -= h;
                c++;
            }
        }
        return c%2;

        //brute force approach
        /*if(n==1) return 0;
        StringBuffer sb = new StringBuffer("0");
        for (int i = 2; i <= n; i++) {
            String s = sb.toString();
            sb = new StringBuffer();
            for (int j = 0; j < s.length(); j++) {
                if(s.charAt(j)=='0')
                    sb.append("01");
                else
                    sb.append("10");
            }
        }
        System.out.println(sb);
        return Integer.parseInt(sb.charAt(k-1)+"");*/
    }

    private static int getSum(int a, int b) {
        int c=0,s=0;
        int ans=0;
        for (int i = 0; i < 32; i++) {
            int bitA = checkBit(a,i)?1:0;
            int bitB = checkBit(b,i)?1:0;
            s = bitA + bitB + c;
            int bit = s%2;
            c = s/2;
            if(bit==1)
                ans = setBit(ans,i);
        }
        return ans;
    }

    private static boolean validUtf8(int[] data){
        int remaining=0;
        for(int i:data){
            if(remaining==0){
                if(i>>7==0b0){
                    remaining = 0;
                }
                else if(i>>5==0b110){
                    remaining = 1;
                }
                else if(i>>4==0b1110){
                    remaining = 2;
                }
                else if(i>>3==0b11110){
                    remaining = 3;
                }
                else{
                    return false;
                }
            }
            else{
                if(i>>6==0b10){
                    remaining--;
                }
                else{
                    return false;
                }
            }
        }
        return remaining == 0;
    }

    private static List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        permutation(s, 0, sb, res);
        return res;
    }

    private static void permutation(String s, int i, StringBuffer sb, List<String> res) {
        if(sb.length()==s.length()){
            res.add(sb.toString());
            return;
        }
        sb.append(s.charAt(i));
        permutation(s,i+1, sb, res);
        sb.deleteCharAt(sb.length()-1);
        if(s.charAt(i)>='a' && s.charAt(i)<='z')
            sb.append(Character.toUpperCase(s.charAt(i)));
        else if(s.charAt(i)>='A' && s.charAt(i)<='Z')
            sb.append(Character.toLowerCase(s.charAt(i)));
        else
            return;

        permutation(s,i+1, sb, res);
        sb.deleteCharAt(sb.length()-1);
    }

    private static String addBinary(String a, String b) {
        StringBuffer res = new StringBuffer();
        int i = a.length()-1;
        int j = b.length()-1;
        int c=0;
        while (i>=0 || j>=0){
            int s = c;
            if(i>=0) s += a.charAt(i--) -'0';
            if(j>=0) s += b.charAt(j--) -'0';
            c = s>1?1:0;
            res.append(s%2);
        }
        if(c!=0) res.append(c);
        return res.reverse().toString();
    }

    private static int[] xorQueries(int[] arr, int[][] queries) {
        int[] prefixXoR = new int[arr.length];
        int[] res = new int[queries.length];

        for (int i = 0; i < arr.length; i++) {
            if(i==0) prefixXoR[0] = arr[0];
            else
                prefixXoR[i] = prefixXoR[i-1] ^ arr[i];
        }

        for (int i = 0; i < queries.length; i++) {
            res[i] = ( (queries[i][0]!=0)?prefixXoR[queries[i][0]-1]:0 ) ^ prefixXoR[queries[i][1]];
        }
        return res;
    }

    private static int[] decode(int[] encoded) {
        int[] res = new int[encoded.length+1];
        int firstElm=0;
        int n = encoded.length+1;
        for (int i = 1; i <=n; i++) {
            firstElm ^= i;
        }
        for (int i = 1; i <n; i+=2) {
            firstElm ^= encoded[i];
        }
        res[0]=firstElm;
        for (int i = 1; i < res.length; i++) {
            res[i] = res[i-1] ^ encoded[i-1];
        }
        return res;
    }

    // All Triple ele and 1 unique
    private static int singleNumber3(final List<Integer> A){
        int ans=0;
        for (int i = 0; i < 31; i++) {
            int c=0;
            for (int j = 0; j < A.size(); j++) {
                if(checkBit(A.get(j),i)) c++;
            }
            if(c%3==1)  ans+=(1<<i);
        }
        return ans;
    }

    private static int maxSumOfAllXorPair(ArrayList<Integer> A){
        int sum =0;
        for (int i = 0; i < 31; i++) {
            int setC=0, unsetC=0;
            for (Integer e : A) {
                if(checkBit(e,i))   setC++;
                else unsetC++;
            }
            sum += setC * unsetC *(1<<i);
        }
        return 2*sum;
    }

    private static int maxAndOfPair(ArrayList<Integer> A){
        ArrayList<Integer> t = new ArrayList<>(A);
        int ans=0;
        for (int i = 31; i >=0; i--) {
            int elm =0;
            for (Integer integer : t) if (checkBit(integer, i)) elm++;

            if(elm>=2){
                ans += 1<<i;
                for (int j =0; j<t.size();j++) {
                    if(!checkBit(t.get(j),i)){
                        t.set(j,0);
                    }
                }
            }
        }
        return ans;
    }

    private static int hammingDistancePairSum(final List<Integer> A){
        int M = 1000000007;
        long sum =0;
        for (int i = 0; i < 31; i++) {
            int setC=0, unsetC=0;
            for (Integer e : A) {
                if(checkBit(e,i))   setC++ ;
                else unsetC++;
            }
            sum += (2L * setC * unsetC)%M ;
            sum = (sum >= M) ? sum-M : sum;
        }
        return (int)sum;
//        return sum%M;
    }

    private static int strangeEquality(int A){
        int temp = A;
        int x=0;
        int n=0;
        while (temp!=0){
            if(!checkBit(temp,0)) x += 1<<n;
            n++;
            temp = temp>>1;
        }

        int y = 1<<n;
        return x^y;
    }

    // find a number X such that A xor X is minimum possible, and the number of set bits in X equals B.
    private static int smallestXor(int A, int B){
        int x=0;
        for (int i = 31; i >=0; i--) {
            if(checkBit(A,i)){
                x += 1<<i;
                B--;
            }
            if(B==0) break;
        }
        if(B!=0){
            int n=0;
            while (A!=0){
                n++;
                A=A>>1;
            }
            int temp =x;
            int ti=0;
            while (temp!=0 && B!=0){
                if((temp&1)==0){
                    x += 1<<ti;
                    temp = temp>>1;
                    ti++;
                    B--;
                }else {
                    temp = temp >> 1;
                    ti++;
                }
            }
            while (B!=0){
                x += 1<<n;
                n++;
                B--;
            }
        }
        return x;
    }




}
