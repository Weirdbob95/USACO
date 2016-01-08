
import java.io.*;
import java.util.*;

public class feast {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("feast.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("feast.out")));

        List<Integer> li = tokenizeInt(in);
        int t = li.get(0);
        int a = li.get(1);
        int b = li.get(2);

        int max = 0;
        List<Integer> wl = new LinkedList(Arrays.asList(0, a / 2, b / 2));
        if (a + b <= t) {
            wl.add((a + b) / 2);
        }
        for (int w : wl) {
            for (int i = 0; i < b && w + a * i <= t; i++) {
                int j = (t - w - a * i) / b;
                int n = w + a * i + b * j;
                max = Math.max(max, n);
            }
        }
        out.println(max);
        out.close();

//        int s = Math.max(0, (t / lcm) * lcm - lcm);
//        int max = s;
//        for (int i = 0; a * i / 2 <= t - s; i++) {
//            for (int j = 2 * (t - s - a * i / 2) / b;; j--) {
//                if ((i % 2) * a + (j % 2) * b <= t) {
//                    int n = s + (i * a + j * b) / 2;
//                    max = Math.max(max, n);
//                    if (n >= t) {
//                        System.out.println(i + " " + j);
//                    }
//                    break;
//                }
//            }
//        }
//        out.println(max);
//        out.close();
//
//        if (t % g == 0 && t > a * b - a - b) {
//            out.println(t);
//        } else {
//            Set<Integer> h = new TreeSet();
//            for (int i = 0; i < 2 * b; i++) {
//                for (int j = 0; i * a + j * b < 2 * lcm; j++) {
//                    if ((i * a + j * b) / 2 <= t) {
//                        if ((i % 2) * a + (j % 2) * b < t) {
//                            h.add((i * a + j * b) / 2);
//                        }
//                    } else {
//                        break;
//                    }
//                }
//            }
//            //System.out.println(h);
//
//            int s = (t / lcm) * lcm;
//            int max = s;
//            for (int i : h) {
//                if (s + i <= t) {
//                    max = s + i;
//                } else {
//                    break;
//                }
//            }
//            out.println(max);
//        }
//        out.close();
    }

    public static int gcd(int a, int b) {
        if (Math.min(a, b) == 0) {
            return Math.max(a, b);
        }
        return gcd(Math.max(a, b) % Math.min(a, b), Math.min(a, b));
    }

    public static List<Integer> tokenizeInt(BufferedReader in) throws IOException {
        List<Integer> r = new LinkedList();
        for (Object s : Collections.list(new StringTokenizer(in.readLine()))) {
            r.add(Integer.parseInt((String) s));
        }
        return r;
    }
}
