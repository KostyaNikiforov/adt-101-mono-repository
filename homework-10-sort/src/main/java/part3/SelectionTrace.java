package part3;

public class SelectionTrace {
    public static void main(String[] args) {
        Integer[] a = {13, 75, 12, 4, 18, 6, 9, 10, 7, 14, 15};

        System.out.println("Part 3. Selection Sort Trace");
        System.out.println("Initial: " + format(a));
        System.out.println();

        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (a[j].compareTo(a[min]) < 0) {
                    min = j;
                }
            }
            Integer t = a[i];
            a[i] = a[min];
            a[min] = t;

            System.out.println("After i=" + i + ": " + formatMarked(a, i, min));
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

    private static String formatMarked(Integer[] a, int sortedUntil, int swappedWith) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a.length; i++) {
            if (i == sortedUntil || i == swappedWith) {
                sb.append("*").append(a[i]).append("*");
            } else if (i <= sortedUntil) {
                sb.append(a[i]);
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
