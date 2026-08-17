package part1;

public class PersonDriver {
    public static void main(String[] args) {
        Person p1 = new Person("Anna", 2001);
        Person p2 = new Person("Bohdan", 1998);
        Person p3 = new Person("Olena", 2001);
        Person p4 = new Person("Dmytro", 2005);

        System.out.println("p1 = " + p1);
        System.out.println("p2 = " + p2);
        System.out.println("p3 = " + p3);
        System.out.println("p4 = " + p4);
        System.out.println();

        System.out.println("p1.compareTo(p4) = " + p1.compareTo(p4) + "  (2001 < 2005, less)");
        System.out.println("p4.compareTo(p1) = " + p4.compareTo(p1) + "  (2005 > 2001, bigger)");
        System.out.println("p1.compareTo(p3) = " + p1.compareTo(p3) + "  (2001 == 2001, equals)");
        System.out.println("p2.compareTo(p1) = " + p2.compareTo(p1) + "  (1998 < 2001, less)");
        System.out.println("p1.compareTo(p2) = " + p1.compareTo(p2) + "  (2001 > 1998, bigger)");
    }
}
