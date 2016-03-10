
import java.io.*;
import java.util.*;

public class mowing {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("mowing.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mowing.out")));

        List<Integer> d = tokenizeInt(in);
        int n = d.get(0);
        int t = d.get(1);

        int count = 0;

        Queue<Cut> toRegrow = new LinkedList();
        //List<Cut> regrownH = new LinkedList();
        //List<Cut> regrownV = new LinkedList();
        BinTree hor = null, ver = null;
        for (int i = 0; i < n; i++) {
            Cut c = new Cut(tokenizeInt(in));
            if (i >= t) {
                Cut p = toRegrow.poll();
                if ((p.isVert ? ver : hor) == null) {
                    if (p.isVert) {
                        ver = new BinTree(p);
                    } else {
                        hor = new BinTree(p);
                    }
                } else {
                    (p.isVert ? ver : hor).add(p);
                }

                //System.out.println(c);
                BinTree checkAgainst = c.isVert ? hor : ver;
                if (checkAgainst != null) {
                    count += checkAgainst.countIntersections(c);
                }
            }
            toRegrow.add(c);
        }

//        System.out.println(hor);
//        System.out.println(ver);
        out.println(count);
        out.close();
    }

    public static class Cut {

        public static int prevX, prevY;

        private int x1, x2, y1, y2;
        boolean isVert;

        public Cut(List<Integer> d) {
            this.x1 = prevX;
            this.x2 = prevX = d.get(0);
            this.y1 = prevY;
            this.y2 = prevY = d.get(1);
            isVert = x1 == x2;
        }

        public boolean intersects(Cut other) {
            return max() > other.same() && min() < other.same() && other.max() > same() && other.min() < same();
        }

        public int max() {
            return isVert ? Math.max(y1, y2) : Math.max(x1, x2);
        }

        public int min() {
            return isVert ? Math.min(y1, y2) : Math.min(x1, x2);
        }

        public int same() {
            return isVert ? x1 : y1;
        }

        @Override
        public String toString() {
            return "Cut{" + "x1=" + x1 + ", x2=" + x2 + ", y1=" + y1 + ", y2=" + y2 + ", isVert=" + isVert + '}';
        }
    }

    public static class BinTree {

        public Cut cut;
        public BinTree less, more;
        public IntTree starts, ends;
        public int smallest, largest;

        public BinTree(Cut c) {
            cut = c;
            starts = new IntTree(c.min());
            ends = new IntTree(c.max());
            smallest = largest = c.same();
        }

        public void add(Cut c) {
            smallest = Math.min(smallest, c.same());
            largest = Math.max(largest, c.same());
            starts.add(c.min());
            ends.add(c.max());
            if (c.same() > cut.same()) {
                if (more == null) {
                    more = new BinTree(c);
                } else {
                    more.add(c);
                }
            } else if (less == null) {
                less = new BinTree(c);
            } else {
                less.add(c);
            }
        }

        public int countIntersections(Cut o) {
            if (o.max() > largest && o.min() < smallest) {
                return starts.indexOf(o.same()) - ends.indexOf(o.same());
            }
            if (o.max() < smallest || o.min() > largest) {
                return 0;
            }
            int r = 0;
            if (cut.intersects(o)) {
                r++;
            }
            if (more != null) {
                r += more.countIntersections(o);
            }
            if (less != null) {
                r += less.countIntersections(o);
            }
            return r;
        }

        @Override
        public String toString() {
            return "BinTree{" + "cut=" + cut + ", \nless=" + less + ", \nmore=" + more + ", \nstarts=" + starts + ", ends=" + ends + ", smallest=" + smallest + ", largest=" + largest + '}';
        }
    }

    public static class IntTree {

        public int val;
        public IntTree less, more;
        public int smallest, largest;
        public int size = 1;

        public IntTree(int val) {
            this.val = smallest = largest = val;
        }

        public void add(int element) {
            smallest = Math.min(smallest, element);
            largest = Math.max(largest, element);
            if (element > val) {
                if (more == null) {
                    more = new IntTree(element);
                } else {
                    more.add(element);
                }
            } else if (less == null) {
                less = new IntTree(element);
            } else {
                less.add(element);
            }
            size++;
        }

        public int indexOf(int element) {
            if (element > largest) {
                return size;
            }
            if (element < smallest) {
                return 0;
            }
            int r = 0;
            if (element > val) {
                r++;
            }
            if (more != null) {
                r += more.indexOf(element);
            }
            if (less != null) {
                r += less.indexOf(element);
            }
            return r;
        }

        @Override
        public String toString() {
            return "IntTree{" + "val=" + val + ", less=" + less + ", more=" + more + ", smallest=" + smallest + ", largest=" + largest + ", size=" + size + '}';
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
