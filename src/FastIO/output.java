package FastIO;

import java.io.*;

public class output {
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

    public void print(int[] array){
        for (int i=0; i<array.length; i++){
            print(array[i]+" ");
        }
        println();
    }

    public void print(short[] array){
        for (int i=0; i<array.length; i++){
            print(array[i]+" ");
        }
        println();
    }

    public void print(char[] array){
        for (int i=0; i<array.length; i++){
            print(array[i]+" ");
        }
        println();
    }

    public void print(long[] array){
        for (int i=0; i<array.length; i++){
            print(array[i]+" ");
        }
        println();
    }

    public void print(float[] array){
        for (int i=0; i<array.length; i++){
            print(array[i]+" ");
        }
        println();
    }

    public void print(double[] array){
        for (int i=0; i<array.length; i++){
            print(array[i]+" ");
        }
        println();
    }

    public void print(String[] array){
        for (int i=0; i<array.length; i++){
            print(array[i]+" ");
        }
        println();
    }

    public void print(int[][] array){
        for (int i=0; i<array.length; i++){
            for (int j=0; j<array[0].length; j++) {
                print(array[i][j] + " ");
            }
            println();
        }
        println();
    }

    public void print(short[][] array){
        for (int i=0; i<array.length; i++){
            for (int j=0; j<array[0].length; j++) {
                print(array[i][j] + " ");
            }
            println();
        }
        println();
    }

    public void print(char[][] array){
        for (int i=0; i<array.length; i++){
            for (int j=0; j<array[i].length; j++) {
                print(array[i][j]+" ");
            }
            println();
        }
        println();
    }

    public void print(long[][] array){
        for (int i=0; i<array.length; i++){
            for (int j=0; j<array[i].length; j++) {
                print(array[i][j]+" ");
            }
            println();
        }
        println();
    }

    public void print(float[][] array){
        for (int i=0; i<array.length; i++){
            for (int j=0; j<array[i].length; j++) {
                print(array[i][j]+" ");
            }
            println();
        }
        println();
    }

    public void print(double[][] array){
        for (int i=0; i<array.length; i++){
            for (int j=0; j<array[i].length; j++) {
                print(array[i][j]+" ");
            }
            println();
        }
        println();
    }

    public void print(String[][] array){
        for (int i=0; i<array.length; i++){
            for (int j=0; j<array[i].length; j++) {
                print(array[i][j]+" ");
            }
            println();
        }
        println();
    }

    public void close() {
        writer.close();
    }

}