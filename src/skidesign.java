/*
 ID: rory.so1
 LANG: JAVA
 TASK: skidesign
 */

import java.io.*;
import java.util.LinkedList;
import java.util.List;

class skidesign {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("skidesign.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
        String output = "";

        int n = Integer.parseInt(in.readLine());
        List<Integer> heights = new LinkedList();
        for (int i = 0; i < n; i++) {
            heights.add(Integer.parseInt(in.readLine()));
        }

        int minCost = Integer.MAX_VALUE;
        for (int i = 17; i <= 100; i++) {
            int cost = 0;
            boolean good = true;
            for (int height : heights) {
                if (height > i) {
                    cost += (height - i) * (height - i);
                    if (cost > minCost) {
                        good = false;
                        break;
                    }
                } else if (height < i - 17) {
                    cost += (height - i + 17) * (height - i + 17);
                    if (cost > minCost) {
                        good = false;
                        break;
                    }
                }
            }
            if (good) {
                minCost = cost;
            }
        }
        output += minCost;

        //output = output.substring(0, output.length() - 1);
        out.println(output);
        out.close();
    }
}
