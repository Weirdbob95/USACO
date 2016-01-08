
import java.io.*;
import java.util.*;

public class dream {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("dream.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dream.out")));

        final List<Integer> size = tokenizeInt(in);
        Integer[][] grid = new Integer[size.get(0)][];
        for (int i = 0; i < size.get(0); i++) {
            grid[i] = tokenizeInt(in).toArray(new Integer[size.get(1)]);
        }

        Pos s = new Pos(0, 0, false);
        final Map<Pos, Integer> minDist = new HashMap();
        minDist.put(s, 0);
        TreeSet<Pos> toCheck = new TreeSet(new Comparator<Pos>() {
            @Override
            public int compare(Pos o1, Pos o2) {
                int n = minDist.get(o1) + o1.h(size) - minDist.get(o2) - o2.h(size);
                if (n == 0) {
                    return o1.id - o2.id;
                }
                return n;
            }
        });
        toCheck.add(s);
        Set<Pos> closedSet = new HashSet();

        while (!toCheck.isEmpty()) {
            Pos p = toCheck.pollFirst();
            closedSet.add(p);
            if (p.h(size) == 2) {
                out.println(minDist.get(p));
                out.close();
                return;
            }
            for (Pos n : p.adj(size, grid)) {
                if (n != p && n != null) {
                    if (!closedSet.contains(n)) {
                        if (!minDist.containsKey(n)) {
                            minDist.put(n, minDist.get(p) + p.dist(n));
                            toCheck.add(n);
                        } else {
                            int nd = minDist.get(p) + p.dist(n);
                            if (nd < minDist.get(n)) {
                                minDist.put(n, nd);
                                toCheck.add(n);
                            }
                        }
                    }
                }
            }
        }
        out.println(-1);
        out.close();
    }

    public static List<Integer> tokenizeInt(BufferedReader in) throws IOException {
        List<Integer> r = new LinkedList();
        for (Object s : Collections.list(new StringTokenizer(in.readLine()))) {
            r.add(Integer.parseInt((String) s));
        }
        return r;
    }

    public static class Pos {

        private static int maxID = 0;
        public int id = maxID++;
        public int x, y;
        public boolean orange;

        public Pos(int x, int y, boolean orange) {
            this.x = x;
            this.y = y;
            this.orange = orange;
        }

        public List<Pos> adj(List<Integer> size, Integer[][] grid) {
            return Arrays.asList(adjDir(1, 0, size, grid, false), adjDir(-1, 0, size, grid, false), adjDir(0, 1, size, grid, false), adjDir(0, -1, size, grid, false));
        }

        private Pos adjDir(int h, int v, List<Integer> size, Integer[][] grid, boolean sliding) {
            boolean amPurple = sliding && grid[x][y] == 4;
            if (x + h < 0 || y + v < 0 || x + h >= size.get(0) || y + v >= size.get(1)) {
                return amPurple ? this : null;
            }
            int type = grid[x + h][y + v];
            switch (type) {
                case 0:
                    return amPurple ? this : null;
                case 1:
                    return new Pos(x + h, y + v, orange);
                case 2:
                    return new Pos(x + h, y + v, true);
                case 3:
                    return amPurple ? this : orange ? new Pos(x + h, y + v, orange) : null;
                case 4:
                    return new Pos(x + h, y + v, false).adjDir(h, v, size, grid, true);
            }
            return null;
        }

        public int dist(Pos other) {
            return Math.abs(x - other.x) + Math.abs(y - other.y);
        }

        @Override
        public boolean equals(Object other) {
            return x == ((Pos) other).x && y == ((Pos) other).y && (orange == ((Pos) other).orange);
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 41 * hash + this.x;
            hash = 41 * hash + this.y;
            hash = 41 * hash + (this.orange ? 1 : 0);
            return hash;
        }

        public int h(List<Integer> size) {
            return size.get(0) + size.get(1) - x - y;
        }

        @Override
        public String toString() {
            return x + " " + y + " " + orange;
        }
    }
}
