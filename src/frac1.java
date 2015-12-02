/*
 ID: rory.so1
 LANG: JAVA
 TASK: frac1
 */

import java.io.*;
import java.util.*;

class frac1 {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("frac1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));

        int x = Integer.parseInt(in.readLine());
        List<Pair> fracs = new ArrayList();
        fracs.add(new Pair(0, 1));
        fracs.add(new Pair(1, 1));
        for (int d = 2; d <= x; d++) {
            for (int n = 1; n < d; n++) {
                boolean red = true;
                for (int v = 2; v <= n; v++) {
                    if (n % v == 0 && d % v == 0) {
                        red = false;
                        break;
                    }
                }
                if (red) {
                    fracs.add(new Pair(n, d));
                }
            }
        }
        Collections.sort(fracs, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return (int) Math.signum((double) o1.a / o1.b - (double) o2.a / o2.b);
            }
        });
        for (Pair p : fracs) {
            out.println(p);
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

        @Override
        public String toString() {
            return a + "/" + b;
        }
    }
}
