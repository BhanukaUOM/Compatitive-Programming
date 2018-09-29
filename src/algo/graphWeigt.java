package algo;

import java.util.*;

public class graphWeigt {
    private int[][] edges;
    private int n, k, last;
    
    public graphWeigt(int no, int ko){
        edges = new int[no][no];
        n = no;
        k = ko;
        last = no-1;
    }

    public void addEdge(int s, int e, int w){
        edges[s][e] = w;
        edges[e][s] = w;
    }

    public void DFS(int k){
        boolean[] visited = new boolean[n];
        util(0, visited, k);
    }

    public void util(int s, boolean[] visited, int k){
        visited[s] = true;

        for(int i=0; i<n; i++){
            if(edges[s][i]>0 && !visited[i]){
                util(i, visited, k);
            }
        }
    }

    private int minr(int res[], boolean visited[], int V)
    {
        int min = 2147483647, min_index=-1;

        for (int v = 0; v < V; v++){
            if (!visited[v] && res[v] <= min)
            {
                min = res[v];
                min_index = v;
            }
        }
        return min_index;
    }

    private int modul(int a){return (a%(2*k)>=k)?((2*k)-a%(2*k)):0;}

    public int dijkstra() {
        int[] res = new int[n];
        Arrays.fill(res, 2147483647);
        res[0] = 0;
        boolean[] visited = new boolean[n];
        int val = 0;

        while(val != -1){
            visited[val] = true;
            for(int i=0; i<n; i++) {
                int z = res[val] + modul(res[val]) + edges[val][i];
                if (edges[val][i] > 0 && res[i] > z) {
                    res[i] = z;
                }
            }
            val = minr(res, visited, n);
        }
        return res[last];
    }
}
