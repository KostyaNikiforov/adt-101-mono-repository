package part1;

public class Sum1toN {
    public static int sum1toN(int n) {
        if (n == 1) {
            return 1;
        }
        return n + sum1toN(n - 1);
    }

    public static void main(String[] args) {
        System.out.println("sum1toN(1) = " + sum1toN(1));
        System.out.println("sum1toN(5) = " + sum1toN(5));
        System.out.println("sum1toN(10) = " + sum1toN(10));
        System.out.println("sum1toN(100) = " + sum1toN(100));
    }
}
