/*
 ID: rory.so1
 LANG: JAVA
 TASK: namenum
 */

import java.io.*;

class namenum {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("namenum.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));

        String n = in.readLine();
        String output = "";

        BufferedReader dict = new BufferedReader(new FileReader("dict.txt"));
        for (String name = dict.readLine(); dict.ready(); name = dict.readLine()) {
            if (name.length() == n.length()) {
                boolean good = true;
                for (int i = 0; i < n.length(); i++) {
                    if (n.charAt(i) != charToNum(name.charAt(i))) {
                        good = false;
                        break;
                    }
                }
                if (good) {
                    output += name + "\n";
                }
            }
        }

        if (output.length() == 0) {
            output = "NONE";
        } else {
            output = output.substring(0, output.length() - 1);
        }

        out.println(output);
        out.close();
    }

    public static char charToNum(char c) {
        c = Character.toLowerCase(c);
        switch (c) {
            case 'a':
            case 'b':
            case 'c':
                return '2';
            case 'd':
            case 'e':
            case 'f':
                return '3';
            case 'g':
            case 'h':
            case 'i':
                return '4';
            case 'j':
            case 'k':
            case 'l':
                return '5';
            case 'm':
            case 'n':
            case 'o':
                return '6';
            case 'p':
            case 'r':
            case 's':
                return '7';
            case 't':
            case 'u':
            case 'v':
                return '8';
            case 'w':
            case 'x':
            case 'y':
                return '9';
            default:
                return '-';
        }
    }
}
