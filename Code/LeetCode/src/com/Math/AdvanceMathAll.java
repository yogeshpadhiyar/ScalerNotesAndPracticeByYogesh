package com.Math;

import java.util.*;

public class AdvanceMathAll {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
//        int N = sc.nextInt();
//        int B = sc.nextInt();
//        int C = sc.nextInt();
        String A = sc.nextLine();
        /*ArrayList<Integer> A = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            A.add(sc.nextInt());
        }*/
//        int M = sc.nextInt();
        System.out.println(findRankRepeat(A));
    }

    public static int reverseIntegerSigned(int x){
        int sign = 1;
        if(x<0){
            sign = -1;
            x = sign * x;
        }
        int ans = 0;
        while (x>0){
            if(ans *sign < (Integer.MIN_VALUE/10) || ans*sign> (Integer.MAX_VALUE/10)){
                return 0;
            }
            ans = ans*10 + x%10;
            x /=10;
        }
        return sign*ans;
    }

    //TODO pending
    // Que: No of digit occur same as digit
    public  static  long beautifulNo(long N){
        long result = 0L;
        int digit = 0;
        long temp = N;
        while (temp!=0){
            digit++;
            temp /=10;
        }
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 1; i <=digit; i++) {
            map.put(i, new ArrayList<>(Collections.nCopies(i,i)));
        }
        ArrayList<Integer> reqDigit;
        for (int i = 1; i <=digit/2; i++) {
            long tans=0L;
            reqDigit = new ArrayList<>();
            reqDigit.addAll(map.get(i));
            reqDigit.addAll(map.get(digit-i));
            for (int j = 0; j < reqDigit.size(); j++) {
                tans += reqDigit.get(i)* Math.pow(10,j);
            }
            if(tans<N)  break;
            else{
                for (int j = 0; j < reqDigit.size(); j++) {
                    for (int k = 0; k < reqDigit.size(); k++) {

                    }
                }
            }
        }
        reqDigit = new ArrayList<>();
        int t=1;
        while (reqDigit.size()<=digit){
            reqDigit.addAll(map.get(t));
            t++;
        }
        for (int i = reqDigit.size()-1; i >=0; i--) {
            result += reqDigit.get(i) * Math.pow(10,i);
        }
        return  result;
    }
    public static int distinctPrimeCount(ArrayList<Integer> A){
        int max = Integer.MIN_VALUE;
        for (Integer i : A) max = Math.max(max, i);
        ArrayList<Integer> spf = smallestPrimeFactor(max);
        HashSet<Integer> set = new HashSet<>();
        for (Integer elm : A) {
            int val = spf.get(elm);
            while (elm!=1){
                set.add(val);
                elm /= val;
                val = spf.get(elm);
            }
        }
        return set.size();
    }

    private static int factors(int r) {
        HashSet<Integer> factor=new HashSet<>();
        for (int i = 1; i <= Math.sqrt(r); i++) {
            if(r%i==0) {
                int f1 = r/i;
                int f2 = r/f1;
                factor.add(f1);
                factor.add(f2);
            }
        }
        return factor.size();
    }
    public static int noOfOpenDoors(int A){
        return (int)Math.sqrt(A);
    }

    public static int primeSubSequence(ArrayList<Integer> A){
        int M = 1000000007;
        int count = 0;
        for (Integer v : A) {
            if(isPrime(v)) count++;
        }
        long ans = 1L;
        for (int i = 0; i < count; i++) {
            ans = (ans*2)%M;
        }
        ans = (ans<0)?ans+M:ans%M;
        return (int)(ans-1);
    }

    public static int findRankRepeat(String A){
        int M = 1000003;
        long ans=0;
        ArrayList<Long> freq = new ArrayList<>(Collections.nCopies(300,0L));
        long n = A.length();
        ArrayList<Long> fact = new ArrayList<Long>();

        //cal factorial
        fact.add(1L);
        for (int i = 1; i <=n; i++) {
            fact.add( (fact.get(i-1) %M * (i %M)) %M);
        }
        
        //cal freq
        for (int i = 0; i < n; i++) {
            freq.set( A.charAt(i), freq.get(A.charAt(i))+1 );
        }

        //actual work for all char
        for (int i = 0; i <n; i++) {
            long cnt = 0L;
            long div = 1L;

            for (int j = A.charAt(i)-1; j >=0; j--) {
                cnt += freq.get(j);
            }

            //divisor multiple
            for (int j = 0; j < 300; j++) {
                div = (div%M * (fact.get(Math.toIntExact(freq.get(j))))%M )%M;
            }

            long a = pow(div,M-2,M);

            ans = (ans + (cnt * a * (fact.get((int) (n-1-i)) %M) )%M) %M;

            freq.set(A.charAt(i), (freq.get(A.charAt(i))-1));
        }
        ++ans;
        return (int)ans;
    }

    public static int findRank(String A){
        int M = 1000003;
        ArrayList<Character> set = new ArrayList<>();
        for (int i = 0; i < A.length(); i++) {
            set.add(A.charAt(i));
        }
        Collections.sort(set);
        int n = A.length();
        long ans = 0L;
        for (int i = 0; i < A.length(); i++) {
            for (int j = 0; j < set.size(); j++) {
                if(A.charAt(i)!=set.get(j)){
                    ans = (ans+ factorial(n-i-1,M))%M;
                }else {
                    set.remove(j);
                    break;
                }
            }
        }
        return (int)(ans+1);
    }
    public static int factorial(int A, int M){
        long ans=1L;
        for (int i = 2; i <=A; i++) {
            ans = (ans * i)%M;
        }
        ans = (ans<0)?ans+M:ans%M;
        return (int)ans;
    }

    //M is not Prime
    public static int computeNcRModeMWithOutPrime(int A, int B, int C){
        ArrayList<ArrayList<Long>> mat = new ArrayList<>();
        for (int i = 0; i <=A; i++) {
            mat.add(new ArrayList<>(Collections.nCopies(B+1,0L)));
        }
        for (int i = 0; i <=A; i++) {
            mat.get(i).set(0,1L);
        }

        for (int i = 1; i <=A; i++) {
            for (int j = 1; j <=B; j++) {
                long val = (mat.get(i-1).get(j) + mat.get(i-1).get(j-1))%C;
                mat.get(i).set(j, val);
            }
        }
//        System.out.println(mat);
        long ans =mat.get(A).get(B);
        return (int)ans;
    }

    //M is prime
    public static int computeNcRModeM(int A, int B, int C){
        long p =1L;
        for (int i = 2; i <=A; i++) {
            p = (p * i)%C;
        }
        p = (p<0)?p+C:p%C;
        int P = (int)p;

        long q= 1L;
        for (int i = 2; i <=(A-B); i++) {
            q = (q * i)%C;
        }
        q = (q<0)?q+C:q%C;
        int Q = (int)q;
        Q = pow(Q,C-2,C);

        long r = 1L;
        for (int i = 2; i <=B; i++) {
            r = (r*i)%C;
        }
        r = (r<0)?r+C:r%C;
        int R = (int)r;
        R = pow(R,C-2,C);

        long T = ((long) P * Q)%C;
        T = (T<0)?T+C:T%C;
        long ans =(T * R )%C;
        ans = (ans<0)?ans+C:ans%C;
        return (int)ans;
    }

    public static int luckyNumber(int A){
        ArrayList<Integer> spf = smallestPrimeFactor(A);
        int ans =0;
        for (int i = 1; i <=A; i++) {
            HashSet<Integer> p = new HashSet<>();
            int temp = i;
            while (temp != 1) {
                p.add(spf.get(temp));
                temp /= spf.get(temp);
            }
            if(p.size()==2) ans++;
        }
        return ans;

    }

    public static boolean isPrime(int A){
        int c=0;
        for (int i = 1; i <= Math.sqrt(A); i++) {
            if(A%i==0){
                if(i==A/i) c++;
                else c+=2;
            }
            if(c>2) break;
        }
        return c == 2;
    }
    public static ArrayList<Integer> primeSum(int A){
        ArrayList<Integer> ans = new ArrayList<>();
        int min=0,max=0;
        for (int i = 2; i <=A; i++) {
            if(isPrime(i) && isPrime(A-i)){
                min = i ;
                max = A-i;
                break;
            }
        }
        ans.add(min);
        ans.add(max);
        return ans;
    }

    public static ArrayList<Integer> countOfDivisors(ArrayList<Integer> A){
        ArrayList<Integer> result = new ArrayList<>();
        int max =Integer.MIN_VALUE;
        for (Integer integer : A) max = Math.max(max, integer);

        ArrayList<Integer> spf = smallestPrimeFactor(max);
        for (Integer value : A) {
            ArrayList<Integer> p = new ArrayList<>();
            int temp = value;
            while (temp != 1) {
                p.add(spf.get(temp));
                temp /= spf.get(temp);
            }
            int ans = 1, c = 0;
            int val = 0;
            if(!p.isEmpty())  val = p.get(0);
            for (Integer integer : p) {
                if (val == integer) {
                    c++;
                } else {
                    ans *= (c + 1);
                    c = 1;
                    val = integer;
                }
            }
            ans *=(c+1);
            result.add(ans);
        }

        return result;
    }
    public static ArrayList<Integer> smallestPrimeFactor(int a){
        ArrayList<Integer> spf = new ArrayList<>();
        for (int i = 0; i <=a; i++) {
            spf.add(i);
        }
        for (int i = 2; i <=Math.sqrt(a); i++) {
            if(i==spf.get(i)) {
                for (int j = i*i; j <=a; j+=i) {
                    if(spf.get(j)==j){
                        spf.set(j,i);
                    }
                }
            }
        }
        return spf;
    }

    public static ArrayList<Integer> primeNoTillGivenNoUsingBollen(int A){
        ArrayList<Boolean> p = new ArrayList<>(Collections.nCopies(A+1,true));
        for (int i = 2; i <=Math.sqrt(A); i++) {
            if(p.get(i)){
                for (int j = i*i; j <=A; j+=i) {
                    p.set(j,false);
                }
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 2; i < p.size(); i++) {
            if(p.get(i))    ans.add(i);
        }
        return ans;
    }

    public static ArrayList<Integer> primeNoTillGivenNo(int A){
        ArrayList<Integer> primeNo = new ArrayList<>();
        primeNo.add(2);
        for (int i = 3; i <=A; i++) {
            boolean flag = true;
            for (int j = 0; j < primeNo.size(); j++) {
                if(i%primeNo.get(j)==0){
                    flag = false;
                    break;
                }
            }
            if(flag) primeNo.add(i);
        }
        return primeNo;
    }

    public static ArrayList<Integer> allGDCPairNeedElm(ArrayList<Integer> A){
        HashMap<Integer,Integer> hm = new HashMap<>();
        ArrayList<Integer> ans = new ArrayList<>();
        A.sort(Collections.reverseOrder());
        for (int i = 0; i < A.size(); i++) {
            if(hm.containsKey(A.get(i)) && hm.get(A.get(i))>0){
                hm.put(A.get(i),hm.get(A.get(i))-1);
            }else {
                for (Integer a : ans) {
                    int gcd = gcd(a,A.get(i));
                    if(hm.containsKey(gcd)) hm.put(gcd,hm.get(gcd)+2);
                    else hm.put(gcd,2);
                }
                ans.add(A.get(i));
            }
        }
        return ans;
    }

    public static int divisorGame(int A, int B, int C){
        int gcdOfBC = gcd(B,C);
        int lcm = (B/gcdOfBC) *C;
        return A/lcm;
    }

    public static int cpFact(int A, int B){
        int x = 0;
        for (int i = 1; i < Math.sqrt(A); i++) {
            int f1,f2;
            if(A%i==0){
                 f1 = i;
                 f2 = A/i;
                 if(gcd(f2,B)==1)   x=Math.max(x,f2);
                 if(gcd(f1,B)==1)   x=Math.max(x,f1);
            }
        }
        return x;
    }

    public static int gcd(int A, int B){
        if(B==0) return A;
        return gcd(B, A%B );
    }

    public static int gcdOfArray(ArrayList<Integer> A){
        int ans =0;
        for (int i = 0; i < A.size(); i++) {
            ans = gcd(ans,A.get(i));
        }
        return ans;
    }

    public static boolean subSqeGCDIsOne(ArrayList<Integer> A){
        int ans =0;
        for (int i = 0; i < A.size(); i++) {
            ans = gcd(ans,A.get(i));
        }
        return ans==1;
    }

    public static int gcdOfMaxAfterDeleteOneElm(ArrayList<Integer> A){
        ArrayList<Integer> pfGcd = new ArrayList<>();
        int currPFGCD=0;
        for (int i = 0; i < A.size(); i++) {
            currPFGCD = gcd(currPFGCD,A.get(i));
            pfGcd.add(currPFGCD);
        }
        ArrayList<Integer> sfGcd = new ArrayList<>();
        int currSFGCD=0;
        for (int i = A.size()-1; i >=0; i--) {
            currSFGCD = gcd(currSFGCD,A.get(i));
            sfGcd.add(currSFGCD);
        }
        int max =0;
        for (int i = 0,j=A.size()-1; i < A.size(); i++,j--) {
            if(i==0) max= Math.max(max,gcd(0,sfGcd.get(j-1)));
            else if(i==A.size()-1) max=Math.max(max, gcd(pfGcd.get(i-1),0));
            else{
                max=Math.max(max, gcd(pfGcd.get(i-1),sfGcd.get(j-1)));
            }
        }
        return max;
    }

    public static String enumeratingGCD(String A, String B){
        if(A.equals(B)) return A;
        return "1";
    }

    public static int veryLargePower(int A, int B){
        int M = 1000000007;
        int P = M-1;
        long x = 1L;
        for (int i = 2; i <=B; i++) {
            x = (x*i)%P;
        }
        x = (x<0)?x+P:x%P;
        int X = (int)x;
        long ans = pow(A,X,M);
        ans = (ans<0)?ans+M:ans%M;
        return (int)ans;
    }

    private static int pairOfModZero(ArrayList<Integer> A, int B) {
        int M = 1000000007;
        HashMap<Integer,Long> hm = new HashMap<>();
        for (int i = 0; i < A.size(); i++) {
            int r = A.get(i)%B;
            if(hm.containsKey(r))   hm.put(r,hm.get(r)+1);
            else hm.put(r,1L);
        }
        long ans = 0L;
        if(hm.containsKey(0))
            ans =((hm.get(0)*(hm.get(0)-1))/2)%M;
        if(B%2==0 && hm.containsKey(B/2)){
            ans += ((hm.get(B/2) *(hm.get(B/2)-1) )/2)%M;
        }
        for (int i = 1; i < (B+1) / 2; i++) {
            long n = (hm.containsKey(i))?hm.get(i):0;
            long n1 = (hm.containsKey(B-i))?hm.get(B-i):0;
            ans += ((n * n1))%M;
        }
        return (int)(ans%M);
    }

    //Replace Arr[Arr[i]]
    public static ArrayList<Integer> rearrange(ArrayList<Integer> a) {
        int n = a.size();
        for (int i = 0; i < a.size(); i++) {
            a.set(i,a.get(i)*n);
        }

        for (int i = 0; i < a.size(); i++) {
            int old = a.get(a.get(i)/n)/n;
            a.set(i, a.get(i)+old);
        }
        for (int i = 0; i < a.size(); i++) {
            a.set(i,a.get(i)%n);
        }
        return a;
    }

    public static int pow(long A, int B, int C){
        if(A==0) return 0;
        long a = A;
        long ans = 1L;
        while (B>0){
            if(B%2==1){
                ans *= a;
                ans %= C;
            }
            a*=a;
            a%=C;
            B= B>>1;
        }
        ans = (ans+C)%C;
        return (int)ans;
    }

    public static int primeModuloInverse(int A, int B){
        int C = B;
        B = B-2;
        return pow(A,B,C);
    }

    private static int powRec(int A, int B, int C){
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

    public static int subArrayMaxAddSub(ArrayList<Integer> A){
        int ans =0;
        ArrayList<Integer> oddPf = new ArrayList<>();
        ArrayList<Integer> evenPf = new ArrayList<>();
        evenPf.add(A.get(0));
        oddPf.add(0);
        for (int i = 1; i < A.size(); i++) {
            if((i&1)==0){
                evenPf.add(evenPf.get(i-1)+A.get(i));
                oddPf.add(oddPf.get(i-1));
            }else {
                oddPf.add(oddPf.get(i-1)+A.get(i));
                evenPf.add(evenPf.get(i-1));
            }
        }

        for (int i = 0; i < A.size(); i++) {
            for (int j = i; j < A.size(); j++) {
                int even = evenPf.get(j)- ((i==0)?0:evenPf.get(i-1));
                int odd = oddPf.get(j)- ((i==0)?0:oddPf.get(i-1));
                int sum  = ((i&1)==0)?(even-odd):(odd-even);
                ans = Math.max(ans,sum);
            }
        }
        return ans;
    }

    public static int subArrayMaxAddSubWithKadane(ArrayList<Integer> A){
        int ans=0;
        int sum1=0,sum2=0;
        for (int i = 0; i < A.size(); i++) {
            if((i&1)==0){
                sum2 -= A.get(i);
            }else{
                sum2 = Math.max(sum2+A.get(i), A.get(i));
            }
            ans = Math.max(ans,sum2);
        }

        for (int i = 0; i < A.size(); i++) {
            if((i&1)==1){
                sum1 -= A.get(i);
            }else{
                sum1 = Math.max(sum1+A.get(i),A.get(i));
            }
            ans = Math.max(ans,sum1);
        }
        return ans;
    }
}
