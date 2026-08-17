package part1;

import java.util.Arrays;

public class MergeDriver {
    public static void main(String[] args) {
        Integer[] a = {5, 2, 7, 0, 3, 9};
        System.out.println("Before: " + Arrays.toString(a));
        Merge.sort(a);
        System.out.println("After:  " + Arrays.toString(a));

        Integer[] b = {9, 0, 8, 3, 7, 1, 4};
        System.out.println("Before: " + Arrays.toString(b));
        Merge.sort(b);
        System.out.println("After:  " + Arrays.toString(b));

        String[] c = {"dog", "cat", "apple", "banana"};
        System.out.println("Before: " + Arrays.toString(c));
        Merge.sort(c);
        System.out.println("After:  " + Arrays.toString(c));
    }
}
