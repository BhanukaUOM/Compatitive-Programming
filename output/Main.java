import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.DataInputStream;
import java.io.BufferedWriter;
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
            int t = ni();
            int n = ni();
            int m = ni();
            int cl = ni();
            int cr = ni();

            graphUnweightMatrix g = new graphUnweightMatrix(n);

            for (int i = 0; i < m; i++) {
                g.addUndirectedEdge(ni(), ni());
            }

            g.DFSAll(0);
        }

        private int ni() {
            return in.nextInt();
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

        public void close() {
            writer.close();
        }

    }

    static class graphUnweightMatrix {
        private boolean[][] edges;
        private int n;
        private StringBuilder Traversal;

        public graphUnweightMatrix(int nodes) {
            n = nodes;
            edges = new boolean[n][n];
        }

        public void addUndirectedEdge(int start, int end) {
            edges[start][end] = true;
            edges[end][start] = true;
        }

        public String DFSAll(int startIndex) {
            Traversal = new StringBuilder();
            boolean[] visited = new boolean[n];

            for (int i = 0; i < n; i++)
                if (!visited[i])
                    DFSUtil(i, visited, Traversal);

            Traversal.deleteCharAt(Traversal.length() - 1);
            return Traversal.toString();
        }

        private void DFSUtil(int s, boolean[] visited, StringBuilder Traversal) {
            visited[s] = true;
            Traversal.append(s);
            Traversal.append(" ");

            for (int i = 0; i < n; i++) {
                if (edges[s][i] && !visited[i]) {
                    DFSUtil(i, visited, Traversal);
                }
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

        public input(String file_name) {
            try {
                din = new DataInputStream(new FileInputStream(file_name));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

    }
}

