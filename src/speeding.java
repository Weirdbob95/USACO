
import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class speeding {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("speeding.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("speeding.out")));

        List<Integer> n = tokenizeInt(in);

        List<Pair> road = new LinkedList();
        for (int i = 0; i < n.get(0); i++) {
            List<Integer> info = tokenizeInt(in);
            road.add(new Pair(info.get(0), info.get(1)));
        }
        int max = 0;
        int bpos = 0;
        for (int i = 0; i < n.get(1); i++) {
            List<Integer> info = tokenizeInt(in);
            int nbpos = bpos + info.get(0);
            int rpos = 0;
            for (int j = 0; j < n.get(0); j++) {
                int end = rpos + road.get(j).a;
                if (end > bpos) {
                    max = Math.max(max, info.get(1) - road.get(j).b);
                }
                rpos = end;
                if (rpos >= nbpos) {
                    break;
                }
            }
            bpos = nbpos;
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

        @Override
        public String toString() {
            return a + " " + b;
        }
    }
}
