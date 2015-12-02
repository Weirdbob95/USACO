/*
 ID: rory.so1
 LANG: JAVA
 TASK: sprime
 */

import java.io.*;

class sprime {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("sprime.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));

        int n = Integer.parseInt(in.readLine());
        r(n, 0, out);

        out.close();
    }

    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void r(int n, int num, PrintWriter out) {
        if (n == 0) {
            out.println(num);
        } else {
            for (int d = 1; d < 10; d++) {
                if (isPrime(10 * num + d)) {
                    r(n - 1, 10 * num + d, out);
                }
            }
        }
    }
}
