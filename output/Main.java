import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.stream.IntStream;
import java.io.DataInputStream;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.util.HashMap;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.stream.Stream;
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
        FlatEarthSociety solver = new FlatEarthSociety();
        solver.solve(1, in, out);
        out.close();
    }

    static class FlatEarthSociety {
        public void solve(int testNumber, input in, output out) {
            int t = in.ni();
            for (int z = 0; z < t; z++) {
                int n = in.ni();
                int l = in.ni();
                int r = in.ni();

                HashMap<Integer, Integer> c = counterHashMap(in.nextIntArray());
                out.print(c);
            }
        }

        private HashMap<Integer, Integer> counterHashMap(int[] array) {
            HashMap<Integer, Integer> counter = new HashMap<>();
            for (int i = 0; i < array.length; i++) {
                Integer key = counter.get(array[i]);
                if (key == null) counter.put(array[i], 0);
                else counter.put(array[i], key + 1);
            }
            return counter;
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

        public void close() {
            writer.close();
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

        public String nextLine() {
            StringBuilder str = new StringBuilder();
            byte c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                str.append((char) c);
            }
            return str.toString();
        }

        public int[] nextIntArray() {
            return Arrays.stream(nextStringArray()).mapToInt(Integer::parseInt).toArray();
        }

        public String[] nextStringArray() {
            String[] ret = nextLine().split(" ");
            return ret;
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
}

