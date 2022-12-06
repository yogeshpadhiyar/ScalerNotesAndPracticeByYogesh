package com.DymanicProgramming;

import java.util.*;

public class DPProblems {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        String A = sc.nextLine();
//        String B = sc.nextLine();
//        String C = sc.nextLine();
        int n = sc.nextInt();
//        int m = sc.nextInt();
//        ArrayList<Integer> A = new ArrayList<>();
//        ArrayList<Integer> B = new ArrayList<>();
//        ArrayList<Integer> C = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            A.add(sc.nextInt());
//        }
//        for (int i = 0; i < m; i++) {
//            B.add(sc.nextInt());
//        }
//        for (int i = 0; i < m; i++) {
//            C.add(sc.nextInt());
//        }

        DPProblems dpProblems = new DPProblems();
        System.out.println(dpProblems.chordCnt(n));
    }

    public int maximumScore(int[] nums, int[] multipliers) {
        int N = nums.length;
        int M = multipliers.length;
        Integer[][] dp = new Integer[M][M];
        return maxScoreHelper(nums, multipliers, 0, 0, dp, N, M);
    }

    private int maxScoreHelper(int[] nums, int[] multipliers, int left, int index, Integer[][] dp, int N, int M) {
        int right = N-1-(index-left);
        if(index==M)    return 0;
        if(dp[left][index]!=null)   return dp[left][index];

        int res = Math.max( nums[left]*multipliers[index] + maxScoreHelper(nums,multipliers, left+1, index+1, dp, N, M),
                nums[right]*multipliers[index] + maxScoreHelper(nums,multipliers, left, index+1, dp, N, M));

        dp[left][index] = res;
        return res;
    }

    public int chordCnt(int A){
        int M = 1000000007;
        long[] dp = new long[A+1];
        dp[0]=1;
        dp[1]=1;
        for (int i = 2; i <=A; i++) {
            dp[i]=0;
            for (int j = 0; j < i; j++) {
                dp[i] = (dp[i] + (dp[j]%M * dp[i-j-1]%M)%M ) %M;
            }
        }
        return (int)dp[A];
    }

    public int russianDollEnvelopes(ArrayList<ArrayList<Integer>> A) {
        int envelopes = A.size();
        A.sort( (a,b) -> Objects.equals(a.get(0), b.get(0)) ?a.get(1)-b.get(1):a.get(0)-b.get(0));

        int[] dp= new int[envelopes];
        Arrays.fill(dp,1);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < envelopes; i++) {
            for (int j = 0; j < i; j++) {
                if( (A.get(i).get(0)>A.get(j).get(0)) && (A.get(i).get(1)>A.get(j).get(1)) && (dp[i]<dp[j]+1)){
                    dp[i]=dp[j]+1;

                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public int isInterleave(String A, String B, String C) {
        int m = A.length();
        int n = B.length();
        if(m+n !=C.length())    return 0;
        boolean[][] dp = new boolean[m+1][n+1];
        for (int i = 0; i <=m; i++) {
            for (int j = 0; j <=n; j++) {
                if(i==0 && j==0)    dp[i][j]=true;
                else if(i==0 && B.charAt(j-1)==C.charAt(i+j-1)){
                    dp[i][j] = dp[i][j-1];
                }else if(j==0 && A.charAt(i-1)==C.charAt(i+j-1)){
                    dp[i][j] = dp[i-1][j];
                }else if(i!=0 && j!=0 && A.charAt(i-1)==C.charAt(i+j-1) && B.charAt(j-1)!=C.charAt(i+j-1)){
                    dp[i][j] = dp[i-1][j];
                }else if(i!=0 && j!=0 && A.charAt(i-1)!=C.charAt(i+j-1) && B.charAt(j-1)==C.charAt(i+j-1)){
                    dp[i][j] = dp[i][j-1];
                }else if(i!=0 && j!=0 && A.charAt(i-1)==C.charAt(i+j-1) && B.charAt(j-1)==C.charAt(i+j-1)){
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
            }
        }
        return dp[m][n]?1:0;
    }

    public int longestPalindromicSubsequence(String A) {
        int n = A.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i]=1;
        }
        for (int len = 2; len <=n; len++) {
            for (int i = 0; i < n-len+1; i++) {
                int j = i+len-1;
                if(A.charAt(i)==A.charAt(j) && len==2){
                    dp[i][j]=2;
                }else if(A.charAt(i)==A.charAt(j)){
                    dp[i][j] = dp[i+1][j-1]+2;
                }else{
                    dp[i][j]= Math.max(dp[i][j-1], dp[i+1][j]);
                }
            }
        }
        return dp[0][n-1];
    }

    public int minCut(String A) {
        int n = A.length();
        boolean[][] mat = new boolean[n][n];
        constructMatrixForPalindrome(A,n-1,mat);
        int[] dp = new int[n];
        Arrays.fill(dp,-1);
        minPartition(A, dp, mat, n-1);
        return dp[n-1];
    }
    private int minPartition(String A, int[] dp, boolean[][] mat, int i) {
        if(mat[0][i]) {
            dp[i]=0;
            return 0;
        }
        if(dp[i]==-1){
            int ans = Integer.MAX_VALUE;
            for (int j = i-1; j>=0; j--) {
                if(mat[j+1][i]){
                    ans = Math.min(ans, minPartition(A,dp, mat, j)+1);
                }
            }
            dp[i]=ans;
        }
        return dp[i];
    }
    public void constructMatrixForPalindrome(String A, int n, boolean[][] mat){
        for (int i = n; i >=0; i--) {
            for (int j = n; j>=i; j--) {
                if(i==j || (A.charAt(i)==A.charAt(j) && ( i+1>=j-1 || mat[i+1][j-1]))){
                    mat[i][j]=true;
                }
            }
        }
    }

    public int matrixChainMulti(ArrayList<Integer> A) {
        int n = A.size();
        int[][] dp = new int[n+1][n+1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(dp[i],-1);
        }
        return mcm(dp, A, 1,n-1);
    }
    private int mcm(int[][] dp, ArrayList<Integer> A, int i, int j) {
        if(i==j)    return 0;
        if(dp[i][j]==-1){
            int ans = Integer.MAX_VALUE;
            for (int k = i; k < j; k++) {
                int l = mcm(dp, A, i, k);
                int r = mcm(dp, A, k+1, j);
                int b = A.get(i-1)*A.get(k)*A.get(j);
                ans = Math.min(ans, l+r+b);
            }
            dp[i][j]=ans;
        }
        return dp[i][j];
    }

    public int anyTwo(String A) {
        int n = A.length();
        int[][] lcs = new int[n+1][n+1];
        for (int i = 1; i <=n; i++) {
            for (int j = 1; j <=n; j++) {
                if(A.charAt(i-1)==A.charAt(j-1) && i!=j)    lcs[i][j]= 1+lcs[i-1][j-1];
                else    lcs[i][j] = Math.max(lcs[i-1][j],lcs[i][j-1]);
            }
        }
        if(lcs[n][n]>=2)    return 1;
        else return 0;
    }
    public static boolean isPalindrome(String str, int l, int h) {
        while(h > l)
            if (str.charAt(l++) != str.charAt(h--))
                return false;
        return true;
    }

    public int countVowelPermutation(int n) {
        int M = 1000000007;
        long[][] dp = new long[6][n+1];
        if(n==1) return 5;
        for (int i = 0; i <5; i++) {
            dp[i][0]=1;
        }
        countVowelPermutationHelper(n,'z', dp,M);
        return (int)dp[5][n];
    }

    private long countVowelPermutationHelper(int n, char ch, long[][]dp, int M) {
        long ans = 0;
        if(n==0)    return 1;
        if(ch=='z'){
            ans = (ans+countVowelPermutationHelper(n-1,'a',dp,M)+countVowelPermutationHelper(n-1,'e',dp,M)+
                    countVowelPermutationHelper(n-1,'i',dp,M)+countVowelPermutationHelper(n-1,'o',dp,M)+
                    countVowelPermutationHelper(n-1,'u',dp,M))%M;
            dp[5][n]=ans;
        }
        else if(ch=='a'){
            if(dp[0][n]!=0) return dp[0][n];
            ans = (ans+countVowelPermutationHelper(n-1,'e',dp,M))%M;
            dp[0][n]=ans;
        }
        else if(ch=='e'){
            if(dp[1][n]!=0) return dp[1][n];
            ans = (ans+countVowelPermutationHelper(n-1,'a',dp,M)+ countVowelPermutationHelper(n-1,'i',dp,M))%M;
            dp[1][n]=ans;
        }
        else if(ch=='i'){
            if(dp[2][n]!=0) return dp[2][n];
            ans = (ans + countVowelPermutationHelper(n-1,'a',dp,M) + countVowelPermutationHelper(n-1,'e',dp,M)
                    + countVowelPermutationHelper(n-1,'o',dp,M)+ countVowelPermutationHelper(n-1,'u',dp,M))%M;
            dp[2][n]=ans;
        }
        else if(ch=='o'){
            if(dp[3][n]!=0) return dp[3][n];
            ans = (ans + countVowelPermutationHelper(n-1,'i',dp,M)+ countVowelPermutationHelper(n-1,'u',dp,M))%M;
            dp[3][n]=ans;
        }
        else if(ch=='u'){
            if(dp[4][n]!=0) return dp[4][n];
            ans = (ans+countVowelPermutationHelper(n-1,'a',dp,M))%M;
            dp[4][n]=ans;
        }
        return ans;
    }

    public int buyingCandy(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<Integer> C, int D) {
        //total Sweetness
        for (int i = 0; i < A.size(); i++) {
            B.set(i, B.get(i)*A.get(i));
        }
        int n = B.size();
        int[][] dp = new int[n+1][D+1];
        for (int i = 1; i <=n; i++) {
            for (int j = 0; j <=D; j++) {
                int a = dp[i-1][j];
                if(j>=C.get(i-1)){
                    a = Math.max(a, dp[i][j-C.get(i-1)]+B.get(i-1));
                }
                dp[i][j]=a;
            }
        }
        return dp[n][D];
    }

    public int knapsackII(ArrayList<Integer> A, ArrayList<Integer> B, int C) {
        int n = A.size();
        int[][] dp = new int[2][C+1];
        int ans=0;
        for (int i = 1; i <=n; i++) {
            for (int j = 0; j <=C; j++) {
                dp[i%2][j] = dp[(i-1)%2][j];
                if(j>=B.get(i-1)){
                    dp[i%2][j] = Math.max(dp[i%2][j],dp[(i-1)%2][j-B.get(i-1)]+A.get(i-1));
                }
                ans = Math.max(ans,dp[i%2][j]);
            }
        }
        return ans;
    }

    public int wayToSendSignal(int A) {
        int M = 1000000007;
        int[] dp = new int[A+1];
        dp[0]=1;
        dp[1]=2;
        for (int i = 2; i <=A; i++) {
            dp[i]= (dp[i-1]+dp[i-2])%M;
        }
        return dp[A];
    }

    public int cuttingRod(ArrayList<Integer> A) {
        int n =A.size();
        int[] dp = new int[n+1];
        Arrays.fill(dp,-1);
        cuttingRodHelper(A,dp, n);
        return dp[n];
    }
    private int cuttingRodHelper(ArrayList<Integer> A, int[] dp, int total) {
        if(total<=0) return 0;
        if(dp[total]==-1){
            int res = 0;
            for (int i = 1; i <=total; i++) {
                int curr = A.get(i-1)+cuttingRodHelper(A,dp,total-i);
                res = Math.max(res,curr);
            }
            dp[total] = res;
        }
        return dp[total];
    }

    public int coinChange2(ArrayList<Integer> A, int B) {
        int M = 1000007;
        int n = A.size();
        int[] dp = new int[B+1];
        dp[0]=1;
        for (int i = 0; i < n; i++) {
            for (int j = A.get(i); j <=B; j++) {
                dp[j] += dp[j-A.get(i)];
                dp[j] %= M;
            }
        }
        return dp[B];
    }

    public int subsetWithSumK(ArrayList<Integer> A, int B){
        int n = A.size();
        int[][] dp = new int[n][B+1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i],-1);
        }
        subsetWithSumKHelper(A,B,n-1,B,dp);
        return dp[n-1][B];
    }
    private int subsetWithSumKHelper(ArrayList<Integer> A, int B, int i, int j, int[][] dp) {
        if(i==0&&j==0)  return 1;
        if(dp[i][j]==-1){
            dp[i][j] = subsetWithSumKHelper(A,B,i-1,j,dp);
            if(j>=A.get(i)){
                dp[i][j] += subsetWithSumKHelper(A,B,i-1,j-A.get(i),dp);
            }
        }
        return dp[i][j];
    }

    public int numDistinct(String A, String B) {
        int n=A.length();
        int m=B.length();
        int[][] dp = new int[n+1][m+1];
        for (int i = 0; i <=m; i++) {
            dp[0][i]=0;
        }
        for (int i = 0; i <=n; i++) {
            dp[i][0]=1;
        }
        for (int i = 1; i <=n; i++) {
            for (int j = 1; j <=m; j++) {
                if (A.charAt(i-1) == B.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-1];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][m];
    }

    private int numDistinctHelper(String A, String B, int i, int j, int[][] dp) {
        if(i==-1 && j==-1) return 1;
        if(i==-1)   return 0;
        if(j==-1)   return 1;
        if(dp[i][j]==-1) {
            if (A.charAt(i) == B.charAt(j)) {
                dp[i][j] = numDistinctHelper(A, B, i - 1, j, dp) + numDistinctHelper(A, B, i - 1, j - 1, dp);
            } else {
                dp[i][j] = numDistinctHelper(A, B, i - 1, j, dp);
            }
        }
        return dp[i][j];
    }

    public int tusharBdayParty(final List<Integer> A, final List<Integer> B, final List<Integer> C) {
        int max = Integer.MIN_VALUE;
        for(int i : A){
            max = Math.max(max, i);
        }
        int[] dp = new int[max+1];
        Arrays.fill(dp,-1);
        knapsackForParty(max, B,C, dp);
        int ans =0;
        for (int i : A){
            ans+= dp[i];
        }
        return ans;
    }
    public int knapsackForParty(int val ,List<Integer> B, List<Integer> C, int[] dp){
        if(val==0)  return 0;
        if(dp[val]!=-1) return dp[val];
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < B.size(); i++) {
            int cost = Integer.MAX_VALUE;
            if(val>=B.get(i)){
                cost = C.get(i) + knapsackForParty(val-B.get(i), B, C, dp);
            }
            ans = Math.min(ans, cost);
        }
        dp[val] = ans;
        return ans;
    }

    public int knapsackUnbounded(int A,List<Integer> B, List<Integer> C) {
        int n = B.size();
        int[][] dp = new int[n+1][A+1];
        for (int i = 0; i < A + 1; i++) {
            dp[0][i]=0;
        }
        for (int i = 1; i <=n; i++) {
            for (int j = 0; j < A + 1; j++) {
                int a = dp[i-1][j];
                if(j>=C.get(i-1)){
                    a = Math.max(a, dp[i][j-C.get(i-1)]+B.get(i-1));
                }
                dp[i][j]=a;
            }
        }
        return dp[n][A];
    }

    public int knapsack(ArrayList<Integer> A, ArrayList<Integer> B, int C) {
        int n = A.size();
        int[][] dp = new int[n+1][C+1];
        for (int i = 0; i < C + 1; i++) {
            dp[0][i]=0;
        }
        for (int i = 1; i <=n; i++) {
            for (int j = 0; j < C + 1; j++) {
                int a = dp[i-1][j];
                if(j>=B.get(i-1)){
                    a = Math.max(a, dp[i-1][j-B.get(i-1)]+A.get(i-1));
                }
                dp[i][j]=a;
            }
        }
        return dp[n][C];
    }

    public int isMatch(final String A, final String B) {
        int n=A.length();
        int m=B.length();
        boolean[][] dp = new boolean[n+1][m+1];
        dp[0][0]=true;
        for (int i = 1; i <=m; i++) {
            if(B.charAt(i-1)=='*')  dp[0][i]=true;
            else break;
        }
        for (int i = 1; i <=n; i++) {
            for (int j = 1; j <=m; j++) {
                if(A.charAt(i-1)==B.charAt(j-1) || B.charAt(j-1)=='?'){
                    dp[i][j] = dp[i-1][j-1];
                }else if(B.charAt(j-1)=='*'){
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
            }
        }
        return dp[n][m]?1:0;
        /*int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i],-1);
        }
        return isMatchHelper(A,B,n-1,m-1,dp);*/
    }

    //using recursion
    private int isMatchHelper(String A, String B, int i, int j, int[][] dp) {
        if(i==-1 && j==-1)  return 1;
        if(j==-1)   return 0;
        if(i==-1){
            for (int k = 0; k <= j; k++) {
                if(B.charAt(k)!='*')    return 0;
            }
            return 1;
        }
        if(dp[i][j]==-1){
            if (A.charAt(i) == B.charAt(j) || B.charAt(j)=='?') {
                dp[i][j] = isMatchHelper(A,B,i-1,j-1,dp);
            }else if(B.charAt(j)=='*'){
                dp[i][j]= isMatchHelper(A,B,i-1,j,dp);
                if(dp[i][j]==0) dp[i][j] = isMatchHelper(A,B,i,j-1,dp);
            }else{
                dp[i][j]=0;
            }
        }
        return dp[i][j];
    }

    public int minDistance(String A, String B) {
        int n =A.length();
        int m =B.length();
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i],-1);
        }
        minDistanceHelper(A,B, n-1,m-1, dp);
        return dp[n-1][m-1];
    }
    private int minDistanceHelper(String A, String B, int i, int j, int[][] dp) {
        if(i==-1&&j==-1)    return 0;
        if(i==-1)   return j+1;
        if(j==-1)   return i+1;
        if(dp[i][j]==-1) {
            if (A.charAt(i) == B.charAt(j)) {
                dp[i][j] = minDistanceHelper(A, B, i - 1, j - 1, dp);
            } else {
                dp[i][j] = 1+ Math.min( minDistanceHelper(A,B,i,j-1,dp),
                        Math.min( minDistanceHelper(A,B,i-1,j,dp),minDistanceHelper(A,B,i-1,j-1,dp) ) );
            }
        }
        return dp[i][j];
    }

    public int longestCommonSubsequence(String A, String B){
        int n =A.length();
        int m =B.length();
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i],-1);
        }
        lcsHelper(A,B,n-1,m-1,dp);
        return dp[n-1][m-1];
    }
    private int lcsHelper(String A, String B, int i, int j, int[][] dp) {
        if(i==-1 || j==-1) return 0;
        if(dp[i][j]==-1){
            if(A.charAt(i)==B.charAt(j)){
                dp[i][j] = 1+lcsHelper(A,B,i-1,j-1,dp);
            }else{
                dp[i][j] = Math.max(lcsHelper(A,B,i-1,j,dp), lcsHelper(A,B,i,j-1,dp));
            }
        }
        return dp[i][j];
    }

    public int minFallingPathSum(ArrayList<ArrayList<Integer>> A) {
        int n = A.size();
        Integer[][] dp = new Integer[n][n];
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, minFallingPathSumHelper(A,0,i,dp));
        }
        return ans;
    }
    private int minFallingPathSumHelper(ArrayList<ArrayList<Integer>> A, int r, int c, Integer[][] dp) {
        if(r>A.size())  return Integer.MAX_VALUE;
        if(c<0|| c>=A.size())   return Integer.MAX_VALUE;
        if(r==A.size()-1){
            dp[r][c] = A.get(r).get(c);
            return dp[r][c];
        }
        if(dp[r][c]!=null)  return dp[r][c];
        int temp = Integer.MAX_VALUE;
        for (int i = 0; i < A.size(); i++) {
            if(i!=c){
                temp = Math.min(temp, minFallingPathSumHelper(A,r+1,i,dp));
            }
        }
        dp[r][c] = A.get(r).get(c)+temp;
        return dp[r][c];
    }

    public int findLIS(ArrayList<Integer> A){
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(A.get(0));
        for (int i = 1; i < A.size(); i++) {
            int index = getPositionInTempArray(temp,A.get(i));
            if(index>=temp.size())   temp.add(A.get(i));
            else temp.set(index,A.get(i));
        }
        return temp.size();
    }
    private int getPositionInTempArray(ArrayList<Integer> temp, int B) {
        int l=0, h= temp.size()-1;
        int ans=temp.size();
        while (l<=h){
            int mid = (l+h)/2;
            if(temp.get(mid)>=B){
                ans=mid;
                h=mid-1;
            }else l=mid+1;
        }
        return ans;
    }

    public int wayToHaveFun(int A){
        int M = 1000000007;
        long[][] dp = new long[A+1][3];
        dp[1][0]= dp[1][1]= dp[1][2]= 1L;
        for (int i = 2; i <=A; i++) {
            dp[i][0] = ( (dp[i-1][0]+dp[i-1][1]) + dp[i-1][2])%M;
            dp[i][1] = (dp[i-1][0]+dp[i-1][2])%M;
            dp[i][2] = ( (dp[i-1][0]%M+dp[i-1][1]%M - (2*dp[i-2][2])%M)+M )%M;
        }
        long ans = ( (dp[A][0]+dp[A][1]) + dp[A][2])%M;
        return (int)ans;
    }

    public int minimumTotal(ArrayList<ArrayList<Integer>> a) {
        int[][] dp = new int[a.size()][];
        for (int i = 0; i < a.size(); i++) {
            dp[i] = new int[a.get(i).size()];
        }
        int n = a.size();
        for (int i = 0; i < a.get(n - 1).size(); i++) {
            dp[n-1][i] = a.get(n-1).get(i);
        }
        for (int i = n-2; i>=0; i--) {
            for (int j = 0; j < a.get(i).size(); j++) {
                dp[i][j] = Math.min(dp[i+1][j],dp[i+1][j+1])+a.get(i).get(j);
            }
        }
        return dp[0][0];
    }

    public int calculateMinimumHP(ArrayList<ArrayList<Integer>> A) {
        int row=A.size();
        int col=A.get(0).size();
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dp[i][j]=-1;
            }
        }
        dp[row-1][col-1] = Math.max(1,1-A.get(row-1).get(col-1));
        return  calculateMinimumHPHelper(A,row,col, dp,0,0);
    }
    private int calculateMinimumHPHelper(ArrayList<ArrayList<Integer>> A, int row, int col, int[][] dp, int i, int j) {
        if(i==row || j==col)    return Integer.MAX_VALUE;
        if(dp[i][j]==-1){
            int a = calculateMinimumHPHelper(A,row,col,dp,i,j+1);
            int b = calculateMinimumHPHelper(A,row,col,dp,i+1,j);
            dp[i][j]= Math.max(1, Math.min(a,b)-A.get(i).get(j));
        }
        return dp[i][j];
    }

    public int uniquePathsWithObstacles(ArrayList<ArrayList<Integer>> A) {
        int row =A.size();
        int col =A.get(0).size();
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            if(A.get(i).get(0)==0)  dp[i][0]=1;
            else break;
        }
        for (int i = 0; i < col; i++) {
            if(A.get(0).get(i)==0)  dp[0][i]=1;
            else break;
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if(A.get(i).get(j)==0) {
                    dp[i][j] = dp[i-1][j]+dp[i][j-1];
                }
            }
        }
        return dp[row-1][col-1];
    }

    public int minPathSum(ArrayList<ArrayList<Integer>> A) {
        int row=A.size();
        int col=A.get(0).size();
        int[][] dp = new int[row][col];
        for (int i = 0; i < A.size(); i++) {

            for (int j = 0; j < A.get(i).size(); j++) {
                if(i==0 && j==0)    dp[i][j]=A.get(i).get(j);
                else if(i==0 || j==0){
                    if(i==0) dp[i][j] = dp[i][j-1]+A.get(i).get(j);
                    else dp[i][j]= dp[i-1][j]+A.get(i).get(j);
                }
                if(i>0 && j>0){
                    dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1])+A.get(i).get(j);
                }
            }
        }
        return dp[row-1][col-1];
    }

    public int adjacent(ArrayList<ArrayList<Integer>> A) {
        if(A.get(0).size()==1)  return Math.max(A.get(0).get(0),A.get(1).get(0));
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < A.get(0).size(); i++) {
            a.add(Math.max(A.get(0).get(i),A.get(1).get(i)));
        }
        int[] dp = new int[a.size()];
        dp[0]=a.get(0);
        dp[1]=Math.max(a.get(0),a.get(1));
        for (int i = 2; i < a.size(); i++) {
            dp[i] = Math.max(dp[i-1], Math.max(a.get(i)+dp[i-2], a.get(i)));
        }
        return dp[a.size()-1];
    }

    public int wayToDecode(String A){
        int M = 1000000007;
        if(A.isEmpty()) return 0;
        if(A.charAt(0)=='0') return 0;
        int[] dp = new int[A.length()+1];
        dp[0]=1;
        dp[1]=1;
        for (int i = 2; i <=A.length(); i++) {
            if(A.charAt(i-1)>='1' && A.charAt(i-1)<='9'){
                dp[i] = dp[i-1]%M;
            }
            if(A.charAt(i-2)=='1')  dp[i]= (dp[i]+dp[i-2])%M;
            else if(A.charAt(i-2)=='2' && (A.charAt(i-1)>='1' && A.charAt(i-1)<='6')){
                dp[i]= (dp[i]+dp[i-2])%M;
            }
        }
        return dp[A.length()];
    }

    public int lis(final List<Integer> A) {
        int max = 0;
        int[] dp = new int[A.size()];
        for (int i = 0; i < A.size(); i++) {
            int v = 0;
            for (int j = 0; j < i; j++) {
                if(A.get(i)>A.get(j))   v= Math.max(v,dp[j]);
            }
            max = Math.max(max,v+1);
            dp[i]=v+1;
        }
        return max;
    }

    public int diceThrow(int A){
        int M = 1000000007;
        int[] dp = new int[A+1];
        dp[0]=1;
        for (int i = 1; i <=A; i++) {
            int s=0;
            for (int j = 1; j <=6 && j<=i; j++) {
                s= (s+dp[i-j])%M;
            }
            dp[i]=s;
        }
        return dp[A];
    }

    //For Fibonacci dp[0]=0;
    private int climbStairs(int A) {
        int[] dp = new int[A+1];
        dp[0]=1;
        dp[1]=1;
        for (int i = 2; i <= A; i++) {
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[A];
    }

    public int letsParty(int A) {
        int M =10003;
        int[] dp = new int[A+1];
        dp[0]=1;
        dp[1]=1;
        for (int i = 2; i <= A; i++) {
            dp[i]= (dp[i-1]%M+ ((i-1)*dp[i-2])%M)%M;
        }
        return dp[A];
    }

    public int countMinSquares(int A){
        int[] dp = new int[A+1];
        for (int i = 1; i <=A; i++) {
            dp[i]=i;
            for (int j = 1; j*j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i-j*j]+1);
            }
        }
        return dp[A];
    }
}
