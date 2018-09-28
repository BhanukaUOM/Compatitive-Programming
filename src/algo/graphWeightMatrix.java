package CHelper.src.algo;

import CHelper.src.algs.In;

import java.util.*;

public class graphWeightMatrix {
    private int[][] edges;
    private int n;

    public graphWeightMatrix(int nodes){
        n = nodes;
        edges = new int[nodes][nodes];
    }

    public void addUndirectedEdge(int start, int end, int weight){
        edges[start][end] = weight;
        edges[end][start] = weight;
    }

    public void addDirectedEdge(int start, int end, int weight){
        edges[start][end] = weight;
    }

    public int getEdge(int i, int j){
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
                if(!visited[i] && edges[startIndex][i]>0){
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
            if(edges[s][i]>0 && !visited[i]){
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
            if(edges[s][i]>0 && visited[i]){
                count++;
            }
            if(edges[s][i]>0 && !visited[i]){
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
                if(edges[s][i]>0 && visited[i]){
                    cycle = true;
                    break;
                }
                if(edges[s][i]>0 && !visited[i]){
                    hasCyclesUtil(i, visited);
                }
            }
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
            if(edges[s][i]>0 && !visited[i]){
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
                    if(edges[u][i]>0 && !visited[i]){
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

                while (!queue.isEmpty()) {
                    int startIndex = queue.poll();
                    tmp.add(startIndex);
                    for (int j = 0; j < n; j++) {
                        if (!visited[j] && edges[startIndex][j] > 0) {
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

                while (!queue.isEmpty()) {
                    int startIndex = queue.poll();
                    count++;
                    for (int j = 0; j < n; j++) {
                        if (!visited[j] && edges[startIndex][j] > 0) {
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

    private int minresultance(int result[], boolean visited[], int V)
    {
        int min = Integer.MAX_VALUE;
        int min_index=-1;

        for (int v = 0; v < V; v++){
            if (!visited[v] && result[v] <= min)
            {
                min = result[v];
                min_index = v;
            }
        }
        return min_index;
    }

    public int[] shotestPathDijkstra(int startNode) {
        int[] result = new int[n];
        Arrays.fill(result, Integer.MAX_VALUE);
        result[startNode] = 0;
        boolean[] visited = new boolean[n];
        int tmp = startNode;
        while(tmp != -1){
            visited[tmp] = true;
            for(int i=0; i<n; i++){
                if(edges[tmp][i]>0 && result[i] > result[tmp] + edges[tmp][i]){
                    result[i] = result[tmp] + edges[tmp][i];
                }
            }
            tmp = minresultance(result, visited, n);
        }

        return result;
    }


    private int minKey(int key[], Boolean mstSet[]) {
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < n; v++)
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        return min_index;
    }

    public int[][] MSTPrims()   //Not Working with 0 Weight Edges
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
                if (edges[u][v]!=0 && mstSet[v] == false &&
                        edges[u][v] <  key[v])
                {
                    parent[v]  = u;
                    key[v] = edges[u][v];
                }
        }
        int[][] arr = new int[n-1][3];
        for (int i = 1; i < n; i++) {
            arr[i - 1][0] = parent[i];
            arr[i - 1][1] = i;
            arr[i - 1][2] = edges[i][parent[i]];
        }
        return arr;
    }
}
