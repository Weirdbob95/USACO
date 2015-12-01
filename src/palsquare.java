/*
 ID: rory.so1
 LANG: JAVA
 TASK: palsquare
 */

import java.io.*;

class palsquare {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("palsquare.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
        String output = "";

        int base = Integer.parseInt(in.readLine());
        for (int i = 1; i < 300; i++) {
            if (isPalindrome(numToString(i * i, base))) {
                output += numToString(i, base) + " " + numToString(i * i, base) + "\n";
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
}
