
import java.io.*;
import java.util.*;

public class balancing {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("balancing.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("balancing.out")));

        int n = Integer.parseInt(in.readLine());
        List<List<Integer>> cows = new LinkedList();
        for (int i = 0; i < n; i++) {
            cows.add(tokenizeInt(in));
        }

//        HorizontalBinaryTree horTree = new HorizontalBinaryTree(cows.get(0).get(0), cows.get(0).get(1));
//        for (int i = 1; i < n; i++) {
//            horTree.add(cows.get(i).get(0), cows.get(i).get(1));
//        }
        Set<Integer> xOptions = new TreeSet();
        for (List<Integer> cow : cows) {
            xOptions.add(cow.get(0));
        }
        List<Integer> xList = new ArrayList(xOptions);
        Map<Integer, VerticalBinaryTree> verTrees = new HashMap();
        for (List<Integer> cow : cows) {
            if (!verTrees.containsKey(cow.get(0))) {
                verTrees.put(cow.get(0), new VerticalBinaryTree(cow.get(1)));
            } else {
                verTrees.get(cow.get(0)).add(cow.get(1));
            }
        }

        Set<Integer> yOptions = new TreeSet();
        for (List<Integer> cow : cows) {
            yOptions.add(cow.get(1));
        }
        List<Integer> yList = new ArrayList(yOptions);
        Map<Integer, VerticalBinaryTree> horTrees = new HashMap();
        for (List<Integer> cow : cows) {
            if (!horTrees.containsKey(cow.get(1))) {
                horTrees.put(cow.get(1), new VerticalBinaryTree(cow.get(0)));
            } else {
                horTrees.get(cow.get(1)).add(cow.get(0));
            }
        }

        int min = n;

        int yPos = 0;
        int dir = 1;
        int UL = 0, LR = 0, LL = 0;
        for (int xVal : xList) {
            while (true) {
                int max = Math.max(n - UL - LR - LL, Math.max(LL, Math.max(UL, LR)));
                min = Math.min(max, min);
                boolean downBig = (LR == max || LL == max);

                //if (LL < 0 || UL < 0 || LR < 0) {
//                System.out.println(xVal - 1 + " " + (yList.get(yPos) - 1));
//                System.out.println(LL + " " + UL + " " + LR);
//                System.out.println(dir + " " + max + " " + downBig);
                //}
//
//                if (max == 5) {
//                    System.out.println(xVal - 1 + " " + (yList.get(yPos) - 1));
//                    System.out.println(LL + " " + UL + " " + LR);
//                    System.out.println(dir + " " + max + " " + downBig);
//                }
                if ((dir == 1 && (downBig || yPos >= yList.size() - 1)) || (dir == -1 && (!downBig || yPos <= 0))) {
                    VerticalBinaryTree t = verTrees.get(xVal);
                    int bottom = t.range(0, yList.get(yPos) - 1);
                    LR -= bottom;
                    UL += t.sum - bottom;
                    LL += bottom;
                    dir = 0;
                    break;
                } else {
                    if (dir == 0) {
                        dir = downBig ? -1 : 1;
                    }
                    if (dir == 1) {
                        VerticalBinaryTree t = horTrees.get(yList.get(yPos));
                        int bottom = t.range(0, xVal - 1);
                        UL -= bottom;
                        LR += t.sum - bottom;
                        LL += bottom;
                    } else {
                        VerticalBinaryTree t = horTrees.get(yList.get(yPos - 1));
                        int bottom = t.range(0, xVal - 1);
                        UL += bottom;
                        LR -= t.sum - bottom;
                        LL -= bottom;
                    }
                    yPos += dir;
                }
            }
        }
//        {
//            int xPos = 0, yPos = 0;
//            int UL = 0, LR = 0, LL = 0;
//            while (xPos < xList.size() && yPos < yList.size()) {
//                //System.out.println(xList.get(xPos) - 1 + " " + (yList.get(yPos) - 1));
//                //System.out.println(LL + " " + UL + " " + LR);
//
//                int max = Math.max(n - UL - LR - LL, Math.max(LL, Math.max(UL, LR)));
//                min = Math.min(max, min);
//
//                if (UL > LR) {
//                    VerticalBinaryTree t = horTrees.get(yList.get(yPos));
//                    int bottom = t.range(0, xList.get(xPos) - 1);
//                    UL -= bottom;
//                    LR += t.sum - bottom;
//                    LL += bottom;
//                    yPos++;
//                } else if (UL < LR) {
//                    VerticalBinaryTree t = verTrees.get(xList.get(xPos));
//                    int bottom = t.range(0, yList.get(yPos) - 1);
//                    LR -= bottom;
//                    UL += t.sum - bottom;
//                    LL += bottom;
//                    xPos++;
//                } else {
//                    VerticalBinaryTree t = verTrees.get(xList.get(xPos));
//                    int bottom = t.range(0, yList.get(yPos) - 1);
//                    LR -= bottom;
//                    UL += t.sum - bottom;
//                    LL += bottom;
//                    xPos++;
//                }
//            }
//        }
//        {
//            int xPos = 0, yPos = yList.size() - 1;
//            int LL = 0, UR = 0, UL = 0;
//            while (xPos < xList.size() && yPos >= 0) {
//                //System.out.println(xList.get(xPos) - 1 + " " + (yList.get(yPos) + 1));
//                //System.out.println(LL + " " + UL + " " + UR);
//
//                int max = Math.max(n - LL - UR - UL, Math.max(UL, Math.max(LL, UR)));
//                min = Math.min(max, min);
//
//                if (LL > UR) {
//                    VerticalBinaryTree t = horTrees.get(yList.get(yPos));
//                    int bottom = t.range(0, xList.get(xPos) - 1);
//                    LL -= bottom;
//                    UR += t.sum - bottom;
//                    UL += bottom;
//                    yPos--;
//                } else if (LL < UR) {
//                    VerticalBinaryTree t = verTrees.get(xList.get(xPos));
//                    int bottom = t.range(0, yList.get(yPos) - 1);
//                    UR -= t.sum - bottom;
//                    LL += bottom;
//                    UL += t.sum - bottom;
//                    xPos++;
//                } else {
//                    VerticalBinaryTree t = verTrees.get(xList.get(xPos));
//                    int bottom = t.range(0, yList.get(yPos) - 1);
//                    UR -= t.sum - bottom;
//                    LL += bottom;
//                    UL += t.sum - bottom;
//                    xPos++;
//                }
//            }
//        }

        /*
        Set<Integer> verticalLinePos = new HashSet();
        verticalLinePos.add(0);
        for (List<Integer> cow : cows) {
            verticalLinePos.add(cow.get(0) + 1);
        }

        int min = n;

        for (int x : verticalLinePos) {
            List<VerticalBinaryTree> left = horTree.range(0, x);
            List<VerticalBinaryTree> right = horTree.range(x, 1000000);
            VerticalBinaryTree pos = verTree;
            while (pos != null) {
                int UL = 0;
                for (VerticalBinaryTree t : left) {
                    UL += t.range(pos.pos, 1000000);
                }
                int LL = 0;
                for (VerticalBinaryTree t : left) {
                    LL += t.range(0, pos.pos);
                }
                int UR = 0;
                for (VerticalBinaryTree t : right) {
                    UR += t.range(pos.pos, 1000000);
                }
                int LR = 0;
                for (VerticalBinaryTree t : right) {
                    LR += t.range(0, pos.pos);
                }
                int max = Math.max(UL, Math.max(LL, Math.max(UR, LR)));
                min = Math.min(min, max);

                if (Math.max(UL, UR) > Math.max(LL, LR)) {
                    pos = pos.more;
                } else {
                    pos = pos.less;
                }
            }
        }
         */
        out.println(min);
        out.close();
    }

