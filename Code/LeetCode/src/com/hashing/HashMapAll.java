package com.hashing;

import java.util.*;

public class HashMapAll {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int n = sc.nextInt();
//        String t = sc.nextLine();
        ArrayList<String> A = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            A.add(sc.nextLine());
        }
//        int k = sc.nextInt();

        /*ArrayList<Integer> A = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            A.add(sc.nextInt());
        }*/
        System.out.println(isValidSudoku(A));
    }

    private static boolean pairSumKExist(ArrayList<Integer> A, int k) {
        HashMap<Integer,Integer> hm = new HashMap<>();
        for (int i = 0; i < A.size(); i++) {
            if(!hm.containsKey(A.get(i))){
                hm.put(A.get(i),1);
            }else{
                hm.put(A.get(i),hm.get(A.get(i))+1);
            }
        }

        for (int i = 0; i < A.size(); i++) {
            int x = k -A.get(i);
            if(A.get(i)==x && hm.get(x)>=2) return true;
            else if(hm.containsKey(k-A.get(i)))  return true;
        }
        return false;
    }

    private static ArrayList<Integer> pairSumKExist2(ArrayList<Integer> A, int k){
        /*HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < A.size(); i++) {
            if(set.contains(k-A.get(i))){
                return true;
            }else set.add(A.get(i));
        }
        return false;*/
        int i1=0,i2=0;
        HashMap<Integer,Integer> hm = new HashMap<>();
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < A.size(); i++) {
            if(hm.containsKey(k-A.get(i))){
                i1= hm.get(k-A.get(i))+1;
                i2=i+1;
                ans.add(i1);
                ans.add(i2);
                break;
            }else if(!hm.containsKey(A.get(i))) hm.put(A.get(i),i);

        }
        return ans;
    }

    private static ArrayList<Integer> DistinctNoInWindow(ArrayList<Integer> A, int w){
        HashMap<Integer,Integer> hm = new HashMap<>();
        ArrayList<Integer> r = new ArrayList<>();
        int i=0,j=w;
        for (i = 0; i < w; i++) {
            if(hm.containsKey(A.get(i)))    hm.put(A.get(i), hm.get(A.get(i))+1);
            else hm.put(A.get(i),1);
        }
        i=0;
        for (int l = 0; l < A.size()-w; l++) {
            r.add(hm.keySet().size());

            if(hm.get(A.get(i))==1) hm.remove(A.get(i));
            else hm.put(A.get(i), hm.get(A.get(i))-1);
            i++;

            if(hm.containsKey(A.get(j)))  hm.put(A.get(j), hm.get(A.get(j))+1);
            else hm.put(A.get(j),1);
            j++;

        }
        r.add(hm.keySet().size());
        return r;
    }

    private static ArrayList<Integer> commonElement(ArrayList<Integer> A, ArrayList<Integer> B) {
        HashMap<Integer,Integer> hm = new HashMap<>();
        ArrayList<Integer> ans = new ArrayList<>();
        for (Integer integer : A) {
            if (hm.containsKey(integer)) hm.put(integer, hm.get(integer) + 1);
            else hm.put(integer, 1);
        }
        for (Integer integer : B) {
            if (hm.containsKey(integer) && hm.get(integer) >= 1) {
                ans.add(integer);
                hm.put(integer, hm.get(integer) - 1);
            }
        }
        return ans;
    }

    private static int firstRepeatElement(ArrayList<Integer> A){
        HashMap<Integer,Integer> hm = new HashMap<>();
        for (Integer i : A) {
            if(hm.containsKey(i))   hm.put(i,hm.get(i)+1);
            else hm.put(i,1);
        }
        for (Integer i : A) {
            if(hm.containsKey(i) && hm.get(i)>=2) return i;
        }
        return -1;
    }

    private static ArrayList<Integer> lsZero(ArrayList<Integer> A){
        HashMap<Integer,Integer> hm = new HashMap<>();
        ArrayList<Integer> pf = new ArrayList<>();
        ArrayList<Integer> r = new ArrayList<>();
        int max=0;
        pf.add(0);
        for (int i=1; i<=A.size(); i++) {
            pf.add(pf.get(i-1)+A.get(i-1));
        }
        for (int i=0; i<pf.size(); i++) {
            if(hm.containsKey(pf.get(i))) {
                if( i-hm.get(pf.get(i))>max) {
                    max = i - hm.get(pf.get(i));
                    r.clear();
                    for (int j = hm.get(pf.get(i)); j < i; j++) {
                        r.add(A.get(j));
                    }
                }
            }
            else hm.put(pf.get(i),i);
        }
        return r;
    }

    private static int subArrayWithZeroSumExist(ArrayList<Integer> A){
        HashSet<Long> hm = new HashSet<>();
        ArrayList<Long> pf = new ArrayList<>();

        pf.add(0L);
        for (int i=1; i<=A.size(); i++) {
            pf.add(pf.get(i-1)+A.get(i-1));
        }
        for (Long elmPf : pf) {
            if (hm.contains(elmPf)) return 1;
            else hm.add(elmPf);
        }
        return 0;
    }

    private static int minDistantOfSameElm(ArrayList<Integer> A){
        HashMap<Integer,Integer> hm =new HashMap<>();
        int min =Integer.MAX_VALUE;
        boolean flag=false;
        for (int i = 0; i < A.size(); i++) {
            if(hm.containsKey(A.get(i))){
                flag=true;
                min = Math.min(min,i-hm.get(A.get(i)));
            }else   hm.put(A.get(i),i);
        }
        return (flag)?min:-1;
    }

    private static int getSum(int A, int B, ArrayList<Integer> C) {
        HashMap<Integer,Integer> hm = new HashMap<>();
        int sum=0;
        boolean flag=false;
        for (int i = 0; i < C.size(); i++) {
            if(!hm.containsKey(C.get(i))){
                hm.put(C.get(i),1);
            }else{
                hm.put(C.get(i),hm.get(C.get(i))+1);
            }
        }
        for (Map.Entry<Integer, Integer> entity : hm.entrySet()) {
            if(entity.getValue()==B){
                flag = true;
                sum += entity.getKey()%1000000007;
            }
        }
        return flag ?sum:-1;
    }

    private static int ableToMakePalindrome(String A){
        HashMap<Character,Integer> hm = new HashMap<>();
        for (int i = 0; i < A.length(); i++) {
            if(hm.containsKey(A.charAt(i))) hm.put(A.charAt(i),hm.get(A.charAt(i))+1);
            else hm.put(A.charAt(i),1);
        }
        boolean oddFlag = true;
        for (Map.Entry<Character,Integer> entry : hm.entrySet()) {
            if((A.length()&1)==1){
                if((entry.getValue()&1)==1 && oddFlag)
                    oddFlag =false;
                else if((entry.getValue()&1)==1 && !oddFlag)
                    return 0;
            }else{
                if((entry.getValue()&1)==1)
                    return 0;
            }
        }
        return 1;
    }

    private static int colorful(int A) {
        ArrayList<Integer> digit = new ArrayList<>();
        while (A!=0){
            digit.add(A%10);
            A=A/10;
        }
        Collections.reverse(digit);
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < digit.size(); i++) {
            int p = 1;
            for (int j = i; j < digit.size(); j++) {
                p *= digit.get(j);
                if(set.contains(p)) return 0;
                else set.add(p);
            }
        }
        return 1;

    }

    private static HashSet<Integer> factors(int r) {
        HashSet<Integer> factor=new HashSet<>();
        for (int i = 1; i <= Math.sqrt(r); i++) {
            if(r%i==0) {
                int f1 = r/i;
                int f2 = r/f1;
                factor.add(f1);
                factor.add(f2);
            }
        }
        return factor;
    }

    //Hashing-2

    private static ArrayList<Integer> subarrayWithGivenSum(ArrayList<Integer> A, int B) {
        ArrayList<Long> pf = new ArrayList<>();
        pf.add(0L);
        for (int i = 1; i <= A.size(); i++) {
            pf.add(pf.get(i-1)+A.get(i-1));
        }

        HashMap<Long,Integer> hm = new HashMap<>();
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < pf.size(); i++) {
            if(hm.containsKey(pf.get(i)-B)){
                for (int j = hm.get(pf.get(i)-B); j < i; j++) {
                    ans.add(A.get(j));
                }
                break;
            }else   hm.put(pf.get(i),i);
        }
        if(ans.isEmpty())   ans.add(-1);
        return ans;
    }

    //TODO wrong need to fix it
    private static int subarraySum(int[] A, int k) {
        int ans=0;
        ArrayList<Integer> pf = new ArrayList<>();
        pf.add(0);
        for (int i = 1; i <= A.length; i++) {
            pf.add(pf.get(i-1)+A[i-1]);
        }

        HashSet<Integer> hm = new HashSet<>();
        for (int i = 0; i < pf.size(); i++) {
            if(hm.contains(pf.get(i)-k)){
                hm.add(pf.get(i));
                ans++;
            }else   hm.add(pf.get(i));
        }

        return ans;
    }

    private static int diffPossible(final List<Integer> A, int k){
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < A.size(); i++) {
            if(set.contains(Math.abs(A.get(i)+k)) || set.contains(A.get(i)-k)) return 1;
            else set.add(A.get(i));
        }
        return 0;
    }

    private static int isDictionary(ArrayList<String> A, String B){
        HashMap<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < B.length(); i++) {
            hm.put(B.charAt(i),i);
        }

        for (int i = 0; i < A.size()-1; i++) {
            if(!checkChar(A.get(i),A.get(i+1),hm,0)){
                return 0;
            }
        }
        return 1;
    }

    private static boolean checkChar(String s, String s1, HashMap<Character, Integer> hm, int i) {
        if(i>=s1.length() && i<s.length())   return false;
        if( (i==s.length() && i<=s1.length()) ) return true;
        if(Objects.equals(hm.get(s.charAt(i)), hm.get(s1.charAt(i)))){
            return checkChar(s,s1,hm,i+1);
        }else if(hm.get(s.charAt(i))< hm.get(s1.charAt(i))){
            return true;
        }else return false;
    }

    private static int pairWithXor(ArrayList<Integer> A, int B){
        HashSet<Integer> set = new HashSet<>();
        int ans =0;
        for (int i = 0; i < A.size(); i++) {
            if(set.contains(A.get(i)^B)) ans++;
            else set.add(A.get(i));
        }
        return ans;
    }

    private static int isValidSudoku(final List<String> A) {
        //Row duplicate Check
        for (int i = 0; i < A.size(); i++) {
            HashSet<Integer> row = new HashSet<>();
            for (int j = 0; j < A.get(i).length(); j++) {
                if(A.get(i).charAt(j)!='.') {
                    if (row.contains(Integer.parseInt(String.valueOf(A.get(i).charAt(j))))) return 0;
                    else row.add(Integer.parseInt(String.valueOf(A.get(i).charAt(j))));
                }
            }
        }
        //Col Duplicate check
        for (int i = 0; i < A.get(0).length(); i++) {
            HashSet<Integer> col = new HashSet<>();
            for (int j = 0; j < A.size(); j++) {
                if(A.get(j).charAt(i)!='.') {
                    if (col.contains(Integer.parseInt(String.valueOf(A.get(j).charAt(i))))) return 0;
                    else col.add(Integer.parseInt(String.valueOf(A.get(j).charAt(i))));
                }
            }
        }

        //Square check
        for (int p = 0; p < 9; p+=3) {

            for (int q = 0; q < 9; q += 3) {
                HashSet<Integer> set = new HashSet<>();
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (A.get(i+p).charAt(j + q) != '.') {
                            if (set.contains(Integer.parseInt(String.valueOf(A.get(i+p).charAt(j + q))))) return 0;
                            else set.add(Integer.parseInt(String.valueOf(A.get(i+p).charAt(j + q))));
                        }
                    }
                }
            }
        }
        return 1;
    }

}
