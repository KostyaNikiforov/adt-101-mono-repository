package part2;

public class MergeTrace {
    public static void main(String[] args) {
        Integer[] a = {9, 0, 8, 3, 7, 1, 4};
        System.out.println("Mergesort:");
        System.out.println(format(a, 0, a.length - 1));
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1, 0, "");
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi, int depth, String label) {
        if (depth > 0) {
            System.out.println(depth + ": " + format(a, lo, hi) + " - " + label);
        }

        if (hi <= lo) {
            return;
        }

        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid, depth + 1, "sort left half");
        sort(a, aux, mid + 1, hi, depth + 1, "sort right half");
        merge(a, aux, lo, mid, hi);
        System.out.println((depth + 1) + ": " + format(a, lo, hi) + " - merged and sorted");
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static String format(Comparable[] a, int lo, int hi) {
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
