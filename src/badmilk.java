
import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class badmilk {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("badmilk.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("badmilk.out")));

        List<Integer> n = tokenizeInt(in);

        int[][] drinks = new int[n.get(0)][n.get(1)];

        for (int i = 0; i < n.get(2); i++) {
            List<Integer> d = tokenizeInt(in);
            int t = drinks[d.get(0) - 1][d.get(1) - 1];
            if (t == 0 || t > d.get(2)) {
                drinks[d.get(0) - 1][d.get(1) - 1] = d.get(2);
            }
        }
        List<List<Integer>> sick = new LinkedList();
        for (int i = 0; i < n.get(3); i++) {
            sick.add(tokenizeInt(in));
        }
        int max = 0;
        for (int bm = 0; bm < n.get(1); bm++) {
            boolean works = true;
            for (List<Integer> s : sick) {
                int t = drinks[s.get(0) - 1][bm];
                if (t == 0 || t >= s.get(1)) {
                    works = false;
                    break;
                }
            }
            if (works) {
                int c = 0;
                for (int i = 0; i < n.get(0); i++) {
                    if (drinks[i][bm] > 0) {
                        c++;
                    }
                }
                max = Math.max(max, c);
            }
        }

        out.println(max);
        out.close();
    }

    public static List<Integer> tokenizeInt(BufferedReader in) throws IOException {
        List<Integer> r = new LinkedList();
        for (Object s : Collections.list(new StringTokenizer(in.readLine()))) {
            r.add(Integer.parseInt((String) s));
        }
        return r;
    }
}
