package CHelper.src.algo;

import java.util.*;

public class graphUnweightMatrix {
    private boolean[][] edges;
    private int n;

    public graphUnweightMatrix(int nodes){
        n = nodes;
        edges = new boolean[n][n];
    }

    public void addUndirectedEdge(int start, int end){
        edges[start][end] = true;
        edges[end][start] = true;
    }

    public void addDirectedEdge(int start, int end){
        edges[start][end] = true;
    }

    public boolean getEdge(int i, int j){
        return edges[i][j];
    }

    private StringBuilder Traversal;
    public String BFS(int startIndex){
        Traversal = new StringBuilder();

        boolean visited[] = new boolean[n];
        LinkedList<Integer> queue = new LinkedList<>();

        queue.add(startIndex);
        visited[startIndex] = true;

        while(!queue.isEmpty()){
            startIndex = queue.poll();
            Traversal.append(startIndex);
            Traversal.append(" ");
            for(int i=0; i<n; i++){
                if(!visited[i] && edges[startIndex][i]){
                    visited[i] = true;
                    queue.add(i);
                }
            }

        }
        Traversal.deleteCharAt(Traversal.length()-1);
        return Traversal.toString();
    }

    public String DFS(int startIndex){
        Traversal = new StringBuilder();
        boolean[] visited = new boolean[n];
        DFSUtil(startIndex, visited, Traversal);

        Traversal.deleteCharAt(Traversal.length()-1);
        return Traversal.toString();
    }

    public String DFSAll(int startIndex){
        Traversal = new StringBuilder();
        boolean[] visited = new boolean[n];

        for(int i=0; i<n; i++)
            if(!visited[i])
                DFSUtil(i, visited, Traversal);

        Traversal.deleteCharAt(Traversal.length()-1);
        return Traversal.toString();
    }

    private void DFSUtil(int s, boolean[] visited, StringBuilder Traversal){
        visited[s] = true;
        Traversal.append(s);
        Traversal.append(" ");

        for(int i=0; i<n; i++){
            if(edges[s][i] && !visited[i]){
                DFSUtil(i, visited, Traversal);
            }
        }
    }

    private int count = 0;
    public int noOfCycles(){
        //int cycle = 0;   //can't use
        count = 0;
        boolean[] visited = new boolean[n];
        for(int i=0; i<n; i++)
            if(!visited[i])
                noOfCyclesUtil(i, visited);
        return count;
    }

    private void noOfCyclesUtil(int s, boolean[] visited){
        visited[s] = true;

        for(int i=0; i<n; i++){
            if(edges[s][i] && visited[i]){
                count++;
            }
            if(edges[s][i] && !visited[i]){
                noOfCyclesUtil(i, visited);
            }
        }
    }

    private boolean cycle;
    public boolean hasCycles(){
        //int cycle = 0;   //can't use
        cycle = false;
        boolean[] visited = new boolean[n];
        for(int i=0; i<n; i++)
            if(!visited[i])
                noOfCyclesUtil(i, visited);
        return cycle;
    }

    private void hasCyclesUtil(int s, boolean[] visited){
        visited[s] = true;
        if(!cycle)
            for(int i=0; i<n; i++){
                if(edges[s][i] && visited[i]){
                    cycle = true;
                    break;
                }
                if(edges[s][i] && !visited[i]){
                    hasCyclesUtil(i, visited);
                }
            }
    }

    public int MSTPrimesSum(){
        boolean[] visited = new boolean[n];
        visited[0] = true;
        int min, sum=0, col=0;
        for (int k=0; k<n; k++){
            min = Integer.MAX_VALUE;
            for(int j=0;j<n;j++){
                if(visited[j]){
                    for(int i=0; i<n; i++){
                        if(!visited[i]){
                            if(min>(edges[j][i]?1:0)){
                                min = (edges[j][i]?1:0);
                                col = i;
                            }
                        }
                    }
                }
            }
            visited[col] = true;
            if(min<99999)
                sum += min;
        }
        return sum;
    }

    public String topologicalSorting(){
        Traversal = new StringBuilder();
        boolean[] visited = new boolean[n];

        for(int i=0; i<n; i++)
            if(!visited[i])
                topologicalSortingUtil(visited, i);

        Traversal.deleteCharAt(Traversal.length()-1);
        return Traversal.toString();
    }

    public void topologicalSortingUtil(boolean[] visited, int s){
        visited[s]=true;
        Traversal.append(s);
        Traversal.append(" ");
        for(int i=0; i<n; i++){
            if(edges[s][i] && !visited[i]){
                topologicalSortingUtil(visited, i);
            }
        }
    }

    private boolean result;
    public boolean isConnectedDFS(int u, int w){
        result = false;
        boolean[] visited = new boolean[n];
        visited[u] = true;
        isConnectedDFSUtil(u, w, visited);
        return result;
    }

    private void isConnectedDFSUtil(int u, int w,boolean[] visited){
        if (!result){
            if(u==w)
                result=true;
            else {
                for(int i=0; i<n; i++){
                    if(edges[u][i] && !visited[i]){
                        visited[i] = true;
                        isConnectedDFSUtil(i, w, visited);
                    }
                }
            }
        }
    }

    public Set<Set> connectivityGrouping(){
        Set<Set> set = new HashSet<>();

        boolean visited[] = new boolean[n];
        LinkedList<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            Set<Integer> tmp = new HashSet<>();
            if (!visited[i]) {
                visited[i] = true;
                queue.add(i);

                while (!queue.isEmpty()) {
                    int startIndex = queue.poll();
                    tmp.add(startIndex);
                    for (int j = 0; j < n; j++) {
                        if (!visited[j] && edges[startIndex][j]) {
                            visited[j] = true;
                            queue.add(j);
                        }
                    }

                }
            }
            if(tmp.size()>0)
                set.add(tmp);
        }
        return set;
    }

    public List<Integer> connectivityGroupingCount(){
        List<Integer> list = new LinkedList<>();

        boolean visited[] = new boolean[n];
        LinkedList<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            int count = 0;
            if (!visited[i]) {
                visited[i] = true;
                queue.add(i);

                while (!queue.isEmpty()) {
                    int startIndex = queue.poll();
                    count++;
                    for (int j = 0; j < n; j++) {
                        if (!visited[j] && edges[startIndex][j]) {
                            visited[j] = true;
                            queue.add(j);
                        }
                    }

                }
            }
            if(count>0)
                list.add(count);
        }
        return list;
    }

    private int minKey(int key[], Boolean mstSet[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index=-1;

        for (int v = 0; v < n; v++)
            if (!mstSet[v] && key[v] < min)
            {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }

    public int[][] MSTPrims()
    {
        int parent[] = new int[n];
        int key[] = new int [n];
        Boolean mstSet[] = new Boolean[n];
        for (int i = 0; i < n; i++)
        {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }
        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < n-1; count++)
        {
            int u = minKey(key, mstSet);
            mstSet[u] = true;
            for (int v = 0; v < n; v++)
                if (edges[u][v] && !mstSet[v] && (edges[u][v]?1:0) <  key[v])
                {
                    parent[v]  = u;
                    key[v] = edges[u][v]?1:0;
                }
        }
        int[][] mst = new int[n-1][3];
        for (int i = 1; i < n; i++) {
            mst[i-1][0] = parent[i];
            mst[i-1][1] = i;
            mst[i-1][2] = edges[i][parent[i]]?1:0;
        }
        return mst;
    }
}
