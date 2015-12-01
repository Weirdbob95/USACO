/*
 ID: rory.so1
 LANG: JAVA
 TASK: combo
 */

import java.io.*;
import java.util.*;

class combo {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("combo.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));
        String output = "";

        int n = Integer.parseInt(in.readLine());
        List<Integer> fj = new LinkedList();
        for (String s : tokenize(in)) {
            fj.add(Integer.parseInt(s));
        }
        List<Integer> master = new LinkedList();
        for (String s : tokenize(in)) {
            master.add(Integer.parseInt(s));
        }

        int dif0 = Math.abs(fj.get(0) - master.get(0));
        int dif1 = Math.abs(fj.get(1) - master.get(1));
        int dif2 = Math.abs(fj.get(2) - master.get(2));
        if (dif0 > n / 2) {
            dif0 = n - dif0;
        }
        if (dif1 > n / 2) {
            dif1 = n - dif1;
        }
        if (dif2 > n / 2) {
            dif2 = n - dif2;
        }

        if (n > 5) {
            if (dif0 < 5 && dif1 < 5 && dif2 < 5) {
                output += 250 - (5 - dif0) * (5 - dif1) * (5 - dif2);
            } else {
                output += 250;
            }
        } else {
            output += n * n * n;
        }

        //output = output.substring(0, output.length() - 1);
        out.println(output);
        out.close();
    }

    public static boolean isLegal(int n, List<Integer> digits) {
        for (int i = 0; Math.pow(10, i) <= n; i++) {
            int digit = (n / (int) Math.pow(10, i)) % 10;
            if (!digits.contains(digit)) {
                return false;
            }
        }
        return true;
    }

    public static List<String> tokenize(BufferedReader in) throws IOException {
        return (List) Collections.list(new StringTokenizer(in.readLine()));
    }
}
