package com.graph;

import java.util.*;

public class GraphProblem {
    static class Pair<T,T1>{
        T val1;
        T1 val2;
        public Pair(T v1, T1 v2){
            this.val1 = v1;
            this.val2=v2;
        }
    }
    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        GraphProblem gp = new GraphProblem();
//        int x = sc.nextInt();
//        int y = sc.nextInt();
        int N = sc.nextInt();
//        int R = sc.nextInt();
        ArrayList<Integer> E = new ArrayList<>();
        for (int i = 0; i <N; i++) {
            E.add(sc.nextInt());
        }
        ArrayList<Integer> F = new ArrayList<>();
        for (int i = 0; i <N; i++) {
            F.add(sc.nextInt());
        }
        /*int n = sc.nextInt();
        int C = sc.nextInt();
        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> t = new ArrayList<>();
            for (int j = 0; j < C; j++) {
                t.add(sc.nextInt());
            }
            A.add(t);
        }*/


        System.out.println(gp.damageRoad(E,F));
    }

    public int damageRoad(ArrayList<Integer> A, ArrayList<Integer> B) {
        //vertical=1
        //Horizontal=0
        int n = A.size(); //row-vertical
        int m = B.size(); //col-horizontal
        ArrayList<int[]> pair = new ArrayList<>();
        for (Integer integer : A) {
            pair.add(new int[]{integer, 1});
        }
        for (Integer integer : B) {
            pair.add(new int[]{integer, 0});
        }
        pair.sort((a,b)-> a[0]-b[0]);
        n++;
        m++;
        long ans = 0L;
        int M = 1000000007;
        for (int i = 0; i < pair.size(); i++) {
            if(pair.get(i)[1]==0){
                ans = (ans%M + (long) n *pair.get(i)[0]%M)%M;
                m--;
            }else{
                ans = (ans%M + (long) m *pair.get(i)[0]%M)%M;
                n--;
            }
        }
        return (int)ans;
    }

    public ArrayList<Integer> edgeInMST(int A, ArrayList<ArrayList<Integer>> B) {
        HashMap<String, Integer> pos = new HashMap<>();
        ArrayList<Integer> ans = new ArrayList<>(Collections.nCopies(B.size(),0));
        for (int i = 0; i < B.size(); i++) {
            pos.put(B.get(i).get(0)+"@"+B.get(i).get(1), i);
        }

        B.sort((a,b)-> (Objects.equals(a.get(2), b.get(2)))?a.get(0)-b.get(0):a.get(2)-b.get(2));
        int[] comp = new int[A+1];
        for (int i = 0; i <comp.length; i++) {
            comp[i] = i;
        }
        for (int i = 0; i < B.size();) {
            int u = B.get(i).get(0);
            int v = B.get(i).get(1);
            int w = B.get(i).get(2);
            int j =i;
            while (j<B.size() && B.get(j).get(2)==w) {
                if (findComp(B.get(j).get(0),comp) != findComp(B.get(j).get(1),comp)) {
                    ans.set(pos.get(B.get(j).get(0) + "@" + B.get(j).get(1)), 1);
                }
                j++;
            }
            j=i;
            while (j<B.size() && B.get(j).get(2)==w) {
                if (findComp(B.get(j).get(0),comp) != findComp(B.get(j).get(1),comp)) {
                    int cu = findComp(B.get(j).get(0),comp);
                    int cv = findComp(B.get(j).get(1),comp);
                    comp[Math.max(cu, cv)] = comp[Math.min(cu, cv)];
                }
                j++;
            }
            i=j;
        }
        return ans;
    }

    public int knight(int A, int B, int C, int D, int E, int F) {
        boolean[][] visited = new boolean[A][B];
        int[][] depth = new int[A][B];
        int[] xa = new int[]{-1,1,-1,1,2,2,-2,-2};
        int[] ya = new int[]{2,2,-2,-2,-1,1,-1,1};
        Queue<Pair<Integer,Integer>> q = new LinkedList<>();
        q.add(new Pair<>(C-1,D-1));
        visited[C-1][D-1]=true;
        depth[C-1][D-1]=0;
        while (!q.isEmpty()){
            Pair<Integer,Integer> p = q.poll();
            int x = p.val1;
            int y = p.val2;
            for (int i = 0; i < xa.length; i++) {
                if(knightAbleToGo(A,B,x+xa[i], y+ya[i], visited)){
                    if(x+xa[i]==E-1 && y+ya[i]==F-1)    return 1+depth[x][y];
                    visited[x+xa[i]][y+ya[i]]=true;
                    depth[x+xa[i]][y+ya[i]]= 1+ depth[x][y];
                    q.add(new Pair<>(x+xa[i], y+ya[i]));
                }
            }
        }
        return -1;
    }
    private boolean knightAbleToGo(int A, int B, int x, int y, boolean[][] vis) {
        return x>=0 && x<A && y>=0 && y<B && !vis[x][y];
    }

    public int reverseEdges(int A, ArrayList<ArrayList<Integer>> B) {
        HashMap<Integer, ArrayList<Integer>> gp = createAdjList(A,B);
        return noOfComponent(A,gp);
    }

    public int noOfComponent(int A, HashMap<Integer,ArrayList<Integer>> gp){
        boolean[] vis = new boolean[A+1];
        int ans=0;
        for (int i = 1; i <=A; i++) {
            if(!vis[i]){
                Set<Integer> compList = new HashSet<>();
                dfsForComponent(gp, vis, i, compList);
            }
        }
        return ans;
    }

    public ArrayList<ArrayList<Integer>> distanceOfNearestCell(ArrayList<ArrayList<Integer>> A) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        int col = A.get(0).size();
        int row = A.size();
        for (int i = 0; i < row; i++) {
            ans.add(new ArrayList<>(Collections.nCopies(col,Integer.MAX_VALUE)));
        }
        boolean[][] vis = new boolean[row][col];
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(A.get(i).get(j)==1){
                    ans.get(i).set(j,0);
                    q.add(new int[]{i,j});
                }
            }
        }
        int[] x = new int[]{-1,1,0,0};
        int[] y = new int[]{0,0,-1,1};
        while (!q.isEmpty()){
            int[] pair = q.poll();
            int r = pair[0];
            int c = pair[1];
            vis[r][c]=true;
            for (int i = 0; i < x.length; i++) {
                if(ableToTravel(ans,vis,r+x[i], c+y[i]) && ans.get(r).get(c)+1<ans.get(r+x[i]).get(c+y[i])){
                    q.add(new int[]{r+x[i], c+y[i]});
                    ans.get(r+x[i]).set(c+y[i], ans.get(r).get(c)+1);
                }
            }
        }
        return ans;
    }
    private boolean ableToTravel(ArrayList<ArrayList<Integer>> ans, boolean[][] vis, int r, int c) {
        return (r>=0 && r<ans.size() && c>=0 && c< ans.get(0).size() && !vis[r][c]);
    }

    public String validPath(int x, int y, int N, int R, ArrayList<Integer> E, ArrayList<Integer> F) {
        int[][] matrix = new int[x+1][y+1];
        //matrix filling
        for (int i = 0; i <=x; i++) {
            for (int j = 0; j <=y; j++) {
                for (int k = 0; k < E.size(); k++) {
                    if( (E.get(k)-i)*(E.get(k)-i) + (F.get(k)-j)*(F.get(k)-j) <= R*R ){
                        matrix[i][j]=1;
                        break;
                    }
                }
            }
        }
        if(matrix[0][0]!=0 || matrix[x][y]!=0)  return "NO";
        int[] xAxis = new int[]{-1,1,0,0,-1,-1,1,1};
        int[] yAxis = new int[]{0,0,-1,1,-1,1,-1,1};
        matrix[0][0]=1;
        dfsForValidPath(matrix,0,0,x+1,y+1, xAxis, yAxis);
        return (matrix[x][y]==1)?"YES":"NO";
    }
    private void dfsForValidPath(int[][] matrix, int i, int j, int x, int y, int[] xAxis, int[] yAxis) {
        if(i==x && j==y) {
            matrix[x][y]=1;
            return;
        }
        for (int k = 0; k < xAxis.length; k++) {
            if(ableToGo(matrix,i+xAxis[k], j+yAxis[k], x, y)){
                matrix[i+xAxis[k]][j+yAxis[k]]=1;
                dfsForValidPath(matrix, i+xAxis[k], j+yAxis[k], x, y, xAxis, yAxis);
            }
        }
    }
    private boolean ableToGo(int[][] matrix, int i, int j, int x, int y) {
        return (i>=0 && i<x && j>=0 && j<y && matrix[i][j]!=1);
    }

    public int constructRoads(int A, ArrayList<ArrayList<Integer>> B) {
        long ans = 0L;
        int M = 1000000007;
        HashMap<Integer, ArrayList<Integer>> gp = createAdjList(A,B);
        int[] comp = checkBipartiteGraph(A,B, gp);
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        for (int i = 1; i <=A; i++) {
            if(comp[i]==0)  set1.add(i);
            else set2.add(i);
        }
        for(int v : set1){
            int size = gp.get(v).size();
            ans = (ans + set2.size()-size)%M;
        }
        return (int)ans;
    }

    public int[] checkBipartiteGraph(int A, ArrayList<ArrayList<Integer>> B, HashMap<Integer, ArrayList<Integer>> gp) {
//        HashMap<Integer, ArrayList<Integer>> gp = createAdjList(A,B);

        Queue<Integer> q = new LinkedList<>();
        int[] com = new int[A+1];
        Arrays.fill(com,-1);
        for (int i = 1; i <=A; i++) {
            if(com[i]==-1) {
                q.add(i);
                com[i] = 0;

                while (!q.isEmpty()) {
                    int v = q.poll();
                    int set = com[v];
                    for (int next : gp.get(v)) {
                        if (com[next] == set) return com;
                        else if (com[next] == (set ^ 1)) {
                        } else {
                            q.add(next);
                            com[next] = set ^ 1;
                        }
                    }
                }
            }
        }
        return com;
    }
    public HashMap<Integer, ArrayList<Integer>> createAdjList(int A, ArrayList<ArrayList<Integer>> B){
        HashMap<Integer, ArrayList<Integer>> gp = new HashMap<>();
        for (int i = 1; i <=A; i++) {
            gp.put(i,new ArrayList<>());
        }
        for (int i = 0; i < B.size(); i++) {
            ArrayList<Integer> t = gp.get(B.get(i).get(0));
            t.add(B.get(i).get(1));
            gp.put(B.get(i).get(0), t);
            /*t = gp.get(B.get(i).get(1));
            t.add(B.get(i).get(0));
            gp.put(B.get(i).get(1), t);*/
        }
        return gp;
    }

    public int shortestPathInMaze(ArrayList<ArrayList<Integer>> A, ArrayList<Integer> B, ArrayList<Integer> C) {
        int n=A.size(), m = A.get(0).size();
        int[] x = new int[]{-1,1,0,0};
        int[] y = new int[]{0,0,-1,1};
        boolean[][][] vis = new boolean[n][m][4];
        Queue<Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>> q = new LinkedList<>();

        for (int i = 0; i < 4; i++) {
            if(B.get(0)+x[i]>=0 && B.get(0)+x[i]<n && B.get(1)+y[i]>=0 && B.get(1)+y[i]<m && A.get(B.get(0)+x[i]).get(B.get(1)+y[i])==0)
                q.add(new Pair<>( new Pair<>(B.get(0)+x[i],B.get(1)+y[i]), new Pair<>(i,1)));
        }

        while (!q.isEmpty()){
            Pair<Pair<Integer,Integer>,Pair<Integer,Integer>> p = q.poll();
            Pair<Integer,Integer> node = p.val1;
            Pair<Integer,Integer> dirWithAns = p.val2;
            int dir = dirWithAns.val1;
            int ans = dirWithAns.val2;
            vis[node.val1][node.val2][dir] = true;
            //If destination
            if(node.val1.equals(C.get(0)) && node.val2.equals(C.get(1))
                    && isAbleToStop(A, node.val1+x[dir], node.val2+y[dir])){
                return dirWithAns.val2;
            }

            if(isAbleTogoNext(A, vis, node.val1+x[dir], node.val2+y[dir], dir)){
                q.add(new Pair<>( new Pair<>(node.val1+x[dir], node.val2+y[dir]), new Pair<>(dir,ans+1)));
            }else{
                for (int i = 0; i < 4; i++) {
                    if(i!=dir && isAbleTogoNext(A, vis, node.val1+x[i], node.val2+y[i], i)){
                        q.add(new Pair<>( new Pair<>(node.val1+x[i], node.val2+y[i]), new Pair<>(i,ans+1) ));
                    }
                }
            }
        }
        return -1;
    }

    private boolean isAbleToStop(ArrayList<ArrayList<Integer>> A,  int x, int y) {
        return x<0 || x>=A.size() || y<0 || y>=A.get(0).size() || A.get(x).get(y) == 1;
    }
    private boolean isAbleTogoNext(ArrayList<ArrayList<Integer>> A, boolean[][][] vis, int x, int y, int dir) {
        return x>=0 && x<A.size() && y>=0 && y<A.get(0).size() && A.get(x).get(y) != 1 && !vis[x][y][dir];
    }

    public ArrayList<ArrayList<Integer>> floydWarshallAlgo(ArrayList<ArrayList<Integer>> A) {
        int n = A.size();
        int[][][] dp = new int[n+1][n+1][2];
        for (int i = 0; i <=n; i++) {
            for (int j = 0; j <=n; j++) {
                Arrays.fill(dp[i][j],Integer.MAX_VALUE);
            }
        }
        for (int i = 1; i <=n; i++) {
            for (int j = 1; j <=n; j++) {
                if(A.get(i-1).get(j-1)!=-1)
                    dp[i][j][0] = A.get(i-1).get(j-1);
            }
        }
        for (int k = 1; k <=n; k++) {
            for (int i = 1; i <=n; i++) {
                for (int j = 1; j <=n; j++) {
//                    dp[i][j][k%2] = Math.min(dp[i][j][(k-1)%2], dp[i][k][(k-1)%2]+ dp[k][j][(k-1)%2]);
                    dp[i][j][k%2] = Math.min(dp[i][j][k%2], dp[i][j][(k-1)%2]);
                    if(dp[i][k][(k-1)%2]!=Integer.MAX_VALUE && dp[k][j][(k-1)%2]!=Integer.MAX_VALUE){
                        dp[i][j][k%2] = Math.min(dp[i][j][k%2], dp[i][k][(k-1)%2]+ dp[k][j][(k-1)%2]);
                    }
                }
            }
        }
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (int i = 1; i <=n; i++) {
            ArrayList<Integer> t = new ArrayList<>();
            for (int j = 1; j <=n; j++) {
                t.add(dp[i][j][n%2]==Integer.MAX_VALUE?-1:dp[i][j][n%2]);
            }
            ans.add(t);
        }
        return ans;
    }

    //TODO learn prims algo also
    //Kruskal Algo
    public int commutableIsland(int A, ArrayList<ArrayList<Integer>> B) {
        long ans=0L;
        int M = 1000000007;
        B.sort((b1, b2) -> b1.get(2) - b2.get(2));
        int[] parent = new int[A+1];
        for (int i = 0; i < parent.length; i++) {
            parent[i]=i;
        }
        for (int i = 0; i < B.size(); i++) {
            int w = B.get(i).get(2);
            int u = B.get(i).get(0);
            int v = B.get(i).get(1);
            int compU = findComp(u,parent);
            int compV = findComp(v,parent);
            if(compU!=compV){
                parent[Math.max(compU,compV)] = parent[Math.min(compU,compV)];
                ans = (ans +w)%M;
            }
        }
        return (int)ans;
    }
    public int findComp(int node, int[] parent){
        if(node==parent[node])  return node;
        parent[node] = findComp(parent[node], parent);
        return parent[node];
    }

    public int batches(int A, ArrayList<Integer> B, ArrayList<ArrayList<Integer>> C, int D) {
        HashMap<Integer,ArrayList<Integer>> gp = new HashMap<>();
        for (int i = 1; i <=A; i++) {
            gp.put(i,new ArrayList<>());
        }
        for (ArrayList<Integer> c : C) {
            gp.get(c.get(0)).add(c.get(1));
            gp.get(c.get(1)).add(c.get(0));
        }
        return noOfComponent(A,B,gp,D);
    }
    public int noOfComponent(int A, ArrayList<Integer> B, HashMap<Integer,ArrayList<Integer>> gp, int D){
        boolean[] vis = new boolean[A+1];
        int ans=0;
        for (int i = 1; i <=A; i++) {
            if(!vis[i]){
                Set<Integer> compList = new HashSet<>();
                dfsForComponent(gp, vis, i, compList);
                int val=0;
                for (Integer integer : compList) {
                    val += B.get(integer-1);
                }
                if(D<=val)  ans++;
            }
        }
        return ans;
    }
    private void dfsForComponent(HashMap<Integer, ArrayList<Integer>> gp, boolean[] vis, int s, Set<Integer> compList) {
        if(vis[s])  return;
        vis[s]=true;
        compList.add(s);
        for (int i = 0; i < gp.get(s).size(); i++) {
            int v = gp.get(s).get(i);
            compList.add(v);
            dfsForComponent(gp, vis, v, compList);
        }
    }

    public int cycleDetectInDirected(int A, ArrayList<Integer> B, ArrayList<Integer> C) {
        HashMap<Integer,ArrayList<Integer>> gp = new HashMap<>();
        int[] incoming = new int[A+1];
        for (int i = 1; i <=A; i++) {
            gp.put(i,new ArrayList<>());
        }
        for (int i=0; i<B.size(); i++) {
            gp.get(B.get(i)).add(C.get(i));
        }
        for (int i = 1; i <=A; i++) {
            for (int j = 0; j < gp.get(i).size(); j++) {
                incoming[gp.get(i).get(j)]++;
            }
        }
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i = 1; i < incoming.length; i++) {
            if(incoming[i]==0){
                q.add(i);
            }
        }
        while (!q.isEmpty()){
            int node = q.poll();
            for(int next : gp.get(node)){
                incoming[next]--;
                if(incoming[next]==0){
                    q.add(next);
                }
            }
        }
        for (int i = 1; i <=A; i++) {
            if(incoming[i]!=0)  return 0;
        }
        return 1;
    }

    public ArrayList<Integer> topologicalSort(int A, ArrayList<ArrayList<Integer>> B) {
        int[] incoming = new int[A+1];
        HashMap<Integer,ArrayList<Integer>> gp = new HashMap<>();
        for (int i = 1; i <=A; i++) {
            gp.put(i,new ArrayList<>());
        }
        for (ArrayList<Integer> uv : B) {
            gp.get(uv.get(0)).add(uv.get(1));
        }
        for (int i = 1; i <=A; i++) {
            for (int j = 0; j < gp.get(i).size(); j++) {
                incoming[gp.get(i).get(j)]++;
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i = 1; i < incoming.length; i++) {
            if(incoming[i]==0){
                q.add(i);
            }
        }
        while (!q.isEmpty()){
            int node = q.poll();
            ans.add(node);
            for(int next : gp.get(node)){
                incoming[next]--;
                if(incoming[next]==0){
                    q.add(next);
                }
            }
        }
        return ans;
    }

    public ArrayList<Integer> dijsktra(int A, ArrayList<ArrayList<Integer>> B, int C) {
        Map<Integer,ArrayList<Pair<Integer,Integer>>> gp = new HashMap<>();
        for (int i = 0; i < A; i++) {
            gp.put(i, new ArrayList<>());
        }
        for (int i = 0; i < B.size(); i++) {
            gp.get(B.get(i).get(0)).add(new Pair<>(B.get(i).get(2),B.get(i).get(1)));
            gp.get(B.get(i).get(1)).add(new Pair<>(B.get(i).get(2),B.get(i).get(0)));
        }
        ArrayList<Integer> ans = new ArrayList<>(Collections.nCopies(A,-1));
        int[] time = new int[A];
        Arrays.fill(time, Integer.MAX_VALUE);
        time[C]=0;
        PriorityQueue<Pair<Integer,Integer>> min_heap = new PriorityQueue<>((a,b)-> a.val1-b.val1);
        min_heap.add(new Pair<>(0,C));
        while (!min_heap.isEmpty()){
            Pair<Integer,Integer> p = min_heap.poll();
            int t = p.val1;
            int u = p.val2;
            if(t<=time[u]){
                ans.set(u,t);
                for(Pair<Integer,Integer> node: gp.get(u)){
                    int t1 = node.val1;
                    int n = node.val2;
                    if(t1+t<time[n]){
                        time[n]=t1+t;
                        min_heap.add(new Pair<>(time[n],n));
                    }
                }
            }
        }
        return ans;
    }

    public int isCyclePresent(int A, ArrayList<ArrayList<Integer>> B){
        HashMap<Integer,ArrayList<Integer>> gp = new HashMap<>();
        for (int i = 1; i <=A; i++) {
            gp.put(i,new ArrayList<>());
        }
        for (ArrayList<Integer> uv : B) {
            gp.get(uv.get(0)).add(uv.get(1));
            gp.get(uv.get(1)).add(uv.get(0));
        }
        boolean[] visited= new boolean[A+1];
        for (int i = 1; i <=A; i++) {
            if(!visited[i])
                if( dfsForCycle(gp, visited, i,-1)) return 1;
        }
        return 0;
    }

    private boolean dfsForCycle(HashMap<Integer, ArrayList<Integer>> gp, boolean[] visited, int curr, int parent) {
        visited[curr]=true;
        for(int it : gp.get(curr)){
            if(!visited[it]) {
                if (dfsForCycle(gp, visited, it, curr)) return true;
            }
            else{
                if(it != parent)    return true;
            }
        }
        return false;
    }

    public int rottenOrange(ArrayList<ArrayList<Integer>> A){
        Deque<Pair<Integer,Integer>> q   = new LinkedList<>();
        int n = A.size();
        int m = A.get(0).size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(A.get(i).get(j)==2){
                    q.add(new Pair<>(i,j));
                }
            }
        }
        q.add(null);
        int count=0;
        int[] x = {-1,1,0,0};
        int[] y = {0,0,-1,1};
        while (!q.isEmpty()){
            Pair<Integer,Integer> p = q.poll();
            if(p==null) {
                if(!q.isEmpty()) {
                    count++;
                    q.add(null);
                }
            }
            else{
                for (int i = 0; i < x.length; i++) {
                    int a= p.val1 +x[i], b = p.val2+y[i];
                    if(a>=0&&a<n && b>=0&& b<m && A.get(a).get(b)==1){
                        A.get(a).set(b,2);
                        q.add(new Pair<>(a,b));
                    }
                }
            }
        }
        for (ArrayList<Integer> integers : A) {
            for (int j = 0; j < m; j++) {
                if (integers.get(j) == 1) return -1;
            }
        }
        return count;
    }

    public int NoOfIsland(ArrayList<ArrayList<Integer>> A) {
        int count=0;
        int n=A.size();
        int m=A.get(0).size();
        int[] x= {-1,-1,-1,0,0,1,1,1};
        int[] y= {-1,0, 1,-1,1,-1,0,1};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(A.get(i).get(j)==1){
                    count++;
                    dfsForIsland(A,i,j,x,y);
                }
            }
        }
        return count;
    }

    private void dfsForIsland(ArrayList<ArrayList<Integer>> A, int i, int j, int[] x, int[] y) {
        if(i<0||i>=A.size() || j<0||j>=A.get(0).size() || A.get(i).get(j)==0)   return;
        A.get(i).set(j,0);
        for (int k = 0; k < x.length; k++) {
            dfsForIsland(A,i+x[k],j+y[k],x,y);
        }
    }

    private int ableToReachStartToEnd(int A, ArrayList<ArrayList<Integer>> B) {
        ArrayList<ArrayList<Integer>> gp = new ArrayList<>();
        for (int i = 0; i <=A; i++) {
            gp.add(new ArrayList<>());
        }
        for (ArrayList<Integer> uv : B) {
            gp.get(uv.get(0)).add(uv.get(1));
        }
        int[] visited = new int[A+1];
        Queue<Integer> q = new LinkedList<Integer>();
        visited[1]=1;
        q.add(1);
        while (q.size()>0 && visited[A]!=1){
            int curr = q.poll();
            visited[curr]=1;
            ArrayList<Integer> ableToGo = gp.get(curr);
            for(int next : ableToGo){
                if(next==A)    return 1;
                if(visited[next]!=1){
                    q.add(next);
                    visited[next]=1;
                }
            }
        }
        return visited[A];
    }

    public int ableToReachSourceToDestination(ArrayList<Integer> A, final int Dest, final int Soc) {
        HashMap<Integer, ArrayList<Integer>> gp = new HashMap<>();
        createAdjList(gp, A);
        int n = A.size();
        boolean[] visited = new boolean[n+1];
        Queue<Integer> q = new LinkedList<>();
        q.add(Soc);
        visited[Soc]=true;
        while (!q.isEmpty()){
            int curr = q.poll();
            visited[curr]=true;
            ArrayList<Integer> ableToGoNext = gp.get(curr);
            for(int next : ableToGoNext){
                if(!visited[next]){
                    if(next==Dest) return 1;
                    visited[next] =true;
                    q.add(next);
                }
            }
        }
        return visited[Dest]?1:0;
    }
    private void createAdjList(HashMap<Integer, ArrayList<Integer>> gp, ArrayList<Integer> A) {
        int n = A.size();
        for (int i = 0; i <=n; i++) {
            gp.put(i, new ArrayList<>());
        }
        for (int i = 1; i <A.size(); i++) {
            gp.get(A.get(i)).add(i+1);
        }
    }
}
