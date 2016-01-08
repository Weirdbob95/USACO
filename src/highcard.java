
import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class highcard {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("highcard.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("highcard.out")));

        int n = Integer.parseInt(in.readLine());

        boolean[] owner = new boolean[2 * n];
        for (int i = 0; i < n; i++) {
            int c = Integer.parseInt(in.readLine()) - 1;
            owner[c] = true;
        }
        int max = 0;
        int hu = 0;
        for (int i = 0; i < 2 * n; i++) {
            if (owner[i]) {
                for (int j = Math.max(i, hu) + 1; j < 2 * n; j++) {
                    if (!owner[j]) {
//                        System.out.println("beat " + i + " with " + j);
                        hu = j;
                        max++;
                        break;
                    }
                }
            }
        }

        out.println(max);
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
