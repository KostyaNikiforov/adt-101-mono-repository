package part5;

public class InsertionTrace {
    public static void main(String[] args) {
        Integer[] a = {13, 75, 12, 4, 18, 6, 9, 10, 7, 14, 15};

        System.out.println("Part 5. Insertion Sort Trace");
        System.out.println("Initial: " + format(a));
        System.out.println();

        int N = a.length;
        for (int i = 0; i < N; i++) {
            Integer key = a[i];
            int j = i;
            for (; j > 0; j--) {
                if (a[j].compareTo(a[j - 1]) < 0) {
                    Integer t = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = t;
                } else {
                    break;
                }
            }

            System.out.println("After i=" + i + ": " + formatMarked(a, i, j)
                    + "  (inserted " + key + ")");
        }
    }

    private static String format(Integer[] a) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i]);
            if (i < a.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private static String formatMarked(Integer[] a, int sortedUntil, int insertedAt) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a.length; i++) {
            if (i == insertedAt) {
                sb.append("*").append(a[i]).append("*");
            } else {
                sb.append(a[i]);
            }
            if (i < a.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
