
public class randprinter {

    public static void main(String[] args) {
        int x = 0;
        int y = 0;
        for (int i = 0; i < 50000; i++) {
            x = (int) (Math.random() * 1000000000);
            System.out.println(x + " " + y);
            y = (int) (Math.random() * 1000000000);
            System.out.println(x + " " + y);
        }
    }
}
