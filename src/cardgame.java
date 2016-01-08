
import java.io.*;
import java.util.TreeSet;

public class cardgame {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("cardgame.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cardgame.out")));

        int n = Integer.parseInt(in.readLine());

        boolean[] owner = new boolean[2 * n];
        int[] plays = new int[n];

        for (int i = 0; i < n; i++) {
            int c = Integer.parseInt(in.readLine()) - 1;
            owner[c] = true;
            plays[i] = c;
        }

        int[] score = new int[n + 1];
        TreeSet<Integer> bessie = new TreeSet();
        for (int i = 0; i < 2 * n; i++) {
            if (!owner[i]) {
                bessie.add(i);
            }
        }
        TreeSet<Integer> bessie2 = new TreeSet(bessie);

        for (int i = 0; i < n; i++) {
            Integer j = bessie.ceiling(plays[i]);
            if (j != null) {
                bessie.remove(j);
                score[i + 1]++;
            }
            score[i + 1] += score[i];
        }

        int maxP = 0;
        int max = 0;
        for (int i = n - 1; i >= 0; i--) {
            Integer j = bessie2.floor(plays[i]);
            if (j != null) {
                bessie2.remove(j);
                max++;
            }
            maxP = Math.max(maxP, score[i] + max);
//                score[i] += max;
        }

//        int max = 0;
//        for (int i : score) {
//            max = Math.max(max, i);
//        }
        out.println(maxP);
        out.close();
    }
}
