/*
 ID: rory.so1
 LANG: JAVA
 TASK: dualpal
 */

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class dualpal {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("dualpal.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
        String output = "";

        List<String> tokens = tokenize(in);
        int n = Integer.parseInt(tokens.get(0));
        int s = Integer.parseInt(tokens.get(1));

        for (int i = 0; i < n; i++) {
            while (true) {
                s++;
                int c = 0;
                for (int base = 2; base <= 10; base++) {
                    if (isPalindrome(numToString(s, base))) {
                        c++;
                        if (c == 2) {
                            break;
                        }
                    }
                }
                if (c == 2) {
                    output += s + "\n";
                    break;
                }
            }
        }

        output = output.substring(0, output.length() - 1);
        out.println(output);
        out.close();
    }

    public static boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static String numToString(int n, int base) {
        String r = "";
        for (int i = 0; n >= Math.pow(base, i); i++) {
            int digit = (n / (int) Math.pow(base, i)) % base;
            if (digit < 10) {
                r = digit + r;
            } else {
                r = (char) (digit + 55) + r;
            }
        }
        return r;
    }

    public static List<String> tokenize(BufferedReader in) throws IOException {
        return (List) Collections.list(new StringTokenizer(in.readLine()));
    }
}
