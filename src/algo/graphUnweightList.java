package algo;

import java.util.*;

public class graphUnweightList {
    private LinkedList<Integer>[] edges;
    private int n;


    public graphUnweightList(int nodes){
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
                for (int j : edges[i]) {
                    System.out.print(j + " ");
                }
                System.out.println();
            }
        }
    }

    public void addUndirectedEdge(int start, int end){
        edges[start].addFirst(end);
        edges[end].addFirst(start);
    }

    public void addDirectedEdge(int start, int end){
        edges[start].addFirst(end);
    }

    public boolean getEdge(int i, int j){
        return (edges[i].contains(j));
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
            for(int i : edges[startIndex]){
                if(!visited[i]){
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

        for(int i : edges[s]){
            if(!visited[i]){
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

        for(int i: edges[s]){
            if(visited[i]){
                count++;
            }
            else{
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
            for(int i: edges[s]){
                if(visited[i]){
                    cycle = true;
                    break;
                }
                else{
                    hasCyclesUtil(i, visited);
                }
            }
    }

    ////////
    public int MSTPrimesSum(){
        boolean[] visited = new boolean[n];
        visited[0] = true;
        int min, sum=0, col=0;
        for (int k=0; k<n; k++){
            min = Integer.MAX_VALUE;
            for(int j=0;j<n;j++){
                if(visited[j]){
                    for(int i : edges[j]){
                        if(!visited[i]){
                            if(min>1){
                                min = 1;
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
        for(int i: edges[s]){
            if(!visited[i]){
                topologicalSortingUtil(visited, i);
            }
        }
    }

    private boolean result;
    public boolean isConnectedDFS(int u, int w){
        result = false;
        if(edges[u].size()==0 || edges[w].size()==0)
            return false;
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
                for(int i : edges[u]){
                    if(!visited[i]){
                        visited[i] = true;
                        isConnectedDFSUtil(i, w, visited);
                    }
                }
            }
        }
    }

    public boolean isConnectedBFS(int u, int w){
        if(edges[u].size()==0 || edges[w].size()==0)
            return false;
        else if(u==w)
            return true;
        boolean visited[] = new boolean[n];
        LinkedList<Integer> queue = new LinkedList<>();

        queue.add(u);
        visited[u] = true;

        while(!queue.isEmpty()){
            u = queue.poll();
            if(u==w)
                return true;
            for(int i : edges[u]){
                if(!visited[i]){
                    visited[i] = true;
                    queue.add(i);
                }
            }

        }
        return false;
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
                    for (int j : edges[startIndex]) {
                        if (!visited[j]) {
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
//////////////
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
            for (int v : edges[u])
                if (!mstSet[v] && 1 <  key[v])
                {
                    parent[v]  = u;
                    key[v] = 1;
                }
        }
        int[][] mst = new int[n-1][3];
        for (int i = 1; i < n; i++) {
            mst[i-1][0] = parent[i];
            mst[i-1][1] = i;
            mst[i-1][2] = edges[i].contains(parent[i])?1:0;
        }
        return mst;
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
            for (int i : edges[tmp])
                if(!visited[i])
                {
                    visited[i] = true;
                    queue.add(i);
                    result[i] = result[tmp]+1;
                }
        }
        return result;
    }
}
