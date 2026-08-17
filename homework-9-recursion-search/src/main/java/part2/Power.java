package part2;

public class Power {
    public static double power(double x, int n) {
        if (n == 0) {
            return 1;
        }
        return x * power(x, n - 1);
    }

    public static void main(String[] args) {
        System.out.println("power(2, 0) = " + power(2, 0));
        System.out.println("power(2, 3) = " + power(2, 3));
        System.out.println("power(5, 4) = " + power(5, 4));
        System.out.println("power(1.5, 3) = " + power(1.5, 3));
        System.out.println("power(10, 2) = " + power(10, 2));
    }
}
