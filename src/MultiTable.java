
public class MultiTable {

    public static void main(String[] args) {
        printMultiTable(1, 9);
    }

    private static void printMultiTable(int startNumber, int limit) {

        for (int i = startNumber; i <= limit; i++) {
            for (int j = 1; j <= limit; j++) {
                System.out.printf("%d * %d = %d\n", i, j, i * j);
            }
        }

    }
}
