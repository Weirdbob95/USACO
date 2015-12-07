/*
 ID: rory.so1
 LANG: JAVA
 TASK: sort3
 */

import java.io.*;

class sort3 {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("sort3.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));

        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        int[] c = new int[3];
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(in.readLine()) - 1;
            a[i] = x;
            c[x]++;
        }

        int b = 0;
        for (int i = 0; i < c[0]; i++) {
            if (a[i] != 0) {
                b++;
                if (a[i] == 1) {
                    for (int j = c[0];; j++) {
                        if (a[j] == 0) {
                            a[i] = 0;
                            a[j] = 1;
                            break;
                        }
                    }
                } else {
                    for (int j = n - 1;; j--) {
                        if (a[j] == 0) {
                            a[i] = 0;
                            a[j] = 2;
                            break;
                        }
                    }
                }
            }
        }
        for (int i = c[0]; i < c[0] + c[1]; i++) {
            if (a[i] != 1) {
                b++;
                for (int j = c[0] + c[1];; j++) {
                    if (a[j] == 1) {
                        a[i] = 1;
                        a[j] = 2;
                        break;
                    }
                }

            }
        }
        out.println(b);

        out.close();
    }
}
