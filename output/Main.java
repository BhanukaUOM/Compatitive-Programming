import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.DataInputStream;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.AbstractCollection;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
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
            for (int p = 0; p < t; p++) {
                int n = in.ni();
                int m = in.ni();
                graphWeightMatrix g = new graphWeightMatrix(n);
                for (int i = 0; i < m; i++) {
                    g.addUndirectedEdge(in.ni() - 1, in.ni() - 1, 6);
                }
                int s = in.ni() - 1;
                int[] arr = g.distancetonodes(s);
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i] > 0) {
                        out.print(arr[i] + " ");
                    } else if (arr[i] == -1) {
                        out.print(arr[i] + " ");
                    }
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

        public int[] distancetonodes(int s) {
            LinkedList<Integer> queue = new LinkedList<Integer>();
            boolean[] visited = new boolean[n];
            int[] result = new int[n];
            Arrays.fill(result, -1);

            result[s] = 0;
            visited[s] = true;
            queue.add(s);

            while (!queue.isEmpty()) {
                int tmp = queue.poll();
                for (int i = 0; i < n; i++)
                    if (!visited[i] && edges[tmp][i] > 0) {
                        visited[i] = true;
                        queue.add(i);
                        result[i] = result[tmp] + edges[tmp][i];
                    }
            }
            return result;
        }

    }
}

