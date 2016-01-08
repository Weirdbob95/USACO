
import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class maxflow {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("maxflow.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maxflow.out")));

        List<Integer> a = tokenizeInt(in);
        int n = a.get(0);
        parent = new int[n];
        depth = new int[n];
        flow = new int[n];
        {
            Node[] nodes = new Node[n];
            for (int i = 0; i < n; i++) {
                nodes[i] = new Node(i);
            }
            for (int i = 1; i < n; i++) {
                List<Integer> l = tokenizeInt(in);
                nodes[l.get(0) - 1].connected.add(nodes[l.get(1) - 1]);
                nodes[l.get(1) - 1].connected.add(nodes[l.get(0) - 1]);
            }
            nodes[0].r(-1, 0);
        }
        for (int i = 0; i < a.get(1); i++) {
            List<Integer> l = tokenizeInt(in);
            int n1 = l.get(0) - 1;
            int n2 = l.get(1) - 1;
            while (true) {
                if (n1 == n2) {
                    flow[n1]++;
                    break;
                }
                if (depth[n1] > depth[n2]) {
                    flow[n1]++;
                    n1 = parent[n1];
                } else {
                    flow[n2]++;
                    n2 = parent[n2];
                }
            }
        }

        int maxFlow = 0;
        for (int i = 0; i < n; i++) {
            maxFlow = Math.max(maxFlow, flow[i]);
        }
        out.println(maxFlow);
        out.close();
    }

    public static int[] parent;
    public static int[] depth;
    public static int[] flow;

    public static List<Integer> tokenizeInt(BufferedReader in) throws IOException {
        List<Integer> r = new LinkedList();
        for (Object s : Collections.list(new StringTokenizer(in.readLine()))) {
            r.add(Integer.parseInt((String) s));
        }
        return r;
    }

    public static class Node {

        public int v;
        public List<Node> connected = new LinkedList();

        public Node(int v) {
            this.v = v;
        }

        public void r(int from, int d) {
            depth[v] = d;
            parent[v] = from;
            for (Node n : connected) {
                if (n.v != from) {
                    n.r(v, d + 1);
                }
            }
        }
    }
}
