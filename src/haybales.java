
import java.io.*;
import java.util.*;

public class haybales {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("haybales.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("haybales.out")));

        List<Integer> a = tokenizeInt(in);

        Integer[] field = tokenizeInt(in).toArray(new Integer[a.get(0)]);
        TreeSet<Range> ranges = new TreeSet();
        List<Query> queries = new LinkedList();

        for (int i = 0; i < a.get(1); i++) {
            char c = (char) in.read();
            List<Integer> l = tokenizeInt(in);
            int min = l.get(0) - 1;
            int max = l.get(1);
            queries.add(new Query(c, l, min, max));

            ranges.add(new Range(min));
            ranges.add(new Range(max));
        }
        Range prev = null;
        Iterator<Range> i = ranges.iterator();
        while (true) {
            Range r = i.next();
            if (prev != null) {
                prev.end = r.start;
                prev.calc(field);
            }
            prev = r;
            if (!i.hasNext()) {
                i.remove();
                break;
            }
        }
        for (Query q : queries) {
            q.run(ranges, out);
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

    public static class Range implements Comparable<Range> {

        public int start, end;
        public int min, sum;

        public Range(int start) {
            this.start = start;
        }

        public void calc(Integer[] field) {
            min = Integer.MAX_VALUE;
            sum = 0;
            for (int i = start; i < end; i++) {
                sum += field[i];
                min = Math.min(min, field[i]);
            }
        }

        @Override
        public int compareTo(Range o) {
            return start - o.start;
        }

        @Override
        public boolean equals(Object other) {
            return start == ((Range) other).start;
        }

        public void place(int amt) {
            min += amt;
            sum += amt * (end - start);
        }

        @Override
        public String toString() {
            return "Range{" + "start=" + start + ", end=" + end + ", min=" + min + ", sum=" + sum + '}';
        }
    }

    public static class Query {

        public int c;
        public List<Integer> data;
        public int min, max;

        public Query(int c, List<Integer> data, int min, int max) {
            this.c = c;
            this.data = data;
            this.min = min;
            this.max = max;
        }

        public void run(TreeSet<Range> ranges, PrintWriter out) {
            switch (c) {
                case 'M':
                    int r = Integer.MAX_VALUE;
                    for (Range f : ranges.subSet(new Range(min), new Range(max))) {
                        r = Math.min(r, f.min);
                    }
                    out.println(r);
                    break;
                case 'S':
                    int r1 = 0;
                    for (Range f : ranges.subSet(new Range(min), new Range(max))) {
                        r1 += f.sum;
                    }
                    out.println(r1);
                    break;
                case 'P':
                    int h = data.get(2);
                    for (Range f : ranges.subSet(new Range(min), new Range(max))) {
                        f.place(h);
                    }
            }
        }
    }
}
