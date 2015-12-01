/*
 ID: rory.so1
 LANG: JAVA
 TASK: milk
 */

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class milk {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("milk.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
        String output = "";

        List<String> tokens = tokenize(in);
        int milk = Integer.parseInt(tokens.get(0));
        int n = Integer.parseInt(tokens.get(1));
        LinkedList<Pair> farmers = new LinkedList();
        for (int i = 0; i < n; i++) {
            tokens = tokenize(in);
            farmers.add(new Pair(Integer.parseInt(tokens.get(0)), Integer.parseInt(tokens.get(1))));
        }
        Collections.sort(farmers);

        int totalCost = 0;
        while (milk > 0) {
            Pair p = farmers.poll();
            if (p.b > milk) {
                totalCost += p.a * milk;
                milk = 0;
            } else {
                totalCost += p.a * p.b;
                milk -= p.b;
            }
        }
        output += totalCost;

        //output = output.substring(0, output.length() - 1);
        out.println(output);
        out.close();
    }

    public static List<String> tokenize(BufferedReader in) throws IOException {
        return (List) Collections.list(new StringTokenizer(in.readLine()));
    }

    public static class Pair implements Comparable {

        public int a, b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Object o) {
            return a - ((Pair) o).a;
        }
    }
}
