
import java.io.*;
import java.util.*;

public class fencedin {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("fencedin.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fencedin.out")));

        List<Integer> l = tokenizeInt(in);

        TreeSet<Integer> vertPos = new TreeSet(), horPos = new TreeSet();
        for (int i = 0; i < l.get(2); i++) {
            vertPos.add(Integer.parseInt(in.readLine()));
        }
        vertPos.add(l.get(0));
        for (int i = 0; i < l.get(3); i++) {
            horPos.add(Integer.parseInt(in.readLine()));
        }
        horPos.add(l.get(1));

        TreeSet<Line> lines = new TreeSet();
        int prev = 0;
        for (int x : vertPos) {
            lines.add(new Line(x - prev, false));
            prev = x;
        }
        prev = 0;
        for (int x : horPos) {
            lines.add(new Line(x - prev, true));
            prev = x;
        }

        long removed = 0;

        long vCount = -1, hCount = -1;
        for (Line line : lines) {
            if (line.isHorizontal) {
                if (hCount == -1) {
                    removed += l.get(2) * line.width;
                } else {
                    removed += (l.get(2) - Math.max(0, vCount)) * line.width;
                }
                hCount++;
            } else {
                if (vCount == -1) {
                    removed += l.get(3) * line.width;
                } else {
                    removed += (l.get(3) - Math.max(0, hCount)) * line.width;
                }
                vCount++;
            }
        }

        out.println(removed);
        out.close();
    }

    public static class Line implements Comparable<Line> {

        long width;
        boolean isHorizontal;

        public Line(long width, boolean isHorizontal) {
            this.width = width;
            this.isHorizontal = isHorizontal;
        }

        @Override
        public int compareTo(Line o) {
            if (width == o.width) {
                return 1;
            }
            return (int) (width - o.width);
        }

        @Override
        public String toString() {
            return "Line{" + width + " " + isHorizontal + '}';
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