    public static class HorizontalBinaryTree {

        public final int pos;
        public VerticalBinaryTree value;
        public VerticalBinaryTree sum;

        public int smallest, largest;
        public HorizontalBinaryTree less, more;

        public HorizontalBinaryTree(int x, int y) {
            pos = smallest = largest = x;
            value = new VerticalBinaryTree(y);
            sum = new VerticalBinaryTree(y);
        }

        public void add(int x, int y) {
            sum.add(y);
            smallest = Math.min(smallest, x);
            largest = Math.max(largest, x);

            if (x == pos) {
                value.add(y);
            } else if (x < pos) {
                if (less == null) {
                    less = new HorizontalBinaryTree(x, y);
                } else {
                    less.add(x, y);
                }
            } else if (x > pos) {
                if (more == null) {
                    more = new HorizontalBinaryTree(x, y);
                } else {
                    more.add(x, y);
                }
            }
        }

        public List<VerticalBinaryTree> range(int min, int max) {
            if (smallest > max || largest < min) {
                return new LinkedList();
            }
            List<VerticalBinaryTree> r = new LinkedList();
            if (pos > min && pos < max) {
                r.add(value);
            }
            if (less != null) {
                r.addAll(less.range(min, max));
            }
            if (more != null) {
                r.addAll(more.range(min, max));
            }
            return r;
        }

        public int range(int minX, int maxX, int minY, int maxY) {
            int r = 0;
            for (VerticalBinaryTree t : range(minX, maxX)) {
                r += t.range(minY, maxY);
            }
            return r;
        }
    }

    public static class VerticalBinaryTree {

        public final int pos;
        public int value;
        public int sum;

        public int smallest, largest;
        public VerticalBinaryTree less, more;

        public VerticalBinaryTree(int y) {
            pos = smallest = largest = y;
            value = sum = 1;
        }

        public void add(int y) {
            sum++;
            smallest = Math.min(smallest, y);
            largest = Math.max(largest, y);

            if (y == pos) {
                value++;
            } else if (y < pos) {
                if (less == null) {
                    less = new VerticalBinaryTree(y);
                } else {
                    less.add(y);
                }
            } else if (y > pos) {
                if (more == null) {
                    more = new VerticalBinaryTree(y);
                } else {
                    more.add(y);
                }
            }
        }

        public int range(int min, int max) {
            if (smallest > max || largest < min) {
                return 0;
            }
            int r = 0;
            if (pos > min && pos < max) {
                r++;
            }
            if (less != null) {
                r += less.range(min, max);
            }
            if (more != null) {
                r += more.range(min, max);
            }
            return r;
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
