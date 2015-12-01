/*
 ID: rory.so1
 LANG: JAVA
 TASK: milk2
 */

import java.io.*;
import java.util.*;

class milk2 {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("milk2.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));

        int n = Integer.parseInt(in.readLine());
        List<Pair<Integer, Boolean>> times = new LinkedList();

        for (int i = 0; i < n; i++) {
            List<String> tokens = tokenize(in);
            times.add(new Pair(Integer.parseInt(tokens.get(0)), true));
            times.add(new Pair(Integer.parseInt(tokens.get(1)), false));
        }

        Collections.sort(times, new Comparator<Pair<Integer, Boolean>>() {
            @Override
            public int compare(Pair<Integer, Boolean> o1, Pair<Integer, Boolean> o2) {
                if (o1.a.equals(o2.a)) {
                    return o1.b ? -1 : 1;
                }
                return o1.a - o2.a;
            }
        });

        int maxTime = 0;
        int maxGap = 0;
        int count = 0;
        int prev = -1;
        for (Pair<Integer, Boolean> p : times) {
            if (p.b) {
                count++;
                if (count == 1) {
                    if (prev >= 0) {
                        maxGap = Math.max(maxGap, p.a - prev);
                    }
                    prev = p.a;
                }
            } else {
                count--;
                if (count == 0) {
                    if (prev >= 0) {
                        maxTime = Math.max(maxTime, p.a - prev);
                    }
                    prev = p.a;
                }
            }
        }

        String output = maxTime + " " + maxGap;

        out.println(output);
        out.close();
    }

    public static List<String> tokenize(BufferedReader in) throws IOException {
        return (List) Collections.list(new StringTokenizer(in.readLine()));
    }

    public static class Pair<S, T> {

        public S a;
        public T b;

        public Pair(S one, T two) {
            this.a = one;
            this.b = two;
        }

        @Override
        public String toString() {
            return "[" + a + ", " + b + "]";
        }
    }
}
