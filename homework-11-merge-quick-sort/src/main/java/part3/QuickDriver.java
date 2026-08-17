package part3;

import java.util.Arrays;

public class QuickDriver {
    public static void main(String[] args) {
        Integer[] a = {5, 2, 7, 0, 3, 9};
        System.out.println("Before: " + Arrays.toString(a));
        Quick.sort(a);
        System.out.println("After:  " + Arrays.toString(a));

        Integer[] b = {5, 4, 2, 9, 1, 7, 3, 8, 6};
        System.out.println("Before: " + Arrays.toString(b));
        Quick.sort(b);
        System.out.println("After:  " + Arrays.toString(b));

        String[] c = {"dog", "cat", "apple", "banana"};
        System.out.println("Before: " + Arrays.toString(c));
        Quick.sort(c);
        System.out.println("After:  " + Arrays.toString(c));
    }
}
