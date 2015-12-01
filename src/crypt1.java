/*
 ID: rory.so1
 LANG: JAVA
 TASK: crypt1
 */

import java.io.*;
import java.util.*;

class crypt1 {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("crypt1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
        String output = "";

        in.readLine();
        List<Integer> digits = new LinkedList();
        for (String s : tokenize(in)) {
            digits.add(Integer.parseInt(s));
        }
        int count = 0;
        System.out.println(digits);

        for (int n = 100; n < 1000; n++) {
            if (isLegal(n, digits)) {
                for (int a = 1; a < 10; a++) {
                    if (isLegal(a, digits)) {
                        for (int b = 0; b < 10; b++) {
                            if (isLegal(b, digits)) {
                                if ((int) Math.log10(n * a) == 2 && (int) Math.log10(n * b) == 2 && (int) Math.log10(n * (10 * a + b)) == 3) {
                                    if (isLegal(n * a, digits) && isLegal(n * b, digits) && isLegal(n * (10 * a + b), digits)) {
                                        System.out.println(n + " " + a + "" + b);
                                        System.out.println(n * a + " " + n * b + " " + n * (10 * a + b));
                                        count++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        output += count;

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
