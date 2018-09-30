import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.DataInputStream;
import java.io.BufferedWriter;
import java.util.Set;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
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
            int n = in.ni();
            int t = in.ni();

            graphWeightList g = new graphWeightList(n);
            for (int i = 0; i < t; i++) {
                g.addUndirectedEdge(in.ni(), in.ni(), 1);
            }
            List<Integer> country = new LinkedList<>();
            for (Set i : g.connectivityGrouping()) {
                country.add(i.size());
            }
            long sum = 0;
            for (int i : country) {
                sum += i;
            }

            long ans = 0;
            for (int i : country) {
                sum -= i;
                ans += i * sum;
            }

            out.println(ans);
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

    static class graphWeightList {
        private LinkedList<Edge>[] edges;
        private int n;

        public graphWeightList(int nodes) {
            this.n = nodes;
            edges = new LinkedList[nodes];
            for (int i = 0; i < nodes; i++) {
                edges[i] = new LinkedList<>();
            }
        }

        public void addUndirectedEdge(int start, int end, int weight) {
            edges[start].addFirst(new Edge(end, weight));
            edges[end].addFirst(new Edge(start, weight));
        }

        public Set<Set> connectivityGrouping() {
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
                if (tmp.size() > 0)
                    set.add(tmp);
            }
            return set;
        }

        private class Edge {
            int node;
            int weight;

            Edge(int to, int weight) {
                this.node = to;
                this.weight = weight;
            }

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

        public void println(Object... objects) {
            print(objects);
            writer.println();
        }

        public void close() {
            writer.close();
        }

    }
}

