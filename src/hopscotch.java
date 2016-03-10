
import java.io.*;
import java.util.*;

public class hopscotch {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("hopscotch.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hopscotch.out")));

        List<Integer> d = tokenizeInt(in);

        List<List<Integer>> grid = new ArrayList(d.get(0));
        for (int i = 0; i < d.get(0); i++) {
            grid.add(tokenizeInt(in));
        }

        int[][] paths = new int[d.get(0)][d.get(1)];
        paths[0][0] = 1;
        for (int x = 0; x < d.get(0); x++) {
            for (int y = 0; y < d.get(1); y++) {
                int p = paths[x][y];
                if (p > 0) {
                    int n = grid.get(x).get(y);
                    for (int x2 = x + 1; x2 < d.get(0); x2++) {
                        for (int y2 = y + 1; y2 < d.get(1); y2++) {
                            if (n != grid.get(x2).get(y2)) {
                                paths[x2][y2] += p;
                            }
                        }
                    }
                }
            }
        }

        out.println(paths[d.get(0) - 1][d.get(1) - 1]);
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
