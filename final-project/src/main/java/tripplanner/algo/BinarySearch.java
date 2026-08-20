package tripplanner.algo;

public class BinarySearch {
    public static int search(Comparable[] a, Comparable key) {
        int left = 0;
        int right = a.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            int cmp = a[mid].compareTo(key);
            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
