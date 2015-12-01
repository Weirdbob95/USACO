/*
 ID: rory.so1
 LANG: JAVA
 TASK: numtri
 */

import java.io.*;
import java.util.*;

class numtri {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("numtri.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
        String output = "";

        int r = Integer.parseInt(in.readLine());
        List<Integer> costs = tokenizeInt(in);
        for (int i = 1; i < r; i++) {
            List<Integer> newCosts = new ArrayList(tokenizeInt(in));
            newCosts.set(0, newCosts.get(0) + costs.get(0));
            for (int j = 1; j < i; j++) {
                newCosts.set(j, newCosts.get(j) + Math.max(costs.get(j), costs.get(j - 1)));
            }
            newCosts.set(i, newCosts.get(i) + costs.get(i - 1));
            costs = newCosts;
        }
        int max = costs.get(0);
        for (int i = 1; i < r; i++) {
            max = Math.max(max, costs.get(i));
        }
        output += max;
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
