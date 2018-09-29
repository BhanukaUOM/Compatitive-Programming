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
        SandunsCourierService solver = new SandunsCourierService();
        solver.solve(1, in, out);
        out.close();
    }

    static class SandunsCourierService {
        public void solve(int testNumber, input in, output out) {
            int[] arr = {11, 2, 3, 4, 7, 9, 10, 22, 5, 1};
            int min, max;
            int n = arr.length;
            int i = 0;

            if (n % 2 == 0) {
                if (arr[0] > arr[1]) {
                    max = arr[0];
                    min = arr[1];
                } else {
                    min = arr[0];
                    max = arr[1];
                }
                i = 2;
            } else {
                min = arr[0];
                max = arr[0];
                i = 1;
            }

            for (; i < n - 1; i += 2) {
                if (arr[i] > arr[i + 1]) {
                    if (arr[i] > max)
                        max = arr[i];
                    if (arr[i + 1] < min)
                        min = arr[i + 1];
                } else {
                    if (arr[i + 1] > max)
                        max = arr[i + 1];
                    if (arr[i] < min)
                        min = arr[i];
                }
            }

            out.print(min, max);

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
}

