package part1_class_c;

public class CDriver {
    public static void main(String[] args) {
        C first = new C();
        System.out.println("Default object:");
        System.out.println("x = " + first.getX());
        System.out.println("y = " + first.getY());
        System.out.println("CONST1 = " + C.CONST1);

        C second = new C(5, 10, "point");
        System.out.println("\nSecond object:");
        System.out.println("x = " + second.getX());
        System.out.println("y = " + second.getY());

        second.setX(7);
        second.setY(12);
        System.out.println("\nAfter setX and setY:");
        System.out.println("x = " + second.getX());
        System.out.println("y = " + second.getY());
    }
}
