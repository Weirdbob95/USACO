/*
 ID: rory.so1
 LANG: JAVA
 TASK: wormhole
 */

import java.io.*;
import java.util.*;

class wormhole {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("wormhole.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
        String output = "";

        int n = Integer.parseInt(in.readLine());
        List<Integer>[] wormholes = new List[n];
        for (int i = 0; i < n; i++) {
            wormholes[i] = tokenizeInt(in);
        }

        Queue<int[]> pairs = new LinkedList();
        int[] start = new int[n];
        Arrays.fill(start, -1);
        pairs.add(start);
        for (int i = 0; i < n; i++) {
            Queue<int[]> newPairs = new LinkedList();
            for (int[] a : (List<int[]>) new ArrayList(pairs)) {
                if (a[i] == -1) {
                    for (int j = i + 1; j < n; j++) {
                        if (a[j] == -1) {
                            int[] na = Arrays.copyOf(a, n);
                            na[i] = j;
                            na[j] = i;
                            newPairs.add(na);
                        }
                    }
                } else {
                    newPairs.add(a);
                }
            }
            pairs = newPairs;
        }

        int count = 0;
        for (int[] a : pairs) {
            int[] to = new int[n];
            Arrays.fill(to, -1);
            for (int i = 0; i < n; i++) {
                int minX = -1;
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        if (wormholes[i].get(1).equals(wormholes[j].get(1))) {
                            int x = wormholes[j].get(0);
                            if (x > wormholes[i].get(0)) {
                                if (minX == -1 || x < minX) {
                                    minX = x;
                                    to[i] = a[j];
                                }
                            }
                        }
                    }
                }
            }

            //System.out.println(Arrays.toString(a));
            //System.out.println(Arrays.toString(to));
            for (int i = 0; i < n; i++) {
                int pos = i;
                for (int rep = 0; rep < n; rep++) {
                    pos = to[pos];
                    if (pos == -1) {
                        break;
                    }
                }
                if (pos != -1) {
                    count++;
                    break;
                }
            }
        }

        output += count;
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
