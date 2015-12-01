/*
 ID: rory.so1
 LANG: JAVA
 TASK: transform
 */

import java.io.*;
import java.util.*;

class transform {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("transform.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));

        int n = Integer.parseInt(in.readLine());
        boolean[][] before = new boolean[n][n];
        boolean[][] after = new boolean[n][n];
        for (int y = 0; y < n; y++) {
            String s = in.readLine();
            for (int x = 0; x < n; x++) {
                before[x][n - y - 1] = s.charAt(x) == '@';
            }
        }
        for (int y = 0; y < n; y++) {
            String s = in.readLine();
            for (int x = 0; x < n; x++) {
                after[x][n - y - 1] = s.charAt(x) == '@';
            }
        }

        String output = "" + num(before, after, n);

        out.println(output);
        out.close();
    }

    public static int num(boolean[][] before, boolean[][] after, int n) {
        before = rotate90(before, n);
        if (Arrays.deepEquals(before, after)) {
            return 1;
        }
        before = rotate90(before, n);
        if (Arrays.deepEquals(before, after)) {
            return 2;
        }
        before = rotate90(before, n);
        if (Arrays.deepEquals(before, after)) {
            return 3;
        }
        before = rotate90(before, n);
        before = mirror(before, n);
        if (Arrays.deepEquals(before, after)) {
            return 4;
        }
        before = rotate90(before, n);
        if (Arrays.deepEquals(before, after)) {
            return 5;
        }
        before = rotate90(before, n);
        if (Arrays.deepEquals(before, after)) {
            return 5;
        }
        before = rotate90(before, n);
        if (Arrays.deepEquals(before, after)) {
            return 5;
        }
        before = rotate90(before, n);
        if (Arrays.deepEquals(before, after)) {
            return 6;
        }
        return 7;
    }

    public static boolean[][] mirror(boolean[][] a, int n) {
        boolean[][] r = new boolean[n][n];
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                r[x][y] = a[n - x - 1][y];
            }
        }
        return r;
    }

    public static boolean[][] rotate90(boolean[][] a, int n) {
        boolean[][] r = new boolean[n][n];
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                r[x][y] = a[n - y - 1][x];
            }
        }
        return r;
    }
}
