/*
 ID: rory.so1
 LANG: JAVA
 TASK: castle
 */

import java.io.*;
import java.util.*;

class castle {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("castle.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));

        List<Integer> size = tokenizeInt(in);
        int w = size.get(0), h = size.get(1);
        List<List<Integer>> walls = new ArrayList();
        for (int i = 0; i < h; i++) {
            walls.add(tokenizeInt(in));
        }

        List<List<Pair>> rooms = new LinkedList();
        int[][] regions = new int[w][h];
        int largest = 0;
        Queue<Pair> toCheck = new LinkedList();
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                toCheck.add(new Pair(x, y));
            }
        }
        while (!toCheck.isEmpty()) {
            List<Pair> room = new LinkedList();
            rooms.add(room);
            Queue<Pair> toAdd = new LinkedList();
            toAdd.add(toCheck.poll());
            while (!toAdd.isEmpty()) {
                Pair p = toAdd.poll();
                if (room.contains(p)) {
                    continue;
                }
                //System.out.println(p + " " + walls.get(p.b).get(p.a));
                room.add(p);
                toCheck.remove(p);
                regions[p.a][p.b] = rooms.size() - 1;
                if ((walls.get(p.b).get(p.a) & 1) == 0) {
                    toAdd.add(new Pair(p.a - 1, p.b));
                }
                if ((walls.get(p.b).get(p.a) & 2) == 0) {
                    toAdd.add(new Pair(p.a, p.b - 1));
                }
                if ((walls.get(p.b).get(p.a) & 4) == 0) {
                    toAdd.add(new Pair(p.a + 1, p.b));
                }
                if ((walls.get(p.b).get(p.a) & 8) == 0) {
                    toAdd.add(new Pair(p.a, p.b + 1));
                }
            }
            largest = Math.max(largest, room.size());
        }

        int largestCombo = 0;
        String best = "";
        for (int x = 0; x < w; x++) {
            for (int y = h - 1; y >= 0; y--) {
                if (y > 0) {
                    if ((walls.get(y).get(x) & 2) != 0) {
                        int r1 = regions[x][y], r2 = regions[x][y - 1];
                        if (r1 != r2) {
                            int lc = rooms.get(r1).size() + rooms.get(r2).size();
                            if (lc > largestCombo) {
                                largestCombo = lc;
                                best = y + 1 + " " + (x + 1) + " N";
                            }
                        }
                    }
                }
                if (x < w - 1) {
                    if ((walls.get(y).get(x) & 4) != 0) {
                        int r1 = regions[x][y], r2 = regions[x + 1][y];
                        if (r1 != r2) {
                            int lc = rooms.get(r1).size() + rooms.get(r2).size();
                            if (lc > largestCombo) {
                                largestCombo = lc;
                                best = y + 1 + " " + (x + 1) + " E";
                            }
                        }
                    }
                }
            }
        }

        out.println(rooms.size());
        out.println(largest);
        out.println(largestCombo);
        out.println(best);
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
        public boolean equals(Object other) {
            return a == ((Pair) other).a && b == ((Pair) other).b;
        }

        @Override
        public String toString() {
            return a + " " + b;
        }
    }
}
