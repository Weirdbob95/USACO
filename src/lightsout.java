
import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class lightsout {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("lightsout.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lightsout.out")));

        int n = Integer.parseInt(in.readLine());
        List<List<Integer>> d = new LinkedList();
        for (int i = 0; i < n; i++) {
            d.add(tokenizeInt(in));
        }
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point();
        }
        for (int i = 0; i < n; i++) {
            List<Integer> c = d.get(i);
            List<Integer> ne = d.get((i + 1) % n);
            List<Integer> ne2 = d.get((i + 2) % n);
            int dist = Math.abs(c.get(0) - ne.get(0)) + Math.abs(c.get(1) - ne.get(1));
            points[i].dist1 = points[(i + 1) % n].dist2 = dist;
            if (dir(c, ne) != (dir(ne, ne2) + 1) % 4) {
                points[(i + 1) % n].inward = true;
            }
        }
        for (int i = 1; i < n; i++) {
            points[i].totalDist1 = points[i - 1].totalDist1 + points[i - 1].dist1;
        }
        for (int i = n - 1; i > 0; i--) {
            points[i].totalDist2 = points[(i + 1) % n].totalDist2 + points[(i + 1) % n].dist2;
        }

//        for (Point p : points) {
//            System.out.println(p);
//        }
        List<Path>[] paths = new List[n];
        for (int length = 0; length < n; length++) {
            paths[length] = new LinkedList();
            for (int startPos = 0; startPos < n - length; startPos++) {
                Path p = new Path();
                paths[length].add(p);
                p.length = length;
                p.startPos = startPos;
                p.atDoor = startPos == 0;
                p.onRight = false;
                p.dists = new int[length];
                for (int cPos = 0; cPos < length; cPos++) {
                    p.dists[cPos] = points[startPos + cPos].dist1;
                }
                p.inwards = new boolean[length + 1];
                for (int cPos = 0; cPos <= length; cPos++) {
                    p.inwards[cPos] = points[startPos + cPos].inward;
                }
            }
            if (length > 0) {
                for (int startPos = 1; startPos < n - length + 1; startPos++) {
                    Path p = new Path();
                    paths[length].add(p);
                    p.length = length;
                    p.startPos = startPos;
                    p.atDoor = startPos == n - length;
                    p.onRight = true;
                    p.dists = new int[length];
                    for (int cPos = 0; cPos < length; cPos++) {
                        p.dists[cPos] = points[startPos + cPos].dist2;
                    }
                    p.inwards = new boolean[length + 1];
                    for (int cPos = 0; cPos <= length; cPos++) {
                        if (startPos + cPos == n) {
                            p.inwards[cPos] = points[0].inward;
                        } else {
                            p.inwards[cPos] = points[startPos + cPos].inward;
                        }
                    }
                }
            }
        }
        for (int length = 1; length < n; length++) {
            for (Path p : paths[length]) {
                for (Path from : paths[length - 1]) {
                    int offset = p.onRight ? 0 : 1;
                    boolean works = true;
                    for (int cPos = 0; cPos < length; cPos++) {
                        if (from.inwards[cPos] != p.inwards[cPos + offset]) {
                            works = false;
                            break;
                        }
                        if (cPos < length - 1) {
                            if (from.dists[cPos] != p.dists[cPos + offset]) {
                                works = false;
                                break;
                            }
                        }
                    }
                    if (works) {
                        int dist = 0;
                        if (p.onRight) {
                            dist += p.dists[length - 1];
                        } else {
                            dist += p.dists[0];
                        }
                        if (p.onRight != from.onRight) {
                            for (int d2 : from.dists) {
                                dist += d2;
                            }
                        }
                        from.edges.add(p);
                        from.edgeLengths.add(dist);
                    }
                }
            }
        }

        int max = 0;
        for (int length = n - 1; length >= 0; length--) {
            for (Path p : paths[length]) {
                System.out.println(p);
                if (p.atDoor) {
                    continue;
                }
                int rMax = 0;
                for (int i = 0; i < p.edges.size(); i++) {
                    if (p.edges.get(i).onRight) {
                        rMax = Math.max(p.max, p.edges.get(i).max + p.edgeLengths.get(i));
                    }
                }
                int lMax = 0;
                for (int i = 0; i < p.edges.size(); i++) {
                    if (!p.edges.get(i).onRight) {
                        lMax = Math.max(p.max, p.edges.get(i).max + p.edgeLengths.get(i));
                    }
                }
                System.out.println(lMax + " " + rMax);
                p.max = Math.min(lMax, rMax);
                max = Math.max(max, p.max);
            }
        }
        System.out.println(max);

        int maxBad1 = 0;
        for (Point p : points) {
            maxBad1 = Math.max(maxBad1, p.totalDist1 - p.totalDist2);
        }
        int maxBad2 = 0;
        for (Point p : points) {
            maxBad2 = Math.max(maxBad2, p.totalDist2 - p.totalDist1);
        }

        out.println(Math.min(maxBad1, maxBad2));
        out.close();
    }

    public static int dir(List<Integer> p1, List<Integer> p2) {
        if (p1.get(0) < p2.get(0)) {
            return 0;
        }
        if (p1.get(1) < p2.get(1)) {
            return 1;
        }
        if (p1.get(0) > p2.get(0)) {
            return 2;
        }
        if (p1.get(1) > p2.get(1)) {
            return 3;
        }
        throw new RuntimeException();
    }

    public static class Path {

        int max;

        int startPos;
        int length;
        boolean[] inwards;
        int[] dists;
        boolean onRight;
        boolean atDoor;

        List<Path> edges = new LinkedList();
        List<Integer> edgeLengths = new LinkedList();

        @Override
        public String toString() {
            return startPos + " " + length + " " + onRight + " " + atDoor;
        }
    }

    public static class Point {

        boolean inward;
        int dist1, dist2;
        int totalDist1, totalDist2;

        @Override
        public String toString() {
            return "Point{" + "inward=" + inward + ", dist1=" + dist1 + ", dist2=" + dist2 + ", totalDist1=" + totalDist1 + ", totalDist2=" + totalDist2 + '}';
        }

    }

    public static List<Integer> tokenizeInt(BufferedReader in) throws IOException {
        List<Integer> r = new LinkedList();
        for (Object s : Collections.list(new StringTokenizer(in.readLine()))) {
            r.add(Integer.parseInt((String) s));
        }
        return r;
    }
}
