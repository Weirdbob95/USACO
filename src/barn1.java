/*
 ID: rory.so1
 LANG: JAVA
 TASK: barn1
 */

import java.io.*;
import java.util.*;

class barn1 {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("barn1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
        String output = "";

        List<String> tokens = tokenize(in);
        int boards = Integer.parseInt(tokens.get(0));
        int cows = Integer.parseInt(tokens.get(2));

        Queue<Integer> cowPos = new PriorityQueue();
        for (int i = 0; i < cows; i++) {
            cowPos.add(Integer.parseInt(in.readLine()));
        }

        Queue<Integer> q = new PriorityQueue();
        int pos = cowPos.poll();
        int firstCow = pos;
        for (int i = 1; i < cows; i++) {
            int end = cowPos.poll();
            q.add(pos + 1 - end);
            pos = end;
        }

        int cost = pos - firstCow + 1;
        for (int i = 1; i < boards; i++) {
            if (q.isEmpty()) {
                break;
            }
            cost += q.poll();
        }
        output += cost;

        //output = output.substring(0, output.length() - 1);
        out.println(output);
        out.close();
    }

    public static List<String> tokenize(BufferedReader in) throws IOException {
        return (List) Collections.list(new StringTokenizer(in.readLine()));
    }
}
