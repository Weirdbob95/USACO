
import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class fortmoo {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("fortmoo.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fortmoo.out")));

        List<Integer> d = tokenizeInt(in);
        int height = d.get(0);
        int width = d.get(1);
        boolean[][] swamp = new boolean[width][height];

        for (int y = 0; y < d.get(0); y++) {
            String s = in.readLine();
            for (int x = 0; x < d.get(1); x++) {
                swamp[x][y] = s.charAt(x) == 'X';
            }
        }

        int max = 0;

        //Takes n time
        for (int x1 = 0; x1 < width; x1++) {
            int[] extend = new int[height];
            int maxE = 0;
            //Takes n^2 time
            for (int y = 0; y < height; y++) {
                for (int x2 = x1 + 1; x2 < width && !swamp[x2][y]; x2++) {
                    extend[y]++;
                    maxE = Math.max(maxE, extend[y]);
                }
            }
            //Takes n^2 time
            for (int x2 = x1 + maxE; x2 > x1; x2--) {
                int top = -1;
                for (int y = 0; y < height; y++) {
                    if (swamp[x1][y] || swamp[x2][y]) {
                        top = -1;
                    } else if (x1 + extend[y] >= x2) {
                        if (top == -1) {
                            top = y;
                        } else {
                            max = Math.max((y - top + 1) * (x2 - x1 + 1), max);
                            //System.out.println((y - top + 1) * (x2 - x1 + 1) + " " + x1 + " " + x2 + " " + top + " " + y);
                        }
                    }
                }
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
