
import java.io.*;
import java.util.*;

public class trapped {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("trapped.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("trapped.out")));

        n = Integer.parseInt(in.readLine());
        heights = new int[n];
        pos = new int[n];
        arrows = new Arrow[n - 1];

        Stack<Arrow> toCheck = new Stack();
        {
            List<List<Integer>> all = new ArrayList(n);
            for (int i = 0; i < n; i++) {
                all.add(tokenizeInt(in));
            }

            Collections.sort(all, new Comparator<List<Integer>>() {
                @Override
                public int compare(List<Integer> o1, List<Integer> o2) {
                    return o1.get(1) - o2.get(1);
                }
            });

            for (int i = 0; i < n; i++) {
                List<Integer> dat = all.get(i);
                System.out.println(i + ": " + dat);
                heights[i] = dat.get(0);
                pos[i] = dat.get(1);
                if (i > 0) {
                    Arrow a = new Arrow();
                    a.leftID = a.oLeft = a.oRight = i - 1;
                    a.rightID = i;
                    a.winLength = a.length();
                    a.calcCan();
                    arrows[i - 1] = a;
                    toCheck.add(a);
                }
            }
        }

        int total = 0;

        for (Arrow a = toCheck.pop();; a = toCheck.pop()) {
            if (a.win()) {
                total += a.winLength;
                a.winLength = 0;
            }
            if (a.rightID == n - 1) {
                continue;
            }
            Arrow next = arrows[a.rightID];
            if (a.canRight || next.canLeft) {
                Arrow na = new Arrow();
                na.leftID = a.leftID;
                na.rightID = next.rightID;

                if (a.canRight) {
                    arrows[a.oLeft] = arrows[a.oRight] = na;
                    na.oLeft = a.oLeft;
                    na.oRight = a.oRight;
                    na.winLength += a.winLength;
                    a.winLength = 0;
                }
                if (next.canLeft) {
                    arrows[next.oLeft] = arrows[next.oRight] = na;
                    if (!a.canRight) {
                        na.oLeft = next.oLeft;
                    }
                    na.oRight = next.oRight;
                    na.winLength += next.winLength;
                    next.winLength = 0;
                }
                na.calcCan();
                toCheck.push(na);
            }
            if (toCheck.isEmpty()) {
                break;
            }
        }

//        while (true) {
//            int i = 0;
//            int c = 0;
//            while (true) {
//                Arrow a = arrows[i];
//                if (a.rightID == n - 1) {
//                    break;
//                }
//                Arrow next = arrows[a.rightID];
//                if (a.canRight || next.canLeft) {
//                    c++;
//                    Arrow n = new Arrow();
//                    n.leftID = a.leftID;
//                    n.rightID = next.rightID;
//                    if (a.canRight) {
//                        n.oLeft = a.oLeft;
//                        n.oRight = a.oRight;
//                        n.winLength += a.winLength;
//                        a.winLength = 0;
//                    }
//                    if (next.canLeft) {
//                        if (!a.canRight) {
//                            n.oLeft = next.oLeft;
//                        }
//                        n.oRight = next.oRight;
//                        n.winLength += next.winLength;
//                        next.winLength = 0;
//                    }
//                    n.calcCan();
//                    arrows[n.oLeft] = n;
//                    arrows[n.oRight - 1] = n;
//                } else {
//                    i++;
//                }
//            }
//            if (c == 0) {
//                break;
//            }
//        }
//        int o = 0;
//        int c2 = pos[n - 1] - pos[0];
//        for (Arrow a : arrows) {
//            if (!a.win()) {
//                o += a.winLength;
//            } else {
//                c2 -= a.winLength;
//            }
//            if (a.winLength > 0) {
//                System.out.println(a);
//                System.out.println(a.win());
//            }
//        }
        out.println(pos[n - 1] - pos[0] - total);
        out.close();
    }

    static int n;
    static Arrow[] arrows;
    static int[] heights;
    static int[] pos;

    public static class Arrow {

        int leftID, rightID;
        int oLeft, oRight;
        boolean canLeft, canRight;
        int winLength;

        void calcCan() {
            int l = length();
            canLeft = l > heights[leftID];
            canRight = l > heights[rightID];
        }

        int length() {
            return pos[rightID] - pos[leftID];
        }

        boolean win() {
            return (leftID == 0 && canLeft) || (rightID == n - 1 && canRight);
        }

        @Override
        public String toString() {
            return "Arrow{" + "leftID=" + leftID + ", rightID=" + rightID + ", canLeft=" + canLeft + ", canRight=" + canRight + ", winLength=" + winLength + '}';
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
