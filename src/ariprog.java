/*
 ID: rory.so1
 LANG: JAVA
 TASK: ariprog
 */

import java.io.*;
import java.util.*;

class ariprog {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("ariprog.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
        String output = "";

        int n = Integer.parseInt(in.readLine());
        int m = Integer.parseInt(in.readLine());
        int max = 2 * m * m;
        boolean[] bisquares = new boolean[max + 1];
        for (int p = 0; p <= m; p++) {
            for (int q = 0; q <= m; q++) {
                bisquares[p * p + q * q] = true;
            }
        }

        List<Pair> r = new LinkedList();
        for (int b = 1; b <= max / (n - 1); b++) {
            for (int a = 0; a <= max - b * (n - 1); a++) {
                boolean good = true;
                for (int x = 0; x < n; x++) {
                    if (!bisquares[a + x * b]) {
                        good = false;
                        break;
                    }
                }
                if (good) {
                    r.add(new Pair(a, b));
                }
            }
        }

        for (Pair p : r) {
            output += p + "\n";
        }
        if (!r.isEmpty()) {
            output = output.substring(0, output.length() - 1);
        } else {
            output = "NONE";
        }
        out.println(output);
        out.close();
    }

    public static class Pair {

        public int a, b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return a + " " + b;
        }
    }
}
