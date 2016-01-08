
import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class paint {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("paint.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("paint.out")));

        List<Integer> fj = tokenizeInt(in);
        List<Integer> be = tokenizeInt(in);

        int max = Math.max(fj.get(1), be.get(1));
        int min = Math.min(fj.get(0), be.get(0));
        int f = max - min - Math.max(0, Math.max(fj.get(0) - be.get(1), be.get(0) - fj.get(1)));

        out.println(f);
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
