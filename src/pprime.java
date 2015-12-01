/*
 ID: rory.so1
 LANG: JAVA
 TASK: pprime
 */

import java.io.*;
import java.util.*;

class pprime {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("pprime.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));

        List<Integer> range = tokenizeInt(in);
        int a = range.get(0), b = range.get(1);

//        BitSet primes = new BitSet(b + 1);
//        primes.set(2);
//        primes.set(3);
//        primes.set(5);
//        int[] s = new int[]{1, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 49, 53, 59};
//        int[] arr = new int[]{
//            0, 1, 0, 0, 0, 0, 0, 2, 0, 0,
//            0, 3, 0, 1, 0, 0, 0, 1, 0, 2,
//            0, 0, 0, 3, 0, 0, 0, 0, 0, 1,
//            0, 2, 0, 0, 0, 0, 0, 1, 0, 0,
//            0, 1, 0, 2, 0, 0, 0, 3, 0, 1,
//            0, 0, 0, 1, 0, 0, 0, 0, 0, 3
//        };
//        for (int x = 1; 4 * x * x <= b; x++) {
//            for (int y = 1; 4 * x * x + y * y <= b; y += 2) {
//                int n = 4 * x * x + y * y;
//                if (arr[n % 60] == 1) {
//                    primes.flip(n);
//                }
//            }
//        }
//        for (int x = 1; 3 * x * x <= b; x += 2) {
//            for (int y = 2; 3 * x * x + y * y <= b; y += 2) {
//                int n = 3 * x * x + y * y;
//                if (arr[n % 60] == 2) {
//                    primes.flip(n);
//                }
//            }
//        }
//        for (int x = 1; 2 * x * x <= b; x++) {
//            for (int y = x - 1; y > 0 && 3 * x * x - y * y <= b; y -= 2) {
//                int n = 3 * x * x - y * y;
//                if (arr[n % 60] == 3) {
//                    primes.flip(n);
//                }
//            }
//        }
//        for (int w = 0; w * w <= b; w += 60) {
//            for (int x : s) {
//                int n = w + x;
//                if (n * n <= b) {
//                    if (primes.get(n)) {
//                        for (int w2 = 0; n * n * w2 <= b; w2 += 60) {
//                            for (int x2 : s) {
//                                int n2 = n * n * (w2 + x2);
//                                if (n2 > 0 && n2 <= b) {
//                                    primes.set(n2, false);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        for (int digits = 1; digits <= Math.log10(b) + 1; digits++) {
//            printPalins(digits, 0, 0, a, b, primes, out);
//        }
        for (int digits = 1; digits <= Math.log10(b) + 1; digits++) {
            r(digits, 0, 0, a, b, out);
        }
        out.close();
    }

    public static boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void printPalins(int digits, int current, int n, int a, int b, BitSet primes, PrintWriter out) {
        if (current >= digits / 2.) {
            if (a <= n && n <= b) {
                if (primes.get(n)) {
                    out.println(n);
                }
            }
            return;
        }
        int min = (current == 0) ? 1 : 0;
        for (int d = min; d < 10; d++) {
            if (current * 2 + 1 == digits) {
                printPalins(digits, current + 1, n + d * (int) Math.pow(10, current), a, b, primes, out);
            } else {
                printPalins(digits, current + 1, n + d * (int) (Math.pow(10, current) + Math.pow(10, digits - current - 1)), a, b, primes, out);
            }
        }
    }

    public static void r(int digits, int current, int n, int a, int b, PrintWriter out) {
        if (current >= digits / 2.) {
            if (a <= n && n <= b) {
                if (isPrime(n)) {
                    out.println(n);
                }
            }
            return;
        }
        int min = (current == 0) ? 1 : 0;
        for (int d = min; d < 10; d++) {
            if (current * 2 + 1 == digits) {
                r(digits, current + 1, n + d * (int) Math.pow(10, current), a, b, out);
            } else {
                r(digits, current + 1, n + d * (int) (Math.pow(10, current) + Math.pow(10, digits - current - 1)), a, b, out);
            }
        }
    }

    public static List<Integer> tokenizeInt(BufferedReader in) throws IOException {
        List<Integer> r = new LinkedList();
        for (Object s : Collections.list(new StringTokenizer(in.readLine()))) {
            r.add(Integer.parseInt((String) s));
        }
        return r;
    }
}
