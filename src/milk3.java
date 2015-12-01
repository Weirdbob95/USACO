/*
 ID: rory.so1
 LANG: JAVA
 TASK: milk3
 */

import java.io.*;
import java.util.*;

class milk3 {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("milk3.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
        String output = "";

        List<Integer> buckets = tokenizeInt(in);
        HashSet<List<Integer>> values = new HashSet();
        Queue<List<Integer>> toCheck = new LinkedList();
        toCheck.add(Arrays.asList(0, 0, buckets.get(2)));

        while (!toCheck.isEmpty()) {
            List<Integer> b = toCheck.poll();
            if (values.add(b)) {
                for (int from = 0; from < 3; from++) {
                    for (int to = 0; to < 3; to++) {
                        if (from != to && b.get(from) > 0 && !b.get(to).equals(buckets.get(to))) {
                            int amt = Math.min(buckets.get(to) - b.get(to), b.get(from));
                            List<Integer> newB = new ArrayList(b);
                            newB.set(to, b.get(to) + amt);
                            newB.set(from, b.get(from) - amt);
                            toCheck.add(newB);
                        }
                    }
                }
            }
        }

//        for (int i = 0; i < 10; i++) {
//            int[] b = new int[3];
//            b[2] = buckets.get(2);
//            for (int j = 0; j < i; j++) {
//                for (int from = 0; from < 3; from++) {
//                    for (int to = 0; to < 3; to++) {
//                        if (from != to && b[from] > 0 && b[to] != buckets.get(to)) {
//                            int amt = Math.min(buckets.get(to) - b[to], b[from]);
//                            b[to] += amt;
//                            b[from] -= amt;
//                            //System.out.println(from + " " + to + " " + amt);
//                            //System.out.println(Arrays.toString(b));
//                            List<Integer> l = new ArrayList(3);
//                            for (int x = 0; x < 3; x++) {
//                                l.add(b[x]);
//                            }
//                            values.add(l);
//                        }
//                    }
//                }
//            }
//        }
        Set<Integer> o = new TreeSet();
        for (List<Integer> l : values) {
            if (l.get(0) == 0) {
                o.add(l.get(2));
            }
        }
        for (int i : o) {
            output += i + " ";
        }

        output = output.substring(0, output.length() - 1);
        out.println(output);
        out.close();
    }

    public static List<Integer> tokenizeInt(BufferedReader in) throws IOException {
        List<Integer> r = new LinkedList();
        for (Object s : Collections.list(new StringTokenizer(in.readLine()))) {
            r.add(Integer.parseInt((String) s));
        }
        return r;
    }
}
