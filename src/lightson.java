
import java.io.*;
import java.util.*;

public class lightson {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("lightson.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lightson.out")));

        List<Integer> na = tokenizeInt(in);
        int n = na.get(0);

        List<Pair>[][] switches = new List[n][n];
        for (int i = 0; i < na.get(1); i++) {
            List<Integer> s = tokenizeInt(in);
            if (switches[s.get(0) - 1][s.get(1) - 1] == null) {
                switches[s.get(0) - 1][s.get(1) - 1] = new LinkedList();
            }
            switches[s.get(0) - 1][s.get(1) - 1].add(new Pair(s.get(2) - 1, s.get(3) - 1));
        }

        boolean[][] lit = new boolean[n][n];
        lit[0][0] = true;
        boolean[][] canReach = new boolean[n][n];
        canReach[0][0] = true;

        Queue<Pair> toCheck = new LinkedList();
        toCheck.add(new Pair(0, 0));
        int max = 1;
        while (!toCheck.isEmpty()) {
            Pair room = toCheck.poll();
            if (switches[room.a][room.b] != null) {
                for (Pair p : switches[room.a][room.b]) {
                    if (!lit[p.a][p.b]) {
                        max++;
                        lit[p.a][p.b] = true;
                        for (Pair adj : p.adjacent(n, n)) {
                            if (canReach[adj.a][adj.b]) {
                                toCheck.add(p);
                                canReach[p.a][p.b] = true;
                                break;
                            }
                        }
                    }
                }
            }
            for (Pair adj : room.adjacent(n, n)) {
                if (lit[adj.a][adj.b] && !canReach[adj.a][adj.b]) {
                    canReach[adj.a][adj.b] = true;
                    toCheck.add(adj);
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
