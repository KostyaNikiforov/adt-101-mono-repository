package part2;

import java.util.Arrays;

public class SelectionDriver {
    public static void main(String[] args) {
        Integer[] a = {5, 2, 7, 0, 3, 9};
        System.out.println("Before: " + Arrays.toString(a));
        Selection.sort(a);
        System.out.println("After:  " + Arrays.toString(a));

        Integer[] b = {13, 75, 12, 4, 18, 6, 9, 10, 7, 14, 15};
        System.out.println("Before: " + Arrays.toString(b));
        Selection.sort(b);
        System.out.println("After:  " + Arrays.toString(b));

        String[] c = {"dog", "cat", "apple", "banana"};
        System.out.println("Before: " + Arrays.toString(c));
        Selection.sort(c);
        System.out.println("After:  " + Arrays.toString(c));
    }
}
