package part4;

public class QuickTrace {
    public static void main(String[] args) {
        Integer[] a = {5, 4, 2, 9, 1, 7, 3, 8, 6};
        System.out.println(format(a, 0, a.length - 1));
        sort(a, 0, a.length - 1, 1);
    }

    private static void sort(Comparable[] a, int lo, int hi, int depth) {
        if (hi <= lo) {
            return;
        }

        System.out.println(depth + ": Pivot = " + a[lo]);
        int j = partition(a, lo, hi, depth);
        System.out.println(depth + ": Partition: " + (j - lo));
        System.out.println(depth + ": " + format(a, lo, hi) + " - Array after partitioning");

        if (lo <= j - 1) {
            System.out.println(depth + ": " + format(a, lo, j - 1) + " - sort left part");
        } else {
            System.out.println(depth + ": [] - sort left part");
        }
        sort(a, lo, j - 1, depth + 1);

        if (j + 1 <= hi) {
            System.out.println(depth + ": " + format(a, j + 1, hi) + " - sort right part");
        } else {
            System.out.println(depth + ": [] - sort right part");
        }
        sort(a, j + 1, hi, depth + 1);

        System.out.println(depth + ": " + format(a, lo, hi) + " - sort result");
    }

    private static int partition(Comparable[] a, int lo, int hi, int depth) {
        int i = lo;
        int j = hi + 1;
        while (true) {
            while (less(a[++i], a[lo])) {
                if (i == hi) {
                    break;
                }
            }
            while (less(a[lo], a[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            System.out.println(depth + ": swap " + a[i] + " and " + a[j]);
            exch(a, i, j);
        }
        System.out.println(depth + ": swap " + a[lo] + " and " + a[j]);
        exch(a, lo, j);
        return j;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static String format(Comparable[] a, int lo, int hi) {
        if (hi < lo) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = lo; i <= hi; i++) {
            sb.append(a[i]);
            if (i < hi) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
