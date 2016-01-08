
import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class bcount {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("bcount.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bcount.out")));

        List<Integer> a = tokenizeInt(in);
        int n = a.get(0);
        int[] h = new int[n + 1], g = new int[n + 1], j = new int[n + 1];
        int[] c = new int[3];
        for (int i = 1; i <= n; i++) {
            c[Integer.parseInt(in.readLine()) - 1]++;
            h[i] = c[0];
            g[i] = c[1];
            j[i] = c[2];
        }
        for (int i = 0; i < a.get(1); i++) {
            List<Integer> r = tokenizeInt(in);
            int r0 = r.get(0) - 1;
            int r1 = r.get(1);
            out.println((h[r1] - h[r0]) + " " + (g[r1] - g[r0]) + " " + (j[r1] - j[r0]));
        }

        out.close();
    }

    public static List<Integer> tokenizeInt(BufferedReader in) throws IOException {
        List<Integer> r = new LinkedList();
        for (Object s : Collections.list(new StringTokenizer(in.readLine()))) {
            r.add(Integer.parseInt((String) s));
        }
        return r;
    }

    public static class Pair {

        public int a, b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public List<Pair> adjacent(int maxA, int maxB) {
            List<Pair> r = new LinkedList();
            if (a > 0) {
                r.add(new Pair(a - 1, b));
            }
            if (a < maxA - 1) {
                r.add(new Pair(a + 1, b));
            }
            if (b > 0) {
                r.add(new Pair(a, b - 1));
            }
            if (b < maxB - 1) {
                r.add(new Pair(a, b + 1));
            }
            return r;
        }

        @Override
        public boolean equals(Object other) {
            return a == ((Pair) other).a && b == ((Pair) other).b;
        }
    }
}
