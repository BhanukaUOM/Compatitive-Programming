import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.DataInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        input in = new input(inputStream);
        output out = new output(outputStream);
        NeedHelp solver = new NeedHelp();
        solver.solve(1, in, out);
        out.close();
    }

    static class NeedHelp {
        public void solve(int testNumber, input in, output out) {
            int t = in.ni();
            for (int z = 0; z < t; z++) {
                int n = in.ni();
                int m = in.ni();
                graphWeightMatrix g = new graphWeightMatrix(n);
                for (int i = 0; i < m; i++) {
                    int x = in.ni() - 1, y = in.ni() - 1, w = in.ni();
                    if (g.getEdge(x, y) == 0 || g.getEdge(x, y) > w)
                        g.addUndirectedEdge(x, y, w);
                }
                int p = in.ni() - 1;
                int[] arr = g.dijkstra(p);
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i] > Integer.MAX_VALUE - 100000)
                        out.print(-1 + " ");
                    else if (i != p)
                        out.print(arr[i] + " ");
                }
                out.pl();
            }
        }

    }

    static class input {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer;
        private int bytesRead;

        public input() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public input(InputStream inputStream) {
            din = new DataInputStream(inputStream);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public int ni() {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public input(String file_name) {
            try {
                din = new DataInputStream(new FileInputStream(file_name));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        private void fillBuffer() {
            try {
                bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

    }

    static class output {
        private final PrintWriter writer;

        public output(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }

        public output(Writer writer) {
            this.writer = new PrintWriter(writer);
        }

        public void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0) {
                    writer.print(' ');
                }
                writer.print(objects[i]);
            }
        }

        public void pl(Object... objects) {
            print(objects);
            writer.println();
        }

        public void close() {
            writer.close();
        }

    }

    static class graphWeightMatrix {
        private int[][] edges;
        private int n;

        public graphWeightMatrix(int nodes) {
            n = nodes;
            edges = new int[nodes][nodes];
        }

        public void addUndirectedEdge(int start, int end, int weight) {
            edges[start][end] = weight;
            edges[end][start] = weight;
        }

        public int getEdge(int i, int j) {
            return edges[i][j];
        }

        int minDistance(int dist[], Boolean set[]) {
            int min = Integer.MAX_VALUE, min_index = -1;

            for (int v = 0; v < n; v++)
                if (!set[v] && dist[v] <= min) {
                    min = dist[v];
                    min_index = v;
                }
            return min_index;
        }

        public int[] dijkstra(int startNode) {
            int res[] = new int[n];
            Boolean sptSet[] = new Boolean[n];

            for (int i = 0; i < n; i++) {
                res[i] = Integer.MAX_VALUE;
                sptSet[i] = false;
            }

            res[startNode] = 0;

            for (int i = 0; i < n - 1; i++) {
                int u = minDistance(res, sptSet);
                sptSet[u] = true;

                for (int j = 0; j < n; j++)
                    if (!sptSet[j] && edges[u][j] != 0 && res[u] != Integer.MAX_VALUE && res[u] + edges[u][j] < res[j])
                        res[j] = res[u] + edges[u][j];
            }
            return res;
        }

    }
}

