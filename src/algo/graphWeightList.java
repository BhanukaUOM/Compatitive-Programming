package algo;

import java.util.*;

public class graphWeightList {
    private class Edge {
        int node, weight;

        Edge(int to, int weight) {
            this.node = to;
            this.weight = weight;
        }
    }

    private LinkedList<Edge>[] edges;
    private int n;


    public graphWeightList(int nodes){
        this.n = nodes;
        edges = new LinkedList[nodes];
        for (int i = 0; i <nodes ; i++) {
            edges[i] = new LinkedList<>();
        }
    }

    public void print(){
        for (int i = 0; i <n ; i++) {
            if (edges[i].size() > 0) {
                System.out.print("Vertex " + i + " is connected to: ");
                for (Edge j : edges[i]) {
                    System.out.print(j.node + "(" + j.weight + ")" + " ");
                }
                System.out.println();
            }
        }
    }

    public void addUndirectedEdge(int start, int end, int weight){
        edges[start].addFirst(new Edge(end, weight));
        edges[end].addFirst(new Edge(start, weight));
    }

    public void addDirectedEdge(int start, int end, int weight){
        edges[start].addFirst(new Edge(end, weight));
    }

    public Edge getEdge(int i, int j){
        for(Edge e : edges[i]){
            if(e.node == j){
                return e;
            }
        }
        return new Edge(-1, 0);
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
            for(Edge i : edges[startIndex]){
                if(!visited[i.node] && i.weight>0){
                    visited[i.node] = true;
                    queue.add(i.node);
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

        for(Edge i : edges[s]){
            if(i.weight>0 && !visited[i.node]){
                DFSUtil(i.node, visited, Traversal);
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

        for(Edge i : edges[s]){
            if(i.weight>0 && visited[i.node]){
                count++;
            }
            else {
                noOfCyclesUtil(i.node, visited);
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
            for(Edge i : edges[s]){
                if(i.weight>0 && visited[i.node]){
                    cycle = true;
                    break;
                }
                else if(i.weight>0 && !visited[i.node]){
                    hasCyclesUtil(i.node, visited);
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
        for(Edge i : edges[s]){
            if(i.weight>0 && !visited[i.node]){
                topologicalSortingUtil(visited, i.node);
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
                for(Edge i : edges[u]){
                    if(i.weight>0 && !visited[i.node]){
                        visited[i.node] = true;
                        isConnectedDFSUtil(i.node, w, visited);
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
                    for (Edge j : edges[startIndex]) {
                        if (!visited[j.node] && j.weight > 0) {
                            visited[j.node] = true;
                            queue.add(j.node);
                        }
                    }

                }
            }
            //System.out.println(tmp.toString());
            if(tmp.size()>0)
                set.add(tmp);
        }
        return set;
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
            for(Edge i : edges[tmp]){
                if(i.node>0 && result[i.node] > result[tmp] + i.weight){
                    result[i.node] = result[tmp] + i.weight;
                }
            }
            tmp = minresultance(result, visited, n);
        }

        return result;
    }


    private int minKey(int key[], Boolean mstSet[]) {
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < n; v++)
            if (!mstSet[v] && key[v] < min) {
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
            for (Edge v : edges[u])
                if (v.weight!=0 && !mstSet[v.node] &&
                        v.weight <  key[v.node])
                {
                    parent[v.node]  = u;
                    key[v.node] = v.weight;
                }
        }
        int[][] arr = new int[n-1][3];
        for (int i = 1; i < n; i++) {
            arr[i - 1][0] = parent[i];
            arr[i - 1][1] = i;
            arr[i - 1][2] = getEdge(i, parent[i]).weight;
        }
        return arr;
    }

    int minDistance(int dist[], Boolean set[])
    {
        int min = Integer.MAX_VALUE, min_index=-1;

        for (int v = 0; v < n; v++)
            if (!set[v] && dist[v] <= min)
            {
                min = dist[v];
                min_index = v;
            }
        return min_index;
    }

    public int[] dijkstra(int startNode)
    {
        int res[] = new int[n];
        Boolean sptSet[] = new Boolean[n];

        for (int i = 0; i < n; i++)
        {
            res[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        res[startNode] = 0;

        for (int i = 0; i < n-1; i++)
        {
            int u = minDistance(res, sptSet);
            sptSet[u] = true;

            for (Edge j : edges[u])
                if (!sptSet[j.node] && j.weight!=0 && res[u] != Integer.MAX_VALUE && res[u]+j.weight < res[j.node])
                    res[j.node] = res[u] + j.weight;
        }
        return res;
    }

    public int[] distancetonodes(int s) {
        LinkedList<Integer> queue = new LinkedList<Integer>();
        boolean[] visited = new boolean[n];
        int[] result = new int[n];
        Arrays.fill(result, -1);

        result[s] = 0;
        visited[s] = true;
        queue.add(s);

        while(!queue.isEmpty()){
            int tmp = queue.poll();
            for (Edge i : edges[tmp])
                if(!visited[i.node])
                {
                    visited[i.node] = true;
                    queue.add(i.node);
                    result[i.node] = result[tmp]+i.weight;
                }
        }
        return result;
    }
}
